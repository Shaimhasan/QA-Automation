package manager;

import driver.Capabilities;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManager {

	protected Logger log = LogManager.getLogger(this.getClass().getName());

	@Override
	public void createDriver(){
		Capabilities cap = new Capabilities();
		SafariOptions options = new SafariOptions();
		driver = new SafariDriver(options.merge(cap.getCap()));
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 