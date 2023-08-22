package utility;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import common.FileHelper;
import common.TestContext;
import core.BaseWebSteps;
import core.Constants;
import core.Element;
import cucumber.api.PendingException;
import driver.DriverContext;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reporting.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static core.ObjectFinder.invokePOMethod;

public class AutoEngUtility extends BaseWebSteps {
    @When("^flag test data as \"([^\"]*)\"$")
    public void flagTestDataAs(String testDataReusableFlag) {
        //TO-DO: implement based on final TDM solution
    }

    @When("^the user takes a screenshot \"([^\"]*)\"$")
    public void theUserTakesAScreenshot(String screenshotType) {
        saveScreenshotType(screenshotType);
    }

    @When("^the user switches to frame \"([^\"]*)\"$")
    public void theUserSwitchesToFrame(String frameName) {
        frameName = parseValue(frameName);

        try {
            getPO().switchFrame(frameName,3);
        } catch (NoSuchFrameException | TimeoutException ex) {
            try {
                getPO().switchFrame(By.xpath("//frame[@title=" + "'" + frameName + "'" + "] | //iFrame[@title=" + "'" + frameName + "'" + "]"));
            } catch (InvalidSelectorException | TimeoutException ex2) {
                getPO().switchFrame(By.xpath(frameName));
            }
        }
    }

    @When("^the user switches to frame with title \"([^\"]*)\"$")
    public void theUserSwitchesToFrameXpath(String frameName) {
        frameName = parseValue(frameName);
        getPO().switchFrame(By.xpath("//frame[@title=" + "'" + frameName + "'" + "] | //iFrame[@title=" + "'" + frameName + "'" + "]"), 3);
    }

    @When("^the user switches to frame with xPath \"([^\"]*)\"$")
    public void theUserSwitchesToFrameTitle(String frameName) {
        frameName = parseValue(frameName);
        getPO().switchFrame(By.xpath(frameName), 3);
    }

    @When("^the user switches to the \"([^\"]*)\" frame element at the \"([^\"]*)\" page$")
    public void theUserSwitchesToFrameElement(String frameName,
                                              String pageName) {
        getPO().switchFrame(getObject(frameName, pageName));
    }

    @When("^the user switches to the default window content$")
    public void theUserSwitchesToTheDefaultWindowContent() {
        getPO().switchToDefaultContent();
    }

    @When("^the user switches to window \"([^\"]*)\"$")
    public void theUserSwitchesToWindow(String windowName) throws InterruptedException {
        switchToNamedWindow(windowName, "EXACT", 5);
    }

    @When("^the user switches to window that contains \"([^\"]*)\"$")
    public void theUserSwitchesToWindowContains(String windowName) throws InterruptedException {
        switchToNamedWindow(windowName, "PARTIAL",5);
    }

    @When("^the user switches to the next window$")
    public void theUserSwitchesToNextWindow() throws InterruptedException {
        switchToNextWindow(5);
    }

    @When("^the user switches to the last open window$")
    public void theUserSwitchesToLastOpenWindow() throws InterruptedException {
        TestContext.getInstance().popWindowHandle();

        try {
            getDriver().switchTo().window(TestContext.getInstance().peekLastWindowHandle());
        } catch(NoSuchWindowException e) {
            long windowSwitchDelay = Integer.parseInt(System.getProperty("fw.windowSwitchDelay"));
            log.debug("Last window not found. Waiting {}ms and trying again.", windowSwitchDelay);
            Thread.sleep(windowSwitchDelay);
            getDriver().switchTo().window(TestContext.getInstance().peekLastWindowHandle());
        }
    }

    @When("^the user closes the current window in focus$")
    public void theUserClosesTheCurrentWindowInFocus() {
        getDriver().switchTo().window(getDriver().getWindowHandle()).close();
        TestContext.getInstance().popWindowHandle();
    }

    @When("^add log \"([^\"]*)\" \"([^\"]*)\"$")
    public void addLog(String logType, String logMessage) {
        switch (logType.toUpperCase()) {
            case "INFO":
                log.info(logMessage);
                break;
            case "WARNING":
                log.warn(logMessage);
                break;
            case "ERROR":
                log.error(logMessage);
                break;
            default:
                log.warn("Expected log type not found: {}. Options are INFO | WARNING | ERROR", logType);
        }

    }

