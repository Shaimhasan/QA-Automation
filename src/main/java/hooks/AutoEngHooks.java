package hooks;

//import automation.library.alm.core.ALMContext;

import common.Constants;
import common.FileHelper;
import common.TestContext;
import core.DataSpecHelper;
import core.DataTableFormatter;
import io.cucumber.core.api.Scenario;
import io.cucumber.java8.En;
import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.Extension;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.allure1.Allure1Plugin;
import io.qameta.allure.allure2.Allure2Plugin;
import io.qameta.allure.category.CategoriesPlugin;
import io.qameta.allure.category.CategoriesTrendPlugin;
import io.qameta.allure.context.FreemarkerContext;
import io.qameta.allure.context.JacksonContext;
import io.qameta.allure.context.MarkdownContext;
import io.qameta.allure.context.RandomUidContext;
import io.qameta.allure.core.*;
import io.qameta.allure.duration.DurationPlugin;
import io.qameta.allure.duration.DurationTrendPlugin;
import io.qameta.allure.environment.Allure1EnvironmentPlugin;
import io.qameta.allure.executor.ExecutorPlugin;
import io.qameta.allure.history.HistoryPlugin;
import io.qameta.allure.history.HistoryTrendPlugin;
import io.qameta.allure.idea.IdeaLinksPlugin;
import io.qameta.allure.influxdb.InfluxDbExportPlugin;
import io.qameta.allure.launch.LaunchPlugin;
import io.qameta.allure.mail.MailPlugin;
import io.qameta.allure.owner.OwnerPlugin;
import io.qameta.allure.prometheus.PrometheusExportPlugin;
import io.qameta.allure.retry.RetryPlugin;
import io.qameta.allure.retry.RetryTrendPlugin;
import io.qameta.allure.severity.SeverityPlugin;
import io.qameta.allure.status.StatusChartPlugin;
import io.qameta.allure.suites.SuitesPlugin;
import io.qameta.allure.tags.TagsPlugin;
import io.qameta.allure.timeline.TimelinePlugin;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reporting.Reporter;
import utility.SummaryPlugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class AutoEngHooks implements En {

    protected static final Logger LOG = LogManager.getLogger(AutoEngHooks.class);

    public AutoEngHooks() {
        Before(15, (Scenario scenario) -> {
            FileUtils.deleteDirectory(new File("target/allure-results"));
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
            onGenerateAllureReport();
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
    private void onGenerateAllureReport() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    final List<Extension> extensions = Arrays.asList(new JacksonContext(), new MarkdownContext(), new FreemarkerContext(), new RandomUidContext(), new MarkdownDescriptionsPlugin(), new RetryPlugin(), new RetryTrendPlugin(), new TagsPlugin(), new SeverityPlugin(), new OwnerPlugin(), new IdeaLinksPlugin(), new CategoriesPlugin(), new CategoriesTrendPlugin(), new HistoryPlugin(), new HistoryTrendPlugin(), new DurationPlugin(), new DurationTrendPlugin(), new StatusChartPlugin(), new TimelinePlugin(), new SuitesPlugin(), new TestsResultsPlugin(), new AttachmentsPlugin(), new MailPlugin(), new InfluxDbExportPlugin(), new PrometheusExportPlugin(), new SummaryPlugin(), new ExecutorPlugin(), new LaunchPlugin(), new Allure1Plugin(), new Allure1EnvironmentPlugin(), new Allure2Plugin(), new ReportWebPlugin());
                    Configuration configuration = (new ConfigurationBuilder()).fromExtensions(extensions).build();
                    Path resultDi = Paths.get("target/allure-results");
                    Path outDir = Paths.get("target/allure-report");
                    new ReportGenerator(configuration).generate(outDir, resultDi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
