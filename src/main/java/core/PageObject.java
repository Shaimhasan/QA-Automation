package core;

import common.CommonPageObject;
import common.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static core.Locator.Loc;
import static core.Locator.getLocator;


/**
 * Utility class providing set of selenium wrapper methods for finding web elements with build in waits
 */


public class PageObject extends CommonPageObject {
    protected Logger log = LogManager.getLogger(this.getClass().getName());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssertions sa = null;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitDuration()));
    }

    public WebDriver getDriver() {
        log.debug("obtaining the driver object for current thread");
        return driver;
    }

    public WebDriverWait getWait() {
        log.debug("obtaining the wait object for current thread");
        return wait;
    }

    public void initialise(Object obj) {
        PageFactory.initElements(getDriver(), obj);
    }

    /*
     * Set of common methods for Page Objects which are defined with either
     * standard By locators or PageFactory
     */

    public PageObject gotoURL(String url) {
        log.debug("navigating to url:{}", url);
        getDriver().get(url);
        return this;
    }

    /**
     * Wait for page to load
     */
    public PageObject waitPageToLoad() {
        domLoaded();
        jqueryLoaded();
        //  angularLoaded();
        return this;
    }

    public PageObject waitDomToLoad() {
        domLoaded();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  angularLoaded();
        return this;
    }

    public PageObject waitPageToJqueryLoad() {
        jqueryLoadedWithTime();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  angularLoaded();
        return this;
    }

    public PageObject staticWait(String time) {
        try {
            Thread.sleep(Long.parseLong(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Wait for page to load based on document.readyState=complete
     */
    public void domLoaded() {
        log.debug("checking that the DOM is loaded");
        final JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Boolean domReady = js.executeScript("return document.readyState").equals("complete");

        if (!domReady) {
            getWait().until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return (js.executeScript("return document.readyState").equals("complete"));
                }
            });
        }
    }

    /**
     * Wait for JQuery to load
     */
    private void jqueryLoaded() {
        log.debug("checking that any JQuery operations complete");
        final JavascriptExecutor js = (JavascriptExecutor) getDriver();

        if ((Boolean) js.executeScript("return typeof jQuery != 'undefined'")) {
            boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");
            if (!jqueryReady) {
                getWait().until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return (Boolean) js.executeScript("return window.jQuery.active === 0");
                    }
                });
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void jqueryLoadedWithTime() {
        log.debug("checking that any JQuery operations complete");
        final JavascriptExecutor js = (JavascriptExecutor) getDriver();

        if ((Boolean) js.executeScript("return typeof jQuery != 'undefined'")) {
            boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");
            if (!jqueryReady) {
                getWait().until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return (Boolean) js.executeScript("return window.jQuery.active === 0");

                    }
                });
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for AngularJs to load
     */
    public void angularLoaded() {
        log.debug("checking that any AngularJS operations complete");
        final JavascriptExecutor js = (JavascriptExecutor) getDriver();
        if ((Boolean) js.executeScript("if (window.angular){return true;}")) {
            Boolean angularInjectorUndefined = (Boolean) js.executeScript("return angular.element(document).find('body').injector() === undefined");

            if (!angularInjectorUndefined) {
                Boolean angularReady = (Boolean) js.executeScript("return angular.element(document).find('body').injector().get('$http').pendingRequests.length === 0");

                if (!angularReady) {
                    getWait().until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return js.executeScript("return angular.element(document).find('body').injector().get('$http').pendingRequests.length === 0").equals(true);
                        }
                    });
                }
            } else {
                log.debug("no AngularJS injector defined so cannot wait");
            }
        }
    }


    /*
     * Further methods for Page Objects when using Standard By Locators (non PageFactory)
     * These utilise an additional Element class that wraps WebElement to provide additional
     * functionality or composite functions (see Element class for details).
     * These methods include in-built waits when finding elements as needed.
     */


    /**
     * Returns first element occurrence matching the supplied locator if an element exists in DOM
     */
    public Element $(By by) {
        Element el = new Element(driver, by);
        return el.scroll();
    }

    public Element $(Loc type, String locator, Object... variables) {
        Element el = new Element(driver, getLocator(type, locator, variables));
        return el.scroll();
    }

    /**
     * Returns first element occurrence matching the supplied locator based on the supplied wait condition
     */
    public Element $(ExpectedCondition<?> exp, int... delay) {
        Element el = new Element(driver, exp, delay);
        return el.scroll();
    }

    /**
     * Finds first nested element within current element matching the supplied locator
     */
    public Element $(By by, By sub, int... delay) {
        Element el = new Element(driver, ExpectedConditions.presenceOfNestedElementLocatedBy(by, sub), delay);
        return el.scroll();
    }

    /**
     * Returns all element occurrences matching the supplied locator if the elements exist in DOM
     */
    public List<Element> $$(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    public List<Element> $$(Loc type, String locator, Object[]... variables) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(type, locator, variables)));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Returns all element occurrences matching the supplied locator if the elements exist in DOM
     */
    public List<Element> $$(ExpectedCondition<List<WebElement>> exp, int... delay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), delay.length > 0 ? Duration.ofSeconds(delay[0]) : Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(exp);
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Finds all elements within current element matching the supplied locator
     */
    public List<Element> $$(By by, By sub, int... delay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), delay.length > 0 ? Duration.ofSeconds(delay[0]) : Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(by, sub));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Returns first element occurrence matching the supplied locator if an element exists in DOM
     */
    public Element findElement(By by) {
        Element el = new Element(driver, by);
        return el.scroll();
    }

    public Element findElement(Loc type, String locator, Object... variables) {
        Element el = new Element(driver, getLocator(type, locator, variables));
        return el.scroll();
    }

    /**
     * Returns first element occurrence matching the supplied locator if an element exists in DOM
     */
    public Element findElement(ExpectedCondition<?> exp, int... delay) {
        Element el = new Element(driver, exp, delay);
        return el.scroll();
    }

    /**
     * Finds first nested element within current element matching the supplied locator
     */
    public Element findElement(By by, By sub, int... delay) {
        Element el = new Element(driver, ExpectedConditions.presenceOfNestedElementLocatedBy(by, sub), delay);
        return el.scroll();
    }

    /**
     * Returns all element occurrences matching the supplied locator if the elements exist in DOM
     */
    public List<Element> findElements(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    public List<Element> findElements(Loc type, String locator, Object[]... variables) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(type, locator, variables)));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Returns all element occurrences matching the supplied locator if the elements exist in DOM
     */
    public List<Element> findElements(ExpectedCondition<List<WebElement>> exp, int... delay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), delay.length > 0 ? Duration.ofSeconds(delay[0]) : Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = getWait().until(exp);
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Finds all elements within current element matching the supplied locator
     */
    public List<Element> findElements(By by, By sub, int... delay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), delay.length > 0 ? Duration.ofSeconds(delay[0]) : Duration.ofSeconds(getWaitDuration()));
        List<WebElement> els = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(by, sub));
        List<Element> elements = setElements(els);
        if (!elements.isEmpty()) {
            elements.get(0).scroll();
        }
        return elements;
    }

    /**
     * Returns first element occurrence matching the supplied locator if an element is clickable
     */
    public Element findClickable(By by, int... delay) {
        Element el = new Element(driver, by, delay).clickable();
        return el.scroll();
    }


    /**
     * Builds and returns list of nested elements
     */
    public List<Element> setElements(List<WebElement> els) {
        List<Element> list = new ArrayList<>();
        for (WebElement el : els) {
            list.add(new Element(driver, el));
        }
        return list;
    }

    /**
     * Checks for element existence
     */
    public boolean exist(By by, int... delay) {
        Element el = new Element(driver, by, delay);
        return (el.element() == null) ? false : true;
    }

    /**
     * Returns duration for specified waits
     */
    public int getWaitDuration() {
        final int defaultWait = 10;
        int duration;
        try {
            duration = Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getInt("defaultWait");
            log.debug("selenium getDriver() getWait() time set from environment properties");
        } catch (Exception e) {
            duration = defaultWait;
            log.debug("selenium getDriver() getWait() time not available from environment properties...default applied");
        }
        return duration;
    }

    public void switchWindow(String parent) {
        log.debug("parent window handle:{}", parent);
        switching:
        while (true) {
            for (String handle : getDriver().getWindowHandles()) {
                if (!handle.equals(parent)) {
                    log.debug("switching to window handle:{}", handle);
                    getDriver().switchTo().window(handle);
                    break switching;
                }
            }
        }
    }

    public void switchFrame(String frameLocator, int... retries) {
        try {
            getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.switchFrame(frameLocator, retries);
            } else {
                throw e;
            }
        }
    }

    public void switchFrame(By by, int... retries) {
        try {
            getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.switchFrame(by, retries);
            } else {
                throw e;
            }
        }
    }

    public void switchFrame(Element el, int... retries) {
        try {
            getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(el.element()));
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.switchFrame(el, retries);
            } else {
                throw e;
            }
        }
    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public SoftAssertions sa() {
        if (sa == null) {
            sa = new SoftAssertions();
        }
        return sa;
    }

    /**
     * capture displayed area or scrolling screenshot and return a file object.
     * to capture scrolling screenshot property scrollingScreenshot = true has to be set in runtime.properties file
     */
    public File grabScreenshot() {
        return Screenshot.grabScreenshot(driver);
    }
}