    @When("^the user closes the system dialog pop-up$")
    public void theUserClosesTheSystemDialogPopUp() {
        try {
            getWait().until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().alert().dismiss();
        } catch (NoAlertPresentException ex) {
            log.warn(ex.getMessage());
        }
    }

    @When("^the user saves a file as \"([^\"]*)\" in location \"([^\"]*)\" with type \"([^\"]*)\" on a file download dialog$")
    public void theUserSavesAFileAsInLocationWithTypeOnAFileDownloadDialog(String fileName,
                                                                           String filePath,
                                                                           String fileType) {
        new File(filePath + fileName + fileType);
    }

    @When("^the user chooses \"([^\"]*)\" in the system dialog pop-up$")
    public void theUserChoosesInTheSystemDialogPopUp(String dialogOption) {
        try {
            getWait().until(ExpectedConditions.alertIsPresent());
            if (dialogOption.equalsIgnoreCase("OK")) getDriver().switchTo().alert().accept();
            else getDriver().switchTo().alert().dismiss();
        } catch (NoAlertPresentException ex) {
            log.warn(ex.getMessage());
        }
    }

    @When("^format data dictionary value at key \"([^\"]*)\" as \"([^\"]*)\"$")
    public void formatDataDictionaryValueAtKeyAs(String dictionaryKey,
                                                 String formatType) throws ParseException {
        formatDataDictionaryValueAtKeyToNewKey(dictionaryKey, formatType, dictionaryKey);
    }

    @When("^format data dictionary value at key \"([^\"]*)\" as \"([^\"]*)\" and store in new key \"([^\"]*)\"$")
    public void formatDataDictionaryValueAtKeyToNewKey(String oldDictionaryKey,
                                                       String formatType,
                                                       String newDictionaryKey) throws ParseException {
        oldDictionaryKey = parseDictionaryKey(oldDictionaryKey);
        newDictionaryKey = parseDictionaryKey(newDictionaryKey);

        Object originalValue = TestContext.getInstance().testdataGet(oldDictionaryKey);
        if(originalValue != null) {
            String formattedValue = format(originalValue.toString(), formatType);
            TestContext.getInstance().testdata().put(newDictionaryKey, formattedValue);
            logStepMessage(String.format("Formatted %s to new value of %s", originalValue, formattedValue));
        } else {
            log.warn("Did not find value at key: {}. Skipping format.", oldDictionaryKey);
        }


    }

    @When("^split data dictionary value at key \"([^\"]*)\" with \"([^\"]*)\"$")
    public void splitDataDictionaryValueAtKeyWith(String dictionaryKey,
                                                  String delimiter) {
        TestContext.getInstance().testdata().put(parseDictionaryKey(dictionaryKey), split(parseValue(dictionaryKey), delimiter));
    }

    @When("^the user waits for the page to load$")
    public void theUserWaitsForThePageToLoad() {
        getPO().waitPageToLoad();
    }

    @When("^the user waits Jquery for the page to load$")
    public void theUserWaitsForJqueryThePageToLoad() {
        getPO().waitPageToJqueryLoad();
    }

    @When("^the user waits \"([^\"]*)\" seconds$")
    public void theUserWaitsForSomeTime(String timeInSeconds) {
        getPO().staticWait(timeInSeconds);
    }

    @When("^the user waits for the \"([^\"]*)\" element to be \"([^\"]*)\" on the \"([^\"]*)\" page$")
    public void theUserWaitsForTheElementToBeOnThePage(String objectName,
                                                       String expectedCondition,
                                                       String pageName) {
        switch (expectedCondition.toUpperCase()) {
            case "VISIBLE":
                getObject(objectName, pageName).visible();
                break;
            case "HIDDEN":
                getObject(objectName, pageName).invisible();
                break;
            case "DISPLAYED":
                getObject(objectName, pageName).displayed();
                break;
            case "CLICKABLE":
                getObject(objectName, pageName).clickable();
                break;
            case "ENABLED":
                getObject(objectName, pageName).enabled();
                break;
            case "DISABLED":
                getObject(objectName, pageName).disabled();
                break;
            case "NOT EMPTY":
                getObject(objectName, pageName).notEmpty();
                break;
            case "EMPTY":
                getObject(objectName, pageName).empty();
                break;
            default:
                log.warn("Expected condition not found: {}. Options are CLICKABLE | VISIBLE | HIDDEN | DISPLAYED | ENABLED | DISABLED | NOT EMPTY | EMPTY",
                         expectedCondition);
        }
    }

