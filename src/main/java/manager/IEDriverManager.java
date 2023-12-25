package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEDriverManager extends DriverManager {

	protected Logger log = LogManager.getLogger(this.getClass().getName());

	@Override
	public void createDriver(){
		Capabilities cap = new Capabilities();
		if (Property.getVariable("cukes.webdrivermanager") != null && Property.getVariable("cukes.webdrivermanager").equalsIgnoreCase("true")) {
			if (Property.getVariable("cukes.ieDriver")!=null) {
				WebDriverManager.iedriver().driverVersion(Property.getVariable("cukes.ieDriver")).setup();
			}else {
				WebDriverManager.iedriver().setup();
			}
    	}else {
    		System.setProperty("webdriver.ie.driver", getDriverPath("IEDriverServer"));
    	}
    	
		System.setProperty("webdriver.ie.driver.silent", "true");
		System.setProperty("ie.ensureCleanSession", "true");

		driver = new InternetExplorerDriver(new InternetExplorerOptions().merge(cap.getCap()));
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 