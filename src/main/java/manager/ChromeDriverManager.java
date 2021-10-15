package manager;

import common.Property;
import core.Constants;
import driver.DriverContext;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static core.Constants.SELENIUMRUNTIMEPATH;

public class ChromeDriverManager extends DriverManager {

    protected Logger log = LogManager.getLogger(this.getClass().getName());


    @Override
    public void createDriver() {
        PropertiesConfiguration props = Property.getProperties(SELENIUMRUNTIMEPATH);
        if (Property.getVariable("cukes.webdrivermanager") != null && Property.getVariable("cukes.webdrivermanager").equalsIgnoreCase("true")) {
            if (Property.getVariable("cukes.chromeDriver") != null) {
                WebDriverManager.chromedriver().version(Property.getVariable("cukes.chromeDriver")).setup();
            } else {
                WebDriverManager.chromedriver().setup();
            }
        } else {
            System.setProperty("webdriver.chrome.driver", getDriverPath("chromedriver"));
        }
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();

        for (String variable : props.getStringArray("options." + DriverContext.getInstance().getBrowserName().replaceAll("\\s", ""))) {
            options.addArguments(variable);
        }

        if(props.getString("options.chrome.useAutomationExtension") != null &&
           props.getString("options.chrome.useAutomationExtension").equalsIgnoreCase("false")) {
            options.setExperimentalOption("useAutomationExtension", false);
        }


        if(Boolean.parseBoolean(props.getString("prefs.chrome.overrideDownloadSettings"))) {
            String downloadDir = Paths.get(Constants.DOWNLOADPATH).toAbsolutePath().toString();
            Map<String, Object> userPrefs = new HashMap<>();
            userPrefs.put("download.default_directory", downloadDir);
            userPrefs.put("download.prompt_for_download", false);
            userPrefs.put("plugins.always_open_pdf_externally", true);
            options.setExperimentalOption("prefs", userPrefs);
        }

        if (DriverContext.getInstance().getBrowserName().contains("kiosk")) {
            options.addArguments("--kiosk");
        }
        if(props.getString("options.headless.chrome").equalsIgnoreCase("true")){
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
    }

    @Override
    public void updateResults(String result) {
        //do nothing
    }
} 