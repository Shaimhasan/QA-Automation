Feature: Basic Cancel Add Pricing Groups
  This script is to validate Cancel Add Tax Definitions

  @Basic_Cancel_Add_Pricing_Groups @RegressionSuite @BO_Pricing_Groups @Settings @Back_Office
  Scenario: Basic_Cancel_Add_Pricing_Groups_Testcase
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
    #Comment: the user click Settings
    And the user clicks the "settings" element at the "SettingsPage" page
    #Comment: the user click on Pricing Groups
    And the user clicks the "pricingGroups" element at the "SettingsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load

    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate history text
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: the user store the ID value
    And store value from row "1" and column "2" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingIdNumber_First"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page

    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "pricingGroupsTxtPopup" element to be "VISIBLE" on the "AddPricingGroupsPage" page
    #Comment: the user enters the name On ADD Pricing Groups
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddPricingGroupsPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user enters the name Spanish
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "AddPricingGroupsPage" page into the data dictionary with key "nameSpanish_value1"
    #Comment: the user enters the description
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "AddPricingGroupsPage" page into the data dictionary with key "descriptionUS_value1"
    #Comment: the user enters the description Spanish
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "AddPricingGroupsPage" page into the data dictionary with key "descriptionSpanish_value1"
    #Comment: the user select the 3rd Party Price Menu check box
    And the user clicks the "thirdPartyCheckbox" element at the "AddPricingGroupsPage" page
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditPricingGroupsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load

    #Comment: the user refresh the page
    And the user refreshes the page
    #Comment: the user click Settings
    And the user clicks the "settings" element at the "SettingsPage" page
    #Comment: the user click on Pricing Groups
    And the user clicks the "pricingGroups" element at the "SettingsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate history text
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: the user store the Existing ID number
    And store value from row "1" and column "2" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingIdNumber_Second"
    #Comment: the user validate the data dictionary value
    And the user validates the data dictionary value of "#(existingIdNumber_First)" is "Equal To" data dictionary value of "#(existingIdNumber_Second)" "validate_ID_Number" "HardStopOnFailure"