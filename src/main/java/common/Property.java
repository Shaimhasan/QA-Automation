package common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;

/** 
 * utility class based on Apache Commons extension to java properties file.
 * additional capabilities provided by the Apache util include nested property files
 * and multi occurrences of keys (ie. array lists)    
 */
public class Property {

	private Property() {
		//utility class
	}

	private static Logger getLogger() {
		return LogManager.getLogger(Property.class);
	}

	/**
	 * gets properties file 
	 */
	public static PropertiesConfiguration getProperties(String propsPath) {
		PropertiesConfiguration props = new PropertiesConfiguration();
		try {
			File propsFile = new File(propsPath);
			if(propsFile.exists()) {
				FileInputStream inputStream = new FileInputStream(propsFile);
				props.load(inputStream);
				inputStream.close();
				return props;
			}
		} catch (Exception e) {
			getLogger().debug(e.getMessage());
			return null;
		}

		return null;
	}

	/**
	 * gets string data from any properties file on given path
	 */
	public static String getProperty(String propsPath, String key) {
		PropertiesConfiguration props = getProperties(propsPath);
		String keyVal = null;

		if (props != null) {
			keyVal = props.getString(key);
		}

		return keyVal;
	}

	/**
	 * gets string data from any properties file on given path
	 */
	public static void setProperty(String propsPath, String key, String value) {
		PropertiesConfiguration props = getProperties(propsPath);

		if (props != null) {
			props.setProperty(key, value);
			try {
				props.save(propsPath);
			} catch (ConfigurationException e) {
				getLogger().warn("Unable to set property of '{}'. {}", key, e.getMessage());
			}
		}
	}


	/** 
	 * gets string array data from any properties file on given path
	 */
	public static String[] getPropertyArray(String propsPath, String key) {
		PropertiesConfiguration props = getProperties(propsPath);
		String[] keyVal = null;

		if(props != null) {
			keyVal = props.getStringArray(key);
		}

		return keyVal;

	}
	

	/** 
	 * gets value for variable based on preference of system property first then environment variable
	 */
	public static String getVariable(String propname){
	    	String val = System.getProperty(propname, null);
	    	val = (val==null?System.getenv(propname):val);    	
	    	return val;
	    	
	    }

}
