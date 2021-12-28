package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@BO_Coupons", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
