package core;

import common.TestContext;
import driver.DriverFactory;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BasePO extends PageObject {

    public BasePO() {
        super(DriverFactory.getInstance().getDriver());
    }

    public WebDriver getDriver() {
        log.debug("obtaining the driver for current thread");
        return DriverFactory.getInstance().getDriver();
    }

    public WebDriverWait getWait() {
        log.debug("obtaining the wait for current thread");
        return DriverFactory.getInstance().getWait();
    }

    public void quitDriver() {
        log.debug("quitting the driver and removing from current thread of driver factory");
        DriverFactory.getInstance().quit();
    }

    public SoftAssertions sa() {
        return TestContext.getInstance().sa();
    }

    public void performDriverOperation(String action, String value) {
        log.debug("performing selenium driver operation:{};{}", action, value);
        switch (action) {
            case "launch":
                getDriver();
                break;
            case "goto":
                gotoURL(value);
                break;
            case "back":
                getDriver().navigate().back();
                break;
            case "forward":
                getDriver().navigate().forward();
                break;
            case "quit":
                quitDriver();
                break;
        }
    }

    public Boolean includesClass(String actClasses, String expClass) {
        Optional<String> classFindResult = Arrays.stream(actClasses.split(" ")).filter(cl -> cl.equals(expClass)).findFirst();
        if (classFindResult.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public Map<String, By> getUI(String className) {
        Map<String, By> map = new HashMap<>();
        Class<?> cl;
        try {
            cl = Class.forName(className);
            Constructor<?> con;
            con = cl.getConstructor();
            Object obj;
            obj = con.newInstance();
            log.debug("found uimap:{}", className);
            Field[] fields = cl.getFields();

            for (Field f : fields) {
                String name = f.getName();
                if (f.get(obj) instanceof By) {
                    By by = (By) f.get(obj);
                    map.put(name, by);
                    log.debug("found element:{}", name);
                }
            }
        } catch (Exception e) {
            log.error("unable to process uimap", e);
            return null;
        }
        return map;
    }

}
