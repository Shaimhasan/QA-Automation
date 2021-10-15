package hooks;

import common.Property;
import common.TestContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java8.En;

import static reporting.Reporter.addScreenCaptureFromPath;

public class AfterHooks implements En {

	public AfterHooks() {

		After(10, (Scenario scenario) -> {
			if (scenario.isFailed()) {
				String screenshotOnFailure = Property.getVariable("screenshotOnFailure");
				if (Boolean.parseBoolean(screenshotOnFailure)) {
					String screenshotPath = (String) TestContext.getInstance().testdataGet("fw.screenshotAbsolutePath");
					if (screenshotPath != null) {
						addScreenCaptureFromPath(screenshotPath);
					}
				}
			}
		});
	}
}