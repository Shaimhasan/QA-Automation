package core;


import common.Property;
import common.TestContext;
import driver.DriverFactory;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static core.Constants.ENVIRONMENTPATH;


public class BaseWebSteps extends BaseStepsEngine {
    private static final String WINDOW_SWITCH_DELAY = "fw.windowSwitchDelay";
    Map<Boolean, Boolean> cache = new ConcurrentHashMap<>();
    private BasePO po;

    public BaseWebSteps() {
        //base web steps
    }

    public void setRuntimeProperties() {
        PropertiesConfiguration props = Property.getProperties(Constants.SELENIUMRUNTIMEPATH);

        if (props != null) {
            String screenshotOnEveryStep = props.getString("screenshotOnEveryStep");
            if (screenshotOnEveryStep != null) {
                screenshotOnEveryStep = "false";
                System.setProperty("fw.screenshotOnEveryStep", screenshotOnEveryStep);
            }
        }
    }

    public void waitOnce() {
        cache.computeIfAbsent(true, x -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });
    }


    public BasePO getPO() {
        log.debug("obtaining an instance of the base page objects");
        if (po == null)
            po = new BasePO();
        return po;
    }

    public WebDriver getDriver() {
        log.debug("obtaining the driver for current thread");
        return DriverFactory.getInstance().getDriver();
    }

    public WebDriverWait getWait() {
        log.debug("obtaining the wait for current thread");
        return DriverFactory.getInstance().getWait();
    }

    /**
     * Returns duration for specified waits
     */
    @SuppressWarnings("unused")
    private int getWaitDuration() {
        final int defaultWait = 10;
        int duration;
        try {
            duration = Integer.parseInt(Property.getProperty(Constants.SELENIUMRUNTIMEPATH, "defaultWait"));
            log.debug("selenium driver wait time set from environment properties");
        } catch (Exception e) {
            duration = defaultWait;
            log.debug("selenium driver wait time not available from environment properties...default applied={}", defaultWait);
        }
        return duration;
    }

    /**
     * Generic Method to launch Web App
     *
     * @param appName
     * @param location
     */
    protected void launchApplication(String appName, String location) {
        final String propsFilePath = ENVIRONMENTPATH + Property.getVariable(ENV_PROP) + PROPERTIES_EXT;
        String url = Property.getProperty(propsFilePath, appName);

        if (url != null) {
            url = replaceParameterVals(url);
            TestContext.getInstance().setActiveWindowType(SELENIUM);
            logStepMessage(String.format("URL: %s | Environment: %s", url, Property.getVariable(ENV_PROP)));
        } else {
            String errMsg = "App URL for '" + appName + "' not found in properties file: " + propsFilePath;
            log.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

    }

    protected void launchApplicationURL(String appName, String location) {
        final String propsFilePath = ENVIRONMENTPATH + Property.getVariable(ENV_PROP) + PROPERTIES_EXT;
        String url = Property.getProperty(propsFilePath, appName);

        if (url != null) {
            url = replaceParameterVals(url);
            TestContext.getInstance().setActiveWindowType(SELENIUM);
            final JavascriptExecutor js = (JavascriptExecutor) getDriver();
            logStepMessage(String.format("URL: %s | Environment: %s", url, Property.getVariable(ENV_PROP)));
            if (location.equalsIgnoreCase("NewWindow")) {
                getDriver().get(url);
            }
            if (location.equalsIgnoreCase("NewTab")) {
                js.executeScript("window.open()");
                ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
                getDriver().switchTo().window(tabs.get(1));
                getDriver().get(url);
            }
        } else {
            String errMsg = "App URL for '" + appName + "' not found in properties file: " + propsFilePath;
            log.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

    }

    /**
     * SendKeys List of keys
     * Where KeyLabel can be any key on the Keyboard for E.g. Key_Home, Key_PgUp, Key_RCtrl+Key_B, Key_LCtrl+Key_LAlt+Key_PrntScr
     */

    protected void sendKeyList(List<Keys> keysToPress) {
        switch (keysToPress.size()) {
            case 1:
                new Actions(getDriver()).sendKeys(Keys.chord(keysToPress.get(0)));
                break;
            case 2:
                new Actions(getDriver()).sendKeys(Keys.chord(keysToPress.get(0), keysToPress.get(1)));
                break;
            case 3:
                new Actions(getDriver()).sendKeys(Keys.chord(keysToPress.get(0), keysToPress.get(1), keysToPress.get(2)));
                break;
            default:
                log.error("No keys to press found");
                break;
        }
    }

    /**
     * @param screenshotType
     */
    protected void saveScreenshotType(String screenshotType) {
        File screenShotFile = null;

        if (screenshotType.equalsIgnoreCase(ScreenShotType.AREA.toString()))
            screenShotFile = Screenshot.grabDisplayedAreaScreenShot(getDriver());
        else if (screenshotType.equalsIgnoreCase(ScreenShotType.SCROLLING.toString())) {
            screenShotFile = Screenshot.grabScrollingScreenshot(getDriver());
        } else {
            screenShotFile = Screenshot.grabDisplayedAreaScreenShot(getDriver());
        }

        // Save the Screenshot
        Screenshot.saveScreenshot(screenShotFile, Constants.SCREENSHOTPATH);
    }

    protected String subStringText(String text, String startIndex, String lastIndex) {
        int startValue = Integer.parseInt(startIndex);
        int lastValue = Integer.parseInt(lastIndex);
        String Str = text;
        System.out.println("String value: " + text);
        System.out.println("SubString value: " + Str.substring(startValue, lastValue));
        return Str.substring(startValue, lastValue);
    }

    protected String getTextFromElement(Element object) {
        String valToReturn = "";

        if (object.element().getTagName().equalsIgnoreCase("select")) {
            valToReturn = object.dropdown().getFirstSelectedOption().getText();
        } else if (object.element().getTagName().equalsIgnoreCase("input") &&
                object.getAttribute("type").equalsIgnoreCase("radio")) {
            valToReturn = object.getRadioLabelText();
        } else {
            valToReturn = findElementText(object);
        }

        if (Boolean.parseBoolean(System.getProperty("fw.trimTextOnRetrieval"))) {
            return valToReturn.trim();
        } else {
            return valToReturn;
        }
    }

    private String findElementText(Element object) {
        String valToReturn = object.getText();

        if (valToReturn == null || valToReturn.isEmpty()) {
            valToReturn = object.getValue();

            if (valToReturn == null || valToReturn.isEmpty()) {
                valToReturn = object.getAttribute("textContent");

                if (valToReturn == null || valToReturn.isEmpty()) {
                    valToReturn = object.getAttribute("placeholder");

                    if (valToReturn == null || valToReturn.isEmpty()) {
                        log.warn("Unable to retrieve a value from the '{}' element. Returning an empty string.", object.by());
                        return "";
                    }
                }
            }
        }
        return valToReturn;
    }

    protected Element findMatchingTableCell(String firstColName,
                                            String firstColValue,
                                            Element table) {

        return findMatchingTableCell(firstColName,
                firstColValue,
                null,
                null,
                null,
                null,
                table);
    }

    protected Element findMatchingTableCell(String firstColName,
                                            String firstColValue,
                                            String secondColName,
                                            String secondColValue,
                                            Element table) {

        return findMatchingTableCell(firstColName,
                firstColValue,
                secondColName,
                secondColValue,
                null,
                null,
                table);
    }

    protected Element findMatchingTableCell(String firstColName,
                                            String firstColValue,
                                            String secondColName,
                                            String secondColValue,
                                            String thirdColName,
                                            String thirdColValue,
                                            Element table) {

        Element tableCellToClick = null;

        if (secondColName == null && thirdColName == null) {
            Reporter.addStepLog(String.format("Looking in the \"%s\" column for matching value: \"%s\"", firstColName, firstColValue));
            tableCellToClick = table.getMatchingRowNum(firstColName, firstColValue);
        } else if (thirdColName == null) {
            Reporter.addStepLog(String.format("Looking in the \"%s\" column for matching value: \"%s\", and in the \"%s\" column for match value \"%s\"",
                    firstColName,
                    firstColValue,
                    secondColName,
                    secondColValue));

            tableCellToClick = table.getMatchingRowNum(firstColName,
                    firstColValue,
                    secondColName,
                    secondColValue);

        } else {
            Reporter.addStepLog(String.format("Looking in the \"%s\" column for matching value: \"%s\", and in the \"%s\" column for match value \"%s\", " +
                            "and in the \"%s\" column for matching value \"%s\"",
                    firstColName,
                    firstColValue,
                    secondColName,
                    secondColValue,
                    thirdColName,
                    thirdColValue));

            tableCellToClick = table.getMatchingRowNum(firstColName, firstColValue,
                    secondColName, secondColValue,
                    thirdColName, thirdColValue);

        }

        return tableCellToClick;
    }

    protected void enterValueInTextBox(String value, Element textBox) {
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(value);
            } else {
                textBox.sendKeysChord(value);
            }

            //Re-enter if textbox value did not enter properly
            if (!textBox.getValue().equalsIgnoreCase(value)) {
                log.warn("Value did not enter properly. Clearing and re-entering value");
                textBox.sendKeysChord(value);
            }
        } else {
            textBox.sendKeysChord(value);
        }
    }

    protected void enterValueDynamicEmailIdInTextBox(String val1, String val2, Element textBox) {
        int randomNumber = generateDynamicNumber();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(val1 + randomNumber + val2);
            } else {
                textBox.sendKeysChord(val1 + randomNumber + val2);
            }
        } else {
            textBox.sendKeysChord(val1 + randomNumber + val2);
        }
    }

    protected void enterValueDynamicUserNameInTextBox(String val1, Element textBox) {
        int randomNumber = generateDynamicNumber();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(val1 + randomNumber);
            } else {
                textBox.sendKeysChord(val1 + randomNumber);
            }
        } else {
            textBox.sendKeysChord(val1 + randomNumber);
        }
    }

    protected void enterValueDynamicAlphabeticUserNameInTextBox(String val1, Element textBox) {
        String randomAlphabeticString = generateRandomAlphabeticString();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(val1 + randomAlphabeticString);
            } else {
                textBox.sendKeysChord(val1 + randomAlphabeticString);
            }
        } else {
            textBox.sendKeysChord(val1 + randomAlphabeticString);
        }
    }

    protected void enterDateInTextBox(String val1, Element textBox) {
        String pastDates = getPastDate(val1);
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(pastDates);
            } else {
                textBox.sendKeysChord(pastDates);
            }
        } else {
            textBox.sendKeysChord(pastDates);
        }
    }

    protected void performTabOperation(Element textBox) {
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(Keys.TAB));
            } else {
                textBox.sendKeysChord(String.valueOf(Keys.TAB));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(Keys.TAB));
        }
    }

    protected void enterValueRandomNumberInTextBox(Element textBox) {
        int randomNumber = generateDynamicNumber();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(randomNumber));
            } else {
                textBox.sendKeysChord(String.valueOf(randomNumber));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(randomNumber));
        }
    }

    protected void enterAnyDigitRandomNumber(Element textBox, String number) {
        long randomNumber = generateRandomANyNumber(number);
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(randomNumber));
            } else {
                textBox.sendKeysChord(String.valueOf(randomNumber));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(randomNumber));
        }
    }

    protected void enterAnyDigitRandomNum(Element textBox) {
        long randomNumber = generateRandomAnyNum();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(randomNumber));
            } else {
                textBox.sendKeysChord(String.valueOf(randomNumber));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(randomNumber));
        }
    }

    protected int generateRandomNumberAndEnter(Element textBox) {
        int randomNumber = generateDynamicNumber();
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(randomNumber));
            } else {
                textBox.sendKeysChord(String.valueOf(randomNumber));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(randomNumber));
        }
        return randomNumber;
    }

    protected int generateRandomNumberBasicOnDigitAndEnter(Element textBox, String digit) {
        int randomNumber = generateDynamicNumberBasedOnDigit(digit);
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(String.valueOf(randomNumber));
            } else {
                textBox.sendKeysChord(String.valueOf(randomNumber));
            }
        } else {
            textBox.sendKeysChord(String.valueOf(randomNumber));
        }
        return randomNumber;
    }

    protected int generateDecimalRandomNumberAndEnter(Element textBox) {
        int randomNumber = generateDynamicNumber();
        String decimalNum = randomNumber + ".01";
        if (textBox.getValue() != null) {
            if (textBox.getValue().isEmpty()) {
                textBox.sendKeys(decimalNum);
            } else {
                textBox.sendKeysChord(decimalNum);
            }
        } else {
            textBox.sendKeysChord(decimalNum);
        }
        return randomNumber;
    }

    protected void switchToNextWindow(int retries) throws InterruptedException {
        if (retries > 0) {
            try {
                String currentWindowHandle = getDriver().getWindowHandle();
                long windowSwitchDelay = Integer.parseInt(System.getProperty(WINDOW_SWITCH_DELAY));
                log.debug("Next window not found. Waiting {}ms and trying again. Number of retries left: {}", windowSwitchDelay, retries);
                Thread.sleep(windowSwitchDelay);
                Set<String> windowHandles = getDriver().getWindowHandles();

                windowHandles.forEach(windowHandleName -> {
                    if (!TestContext.getInstance().windowHandleExists(windowHandleName)) {
                        getDriver().switchTo().window(windowHandleName);
                        TestContext.getInstance().pushWindowHandle(getDriver().getWindowHandle());
                    }
                });
                if (currentWindowHandle.equalsIgnoreCase(TestContext.getInstance().peekLastWindowHandle())) {
                    switchToNextWindow(retries - 1);
                }
            } catch (NoSuchWindowException e) {
                long windowSwitchDelay = Integer.parseInt(System.getProperty(WINDOW_SWITCH_DELAY));
                log.debug("Next window not found. Waiting {}ms and trying again. Number of retries left: {}", windowSwitchDelay, retries);
                Thread.sleep(windowSwitchDelay);
                switchToNextWindow(retries - 1);
            }
        }
    }

    protected void switchToNamedWindow(String windowName, String matchPattern, int retries) throws InterruptedException {
        if (retries > 0) {
            long windowSwitchDelay = Integer.parseInt(System.getProperty(WINDOW_SWITCH_DELAY));
            log.debug("Next window not found. Waiting {}ms and trying again. Number of retries left: {}", windowSwitchDelay, retries);
            Thread.sleep(windowSwitchDelay);
            Set<String> windowHandles = getDriver().getWindowHandles();
            String windowHandleToSwitchTo = null;

            for (String windowHandleName : windowHandles) {
                getDriver().switchTo().window(windowHandleName);

                if ((matchPattern.equals("EXACT") && getDriver().getTitle().equalsIgnoreCase(windowName)) ||
                        (matchPattern.equals("PARTIAL") && getDriver().getTitle().contains(windowName))) {
                    windowHandleToSwitchTo = windowHandleName;
                }
            }

            if (windowHandleToSwitchTo == null) {
                sleepAndRetryWindowSwitch(windowName, matchPattern, retries);
            } else {
                getDriver().switchTo().window(windowHandleToSwitchTo);
                if (!TestContext.getInstance().windowHandleExists(windowHandleToSwitchTo)) {
                    TestContext.getInstance().pushWindowHandle(windowHandleToSwitchTo);
                }

            }
        }
    }

    private void sleepAndRetryWindowSwitch(String windowName, String matchPattern, int retries) {
        try {
            long windowSwitchDelay = Integer.parseInt(System.getProperty(WINDOW_SWITCH_DELAY));
            log.debug("Window with name '{}' not found. Waiting {}ms and trying again. Number of retries left: {}", windowName, windowSwitchDelay, retries);
            Thread.sleep(windowSwitchDelay);
            switchToNamedWindow(windowName, matchPattern, retries - 1);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    protected void sendKeysWithRobot(String val) throws AWTException {
        Robot robot = new Robot();
        try {
            long keyToPressDelay = Integer.parseInt(System.getProperty("fw.keyToPressDelay"));
            Thread.sleep(keyToPressDelay);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        if (!val.isEmpty()) {
            switch (val.toUpperCase()) {
                case "TAB":
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    break;

                case "ENTER":
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    break;

                case "RIGHT":
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    break;

                case "SHIFT":
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;

                case "CONTROL":
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    break;

                case "SPACE":
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    break;

                case "DOWN":
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    break;

                case "SPACETAB":
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    break;

                case "ALT_DOWN":
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    break;

                case "SHIFT_TAB":
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;

                case "UP":
                    robot.keyPress(KeyEvent.VK_UP);
                    robot.keyRelease(KeyEvent.VK_UP);
                    break;

                case "CONTROL_A":
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    break;

                case "CONTROL_X":
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_X);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    break;

                case "CONTROL_V":
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    break;

                case "F4":
                    robot.keyPress(KeyEvent.VK_F4);
                    robot.keyRelease(KeyEvent.VK_F4);
                    break;

                default:
                    enterStringWithRobot(val, robot);
            }
        } else {
            log.debug("Blank key '{}' has been passed", val);
        }
    }

    private void enterStringWithRobot(String val, Robot robot) {
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            if (!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_SHIFT);

            } else if (Character.isAlphabetic(c)) {
                if (Character.isUpperCase(c)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(Character.toUpperCase(c));
                    robot.keyRelease(Character.toUpperCase(c));
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(Character.toUpperCase(c));
                    robot.keyRelease(Character.toUpperCase(c));
                }
            } else {
                robot.keyPress(c);
                robot.keyRelease(c);
            }
        }
    }

}