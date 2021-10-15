package core;

//import automation.library.alm.core.ALMContext;

import com.google.gson.JsonSyntaxException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.ReadContext;
import common.Constants;
import common.FileHelper;
import common.Property;
import common.TestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class DataSpecHelper {
    public static final String QUERY_NAME = "queryName";
    private static List<DataSpecHelper> threads = new ArrayList<>();
    private long threadToEnvID;
    private List<Map<String, Object>> dataResultList = new ArrayList<>();
    private static final String ERROR_RESPONSE = "-1";
    private ReadContext jsonFile = null;
    private ReadContext priorJsonFile = null;
    private String rootAttribute = null;

    protected final Logger log = LogManager.getLogger(DataSpecHelper.class);

    private DataSpecHelper() {
        //Utility class
    }

    private DataSpecHelper(long threadID) {
        this.threadToEnvID = threadID;
    }

    public static synchronized DataSpecHelper getInstance() {
        long currentRunningThreadID = Thread.currentThread().getId();
        for (DataSpecHelper thread : threads) {
            if (thread.threadToEnvID == currentRunningThreadID) {
                return thread;
            }
        }
        DataSpecHelper temp = new DataSpecHelper(currentRunningThreadID);
        threads.add(temp);
        return temp;
    }

    public List<Map<String, Object>> processDataSpec(String testName) {
        jsonFile = readJsonFile(testName);

        if (jsonFile != null) {
            setRootAttribute();
            if (rootAttribute != null) {
                addQueryDataToResultList();
                addStaticDataToResultList();
            }
        }

        addExternalFlatFileData(testName);

        if (dataResultList.isEmpty()) {
            log.warn("Data spec and/or data file not found this test. Skipping data load.");
            return Collections.emptyList();
        } else {
            return dataResultList;
        }


    }

    private void setRootAttribute() {
        try {
            jsonFile.read("$.TDaaS", Map.class);
            rootAttribute = "TDaaS";
        } catch (JsonPathException e) {
            try {
                jsonFile.read("$.DataAutomation", Map.class);
                rootAttribute = "DataAutomation";
            } catch (JsonPathException e2) {
                log.warn(e2.getMessage());
            }
        }
    }

    private ReadContext readJsonFile(String testName) {
        String pathToDataSpec = FileHelper.findFileInPath(common.Constants.FEATUREFILEPATH, testName + ".json");

        if (pathToDataSpec != null) {
            try {
                return JsonPath.parse(new FileInputStream(new File(pathToDataSpec)));
            } catch (FileNotFoundException | NullPointerException | JsonSyntaxException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        } else {
            return null;
        }
    }

    private void addQueryDataToResultList() {
        Map<String, Object> sqlContent = jsonFile.read(getTDaaSJsonPath(getSQLAttribute()), Map.class);

        if (sqlContent != null &&
            sqlContent.containsKey(QUERY_NAME) &&
            !sqlContent.get(QUERY_NAME).toString().isEmpty()) {
            TDaaSClient queryClient = new TDaaSClient(jsonFile.read(getTDaaSJsonPath("sharedAttributes"), Map.class));

            List<Map<String, Object>> resultSet = queryClient.runTDaaSQuery(sqlContent.get(QUERY_NAME).toString(),
                                                                            getParamList(sqlContent));

            //filter by output columns and add to result set
            resultSet.forEach(dataRow -> dataResultList.add(dataRow.entrySet()
                                                                   .stream().filter(entry -> entry.getKey().matches(getOutputColumns()))
                                                                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        }

    }

    private String getSQLAttribute() {
        try {
            jsonFile.read(getTDaaSJsonPath("sql"), Map.class);
            return "sql";
        } catch (JsonPathException e) {
            try {
                jsonFile.read(getTDaaSJsonPath("api"), Map.class);
                return "api";
            } catch (JsonPathException e2) {
                log.warn(e2.getMessage());
                return "";
            }
        }
    }


    private String getOutputColumns() {
        List<String> outputColumns = jsonFile.read(getTDaaSJsonPath("dataOutput"), List.class);
        StringJoiner filterRegEx = new StringJoiner("|", ".*(?i)(", ")(?i).*");

        if (!outputColumns.isEmpty()) {
            outputColumns.stream()
                         .filter(entry -> entry != null && entry.length() > 0)
                         .collect(Collectors.toList())
                         .forEach(column -> filterRegEx.add(splitColumnNameBySpace(column)));
            return filterRegEx.toString();
        }

        return ERROR_RESPONSE;
    }

    private String splitColumnNameBySpace(String column) {
        List<String> columnNames = Arrays.asList(column.trim().split("\\s+"));

        if (columnNames.size() > 1) {
            return String.join("|", columnNames);
        } else
            return column;
    }

    private Map<String, Object> getParamList(Map<String, Object> sqlContent) {
        return sqlContent.entrySet()
                         .stream()
                         .filter(entry -> entry.getKey().matches(".*(?i)(param)(?i).*"))
                         .collect(Collectors.toMap(Map.Entry::getKey,
                                                   entry -> parseJsonPathVal(entry.getValue().toString())));


    }

    private void addStaticDataToResultList() {
        Map<String, Object> staticData = jsonFile.read(getTDaaSJsonPath("staticData"), Map.class);

        if (staticData != null) {
            Map<String, Object> staticDataFiltered = staticData.entrySet()
                                                               .stream()
                                                               .filter(entry -> !entry.getValue().toString().isEmpty())
                                                               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (!dataResultList.isEmpty()) {
                dataResultList.forEach(dataRow -> staticDataFiltered.forEach(dataRow::put));
            } else
                dataResultList.add(staticDataFiltered);
        }
    }

    private String getTDaaSJsonPath(String jsonPathAddition) {
        StringJoiner jsonPath = new StringJoiner(".");

        jsonPath.add("$");
        jsonPath.add(rootAttribute);
        jsonPath.add(jsonPathAddition);

        return jsonPath.toString();
    }

    private String parseJsonPathVal(String valToParse) {
        if (valToParse.matches("^\\$\\..*")) {
            try {
                if (valToParse.matches(".*\\.\\..*")) {
                    List<String> actualVal = jsonFile.read(valToParse, List.class);
                    return actualVal.get(0);
                } else {
                    return jsonFile.read(valToParse, String.class);
                }
            } catch (JsonPathException e) {
                log.error("Invalid JSON Path: {} Returning actual value.", valToParse);
                return valToParse;
            } catch (ClassCastException e) {
                log.error("JSON Path type mismatch. Check for array references {}", valToParse);
                return valToParse;
            }
        } else {
            return valToParse;
        }
    }

    private void addExternalFlatFileData(String testName) {
        String fileName = testName + ".xlsx";
        String pathToFlatFile = FileHelper.findFileInPath(Constants.TESTDATAPATH, fileName);

        if (pathToFlatFile != null) {
            List<Map<String, Object>> fileDataResultList = FileHelper.getExcelDataAsMapWithoutIndex(pathToFlatFile,
                                                                                                    Property.getVariable("cukes.env"));

            if (!dataResultList.isEmpty()) {
                List<Map<String, Object>> tempDataResultList = new ArrayList<>();
                mergeExcelToDataSet(fileDataResultList, tempDataResultList);

                dataResultList = new ArrayList<>(tempDataResultList);
            } else {
                dataResultList = new ArrayList<>(fileDataResultList);
            }
        }
    }

    private void mergeExcelToDataSet(List<Map<String, Object>> fileDataResultList,
                                     List<Map<String, Object>> tempDataResultList) {
        //Merge data sets
        for (int dataListCtr = 0; dataListCtr < dataResultList.size(); dataListCtr++) {
            Map<String, Object> dataResultMapEntry = dataResultList.get(dataListCtr);
            if (fileDataResultList.size() > dataListCtr) {
                Map<String, Object> excelMapEntry = fileDataResultList.get(dataListCtr);

                excelMapEntry.forEach(
                        (key, value) -> dataResultMapEntry.merge(key, value,
                                                                 (dataMapVal, excelMapVal) -> dataMapVal.toString()
                                                                                                        .equalsIgnoreCase(excelMapVal.toString()) ? dataMapVal : excelMapVal));
            }

            tempDataResultList.add(dataResultMapEntry);
        }
    }

    public Map<String, Object> processPreBatchDataSpec(String flow) {
        Map<String, Object> prebatchDataResultList = null;
        String prebatchDataFilePath = "acc";

        if (prebatchDataFilePath != null) {
            try {
                priorJsonFile = JsonPath.parse(new FileInputStream(new File(prebatchDataFilePath)));
            } catch (FileNotFoundException | NullPointerException | JsonSyntaxException e) {
                log.error(e.getMessage(), e);
            }
            prebatchDataResultList = priorJsonFile.read("lastRun", Map.class);
            List<Map<String, Object>> dataTableList = priorJsonFile.read("$..priorData[*]", List.class);
            dataTableList.add(prebatchDataResultList);
            TestContext.getInstance().testdataPut("priorData", dataTableList);
            if (prebatchDataResultList == null || prebatchDataResultList.isEmpty()) {
                log.warn("Data spec and/or data file not found this test. Skipping prior test data load.");
                return Collections.emptyMap();
            } else {
                return prebatchDataResultList;
            }
        } else return Collections.emptyMap();
    }
}
