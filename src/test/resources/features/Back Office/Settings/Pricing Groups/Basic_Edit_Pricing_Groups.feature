Feature: Basic Edit Pricing Groups
  This script is to validate Edit Pricing Groups

  @Basic_Edit_Pricing_Groups @RegressionSuite @BO_Pricing_Groups @Back_Office @Settings
  Scenario: Basic_Edit_Pricing_Groups_Testcase
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
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "priceGroupTxtPopup" element to be "VISIBLE" on the "AddPricingGroupsPage" page
    #Comment: the user enters the name On ADD Pricing Groups
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddPricingGroupsPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user enters the name Spanish
    And the user enters dynamic UserName "#(nameSpanish)" into the "nameSpanish" textbox at the "AddPricingGroupsPage" page
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
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddPricingGroupsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "PricingGroupsPage" page with xpath1 "#(nameXpath1)" and xpath2 "#(nameXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "PricingGroupsPage" page and get the dictionary key value "#(nameUS_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "priceGroupTxtPopup" element to be "VISIBLE" on the "EditPricingGroupsPage" page
    #Comment: the user enters the name On ADD Tax Definition
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "EditPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "EditPricingGroupsPage" page into the data dictionary with key "nameUS_value1_Updated_1"
    #Comment: the user enters the name Spanish
    And the user enters dynamic UserName "#(nameSpanish)" into the "nameSpanish" textbox at the "EditPricingGroupsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "EditPricingGroupsPage" page into the data dictionary with key "nameSpanish_value1_Updated_1"
    #Comment: the user select the 3rd Party Price Menu check box
    And the user clicks the "thirdPartyCheckbox" element at the "EditPricingGroupsPage" page
    #Cooment: the user click save
    And the user clicks the "save" element at the "EditPricingGroupsPage" page
    #Comment: the user clicks Continue
    And the user clicks the "continueOnWarning" element at the "EditPricingGroupsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1_Updated_1)" at the "PricingGroupsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "PricingGroupsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "EditPricingGroupsPage" page into the data dictionary with key "nameUS_value2_Updated_2"
    #Comment: user validate Name US Value
    And the user validates the data dictionary value of "#(nameUS_value1_Updated_1)" is "Equal To" data dictionary value of "#(nameUS_value2_Updated_2)" "validate_nameUS_value" "HardStopOnFailure"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "EditPricingGroupsPage" page into the data dictionary with key "nameSpanish_value2_Updated_2"
    #Comment: user validate Name Spanish Value
    And the user validates the data dictionary value of "#(nameSpanish_value1_Updated_1)" is "Equal To" data dictionary value of "#(nameSpanish_value2_Updated_2)" "validate_nameUS_value" "HardStopOnFailure"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "EditPricingGroupsPage" page into the data dictionary with key "descriptionUS_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "EditPricingGroupsPage" page into the data dictionary with key "descriptionSpanish_value2"
    #Comment: user validate the 3rd Party Pricing Menu check box is selected
    And the user validates the item in the "thirdPartyCheckboxIsSelected" checkbox is checked at the "EditPricingGroupsPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: user validate descriptionUS Value
    And the user validates the data dictionary value of "#(descriptionUS_value1)" is "Equal To" data dictionary value of "#(descriptionUS_value2)" "validate_descriptionUS_value" "HardStopOnFailure"
    #Comment: user validate Description Spanish Value
    And the user validates the data dictionary value of "#(descriptionSpanish_value1)" is "Equal To" data dictionary value of "#(descriptionSpanish_value2)" "validate_descriptionSpanish_value" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditPricingGroupsPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "PricingGroupsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(nameUS_value1_Updated_1)" and store with new dictionary key "nameUS_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Edit']//following-sibling::td)[5]" with data dictionary key "#(nameUS_value1_Latest)" and store with new dictionary key "nameUS_value1_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(nameUS_value1_Latest_1)"
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Name: English - United States - Text" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(nameUS_value1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(nameUS_value1_Updated_1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Name: Español - Mexico - Text" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(nameSpanish_value1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(nameSpanish_value1_Updated_1)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
     #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1_Updated_1)" at the "TaxDefinitionsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "PricingGroupsPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "PricingGroupsPage" page