package hooks;

//import automation.library.alm.core.ALMContext;

import common.Constants;
import common.FileHelper;
import common.TestContext;
import core.DataSpecHelper;
import core.DataTableFormatter;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reporting.Reporter;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class AutoEngHooks implements En {

    protected static final Logger LOG = LogManager.getLogger(AutoEngHooks.class);

    public AutoEngHooks() {
        Before(15, (Scenario scenario) -> {
            //add test data to current thread
            List<Map<String, Object>> dataPermutations = DataSpecHelper.getInstance().processDataSpec(scenario.getName());
            if (!dataPermutations.isEmpty()) {
                Map<String, Object> dataRow = dataPermutations.get(0);
                dataRow.forEach((dataItem, val) -> TestContext.getInstance().testdataPut(dataItem, val));
            }
            loadDataFromPriorRun(scenario);
        });

        After(15, (Scenario scenario) -> {
            LOG.info(DataTableFormatter.getDataDictionaryAsFormattedTable());
            Reporter.startFinalStep(scenario.isFailed());
            addPriorDataTables();
            final String consoleLogPath = getPathToLogFile();
            //Reporter.addTextLogContent("Test Run Console Log", FileHelper.getFileAsString(consoleLogPath, "\n"));
            Reporter.stopFinalStep();
            writeHistoryObjectToFile();
            TestContext.getInstance().testdata().clear();
        });
    }

    private void addPriorDataTables() {
        if (TestContext.getInstance().testdataGet("fw.priorData") != null) {
            List<Map<String, Object>> priorRunData = TestContext.getInstance().testdataToClass("fw.priorData", List.class);
            final int[] idx = {1};
            priorRunData.forEach(entry -> Reporter.addDataTable("Prior Run: Test Data Table - Step " + idx[0]++, entry));
        }
    }

    private String getPathToLogFile() {
        if (TestContext.getInstance().testdataGet("fw.logFileName") != null) {
            return FileHelper.findFileInPath(Constants.LOGPATH,
                    TestContext.getInstance().testdataGet("fw.logFileName").toString() + ".log");
        } else {
            return FileHelper.findFileInPath(Constants.LOGPATH, "default.log");
        }
    }


    private String getJSONFileName(String reportPath, String dataTableFileName) {
        return String.format("%s%s.json", reportPath, FileHelper.getFileNameWithoutExtension(dataTableFileName));
    }

    private void loadDataFromPriorRun(Scenario scenario) {
        final Collection<String> scenarioTags = scenario.getSourceTagNames();
        final List<String> almTag = scenarioTags.stream().sorted().filter(tag -> tag.contains("Flow")).collect(toList());

        if (!almTag.isEmpty()) {
            String[] flowName = almTag.get(1).split("_");
            String[] flowNum = almTag.get(0).split("_");
            int suffix = 0;

            if (flowName.length > 1 && flowNum.length > 1 && Integer.valueOf(flowNum[1]) > 1) {
                suffix = Integer.valueOf(flowNum[1]) - 1;
                Map<String, Object> prebatchDataPermutations = DataSpecHelper.getInstance()
                        .processPreBatchDataSpec(flowName[1] + "_" + suffix);
                if (!prebatchDataPermutations.isEmpty()) {
                    prebatchDataPermutations.forEach((dataItem, val) -> TestContext.getInstance().testdataPut(dataItem, val));
                }
            }
        }
    }

    private void writeHistoryObjectToFile() {
        Map<String, String> updateHistory = TestContext.getInstance().testdataToClass("fw.updateHistory", Map.class);

        if (updateHistory != null) {
            File updateDirectory = new File("ac");
            if (!updateDirectory.exists()) {
                updateDirectory.mkdir();
            }
        }
    }
}
