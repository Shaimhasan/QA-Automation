package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@RegressionSuite", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
