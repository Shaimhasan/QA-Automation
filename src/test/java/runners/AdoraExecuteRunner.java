package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@validate_take_out_cash_basic_order_entry_with_lines", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
