package runners;

import core.AutoEngBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = {"@validate_dine_in_half_cash_half_card_basic_order_entry", "not @ignore"})
public class AdoraExecuteRunner extends AutoEngBaseTest {
}