    @When("^the user hovers over the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserHoversOverTheElementAtThePage(String objectName,
                                                     String pageName) {
        getObject(objectName, pageName).hover();
    }

    @When("^the user hovers and click over the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserHoversOverAndClickTheElementAtThePage(String objectName,
                                                     String pageName) {
        getObject(objectName, pageName).hover().click();
    }

    @When("^the user refreshes the page$")
    public void theUserRefreshesThePage() {
        getDriver().navigate().refresh();
    }

    @When("^the user maximizes the current window$")
    public void theUserMaximizesTheWindow() {
        getDriver().manage().window().maximize();
    }

    @When("^the user uploads the file at \"([^\"]*)\" using the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserUploadsAFile(String filePath,
                                    String objectName,
                                    String pageName) {

        File fileToUpload = new File(filePath);

        if (fileToUpload.exists()) {
            getObject(objectName, pageName).sendKeys(fileToUpload.getAbsolutePath());
        } else {
            String errorMsg = String.format("File does not exist at path: %s", fileToUpload);
            log.error(errorMsg);
            Reporter.addStepLog("ERROR", errorMsg);
        }
    }

    @When("^the user drags the \"([^\"]*)\" element and drops it on the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserDragsAndDrops(String objectName1,
                                     String objectName2,
                                     String pageName) {

        getObject(objectName2, pageName).dragAndDrop(getObject(objectName1, pageName));
    }

    @When("^the user downloads a file from the \"([^\"]*)\" link element at the \"([^\"]*)\" page and saves full path to data dictionary key \"([^\"]*)\"$")
    public void theUserDownloadsTheFile(String objectName,
                                        String pageName,
                                        String dictionaryKey) throws URISyntaxException {

        String pathToCurCerts = setTrustStoreBasedOnEnv();

        FileDownloadHelper downloadHelper = new FileDownloadHelper(getDriver());
        Element downloadLink = getObject(objectName, pageName);
        downloadHelper.setURISpecifiedInAnchorElement(downloadLink);
        File downloadedFile = downloadHelper.downloadFile("");

        if(downloadedFile != null && downloadedFile.exists()) {
            TestContext.getInstance().testdataPut(parseDictionaryKey(dictionaryKey), downloadedFile.getAbsolutePath());
            logStepMessage(String.format("Saved file to: %s", downloadedFile.getAbsolutePath()));
        } else {
            logStepMessage("Unable to download file.");
        }

        setTrustStore(pathToCurCerts);

    }

    @When("^the user downloads a \"([^\"]*)\" file at the current page URL and saves full path to data dictionary key \"([^\"]*)\"$")
    public void theUserDownloadsTheFileWithExtensionAtURL(String fileExtension,
                                                          String dictionaryKey) throws URISyntaxException {
        String pathToCurCerts = setTrustStoreBasedOnEnv();

        FileDownloadHelper downloadHelper = new FileDownloadHelper(getDriver());
        downloadHelper.setURIToCurrentBrowserURL();
        File downloadedFile = downloadHelper.downloadFile(fileExtension);

        if(downloadedFile != null && downloadedFile.exists()) {
            TestContext.getInstance().testdataPut(parseDictionaryKey(dictionaryKey), downloadedFile.getAbsolutePath());
            logStepMessage(String.format("Saved file to: %s", downloadedFile.getAbsolutePath()));
        } else {
            logStepMessage("Unable to download file.");
        }

        setTrustStore(pathToCurCerts);
    }

    @When("^the user downloads a file at the current page URL and saves full path to data dictionary key \"([^\"]*)\"$")
    public void theUserDownloadsTheFileAtURL(String dictionaryKey) throws URISyntaxException {
        theUserDownloadsTheFileWithExtensionAtURL("pdf", dictionaryKey);
    }

