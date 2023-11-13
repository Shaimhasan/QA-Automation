package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@Basic_Cancel_Deactivate_Payment_Types", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
