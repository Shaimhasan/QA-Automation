package utility;

import common.TestContext;
import core.BaseWebSteps;
import io.cucumber.java.en.When;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AutoEngStoreValue extends BaseWebSteps {

    @When("^store tooltip value of the \"([^\"]*)\" element at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTooltipValueOfTheElementAtThePageIntoTheDataDictionaryWithKey(String objectName,
                                                                                   String pageName,
                                                                                   String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(objectName, pageName).getAttribute("title");
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the displayed text of the \"([^\"]*)\" element at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTheDisplayedTextOfTheElementAtThePageIntoTheDataDictionaryWithKey(String objectName,
                                                                                       String pageName,
                                                                                       String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getTextFromElement(getObject(objectName, pageName));
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the value of the \"([^\"]*)\" element at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTheValueOfTheElementAtThePageIntoTheDataDictionaryWithKey(String objectName,
                                                                               String pageName,
                                                                               String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(objectName, pageName).getValue();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store list values from the \"([^\"]*)\" dropdown at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeListValuesFromTheDropdownAtTheIntoTheDataDictionaryWithKey(String objectName,
                                                                                String pageName,
                                                                                String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        List<String> valToStore = getObject(objectName, pageName).getDropdownOptionsValues();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store list values from the \"([^\"]*)\" combobox at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeListValuesFromTheComboboxAtTheIntoTheDataDictionaryWithKey(String objectName,
                                                                                String pageName,
                                                                                String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(objectName, pageName).element().getText();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the current page URL into the data dictionary with key \"([^\"]*)\"$")
    public void storeTheCurrentPageURLIntoTheDataDictionaryWithKey(String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getDriver().getCurrentUrl();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store value from row \"([^\"]*)\" and column \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeValueFromRowAndColumnFromTheTableAtThePageIntoTheDataDictionaryWithKey(String rowNum,
                                                                                            String colNum,
                                                                                            String tableName,
                                                                                            String pageName,
                                                                                            String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(tableName, pageName).getDataCellElement(Integer.parseInt(rowNum),
                                                                              Integer.parseInt(colNum)).getText();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the value of the phone \"([^\"]*)\" element and \"([^\"]*)\" element at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTheValueOfThePhoneElementAndElementAtThePageIntoTheDataDictionaryWithKey(String countryCodeEle,
                                                                                              String phoneNumEle,
                                                                                              String pageName,
                                                                                              String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String countryCode = getObject(countryCodeEle, pageName).getValue();
        String phoneNum = getObject(phoneNumEle, pageName).getValue();
        String valToStore = countryCode.concat(phoneNum);
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the current system time into the data dictionary with key \"([^\"]*)\" as timezone \"([^\"]*)\"$")
    public void storeTheCurrentSystemTimeIntoTheDataDictionaryWithKeyAsTimezone(String dictionaryKey,
                                                                                String timezone)  {

        dictionaryKey = parseDictionaryKey(dictionaryKey);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mma").withLocale(Locale.ENGLISH);
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of(timezone));
        String valToStore = dateFormat.format(zonedDateTime);

        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the current system date and time into the data dictionary with key \"([^\"]*)\" and \"([^\"]*)\" timezone \"([^\"]*)\"$")
    public void storeTheCurrentSystemDateAndTimeIntoTheDataDictionaryWithKeyAndTimezone(String dateDictionaryKey,
                                                                                        String timeDictionaryKey,
                                                                                        String timezone)  {
        dateDictionaryKey = parseDictionaryKey(dateDictionaryKey);
        timeDictionaryKey = parseDictionaryKey(timeDictionaryKey);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.ENGLISH);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma").withLocale(Locale.ENGLISH);

        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of(timezone));

        String valToStore = dateFormat.format(zonedDateTime);
        TestContext.getInstance().testdataPut(dateDictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dateDictionaryKey));

        valToStore = timeFormat.format(zonedDateTime);
        TestContext.getInstance().testdataPut(timeDictionaryKey, timeFormat.format(zonedDateTime));
        logStepMessage(String.format(STORED_VALUE, valToStore, timeDictionaryKey));
    }

    @When("^store text of the cell having unique rowVal \"([^\"]*)\" and columnHeader \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTextOfTheCellHavingUniqueRowValAndColumnHeaderFromTheTableAtThePageIntoTheDataDictionaryWithKey(String rowValue,
                                                                                                                     String colHeader,
                                                                                                                     String tableName,
                                                                                                                     String pageName,
                                                                                                                     String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(tableName, pageName).findMatchingCellinTable(rowValue,
                                                                                   colHeader).getText();

        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }
    @When("^store text of the cell having unique rowVal comes from Data Dictionary \"([^\"]*)\" and columnHeader \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTextOfTheCellHavingUniqueRowValAsDataDictionaryAndColumnHeaderFromTheTableAtThePageIntoTheDataDictionaryWithKey(String rowValue,
                                                                                                                                     String colHeader,
                                                                                                                                     String tableName,
                                                                                                                                     String pageName,
                                                                                                                                     String dictionaryKey)  {
        rowValue=parseValue(rowValue);
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = getObject(tableName, pageName).findMatchingCellinTable(rowValue,
                colHeader).getText();

        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store text of the matching cell in the column \"([^\"]*)\" where the column \"([^\"]*)\" contains unique value \"([^\"]*)\" in the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTextOfTheMatchingCellInTheColumnWhereTheColumnContainsUniqueInTheTableAtThePageIntoTheDataDictionaryWithKey(String colHeader1,
                                                                                                                                 String colHeader2,
                                                                                                                                 String colValue2,
                                                                                                                                 String tableName,
                                                                                                                                 String pageName,
                                                                                                                                 String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        colValue2 = parseValue(colValue2);
        String valToStore = getObject(tableName, pageName).findMatchingCellValueinTableForGivenColumnHeaderAndValue(colValue2,
                                                                                                                    colHeader2,
                                                                                                                    colHeader1).getText();
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
        logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store the concatenated string of \"([^\"]*)\" and the \"([^\"]*)\" element value at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeConcatenatedValueandElementValue(String valsToConcat,
                                                      String objectName,
                                                      String pageName,
                                                      String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        valsToConcat = String.join("|", valsToConcat, getTextFromElement(getObject(objectName, pageName)));
        String combinedString = getConcatenatedVal(valsToConcat, "\\|", " ");

        TestContext.getInstance().testdataPut(dictionaryKey, combinedString);
        logStepMessage(String.format(STORED_VALUE, combinedString, dictionaryKey));
    }


    @When("^store the concatenated value of \"([^\"]*)\" into the data dictionary with key \"([^\"]*)\"$")
    public void storeConcatenatedValue(String valsToConcat,
                                       String dictionaryKey)  {
        storeConcatenatedValueWithDelimeter(valsToConcat, " ", dictionaryKey);
    }

    @When("^store the concatenated value of \"([^\"]*)\" with delimiter \"([^\"]*)\" into the data dictionary with key \"([^\"]*)\"$")
    public void storeConcatenatedValueWithDelimeter(String valsToConcat,
                                                    String joinDelimiter,
                                                    String dictionaryKey)  {
        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String combinedString = getConcatenatedVal(valsToConcat, "\\|", joinDelimiter);
        TestContext.getInstance().testdataPut(dictionaryKey, combinedString);
        logStepMessage(String.format(STORED_VALUE, combinedString, dictionaryKey));
    }

    @When("^store substring \"([^\"]*)\" of the data dictionary value at \"([^\"]*)\" into the data dictionary with key \"([^\"]*)\"$")
    public void storeSubstringVal(String substringPattern,
                                  String dictionaryKeyToSubstring,
                                  String dictionaryKeyToStore)  {
        dictionaryKeyToSubstring = parseDictionaryKey(dictionaryKeyToSubstring);
        dictionaryKeyToStore = parseDictionaryKey(dictionaryKeyToStore);
        Object dictionaryValToSubstring = TestContext.getInstance().testdataGet(dictionaryKeyToSubstring);

        if(dictionaryValToSubstring != null) {
            String subStringVal = getSubstringVal(TestContext.getInstance().testdataGet(dictionaryKeyToSubstring).toString(),
                                                  substringPattern);
            TestContext.getInstance().testdataPut(dictionaryKeyToStore, subStringVal);
            logStepMessage(String.format(STORED_VALUE, subStringVal, dictionaryKeyToStore));
        } else {
            log.warn("Value not found at dictionary key: {}. Skipping substring.", dictionaryKeyToSubstring);
        }
    }
    @When("^store text from the corresponding cell in all rows of the \"([^\"]*)\" column in the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTextOfAllTheCellInTheColumnInTheTableAtThePageIntoTheDataDictionaryWithKey(String colHeader,
           String tableName, String pageName, String dictionaryKey)  {
       dictionaryKey = parseDictionaryKey(dictionaryKey);
       List<String> valToStore = getObject(tableName, pageName).getRowValuesForGivenColumn(colHeader);
       TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
       logStepMessage(String.format(STORED_VALUE, valToStore, dictionaryKey));
    }

    @When("^store xmlValue present in the attribute \"([^\"]*)\" of the data dictionary value at \"([^\"]*)\" into the data dictionary with key \"([^\"]*)\"$")
    public void storeXMLValuePresentInTheAttributeOfTheDataDictionaryValueAtIntoTheDataDictionaryWithKey(String attributeName,
                                                                                                          String storedDictionaryKey,
                                                                                                          String dictionaryKeyToStore) throws IOException, SAXException, ParserConfigurationException {
        storedDictionaryKey = parseDictionaryKey(storedDictionaryKey);
        dictionaryKeyToStore = parseDictionaryKey(dictionaryKeyToStore);
        String valToStore = getXMLAttributeVal(TestContext.getInstance().testdataGet(storedDictionaryKey).toString(), attributeName);
        TestContext.getInstance().testdataPut(dictionaryKeyToStore, valToStore);
        logStepMessage(String.format(STORED_VALUE, attributeName, dictionaryKeyToStore));
    }
    
    @When("^stores list of values from column \"([^\"]*)\" and \"([^\"]*)\" where the \"([^\"]*)\" column contains \"([^\"]*)\" and \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page into the data dictionary with key \"([^\"]*)\"$")
    public void storeTextOfTheMatchingCellInTheColumnWhereTheColumnContainsUniqueInTheTableAtThePageIntoTheDataDictionaryWithKey(String colHeader1,
                                                                                                                                 String colHeader2,
                                                                                                                                 String colHeader3,
                                                                                                                                 String colValue3,
                                                                                                                                 String colHeader4,
                                                                                                                                 String colValue4,
                                                                                                                                 String tableName,
                                                                                                                                 String pageName,
                                                                                                                                 String dictionaryKey) {
        colValue3 = parseValue(colValue3);
        colValue4 = parseValue(colValue4);
        dictionaryKey = parseDictionaryKey(dictionaryKey);

        int rowNumber = getObject(tableName, pageName).getMatchingRowNumber(colHeader3,colValue3,colHeader4,colValue4);
        List<String> colName1 = getObject(tableName, pageName).getTopNineSubRowValuesForGivenColumn(colHeader1, rowNumber);
        List<String> colName2 = getObject(tableName, pageName).getTopNineSubRowValuesForGivenColumn(colHeader2,rowNumber);
        Map<String,String> map =new LinkedHashMap<>();
        for (int i=2;i<colName1.size();i++) {
            map.put(colName1.get(i), colName2.get(i));
        }
        TestContext.getInstance().testdataPut(dictionaryKey, map);
        logStepMessage(String.format(STORED_VALUE, map, dictionaryKey));
    }
    
    @When("^the user loads user credential into the data dictionary at key \"([^\"]*)\"$")
    public void theUserLoadsUserCredIntoDataDictionary(String dictionaryKey)  {

        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = parseUser(dictionaryKey);
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
    }

    @When("^the user loads secure credential into the data dictionary at key \"([^\"]*)\"$")
    public void theUserLoadsSecureCredIntoDataDictionary(String dictionaryKey)  {

        dictionaryKey = parseDictionaryKey(dictionaryKey);
        String valToStore = parseSecureText(dictionaryKey);
        TestContext.getInstance().testdataPut(dictionaryKey, valToStore);
    }
}