    @When("^the user finds the latest \"([^\"]*)\" file from the default download directory and stores to data dictionary key \"([^\"]*)\"$")
    public void theUserFindsLastDownloadedFile(String extension,
                                               String dictionaryKey) {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String pathToFile = FileHelper.findMostRecentFileByExtension(Constants.DOWNLOADPATH, extension);

        TestContext.getInstance().testdataPut(dictionaryKey, pathToFile);
        logStepMessage(String.format(STORED_VALUE, dictionaryKey, pathToFile));
    }

    @When("^the user scroll to the page until the object \"([^\"]*)\" is visible at the page \"([^\"]*)\"$")
    public void theUserScrollsToElementInThePage(String objectName,String pageName)  {
        getObject(objectName, pageName).scroll();
    }

    @When("^load data from JSON file \"([^\"]*)\" into the data dictionary$")
    public void loadToDataDictionary(String fileName) throws IOException {
        String pathToDataJson = FileHelper.findFileInPath(common.Constants.TESTDATAPATH,
                fileName + ".json");

        if(pathToDataJson != null) {
            Map<String, String> dataCriteria;
            Object jsonObject = FileHelper.getDataPOJO(pathToDataJson, Object.class);

            if (jsonObject instanceof ArrayList) {
                log.info("Found array in JSON. Reading first array element only");
                dataCriteria = (Map) ((List) jsonObject).get(0);
            } else {
                dataCriteria = (Map) jsonObject;
            }

            dataCriteria.forEach((dataItem, val) -> TestContext.getInstance().testdataPut(dataItem, val));
        } else {
            log.warn("Did not find JSON file to load: {}", fileName);
        }
    }

    @When("^the user extracts value at index \"([^\"]*)\" from the JSON array at dictionary key \"([^\"]*)\" and stores into dictionary key \"([^\"]*)\"$")
    public void theUserExtractsValue(String index, String dictionaryKey, String newDictionaryKey)  {
        try {
            List<String> list = new Gson().fromJson(TestContext.getInstance().testdataGet(parseDictionaryKey(dictionaryKey)).toString(),
                                                    List.class);
            if (!list.isEmpty()) {
                TestContext.getInstance().testdataPut(parseDictionaryKey(newDictionaryKey), list.get(Integer.parseInt(index)));
            }
        } catch (JsonSyntaxException e) {
            logStepMessage(String.format("Did not find a JSON array. Found: %s. Error: %s",
                                         TestContext.getInstance().testdataGet(parseDictionaryKey(dictionaryKey)),
                                         e.getMessage()));
        }
    }


    @When("^the user completes the rest of the test manually$")
    public void theUserFinishesTheTestManually() {
        DriverContext.getInstance().setKeepBrowserOpen(true);
        throw new PendingException("Complete rest of the test manually");
    }

    @When("^the user responds to challenge questions on the \"([^\"]*)\" page$")
    public void userRespondsToChallengequestions(String pageName) throws InstantiationException,
                                                                         IllegalAccessException {
        invokePOMethod(pageName, "userRespondsToChallenge");
    }
    @When("^the user perform mathematical operation \"([^\"]*)\" on the value 1 \"([^\"]*)\" and value 2 \"([^\"]*)\" and store result into dictionary key \"([^\"]*)\"$")
    public void theUserPerformsMathematicalOperation(String operationPerf,
                                                     String value1,
                                                     String value2,
                                                     String dictonaryKey) {
        double calValue = 0.00;
        dictonaryKey = parseDictionaryKey(dictonaryKey);
        value1 = parseValue(value1);
        value2 = parseValue(value2);

        switch (operationPerf.toUpperCase()) {
            case "ADDITION":
                calValue = Double.parseDouble(value1) + Double.parseDouble(value2);
                break;
            case "SUBTRACTION":
                calValue = Double.parseDouble(value1) - Double.parseDouble(value2);
                break;
            case "DIVISION":
                calValue = Double.parseDouble(value1) / Double.parseDouble(value2);
                break;
            case "MULTIPLICATION":
                calValue = Double.parseDouble(value1) * Double.parseDouble(value2);
                break;
            default:
                log.warn("Operation performed condition not found: {}. Options are ADDITION | SUBTRACTION | DIVISION | MULTIPLICATION ",
                        operationPerf);
        }

        logStepMessage(String.format("Result of mathematical operation %s is %s", operationPerf, calValue));
        TestContext.getInstance().testdata().put(dictonaryKey, String.valueOf(calValue));

    }

}