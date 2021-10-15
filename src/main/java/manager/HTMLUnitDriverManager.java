package manager;

import driver.Capabilities;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HTMLUnitDriverManager extends DriverManager {

	protected Logger log = LogManager.getLogger(this.getClass().getName());


	@Override
	public void createDriver(){
		Capabilities cap = new Capabilities();
		driver = new HtmlUnitDriver(cap.getCap());
	}

	@Override
	public void updateResults(String result){
		//do nothing
	}
} 