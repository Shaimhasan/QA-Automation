Feature: Basic Add Vendors
  This script is to validate Add Vendors

  @Basic_Add_Vendors @RegressionSuite @BO_Vendors
  Scenario: Basic_Add_Vendors_Testcase
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
    #Comment: the user validate the Text
    And the user validates the "vendorTxt" element is visible at the "AddVendorsPage" page "validate_txt" "HardStopOnFailure"
    #Comment : the User enters dynamic name
    And the user enters dynamic UserName "#(vendorName)" into the "vendorName" textbox at the "AddVendorsPage" page
    #Comment : the User stores the value
    And store the displayed text of the "vendorName" element at the "AddVendorsPage" page into the data dictionary with key "vendorName_value1"
    #Comment : the User enters dynamic name
    And the user enters "#(vendorPhoneNum)" into the "vendorPhoneNum" textbox at the "AddVendorsPage" page
    #Comment : the User stores the value
    And store the displayed text of the "vendorPhoneNum" element at the "AddVendorsPage" page into the data dictionary with key "vendorPhoneNum_value1"
    #Comment : the User enters email
    And the user enters "#(email)" into the "email" textbox at the "AddVendorsPage" page
    #Comment : the User stores the email
    And store the displayed text of the "email" element at the "AddVendorsPage" page into the data dictionary with key "email_value1"
    #Comment : the User enters address
    And the user enters "#(address)" into the "address" textbox at the "AddVendorsPage" page
    #Comment : the User stores the value
    And store the displayed text of the "address" element at the "AddVendorsPage" page into the data dictionary with key "address_value1"
    #Comment : the User enters city
    And the user enters "#(city)" into the "city" textbox at the "AddVendorsPage" page
    #Comment : the User stores the city
    And store the displayed text of the "city" element at the "AddVendorsPage" page into the data dictionary with key "city_value1"
     #Comment : the User enters zipCode
    And the user enters "#(zipCode)" into the "zipCode" textbox at the "AddVendorsPage" page
    #Comment : the User stores the zipCode
    And store the displayed text of the "zipCode" element at the "AddVendorsPage" page into the data dictionary with key "zipCode_value1"
    #Comment : the User select the state
    And the user selects value "Arkansas" from the "state" dropdown at the "AddVendorsPage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "AddVendorsPage" page
    #Comment : the User wait to page load
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(vendorName_value1)" at the "VendorsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"

