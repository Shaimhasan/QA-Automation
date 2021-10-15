package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

    private static final int MAX_DEPTH = 10;
    private static final String EXCEPTION_PROCESSING_FILE = "exception processing file";

    private FileHelper() { //Utility class
    }

    private static Logger getLogger() {
        return LogManager.getLogger(FileHelper.class);
    }

    /**
     * Reads JSON file and returns a specific json object from that file based on the root element value
     */
    public static JSONObject getJSONData(String filepath, String... key) {
        try {
            FileReader reader = new FileReader(filepath);
            JSONTokener token = new JSONTokener(reader);
            return (JSONObject) (key.length > 0 ? new JSONObject(token).get(key[0]) : new JSONObject(token));
        } catch (FileNotFoundException e) {
            getLogger().error(e);
            return null;
        }

    }

    /**
     * Reads JSON file and returns a specific json object from that file based on the root element value
     */
    public static JSONArray getJSONArray(String filepath, String... key) {
        try {
            FileReader reader = new FileReader(filepath);
            JSONTokener token = new JSONTokener(reader);
            return (JSONArray) (key.length > 0 ? new JSONObject(token).get(key[0]) : new JSONArray(token));
        } catch (FileNotFoundException e) {
            getLogger().error("file not found", e);
            return null;
        }
    }

    /**
     * reads data from json files and casts to supplied pojo format
     */
    public static <T> T getDataPOJO(String filepath, Class<T> clazz) throws IOException {
        Gson gson = new Gson();
        File file = new File(filepath);
        T dataObj = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            dataObj = gson.fromJson(br, clazz);
        } catch (FileNotFoundException e) {
            getLogger().error(e);
        }

        return dataObj;

    }

    public static Map<String, String> getJSONToMap(JSONObject json) {

        Map<String, String> map = new HashMap<>();
        String[] keys = JSONObject.getNames(json);
        for (String key : keys) {
            map.put(key, json.get(key).toString());
        }

        return map;

    }

    /**
     * Performs a recursive merge between 2 json objects.
     * When the json includes an array then will loop through this as
     * part of the recursive merge.
     */
    public static JSONObject jsonMerge(JSONObject source, JSONObject target) {
        String[] keys = JSONObject.getNames(source);
        if (keys != null) {
            for (String key : keys) {
                Object value = source.get(key);
                if (!target.has(key)) {
                    target.put(key, value);
                } else if (value instanceof JSONArray) {
                    JSONArray array = (JSONArray) value;
                    JSONArray targetarray = (JSONArray) target.get(key);
                    for (int i = 0; i < array.length(); i++) {
                        Object arrayvalue = array.get(i);
                        Object targetarrayvalue = targetarray.get(i);
                        if (arrayvalue instanceof JSONObject) {
                            JSONObject valueJson = (JSONObject) arrayvalue;
                            JSONObject targetvalueJson = (JSONObject) targetarrayvalue;
                            jsonMerge(valueJson, targetvalueJson);
                        } else {
                            target.put(key, value);
                        }
                    }
                } else if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject) value;
                    jsonMerge(valueJson, target.getJSONObject(key));
                } else {
                    target.put(key, value);
                }
            }
        }

        return target;
    }

    public static List<Map<String, String>> getJSONAsListOfMaps(String path) {
        Gson gson = new GsonBuilder().create();

        try {
            return gson.fromJson(new FileReader(path), List.class);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }

    }

    /**
     * reads data from a given sheet in excel returning all rows and columns matching the supplied recordset (col A)
     */
    public static List<ArrayList<Object>> getDataAsArrayList(String filepath, String worksheet, String... recordSet) {

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet(worksheet);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int maxDataCount = 0;
            // Iterate through each rows one by one


            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                //Skip the first row beacause it will be header and also get column count
                if (row.getRowNum() == 0) {
                    maxDataCount = row.getLastCellNum();
                }

                //if row is empty then break the loop,do not go further
                if (isRowEmpty(row)) {
                    break;
                }

                ArrayList<Object> singleRows = new ArrayList<>();

                // For each row, iterate through all the columns

                for (int c = 0; c < maxDataCount; c++) {

                    Cell cell = row.getCell(c);

                    singleRows.add(getCellData(cell, evaluator));

                }

                if (recordSet.length > 0) {
                    if (singleRows.get(0).toString().equalsIgnoreCase(recordSet[0])) {
                        data.add(singleRows);
                    }
                } else {
                    data.add(singleRows);
                }
            }
        } catch (Exception e) {
            getLogger().error(EXCEPTION_PROCESSING_FILE, e);
        }

        return data;

    }

    public static Map<String, LinkedHashMap<String, Object>> getDataAsMap(String filepath, String worksheet) {

        Map<String, LinkedHashMap<String, Object>> dataMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> mapTemp = null;

        String dataSetKey = null;
        Row headerRow = null;
        Object key = null;
        Object value = null;

        try (FileInputStream file = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheet(worksheet);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int lastRow = sheet.getLastRowNum();


            int currentRowNum = 0;
            while (currentRowNum < lastRow) {
                if (isRowEmpty(sheet.getRow(currentRowNum))) {
                    currentRowNum++;
                    continue;
                }

                headerRow = sheet.getRow(currentRowNum);

                for (int j = currentRowNum + 1; j <= lastRow; j++) {
                    if (isRowEmpty(sheet.getRow(j))) {
                        currentRowNum = j + 1;
                        break;
                    }

                    dataSetKey = getCellData(sheet.getRow(headerRow.getRowNum()).getCell(0), evaluator) + "." + getCellData(sheet.getRow(sheet.getRow(j).getRowNum()).getCell(0), evaluator);
                    mapTemp = new LinkedHashMap<>();
                    for (int cellCounter = 1; cellCounter < sheet.getRow(j).getLastCellNum(); cellCounter++) {

                        key = getCellData(sheet.getRow(headerRow.getRowNum()).getCell(cellCounter), evaluator);
                        value = getCellData(sheet.getRow(j).getCell(cellCounter), evaluator);

                        if (value != null && key != null) {
                            mapTemp.put(key.toString(), value);

                        }
                    }

                    currentRowNum++;
                    dataMap.put(dataSetKey, mapTemp);
                }
            }
        } catch (Exception e) {
            getLogger().error(EXCEPTION_PROCESSING_FILE, e);
        }
        return dataMap;
    }

    public static List<Map<String, Object>> getExcelDataAsMapWithoutIndex(String filepath, String worksheet) {

        List<Map<String, Object>> dataMap = new ArrayList<>();
        Map<String, Object> mapTemp = null;

        Row headerRow = null;
        Object key = null;
        Object value = null;

        try (FileInputStream file = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheet(worksheet);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int lastRow = sheet.getLastRowNum();

            int currentRowNum = 0;
            while (currentRowNum < lastRow) {
                if (isRowEmpty(sheet.getRow(currentRowNum))) {
                    currentRowNum++;
                    continue;
                }

                headerRow = sheet.getRow(currentRowNum);

                for (int j = currentRowNum + 1; j <= lastRow; j++) {
                    if (isRowEmpty(sheet.getRow(j))) {
                        currentRowNum = j + 1;
                        break;
                    }

                    mapTemp = new HashMap<>();
                    for (int cellCounter = 0; cellCounter < sheet.getRow(j).getLastCellNum(); cellCounter++) {

                        key = getCellData(sheet.getRow(headerRow.getRowNum()).getCell(cellCounter), evaluator);
                        value = getCellData(sheet.getRow(j).getCell(cellCounter), evaluator);

                        if (value != null && key != null) {
//                            //only for FDR
//                            int RowNum = currentRowNum + 2;
//                            int ori = value.toString().indexOf("OR");
//                            if (ori != -1) {
//                                StringBuffer str = new StringBuffer(value.toString());
//                                str.insert(ori - 1, "\nROW_NUM = " + RowNum + " ");
//                                value = str.toString();
//                            }
//                            value = value.toString() + "\nROW_NUM = " + RowNum + "";
//                            //only for FDR

                            mapTemp.put(key.toString(), value);

                        }
                    }

                    currentRowNum++;
                    dataMap.add(mapTemp);
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return dataMap;
    }

    private static Object getCellData(Cell cell, FormulaEvaluator evaluator) {
        Object obj = null;

        if (cell == null) return null;

        switch (evaluator.evaluateInCell(cell).getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    obj = (new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));
                } else {
                    obj = (cell.getNumericCellValue());
                }
                break;
            case STRING:
                obj = (cell.getStringCellValue());
                break;
            case BLANK:
                obj = null;
                break;
            default:
                obj = (cell.getStringCellValue());
        }
        return obj;
    }


    private static boolean isRowEmpty(Row row) {

        if (row == null) return true;

        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static String findFileInPath(String rootDir, String fileName) {
        Path filePath = Paths.get(rootDir);
        String fullPath = null;
        String fileNameWithTrailingWhiteSpace = allowWhiteSpaceInFileName(fileName);

        try (Stream<Path> files = Files.find(filePath, MAX_DEPTH,
                                             (path, attributes) -> {
                                                 File file = path.toFile();
                                                 return !file.isDirectory() &&
                                                        file.getName().matches(fileNameWithTrailingWhiteSpace);
                                             })) {

            final List<Path> filesList = files.collect(Collectors.toList());

            if (!filesList.isEmpty()) {
                fullPath = filesList.get(0).toString();

                if (filesList.size() > 1) {
                    getLogger().warn("Found more than one file match. Returning first match");
                }
            }
        } catch (IOException e) {
            return null;
        }

        return fullPath;
    }

    private static String allowWhiteSpaceInFileName(String fileName) {
        return String.join("\\s*\\.",
                           escapeCaptureGroups(FilenameUtils.getBaseName(fileName)),
                           FilenameUtils.getExtension(fileName));
    }

    private static String escapeCaptureGroups(String baseName) {
        return baseName.replace("(", "\\(")
                       .replace(")", "\\)");
    }

    public static String getFileNameWithoutExtension(String path) {
        if (path != null) {
            String fileName = Paths.get(path).getFileName().toString();
            fileName = FilenameUtils.removeExtension(fileName);

            return fileName;
        } else {
            return null;
        }
    }

    public static String getFileNameExtension(String path) {
        if (path != null) {
            String fileName = Paths.get(path).getFileName().toString();
            fileName = FilenameUtils.getExtension(fileName);

            return fileName;
        } else {
            return null;
        }
    }


    public static String getFileAsString(String jsFilePath, String delimeter) throws IOException {
        String stringToReturn = null;

        //remove line breaks
        try (BufferedReader br = Files.newBufferedReader(Paths.get(jsFilePath), StandardCharsets.ISO_8859_1)) {
            stringToReturn = br.lines().collect(Collectors.joining(delimeter));
        } catch (IOException e) {
            getLogger().error(e.getMessage());
        }

        return stringToReturn;
    }

    public static String convertMapToJSON(Map<String, Object> map, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter jsonWrite = new FileWriter(path)) {
            gson.toJson(map, Map.class, jsonWrite);
            File jsonFile = new File(path);
            if (jsonFile.exists()) {
                return jsonFile.getAbsolutePath();
            } else {
                return null;
            }
        } catch (IOException e) {
            getLogger().error("Unable to write to file at path: {}", path);
            return null;
        } catch (JsonSyntaxException e) {
            getLogger().debug("Not a valid JSON: {}. Unable to create file.", map);
            return null;
        }
    }

    public static String findMostRecentFileByExtension(String rootDir, String fileExtension) {
        Path filePath = Paths.get(rootDir);
        String fullPath = null;

        try (Stream<Path> files = Files.find(filePath, MAX_DEPTH,
                                             (path, attributes) -> {
                                                 File file = path.toFile();
                                                 return !file.isDirectory() &&
                                                        FilenameUtils.getExtension(file.getName()).matches(fileExtension);
                                             })) {

            final List<File> filesList = files.map(Path::toFile)
                                              .sorted(LastModifiedFileComparator.LASTMODIFIED_REVERSE)
                                              .collect(Collectors.toList());

            if (!filesList.isEmpty()) {
                fullPath = filesList.get(0).toString();

                if (filesList.size() > 1) {
                    getLogger().warn("Found more than one file match. Returning first match");
                }
            }
        } catch (IOException e) {
            return null;
        }

        return fullPath;
    }

    public static Set<String> getMatchingFilesFromDirectory(String inputDir, String extension) {
        try (Stream<Path> stream = Files.walk(Paths.get(inputDir))) {
            return stream.filter(file -> !Files.isDirectory(file))
                         .filter(f -> (f.toString().endsWith(extension) || f.toString().endsWith(extension.toUpperCase())))
                         .map(Path::getFileName)
                         .map(Path::toString)
                         .collect(Collectors.toSet());
        } catch (IOException e) {
            getLogger().error(e);
            return Collections.emptySet();
        }
    }

    public static Map<String, Object> convertPropertyFileToMap(String path) {
        Properties properties = new Properties();
        Map<String, Object> propMap = new HashMap<>();
        try {
            properties.load(new FileInputStream(path));
            propMap.putAll(properties.entrySet().stream()
                                     .collect(Collectors.toMap(e -> e.getKey().toString(),
                                                               Map.Entry::getValue)));
        } catch (IOException e) {
            getLogger().error(EXCEPTION_PROCESSING_FILE, e);
        }
        return propMap;
    }

    public static String mapToJSONArrayString(Map<String, String> myObject) {
        Gson gson = new Gson();
        return gson.toJson(myObject);
    }


    /**
     * Reads JSON file and returns a specific json string from that file based on the root element value
     */
    public static Map<String, String> getJSONDatasMap(String filepath) {

        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filepath);
            return gson.fromJson(reader, Map.class);
        } catch (FileNotFoundException e) {
            getLogger().error(e);
        }
        return null;
    }
}