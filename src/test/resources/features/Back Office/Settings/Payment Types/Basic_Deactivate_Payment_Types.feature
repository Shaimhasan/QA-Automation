Feature: Basic Deactivate Payment Types
  This script is to validate Deactivate Payment Types

  @Basic_Deactivate_Payment_Types @RegressionSuite @BO_Payment_Types @Settings @Back_Office
  Scenario: Basic_Deactivate_Payment_Types_Testcase
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
    #Comment: the user click save
    And the user clicks the "save" element at the "ActivateDeActivatePaymentTypesPage" page

    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user selects the Amazon Payment (ID: 27)
    And the user clicks the "amazon" element at the "PaymentTypesPage" page
    #Comment: the user click on Activate / Deactivate
    And the user clicks the "activateDeactivateBtn" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "paymentTypeTxt" element to be "VISIBLE" on the "ActivateDeActivatePaymentTypesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "sortingIndex" element at the "ActivateDeActivatePaymentTypesPage" page into the data dictionary with key "sortingIndex_value2"
    #Comment: user validate Sorting Index value
    And the user validates the data dictionary value of "#(sortingIndex_value1)" is "Equal To" data dictionary value of "#(sortingIndex_value2)" "validate_sortingIndex_value" "HardStopOnFailure"
    #Comment: the user select the available check box
    And the user clicks the "smallAvailableChkBx" element at the "ActivateDeActivatePaymentTypesPage" page
    #Comment: user validate the check box is not selected
    And the user validates the item in the "smallAvailableChkBx" checkbox is Not checked at the "ActivateDeActivatePaymentTypesPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user click save
    And the user clicks the "save" element at the "ActivateDeActivatePaymentTypesPage" page

    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user selects the Amazon Payment (ID: 27)
    And the user clicks the "amazon" element at the "PaymentTypesPage" page
    #Comment: the user click on Activate / Deactivate
    And the user clicks the "activateDeactivateBtn" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "paymentTypeTxt" element to be "VISIBLE" on the "ActivateDeActivatePaymentTypesPage" page
    #Comment: user validate the check box is not selected
    And the user validates the item in the "smallAvailableChkBx" checkbox is Not checked at the "ActivateDeActivatePaymentTypesPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancel" element at the "ActivateDeActivatePaymentTypesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load



    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PaymentTypesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: user selects the top row of the History table
    And the user clicks the "detailsBtn" element at the "HistoryPage" page
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Available" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Deleted" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "True" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Sorting Index" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Deleted" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(sortingIndex_value1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page


