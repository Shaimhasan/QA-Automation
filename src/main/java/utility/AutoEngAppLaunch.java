package utility;

import common.TestContext;
import core.BaseWebSteps;
import io.cucumber.java.en.Given;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class AutoEngAppLaunch extends BaseWebSteps {
    @Given("^the web application \"([^\"]*)\" is launched in a \"([^\"]*)\"$")
    public void theWebApplicationIsLaunchedInA(String appName, String location) {
        launchApplication(appName, location);
        TestContext.getInstance().pushWindowHandle(getDriver().getWindowHandle());
    }

    @Given("^the user bypasses the IE security certificate page$")
    public void theUserBypassIESecurityCertificatePage() {
        if (getDriver() instanceof InternetExplorerDriver) {
            getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
        } else {
            log.warn("Not running an IE browser. Skipping step");
        }
    }

}
