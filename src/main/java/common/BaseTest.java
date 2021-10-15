package common;

import core.BasePO;
import core.BaseWebSteps;
import core.ExecConstants;
import driver.DriverContext;
import driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

import static core.ExecConstants.TESTCASEPATH;

public class BaseTest extends BaseWebSteps {

    protected final Logger log = LogManager.getLogger(this.getClass().getName());
    protected BasePO po;
    protected TestContext context = TestContext.getInstance();
    private static final String PROJECT_NAME = "PROJECT_NAME";
    private static final String BUILD_NUMBER = "BUILD_NUMBER";

    public WebDriver getDriver() {
        log.debug("obtaining the driver for current thread");
        return DriverFactory.getInstance().getDriver();
    }

    public WebDriverWait getWait() {
        log.debug("obtaining the wait for current thread");
        return DriverFactory.getInstance().getWait();
    }

    public BasePO getPO() {
        log.debug("obtaining an instance of the base page object");
        if (this.po == null) {
            this.po = new BasePO();
        }
        return po;
    }

    public void setPO() {
        log.debug("obtaining an instance of the base page object");
        this.po = new BasePO();
    }


    public SoftAssertions sa() {
        return TestContext.getInstance().sa();
    }

    @DataProvider(name = "techStackJSON", parallel = true)
    public Object[][] techStackJSON() {
        List<Map<String, String>> listOfTechStacks = FileHelper.getJSONAsListOfMaps(ExecConstants.SELENIUMSTACKSPATH);

        if (!listOfTechStacks.isEmpty()) {
            Object[][] obj = new Object[listOfTechStacks.size()][1];

            for (int i = 0; i < listOfTechStacks.size(); i++) {
                obj[i][0] = listOfTechStacks.get(i);
            }

            return obj;
        } else {
            if (Property.getVariable("cukes.techstack") != null) {
                log.warn("Tech stack JSON file not found: {}. Defaulting to a local ChromeDriver instance.", ExecConstants.SELENIUMSTACKSPATH);
            }
            Map<String, String> techStack = new HashMap<>();
            techStack.put("seleniumServer", "local");
            techStack.put("browserName", "chrome");

            return new Object[][]{Collections.singletonList(techStack).toArray()};

        }

    }

    @DataProvider(name = "techStackExcel", parallel = true)
    public Object[][] techStackExcel() {
        log.debug("spinning up parallel execution threads for multi browser testing");

        List<ArrayList<Object>> browsers = FileHelper.getDataAsArrayList(TESTCASEPATH, "browsers", new String[0]);
        Object[][] obj = new Object[browsers.size() - 1][1];

        for (int i = 1; i < browsers.size(); ++i) {
            Map<String, String> map = new HashMap();
            for (int j = 0; j < browsers.get(0).size(); j++) {
                map.put(browsers.get(0).get(j).toString(), browsers.get(i).get(j).toString());
            }
            obj[i - 1][0] = map;
        }

        return obj;
    }


    @BeforeMethod
    public void startUp(Method method, Object[] args) {
        Test test = method.getAnnotation(Test.class);
        Map<String, String> map = (Map<String, String>) args[0];
        if (!TestContext.getInstance().testdata().containsKey("fw.cucumberTest"))
            TestContext.getInstance().testdataPut("fw.testDescription", test.description() + " (" + map.get("description") + ")");
        if (Property.getVariable(PROJECT_NAME) != null && !Property.getVariable(PROJECT_NAME).isEmpty())
            TestContext.getInstance().testdataPut("fw.projectName", Property.getVariable(PROJECT_NAME));
        if (Property.getVariable(BUILD_NUMBER) != null && !Property.getVariable(BUILD_NUMBER).isEmpty())
            TestContext.getInstance().testdataPut("fw.buildNumber", Property.getVariable(BUILD_NUMBER));
        DriverContext.getInstance().setDriverContext(map);
    }

    @AfterMethod(groups = {"quitDriver"})
    public void closeDown(ITestResult result) {
        if (!TestContext.getInstance().testdata().containsKey("fw.cucumberTest")) {
            DriverFactory.getInstance().driverManager().updateResults(result.isSuccess() ? "passed" : "failed");
        }

        if (DriverContext.getInstance().getTechStack() != null &&
            !DriverContext.getInstance().getKeepBrowserOpen()) {
          //  DriverFactory.getInstance().quit();
        }

        TestContext.getInstance().resetSoftAssert();
    }
}
