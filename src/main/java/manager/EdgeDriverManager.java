package manager;

import common.Property;
import driver.Capabilities;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static core.Constants.SELENIUMRUNTIMEPATH;

public class EdgeDriverManager extends DriverManager {

	protected Logger log = LogManager.getLogger(this.getClass().getName());


	@Override
	public void createDriver(){
		Capabilities cap = new Capabilities();
		PropertiesConfiguration props = Property.getProperties(SELENIUMRUNTIMEPATH);
		EdgeOptions options = new EdgeOptions();
		if (Property.getVariable("cukes.webdrivermanager") != null && Property.getVariable("cukes.webdrivermanager").equalsIgnoreCase("true")) {
    		if (Property.getVariable("cukes.edgeDriver")!=null) {
				WebDriverManager.edgedriver().driverVersion(Property.getVariable("cukes.edgeDriver")).setup();
			}else {
				WebDriverManager.edgedriver().setup();
			}
    	}else {
		System.setProperty("webdriver.edge.driver", getDriverPath("msedgedriver"));

    	}
		if(props.getString("options.headless.edge").equalsIgnoreCase("true")){
			options.addArguments("headless");
		}
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("inprivate");
		options.addArguments("start-maximized");
		driver = new EdgeDriver(options);
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 