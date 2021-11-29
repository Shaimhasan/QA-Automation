Feature: Basic Delete Modifier POS Categories
  This script is to validate Delete Modifier POC Categories

  @Basic_Delete_Modifier_POS_Categories @RegressionSuite @BO_Modifier_POS_Categories @Back_Office
  Scenario: Basic_Delete_Modifier_POS_Categories_Testcase
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
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user validate the Title of the page
    And the user validates that the page title "Equal To" "Adora" "validate_Title" "HardStopOnFailure"
    #Comment: the user click on back office
    And the user clicks the "backOffice" element at the "AdoraHeaderPage" page
    #Comment: the user click on Menu Items
    And the user clicks the "modifierPOSCategories" element at the "MenuSettingPage" page
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "ModifierPOSCategoriesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "modifierPOSCatTextPopUp" element to be "VISIBLE" on the "AddModifierPOSCategoriesPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddModifierPOSCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddModifierPOSCategoriesPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user enter text
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddModifierPOSCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "AddModifierPOSCategoriesPage" page into the data dictionary with key "descriptionUS_value1"
    #Comment: the user enter text
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddModifierPOSCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "AddModifierPOSCategoriesPage" page into the data dictionary with key "nameSpanish_value1"
    #Comment: the user enters the name
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddModifierPOSCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "AddModifierPOSCategoriesPage" page into the data dictionary with key "descriptionSpanish_value1"
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddModifierPOSCategoriesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "ModifierPOSCategoriesPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "ModifierPOSCategoriesPage" page and get the dictionary key value "#(nameUS_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "ModifierPOSCategoriesPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "ModifierPOSCategoriesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "ModifierPOSCategoriesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(nameUS_value1)" and store with new dictionary key "nameUS_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Delete']" with data dictionary key "#(nameUS_value1_Latest)" and store with new dictionary key "nameUS_value1_Latest_1"
    #Comment: the user validate the ID number in History
    And the user validates Exact expected value "Compare_Strings" that the "table" element is "Equal To" "Delete" at the "HistoryPage" page based on datadictionary "#(Id_Number)" and xpath1 "#(IDNumberXpath3)" and xpath2 "#(nameUS_value1_Latest_1)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page