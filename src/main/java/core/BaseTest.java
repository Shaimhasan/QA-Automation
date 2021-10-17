package core;

import com.google.common.collect.Lists;
import common.TestContext;
import driver.DriverContext;
import driver.DriverFactory;
import io.cucumber.java8.En;
import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
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
import objectmatcher.FetchPageObjects;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.SummaryPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import static automation.library.alm.core.Constants.ALM_DEFECT_PROPERTIES_PATH_REPORT;

@CucumberOptions(
        strict = true,
        glue = {"utility", "hooks"}
)

public class BaseTest extends common.BaseTest implements En {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws IOException {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Run Cucumber Scenarios", dataProvider = "techStackWithScenarioList")
    public void scenario(Map<String, String> map, PickleEventWrapper pickle, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickle.getPickleEvent());
    }

    @DataProvider(name = "techStackWithScenarioList")
    public Object[][] combineDataProvider(Method method) {
        List<Object[]> techStackList = Lists.newArrayList();
        List<Object[]> scenarios = Lists.newArrayList();
        List<List<Object>> comboList = Lists.newArrayList();

        try {
            techStackList.addAll(Arrays.asList(techStackJSON()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        scenarios.addAll(Arrays.asList(testNGCucumberRunner.provideScenarios()));

        techStackList.forEach(techStack ->
                scenarios.forEach(scenario ->
                        comboList.add(Arrays.asList(techStack[0],
                                scenario[0],
                                scenario[1]))));

        Object[][] comboArray = new Object[comboList.size()][3];

        for (int i = 0; i < comboList.size(); i++) {
            comboArray[i][0] = comboList.get(i).get(0);
            comboArray[i][1] = comboList.get(i).get(1);
            comboArray[i][2] = comboList.get(i).get(2);
        }

        return comboArray;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        onGenerateAllureReport();
        testNGCucumberRunner.finish();
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
