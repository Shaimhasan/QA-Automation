package core;

import common.FileHelper;
import common.Property;
import driver.DriverContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static core.Locator.Loc;
import static core.Locator.getLocator;


/**
 * Utility class providing set of selenium wrapper methods
 */


public class Element {
    private By by;
    private WebElement element;
    private WebDriver driver;
    private WebDriverWait wait;
    protected Logger log = LogManager.getLogger(this.getClass().getName());
    private static final String SFDC_REPLACE_PATTERN = "%1$s/.//*[text()='%2$s']";

    public Element(WebDriver driver, WebElement e) {
        this.driver = driver;
        wait = new WebDriverWait(driver, getWaitDuration());
        this.element = e;
    }

    public Element(WebDriver driver, WebElement e, By by) {
        this.driver = driver;
        wait = new WebDriverWait(driver, getWaitDuration());
        this.element = e;
        this.by = by;
    }

    public Element(WebDriver driver, By by, int... delay) {
        this.driver = driver;
        this.by = by;
        try {
            wait = new WebDriverWait(driver, delay.length > 0 ? delay[0] : getWaitDuration());
            this.element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            log.debug("element located successfully:{}", by);
        } catch (Exception e) {
            this.element = null;
            log.debug("element not located:{}", by);
            log.debug(e.getMessage());
        }
    }

    public Element(WebDriver driver, ExpectedCondition<?> exp, int... delay) {
        this.driver = driver;
        this.by = null;
        try {
            wait = new WebDriverWait(driver, delay.length > 0 ? delay[0] : getWaitDuration());
            this.element = (WebElement) wait.until(exp);
        } catch (Exception e) {
            this.element = null;
            log.debug("element not located:{}", by);
            log.debug(e.getMessage());
        }
    }

    public By by() {
        return by;
    }

    public WebElement element() {
        return element;
    }

//    public MobileElement mobElement() {
//        return (MobileElement) element;
//    }

    /**
     * searches again for the element using the by
     */
    public Element refind(int... retries) {
        log.info("Attempting to refind the element: {}", by);
        int attempts = 0;
        Boolean retry = true;
        int maxRetry = retries.length > 0 ? retries[0] : getFindRetries();
        while (attempts < maxRetry && retry) {
            try {
                log.debug("retry number {}", attempts);
                this.element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                this.element.getTagName();
                retry = false;
            } catch (Exception e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    log.debug(e1.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
            attempts++;
        }
        return this;
    }

    /**
     * Returns a nested element
     */
    public Element $(By by) {
        return new Element(driver, (WebElement) wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(this.element, by)), by);
    }

    public Element $(Loc type, String locator, Object... variables) {
        By localBy = getLocator(type, locator, variables);
        return new Element(driver, (WebElement) wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(this.element, localBy)), localBy);
    }

