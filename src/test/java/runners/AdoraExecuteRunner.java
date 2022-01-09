package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@Basic_Order_Entry_Dine_In_Cash_With_All_Lines_Enabled", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
