Feature: Basic Add Receiving
  This script is to validate Add Receiving

  @Basic_Add_Receiving @RegressionSuite @BO_Receiving @Inventory @Back_Office
  Scenario: Basic_Add_Receiving_Testcase
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

    #Comment: the user add new Vendors

    And the user clicks the "inventory" element at the "InventoryPage" page
    #Comment: the user click vendors
    And the user clicks the "vendors" element at the "InventoryPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorTxt" element to be "VISIBLE" on the "VendorsPage" page
    #Comment: the user click Add vendors
    And the user clicks the "addBtn" element at the "VendorsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "vendorTxt" element to be "VISIBLE" on the "AddVendorsPage" page
    #Comment : the User enters dynamic name
    And the user enters dynamic UserName "#(name)" into the "name" textbox at the "AddVendorsPage" page
    #Comment : the User stores the value
    And store the displayed text of the "name" element at the "AddVendorsPage" page into the data dictionary with key "name_value1"
    #Comment : the User enters dynamic name
    And the user enters "#(phoneNo)" into the "phoneNo" textbox at the "AddVendorsPage" page
    #Comment : the User enters email
    And the user enters "#(email)" into the "email" textbox at the "AddVendorsPage" page
    #Comment : the User enters address
    And the user enters "#(address)" into the "address" textbox at the "AddVendorsPage" page
    #Comment : the User enters city
    And the user enters "#(city)" into the "city" textbox at the "AddVendorsPage" page
     #Comment : the User enters zipCode
    And the user enters "#(zipCode)" into the "zipCode" textbox at the "AddVendorsPage" page
    #Comment : the User select the state
    And the user selects value "Arkansas" from the "state" dropdown at the "AddVendorsPage" page
    #Comment : the User click on save
    And the user clicks the "save" element at the "AddVendorsPage" page
    #Comment : the User wait to page load
    And the user waits for the dom to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(name_value1)" at the "VendorsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "VendorsPage" page and get the dictionary key value "#(name_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"

    #Comment: the user click on Menu Items
    And the user clicks the "receiving" element at the "InventoryPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "ReceivingPage" page
    #Comment: the user click on list
    And the user clicks the "addBtn" element with dictionary key "#(name_value1)" at the "ReceivingPage" page with xpath1 "#(receivingXpath1)" and xpath2 "#(receivingXpath2)"
    #Comment: the user validate the visibility of popup
    And the user waits for the "recievingTxtPopup" element to be "VISIBLE" on the "AddRecievingPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters dynamic UserName "#(invoiceNum)" into the "invoiceNum" textbox at the "AddRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "invoiceNum" element at the "AddRecievingPage" page into the data dictionary with key "invoiceNum_value1"
    #Comment: the user enter text
    And the user enters "#(date)" into the "date" textbox at the "AddRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "date" element at the "AddRecievingPage" page into the data dictionary with key "date_value1"
    #Comment: the user enter text
    And the user enters "#(tax)" into the "tax" textbox at the "AddRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "tax" element at the "AddRecievingPage" page into the data dictionary with key "tax_value1"
    #Comment: the user append 00
    And the user validates and append at trailing any value ".00" with data dictionary key "tax_value1" and store with new dictionary key "tax_value3"
    #Comment: the user enters the name
    And the user enters "#(freight)" into the "freight" textbox at the "AddRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "freight" element at the "AddRecievingPage" page into the data dictionary with key "freight_value1"
    #Comment: the user append 00
    And the user validates and append at trailing any value ".00" with data dictionary key "freight_value1" and store with new dictionary key "freight_value3"
    #Comment: the user enters the couponNumber
    And the user enters "#(others)" into the "others" textbox at the "AddRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "others" element at the "AddRecievingPage" page into the data dictionary with key "others_value1"
    #Comment: the user append 00
    And the user validates and append at trailing any value ".00" with data dictionary key "others_value1" and store with new dictionary key "others_value3"
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddRecievingPage" page
    #Comment: The user wait until page is loading
    And the user waits for the dom to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(invoiceNum_value1)" at the "ReceivingPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "ReceivingPage" page and get the dictionary key value "#(invoiceNum_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "ReceivingPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "recievingTxtPopup" element to be "VISIBLE" on the "EditRecievingPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "invoiceNum" element at the "EditRecievingPage" page into the data dictionary with key "invoiceNum_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "date" element at the "EditRecievingPage" page into the data dictionary with key "date_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "tax" element at the "EditRecievingPage" page into the data dictionary with key "tax_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "freight" element at the "EditRecievingPage" page into the data dictionary with key "freight_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "others" element at the "EditRecievingPage" page into the data dictionary with key "others_value2"
    #Comment: user validate Name US Value
    And the user validates the data dictionary value of "#(invoiceNum_value1)" is "Equal To" data dictionary value of "#(invoiceNum_value2)" "validate_invoiceNum_value" "HardStopOnFailure"
    #Comment: user validate descriptionUS Value
    And the user validates the data dictionary value of "#(date_value1)" is "Equal To" data dictionary value of "#(date_value2)" "validate_date_value" "HardStopOnFailure"
    #Comment: user validate Name Spanish Value
    And the user validates the data dictionary value of "#(tax_value2)" is "Equal To" data dictionary value of "#(tax_value2)" "validate_tax_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(freight_value2)" is "Equal To" data dictionary value of "#(freight_value2)" "validate_freight_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(others_value2)" is "Equal To" data dictionary value of "#(others_value2)" "validate_others_value" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditRecievingPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "ReceivingPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(Id_Number)" and store with new dictionary key "Id_Number_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Add']" with data dictionary key "#(Id_Number_Latest)" and store with new dictionary key "Id_Number_Latest_1"
    #Comment: the user validate the ID number in History
    And the user validates Exact expected value "Compare_Strings" that the "table" element is "Equal To" "Add" at the "HistoryPage" page based on datadictionary "#(Id_Number)" and xpath1 "#(IDNumberXpath3)" and xpath2 "#(Id_Number_Latest_1)" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(invoiceNum_value1)" at the "ReceivingPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "ReceivingPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "ReceivingPage" page

