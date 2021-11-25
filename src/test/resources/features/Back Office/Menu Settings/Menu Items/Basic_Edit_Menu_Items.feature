@BasicEditMenuItems
Feature: Basic Edit Menu Items
  This script is to validate Edit Menu Items

  @Basic_Edit_Menu_Items @RegressionSuite @BO_MenuItems @Back_Office
  Scenario: Basic_Edit_Menu_Items_Testcase
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
    #Comment: the user click on Menu Items
    And the user clicks the "menuItems" element at the "MenuSettingPage" page
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "MenuItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "addMenuItemText" element to be "VISIBLE" on the "AddMenuItemsPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters "#(nameUS)" into the "nameUS" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(webNameUS)" into the "webNameUS" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(webDescriptionUS)" into the "webDescriptionUS" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(webNameSpanish)" into the "webNameSpanish" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddMenuItemsPage" page
    #Comment: the user enters the name
    And the user enters "#(webDescriptionSpanish)" into the "webDescriptionSpanish" textbox at the "AddMenuItemsPage" page
    #Cooment: the user enters the random Number and stored at data dictionary
    And the user enters random number into the "itemNumber" textbox at the "AddMenuItemsPage" page and store at dictionary key "item_Number_value1"

    #Comment: the user select the dine in check box
    And the user clicks the "dineInChkBx" element at the "AddMenuItemsPage" page
    #Comment: the user select the Take out check box
    And the user clicks the "takeOutChkBx" element at the "AddMenuItemsPage" page
    #Comment: the user select the delivery check box
    And the user clicks the "deliveryChkBx" element at the "AddMenuItemsPage" page
    #Cooment: the user click on small size dine in
    And the user clicks the "dineInSizeChkBx" element at the "AddMenuItemsPage" page
    #Cooment: the user click on small size take out
    And the user clicks the "takeOutSizeChkBx" element at the "AddMenuItemsPage" page
    #Cooment: the user click on small size delivery
    And the user clicks the "deliverySizeChkBx" element at the "AddMenuItemsPage" page
    #Cooment: the user click on small size default
    And the user clicks the "defualt" element at the "AddMenuItemsPage" page
    #Cooment: the user click on small size default
    And the user clicks the "save" element at the "AddMenuItemsPage" page
    #Comment: the user click on the item number row
    And the user clicks the "table" element with dictionary key "#(item_Number_value1)" at the "MenuItemsPage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    #Comment: the user store the Id Number at Menu item Page
    And store the displayed text of the "table" element at the "MenuItemsPage" page and get the dictionary key value "#(item_Number_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"

    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "MenuItemsPage" page
   #Comment: the user validate the visibility of popup
    And the user waits for the "addMenuItemText" element to be "VISIBLE" on the "AddMenuItemsPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters "#(nameUSChangedValue)" into the "nameUS" textbox at the "AddMenuItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "EditMenuItemsPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user uncheck the dine in check box
    And the user clicks the "dineInChkBx" element at the "EditMenuItemsPage" page
    #Cooment: the user uncheck on small size dine in checkbox
    And the user clicks the "dineInSizeChkBx" element at the "EditMenuItemsPage" page
     #Cooment: the user click on small size default
    And the user clicks the "save" element at the "EditMenuItemsPage" page
     #Comment: the user click on the item number row
    And the user clicks the "table" element with dictionary key "#(item_Number_value1)" at the "MenuItemsPage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "MenuItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "addMenuItemText" element to be "VISIBLE" on the "AddMenuItemsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "EditMenuItemsPage" page into the data dictionary with key "nameUS_value2"
    #Comment: user validate Name US Value
    And the user validates the data dictionary value of "#(nameUS_value1)" is "Equal To" data dictionary value of "#(nameUS_value2)" "validate_nameUS_value" "HardStopOnFailure"
    #Comment: user validate the check box is selected
    And the user validates the "Dine In" item in the "dineInCheckBoxIsSelected" checkbox is not checked at the "EditMenuItemsPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: user validate the check box is selected
    And the user validates the item in the "dineInCheckBoxIsSelectedSize" checkbox is Not checked at the "EditMenuItemsPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditMenuItemsPage" page

    #Comment: the user click on History Button
    And the user clicks the "history" element at the "MenuItemsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: the user validate the ID number in History
    And the user validates "Compare_Strings" that the "table" element is "Equal To" "#(IdNumberExpectedValueChanged)" at the "HistoryPage" page based on datadictionary "#(Id_Number)" and xpath1 "#(IDNumberXpath3)" and xpath2 "#(IdNumberXpath4Changed)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(DetailsClickActionXpath2)"
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Order Type: Dine In" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "True" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "False" "validate_Item_Changed_Details" "HardStopOnFailure"

    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Name: English - United States - Text" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Automation Testing US" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "2" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Automation Testing US Changed TO America" "validate_Item_Changed_Details" "HardStopOnFailure"
