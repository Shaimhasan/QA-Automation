Feature: Basic Edit Tax Rates
  This script is to validate Edit Tax Rates

  @Basic_Edit_Tax_Rates @RegressionSuite @BO_Tax_Rates @Settings @Back_Office
  Scenario: Basic_Edit_Tax_Rates_Testcase
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
    #Comment: the user click on Tax Definitions
    And the user clicks the "taxDefinitions" element at the "SettingsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "TaxDefinitionsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "taxDefinitionsTxtPopup" element to be "VISIBLE" on the "AddTaxDefinitionsPage" page
    #Comment: the user enters the name On ADD Tax Definition
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user enters the name Spanish
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "nameSpanish_value1"
    #Comment: the user enters the description
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "descriptionUS_value1"
    #Comment: the user enters the description Spanish
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "descriptionSpanish_value1"
    #Comment: the user enters the Tax Rate
    And the user enters "#(taxRate)" into the "taxRate" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "taxRate" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "taxRate_value1"
    #Comment: the user enters the Fixed Amount
    And the user enters "#(fixedAmt)" into the "fixedAmt" textbox at the "AddTaxDefinitionsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "fixedAmt" element at the "AddTaxDefinitionsPage" page into the data dictionary with key "fixedAmt_value1"
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddTaxDefinitionsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "TaxDefinitionsPage" page with xpath1 "#(nameXpath1)" and xpath2 "#(nameXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "TaxDefinitionsPage" page and get the dictionary key value "#(nameUS_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"

    #Comment: the user clicks on Tax Rates
    And the user clicks the "taxRates" element at the "SettingsPage" page
    #Comment: the user page to load
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "TaxRatesPage" page with xpath1 "#(nameXpath1)" and xpath2 "#(nameXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "TaxRatesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "taxRatesTxtPopup" element to be "VISIBLE" on the "EditTaxRatesPage" page
    #Comment: the user changes the Fixed Amount
    And the user enters "#(fixedAmt2)" into the "fixedAmt" textbox at the "EditTaxRatesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "fixedAmt" element at the "EditTaxRatesPage" page into the data dictionary with key "fixedAmt_value2"
    #Comment: the user clicks Save
    And the user clicks the "save" element at the "EditTaxRatesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "TaxRatesPage" page with xpath1 "#(nameXpath1)" and xpath2 "#(nameXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "TaxRatesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "taxRatesTxtPopup" element to be "VISIBLE" on the "EditTaxRatesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "fixedAmt" element at the "EditTaxRatesPage" page into the data dictionary with key "fixedAmt_value3"
    #Comment: user validate Fixed Amount Value
    And the user validates the data dictionary value of "#(fixedAmt_value2)" is "Equal To" data dictionary value of "#(fixedAmt_value3)" "validate_fixedAmt_value" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditTaxRatesPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load

    #Comment: the user click on History Button
    And the user clicks the "history" element at the "TaxRatesPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(nameUS_value1)" and store with new dictionary key "nameUS_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Edit']//following-sibling::td)[5]" with data dictionary key "#(nameUS_value1_Latest)" and store with new dictionary key "nameUS_value1_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(nameUS_value1_Latest_1)"
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Amount" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(fixedAmt_value1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(fixedAmt_value2)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page

    #Comment: the user clicks on Tax Definitions
    And the user clicks the "taxDefinitions" element at the "SettingsPage" page
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "TaxDefinitionsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "TaxDefinitionsPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "TaxDefinitionsPage" page