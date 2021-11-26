Feature: Basic Prices Edit for Price Per Modifier
  This script is to validate Basic Prices Edit for Price Per Modifier

  @Basic_Prices_Edit_for_Price_Per_Modifier @RegressionSuite @BO_Prices @Back_Office
  Scenario: Basic_Prices_Edit_for_Price_Per_Modifier_Testcase
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
    #Comment: user click On the continueToLogin Button
    And the user clicks the "continueToLogin" element at the "LoginPage" page
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
    #Comment: the user store the text Value
    And store the displayed text of the "largePriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_Large_Modifier_value"
    #Comment: the user store the text Value
    And store the displayed text of the "smallPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_Small_Modifier_value"
    #Comment: the user store the text Value
    And store the displayed text of the "mediumPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_Medium_Modifier_value"
    #Comment: the user enter price
    And the user enters "#(smallPriceForModifier)" into the "smallPriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user store the text Value
    And store the displayed text of the "smallPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_1"
    #Comment: the user enter price
    And the user enters "#(mediumPriceForModifier)" into the "mediumPriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user store the text Value
    And store the displayed text of the "mediumPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_2"
    #Comment: the user enter price
    And the user enters "#(largePriceForModifier)" into the "largePriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user store the text Value
    And store the displayed text of the "largePriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_3"
    #Comment: the user click on Save Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user store the text Value
    And store the displayed text of the "smallPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_4"
    #Comment: the user store the text Value
    And store the displayed text of the "mediumPriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_5"
    #Comment: the user store the text Value
    And store the displayed text of the "largePriceForModifier" element at the "PricesRegularPage" page into the data dictionary with key "price_6"
    #Comment: compare the price value
    And the user validates the data dictionary value of "#(price_4)" is "Contains" data dictionary value of "#(price_1)" "validate_Price_Small" "HardStopOnFailure"
    #Comment: compare the price value
    And the user validates the data dictionary value of "#(price_5)" is "Contains" data dictionary value of "#(price_2)" "validate_Price_Small" "HardStopOnFailure"
    #Comment: compare the price value
    And the user validates the data dictionary value of "#(price_6)" is "Contains" data dictionary value of "#(price_3)" "validate_Price_Small" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "PricesRegularPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "history" element at the "MenuItemsPage" page
    #Comment: the user click on Deatils Button First Row
    And the user clicks the "detailsBtn" element at the "HistoryPage" page
    #Comment: the user validates on Details page
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: the user click on Edit Button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on Edit Button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user click on row
    And the user clicks the "regularTextClick" element at the "PricesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricesPage" page
    #Comment: the user enter price
    And the user enters "#(smallPriceForModifierRevoke)" into the "smallPriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user enter price
    And the user enters "#(mediumPriceForModifierRevoke)" into the "mediumPriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user enter price
    And the user enters "#(largePriceForModifierRevoke)" into the "largePriceForModifier" textbox at the "PricesRegularPage" page
    #Comment: the user click on Save Button
    And the user clicks the "saveBtn" element at the "PricesRegularPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load