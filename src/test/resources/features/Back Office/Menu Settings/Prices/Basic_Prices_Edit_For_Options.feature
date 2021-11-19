Feature: Basic Prices Edit for Options
  This script is to validate Basic Prices Edit for Options

  @Basic_Prices_Edit_For_Options @RegressionSuite @BO_Prices
  Scenario: Basic_Prices_Edit_For_Options_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewWindow"
    #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key_AutomationStore)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key_AutomationStore)" into the "stationKey" textbox at the "LoginPage" page
    #Comment: user click On the Connect Button
    And the user clicks the "connect" element at the "LoginPage" page
    And the user waits "20000" seconds
    #Comment: The user wait until page is loading
    #And the user validates the "waitTillLoading" element is present at the "LoginPage" page "wait_Untill_Loading" "HardStopOnFailure"
    #Comment: Enter the Employee_Id into username textbox present on Login Page
    When the user enters the user credential "#(Employee_Id)" into the "employee_Id" textbox at the "LoginPage" page
    #Comment: Enter the Password into Password textbox present on Login Page
    When the user enters the secure credential "#(Password)" into the "password" textbox at the "LoginPage" page
    #Comment: The user enter at passsword field
    And the user sends keys "Key_enter" to the "password" element on the "LoginPage" page
    #Comment: user click On the continueToLogin Button
    And the user clicks the "continueToLogin" element at the "LoginPage" page
    #Comment: the user click on back office
    And the user clicks the "backOffice" element at the "AdoraHeaderPage" page
    #Comment: the user click on prices Menu Items
    And the user clicks the "prices" element at the "MenuSettingPage" page
    #Comment: the user validate the price text
    And the user validates "Compare_Strings" that the "priceTxt" element is "Equal To" "Prices" at the "PricesPage" page "validate_Price_Text" "HardStopOnFailure"
    #Comment: the user click on row
    And the user clicks on the cell at row "1" and column "1" from the "table" table at the "PricesPage" page
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Allow only 1 modifiers" from the "selectSmallSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Allow only 2 modifiers" from the "selectMediumSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "Allow only 3 modifiers" from the "selectLargeSize" dropdown at the "PricesRegularPage" page
    #Comment: the user click on Save Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
    And the user waits "2000" seconds
    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    And the user waits "2000" seconds
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectSmallSize" dropdown equal to given value "Allow only 1 modifiers" at the "PricesRegularPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectMediumSize" dropdown equal to given value "Allow only 2 modifiers" at the "PricesRegularPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectLargeSize" dropdown equal to given value "Allow only 3 modifiers" at the "PricesRegularPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "PricesRegularPage" page

    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    And the user waits "2000" seconds
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectSmallSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectMediumSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectLargeSize" dropdown at the "PricesRegularPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
