package reporting;

import common.Property;
import common.TestContext;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.*;
import io.qameta.allure.util.ResultsUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class houses few utilities required for the report
 */
public class Reporter {
    private static final Logger LOG = LogManager.getLogger(Reporter.class);

    private Reporter() {
        //Utility class
    }

    /**
     * Adds an info message to the current step
     *
     * @param message The message to be logged to the current step
     */
    public static void addStepLog(String message) {
        Allure.step(message, Status.SKIPPED);
    }

    /**
     * Adds the screenshot from the given path to the current step
     *
     * @param imagePath The image path
     */
    public static void addScreenCaptureFromPath(String imagePath) {
        Path content = Paths.get(imagePath);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screenshot", is);
        } catch (IOException ex) {
            LOG.error("Screenshot failed: {}", ex.getMessage());
        }
    }

    /**
     * Adds the text log content from the given string to the current step
     *
     * @param logTitle The name of the log file
     * @param content The content of the text log
     */
    public static void addTextLogContent(String logTitle, String content) {
        try (InputStream is = IOUtils.toInputStream(content, StandardCharsets.UTF_8)) {
            Allure.addAttachment(logTitle, is);
        } catch (IOException ex) {
            LOG.error("{} failed: {}", logTitle, ex.getMessage());
        }
    }

    public static String addDataTable(String tableName, final Map<String, Object> dataTable) {
        final StringBuilder datTableTabSeparated = new StringBuilder();

        if (dataTable != null && !dataTable.isEmpty()) {
            dataTable.entrySet()
                     .stream()
                     .sorted(Map.Entry.comparingByKey())
                     .forEach(entry -> {
                datTableTabSeparated.append(String.join("\t", entry.getKey(), entry.getValue().toString()));
                datTableTabSeparated.append('\n');
            });

            final String attachmentSource = Allure.getLifecycle()
                                                  .prepareAttachment(tableName, "text/tab-separated-values", "csv");
            Allure.getLifecycle()
                  .writeAttachment(attachmentSource,
                                   new ByteArrayInputStream(datTableTabSeparated.toString().getBytes(StandardCharsets.UTF_8)));

            return attachmentSource;
        }

        return null;
    }

    /**
     * Adds the screenshot from the given path with the given title to the current step
     *
     * @param imagePath The image path
     * @param title     The title for the image
     * @throws IOException Exception if imagePath is erroneous
     */
    public static void addScreenCaptureFromPath(String imagePath, String title) throws IOException {
        Path content = Paths.get(imagePath);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(title, is);
        } catch (IOException ex) {
            LOG.error("Screenshot failed: {}", ex.getMessage());
        }
    }

    public static void setStepStatus(String step, Status status) {                                //[scol] amended
        Allure.step(step, status);
    }

    //[scol] added
    public static String getReportPath() {
        String defaultReportPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "allure-results" + File.separator;
        String reportPath = Property.getVariable("reportPath");
        return (reportPath == null ? defaultReportPath : reportPath);
    }

    //[scol] added
    public static String getReportName() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "RunReport_" + formatter.format(new Date()) + ".html";
    }

    //[scol] added
    public static String getScreenshotPath() {
        return getReportPath() + "screenshots" + File.separator;
    }

    public static void addStepLog(String type, String message) {
        switch (type) {
            case "DEBUG":
                Allure.step(message);
                break;
            case "ERROR":
                Allure.step(message, Status.BROKEN);
                break;
            case "FAIL":
                Allure.step(message, Status.FAILED);
                break;
            case "PASS":
                Allure.step(message, Status.PASSED);
                break;
            case "SKIP":
                Allure.step(message, Status.SKIPPED);
                break;
            default:
                Allure.step(message);
        }
    }

    public static void startFinalStep(Boolean scenarioIsFailed) {
        String testID = getTestIDFromAfterHook();
        String stepUUID = testID + "finalStep";
        Status stepStatus = Status.SKIPPED;
        String stepMessage;

        if (scenarioIsFailed) {
            stepStatus = Status.FAILED;
            stepMessage = "Scenario Failed";
        } else {
            stepStatus = Status.PASSED;
            stepMessage = "Scenario Successful";
        }

        Allure.getLifecycle().startStep(testID, stepUUID, new StepResult().setName(stepMessage).setStatus(stepStatus));
    }

    public static void stopFinalStep() {
        String testID = getTestIDFromAfterHook();
        String stepUUID = testID + "finalStep";
        Allure.getLifecycle().stopStep(stepUUID);
        addTestIDToHistoryObject(testID);
    }

    private static void addTestIDToHistoryObject(String testID) {
        Map<String, String> updateHistory = TestContext.getInstance().testdataToClass("fw.updateHistory", Map.class);

        if(updateHistory == null) {
            updateHistory = new HashMap<>();
        }

        updateHistory.put("allureTestID", testID);
        TestContext.getInstance().testdataPut("fw.updateHistory", updateHistory);
    }

    private static String getTestIDFromAfterHook() {
        Optional<String> testID = Allure.getLifecycle().getCurrentTestCase();
        if (testID.isPresent()) {
            Pattern regEx = Pattern.compile(String.format("^%s(.*)after", TestContext.getInstance().testdataGet("fw.featureName")));
            Matcher testIDMatcher = regEx.matcher(testID.get());

            if (testIDMatcher.find()) {
                return testIDMatcher.group(1);
            }
        }

        return "-1";
    }

    public static void failScenario(Throwable errorMsg) {
        AllureLifecycle lifecycle = Allure.getLifecycle();

        final StatusDetails statusDetails = ResultsUtils.getStatusDetails(errorMsg).orElse(new StatusDetails());


        lifecycle.getCurrentTestCase()
                 .ifPresent(tcUUID -> lifecycle.updateTestCase(tcUUID,
                                                               testResult -> testResult.setStatus(Status.FAILED)
                                                                                       .setStatusDetails(statusDetails)));

    }

    public static void addLink(String name, String url) {
        String testID = getTestIDFromAfterHook();
        Link link = (new Link()).setName(name).setUrl(url);

        Allure.getLifecycle().updateTestCase(testID, testResult ->  testResult.getLinks().add(link));
    }
}