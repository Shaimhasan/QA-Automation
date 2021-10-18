package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@validate_dine_in_cash_basic_order_entry_without_lines", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
