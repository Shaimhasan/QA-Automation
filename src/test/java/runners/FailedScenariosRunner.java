package runners;

import core.BaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"core.AutoEngineFormatter",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber-reports/runReport.json"},
        features = {"@failScenarios/rerun.txt"}
)
public class FailedScenariosRunner extends BaseTest {
}
