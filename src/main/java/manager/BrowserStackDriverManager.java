package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverContext;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriverManager extends DriverManager {

	protected Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Override
	public void createDriver(){
		Capabilities cap = new Capabilities();
		String browserstackUserName = Property.getVariable("cukes.browserstackUserName");
		String browserstackAccessKey = Property.getVariable("cukes.browserstackAccessKey");
		String browserstackEndPoint = Property.getVariable("cukes.browserstackEndPoint");
		String browserstackServerAddress = "https://"+browserstackUserName + ":" + browserstackAccessKey + browserstackEndPoint;
		System.out.println("Address URL BrowserStack--> " + browserstackServerAddress);
		try {
			if (Property.getVariable("cukes.platformName").equalsIgnoreCase("Android")) {
				cap.getCap().setCapability("automationName","uiautomator2");
				driver = new AndroidDriver(new URL(browserstackServerAddress), cap.getCap());
			}else if (Property.getVariable("cukes.platformName").equalsIgnoreCase("iOS")) {
				cap.getCap().setCapability("automationName","XCUITest");
				driver = new IOSDriver(new URL(browserstackServerAddress), cap.getCap());
			}else if(Property.getVariable("cukes.platformName").equalsIgnoreCase("CHROME")) {
				cap.getCap().setCapability("os","windows");
				cap.getCap().setCapability("os_version","11");
				cap.getCap().setCapability("browser","chrome");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				cap.getCap().setCapability(ChromeOptions.CAPABILITY, options);
				cap.getCap().setCapability("browser_version","113");
				driver = new RemoteWebDriver(new URL(browserstackServerAddress), cap.getCap());
			}else if(Property.getVariable("cukes.platformName").equalsIgnoreCase("SAFARI")) {
				cap.getCap().setCapability("os","OS X");
				cap.getCap().setCapability("os_version","Ventura");
				cap.getCap().setCapability("browser","Safari");
				cap.getCap().setCapability("browser_version","16.3");
				driver = new RemoteWebDriver(new URL(browserstackServerAddress), cap.getCap());
			}
		} catch (MalformedURLException e) {
			log.debug("Could not connect to BrowserStack: url invalid");
		}
	}

	@Override
	public void updateResults(String result){
		//to be completed
	}
} 