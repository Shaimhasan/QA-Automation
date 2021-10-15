package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverContext;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static core.Constants.SELENIUMRUNTIMEPATH;

public class FirefoxDriverManager extends DriverManager {
	protected Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Override
	public void createDriver() {
		Capabilities cap = new Capabilities();
		PropertiesConfiguration props = Property.getProperties(SELENIUMRUNTIMEPATH);
		if (Property.getVariable("cukes.webdrivermanager") != null && Property.getVariable("cukes.webdrivermanager").equalsIgnoreCase("true")) {
			if (Property.getVariable("cukes.firefoxDriver") != null) {
				WebDriverManager.firefoxdriver().version(Property.getVariable("cukes.firefoxDriver")).setup();
			} else {
				WebDriverManager.firefoxdriver().setup();
			}
		} else {
			System.setProperty("webdriver.gecko.driver", getDriverPath("geckodriver"));
		}

		FirefoxOptions options = new FirefoxOptions();
		for (String variable : props.getStringArray("options." + DriverContext.getInstance().getBrowserName().replaceAll("\\s", ""))) {
			options.addArguments(variable);
		}

		options.merge(cap.getCap());
		driver = new FirefoxDriver(options);
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 