package core;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"core.AutoEngineFormatter",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "rerun:failScenarios/rerun.txt"},
        features = {"classpath:features"}
)
public class AutoEngBaseTest extends BaseTest {

}
