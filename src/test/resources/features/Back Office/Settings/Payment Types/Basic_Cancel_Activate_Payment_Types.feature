Feature: Basic Cancel Activate Payment Types
  This script is to validate Cancel Activate Payment Types

  @Basic_Cancel_Activate_Payment_Types @RegressionSuite @BO_Payment_Types @Settings @Back_Office
  Scenario: Basic_Cancel_Activate_Payment_Types_Testcase
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
    And the user clicks the "settings" element at the "SettingsPage" page
    #Comment: the user click on Menu Items
    And the user clicks the "paymentTypes" element at the "SettingsPage" page

    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate history text
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: the user store the Date value
    And store value from row "1" and column "4" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingDate_First"
    #Comment: the user store the Time value
    And store value from row "1" and column "5" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingTime_First"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load

    #Comment: the user selects the Amazon Payment (ID: 27)
    And the user clicks the "amazon" element at the "PaymentTypesPage" page
    #Comment: the user click on Activate / Deactivate
    And the user clicks the "activateDeactivateBtn" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "paymentTypeTxt" element to be "VISIBLE" on the "ActivateDeActivatePaymentTypesPage" page
    #Comment: the user select the availbale check box
    And the user clicks the "smallAvailableChkBx" element at the "ActivateDeActivatePaymentTypesPage" page
    #Comment: user validate the check box is selected
    And the user validates the item in the "smallAvailableChkBx" checkbox is checked at the "ActivateDeActivatePaymentTypesPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user enter number in Sorting Index
    And the user enters dynamic UserName "#(sortingIndex)" into the "sortingIndex" textbox at the "ActivateDeActivatePaymentTypesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "sortingIndex" element at the "ActivateDeActivatePaymentTypesPage" page into the data dictionary with key "sortingIndex_value1"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancel" element at the "ActivateDeActivatePaymentTypesPage" page

    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user selects the Amazon Payment (ID: 27)
    And the user clicks the "amazon" element at the "PaymentTypesPage" page
    #Comment: the user click on Activate / Deactivate
    And the user clicks the "activateDeactivateBtn" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "paymentTypeTxt" element to be "VISIBLE" on the "ActivateDeActivatePaymentTypesPage" page
    #Comment: user validate the check box is selected
    And the user validates the item in the "smallAvailableChkBx" checkbox is Not checked at the "ActivateDeActivatePaymentTypesPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancel" element at the "ActivateDeActivatePaymentTypesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load

    #Comment: the user refresh the page
    And the user refreshes the page
    #Comment: the user click Settings
    And the user clicks the "settings" element at the "SettingsPage" page
    #Comment: the user click on Tax Definitions
    And the user clicks the "paymentTypes" element at the "SettingsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate history text
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: the user store the Existing ID number
    And store value from row "1" and column "4" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingDate_Second"
    #Comment: the user store the Existing ID number
    And store value from row "1" and column "5" from the "table" table at the "HistoryPage" page into the data dictionary with key "existingTime_Second"
    #Comment: the user validate the data dictionary value
    And the user validates the data dictionary value of "#(existingDate_First)" is "Equal To" data dictionary value of "#(existingDate_Second)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user validate the data dictionary value
    And the user validates the data dictionary value of "#(existingTime_First)" is "Equal To" data dictionary value of "#(existingTime_Second)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page