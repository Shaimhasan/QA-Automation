package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class AppiumDriverManager extends DriverManager {

	private static final String PLATFORM_NAME = "platformName";
	private static final String AUTOMATION_NAME = "automationName";
	private static final String CUKES_APPIUM_END_POINT = "cukes.appiumEndPoint";
	protected Logger log = LogManager.getLogger(this.getClass().getName());

	@Override
	public void createDriver(){

		Capabilities cap = new Capabilities();

		try {
			if (cap.getCap().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase("Android")) {
				cap.getCap().setCapability(AUTOMATION_NAME, "uiautomator2");
				driver = new AndroidDriver(new URL(Property.getVariable(CUKES_APPIUM_END_POINT)), cap.getCap());
			}

			if (cap.getCap().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase("iOS")) {
				cap.getCap().setCapability(AUTOMATION_NAME, "XCUITest");
				driver = new IOSDriver(new URL(Property.getVariable(CUKES_APPIUM_END_POINT)), cap.getCap());
			}

			if (cap.getCap().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase("Windows")) {
				cap.getCap().setCapability(AUTOMATION_NAME, "Windows");
				driver = new WindowsDriver(new URL(Property.getVariable(CUKES_APPIUM_END_POINT)), cap.getCap());
			}
		} catch (Exception e) {
			log.debug("Could not connect to Appium Server: {}", e.getMessage());
		}
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 