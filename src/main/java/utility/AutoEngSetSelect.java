package utility;

import common.TestContext;
import core.BaseWebSteps;
import core.Element;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class AutoEngSetSelect extends BaseWebSteps {

    private static final String MATCHING_RADIO_BUTTON_NOT_FOUND = "Matching radio button not found: {}";
    private static final String SELECTED_VALUE_INDEX = "Selected value index: \"%s\"";
    private static final String SELECTED_VALUE = "Selected value: \"%s\"";
    private static final String COULD_NOT_FIND_UNIQUE_ROW = "Could not find unique row with {} having {} and {} having {} and {} having {}";

    @When("^the user selects valueIndex \"([^\"]*)\" from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
    public void theUserSelectsValueIndexFromTheDropdownAtThePage(String valueIndex,
                                                                 String objectName,
                                                                 String pageName) {
        valueIndex = parseValue(valueIndex);
        getObject(objectName, pageName).select(valueIndex);
        logStepMessage(String.format(SELECTED_VALUE_INDEX, valueIndex));
    }

    @When("^the user selects value \"([^\"]*)\" from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
    public void theUserSelectsValueFromTheDropdownAtThePage(String value,
                                                            String objectName,
                                                            String pageName) {
        value = parseValue(value);
        getObject(objectName, pageName).selectFromDropdown(value);
        logStepMessage(String.format(SELECTED_VALUE, value));
    }

    @When("^the user selects conditioning value either YES or NO from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
    public void theUserSelectsConditioningValueFromTheDropdownAtThePage(String objectName,
                                                                        String pageName) {
        String selectedText = getObject(objectName, pageName).dropdown().getFirstSelectedOption().getText();
        selectedText = parseValue(selectedText);
        if (selectedText.equalsIgnoreCase("YES")) {
            getObject(objectName, pageName).selectFromDropdown("NO");
        } else if (selectedText.equalsIgnoreCase("NO")) {
            getObject(objectName, pageName).selectFromDropdown("YES");
        }
    }

    @When("^the user selects value from the \"([^\"]*)\" dropdown not equal to given value \"([^\"]*)\" at the \"([^\"]*)\" page$")
    public void theUserSelectsRandomValueFromTheDropDownAtThePage(String objectName,
                                                                  String valueToFind,
                                                                  String pageName) {
        valueToFind = parseValue(valueToFind);
        boolean dropDownFound = false;
        List<String> dropDowns = getObject(objectName, pageName).getDropdownOptionsValues();
        for (String Value : dropDowns) {
            if (!Value.equalsIgnoreCase(valueToFind)) {
                getObject(objectName, pageName).selectFromDropdown(Value);
                dropDownFound = true;
                logStepMessage(String.format(SELECTED_VALUE, Value));
                break;
            }
        }
        if (!dropDownFound) {
            log.warn("Matching dropdown not found: {}", valueToFind);
        }
    }

    @When("^the user selects value that contains \"([^\"]*)\" from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page$")
    public void theUserSelectsValueThatContainsFromTheDropdownAtThePage(String value,
                                                                        String objectName,
                                                                        String pageName) {
        final String finalValue = parseValue(value);
        Optional<String> options = getObject(objectName, pageName).getDropdownOptionsText()
                .stream()
                .filter(ele -> ele.contains(finalValue)).findFirst();
        if (options.isPresent()) {
            getObject(objectName, pageName).selectFromDropdown(options.get());
            logStepMessage(String.format(SELECTED_VALUE, finalValue));
        }
    }

    @When("^the user selects valueIndex \"([^\"]*)\" from the \"([^\"]*)\" combobox at the \"([^\"]*)\" page$")
    public void theUserSelectsValueIndexFromTheComboboxAtThePage(String valueIndex,
                                                                 String objectName,
                                                                 String pageName) {
        valueIndex = parseValue(valueIndex);
        getObject(objectName, pageName).click();
        getObject(objectName, pageName).select(valueIndex);
        logStepMessage(String.format(SELECTED_VALUE_INDEX, valueIndex));

    }

    @When("^the user selects value \"([^\"]*)\" from the \"([^\"]*)\" combobox at the \"([^\"]*)\" page$")
    public void theUserSelectsValueFromTheComboboxAtThePage(String value,
                                                            String objectName,
                                                            String pageName) {
        value = parseValue(value);
        getObject(objectName, pageName).click();
        getObject(objectName, pageName).element().sendKeys(value, Keys.ENTER);
        logStepMessage(String.format(SELECTED_VALUE, value));
    }

    @When("^the user enters \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersIntoTheTextboxAtThePage(String value,
                                                     String objectName,
                                                     String pageName) {
        value = parseValue(value);
        Element textBox = getObject(objectName, pageName);

        enterValueInTextBox(value, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value));
    }

    @When("^the user enters dynamic email \"([^\"]*)\" and \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersDyanamicEmailIdIntoTheTextboxAtThePage(String value1,
                                                                    String value2,
                                                                    String objectName,
                                                                    String pageName) {
        value1 = parseValue(value1);
        value2 = parseValue(value2);
        Element textBox = getObject(objectName, pageName);

        enterValueDynamicEmailIdInTextBox(value1, value2, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value1));
    }

    @When("^the user enters dynamic UserName \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersDyanamicUserNameIntoTheTextboxAtThePage(String value1,
                                                                     String objectName,
                                                                     String pageName) {
        value1 = parseValue(value1);
        Element textBox = getObject(objectName, pageName);

        enterValueDynamicUserNameInTextBox(value1, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value1));
    }

    @When("^the user enters generate dynamic number \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersGenerateDyanamicNumberIntoTheTextboxAtThePage(String value1,
                                                                           String objectName,
                                                                           String pageName) {
        value1 = parseValue(value1);
        Element textBox = getObject(objectName, pageName);

        enterValueDynamicUserNameInTextBox(value1, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value1));
    }

    @When("^the user enters dynamic alphabetic string \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersDyanamicAlphabeticStringIntoTheTextboxAtThePage(String value1,
                                                                             String objectName,
                                                                             String pageName) {
        value1 = parseValue(value1);
        Element textBox = getObject(objectName, pageName);

        enterValueDynamicAlphabeticUserNameInTextBox(value1, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value1));
    }

    @When("^the user enters \"([^\"]*)\" days before with current date into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersDateTextboxAtThePage(String value1,
                                                  String objectName,
                                                  String pageName) {
        value1 = parseValue(value1);
        Element textBox = getObject(objectName, pageName);

        enterDateInTextBox(value1, textBox);

        logStepMessage(String.format(ENTERED_VALUE, value1));
    }

    @When("^the user perform TAB into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserSwitchToTheNextTabAtThePage(String objectName,
                                                   String pageName) {
        Element textBox = getObject(objectName, pageName);

        performTabOperation(textBox);

    }

    @When("^the user sends keys \"([^\"]*)\"$")
    public void theUserSendsKeys(String keySequence) {
        sendKeyList(getKeysToPress(keySequence));
    }


    @When("^the user sends keys \"([^\"]*)\" to the \"([^\"]*)\" element on the \"([^\"]*)\" page$")
    public void theUserSendsKeysToTheElementOnThePage(String keySequence,
                                                      String objectName,
                                                      String pageName) {

        List<Keys> keysToPress = getKeysToPress(keySequence);

        switch (keysToPress.size()) {
            case 1:
                getObject(objectName, pageName).sendKeys(Keys.chord(keysToPress.get(0)));
                break;
            case 2:
                getObject(objectName, pageName).sendKeys(Keys.chord(keysToPress.get(0), keysToPress.get(1)));
                break;
            case 3:
                getObject(objectName, pageName).sendKeys(Keys.chord(keysToPress.get(0), keysToPress.get(1), keysToPress.get(2)));
                break;
            default:
                log.error("No keys to press found");
                break;
        }
    }

    @When("^the user sets focus on the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserSetsFocusOnTheTextboxAtThePage(String objectName,
                                                      String pageName) {
        getObject(objectName, pageName).focus();
    }

    @When("^the user selects valueIndex \"([^\"]*)\" from the \"([^\"]*)\" radiobutton at the \"([^\"]*)\" page$")
    public void theUserSelectsValueIndexFromTheRadiobuttonAtThePage(String valueIndex,
                                                                    String objectName,
                                                                    String pageName) {
        valueIndex = parseValue(valueIndex);
        getObjects(objectName, pageName).get(Integer.parseInt(valueIndex)).click();
        logStepMessage(String.format(SELECTED_VALUE_INDEX, valueIndex));
    }

    @When("^the user selects value \"([^\"]*)\" from the \"([^\"]*)\" radiobutton at the \"([^\"]*)\" page$")
    public void theUserSelectsValueFromTheRadiobuttonAtThePage(String rdoBtnVal,
                                                               String objectName,
                                                               String pageName) {
        rdoBtnVal = parseValue(rdoBtnVal);
        boolean rdoBtnFound = false;

        final List<Element> radios = getObjects(objectName, pageName);
        for (Element radio : radios) {
            if (radio.findMatchingRadioButtonExact(rdoBtnVal)) {
                radio.click();

                if (!radio.element().isSelected()) {
                    radio.clickJS();
                }

                logStepMessage(String.format(SELECTED_VALUE, rdoBtnVal));
                rdoBtnFound = true;
                break;
            }
        }

        if (!rdoBtnFound) {
            log.warn(MATCHING_RADIO_BUTTON_NOT_FOUND, rdoBtnVal);
        }
    }

    @When("^the user selects value that contains \"([^\"]*)\" from the \"([^\"]*)\" radiobutton at the \"([^\"]*)\" page$")
    public void theUserSelectsValueThatContainsFromTheRadiobuttonAtThePage(String rdoBtnVal,
                                                                           String objectName,
                                                                           String pageName) {
        rdoBtnVal = parseValue(rdoBtnVal);
        boolean rdoBtnFound = false;

        final List<Element> radios = getObjects(objectName, pageName);
        for (Element radio : radios) {
            if (radio.findMatchingRadioButtonContains(rdoBtnVal)) {
                radio.click();

                if (!radio.element().isSelected()) {
                    radio.clickJS();
                }

                logStepMessage(String.format(SELECTED_VALUE, rdoBtnVal));
                rdoBtnFound = true;
                break;
            }
        }

        if (!rdoBtnFound) {
            log.warn(MATCHING_RADIO_BUTTON_NOT_FOUND, rdoBtnVal);
        }
    }

    @When("^the user enters the encrypted value \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersTheEncryptedValueIntoTheTextboxAtThePage(String value,
                                                                      String objectName,
                                                                      String pageName) {
        //route to credentials implementation
        theUserEntersTheSecureUserCredentialIntoTheTextboxAtThePage(value, objectName, pageName);
    }

    @When("^the user enters the user credential \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersTheUserCredentialIntoTheTextboxAtThePage(String value,
                                                                      String objectName,
                                                                      String pageName) {
        String valToEnter = parseUser(value);
        Element textBox = getObject(objectName, pageName);

        enterValueInTextBox(valToEnter, textBox);
    }

    @When("^the user enters the data driven username credential \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersTheDataDrivenUserNameCredentialIntoTheTextboxAtThePage(String value,
                                                                                    String objectName,
                                                                                    String pageName) {
        Element textBox = getObject(objectName, pageName);

        enterValueInTextBox(value, textBox);
    }

    @When("^the user enters the data driven password credential \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersTheDataDrivenPasswordCredentialIntoTheTextboxAtThePage(String value,
                                                                                    String objectName,
                                                                                    String pageName) {
        Element textBox = getObject(objectName, pageName);

        enterValueInTextBox(value, textBox);
    }

    @When("^the user enters the secure credential \"([^\"]*)\" into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserEntersTheSecureUserCredentialIntoTheTextboxAtThePage(String value,
                                                                            String objectName,
                                                                            String pageName) {
        String valToEnter = parseSecureText(value);
        Element textBox = getObject(objectName, pageName);

        enterValueInTextBox(valToEnter, textBox);
    }


    @When("^the user clears the value in the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserClearsTheValueInTheTextBox(String objectName,
                                                  String pageName) {

        getObject(objectName, pageName).clear();
    }

    @When("^the user enters the user credential \"([^\"]*)\" into the authentication popup$")
    public void theUserEntersInAuthenticationPopup(String value) throws AWTException {
        String valToEnter = parseUser(value);
        sendKeysWithRobot(valToEnter);
    }

    @When("^the user enters random number into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page$")
    public void theUserGenerateTheRandomNumberAtPage(String objectName,
                                                     String pageName) {
        Element textBox = getObject(objectName, pageName);
        enterValueRandomNumberInTextBox(textBox);
    }

    @When("^the user enters random number into the \"([^\"]*)\" textbox at the \"([^\"]*)\" page and store at dictionary key \"([^\"]*)\"$")
    public void theUserSavedGeneratedTheRandomNumberDictionaryKeyAtPage(String objectName,
                                                                        String pageName,
                                                                        String dictionaryKey) {
        dictionaryKey=parseValue(dictionaryKey);
        Element textBox = getObject(objectName, pageName);
        int randomNumber=generateRandomNumberAndEnter(textBox);
        TestContext.getInstance().testdataPut(dictionaryKey, randomNumber);
        logStepMessage(String.format(STORED_VALUE, randomNumber, dictionaryKey));
    }

    @When("^the user enters the secure user credential \"([^\"]*)\" into the authentication popup$")
    public void theUserEntersSecureInAuthenticationPopup(String value) throws AWTException {
        String valToEnter = parseSecureText(value);
        sendKeysWithRobot(valToEnter);
    }

    @When("^the user presses the \"([^\"]*)\" key into a desktop popup$")
    public void theUserPressesKey(String value) throws AWTException {
        String valToEnter = parseValue(value);
        sendKeysWithRobot(valToEnter);
    }

    @When("^the user selects radio button on the matching cell in the \"([^\"]*)\" column containing \"([^\"]*)\" where the \"([^\"]*)\" column contains \"([^\"]*)\" and the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserSelectsTheRadioButtonInTheMatchingCellInTheColumnContainingWhereTheColumnContainsAndTheColumnContainsFromTheTableAtThePage(
            String firstColName,
            String firstColValue,
            String secondColName,
            String secondColValue,
            String thirdColName,
            String thirdColValue,
            String tableName,
            String pageName) {

        Element matchingRowCell = findMatchingTableCell(firstColName, parseValue(firstColValue),
                secondColName, parseValue(secondColValue),
                thirdColName, parseValue(thirdColValue),
                getObject(tableName, pageName));

        if (matchingRowCell != null && matchingRowCell.element() != null) {
            Element rdoButtonToClick = matchingRowCell.findRadioButtonInTableRow();

            if (rdoButtonToClick != null) {
                rdoButtonToClick.click();
            } else {
                throw new NotFoundException("Unable to find a matching radio button in this table row");
            }
        } else
            log.warn(COULD_NOT_FIND_UNIQUE_ROW,
                    firstColName, firstColValue, secondColName, secondColValue, thirdColName, thirdColValue);
    }

    @When("^the user selects radio button on the matching cell where the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserSelectsTheRadioButtonInTheMatchingCellWhereTheColumnContainsFromTheTableAtThePage(
            String firstColName,
            String firstColValue,
            String tableName,
            String pageName) {

        Element matchingRowCell = findMatchingTableCell(firstColName, parseValue(firstColValue),
                getObject(tableName, pageName));

        if (matchingRowCell != null && matchingRowCell.element() != null) {
            Element rdoButtonToClick = matchingRowCell.findRadioButtonInTableRow();

            if (rdoButtonToClick != null) {
                rdoButtonToClick.click();
            } else {
                throw new NotFoundException("Unable to find a matching radio button in this table row");
            }
        } else
            log.warn(COULD_NOT_FIND_UNIQUE_ROW,
                    firstColName, firstColValue);
    }

    @When("^the user selects checkbox on the matching cell where the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserSelectsTheCheckboxOnTheMatchingCellWhereTheColumnContainsFromTheTableAtThePage(
            String firstColName,
            String firstColValue,
            String tableName,
            String pageName) {

        Element matchingRowCell = findMatchingTableCell(firstColName, parseValue(firstColValue),
                getObject(tableName, pageName));

        if (matchingRowCell != null && matchingRowCell.element() != null) {
            Element chkBoxToClick = matchingRowCell.findRadioButtonInTableRow();

            if (chkBoxToClick != null) {
                chkBoxToClick.click();
            } else {
                throw new NotFoundException("Unable to find a matching checkbox in this table row");
            }
        } else
            log.warn(COULD_NOT_FIND_UNIQUE_ROW,
                    firstColName, firstColValue);
    }

    @When("^the user selects value \"([^\"]*)\" from the dropdown on the matching cell where the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserSelectsValueFromTheDropdownOnTheMatchingCellWhereTheColumnContainsFromTheTableAtThePage(
            String value,
            String colName,
            String colvalue,
            String tableName,
            String pageName) {

        value = parseValue(value);
        Element matchingRowCell = findMatchingTableCell(colName, parseValue(colvalue),
                getObject(tableName, pageName));

        if (matchingRowCell != null && matchingRowCell.element() != null) {
            Element dropDownToSelect = matchingRowCell.findDropDownInTableRow();
            if (dropDownToSelect != null) {
                dropDownToSelect.selectFromDropdown(value);
                logStepMessage(String.format(SELECTED_VALUE, value));
            } else {
                throw new NotFoundException("Unable to find a matching dropdown in this table row");
            }
        } else
            log.warn(COULD_NOT_FIND_UNIQUE_ROW,
                    colName, colvalue);
    }


    @When("^the user selects random value from the \"([^\"]*)\" radiobutton not equal to given value \"([^\"]*)\" at the \"([^\"]*)\" page$")
    public void theUserSelectsRandomValueFromTheRadiobuttonAtThePage(String objectName,
                                                                     String rdoBtnVal,
                                                                     String pageName) {
        rdoBtnVal = parseValue(rdoBtnVal);
        boolean rdoBtnFound = false;

        final List<Element> radios = getObjects(objectName, pageName);
        for (Element radio : radios) {
            if (!radio.findMatchingRadioButtonExact(rdoBtnVal)) {
                radio.click();

                if (!radio.element().isSelected()) {
                    radio.clickJS();
                }
                String valselected = radio.getRadioLabelText();
                logStepMessage(String.format(SELECTED_VALUE, valselected));
                rdoBtnFound = true;
                break;
            }
        }

        if (!rdoBtnFound) {
            log.warn(MATCHING_RADIO_BUTTON_NOT_FOUND, rdoBtnVal);
        }
    }

    @When("^the user selects the value of \"([^\"]*)\" from the table \"([^\"]*)\" on the page \"([^\"]*)\"$")
    public void theUserSelectsTheValueOfFromTheTableOnThePage(String valueToFind,
                                                              String tableName,
                                                              String pageName) {
        valueToFind = parseValue(valueToFind);
        Element tableCellToClick = getObject(tableName, pageName).checkForMatchInTable(valueToFind);
        if (tableCellToClick != null && tableCellToClick.element() != null) {
            tableCellToClick.click();
        } else {
            log.warn("Could not find Expected value {} in the table", valueToFind);
        }
    }


}