    /**
     * Returns list of nested elements
     */
    public List<Element> $$(By by) {
        List<WebElement> els = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(this.element, by));
        List<Element> list = new ArrayList<>();
        for (WebElement el : els) {
            list.add(new Element(driver, el));
        }
        return list;
    }

    public List<Element> $$(Loc type, String locator, Object... variables) {
        List<WebElement> els = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(this.element, getLocator(type, locator, variables)));
        List<Element> list = new ArrayList<>();
        for (WebElement el : els) {
            list.add(new Element(driver, el));
        }
        return list;
    }

    /**
     * Returns a nested element
     */
    public Element findElement(By by) {
        return new Element(driver, (WebElement) wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(this.element, by)), by);
    }

    public Element findElement(Loc type, String locator, Object... variables) {
        By localBy = getLocator(type, locator, variables);
        return new Element(driver, (WebElement) wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(this.element, localBy)), localBy);
    }

    /**
     * Returns list of nested elements
     */
    public List<Element> findElements(By by) {
        List<WebElement> els;

        if (this.by != null) {
            els = (List<WebElement>) wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(this.by, by));
        } else {
            els = this.element.findElements(by);
        }

        List<Element> list = new ArrayList<>();
        for (WebElement el : els) {
            list.add(new Element(driver, el));
        }
        return list;
    }

    public List<Element> findElements(Loc type, String locator, Object... variables) {
        List<WebElement> els = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(this.element, getLocator(type, locator, variables)));
        List<Element> list = new ArrayList<>();
        for (WebElement el : els) {
            list.add(new Element(driver, el));
        }
        return list;
    }

    /**
     * wait for the element to become visible
     */
    public Element visible(int... retries) {
        {
            try {
                wait.until(ExpectedConditions.visibilityOf(this.element));
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.visible(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    public Element isVisible(int... retries) {
        {
            try {
                wait.until(ExpectedConditions.visibilityOf(this.element));
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.visible(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    /**
     * wait for the element to become invisible
     */
    public Element invisible(int... retries) {
        {
            try {
                wait.until(ExpectedConditions.invisibilityOf(this.element));
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.invisible(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    /**
     * wait for the element to become Enabled;
     */
    public Element enabled(int... retries) {
        {
            try {
                wait.until((ExpectedCondition<Boolean>) driver -> this.element.isEnabled());
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.enabled(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    /**
     * wait for the element to become Disabled;
     */
    public Element disabled(int... retries) {
        {
            try {
                wait.until((ExpectedCondition<Boolean>) driver -> !this.element.isEnabled());
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.disabled(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    /**
     * wait for the element to become displayed;
     */
    public Element displayed(int... retries) {
        {
            try {
                wait.until((ExpectedCondition<Boolean>) driver -> this.element.isDisplayed());
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.displayed(0);
                } else {
                    throw e;
                }
            }
            return this;
        }
    }

    public Boolean isDisplayed(int... retries) {
        boolean value;
        {
            try {
                value = this.element.isDisplayed();
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    return this.isDisplayed(0) != null;
                } else {
                    throw e;
                }
            }

        }
        return value;
    }

    /**
     * wait for the element to become clickable
     */
    public Element clickable(int... retries) {
        try {
            this.element = wait.until(ExpectedConditions.elementToBeClickable(this.element));
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.clickable(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * wait for the element to become NotClickable
     */
    public boolean isNotClickable(int... retries) {
        try {
            this.element = wait.until(ExpectedConditions.elementToBeClickable(this.element));
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.clickable(0) != null;
            } else {
                throw e;
            }

        }
        return false;
    }

    /**
     * wait for the element to have values loaded
     */
    public Element notEmpty(int... retries) {
        try {
            if (this.getValue() != null) {
                wait.until((ExpectedCondition<Boolean>) driver -> this.getValue().length() != 0);
            } else {
                wait.until((ExpectedCondition<Boolean>) driver -> this.getText().length() != 0);
            }
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.notEmpty(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * wait for the element to have values loaded
     */
    public Element empty(int... retries) {
        try {
            if (this.getValue() != null) {
                wait.until((ExpectedCondition<Boolean>) driver -> this.getValue().isEmpty());
            } else {
                wait.until((ExpectedCondition<Boolean>) driver -> this.getText().isEmpty());
            }
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.empty(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    public String getText(int... retries) {
        String str = null;
        try {
            str = this.element.getText();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.getText(0);
            } else {
                throw e;
            }
        }
        return str;
    }

    public String getValue(int... retries) {
        String str = null;
        try {
            str = this.element.getAttribute("value");
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.getValue(0);
            } else {
                throw e;
            }
        }
        return str;
    }

    public String getAttribute(String attr, int... retries) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            return this.element.getAttribute(attr);
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                return this.getAttribute(attr, 0);
            } else {
                throw e;
            }
        }
    }

    public Element clear(int... retries) {
        try {
            this.element().clear();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.clear(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    public Element sendKeys(String val, int... retries) {
        try {
            try {
                this.element().sendKeys(val);
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.sendKeys(val, 0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkSendKeysJS()) {
                sendKeysJS(val);
            }
        }
        return this;
    }

    public Element focus(int... retries) {
        try {
            new Actions(driver).moveToElement(this.element()).perform();
            driver.switchTo().activeElement();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.focus(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    public Element click(int... retries) {
        try {
            try {
                this.element().click();
            } catch (ElementClickInterceptedException e) {
                if (checkClickJS()) {
                    clickJS();
                }
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.click(0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkClickJS()) {
                clickJS();
            }
        }
        return this;
    }

    public Element customClick(String orderNumber, int... retries) {
        try {
            try {
                String orderNum = "//td[text()=" + "'" + orderNumber + " - 1" + "'" + "]";
                System.out.println("Order Number  >" + orderNum);
                Element element = this.findElement(By.xpath(orderNum));
                element.click();

            } catch (ElementClickInterceptedException e) {
                if (checkClickJS()) {
                    clickJS();
                }
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.click(0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkClickJS()) {
                clickJS();
            }
        }
        return this;
    }

    public Element customClickDispatch(String orderNumber, int... retries) {
        try {
            try {
                String orderNum = "//b[text()='" + orderNumber + "']";
                System.out.println("Order Number  >" + orderNum);
                Element element = this.findElement(By.xpath(orderNum));
                element.isDisplayed();
                element.click();

            } catch (ElementClickInterceptedException e) {
                if (checkClickJS()) {
                    clickJS();
                }
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.click(0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkClickJS()) {
                clickJS();
            }
        }
        return this;
    }

    public Element clickBasedOnXpath(String dictionaryKeyValue, String xpath1Value1, String xpathValue2, int... retries) {
        try {
            try {
                String xpathValue = xpath1Value1 + dictionaryKeyValue + xpathValue2;
                System.out.println("xpathValue print  >" + xpathValue);
                Element element = this.findElement(By.xpath(xpathValue));
                wait.until(ExpectedConditions.visibilityOf(this.element));
                element.click();

            } catch (ElementClickInterceptedException e) {
                if (checkClickJS()) {
                    clickJS();
                }
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.click(0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkClickJS()) {
                clickJS();
            }
        }
        return this;
    }

    public Element clickBasedOnOnlyXpath(String xpath1Value1, String xpathValue2, int... retries) {
        try {
            try {
                String xpathValue = xpath1Value1  + xpathValue2;
                System.out.println("xpathValue print  >" + xpathValue);
                Element element = this.findElement(By.xpath(xpathValue));
                element.isDisplayed();
                element.click();

            } catch (ElementClickInterceptedException e) {
                if (checkClickJS()) {
                    clickJS();
                }
            } catch (Exception e) {
                if (!(retries.length > 0 && retries[0] == 0)) {
                    this.refind(retries);
                    this.click(0);
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            if (checkClickJS()) {
                clickJS();
            }
        }
        return this;
    }

    public Element doubleClick(int... retries) {
        Actions action = new Actions(driver);
        try {
            action.doubleClick(this.element).build().perform();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.doubleClick(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    public Element rightClick(int... retries) {
        Actions action = new Actions(driver);
        try {
            action.contextClick(this.element).build().perform();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.rightClick(0);
            } else {
                throw e;
            }
        }
        return this;
    }


    /**
     * send text using character chord to overwrite field
     */
    public Element sendKeysChord(String val, int... retries) {
        try {
            this.element.sendKeys(Keys.chord(Keys.CONTROL, "a"), val);
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.sendKeysChord(val, 0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * send text using character chord to overwrite field
     */
    public Element sendKeysChord(Keys key, int... retries) {
        try {
            this.element.sendKeys(Keys.chord(Keys.CONTROL, "a"), key);
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.sendKeysChord(key, 0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * mimic hitting the enter key
     */
    public Element sendEnter(int... retries) {
        try {
            this.element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.sendEnter(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * select action for checkboxes / radio buttons
     */
    public Element select(Boolean val, int... retries) {
        try {
            if (this.element.isSelected() != val)
                this.element.click();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.select(val, 0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * select action for checkboxes / radio buttons
     */
    public Element select(int... retries) {
        try {
            if (!this.element.isSelected())
                this.element.click();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.select(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * deselect action for checkboxes / radio buttons
     */
    public Element unselect(int... retries) {
        try {
            if (this.element.isSelected())
                this.element.click();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.unselect(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * Return select object for a WebElement
     */
    public Select dropdown(int... retries) {
        Select sel = null;
        try {
            sel = new Select(this.element);
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.dropdown(0);
            } else {
                throw e;
            }
        }
        return sel;
    }

    /**
     * Performs mouse click action on the element using javascript where native click does not work
     */
    public Element clickJS() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", this.element);
        return this;
    }

    /**
     * send text using javascript
     */
    public Element sendKeysJS(String val) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('value', '" + val + "')", this.element);
        return this;
    }

    /**
     * Return all options within a dropdown as string array
     */
    public List<String> getDropdownOptionsValues() {
        List<String> optionsText = new ArrayList<>();
        List<WebElement> options = this.dropdown().getOptions();
        for (WebElement option : options) {
            optionsText.add(option.getAttribute("value"));
        }
        return optionsText;
    }

    public String getFirstDropdownOptionsValues() {
        String firstValue;
        WebElement options = this.dropdown().getFirstSelectedOption();
        firstValue = options.getText();
        return firstValue;
    }

    /**
     * Return all options within a dropdown as string array
     */
    public List<String> getDropdownOptionsText() {
        List<String> optionsText = new ArrayList<>();
        List<WebElement> options = this.dropdown().getOptions();
        for (WebElement option : options) {
            optionsText.add(option.getText());
        }
        return optionsText;
    }

    /**
     * Return all options groups within a dropdown as string array
     */
    public List<String> getDropdownOptGroupsText() {
        List<String> optGroupsText = new ArrayList<>();
        List<WebElement> optGroups = this.element().findElements(By.tagName("optgroup"));
        for (WebElement optGroup : optGroups) {
            optGroupsText.add(optGroup.getText());
        }
        return optGroupsText;
    }

    /**
     * Return all options groups within a dropdown as list of elements
     */
    public List<WebElement> getDropdownOptGroupsElements() {
        return this.element().findElements(By.tagName("optgroup"));
    }

    /**
     * Return all options within an option group of a dropdown as string array
     */
    public List<String> getDropdownOptionsTextWithinGroup(String group) {
        List<String> optionsText = new ArrayList<>();
        List<WebElement> options = this.element().findElements(By.xpath("//optgroup[@label=" + group + "]/option"));
        for (WebElement option : options) {
            optionsText.add(option.getText());
        }
        return optionsText;
    }

    /**
     * Return all options within an option group of a dropdown as list of elements
     */
    public List<WebElement> getDropdownOptionsElementsWithinGroup(String group) {
        return this.element().findElements(By.xpath("//optgroup[@label=" + group + "]/option"));
    }


    /**
     * Return all inner text within a list of elements as string array
     */
    public List<String> getAllText(List<Element> els) {
        List<String> elementsText = new ArrayList<>();
        for (Element el : els) {
            elementsText.add(el.element().getText());
        }
        return elementsText;
    }

    /**
     * Performs mouse action move to element on the screen
     */
    public Element move() {
        Actions action = new Actions(driver);
        action.moveToElement(this.element).build().perform();
        return this;
    }

    /**
     * Performs mouse action move to a parent element on the screen, locate child element and click
     */
    public Element moveAndClick(WebElement elChild) {
        Actions action = new Actions(driver);
        action.moveToElement(this.element).build().perform();
        elChild.click();
        return this;
    }

    /**
     * Performs mouse action click and hold
     */
    public Element clickAndHold() {
        Actions action = new Actions(driver);
        action.clickAndHold(this.element).build().perform();
        return this;
    }

    /**
     * Performs mouse action release button
     */
    public Element release() {
        Actions action = new Actions(driver);
        action.release().build().perform();
        return this;
    }

    /**
     * Hover an element
     */
    public Element hover(int... retries) {
        Actions action = new Actions(driver);
        try {
            action.moveToElement(this.element).build().perform();
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.hover(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    /**
     * Highlights an element with a blue border.....useful when debugging/taking screenshots
     */
    public Element highlight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].style.border";
        String border = "3px solid blue";
        js.executeScript(script + "='" + border + "'", this.element);
        return this;
    }

    /**
     * Returns duration for specified waits
     */
    public static int getWaitDuration() {
        final int defaultWait = 10;
        int duration;
        try {
            duration = Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getInt("defaultWait");
        } catch (Exception e) {
            duration = defaultWait;
        }
        return duration;
    }

    public static int getFindRetries() {
        final int defaultFindRetries = 10;
        int refind;
        try {
            refind = Integer.parseInt(System.getProperty("cukes.selenium.defaultFindRetries"));
        } catch (Exception e) {
            refind = defaultFindRetries;
        }
        return refind;
    }

    public Boolean checkClickJS() {
        return Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getBoolean("clickUsesJavaScript." + DriverContext.getInstance().getBrowserName().replaceAll("\\s", ""), false);
    }

    public Boolean checkSendKeysJS() {
        return Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getBoolean("sendKeysUsesJavaScript." + DriverContext.getInstance().getBrowserName().replaceAll("\\s", ""), false);
    }

    /**
     * Scrolls to element to avoid issues with element location being unclickable
     */
    public Element scroll() {

        if (!(driver instanceof AndroidDriver) && !(driver instanceof IOSDriver) && element() != null) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element());
            } catch (Exception e) {
                log.warn("scrolling to element failed", e);
            }
        }
        return this;
    }

    /**
     * Get count of number of table rows
     */
    public int getTableRowCount() {
        return getDataRowCount() + getHeadRowCount();
    }

    /**
     * Get count of number of table header rows
     */
    public int getHeadRowCount() {
        return this.findElements(By.tagName("th")).size();
    }

    /**
     * Get count of number of table data rows
     */
    public int getDataRowCount() {
        return this.findElements(By.tagName("tr")).size();
    }

    /**
     * Get count of number of table header columns
     */
    public int getHeadColumnCount(int rowIndex) {
        return getHeadRowElements(rowIndex).size();
    }

    /**
     * Get count of number of table data columns
     */
    public int getDataColumnCount(int rowIndex) {
        return getDataRowElements(rowIndex).size();
    }

    /**
     * Get header row of table
     */
    public List<Element> getHeaderColumns() {
        return this.findElements(By.tagName("th"));
    }

    /**
     * Get header row of table
     */
    public List<String> getHeaderColumnsWithGivenAttribute() {
        String attribForTableHeader = Property.getVariable(Constants.ATTRIBUTE_FOR_TABLE_HEADER_COMPARE);
        List<Element> headerElements = this.findElements(By.tagName("th"));
        List<String> headerElementRes = new ArrayList<>();
        for (Element ele : headerElements) {
            if (ele.element().isDisplayed())
                headerElementRes.add(ele.getChildAttributeOnly(attribForTableHeader));
        }
        return headerElementRes;
    }

    /**
     * Get the list of header columns within a given row of a table
     */
    public List<Element> getHeadRowElements(int rowIndex) {
        return this.findElements(By.tagName("tr")).get(rowIndex).findElements(By.tagName("th"));
    }

    /**
     * Get the list of data columns within a given row of a table
     */
    public List<Element> getDataRowElements(int rowIndex) {
        return this.findElements(By.tagName("tr")).get(rowIndex).findElements(By.xpath("./td | ./th"));
    }

    /**
     * Get all rows of a table
     */
    public List<Element> getAllRows() {
        return this.findElements(By.tagName("tr"));
    }

    /**
     * Get table row based on row index
     */
    public Element getRow(int row) {
        return this.findElements(By.tagName("tr")).get(row);
    }

    public Element getRowValue(String orderN, String category) {
        String xpathValue = "//td[text()=" + "'" + orderN + " - " + category + "'" + "]";
        System.out.println(xpathValue);
        return this.findElement(By.xpath(xpathValue));
    }

    public Element getRowValueCutAndWrap(String orderN, String category) {
        String xpathValue = "//td[text()=" + "'" + orderN + " - " + category + "'" + "]//following-sibling::td[text()='Cut & Wrapped']";
        System.out.println(xpathValue);
        return this.findElement(By.xpath(xpathValue));
    }

    public Element getRowValueText(String orderN, String category) {
        String xpathValue = "//td[text()=" + "'" + orderN + "-" + category + "'" + "]";
        System.out.println(xpathValue);
        return this.findElement(By.xpath(xpathValue));
    }

    public Element getTextBasedOnXpath1AndXpath2AndDictionaryKey(String xpath1, String dictionaryKey, String xpath2) {
        String xpathValue = xpath1 + dictionaryKey + xpath2;
        System.out.println(xpathValue);
        return this.findElement(By.xpath(xpathValue));
    }
    public Element getTextBasedOnXpath1AndXpath2(String xpath1, String xpath2) {
        String xpathValue = xpath1 + xpath2;
        System.out.println(xpathValue);
        return this.findElement(By.xpath(xpathValue));
    }

    /**
     * Get table data cell based on row index and column index
     */
    public Element getDataCellElement(int rowIndex, int columnIndex) {
        List<Element> dataRowElements = getDataRowElements(rowIndex);
        if (columnIndex < dataRowElements.size()) {
            return dataRowElements.get(columnIndex);
        } else {
            return dataRowElements.get(dataRowElements.size() - 1);
        }
    }

    /**
     * Get table header cell based on row index and column index
     */
    public Element getHeadCellElement(int rowIndex, int columnIndex) {
        return getHeadRowElements(rowIndex).get(columnIndex);
    }

    /**
     * Get table row based on matching a specified value against a value held in a given column
     */
    public Element tableGetRow(String val, int matchCol) {
        List<Element> rows = this.findElements(By.tagName("tr"));
        Element el = null;
        for (int i = 1; i < rows.size(); i++) {
            if (rows.get(i).findElements(By.tagName("td")).get(matchCol).getText().equalsIgnoreCase(val)) {
                el = rows.get(i);
                break;
            }
        }
        return el;
    }

    /**
     * Get table row numbers based on matching a col Name and Value
     */
    public Set<Integer> getMatchingRows(String colName, String colValue) {
        List<Element> rows = getAllRows();
        Set<Integer> set = new HashSet<>(rows.size());
        String attribForTableCompare = Property.getVariable(Constants.ATTRIBUTE_FOR_TABLE_CELL_COMPARE);

        int colIndex = getMatchingColumnNum(colName);

        if (colIndex != -1) {
            for (int i = 1; i <= rows.size() - 1; i++) {
                Element dataCellElement = getDataCellElement(i, colIndex);

                if (dataCellElement.getText().equalsIgnoreCase(colValue)) {
                    set.add(i);
                } else if (dataCellElement.getText().isEmpty() && attribForTableCompare != null) {
                    List<Element> dataCellChildElements = dataCellElement.findElements(By.xpath(".//*"));

                    for (Element childEle : dataCellChildElements) {
                        if (childEle.getAttribute(attribForTableCompare).equalsIgnoreCase(colValue)) {
                            set.add(i);
                        }
                    }
                }
            }
        }
        return set;
    }

    /**
     * Get table rows based on matching a col Name
     */
    public List<String> getRowValuesForGivenColumn(String colName) {
        List<Element> rows = getAllRows();
        List<String> listOfRows = new ArrayList<>();
        int colIndex = getMatchingColumnNum(colName);

        if (colIndex != -1) {
            for (int i = 1; i <= rows.size() - 1; i++) {
                Element dataCellElement = getDataCellElement(i, colIndex);
                listOfRows.add(dataCellElement.getText());
            }
        }
        return listOfRows;
    }

    /**
     * Get top nine table sub rows based on matching a col Name and row number
     */
    public List<String> getTopNineSubRowValuesForGivenColumn(String colName, int rowNumber) {
        List<String> listOfRows = new ArrayList<>();
        int colIndex = getMatchingColumnNum(colName);

        if (colIndex != -1) {
            for (int i = rowNumber - 2; i <= (rowNumber - 2) + 8; i++) {
                Element dataCellElement = getDataCellElement(i, colIndex);
                listOfRows.add(dataCellElement.getText());
            }
        }
        return listOfRows;
    }

    /**
     * Get table row number based on matching a col Name and col Value
     */
    public int getMatchingRowNumber(String colName, String colValue) {
        List<Element> rows = getAllRows();
        Element eleToClick;
        int colNum = getMatchingColumnNum(colName);
        if (colNum != -1) {
            for (int i = 1; i <= rows.size(); i++) {
                eleToClick = checkForMatchInRow(i, colNum, colValue);
                if (eleToClick != null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Get table row number based on matching two col Names and col Values
     */
    public int getMatchingRowNumber(String colName1, String colValue1, String colName2, String colValue2) {
        List<Element> rows = getAllRows();
        Element eleToClick;
        int colNum1 = getMatchingColumnNum(colName1);
        int colNum2 = getMatchingColumnNum(colName2);
        if (colNum1 != -1 && colNum2 != -1) {
            for (int i = 2; i <= rows.size(); i++) {
                eleToClick = checkForMatchInRow(i, colNum1, colValue1);
                if (eleToClick != null && checkForMatchInRow(i, colNum2, colValue2) != null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Get table column number based on matching a col Name and col Value
     */
    public Set<Integer> getMatchingColumns(String colName, String colValue) {
        List<Element> rows = this.findElements(By.tagName("tr"));
        Set<Integer> set = new HashSet<>();

        List<Element> headerCols = getHeadRowElements(0);
        for (int i = 1; i <= rows.size() - 1; i++) {
            for (int j = 0; j < headerCols.size() - 1; j++) {
                if (headerCols.get(j).getText().equalsIgnoreCase(colName) && getDataCellElement(i, j).getText().equalsIgnoreCase(colValue)) {
                    set.add(j);
                }
            }
        }
        return set;
    }

    /**
     * Get all data held in a table and return as a string array
     */
    public List<ArrayList<String>> getTableAsArray() {
        ArrayList<ArrayList<String>> tabledata = new ArrayList<>();
        List<WebElement> rows = this.element().findElements(By.tagName("tr"));
        int numrows = rows.size();
        for (int i = 0; i < numrows; i++) {
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            if (!cols.isEmpty()) {
                ArrayList<String> rowdata = new ArrayList<>();
                for (int j = 0; j < cols.size(); j++) {
                    rowdata.add(cols.get(j).getText().trim());
                }
                tabledata.add(rowdata);
            }
        }
        return tabledata;

    }

    public Element selectFromDropdown(String value, int... retries) {
        switch (this.element().getTagName().toLowerCase()) {
            case "select":
                this.dropdown(retries).selectByVisibleText(value);
                break;
            case "input":
                this.sendKeys(Keys.chord(Keys.ENTER));
                final String xPath = String.format("//*[contains(@id,'%s')]", this.getAttribute("id"));
                this.findElement(By.xpath(String.format(SFDC_REPLACE_PATTERN, xPath, value))).click();
                break;
            case "a":
                this.findAnchorTagDropDownAndClick(value);
                break;
            case "button":
                this.click();
                this.findElement(By.xpath(String.format(".//following-sibling::ul//a[contains(text(), '%s')]", value))).click();
                break;
            case "div":
                this.sendKeys(Keys.chord(Keys.ENTER));
                final String xPath1 = String.format("//div[contains(@id,'%s')]", this.getAttribute("id"));
                this.findElement(By.xpath(String.format(SFDC_REPLACE_PATTERN, xPath1, value))).click();
                break;
            default:
                break;
        }

        return this;
    }

    /**
     * findAnchorTagDropDownAndClick
     * Looks for matching anchor tag and selects the value.
     * Logic largely designed for the nuances of SFDC dropdowns
     */
    private void findAnchorTagDropDownAndClick(String value) {
        if (this.getAttribute("id") != null && !this.getAttribute("id").isEmpty()) {
            final String xPath = String.format("//*[contains(@id,'%s')]", this.getAttribute("id"));
            this.findElement(By.xpath(String.format(SFDC_REPLACE_PATTERN, xPath, value))).click();
        } else {
            List<WebElement> parentDiv = this.element.findElements(By.xpath(".//ancestor::div[contains(@class, 'uiPopupTrigger')]"));
            if (!parentDiv.isEmpty()) {
                final String xPath = String.format("//*[contains(@aria-labelledby,'%s')]", parentDiv.get(0).getAttribute("id"));
                this.findElement(By.xpath(String.format(SFDC_REPLACE_PATTERN, xPath, value))).click();
            } else {
                this.findElement(By.xpath(String.format(".//a[contains(text(), '%s')]", value))).click();
            }
        }
    }

    /**
     * SelectValueIndex
     * Where ValueIndex can be the keyword First or Last or a Number
     * representing the ListOption to be selected
     */
    public Element select(String valueIndex, int... retries) {
        try {
            switch (valueIndex) {
                case "First":
                    this.dropdown().selectByIndex(0);
                    break;
                case "Last":
                    this.dropdown().selectByIndex(this.dropdown().getOptions().size() - 1);
                    break;
                case "Number":
                    this.dropdown().selectByIndex(Integer.parseInt(valueIndex));
                    break;
                default:
                    this.dropdown().selectByIndex(Integer.parseInt(valueIndex));
            }
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.select(valueIndex, 0);
            } else {
                throw e;
            }
        }
        return this;

    }

    /**
     * getMatchingColumnNum
     * Returns column number for a matching name
     */
    public int getMatchingColumnNum(String colName) {
        List<Element> headRowElements = getHeadRowElements(0);
        if (headRowElements == null || headRowElements.isEmpty()) headRowElements = getDataRowElements(1);
        //For Eclipse Application the header was available in second row, First row returns the whole table
        if (headRowElements == null || headRowElements.isEmpty() || headRowElements.get(0).getText().length() > 100)
            headRowElements = getDataRowElements(2);

        String attribForTableCompare = Property.getVariable(Constants.ATTRIBUTE_FOR_TABLE_CELL_COMPARE);
        String attribForTableHeader = Property.getVariable(Constants.ATTRIBUTE_FOR_TABLE_HEADER_COMPARE);

        int colIndex = headRowElements.stream().map(element1 -> element1.getText().toLowerCase())
                .collect(Collectors.toList())
                .indexOf(colName.toLowerCase());

        if (colIndex == -1 && attribForTableCompare != null) {
            colIndex = headRowElements.stream().map(element1 -> element1.getAttribute(attribForTableCompare).toLowerCase())
                    .collect(Collectors.toList())
                    .indexOf(colName.toLowerCase());
        }
        if (colIndex == -1 && attribForTableHeader != null) {
            List<Element> headerElements = this.findElements(By.tagName("th"));
            List<String> headerElementRes = new ArrayList<>();
            for (Element ele : headerElements) {
                headerElementRes.add(ele.getChildAttributeOnly(attribForTableHeader).toLowerCase());
            }
            colIndex = headerElementRes.indexOf(colName.toLowerCase());
        }
        return colIndex;
    }

    /**
     * checkforMatchInRow
     * Looks within a specific row for a value passed in
     */

    public Element checkForMatchInRow(int rowNum,
                                      int colNum,
                                      String valToFind) {
        Element dataCellElement = getDataCellElement(rowNum, colNum);
        String attribForTableCompare = Property.getVariable(Constants.ATTRIBUTE_FOR_TABLE_CELL_COMPARE);

        if (dataCellElement.getCurrentElementTextOnly().equalsIgnoreCase(valToFind)) {
            return dataCellElement;
        } else if (dataCellElement.getCurrentElementTextOnly().contains(valToFind)) {
            log.warn("Matched value by 'contains' instead of exact match");
            return dataCellElement;
        } else {
            List<Element> dataCellChildElements = dataCellElement.findElements(By.xpath(".//*"));
            for (Element childEle : dataCellChildElements) {
                if (childEle.element != null &&
                        (childEle.getAttribute(attribForTableCompare) != null &&
                                childEle.getAttribute(attribForTableCompare).equalsIgnoreCase(valToFind) ||
                                childEle.getCurrentElementTextOnly().equalsIgnoreCase(valToFind) ||
                                childEle.getCurrentElementTextOnly().contains(valToFind))) {
                    return childEle;
                }
            }
        }

        return null;
    }

    /**
     * checkforMatchInTable
     * Looks within a table for a value passed in
     */
    public Element checkForMatchInTable(String valToFind) {
        List<Element> rows = getAllRows();
        Element eleToClick;

        for (int i = 0; i <= rows.size() - 1; i++) {
            for (int j = 0; j <= getDataColumnCount(i) - 1; j++) {
                eleToClick = checkForMatchInRow(i, j, valToFind);
                if (eleToClick != null)
                    return eleToClick;
            }
        }
        return null;
    }

    /**
     * getMatchingRowNum
     * Looks for match on three columns passed in and returns element in the first column
     */
    public Element getMatchingRowNum(String firstColName, String firstValToMatch,
                                     String secondColName, String secondValToMatch,
                                     String thirdColName, String thirdValToMatch) {
        List<Element> rows = getAllRows();
        Element eleToClick;

        int firstColNum = getMatchingColumnNum(firstColName);
        int secondColNum = getMatchingColumnNum(secondColName);
        int thirdColNum = getMatchingColumnNum(thirdColName);

        if (firstColNum != -1 && secondColNum != -1 && thirdColNum != -1) {

            for (int i = 1; i <= rows.size() - 1; i++) {
                eleToClick = checkForMatchInRow(i, firstColNum, firstValToMatch);

                if (eleToClick != null &&
                        checkForMatchInRow(i, secondColNum, secondValToMatch) != null &&
                        checkForMatchInRow(i, thirdColNum, thirdValToMatch) != null) {
                    return eleToClick;
                }
            }
        }

        return null;
    }

    /**
     * getMatchingRowNum
     * Looks for match on two columns passed in and returns element in the first column
     */
    public Element getMatchingRowNum(String firstColName, String firstValToMatch,
                                     String secondColName, String secondValToMatch) {
        List<Element> rows = getAllRows();
        Element eleToClick;

        int firstColNum = getMatchingColumnNum(firstColName);
        int secondColNum = getMatchingColumnNum(secondColName);

        if (firstColNum != -1 && secondColNum != -1) {

            for (int i = 1; i <= rows.size() - 1; i++) {
                eleToClick = checkForMatchInRow(i, firstColNum, firstValToMatch);

                if (eleToClick != null && checkForMatchInRow(i, secondColNum, secondValToMatch) != null) {
                    return eleToClick;
                }
            }
        }

        return null;
    }

    /**
     * getMatchingRowNum
     * Looks for match on one columns passed in and returns element in that column
     */
    public Element getMatchingRowNum(String firstColName, String firstValToMatch) {
        List<Element> rows = getAllRows();
        Element eleToClick;

        int firstColNum = getMatchingColumnNum(firstColName);

        if (firstColNum != -1) {
            for (int i = 1; i < rows.size(); i++) {
                eleToClick = checkForMatchInRow(i, firstColNum, firstValToMatch);

                if (eleToClick != null) {
                    return eleToClick;
                }
            }
        }

        return null;
    }

    public String getRadioLabelText() {
        List<String> xPathForLabels = Arrays.asList(Property.getVariable(Constants.XPATH_FOR_LABELS).split(Constants.XPATH_FOR_LABELS_DELIMITER));
        final String attribToSearch = "textContent";

        if (!this.getText().isEmpty()) {
            return this.getText();
        } else if (!xPathForLabels.isEmpty()) {
            for (String xPath : xPathForLabels) {
                List<Element> rdoLabel = this.findElements(By.xpath(xPath));

                if (!rdoLabel.isEmpty() &&
                        rdoLabel.get(0).getAttribute(attribToSearch) != null &&
                        !rdoLabel.get(0).getAttribute(attribToSearch).isEmpty()) {
                    return rdoLabel.get(0).getAttribute(attribToSearch);
                }
            }
        }

        return "";
    }

    public boolean findMatchingRadioButtonExact(String rdoBtnVal) {
        return this.getRadioLabelText().equalsIgnoreCase(rdoBtnVal);
    }

    public boolean findMatchingRadioButtonContains(String rdoBtnVal) {
        return this.getRadioLabelText().contains(rdoBtnVal);
    }

    public Boolean findMatchingCheckboxValToClick(String valToClick) {
        switch (valToClick.toLowerCase()) {
            case "true":
            case "on":
                return !this.element().isSelected();
            case "false":
            case "off":
                return this.element().isSelected();
            default:
                if (this.getValue() != null) {
                    return this.getValue().equals(valToClick) && !this.element().isSelected();
                }
        }
        return false;
    }

    /**
     * @param valToClick- anchor row Value to be clicked or stored
     * @param colHeader-  Column Header
     * @return
     */
    public Element findMatchingAnchorLinkCellinTable(String valToClick, String colHeader) {
        List<Element> rows = getAllRows();
        Element eleToClick;
        int colPosition = getMatchingColumnNum(colHeader);
        if (colPosition != -1) {
            for (int i = 1; i <= rows.size() - 1; i++) {
                eleToClick = checkForMatchInRow(i, colPosition, valToClick);
                if (eleToClick != null && eleToClick.element().getTagName().equalsIgnoreCase("a")) {
                    return eleToClick;
                } else if (eleToClick != null) {
                    return eleToClick.findElement(By.tagName("a"));
                }
            }
        }
        return null;
    }

    /**
     * @param rowVal-    row Value to be stored/selected
     * @param colHeader- Column Header
     * @return
     */
    public Element findMatchingCellinTable(String rowVal, String colHeader) {
        List<Element> rows = getAllRows();
        Element eleToClick;
        int colPosition = getMatchingColumnNum(colHeader);
        if (colPosition != -1) {
            for (int i = 1; i <= rows.size() - 1; i++) {
                eleToClick = checkForMatchInRow(i, colPosition, rowVal);
                if (eleToClick != null && eleToClick.element() != null) {
                    return eleToClick;
                }
            }
        }
        return null;
    }

    /**
     * @param rowVal
     * @param colHeaderForRowVal
     * @param colHeader
     * @return
     */
    public Element findMatchingCellValueinTableForGivenColumnHeaderAndValue(String rowVal, String colHeaderForRowVal, String colHeader) {
        List<Element> rows = getAllRows();
        Element elementGiven;
        int colPositionGiven = getMatchingColumnNum(colHeaderForRowVal);
        int colPositionResult = getMatchingColumnNum(colHeader);
        if (colPositionGiven != -1) {
            for (int i = 1; i <= rows.size() - 1; i++) {
                elementGiven = checkForMatchInRow(i, colPositionGiven, rowVal);
                if (elementGiven != null && elementGiven.element() != null && colPositionResult != -1) {
                    return getDataCellElement(i, colPositionResult);
                }
            }
        }
        return null;
    }

    /**
     * @return matching radio button or null if radio button is not found
     */
    public Element findRadioButtonInTableRow() {
        List<String> xPathPatterns = Arrays.asList(".//input",
                "preceding-sibling::td//input",
                "following-sibling::td//input",
                "parent::div//parent::td//preceding-sibling::td//input",
                "parent::td//preceding-sibling::td//input[2]");

        List<Element> rdoButtons;

        for (String pattern : xPathPatterns) {
            rdoButtons = this.findElements(By.xpath(pattern));
            if (!rdoButtons.isEmpty()) {
                return rdoButtons.get(0);
            }
        }

        //no match found
        return null;
    }

    /**
     * @return matching button or null if button is not found
     */
    public Element findButtonInTableRow(String buttonText) {
        List<String> xPathPatterns = Arrays.asList(".//button",
                "preceding-sibling::td//button",
                "following-sibling::td//button");

        List<Element> buttons;

        for (String pattern : xPathPatterns) {
            buttons = this.findElements(By.xpath(pattern));
            if (!buttons.isEmpty()) {
                for (Element button : buttons) {
                    if (button.getText().equalsIgnoreCase(buttonText)) {
                        return button;
                    }
                }
            }
        }

        //no match found
        return null;
    }

    /**
     * @return matching all values or null if column not found
     */
    public List<Element> findTableColumnPattern() {
        List<String> xPathPatterns = Arrays.asList("//span//following-sibling::div//span");

        List<Element> colPattern;

        for (String pattern : xPathPatterns) {
            colPattern = this.findElements(By.xpath(pattern));
            if (!colPattern.isEmpty()) {
                return colPattern;
            }
        }

        // no match found
        return Collections.emptyList();
    }

    /**
     * Implementation of drag and drop functionality. Currently uses a JavaScript-based workaround
     * given inconsistency of driver implementations for the Actions based approach
     *
     * @return element
     */
    public Element dragAndDrop(Element destinationObject, int... retries) {
        try {
            File jsFile = new File(Element.class.getResource("/jsFiles/dragAndDrop.js").getFile());
            String dragAndDropJS = FileHelper.getFileAsString(jsFile.getAbsolutePath(), " ");
            String jsArgument = String.format("%s.simulateDragDrop({ dropTarget: %s});",
                    getjQueryStringfromLocator(this.by.toString()),
                    getjQueryStringfromLocator(destinationObject.by.toString()));
            ((JavascriptExecutor) driver).executeScript(String.format("%s %s", dragAndDropJS, jsArgument));
        } catch (IOException e) {
            log.error("Support JavaScript function not found. Skipping drag and drop");
        } catch (Exception e) {
            if (!(retries.length > 0 && retries[0] == 0)) {
                this.refind(retries);
                this.focus(0);
            } else {
                throw e;
            }
        }
        return this;
    }

    private String getjQueryStringfromLocator(String byString) {
        Pattern regEx = Pattern.compile("(?i)By.(\\w+):\\s*(\\S+)");
        Matcher locatorMatcher = regEx.matcher(byString);

        if (locatorMatcher.find()) {
            switch (locatorMatcher.group(1).toLowerCase()) {
                case "xpath":
                    String xPathLocatorText = locatorMatcher.group(2).replace('\'', '\"');
                    return String.format("$(document).xpathEvaluate('%s')", xPathLocatorText);
                case "id":
                    return String.format("$(\'#%s\')", locatorMatcher.group(2));
                case "cssselector":
                    return String.format("$('%s')", locatorMatcher.group(2));
                default:
                    log.warn("Unrecognized locator type: '{}'", locatorMatcher.group(1));
                    return null;
            }
        } else {
            log.warn("Locator not found in string: {}", byString);
            return null;
        }
    }

    public String getCurrentElementTextOnly() {
        String parentText = this.getText().trim();
        final String[] metaCharacters = {"\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "|", "<", ">", "-", "&", "%"};
        List<Element> childNodes = this.findElements(By.xpath(".//*"));
        for (Element childNode : childNodes) {
            String childText = childNode.getText();
            for (int i = 0; i < metaCharacters.length; i++) {
                if (childText.contains(metaCharacters[i])) {
                    childText = childText.replace(metaCharacters[i], "\\" + metaCharacters[i]);
                }
            }
            parentText = parentText.replaceFirst(childText, "").trim();
        }

        return parentText;
    }

    public String getChildAttributeOnly(String attributeName) {
        String parentText = this.getText().trim();

        List<Element> childNodes = this.findElements(By.xpath(".//*"));
        for (Element childNode : childNodes) {
            if (childNode.getAttribute(attributeName) != null && !childNode.getAttribute(attributeName).equalsIgnoreCase(""))
                parentText = childNode.getAttribute(attributeName);
        }
        return parentText;
    }

    public Element findDropDownInTableRow() {
        List<String> xPathPatterns = Arrays.asList(".//select",
                "preceding-sibling::td//select",
                "parent::div//parent::td//preceding-sibling::td//select");

        List<Element> dropDowns;

        for (String pattern : xPathPatterns) {
            dropDowns = this.findElements(By.xpath(pattern));
            if (!dropDowns.isEmpty()) {
                return dropDowns.get(0);
            }
        }
        return null;
    }

}
