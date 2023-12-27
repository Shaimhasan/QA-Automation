package runners;

import core.BaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"core.AutoEngineFormatter",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/runReport.json",
                "rerun:failScenarios/rerun.txt"},
        features = {"@failScenarios/rerun.txt"}
)
public class FailedScenariosRunner extends BaseTest {
}
