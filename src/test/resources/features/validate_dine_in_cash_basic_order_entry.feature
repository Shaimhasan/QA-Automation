@dineInCashBasicOrderEntry
Feature: Dine in cash basic order entry
  This script is to validate Dine in cash basic order entry

  @validate_dine_in_cash_basic_order_entry @RegressionSuite
  Scenario: validate_dine_in_cash_basic_order_entry_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Desktop_URL" is launched in a "NewWindow"
    #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key)" into the "stationKey" textbox at the "LoginPage" page
    #Comment: user click On the Connect Button
    And the user clicks the "connect" element at the "LoginPage" page
    And the user waits "30000" seconds
    #Comment: Enter the Employee_Id into username textbox present on Login Page
    When the user enters the user credential "#(Employee_Id)" into the "employee_Id" textbox at the "LoginPage" page
    #Comment: Enter the Password into Password textbox present on Login Page
    When the user enters the secure credential "#(Password)" into the "password" textbox at the "LoginPage" page

