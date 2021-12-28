package core;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"core.AutoEngineFormatter",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber-reports/runReport.json",
                "rerun:target/rerun.txt"},
        features = {"classpath:features"}
)
public class AutoEngBaseTest extends BaseTest {

}
