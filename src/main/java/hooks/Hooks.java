package hooks;

import common.Property;
import common.TestContext;
import core.Constants;
import driver.DriverContext;
import driver.DriverFactory;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static core.Screenshot.grabScreenshot;
import static core.Screenshot.saveScreenshot;


public class Hooks implements En {

    private static final String SCREENSHOT_ON_FAILURE = "screenshotOnFailure";

    public Hooks() {
        Before(30, (Scenario scenario) -> setRuntimeProperties());

        After(30, (Scenario scenario) -> {
            if (DriverContext.getInstance().getTechStack() != null) {
                if (scenario.isFailed()) {
                    System.out.println("Scenarios Status : -->" + "Scenarios Failed");
                    takeScreenShotOnFailure();
                    if (!DriverContext.getInstance().getKeepBrowserOpen())
                        DriverFactory.getInstance().quit();
                    // theUserClosesTheBrowsers("IE");

                } else {
                    System.out.println("Scenarios Status : -->" + "Scenarios Passed");
                    DriverFactory.getInstance().quit();
                    //   theUserClosesTheBrowsers("IE");
                }

                if (!DriverContext.getInstance().getKeepBrowserOpen())
                    DriverFactory.getInstance().quit();

                DriverFactory.getInstance().driverManager().updateResults(scenario.isFailed() ? "failed" : "passed");
            }
        });
    }


    private void setRuntimeProperties() {
        PropertiesConfiguration props = Property.getProperties(Constants.SELENIUMRUNTIMEPATH);

        if (props != null) {
            String tableCompareAttribute = props.getString("attributeForTableCellCompare");
            String tableHeaderCompareAttribute = props.getString("attributeForTableHeaderCompare");
            List<String> xPathForLabels = Arrays.asList(props.getStringArray("xPathForLabels"));
            String screenshotOnValidations = props.getString("screenshotOnValidation");
            String screenshotOnFailure = props.getString(SCREENSHOT_ON_FAILURE);
            String screenshotOnEveryStep = props.getString("screenshotOnEveryStep");
            String browserOpenOnFailure = props.getString("browserOpenOnFailure");
            String seleniumGridURL = props.getString("seleniumGridURL");
            String trimTextFlag = props.getString("trimTextOnRetrieval");

            String windowSwitchDelay = props.getString("windowSwitchDelay") == null ? "1000" : props.getString("windowSwitchDelay");
            System.setProperty("fw.windowSwitchDelay", windowSwitchDelay);
            String keyToPressDelay = props.getString("keyToPressDelay") == null ? "1000" : props.getString("keyToPressDelay");
            System.setProperty("fw.keyToPressDelay", keyToPressDelay);

            System.setProperty("fw.ignoreHostNameMismatch", String.valueOf(Boolean.parseBoolean(props.getString("ignoreHostNameMismatch"))));

            if (tableCompareAttribute != null) {
                System.setProperty(Constants.ATTRIBUTE_FOR_TABLE_CELL_COMPARE, tableCompareAttribute);
            }

            if (tableHeaderCompareAttribute != null) {
                System.setProperty(Constants.ATTRIBUTE_FOR_TABLE_HEADER_COMPARE, tableHeaderCompareAttribute);
            }

            if (!xPathForLabels.isEmpty()) {
                System.setProperty(Constants.XPATH_FOR_LABELS, String.join(Constants.XPATH_FOR_LABELS_DELIMITER, xPathForLabels));
            }

            if (screenshotOnValidations != null) {
                System.setProperty("fw.screenshotOnValidation", screenshotOnValidations);
            }

            if (screenshotOnFailure != null) {
                System.setProperty("fw.screenshotOnFailure", screenshotOnFailure);
            }

            if (screenshotOnEveryStep != null) {
                System.setProperty("fw.screenshotOnEveryStep", screenshotOnEveryStep);
            }

            if (browserOpenOnFailure != null) {
                DriverContext.getInstance().setKeepBrowserOpen(Boolean.valueOf(browserOpenOnFailure));
            }

            if (seleniumGridURL != null) {
                System.setProperty("fw.seleniumGrid", seleniumGridURL);
            }

            if (trimTextFlag != null) {
                System.setProperty("fw.trimTextOnRetrieval", trimTextFlag);
            }
        }
    }

    private void takeScreenShotOnFailure() {
        PropertiesConfiguration props = Property.getProperties(Constants.SELENIUMRUNTIMEPATH);
        String screenshotOnFailure = props.getString(SCREENSHOT_ON_FAILURE);

        if (Boolean.parseBoolean(screenshotOnFailure) && TestContext.getInstance().getActiveWindowType().equalsIgnoreCase("Selenium")) {
            System.setProperty(SCREENSHOT_ON_FAILURE, screenshotOnFailure);
            File file = saveScreenshot(grabScreenshot(DriverFactory.getInstance().getDriver()), getScreenshotPath());
            String relativePath = "." + File.separator + "Screenshots" + File.separator + file.getName();
            String absolutePath = file.getAbsolutePath();
            TestContext.getInstance().testdata().put("fw.screenshotRelativePath", relativePath);
            TestContext.getInstance().testdata().put("fw.screenshotAbsolutePath", absolutePath);
        }
    }

    private static String getReportPath() {
        String defaultReportPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "allure-results" + File.separator;
        String reportPath = Property.getVariable("reportPath");
        return (reportPath == null ? defaultReportPath : reportPath);
    }

    private static String getScreenshotPath() {
        return getReportPath() + "screenshots" + File.separator;
    }

    private static void theUserClosesTheBrowsers(String browserName) throws Throwable {
        if (browserName.equalsIgnoreCase("IE")) {
            Runtime rt = Runtime.getRuntime();
            rt.exec("taskkill /F /IM IEDriverServer.exe");
            rt.exec("taskkill /F /IM iexplore.exe");
        }
    }
}