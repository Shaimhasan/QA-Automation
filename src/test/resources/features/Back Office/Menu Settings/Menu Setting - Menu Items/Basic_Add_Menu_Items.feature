@BasicAddMenuItems
Feature: Basic Add Menu Items
  This script is to validate Add Menu Items

  @Basic_Add_Menu_Items @RegressionSuite
  Scenario: Basic_Add_Menu_Items_Testcase
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
    #Comment: the user click on Menu Items
    And the user clicks the "menuItems" element at the "MenuSettingPage" page
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "MenuItemsPage" page
    #Comment: the user enters the name
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
    And the user enters random number into the "itemNumber" textbox at the "AddMenuItemsPage" page and store at dictionary key "item_Number"
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
    And the user clicks the "table" element with dictionary key "#(item_Number)" at the "MenuItemsPage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    And the user waits "5000" seconds