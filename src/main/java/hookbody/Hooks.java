//package hookbody;
//
//import common.Property;
//import common.TestContext;
//import core.Constants;
//import driver.DriverContext;
//import io.cucumber.core.api.Scenario;
//import io.cucumber.java.Before;
//import objectmatcher.FetchPageObjects;
//import org.apache.commons.configuration.PropertiesConfiguration;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Hooks {
//    private static final String SCREENSHOT_ON_FAILURE = "screenshotOnFailure";
//    @Before(order=0)
//    public void getScenrioName(Scenario scenario){
//        String featureName = (scenario.getId().split(";")[0].replace("-", " "));
//        String scenarioName = scenario.getName();
//        TestContext.getInstance().testdataPut("fw.testDescription", featureName + "-" + scenarioName);
//        TestContext.getInstance().testdataPut("fw.cucumberTest","true");
//    }
//    @Before(order=1)
//    public void setRuntimeValues(){
//        setRuntimeProperties();
//    }
//    @Before(order=2)
//    public void populateListOfPo(){
//        TestContext.getInstance().setOfPO().addAll(FetchPageObjects.populateListOfPO());
//    }
//    private void setRuntimeProperties() {
//        PropertiesConfiguration props = Property.getProperties(Constants.SELENIUMRUNTIMEPATH);
//
//        if (props != null) {
//            String tableCompareAttribute = props.getString("attributeForTableCellCompare");
//            String tableHeaderCompareAttribute = props.getString("attributeForTableHeaderCompare");
//            List<String> xPathForLabels = Arrays.asList(props.getStringArray("xPathForLabels"));
//            String screenshotOnValidations = props.getString("screenshotOnValidation");
//            String screenshotOnFailure = props.getString(SCREENSHOT_ON_FAILURE);
//            String screenshotOnEveryStep = props.getString("screenshotOnEveryStep");
//            String browserOpenOnFailure = props.getString("browserOpenOnFailure");
//            String seleniumGridURL = props.getString("seleniumGridURL");
//            String trimTextFlag = props.getString("trimTextOnRetrieval");
//
//            String windowSwitchDelay = props.getString("windowSwitchDelay") == null ? "1000" : props.getString("windowSwitchDelay");
//            System.setProperty("fw.windowSwitchDelay", windowSwitchDelay);
//            String keyToPressDelay = props.getString("keyToPressDelay") == null ? "1000" : props.getString("keyToPressDelay");
//            System.setProperty("fw.keyToPressDelay", keyToPressDelay);
//
//            System.setProperty("fw.ignoreHostNameMismatch", String.valueOf(Boolean.parseBoolean(props.getString("ignoreHostNameMismatch"))));
//
//            if (tableCompareAttribute != null) {
//                System.setProperty(Constants.ATTRIBUTE_FOR_TABLE_CELL_COMPARE, tableCompareAttribute);
//            }
//
//            if (tableHeaderCompareAttribute != null) {
//                System.setProperty(Constants.ATTRIBUTE_FOR_TABLE_HEADER_COMPARE, tableHeaderCompareAttribute);
//            }
//
//            if (!xPathForLabels.isEmpty()) {
//                System.setProperty(Constants.XPATH_FOR_LABELS, String.join(Constants.XPATH_FOR_LABELS_DELIMITER, xPathForLabels));
//            }
//
//            if (screenshotOnValidations != null) {
//                System.setProperty("fw.screenshotOnValidation", screenshotOnValidations);
//            }
//
//            if (screenshotOnFailure != null) {
//                System.setProperty("fw.screenshotOnFailure", screenshotOnFailure);
//            }
//
//            if (screenshotOnEveryStep != null) {
//                System.setProperty("fw.screenshotOnEveryStep", screenshotOnEveryStep);
//            }
//
//            if (browserOpenOnFailure != null) {
//                DriverContext.getInstance().setKeepBrowserOpen(Boolean.valueOf(browserOpenOnFailure));
//            }
//
//            if (seleniumGridURL != null) {
//                System.setProperty("fw.seleniumGrid", seleniumGridURL);
//            }
//
//            if (trimTextFlag != null) {
//                System.setProperty("fw.trimTextOnRetrieval", trimTextFlag);
//            }
//        }
//    }
//}
