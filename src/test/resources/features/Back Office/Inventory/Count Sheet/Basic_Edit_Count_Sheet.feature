Feature: Basic Edit Count Sheet
  This script is to validate Edit Count Sheet

  @Basic_Edit_Count_Sheet @RegressionSuite @BO_Count_Sheet @Back_Office
  Scenario: Basic_Edit_Count_Sheet_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewWindow"
    #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key_AutomationStore)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key_AutomationStore)" into the "stationKey" textbox at the "LoginPage" page
    #Comment: user click On the Connect Button
    And the user clicks the "connect" element at the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: Enter the Employee_Id into username textbox present on Login Page
    When the user enters the user credential "#(Employee_Id)" into the "employee_Id" textbox at the "LoginPage" page
    #Comment: Enter the Password into Password textbox present on Login Page
    When the user enters the secure credential "#(Password)" into the "password" textbox at the "LoginPage" page
    #Comment: The user enter at passsword field
    And the user sends keys "Key_enter" to the "password" element on the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click On the continueToLogin Button
    And the user clicks the "continueToLogin" element at the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user validate the Title of the page
    And the user validates that the page title "Equal To" "Adora" "validate_Title" "HardStopOnFailure"
    #Comment: the user click on back office
    And the user clicks the "backOffice" element at the "AdoraHeaderPage" page
    #Comment: the user click Discounts
    And the user clicks the "inventory" element at the "InventoryPage" page
    #Comment: the user click on Menu Items
    And the user clicks the "countSheet" element at the "InventoryPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment : the user clicks and check If element present
    And the user custom clicks If element present the "table" element with Column Name " Frequency" and value "tblRowXIN_CNT" at the "CountSheetPage" page with element 1 "delete" and element 2 "deleteOnWarning"
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "CountSheetPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "countSheetTxtPopup" element to be "VISIBLE" on the "AddCountSheetPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Weekly" from the "frequency" dropdown at the "AddCountSheetPage" page
    #Comment: the user validate the drop down value selected
    And the user selects value from the "frequency" dropdown equal to given value "Weekly" at the "AddCountSheetPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Freezer" from the "location" dropdown at the "AddCountSheetPage" page
    #Comment: the user validate the drop down value selected
    And the user selects value from the "location" dropdown equal to given value "Freezer" at the "AddCountSheetPage" page
    #Comment: the user click on checkbox
    And the user clicks the "allItems" element at the "AddCountSheetPage" page
    #Comment: the user validate check box selected
    And the user validates the item in the "allItems" checkbox is checked at the "AddCountSheetPage" page "validate_checkbox_Selected" "HardStopOnFailure"
    #Comment: the user click on checkbox
    And the user clicks the "items" element at the "AddCountSheetPage" page
    #Comment: the user validate check box selected
    And the user validates the item in the "items" checkbox is checked at the "AddCountSheetPage" page "validate_checkbox_Selected" "HardStopOnFailure"
    #Comment: the user click save
    And the user clicks the "save" element at the "AddCountSheetPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(Weekly)" at the "CountSheetPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "CountSheetPage" page and get the dictionary key value "#(Weekly)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "CountSheetPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "countSheetTxtPopup" element to be "VISIBLE" on the "EditCountSheetPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Yearly" from the "frequency" dropdown at the "AddCountSheetPage" page
    #Comment: the user validate the drop down value selected
    And the user selects value from the "frequency" dropdown equal to given value "Yearly" at the "EditCountSheetPage" page
    #Comment: the user validate the drop down value selected
    And the user selects value from the "location" dropdown equal to given value "Freezer" at the "EditCountSheetPage" page
    #Comment: the user validate check box selected
    And the user validates the item in the "items" checkbox is checked at the "EditCountSheetPage" page "validate_checkbox_Selected" "HardStopOnFailure"
    #Comment: the user click save
    And the user clicks the "save" element at the "EditCountSheetPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(Yearly)" at the "CountSheetPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "CountSheetPage" page and get the dictionary key value "#(Yearly)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "CountSheetPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "countSheetTxtPopup" element to be "VISIBLE" on the "EditCountSheetPage" page
    #Comment: the user validate the drop down value selected
    And the user selects value from the "frequency" dropdown equal to given value "Yearly" at the "EditCountSheetPage" page
    #Comment: the user validate check box selected
    And the user validates the item in the "items" checkbox is checked at the "EditCountSheetPage" page "validate_checkbox_Selected" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditCountSheetPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "CountSheetPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(Id_Number)" and store with new dictionary key "Id_Number_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Edit']//following-sibling::td)[5]" with data dictionary key "#(Id_Number_Latest)" and store with new dictionary key "Id_Number_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(Id_Number_Latest_1)"
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Frequency" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(Weekly)" "validate_InvoiceNumber_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(Yearly)" "validate_InvoiceNumber_Details" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(Yearly)" at the "CountSheetPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"

