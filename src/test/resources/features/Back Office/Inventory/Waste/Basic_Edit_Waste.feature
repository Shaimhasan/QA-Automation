Feature: Basic Edit Waste
  This script is to validate Edit Waste

  @Basic_Edit_Waste @RegressionSuite @BO_Waste @Inventory @Back_Office @Smoke_Testing
  Scenario: Basic_Edit_Waste_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewWindow"
     #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key_AutomationStore)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key_AutomationStore)" into the "stationKey" textbox at the "LoginPage" page
    #Comment: user click On the Connect Button
    And the user clicks the "connect" element at the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits Jquery for the page to load
     #Comment: Enter the Employee_Id into username textbox present on Login Page
    When the user enters the user credential "#(Employee_Id)" into the "employee_Id" textbox at the "LoginPage" page
    #Comment: Enter the Password into Password textbox present on Login Page
    When the user enters the secure credential "#(Password)" into the "password" textbox at the "LoginPage" page
    #Comment: The user enter at passsword field
    And the user sends keys "Key_enter" to the "password" element on the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: user click On the continueToLogin Button
    And the user clicks the "continueToLogin" element at the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user validate the Title of the page
    And the user validates that the page title "Equal To" "Adora" "validate_Title" "HardStopOnFailure"
    #Comment: the user click on back office
    And the user clicks the "backOffice" element at the "AdoraHeaderPage" page
    #Comment: the user click Discounts
    And the user clicks the "inventory" element at the "InventoryPage" page
    #Comment: the user click vendors
    And the user clicks the "waste" element at the "InventoryPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "wasteTxt" element to be "VISIBLE" on the "WastePage" page
    #Comment: the user delete all waste if available
    And the user custom clicks All If element present the "table" element with Column Name "Item" and value "tblRowXIN_WST" at the "WastePage" page with element 1 "delete" and element 2 "deleteOnWarning" element 3 "editBtnDisable"
    #Comment: the user click Add vendors
    And the user clicks the "addBtn" element at the "WastePage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "addWasteTxt" element to be "VISIBLE" on the "AddWastePage" page
    #Comment: the user enter date
    And the user enters "#(wasteDate)" into the "wasteDate" textbox at the "AddWastePage" page
    #Comment: the user enter the item
    And the user enters "#(item)" into the "item" textbox at the "AddWastePage" page
    #Comment: the user select the item
    And the user clicks the "itemSelect" element at the "AddWastePage" page
    #Comment: the user enter date
    And the user enters "#(quantity1)" into the "quantity1" textbox at the "AddWastePage" page
    #Comment: the user enter date
    And the user enters "#(quantity2)" into the "quantity2" textbox at the "AddWastePage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "AddWastePage" page
    #Comment : the User wait to page load
    And the user waits for the dom to load

    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(item)" at the "WastePage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "WastePage" page and get the dictionary key value "#(item)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click Add vendors
    And the user clicks the "editBtn" element at the "WastePage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "addWasteTxt" element to be "VISIBLE" on the "EditWastePage" page

    #Comment: the user enter date
    And the user enters "#(quantity3)" into the "quantity2" textbox at the "EditWastePage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "EditWastePage" page
    #Comment: the user wait page to load
    And the user waits for the dom to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(item)" at the "WastePage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    #Comment: the user click Add vendors
    And the user clicks the "editBtn" element at the "WastePage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "addWasteTxt" element to be "VISIBLE" on the "EditWastePage" page

    #Comment: the user validate the data
    And the user validates "Compare_Strings" that the "item" element is "Equal To" "#(item1)" at the "EditWastePage" page "validate_data" "HardStopOnFailure"
    #Comment: the user validate the data
    And the user validates "Compare_Strings" that the "quantity1" element is "Equal To" "#(quantity1)" at the "EditWastePage" page "validate_data" "HardStopOnFailure"
    #Comment: the user validate the data
    And the user validates "Compare_Strings" that the "quantity2" element is "Equal To" "#(quantity3)" at the "EditWastePage" page "validate_data" "HardStopOnFailure"
    #Comment: the user validate the data
    And the user validates "Compare_Strings" that the "totalWaste" element is "Equal To" "#(totalWaste70)" at the "EditWastePage" page "validate_data" "HardStopOnFailure"
    #Comment : the User click on Cancel
    And the user clicks the "cancel" element at the "EditWastePage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "VendorsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(item)" and store with new dictionary key "item_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "'])[1]//preceding-sibling::td[text()='Edit']//following-sibling::td)[5]" with data dictionary key "#(item_value1_Latest)" and store with new dictionary key "item_value2_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(item_value2_Latest_1)"

    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Unit Quantity" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(quantity2)" "validate_InvoiceNumber_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(quantity3)" "validate_InvoiceNumber_Details" "HardStopOnFailure"

    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Total Waste" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(totalWaste)" "validate_InvoiceNumber_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(totalWaste70)" "validate_InvoiceNumber_Details" "HardStopOnFailure"

    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(item)" at the "WastePage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "WastePage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "WastePage" page