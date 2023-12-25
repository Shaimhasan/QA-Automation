package core;

import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;

@CucumberOptions(
        plugin = {"core.AutoEngineFormatter",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/runReport.json",
                "rerun:failScenarios/rerun.txt"},
        features = {"classpath:features"}
)
public class AutoEngBaseTest extends BaseTest {

}
