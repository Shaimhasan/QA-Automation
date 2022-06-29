Feature: Basic Add Receiving Items
  This script is to validate Add Receiving Items

  @Basic_Add_Receiving_Items @RegressionSuite @BO_Receiving_Items @Inventory @Back_Office
  Scenario: Basic_Add_Receiving_Items_Testcase
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
    And the user clicks the "receivingItems" element at the "InventoryPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "ReceivingItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "receivingItemText" element to be "VISIBLE" on the "AddReceivingItemsPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters dynamic UserName "#(name)" into the "name" textbox at the "AddReceivingItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "name" element at the "AddReceivingItemsPage" page into the data dictionary with key "name_value1"
    #Comment: the user enter text
    And the user enters "#(yieldFactor)" into the "yieldFactor" textbox at the "AddReceivingItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "yieldFactor" element at the "AddReceivingItemsPage" page into the data dictionary with key "yieldFactor_value1"
    #Comment: the user enter text
    And the user enters "#(InvUnitConFactor)" into the "InvUnitConFactor" textbox at the "AddReceivingItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "InvUnitConFactor" element at the "AddReceivingItemsPage" page into the data dictionary with key "InvUnitConFactor_value1"
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddReceivingItemsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(name_value1)" at the "ReceivingItemsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "ReceivingItemsPage" page and get the dictionary key value "#(name_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "ReceivingItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "receivingItemText" element to be "VISIBLE" on the "EditReceivingItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "name" element at the "EditReceivingItemsPage" page into the data dictionary with key "name_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "yieldFactor" element at the "EditReceivingItemsPage" page into the data dictionary with key "yieldFactor_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "InvUnitConFactor" element at the "EditReceivingItemsPage" page into the data dictionary with key "InvUnitConFactor_value2"
    #Comment: user validate Name US Value
    And the user validates the data dictionary value of "#(name_value1)" is "Equal To" data dictionary value of "#(name_value2)" "validate_invoiceNum_value" "HardStopOnFailure"
    #Comment: user validate descriptionUS Value
    And the user validates the data dictionary value of "#(yieldFactor_value1)" is "Equal To" data dictionary value of "#(yieldFactor_value2)" "validate_date_value" "HardStopOnFailure"
    #Comment: user validate Name Spanish Value
    And the user validates the data dictionary value of "#(InvUnitConFactor_value1)" is "Equal To" data dictionary value of "#(InvUnitConFactor_value2)" "validate_tax_value" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditReceivingItemsPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "ReceivingItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(name_value1)" and store with new dictionary key "name_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Add']" with data dictionary key "#(name_value1_Latest)" and store with new dictionary key "name_value_Latest_1"
    #Comment: the user validate the ID number in History
    And the user validates Exact expected value "Compare_Strings" that the "table" element is "Equal To" "Add" at the "HistoryPage" page based on datadictionary "#(Id_Number)" and xpath1 "#(IDNumberXpath3)" and xpath2 "#(name_value_Latest_1)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(name_value1)" at the "ReceivingItemsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "ReceivingItemsPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "ReceivingItemsPage" page

