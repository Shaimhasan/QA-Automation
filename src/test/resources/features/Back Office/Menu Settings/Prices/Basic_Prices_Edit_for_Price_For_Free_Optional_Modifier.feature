Feature: Basic Prices Edit for Price for Free Optional Modifiers
  This script is to validate Basic Prices Edit for Free Optional Modifiers

  @Basic_Prices_Edit_for_Price_For_Free_Optional_Modifier @RegressionSuite @BO_Prices @Back_Office
  Scenario: Basic_Prices_Edit_for_Price_For_Free_Optional_Modifier_Testcase
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
    #Comment: the user click on prices Menu Items
    And the user clicks the "prices" element at the "MenuSettingPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "priceTxt" element to be "VISIBLE" on the "Prices" page
    #Comment: the user validate the price text
    And the user validates "Compare_Strings" that the "priceTxt" element is "Equal To" "Prices" at the "PricesPage" page "validate_Price_Text" "HardStopOnFailure"
    #Comment: the user click on row
    And the user clicks on the cell at row "1" and column "1" from the "table" table at the "PricesPage" page
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user select the dropdown values
    And the user selects value "1" from the "selectOptModSmallSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "2" from the "selectOptModMediumSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "3" from the "selectOptModLargeSize" dropdown at the "PricesRegularPage" page
    #Comment: the user click on Save Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectOptModSmallSize" dropdown equal to given value "1" at the "PricesRegularPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectOptModMediumSize" dropdown equal to given value "2" at the "PricesRegularPage" page
    #Comment: the user validate the selected value
    And the user selects value from the "selectOptModLargeSize" dropdown equal to given value "3" at the "PricesRegularPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "PricesRegularPage" page
    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectOptModSmallSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectOptModMediumSize" dropdown at the "PricesRegularPage" page
    #Comment: the user select the dropdown values
    And the user selects value "None" from the "selectOptModLargeSize" dropdown at the "PricesRegularPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
