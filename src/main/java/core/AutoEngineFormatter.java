package core;

//import automation.library.alm.core.ALMContext;

import common.PDFHelper;
import common.TestContext;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.event.*;
import cucumber.runtime.formatter.TestSourcesModelProxy;
import driver.DriverFactory;
import org.apache.logging.log4j.ThreadContext;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static core.Screenshot.grabScreenshot;
import static core.Screenshot.saveScreenshot;
import static cucumber.api.Result.Type.FAILED;
import static cucumber.api.Result.Type.PASSED;
import static reporting.Reporter.addScreenCaptureFromPath;
import static reporting.Reporter.getScreenshotPath;

public class AutoEngineFormatter implements ConcurrentEventListener {
    private final EventHandler<TestSourceRead> featureStartedHandler = this::handleFeatureStartedHandler;
    private final EventHandler<TestCaseStarted> caseStartedHandler = this::handleTestCaseStarted;
    private final EventHandler<TestCaseFinished> caseFinishedHandler = this::handleTestCaseFinished;
    private final EventHandler<TestStepStarted> stepStartedHandler = this::handleTestStepStarted;
    private final EventHandler<TestStepFinished> stepFinishedHandler = this::handleTestStepFinished;
    private final TestSourcesModelProxy testSources = new TestSourcesModelProxy();
    private final ThreadLocal<String> currentFeatureFile = new InheritableThreadLocal<>();
    private static final String ERROR_RESPONSE = "-1";
    private static final String SELENIUM = "Selenium";
    private static final String LEANFT = "LeanFT";
    private static final String PDF = "PDF";
    private static final String FW_SCENARIO_NAME = "fw.scenarioName";

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestSourceRead.class, featureStartedHandler);

        eventPublisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);

        eventPublisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
        eventPublisher.registerHandlerFor(TestStepFinished.class, stepFinishedHandler);
    }

    private void handleFeatureStartedHandler(final TestSourceRead event) {
        testSources.addTestSourceReadEvent(event.uri, event);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        currentFeatureFile.set(event.testCase.getUri());
        TestContext.getInstance().testdataPut("fw.featureName",
                                              this.testSources.getFeature((String) this.currentFeatureFile.get()).getName());
        TestContext.getInstance().testdataPut(FW_SCENARIO_NAME, event.testCase.getName());
        System.out.println("Scenarios Name --> "+ event.testCase.getName());
        TestContext.getInstance().testdataPut("fw.logFileName",
                                              String.format("%s-%s",
                                                            TestContext.getInstance().testdataGet("fw.featureName"),
                                                            TestContext.getInstance().testdataGet(FW_SCENARIO_NAME)));

        ThreadContext.put("scenarioName", TestContext.getInstance().testdataGet("fw.logFileName").toString());
    }

    private void handleTestCaseFinished(final TestCaseFinished event) {
        currentFeatureFile.remove();
    }

    private void handleTestStepStarted(TestStepStarted event) {
        //REMOVE?
    }

    private void handleTestStepFinished(TestStepFinished event) {
        String errorMsg = null;

        if (event.result.getStatus() == FAILED) {
            errorMsg = event.result.getErrorMessage();
        }

        if (event.testStep instanceof PickleStepTestStep) {
            final PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;

            String stepText = getStepDescription(testStep);
            String screenshotPath = addScreenshotToStep(stepText, event.result.getStatus());
            String stepStatus = getStepStatus(event.result, stepText);

        }

    }

    private void addRunStep(String stepText, String screenshotPath, String stepStatus, String actualResult, String expectedResult) {
    }

    private void getExpectedResult() {
    }

    private void getActualResult(String errorMsg) {
    }

    private String getStepDescription(PickleStepTestStep testStep) {
        final String stepKeyword = Optional.ofNullable(
                testSources.getKeywordFromSource(currentFeatureFile.get(), testStep.getStepLine()))
                                           .orElse("UNDEFINED");

        final String stepText = replaceDictionaryKeysWithVals(testStep.getPickleStep().getText());
        return String.format("%s %s", stepKeyword, stepText);
    }

    private String replaceDictionaryKeysWithVals(String textToReplace) {
        if (!isStoreSentence(textToReplace)) {
            return new BaseStepsEngine().replaceParameterVals(textToReplace);
        } else {
            return textToReplace;
        }
    }

    private boolean isStoreSentence(String text) {
        if (text.matches("(.*)concatenated (string|value) of \"([^\"]*)\"(.*)")) {
            return false;
        } else {
            return (text.matches("store in new key \"([^\"]*)\"(.*)") ||
                    text.matches("store(.*)into the data dictionary with key \"([^\"]*)\"(.*)") ||
                    text.matches("(.*)data dictionary value at key \"([^\"]*)\"(.*)") ||
                    text.matches("(.*)enters the encrypted value \"([^\"]*)\"(.*)") ||
                    text.matches("(.*)enters the user credential \"([^\"]*)\"(.*)") ||
                    text.matches("(.*)enters the secure credential \"([^\"]*)\"(.*)"));
        }
    }


    private String addScreenshotToStep(String stepText, Result.Type status) {
        String screenshotPath = ERROR_RESPONSE;

        if (stepExecuted(status)) {
            if (!isPopUpStep(stepText) && Boolean.parseBoolean(System.getProperty("fw.screenshotOnEveryStep"))) {
                screenshotPath = takeScreenshot();
            } else if (!isPopUpStep(stepText) && isValidatonStep(stepText) &&
                       Boolean.parseBoolean(System.getProperty("fw.screenshotOnValidation"))) {
                screenshotPath = takeScreenshot();
            } else if ((status == FAILED || isSoftAssertionFailure(stepText)) &&
                       Boolean.parseBoolean(System.getProperty("fw.screenshotOnFailure"))) {
                System.setProperty("screenshotOnFailure", "true");
                screenshotPath = takeScreenshot();
            }
        }
        return screenshotPath;
    }

    private boolean stepExecuted(Result.Type status) {
        return status == FAILED || status == PASSED;
    }

    private boolean isValidatonStep(String stepText) {
        return stepText.contains("validates");
    }

    private boolean isPopUpStep(String stepText) {
        return stepText.contains("pop up");
    }

    private boolean isSoftAssertionFailure(String stepText) {
        return stepText.contains("ContinueOnFailure") && !TestContext.getInstance().sa().wasSuccess();
    }

    private String takeScreenshot() {
        String screenshotPath = ERROR_RESPONSE;
        File file = null;
        String activeWindowType = TestContext.getInstance().getActiveWindowType();

        if (activeWindowType != null && activeWindowType.equalsIgnoreCase(SELENIUM)) {
            file = saveScreenshot(grabScreenshot(DriverFactory.getInstance().getDriver()), getScreenshotPath());
        } else if (activeWindowType != null && activeWindowType.equalsIgnoreCase(LEANFT)) {

        } else if (activeWindowType != null && TestContext.getInstance().getActiveWindowType().equalsIgnoreCase(PDF) &&
                   TestContext.getInstance().testdataGet("fw.activePDF") != null) {
            List<File> pdfDocAsList = PDFHelper.takeScreenshotOfFullDoc(TestContext.getInstance().testdataToClass("fw.activePDF", PDDocument.class),
                                                                        getScreenshotPath());
            for (File screenshot : pdfDocAsList) {
                if (screenshot.exists()) {
                    addScreenCaptureFromPath(screenshot.getAbsolutePath());
                }
            }
        }

        if (file != null && file.exists()) {
            screenshotPath = file.getAbsolutePath();
            addScreenCaptureFromPath(screenshotPath);
            TestContext.getInstance().testdata().put("fw.screenshotAbsolutePath", screenshotPath);
        }

        return screenshotPath;
    }

    private String getStepStatus(final Result testCaseResult, String stepText) {
        if (isValidatonStep(stepText) &&
            stepText.toLowerCase().contains("continueonfailure") &&
            !TestContext.getInstance().sa().wasSuccess()) {
            return "Failed";
        } else if (stepText.contains("completes the rest of the test manually")) {
            return "Not Completed";
        } else {
            return translateTestStatusToALMStatus(testCaseResult);
        }
    }

    private String translateTestStatusToALMStatus(final Result testCaseResult) {
        switch (testCaseResult.getStatus()) {
            case FAILED:
                return "Failed";
            case PASSED:
                return "Passed";
            case SKIPPED:
            case PENDING:
            case AMBIGUOUS:
            case UNDEFINED:
            default:
                return "No Run";
        }
    }

    private void prepDefect(String stepText, String expectedResult, String actualResult) {
        String defectSummary = String.format("%s: %s",
                                             TestContext.getInstance().testdataGet(FW_SCENARIO_NAME),
                                             stepText);
    }

}
