package utility;

import common.TestContext;
import core.BaseWebSteps;
import core.Element;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import reporting.Reporter;
import validator.AssertHelper;
import validator.ComparisonOperator;
import validator.ComparisonType;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoEngValidate extends BaseWebSteps {

    private static final String HARD_STOP_ON_FAILURE = "HardStopOnFailure";
    private static final String VALIDATION_TAG = "VALIDATION.";
    private static final String STATUS_FAIL = "FAIL";
    private static final String VALIDATION_FAILED = "Validation Failed: ";

    @Then("^the user validates the \"([^\"]*)\" element is disabled at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsDisabledAtThePage(String objectName,
                                                              String pageName,
                                                              String validationID,
                                                              String onFailureFlag) {

        final boolean isElementEnabled = getObject(objectName, pageName).element().isEnabled();
        final String compareDescription = String.format("Expected %s to be disabled at the %s page", objectName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDescription + ": " + isElementEnabled);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(isElementEnabled).as(compareDescription).isFalse();
        } else {
            sa().assertThat(isElementEnabled).as(compareDescription).isFalse();
            if (isElementEnabled) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
            }
        }
    }

    @Then("^the user validates the \"([^\"]*)\" element is enabled at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsEnabledAtThePage(String objectName,
                                                             String pageName,
                                                             String validationID,
                                                             String onFailureFlag) {

        final boolean isElementEnabled = getObject(objectName, pageName).element().isEnabled();
        final String compareDescription = String.format("Expecting %s to be enabled at the %s page", objectName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDescription + ": " + isElementEnabled);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(isElementEnabled).as(compareDescription).isTrue();
        } else {
            sa().assertThat(isElementEnabled).as(compareDescription).isTrue();
            if (isElementEnabled) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
            }
        }
    }

    @Then("^the user validates the \"([^\"]*)\" element is not visible at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsNotVisibleAtThePage(String objectName,
                                                                String pageName,
                                                                String validationID,
                                                                String onFailureFlag) {

        final boolean isElementVisible = getObject(objectName, pageName).element().isDisplayed();
        final String compareDescription = String.format("Expecting %s to not be visible at the %s page", objectName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDescription + ": " + isElementVisible);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(isElementVisible).as(compareDescription).isFalse();
        } else {
            sa().assertThat(isElementVisible).as(compareDescription).isFalse();
            if (isElementVisible) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
            }
        }
    }

    @Then("^the user validates the \"([^\"]*)\" element is visible at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsVisibleAtThePage(String objectName,
                                                             String pageName,
                                                             String validationID,
                                                             String onFailureFlag) {

        final boolean isElementVisible = getObject(objectName, pageName).element().isDisplayed();
        final String compareDescription = String.format("Expecting %s to be visible at the %s page", objectName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDescription + ": " + isElementVisible);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(isElementVisible).as(compareDescription).isTrue();
        } else {
            sa().assertThat(isElementVisible).as(compareDescription).isTrue();
            if (!isElementVisible) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
            }
        }
    }

    @Then("^the user validates the \"([^\"]*)\" element is not clickable at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsNotClickableAtThePage(String objectName, String pageName,
                                                                  String validationID, String onFailureFlag) {

        final boolean isElementNotClickable = getObject(objectName, pageName).isNotClickable();
        final String compareDescription = String.format("Expecting %s to be not clickable at the %s page", objectName,
                pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID,
                compareDescription + ": " + isElementNotClickable);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(isElementNotClickable).as(compareDescription).isFalse();
        } else {
            sa().assertThat(isElementNotClickable).as(compareDescription).isFalse();
            if (isElementNotClickable) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
            }
        }
    }

    @Then("^the user validates the data dictionary list \"([^\"]*)\" contains \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheDataDictionaryListContains(String dictionaryKey,
                                                              String expectedValue,
                                                              String validationID,
                                                              String onFailureFlag) {

        dictionaryKey = parseDictionaryKey(dictionaryKey);
        expectedValue = parseValue(expectedValue);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(expectedValue);
        if (TestContext.getInstance().testdata().containsKey(dictionaryKey)) {
            List<String> actualList = TestContext.getInstance().testdataToClass(dictionaryKey, List.class);
            AssertHelper validator = new AssertHelper("Compare_Lists", "Contains", onFailureFlag);
            TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualList.toString(), expectedList.toString()));
            validator.performValidation(actualList, expectedList);
        } else {
            log.warn("Expected dictionary key not found in data dictionary list: {}", dictionaryKey);
        }
    }

    @Then("^the user validates the data dictionary list \"([^\"]*)\" does not contain \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheDataDictionaryListDoesNotContain(String dictionaryKey,
                                                                    String expectedValue,
                                                                    String validationID,
                                                                    String onFailureFlag) {

        dictionaryKey = parseDictionaryKey(dictionaryKey);
        expectedValue = parseValue(expectedValue);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(expectedValue);

        if (TestContext.getInstance().testdata().containsKey(dictionaryKey)) {
            List<String> actualList = TestContext.getInstance().testdataToClass(dictionaryKey, List.class);
            AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_LISTS, ComparisonOperator.NOT_CONTAINS, onFailureFlag);
            TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualList.toString(), expectedList.toString()));
            validator.performValidation(actualList, expectedList);

        } else
            log.warn("Expected dictionary key not found in data dictionary list: {}", dictionaryKey);
    }

    @Then("^the user validates \"([^\"]*)\" that the \"([^\"]*)\" element is \"([^\"]*)\" \"([^\"]*)\" at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheElementIs(String comparisonType,
                                                 String objectName,
                                                 String comparisonOperator,
                                                 String expectedValue,
                                                 String pageName,
                                                 String validationID,
                                                 String onFailureFlag) {

        String actualValue = getTextFromElement(getObject(objectName, pageName));
        expectedValue = parseValue(expectedValue);
        AssertHelper validator = new AssertHelper(comparisonType, comparisonOperator, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);

    }

    @Then("^the user validates \"([^\"]*)\" that the \"([^\"]*)\" element is \"([^\"]*)\" \"([^\"]*)\" at the \"([^\"]*)\" page based on datadictionary \"([^\"]*)\" and xpath1 \"([^\"]*)\" and xpath2 \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheElementBasedOnXpathIs(String comparisonType,
                                                             String objectName,
                                                             String comparisonOperator,
                                                             String expectedValue,
                                                             String pageName,
                                                             String datadictionarykey,
                                                             String xpath1,
                                                             String xpath2,
                                                             String validationID,
                                                             String onFailureFlag) {
        xpath1 = parseValue(xpath1);
        xpath2 = parseValue(xpath2);
        datadictionarykey = parseValue(datadictionarykey);
        Element element = getObject(objectName, pageName);
        Element object = element.getTextBasedOnXpath1AndXpath2AndDictionaryKey(xpath1, datadictionarykey, xpath2);
        String actualValue = object.getText();
        expectedValue = parseValue(expectedValue);
        expectedValue = datadictionarykey + expectedValue;
        System.out.println("Expected Value -->" + expectedValue);
        AssertHelper validator = new AssertHelper(comparisonType, comparisonOperator, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);

    }

    @Then("^the user exact validates \"([^\"]*)\" that the \"([^\"]*)\" element is \"([^\"]*)\" \"([^\"]*)\" at the \"([^\"]*)\" page based on datadictionary \"([^\"]*)\" and xpath1 \"([^\"]*)\" and xpath2 \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserExactValidatesThatTheElementBasedOnXpathIs(String comparisonType,
                                                                  String objectName,
                                                                  String comparisonOperator,
                                                                  String expectedValue,
                                                                  String pageName,
                                                                  String datadictionarykey,
                                                                  String xpath1,
                                                                  String xpath2,
                                                                  String validationID,
                                                                  String onFailureFlag) {
        xpath1 = parseValue(xpath1);
        xpath2 = parseValue(xpath2);
        datadictionarykey = parseValue(datadictionarykey);
        Element element = getObject(objectName, pageName);
        Element object = element.getTextBasedOnXpath1AndXpath2AndDictionaryKey(xpath1, datadictionarykey, xpath2);
        String actualValue = object.getText();
        expectedValue = parseValue(expectedValue);
        System.out.println("Expected Value -->" + expectedValue);
        AssertHelper validator = new AssertHelper(comparisonType, comparisonOperator, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);

    }


    @Then("^the user validates and append at leading any value \"([^\"]*)\" with data dictionary key \"([^\"]*)\" and store with new dictionary key \"([^\"]*)\"$")
    public void theUserValidatesAndAppendAnyValueWithDictionaryKeyThatTheElementBasedOnXpathIs(String value,
                                                                                               String dictionarykey,
                                                                                               String newDictionarykey) {
        value = parseValue(value);
        dictionarykey = parseValue(dictionarykey);
        newDictionarykey = parseDictionaryKey(newDictionarykey);
        String valToStore = value + dictionarykey;
        TestContext.getInstance().testdataPut(newDictionarykey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, newDictionarykey));
    }

    @Then("^the user validates and append at trailing any value \"([^\"]*)\" with data dictionary key \"([^\"]*)\" and store with new dictionary key \"([^\"]*)\"$")
    public void theUserValidatesAndAppendTrailingAnyValueWithDictionaryKeyThatTheElementBasedOnXpathIs(String value,
                                                                                                       String dictionarykey,
                                                                                                       String newDictionarykey) {
        value = parseValue(value);
        dictionarykey = parseValue(dictionarykey);
        newDictionarykey = parseDictionaryKey(newDictionarykey);
        String valToStore = dictionarykey + value;
        TestContext.getInstance().testdataPut(newDictionarykey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, newDictionarykey));
    }

    @Then("^the user validates Exact expected value \"([^\"]*)\" that the \"([^\"]*)\" element is \"([^\"]*)\" \"([^\"]*)\" at the \"([^\"]*)\" page based on datadictionary \"([^\"]*)\" and xpath1 \"([^\"]*)\" and xpath2 \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheElementBasedOnXpathExpIs(String comparisonType,
                                                                String objectName,
                                                                String comparisonOperator,
                                                                String expectedValue,
                                                                String pageName,
                                                                String datadictionarykey,
                                                                String xpath1,
                                                                String xpath2,
                                                                String validationID,
                                                                String onFailureFlag) {
        xpath1 = parseValue(xpath1);
        xpath2 = parseValue(xpath2);
        datadictionarykey = parseValue(datadictionarykey);
        Element element = getObject(objectName, pageName);
        Element object = element.getTextBasedOnXpath1AndXpath2AndDictionaryKey(xpath1, datadictionarykey, xpath2);
        String actualValue = object.getText();
        expectedValue = parseValue(expectedValue);
        AssertHelper validator = new AssertHelper(comparisonType, comparisonOperator, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);

    }

    @Then("^the user validates that the \"([^\"]*)\" element is equal to the concatenated string of \"([^\"]*)\" at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheElementIsEqualToConcatnatedString(String objectName,
                                                                         String expectedValues,
                                                                         String pageName,
                                                                         String validationID,
                                                                         String onFailureFlag) {
        //route to existing comparison method
        theUserValidatesThatTheElementIs("Compare_Strings",
                objectName,
                "Equal To",
                getConcatenatedVal(expectedValues, "\\|", " "),
                pageName,
                validationID,
                onFailureFlag);
    }

    @Then("^the user validates \"([^\"]*)\" that the data dictionary value of \"([^\"]*)\" is \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheDataDictionaryValueOf(String comparisonType,
                                                             String dictionaryKey,
                                                             String comparisonOperator,
                                                             String expectedValue,
                                                             String validationID,
                                                             String onFailureFlag) {

        dictionaryKey = parseDictionaryKey(dictionaryKey);
        expectedValue = parseValue(expectedValue);

        if (TestContext.getInstance().testdata().containsKey(dictionaryKey)) {
            String actualValue = String.valueOf(TestContext.getInstance().testdataGet(dictionaryKey));
            AssertHelper validator = new AssertHelper(comparisonType, comparisonOperator, onFailureFlag);
            TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
            validator.performValidation(actualValue, expectedValue);

        } else
            log.warn("Expected dictionary Key not found: {}", dictionaryKey);

    }

    @Then("^the user validates that \"([^\"]*)\" is present in the system dialog pop up \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatIsPresentInTheSystemDialogPopUp(String textToValidate,
                                                                    String validationID,
                                                                    String onFailureFlag) {

        textToValidate = parseValue(textToValidate);
        String actualAlertText = getDriver().switchTo().alert().getText();
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.CONTAINS, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualAlertText, textToValidate));
        validator.performValidation(actualAlertText, textToValidate);

    }

    @Then("^the user validates the \"([^\"]*)\" table is present on the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheTableIsPresentOnThePage(String tableName,
                                                           String pageName,
                                                           String validationID,
                                                           String onFailureFlag) {

        final String compareDesc = String.format("Expecting the %s table to be present on the page: %s. ",
                tableName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID,
                compareDesc + (getObject(tableName, pageName) != null));

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(getObject(tableName, pageName).refind()).as(compareDesc).isNotNull();
        } else {
            sa().assertThat(getObject(tableName, pageName).refind()).as(compareDesc).isNotNull();
            if (getObject(tableName, pageName).refind() == null) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDesc);
            }
        }

    }

    @Then("^the user validates the \"([^\"]*)\" table at the \"([^\"]*)\" page sorted in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheTableAtThePageSortedIn(String tableName,
                                                          String pageName,
                                                          String sortOrder,
                                                          String validationID,
                                                          String onFailureFlag) {
        List<Element> theList = getObject(tableName, pageName).getAllRows();

        String compareDesc = "Expecting the %s table to be sorted in %s order. ";
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            if (sortOrder.equalsIgnoreCase("Ascending")) {
                assertThat(theList).isSortedAccordingTo(Comparator.comparing(Element::getValue));
            } else if (sortOrder.equalsIgnoreCase("Descending")) {
                assertThat(theList).isSortedAccordingTo(Comparator.comparing(Element::getValue, Comparator.reverseOrder()));
            }
        } else {
            if (sortOrder.equalsIgnoreCase("Ascending")) {
                sa().assertThat(theList).isSortedAccordingTo(Comparator.comparing(Element::getValue));
            } else if (sortOrder.equalsIgnoreCase("Descending")) {
                sa().assertThat(theList).isSortedAccordingTo(Comparator.comparing(Element::getValue, Comparator.reverseOrder()));
            }

            if (!sa().wasSuccess()) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }
    }

    @Then("^the user validates the cell at row \"([^\"]*)\" and column \"([^\"]*)\" of the \"([^\"]*)\" table at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheCellAtRowAndColumnOfTheTableAtThePage(String rowNum,
                                                                         String colNum,
                                                                         String tableName,
                                                                         String pageName,
                                                                         String comparisonOperator,
                                                                         String expectedValue,
                                                                         String validationID,
                                                                         String onFailureFlag) {

        expectedValue = parseValue(expectedValue);
        String actualValue = getObject(tableName, pageName).getDataCellElement(Integer.parseInt(rowNum), Integer.parseInt(colNum)).getText();
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);
    }

    @Then("^the user validates append value \"([^\"]*)\" at the cell at row \"([^\"]*)\" and column \"([^\"]*)\" of the \"([^\"]*)\" table at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserCustomizeValidatesTheCellAtRowAndColumnOfTheTableAtThePage(String value,
                                                                                  String rowNum,
                                                                                  String colNum,
                                                                                  String tableName,
                                                                                  String pageName,
                                                                                  String comparisonOperator,
                                                                                  String expectedValue,
                                                                                  String validationID,
                                                                                  String onFailureFlag) {

        value = parseValue(value);
        expectedValue = parseValue(expectedValue);
        String actualValue = getObject(tableName, pageName).getDataCellElement(Integer.parseInt(rowNum), Integer.parseInt(colNum)).getText();
        String actualValue1 = actualValue + value;
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue1, expectedValue));
        validator.performValidation(actualValue1, expectedValue);
    }

    @Then("^the user validates based on dictionary key as expected Value at cell at row \"([^\"]*)\" and column \"([^\"]*)\" of the \"([^\"]*)\" table at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesDictionaryKeyAsExpectedValueAtTheCellAtRowAndColumnOfTheTableAtThePage(String rowNum,
                                                                                                       String colNum,
                                                                                                       String tableName,
                                                                                                       String pageName,
                                                                                                       String comparisonOperator,
                                                                                                       String dictionaryKeyValueExpected,
                                                                                                       String validationID,
                                                                                                       String onFailureFlag) {

        dictionaryKeyValueExpected = parseValue(dictionaryKeyValueExpected);
        String actualValue = getObject(tableName, pageName).getDataCellElement(Integer.parseInt(rowNum), Integer.parseInt(colNum)).getText();
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, dictionaryKeyValueExpected));
        validator.performValidation(actualValue, dictionaryKeyValueExpected);
    }


    @Then("^the user validates the color of the \"([^\"]*)\" element is \"([^\"]*)\" at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheColorOfTheElementIsAtThePage(String objectName,
                                                                String expectedColor,
                                                                String pageName,
                                                                String validationID,
                                                                String onFailureFlag) {

        expectedColor = parseValue(expectedColor);
        String actualColor = getObject(objectName, pageName).element().getCssValue("color");
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualColor, expectedColor));
        validator.performValidation(actualColor, expectedColor);
    }


    @Then("^the user validates the \"([^\"]*)\" item in the \"([^\"]*)\" radiobutton is selected at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheItemInTheRadiobuttonIsSelectedAtThePage(String rdoBtnVal,
                                                                           String objectName,
                                                                           String pageName,
                                                                           String validationID,
                                                                           String onFailureFlag) {
        rdoBtnVal = parseValue(rdoBtnVal);

        List<Element> radios = getObjects(objectName, pageName);
        boolean radioValSelected = false;
        for (Element radioItem : radios) {
            if (radioItem.findMatchingRadioButtonExact(rdoBtnVal) &&
                    radioItem.element().isSelected()) {
                radioValSelected = true;
            }
        }

        final String compareDesc = String.format("Expecting radio button value of '%s' to be selected in the '%s' radio button.",
                rdoBtnVal, objectName);
        Reporter.addStepLog(compareDesc);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc + ": " + radioValSelected);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(radioValSelected).as(compareDesc).isFalse();
        } else {
            sa().assertThat(radioValSelected).as(compareDesc).isTrue();
            if (!radioValSelected) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }
    }

    @Then("^the user validates the \"([^\"]*)\" item in the \"([^\"]*)\" checkbox is checked at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheItemInTheCheckboxIsCheckedAtThePage(String chkBoxVal,
                                                                       String objectName,
                                                                       String pageName,
                                                                       String validationID,
                                                                       String onFailureFlag) {
        chkBoxVal = parseValue(chkBoxVal);

        List<Element> checkbox = getObjects(objectName, pageName);
        boolean checkBoxValSelected = false;
        for (Element checkboxItem : checkbox) {
            if (checkboxItem.getText().equals(chkBoxVal) || checkboxItem.getAttribute("value").equals(chkBoxVal) || checkboxItem.getAttribute("title").equals(chkBoxVal)) {
                checkBoxValSelected = checkboxItem.element().isSelected();
            }
        }

        final String compareDesc = String.format("Expecting checkbox item of '%s' to be selected in the '%s' checkbox.",
                chkBoxVal, objectName);
        Reporter.addStepLog(compareDesc);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc + ": " + checkBoxValSelected);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(checkBoxValSelected).as(compareDesc).isFalse();
        } else {
            sa().assertThat(checkBoxValSelected).as(compareDesc).isTrue();
            if (!checkBoxValSelected) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^the user validates the item in the \"([^\"]*)\" checkbox is checked at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesBasedOnObjectTheItemInTheCheckboxIsCheckedAtThePage(String objectName,
                                                                                    String pageName,
                                                                                    String validationID,
                                                                                    String onFailureFlag) {
        Element checkbox = getObject(objectName, pageName);
        boolean checkBoxValSelected = false;
        checkBoxValSelected = checkbox.element().isSelected();
        final String compareDesc = String.format("Expecting checkbox item of to be selected in the '%s' checkbox.",
                objectName);
        Reporter.addStepLog(compareDesc);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc + ": " + checkBoxValSelected);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            if (false) {
                assertThat(checkBoxValSelected).as(compareDesc).isFalse();
            }
        } else {
            sa().assertThat(checkBoxValSelected).as(compareDesc).isTrue();
            if (!checkBoxValSelected) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^the user validates the item in the \"([^\"]*)\" checkbox is Not checked at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesBasedOnObjectTheItemInTheNotCheckboxIsCheckedAtThePage(String objectName,
                                                                                       String pageName,
                                                                                       String validationID,
                                                                                       String onFailureFlag) {
        Element checkbox = getObject(objectName, pageName);
        boolean checkBoxValSelected = false;
        checkBoxValSelected = checkbox.element().isSelected();
        final String compareDesc = String.format("Expecting checkbox item of to be Not selected in the '%s' checkbox.",
                objectName);
        Reporter.addStepLog(compareDesc);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc + ": " + checkBoxValSelected);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(checkBoxValSelected).as(compareDesc).isFalse();
        } else {
            sa().assertThat(checkBoxValSelected).as(compareDesc).isTrue();
            if (!checkBoxValSelected) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^the user validates the \"([^\"]*)\" item in the \"([^\"]*)\" checkbox is not checked at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheItemInTheCheckboxIsNotCheckedAtThePage(String chkBoxVal,
                                                                          String objectName,
                                                                          String pageName,
                                                                          String validationID,
                                                                          String onFailureFlag) {
        chkBoxVal = parseValue(chkBoxVal);

        List<Element> checkbox = getObjects(objectName, pageName);
        boolean checkBoxValSelected = false;
        for (Element checkboxItem : checkbox) {
            if (checkboxItem.getText().equals(chkBoxVal) || checkboxItem.getAttribute("value").equals(chkBoxVal)) {
                checkBoxValSelected = checkboxItem.element().isSelected();
            }
        }

        final String compareDesc = String.format("Expecting checkbox item of '%s' to be not selected in the '%s' checkbox.",
                chkBoxVal, objectName);
        Reporter.addStepLog(compareDesc);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc + ": " + checkBoxValSelected);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(checkBoxValSelected).as(compareDesc).isFalse();
        } else {
            sa().assertThat(checkBoxValSelected).as(compareDesc).isTrue();
            if (checkBoxValSelected) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^^the user validates the value of the \"([^\"]*)\" element at the \"([^\"]*)\" page is formatted as \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheValueOfTheElementIsFormattedAs(String objectName,
                                                                  String pageName,
                                                                  String formatType,
                                                                  String validationID,
                                                                  String onFailureFlag) throws ParseException {

        String actualValue = getObject(objectName, pageName).getValue();
        String expectedValue = format(getObject(objectName, pageName).getValue(), formatType);
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);

    }

    @Then("^the user validates that the partial text \"([^\"]*)\" element at the \"([^\"]*)\" page is \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatThePartialTextElementAtThePageIs(String objectName,
                                                                     String pageName,
                                                                     String comparisonOperator,
                                                                     String expectedValue,
                                                                     String validationID,
                                                                     String onFailureFlag) {
        expectedValue = parseValue(expectedValue);
        String actualValue = getTextFromElement(getObject(objectName, pageName));

        //If Equal To, set to a Contains comparison. Otherwise, use Not Contains
        ComparisonOperator comparisonOp = (ComparisonOperator.valueOfLabel(comparisonOperator).equals(ComparisonOperator.EQ)) ?
                ComparisonOperator.CONTAINS :
                ComparisonOperator.NOT_CONTAINS;

        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, comparisonOp, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);
    }

    @Then("^the user validates that the page title \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatTheTitleAtThePageIs(String comparisonOperator,
                                                        String expectedValue,
                                                        String validationID,
                                                        String onFailureFlag) {
        expectedValue = parseValue(expectedValue);
        String actualTitle = getDriver().getTitle();
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualTitle, expectedValue));
        validator.performValidation(actualTitle, expectedValue);
    }

    @Then("^the user validates the background color of the \"([^\"]*)\" element is \"([^\"]*)\" at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheBackgroundColorOfTheElementIsAtThePage(String objectName,
                                                                          String expectedColor,
                                                                          String pageName,
                                                                          String validationID,
                                                                          String onFailureFlag) {

        expectedColor = parseValue(expectedColor);
        final String actualBackgroundColor = getObject(objectName, pageName).element().getCssValue("background-color");
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualBackgroundColor, expectedColor));
        validator.performValidation(actualBackgroundColor.toLowerCase(), expectedColor.toLowerCase());
    }

    @Then("^the user validates the \"([^\"]*)\" element is present at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsPresentAtThePage(String objectName,
                                                             String pageName,
                                                             String validationID,
                                                             String onFailureFlag) {

        final Element elementPresent = getObject(objectName, pageName);

        final String compareDesc = String.format("Expecting the '%s' element to be present on the '%s' page. ", objectName, pageName);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(elementPresent).as(compareDesc).isNotNull();
        } else {
            sa().assertThat(elementPresent).as(compareDesc).isNotNull();
            if (elementPresent == null) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^the user order number \"([^\"]*)\" category value \"([^\"]*)\" cut and wrap validates the \"([^\"]*)\" element is present at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserCutAndWrapValidatesTheElementIsPresentAtThePage(String orderNum,
                                                                       String categoryValue,
                                                                       String tableName,
                                                                       String pageName,
                                                                       String validationID,
                                                                       String onFailureFlag) {

        orderNum = parseValue(orderNum);
        categoryValue = parseValue(categoryValue);
        final Element elementPresent = getObject(tableName, pageName).getRowValueCutAndWrap(orderNum, categoryValue);
        final String compareDesc = String.format("Expecting the '%s' element to be present on the '%s' page. ", tableName, pageName);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(elementPresent).as(compareDesc).isNotNull();
        } else {
            sa().assertThat(elementPresent).as(compareDesc).isNotNull();
            if (elementPresent == null) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }

    }

    @Then("^the user validates the \"([^\"]*)\" element is not present at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheElementIsNotPresenteAtThePage(String objectName,
                                                                 String pageName,
                                                                 String validationID,
                                                                 String onFailureFlag) {

        final Element elementPresent = getObjectAllowNull(objectName, pageName);

        final String compareDesc = String.format("Expecting the '%s' element to not be present on the '%s' page. ",
                objectName, pageName);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(elementPresent.element()).as(compareDesc).isNull();
        } else {
            sa().assertThat(elementPresent.element()).as(compareDesc).isNull();
            if (elementPresent.element() != null) {
                Reporter.addStepLog(STATUS_FAIL, compareDesc);
            }
        }
    }

    @Then("^the user validates the sequence of columns in the \"([^\"]*)\" table at the \"([^\"]*)\" page is \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheSequenceOfColumnsInTheTableAtThePageIs(String tableName,
                                                                          String pageName,
                                                                          String expectedSequence,
                                                                          String validationID,
                                                                          String onFailureFlag) {
        String expectedColumnListValue = parseValue(expectedSequence);
        List<String> actualColumnList = getObject(tableName, pageName).getHeaderColumnsWithGivenAttribute();
        List<String> expectedColumnList = Arrays.asList(expectedColumnListValue.split("\\|"));

        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_LISTS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualColumnList.toString(), expectedColumnList.toString()));
        validator.performValidation(actualColumnList, expectedColumnList);
    }

    @Then("^the user validates the \"([^\"]*)\" table at the \"([^\"]*)\" page is filtered by the value \"([^\"]*)\" in the \"([^\"]*)\" column \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheTableAtThePageIsFilteredByTheValueInTheColumn(String tableName,
                                                                                 String pageName,
                                                                                 String expectedColValue,
                                                                                 String colHeader,
                                                                                 String validationID,
                                                                                 String onFailureFlag) {
        List<String> actualValues = getObject(tableName, pageName).getRowValuesForGivenColumn(colHeader);
        expectedColValue = parseValue(expectedColValue);
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_LISTS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValues.toString(), expectedColValue));
        List<String> expectedValueList = Arrays.asList(expectedColValue);
        validator.performValidation(actualValues, expectedValueList);
    }

    @Then("^the user validates the \"([^\"]*)\" value for \"([^\"]*)\" element is selected at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheValueForElementIsSelectedAtThePage(String expectedValue,
                                                                      String objectName,
                                                                      String pageName,
                                                                      String validationID,
                                                                      String onFailureFlag) {
        String selectedText = getObject(objectName, pageName).dropdown().getFirstSelectedOption().getText();
        expectedValue = parseValue(expectedValue);
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(selectedText, expectedValue));
        validator.performValidation(selectedText, expectedValue);
    }

    @Then("^the user validates the data dictionary value of \"([^\"]*)\" is \"([^\"]*)\" data dictionary value of \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheDataDictionaryValueOfIsDataDictionaryValueOf(String dataDictionaryVal1,
                                                                                String comparisonOperator,
                                                                                String dataDictionaryVal2,
                                                                                String validationID,
                                                                                String onFailureFlag) {
        dataDictionaryVal1 = parseValue(dataDictionaryVal1);
        dataDictionaryVal2 = parseValue(dataDictionaryVal2);

        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(dataDictionaryVal1, dataDictionaryVal2));
        validator.performValidation(dataDictionaryVal1, dataDictionaryVal2);
    }

    @Then("^the user validates the value of cell at the last row and last column is \"([^\"]*)\" \"([^\"]*)\" in the \"([^\"]*)\" table at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheValueForCellAtTheLastRowAndLastColumnInTheTableAtThePageIs(String comparisonOperator,
                                                                                              String expectedValue,
                                                                                              String tableName,
                                                                                              String pageName,
                                                                                              String validationID,
                                                                                              String onFailureFlag) {
        expectedValue = parseValue(expectedValue);
        Element table = getObject(tableName, pageName);
        String actualValue = table.getDataCellElement(table.getDataRowCount() - 1, table.getDataColumnCount(0) - 1).getText();

        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.valueOfLabel(comparisonOperator), onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualValue, expectedValue));
        validator.performValidation(actualValue, expectedValue);
    }

    @Then("^the user validates that \"([^\"]*)\" is found in the \"([^\"]*)\" column in the \"([^\"]*)\" table on the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheValueisFoundInColumn(String expectedValue,
                                                        String colName,
                                                        String tableName,
                                                        String pageName,
                                                        String validationID,
                                                        String onFailureFlag) {

        Element matchingTableCell = findMatchingTableCell(colName,
                parseValue(expectedValue),
                getObject(tableName, pageName));

        final String compareDesc = String.format("Expecting %s to be found in the %s column of the %s table at the %s page",
                expectedValue, colName, tableName, pageName);

        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, compareDesc);

        if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
            assertThat(matchingTableCell).as(compareDesc).isNotNull();
        } else {
            sa().assertThat(matchingTableCell).as(compareDesc).isNotNull();
            if (matchingTableCell == null) {
                Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDesc);
            }
        }
    }

    @Then("^the user validates that all values in the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page matches \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheValuesInTheDropdownAtThePageIs(String dropdown,
                                                                  String pageName,
                                                                  String expectedSequence,
                                                                  String validationID,
                                                                  String onFailureFlag) {

        List<String> actualList = getObject(dropdown, pageName).dropdown().getOptions().stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> expectedList = Arrays.asList(expectedSequence.split("\\|"));

        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_LISTS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(actualList.toString(), expectedList.toString()));
        validator.performValidation(actualList, expectedList);
    }

    @Then("^the user validates that all elements that match the pattern of the \"([^\"]*)\" element are disabled at the \"([^\"]*)\" page \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesThatAllElementsThatMatchThePatternOfTheElementAreDisabledAtThePage(String objectName,
                                                                                                   String pageName,
                                                                                                   String validationID,
                                                                                                   String onFailureFlag) {
        List<Element> els = getObjects(objectName, pageName);
        boolean isElementEnabled = true;
        for (Element el : els) {
            if (el.element().isDisplayed()) {
                isElementEnabled = el.element().isEnabled();

                final String compareDescription = String.format("Expected %s to be disabled at the page", el);

                TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID,
                        compareDescription + ": " + isElementEnabled);

                if (onFailureFlag.equals(HARD_STOP_ON_FAILURE)) {
                    assertThat(isElementEnabled).as(compareDescription).isFalse();
                } else {
                    sa().assertThat(isElementEnabled).as(compareDescription).isFalse();
                    if (isElementEnabled) {
                        Reporter.addStepLog(STATUS_FAIL, VALIDATION_FAILED + compareDescription);
                    }
                }
            }

        }
    }

    @Then("^the user validates the order of the \"([^\"]*)\" object list at the \"([^\"]*)\" page is \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheOrderingOfSectionsAtThePageIs(String listOfObjects,
                                                                 String pageName,
                                                                 String expectedSequence,
                                                                 String validationID,
                                                                 String onFailureFlag) {

        List<String> objectOrderOnPage = getObjects(listOfObjects, pageName).stream()
                .map(this::getTextFromElement)
                .collect(Collectors.toList());

        List<String> expectedObjectOrder = Arrays.asList(expectedSequence.split("\\|"));
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_LISTS, ComparisonOperator.EQ, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID,
                validator.getResultMessage(objectOrderOnPage.toString(), expectedObjectOrder.toString()));
        validator.performValidation(objectOrderOnPage, expectedObjectOrder);

    }

    @Then("^the user validates that the stored data dictionary map \"([^\"]*)\" has a key equal to \"([^\"]*)\" where the value is \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theUserValidatesTheKeyCorrespondsValueOfIsDataDictionaryMap(String storedMapDictionaryKey,
                                                                            String expectedRate,
                                                                            String amount,
                                                                            String validationID,
                                                                            String onFailureFlag) {

        storedMapDictionaryKey = parseDictionaryKey(storedMapDictionaryKey);
        expectedRate = parseValue(expectedRate);
        amount = parseValue(amount).replace(",", "");

        Map<String, String> storedValue = TestContext.getInstance().testdataToClass(storedMapDictionaryKey, Map.class);
        String key = null;
        for (Map.Entry<String, String> entry : storedValue.entrySet()) {
            if (Double.parseDouble(amount) < Double.parseDouble(entry.getValue().replace(",", ""))) {
                key = entry.getKey();
                break;
            }
        }
        AssertHelper validator = new AssertHelper(ComparisonType.COMPARE_STRINGS, ComparisonOperator.CONTAINS, onFailureFlag);
        TestContext.getInstance().testdata().put(VALIDATION_TAG + validationID, validator.getResultMessage(expectedRate, key));
        validator.performValidation(expectedRate, key);
    }

    @Then("^the user click prepstation \"([^\"]*)\" element until \"([^\"]*)\" expected value based on attribute \"([^\"]*)\" found at the page \"([^\"]*)\"$")
    public void theUserClickUntilElementFound(String objectName,
                                              String expectedValue,
                                              String attributeName,
                                              String pageName
    ) {
        attributeName = parseValue(attributeName);
        expectedValue = parseValue(expectedValue);
        String expectedValueOne = "div_PS_item_" + expectedValue + "_1" + "_1";
        String expectedValueTwo = "div_PS_item_" + expectedValue + "_1" + "_2";
//        String expectedValueThreee = "div_PS_item_" + expectedValue + "_1" + "_3";
        String transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);

        if (expectedValueOne.equalsIgnoreCase(transactionNumber)) {
            getObject(objectName, pageName).click();
            transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
        if (expectedValueTwo.equalsIgnoreCase(transactionNumber)) {
            getObject(objectName, pageName).click();
           // transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
//        if (expectedValueThreee.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//        }
        else {
            getObject(objectName, pageName).click();
            theUserClickUntilElementFound(objectName, expectedValue, attributeName, pageName);
        }

    }

    @Then("^the user click makeline \"([^\"]*)\" element until \"([^\"]*)\" expected value based on attribute \"([^\"]*)\" found at the page \"([^\"]*)\"$")
    public void theUserClickMakelineUntilElementFound(String objectName,
                                                      String expectedValue,
                                                      String attributeName,
                                                      String pageName
    ) {
        attributeName = parseValue(attributeName);
        expectedValue = parseValue(expectedValue);
        String expectedValueOne = expectedValue + "_1" + "_1";
        String expectedValueTwo = expectedValue + "_1" + "_2";
//        String expectedValueThreee = expectedValue + "_1" + "_3";
        System.out.println(attributeName);
        String transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);

        if (expectedValueOne.equalsIgnoreCase(transactionNumber)) {
            getObject(objectName, pageName).click();
            transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
        if (expectedValueTwo.equalsIgnoreCase(transactionNumber)) {
            getObject(objectName, pageName).click();
            //transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
//        if (expectedValueThreee.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//        }
        else {
            getObject(objectName, pageName).click();
            theUserClickMakelineUntilElementFound(objectName, expectedValue, attributeName, pageName);
        }

    }

    @Then("^the user click makeline single pizza \"([^\"]*)\" element until \"([^\"]*)\" expected value based on attribute \"([^\"]*)\" found at the page \"([^\"]*)\"$")
    public void theUserClickSinglePizzaMakelineUntilElementFound(String objectName,
                                                      String expectedValue,
                                                      String attributeName,
                                                      String pageName
    ) {
        attributeName = parseValue(attributeName);
        expectedValue = parseValue(expectedValue);
        String expectedValueOne = expectedValue + "_1" + "_1";
      //  String expectedValueTwo = expectedValue + "_1" + "_2";
//        String expectedValueThreee = expectedValue + "_1" + "_3";
        System.out.println(attributeName);
        String transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);

        if (expectedValueOne.equalsIgnoreCase(transactionNumber)) {
            getObject(objectName, pageName).click();
          //  transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
//        if (expectedValueTwo.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//            //transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
//        }
//        if (expectedValueThreee.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//        }
        else {
            getObject(objectName, pageName).click();
            theUserClickSinglePizzaMakelineUntilElementFound(objectName, expectedValue, attributeName, pageName);
        }

    }

    @Then("^the user custom click and clear all prep station Or Make Line item \"([^\"]*)\" element found at the page \"([^\"]*)\"$")
    public void theUserCustomClickUntilElementFound(String objectName,
                                                    String pageName) {

        boolean value = getObject(objectName, pageName).isDisplayed();

        if (value) {
            getObject(objectName, pageName).click();
            theUserCustomClickUntilElementFound(objectName, pageName);
        } else {
            System.out.println("please proceed");
        }

    }

    @Then("^the users click \"([^\"]*)\" element until \"([^\"]*)\" expected value based on attribute \"([^\"]*)\" found at the page \"([^\"]*)\"$")
    public void theUsersClickUntilElementFound(String objectName,
                                               String expectedValue,
                                               String attributeName,
                                               String pageName
    ) {
        attributeName = parseValue(attributeName);
        expectedValue = parseValue(expectedValue);
        String expectedValueOne = expectedValue + "_1" + "_1";
        String expectedValueTwo = expectedValue + "_1" + "_2";
        String expectedValueThreee = expectedValue + "_1" + "_3";
        String actualTransactionNumber = getObject(objectName, pageName).getAttribute(attributeName);

        if (expectedValueOne.equalsIgnoreCase(actualTransactionNumber)) {
            getObject(objectName, pageName).click();
            //  transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
        }
//        if (expectedValueTwo.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//            transactionNumber = getObject(objectName, pageName).getAttribute(attributeName);
//        }
//        if (expectedValueThreee.equalsIgnoreCase(transactionNumber)) {
//            getObject(objectName, pageName).click();
//        }
        else {
            getObject(objectName, pageName).click();
            theUsersClickUntilElementFound(objectName, expectedValue, attributeName, pageName);
        }

    }

    @Then("^the users custom click \"([^\"]*)\" element until \"([^\"]*)\" expected value based on attribute \"([^\"]*)\" attribute \"([^\"]*)\" attribute \"([^\"]*)\" found at the page \"([^\"]*)\"$")
    public void theUsersCustomClickUntilElementFound(String objectName,
                                                     String orderNum,
                                                     String categoryValue1,
                                                     String categoryValue2,
                                                     String categoryValue3,
                                                     String pageName) {
        orderNum = parseValue(orderNum);
        String orderNum1 = orderNum + "-" + categoryValue1;
        String orderNum2 = orderNum + "-" + categoryValue2;
        String orderNum3 = orderNum + "-" + categoryValue3;
        categoryValue1 = parseValue(categoryValue1);
        categoryValue2 = parseValue(categoryValue2);
        categoryValue3 = parseValue(categoryValue3);
        String textValue1 = getObject(objectName, pageName).getRowValueText(orderNum, categoryValue1).getText();
        String textValue2 = getObject(objectName, pageName).getRowValueText(orderNum, categoryValue2).getText();
        String textValue3 = getObject(objectName, pageName).getRowValueText(orderNum, categoryValue3).getText();

        if (textValue1.equalsIgnoreCase(orderNum1)) {
            getObject(objectName, pageName).getRowValueText(orderNum, categoryValue1).click();
        }
        if (textValue2.equalsIgnoreCase(orderNum2)) {
            getObject(objectName, pageName).getRowValueText(orderNum, categoryValue1).click();
        }
        if (textValue3.equalsIgnoreCase(orderNum3)) {
            getObject(objectName, pageName).getRowValueText(orderNum, categoryValue1).click();
        } else {
            getObject(objectName, pageName).click();
            theUsersCustomClickUntilElementFound(objectName, orderNum, categoryValue1, categoryValue2, categoryValue3, pageName);
        }

    }
}
