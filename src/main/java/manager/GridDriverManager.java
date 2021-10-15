package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridDriverManager extends DriverManager {

	private static final String PLATFORM_NAME = "platformName";
	private static final String CUKES_SELENIUM_GRID = "fw.seleniumGrid";
	protected Logger log = LogManager.getLogger(this.getClass().getName());


	@Override
	public void createDriver() {
		Capabilities cap = new Capabilities();
		try {
			if(cap.getCap().getCapability(PLATFORM_NAME) != null) {
				switch(cap.getCap().getCapability(PLATFORM_NAME).toString().toLowerCase()) {
					case "android":
						driver = new AndroidDriver(new URL(Property.getVariable(CUKES_SELENIUM_GRID)), cap.getCap());
						break;
					case "ios":
						driver = new IOSDriver(new URL(Property.getVariable(CUKES_SELENIUM_GRID)), cap.getCap());
						break;
					case "windows-ui":
						driver = new WindowsDriver<WindowsElement>(new URL(Property.getVariable(CUKES_SELENIUM_GRID)), cap.getCap());
						break;
					default:
						driver = new RemoteWebDriver(new URL(Property.getVariable(CUKES_SELENIUM_GRID)), cap.getCap());
						break;
				}
			}
			else {
				driver = new RemoteWebDriver(new URL(Property.getVariable(CUKES_SELENIUM_GRID)), cap.getCap());
			}
		} catch (MalformedURLException e) {
			log.error("Could not connect to Selenium Grid: url invalid", e);
		}
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 