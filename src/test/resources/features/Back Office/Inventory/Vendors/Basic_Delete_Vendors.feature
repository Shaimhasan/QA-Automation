Feature: Basic Delete Vendors
  This script is to validate Delete Vendors

  @Basic_Delete_Vendors @RegressionSuite @BO_Vendors @Inventory @Back_Office
  Scenario: Basic_Delete_Vendors_Testcase
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
    #Comment: the user click vendors
    And the user clicks the "vendors" element at the "InventoryPage" page
    #Comment: the user click Add vendors
    And the user clicks the "addBtn" element at the "VendorsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorTxt" element to be "VISIBLE" on the "AddVendorsPage" page
    #Comment : the User enters dynamic name
    And the user enters dynamic UserName "#(name)" into the "name" textbox at the "AddVendorsPage" page
    #Comment : the User stores the value
    And store the displayed text of the "name" element at the "AddVendorsPage" page into the data dictionary with key "name_value1"
    #Comment : the User enters dynamic name
    And the user enters "#(phoneNo)" into the "phoneNo" textbox at the "AddVendorsPage" page
    #Comment : the User enters email
    And the user enters "#(email)" into the "email" textbox at the "AddVendorsPage" page
    #Comment : the User enters address
    And the user enters "#(address)" into the "address" textbox at the "AddVendorsPage" page
    #Comment : the User enters city
    And the user enters "#(city)" into the "city" textbox at the "AddVendorsPage" page
    #Comment : the User enters zipCode
    And the user enters "#(zipCode)" into the "zipCode" textbox at the "AddVendorsPage" page
    #Comment : the User select the state
    And the user selects value "Arkansas" from the "state" dropdown at the "AddVendorsPage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "AddVendorsPage" page
    #Comment : the User wait to page load
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(name_value1)" at the "VendorsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "VendorsPage" page and get the dictionary key value "#(name_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "VendorsPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "VendorsPage" page
    #Comment : the User wait to page load
    And the user waits for the page to load
    #Comment: the user wait the element is disabled
    And the user waits for the "delete" element to be "DISABLED" on the "VendorsPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "VendorsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(name_value1)" and store with new dictionary key "name_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Delete']" with data dictionary key "#(name_value1_Latest)" and store with new dictionary key "name_value_Latest_1"
    #Comment: the user validate the ID number in History
    And the user validates Exact expected value "Compare_Strings" that the "table" element is "Equal To" "Delete" at the "HistoryPage" page based on datadictionary "#(Id_Number)" and xpath1 "#(IDNumberXpath3)" and xpath2 "#(name_value_Latest_1)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page