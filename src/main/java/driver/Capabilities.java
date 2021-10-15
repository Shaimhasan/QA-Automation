package driver;

import common.Property;
import common.TestContext;
import core.Constants;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Capabilities {

    private DesiredCapabilities dc;

    public Capabilities() {
        Map<String, String> map = DriverContext.getInstance().getTechStack();

        dc = new DesiredCapabilities();

        if (!map.get("seleniumServer").equalsIgnoreCase("grid")) {
            dc.setCapability("name", TestContext.getInstance().testdataGet("fw.testDescription"));
        }

        if (TestContext.getInstance().testdata().containsKey("fw.projectName"))
            dc.setCapability("project", TestContext.getInstance().testdataGet("fw.projectName"));

        if (TestContext.getInstance().testdata().containsKey("fw.buildNumber"))
            dc.setCapability("build", TestContext.getInstance().testdataGet("fw.buildNumber"));

        if (TestContext.getInstance().testdataGet("fw.appPackage") != null)
            dc.setCapability("appPackage", TestContext.getInstance().testdataGet("fw.appPackage"));

        if (TestContext.getInstance().testdataGet("fw.appActivity") != null)
            dc.setCapability("appActivity", TestContext.getInstance().testdataGet("fw.appActivity"));

        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (!pair.getKey().equalsIgnoreCase("seleniumServer") && !pair.getKey().equalsIgnoreCase("description"))
                dc.setCapability(pair.getKey(), pair.getValue());
        }

        PropertiesConfiguration props = Property.getProperties(Constants.SELENIUMRUNTIMEPATH);
        List<String> desiredCapsList = Arrays.asList(props.getStringArray("desiredCapabilities." + DriverContext.getInstance().getBrowserName().replaceAll("\\s", "")));

        desiredCapsList.forEach(desiredCap -> {
            String[] par = desiredCap.split("==");
            if (par[1].trim().equalsIgnoreCase("true") || par[1].trim().equalsIgnoreCase("false"))
                dc.setCapability(par[0].trim(), Boolean.parseBoolean(par[1].trim()));
            else
                dc.setCapability(par[0].trim(), par[1].trim());
        });

        if(Boolean.parseBoolean(props.getString("useProxy"))) {
            Proxy proxy = new Proxy();
            if(props.getString("httpProxyURL") != null && props.getString("sslProxyURL") != null) {
                proxy.setHttpProxy(props.getString("httpProxyURL"))
                     .setSslProxy(props.getString("sslProxyURL"));

                dc.setCapability(CapabilityType.PROXY, proxy);
            } else {
                throw new IllegalArgumentException("useProxy set to true, but value not provided for httpProxyURL or sslProxyURL in runtime.properties.");
            }
        }
    }

    public DesiredCapabilities getCap() {
        return dc;
    }

}
