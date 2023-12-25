package hooks;

import common.TestContext;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reporting.Reporter;

public class GenericHooks implements En{

	protected final Logger log = LogManager.getLogger(GenericHooks.class);

	public GenericHooks(){
		Before(10, (Scenario scenario) -> {
			String featureName = (scenario.getId().split(";")[0].replace("-", " "));
			String scenarioName = scenario.getName();
			TestContext.getInstance().testdataPut("fw.testDescription", featureName + "-" + scenarioName);
			TestContext.getInstance().testdataPut("fw.cucumberTest","true");
		});

		After(20, (Scenario scenario) -> checkForSAFailure());
	}

	private void checkForSAFailure() {
		try {
			TestContext.getInstance().sa().assertAll();
		} catch (AssertionError e) {
			Reporter.failScenario(e.fillInStackTrace());
			log.error(e.getMessage());
			TestContext.getInstance().resetSoftAssert();
			throw e;
		}
	}
}
