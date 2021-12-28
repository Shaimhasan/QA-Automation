Feature: Basic Edit Vendors Delivery Days
  This script is to validate Edit Vendors Delivery Days

  @Basic_Edit_Vendors_Delivery_days @RegressionSuite @BO_Vendors
  Scenario: Basic_Edit_Vendors_Delivery_days_Testcase
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
    And the user clicks the "vendorsDeliveryDays" element at the "InventoryPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorsDeliveryDaysTxt" element to be "VISIBLE" on the "VendorsDeliveryDaysPage" page
    #Comment: the user click Add vendors
    And the user clicks the "editBtn" element at the "VendorsDeliveryDaysPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorDeliveryDaysTxt" element to be "VISIBLE" on the "EditVendorDeliveryDaysPage" page
    #Comment: the user click the check box
    And the user clicks on "t" on the "checkBox" checkbox at the "EditVendorDeliveryDaysPage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "EditVendorDeliveryDaysPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click Add vendors
    And the user clicks the "editBtn" element at the "VendorsDeliveryDaysPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorDeliveryDaysTxt" element to be "VISIBLE" on the "EditVendorDeliveryDaysPage" page
    #Comment: the user click the check box
    And the user clicks on "t" on the "checkBox" checkbox at the "EditVendorDeliveryDaysPage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "EditVendorDeliveryDaysPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load