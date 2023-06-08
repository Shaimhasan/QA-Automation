package driver;

import common.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.Constants.SELENIUMRUNTIMEPATH;

public abstract class DriverManager {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public WebDriver getDriver(){
		if (driver == null){
			createDriver();
		}
		return driver;
	}
	
	public void quitDriver(){
		if (driver != null){
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriverWait getWait() {
		if (wait == null){
			wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitDuration()));
		}
		return wait;
	}
	
	public String getDriverPath(String drivername){
    	String driver = drivername+(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("Windows")?".exe":"");
    	String path = Property.getVariable("cukes.driverPath");
    	return (path==null?"lib/drivers/"+System.getProperty("os.name").split(" ")[0].toLowerCase()+"/"+driver:path);
    }
	
	/** Returns duration for specified waits */
	public int getWaitDuration(){		
		final int defaultWait = 10;	
		int duration;
		try {
			duration = Property.getProperties(SELENIUMRUNTIMEPATH).getInt("defaultWait");
		} catch (Exception e) {
			duration = defaultWait;
		}
		return duration; 
	}

	protected abstract void createDriver();

	public abstract void updateResults(String result);
} 