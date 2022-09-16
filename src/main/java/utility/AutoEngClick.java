package utility;

import core.BaseWebSteps;
import core.Element;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoEngClick extends BaseWebSteps {
    private static final String CLICKED_VALUE = "Clicked value: \"%s\"";
    private static final String CLICKING_ELEMENT = "Clicking element: {}";
    private static final String OUTER_HTML = "outerHTML";

    @When("^the user clicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserClicksTheElementAtThePage(String objectName,
                                                 String pageName) {
        getObject(objectName, pageName).click();
    }

    @When("^the user disable the screenshot of every step due to notification popup$")
    public void theUserDisableScreenShotForEveryStep() {
        setRuntimeProperties(false);
    }

    @When("^the user enable the screenshot of every step due to notification popup$")
    public void theUserEnableScreenShotForEveryStep() {
        setRuntimeProperties(true);
    }

    @When("^the user clicks JS the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserClicksJSTheElementAtThePage(String objectName,
                                                   String pageName) {
        getObject(objectName, pageName).clickJS();
    }

    @When("^the user custom clicks the \"([^\"]*)\" element with order number \"([^\"]*)\" at the \"([^\"]*)\" page$")
    public void theUserCustomClicksTheElementAtThePage(String objectName,
                                                       String orderNum,
                                                       String pageName) {
        orderNum = parseValue(orderNum);
        getObject(objectName, pageName).customClick(orderNum);
    }

    @When("^the user custom clicks dispatch the \"([^\"]*)\" element with order number \"([^\"]*)\" at the \"([^\"]*)\" page$")
    public void theUserCustomClicksDispatchTheElementAtThePage(String objectName,
                                                               String orderNum,
                                                               String pageName) {
        orderNum = parseValue(orderNum);
        getObject(objectName, pageName).customClickDispatch(orderNum);
    }

    @When("^the user clicks the \"([^\"]*)\" element with dictionary key \"([^\"]*)\" at the \"([^\"]*)\" page with xpath1 \"([^\"]*)\" and xpath2 \"([^\"]*)\"$")
    public void theUserClicksBasedOnXpathDictKeyTheElementAtThePage(String objectName,
                                                                    String dictionaryKey,
                                                                    String pageName,
                                                                    String xpath1,
                                                                    String xpath2) {
        dictionaryKey = parseValue(dictionaryKey);
        xpath1 = parseValue(xpath1);
        xpath2 = parseValue(xpath2);
        getObject(objectName, pageName).clickBasedOnXpath(dictionaryKey, xpath1, xpath2);
    }

    @When("^the user custom clicks If element present the \"([^\"]*)\" element with Column Name \"([^\"]*)\" and value \"([^\"]*)\" at the \"([^\"]*)\" page with element 1 \"([^\"]*)\" and element 2 \"([^\"]*)\" element 3 \"([^\"]*)\"$")
    public void theUserClicksIfElementPresentInTableAtThePage(String table,
                                                              String colName,
                                                              String value,
                                                              String pageName,
                                                              String objectName1,
                                                              String objectName2,
                                                              String objectName3) {
        colName = parseValue(colName);
        value = parseValue(value);
        List<String> allRowsWithValue = getObject(table, pageName).getRowValuesForGivenColumn(colName);
        if (!allRowsWithValue.isEmpty()) {
            List<WebElement> element = getDriver().findElements(By.xpath("//tr[contains(@id,'" + value + "')]"));
            try {
                for (WebElement el : element) {
                    el.click();
                    getObject(objectName1, pageName).click();
                    getObject(objectName2, pageName).visible();
                    getObject(objectName2, pageName).click();
                    getObject(objectName3, pageName).visible();
                }
            } catch (Exception e) {
                theUserClicksIfElementPresentInTableAtThePage(table, colName, value, pageName, objectName1, objectName2, objectName3);
            }
        } else {
            System.out.println("Row is empty");
        }

    }

    @When("^the user delete all rows If element present the \"([^\"]*)\" table with Column Name \"([^\"]*)\" and value \"([^\"]*)\" at the \"([^\"]*)\" page with FirstObjectName \"([^\"]*)\" and SecondObjectName \"([^\"]*)\" ThirdObjectName \"([^\"]*)\"$")
    public void theUserDeleteAllRowsElementPresentInTableAtThePage(String table,
                                                                   String colName,
                                                                   String value,
                                                                   String pageName,
                                                                   String FirstObjectName,
                                                                   String SecondObjectName,
                                                                   String ThirdObjectName) {
        colName = parseValue(colName);
        value = parseValue(value);
        List<String> allRowsWithValue = getObject(table, pageName).getRowValuesForGivenColumn(colName);
        if (!allRowsWithValue.isEmpty()) {
            List<WebElement> element = getDriver().findElements(By.xpath(value));
            if (element.isEmpty()) {
                System.out.println("Element is not found in table");
            } else {
                try {
                    for (WebElement el : element) {
                        el.isDisplayed();
                        WebElement elementRow = getDriver().findElement(By.xpath(value + "/../../../.."));
                        elementRow.click();
                        getObject(FirstObjectName, pageName).click();
                        getObject(SecondObjectName, pageName).visible();
                        getObject(SecondObjectName, pageName).click();
                        getObject(ThirdObjectName, pageName).visible();
                    }
                } catch (Exception e) {
                    theUserDeleteAllRowsElementPresentInTableAtThePage(table, colName, value, pageName, FirstObjectName, SecondObjectName, ThirdObjectName);
                }
            }

        } else {
            System.out.println("Row is empty");
        }

    }

    @When("^the user clicks the \"([^\"]*)\" element at the \"([^\"]*)\" page with xpath1 \"([^\"]*)\" and xpath2 \"([^\"]*)\"$")
    public void theUserClicksBasedOnXpathTheElementAtThePage(String objectName,
                                                             String pageName,
                                                             String xpath1,
                                                             String xpath2) {
        xpath1 = parseValue(xpath1);
        xpath2 = parseValue(xpath2);
        getObject(objectName, pageName).clickBasedOnOnlyXpath(xpath1, xpath2);
    }

    @When("^the user doubleclicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserDoubleclicksTheElementAtThePage(String objectName,
                                                       String pageName) {
        getObject(objectName, pageName).doubleClick();
    }

    @When("^the user rightclicks the \"([^\"]*)\" element at the \"([^\"]*)\" page$")
    public void theUserRightclicksTheElementAtThePage(String objectName,
                                                      String pageName) {
        getObject(objectName, pageName).rightClick();
    }

    @When("^the user clicks on \"([^\"]*)\" on the \"([^\"]*)\" checkbox at the \"([^\"]*)\" page$")
    public void theUserClicksOnOnTheCheckboxAtThePage(String valueToClick,
                                                      String objectName,
                                                      String pageName) {

        valueToClick = parseValue(valueToClick);
        List<Element> checkbox = getObjects(objectName, pageName);

        for (Element checkboxItem : checkbox) {
            if (checkboxItem.findMatchingCheckboxValToClick(valueToClick)) {
                checkboxItem.click();
                logStepMessage(String.format(CLICKED_VALUE, valueToClick));
            } else {
                checkboxItem.click();
            }
        }
    }

    @When("^the user clicks the header of column \"([^\"]*)\" from the \"([^\"]*)\" table on the \"([^\"]*)\" page$")
    public void theUserClicksTheHeaderOfColumnFromTheTableOnThePage(String colNum,
                                                                    String tableName,
                                                                    String pageName) {
        getObject(tableName, pageName).getHeadCellElement(0, Integer.parseInt(colNum)).click();
    }

    @When("^the user clicks on the cell at row \"([^\"]*)\" and column \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheCellAtRowAndColumnFromTheTableAtThePage(String rowNum,
                                                                          String colNum,
                                                                          String tableName,
                                                                          String pageName) {
        getObject(tableName, pageName).getDataCellElement(Integer.parseInt(rowNum), Integer.parseInt(colNum)).click();
    }

    @When("^the user clicks on row \"([^\"]*)\" from the \"([^\"]*)\" table on the \"([^\"]*)\" page$")
    public void theUserClicksOnRowFromTheTableOnThePage(String rowNum,
                                                        String tableName,
                                                        String pageName) {
        getObject(tableName, pageName).getRow(Integer.parseInt(rowNum)).click();
    }

    @When("^the user custom clicks on row with order number \"([^\"]*)\" and category value \"([^\"]*)\" from the \"([^\"]*)\" table on the \"([^\"]*)\" page$")
    public void theUserCustomClicksOnRowFromTheTableOnThePage(String orderNum,
                                                              String categoryValue,
                                                              String tableName,
                                                              String pageName) {
        orderNum = parseValue(orderNum);
        categoryValue = parseValue(categoryValue);
        getObject(tableName, pageName).getRowValue(orderNum, categoryValue).click();
    }

    @When("^the user clicks \"([^\"]*)\" element on the \"([^\"]*)\" page if the \"([^\"]*)\" meets \"([^\"]*)\"$")
    public void theUserClicksElementOnThePageIfTheMeets(String objectName,
                                                        String pageName,
                                                        String secondObjectName,
                                                        String expectedCondition) {

        switch (expectedCondition.toUpperCase()) {
            case "CLICKABLE":
                if (getObject(secondObjectName, pageName).clickable() != null)
                    getObject(objectName, pageName).click();
                break;
            case "VISIBLE":
                if (getObject(secondObjectName, pageName).visible() != null)
                    getObject(objectName, pageName).click();
                break;
            case "HIDDEN":
                if (getObject(secondObjectName, pageName).invisible() != null)
                    getObject(objectName, pageName).click();
                break;
            case "DISPLAYED":
                if (getObject(secondObjectName, pageName).displayed() != null)
                    getObject(objectName, pageName).click();
                break;
            case "ENABLED":
                if (getObject(secondObjectName, pageName).enabled() != null)
                    getObject(objectName, pageName).click();
                break;
            case "DISABLED":
                if (getObject(secondObjectName, pageName).disabled() != null)
                    getObject(objectName, pageName).click();
                break;
            default:
                log.warn("Expected condition not found: {}. Options are CLICKABLE | VISIBLE | HIDDEN | DISPLAYED | ENABLED | DISABLED", expectedCondition);
        }
    }

    @When("^the user clicks on the cell at row \"([^\"]*)\"  from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheCellAtRowFromTheTableAtThePage(String rowNum,
                                                                 String tableName,
                                                                 String pageName) {
        getObject(tableName, pageName).getRow(Integer.parseInt(rowNum)).click();
    }

    @When("^the user clicks on the cell at row with \"([^\"]*)\" \"([^\"]*)\"  and column with \"([^\"]*)\" \"([^\"]*)\"  from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheCellAtRowWithAndColumnWithFromTheTableAtThePage(String rowIdentifier,
                                                                                  String rowVal,
                                                                                  String colIdentifier,
                                                                                  String colVal,
                                                                                  String tableName,
                                                                                  String pageName) {
        rowVal = parseValue(rowVal);
        colVal = parseValue(colVal);

        Element table = getObject(tableName, pageName);
        if (table.getMatchingRows(rowIdentifier, rowVal).size() == 1 && table.getMatchingColumns(colIdentifier, colVal).size() == 1) {
            table.getDataCellElement(table.getMatchingRows(rowIdentifier, rowVal).iterator().next(), table.getMatchingColumns(colIdentifier, colVal).iterator().next()).click();
        } else {
            log.warn("Could not find uniue row with {} having {} and {} having {}", rowIdentifier, rowVal, colIdentifier, colVal);
        }
    }

    @And("^the user clicks on \"([^\"]*)\" on the \"([^\"]*)\" radiobutton at the \"([^\"]*)\" page$")
    public void theUserClicksOnOnTheRadiobuttonAtThePage(String rdoBtnVal,
                                                         String objectName,
                                                         String pageName) {
        rdoBtnVal = parseValue(rdoBtnVal);

        final List<Element> radios = getObjects(objectName, pageName);
        for (Element radio : radios) {
            if (radio.getText().equalsIgnoreCase(rdoBtnVal)) {
                radio.click();
                logStepMessage(String.format(CLICKED_VALUE, rdoBtnVal));
            }
        }
    }

    @When("^the user clicks on the matching cell in the \"([^\"]*)\" column containing \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheMatchingCellInTheColumnContainsFromTheTableAtThePage(String firstColName,
                                                                                       String firstColValue,
                                                                                       String tableName,
                                                                                       String pageName) {
        Element tableCellToClick = findMatchingTableCell(firstColName, parseValue(firstColValue),
                getObject(tableName, pageName));

        if (tableCellToClick != null && tableCellToClick.element() != null) {
            log.debug(CLICKING_ELEMENT, tableCellToClick.getAttribute(OUTER_HTML));
            tableCellToClick.click();
        } else
            log.warn("Could not find unique row with {} having {}", firstColName, firstColValue);

    }

    @When("^the user clicks on the customize matching cell in the \"([^\"]*)\" column containing \"([^\"]*)\" with sequence \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheMatchingCustomizeCellInTheColumnContainsFromTheTableAtThePage(String firstColName,
                                                                                                String firstColValue,
                                                                                                String orderSeq,
                                                                                                String tableName,
                                                                                                String pageName) {

        firstColValue = parseValue(firstColValue);
        orderSeq = parseValue(orderSeq);
        firstColValue = firstColValue + " - " + orderSeq;
        Element tableCellToClick = findMatchingTableCell(firstColName, firstColValue,
                getObject(tableName, pageName).click());

//        if (tableCellToClick != null && tableCellToClick.element() != null) {
//            log.debug(CLICKING_ELEMENT, tableCellToClick.getAttribute(OUTER_HTML));
//            tableCellToClick.click();
//        } else
//            log.warn("Could not find unique row with {} having {}", firstColName, firstColValue);

    }

    @When("^the user clicks on the matching cell in the \"([^\"]*)\" column containing \"([^\"]*)\" and where the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheMatchingCellInTheColumnContainingAndWhereTheColumnContainsFromTheTableAtThePage(String firstColName,
                                                                                                                  String firstColValue,
                                                                                                                  String secondColName,
                                                                                                                  String secondColValue,
                                                                                                                  String tableName,
                                                                                                                  String pageName) {
        Element tableCellToClick = findMatchingTableCell(firstColName, parseValue(firstColValue),
                secondColName, parseValue(secondColValue),
                getObject(tableName, pageName));

        if (tableCellToClick != null && tableCellToClick.element() != null) {
            log.debug(CLICKING_ELEMENT, tableCellToClick.getAttribute(OUTER_HTML));
            tableCellToClick.clickJS();
        } else
            log.warn("Could not find unique row with {} having {} and {} having {}", firstColName, firstColValue, secondColName, secondColValue);

    }

    @When("^the user clicks on the matching cell in the \"([^\"]*)\" column containing \"([^\"]*)\" where the \"([^\"]*)\" column contains \"([^\"]*)\" and the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheMatchingCellInTheColumnContainingWhereTheColumnContainsAndTheColumnContainsFromTheTableAtThePage(
            String firstColName,
            String firstColValue,
            String secondColName,
            String secondColValue,
            String thirdColName,
            String thirdColValue,
            String tableName,
            String pageName) {

        Element tableCellToClick = findMatchingTableCell(firstColName, parseValue(firstColValue),
                secondColName, parseValue(secondColValue),
                thirdColName, parseValue(thirdColValue),
                getObject(tableName, pageName));

        if (tableCellToClick != null && tableCellToClick.element() != null) {
            log.debug(CLICKING_ELEMENT, tableCellToClick.getAttribute(OUTER_HTML));
            tableCellToClick.click();
        } else
            log.warn("Could not find unique row with {} having {} where {} is {} and {} is {}",
                    firstColName, firstColValue, secondColName, secondColValue, thirdColName, thirdColValue);

    }

    @When("^the user clicks on the cell link having unique rowVal \"([^\"]*)\" at columnHeader \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheCellLinkHavingUniqueRowValAtColumnHeaderFromTheTableAtThePage(String rowValue,
                                                                                                String colHeader,
                                                                                                String tableName,
                                                                                                String pageName) {
        rowValue = parseValue(rowValue);
        getObject(tableName, pageName).findMatchingAnchorLinkCellinTable(rowValue, colHeader).click();
    }

    @When("^the user clicks on the cell link having unique rowVal from data dictionary \"([^\"]*)\" with sequence \"([^\"]*)\" at columnHeader \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserClicksOnTheCellLinkHavingUniqueRowValFromDataDictionaryAtColumnHeaderFromTheTableAtThePage(String rowValue,
                                                                                                                  String orderSeq,
                                                                                                                  String colHeader,
                                                                                                                  String tableName,
                                                                                                                  String pageName) {
        orderSeq = parseValue(orderSeq);
        rowValue = parseValue(rowValue);
        rowValue = rowValue + " - " + orderSeq;
        getObject(tableName, pageName).findMatchingAnchorLinkCellinTable(rowValue, colHeader).click();
    }

    @When("the user clicks all matching \"([^\"]*)\" elements on the \"([^\"]*)\" page")
    public void theUserClicksAllMatchingElementsOnThePage(String objectName,
                                                          String pageName) {
        List<Element> listOfObjects = getObjects(objectName, pageName);

        listOfObjects.forEach(object -> object.click());
    }

    @When("^the user clicks the \"([^\"]*)\" button on the matching cell where the \"([^\"]*)\" column contains \"([^\"]*)\" from the \"([^\"]*)\" table at the \"([^\"]*)\" page$")
    public void theUserSelectsTheRadioButtonInTheMatchingCellWhereTheColumnContainsFromTheTableAtThePage(String buttoText,
                                                                                                         String firstColName,
                                                                                                         String firstColValue,
                                                                                                         String tableName,
                                                                                                         String pageName) {

        Element matchingRowCell = findMatchingTableCell(firstColName, parseValue(firstColValue),
                getObject(tableName, pageName));

        if (matchingRowCell != null && matchingRowCell.element() != null) {
            Element buttonToClick = matchingRowCell.findButtonInTableRow(buttoText);

            if (buttonToClick != null) {
                buttonToClick.click();
            } else {
                throw new NotFoundException("Unable to find a matching button in this table row");
            }
        } else
            log.warn("Could not find unique row with {} having {} and {} having {} and {} having {}",
                    firstColName, firstColValue);
    }

    @When("^the user clicks on the \"([^\"]*)\" element until the \"([^\"]*)\" element value matches \"([^\"]*)\" on the page \"([^\"]*)\"$")
    public void theUserClicksOnTheElementUntilTheElementValueMatchesOnThePage(String elementToClick,
                                                                              String objectName,
                                                                              String expectedValue,
                                                                              String pageName) {
        expectedValue = parseValue(expectedValue);
        Element eleToClick = getObject(elementToClick, pageName);
        Element matchingEle = getObject(objectName, pageName);
        if (matchingEle != null && matchingEle.element() != null) {
            if (eleToClick != null && eleToClick.element() != null) {
                while (!matchingEle.getText().equalsIgnoreCase(expectedValue)) {
                    eleToClick.click();
                }
            } else {
                log.warn("Matching Element to be clicked not found: {}", eleToClick);
            }
        } else {
            log.warn("Element to be matched not found: {}", matchingEle);
        }
    }

    @When("^the user clicks the cell of column \"([^\"]*)\" of the \"([^\"]*)\" table on the \"([^\"]*)\" page where the \"([^\"]*)\" column has a row containing \"([^\"]*)\"$")
    public void theUserClicksTheCellOfColumnOfTheTableOnThePageWhereTheColumnHasARowContaining(String firstColNum,
                                                                                               String tableName,
                                                                                               String pageName,
                                                                                               String secondColName,
                                                                                               String secondColValue) {

        Element table = getObject(tableName, pageName);
        int matchingRowNumber = table.getMatchingRowNumber(secondColName, parseValue(secondColValue));
        if (matchingRowNumber != -1) {
            table.getDataCellElement(matchingRowNumber, Integer.parseInt(firstColNum)).click();

        } else
            log.warn("Could not find row with {} having {}", secondColName, secondColValue);
    }


    @When("^the user clicks the \"([^\"]*)\" element and closes the pop up at the \"([^\"]*)\" page$")
    public void theUserClicksTheElementAndClosesPopUpAtThePage(String objectName,
                                                               String pageName) {
        getObject(objectName, pageName).click();
    }

}
