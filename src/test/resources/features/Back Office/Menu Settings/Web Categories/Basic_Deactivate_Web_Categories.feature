Feature: Basic Deactivate Web Categories
  This script is to validate Deactivate Web Categories

  @Basic_Deactivate_Web_Categories @RegressionSuite @BO_Web_Categories
  Scenario: Basic_Deactivate_Web_Categories_Testcase
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
    And the user clicks the "webCategories" element at the "MenuSettingPage" page
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "WebCategoriesPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddWebCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddWebCategoriesPage" page into the data dictionary with key "nameUS_value1"
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddWebCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "AddWebCategoriesPage" page into the data dictionary with key "descriptionUS_value1"
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddWebCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "AddWebCategoriesPage" page into the data dictionary with key "nameSpanish_value1"
    #Comment: the user enters the name
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddWebCategoriesPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "AddWebCategoriesPage" page into the data dictionary with key "descriptionSpanish_value1"
    #Comment: the user select the actiiveChkBx check box
    And the user clicks the "actiiveChkBx" element at the "AddWebCategoriesPage" page
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddWebCategoriesPage" page
    And the user waits "3000" seconds
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "WebCategoriesPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "WebCategoriesPage" page and get the dictionary key value "#(nameUS_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"

    #Comment: the user click on Edit Button
    And the user clicks the "actAndDeact" element at the "WebCategoriesPage" page
    And the user waits "3000" seconds
    #Comment: the user select the availbale check box
    And the user clicks the "availabelChkBox" element at the "ActivateDeActicateWebCategoriesPage" page
    #Comment: the user click save Button
    And the user clicks the "save" element at the "ActivateDeActicateWebCategoriesPage" page
    And the user waits "3000" seconds
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "WebCategoriesPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "actAndDeact" element at the "WebCategoriesPage" page
    #Comment: the user validate the Web model text
    And the user waits for the "webCatTextVal" element to be "VISIBLE" on the "ActivateDeActicateWebCategoriesPage" page
    #Comment: the user validate the text
    And the user validates "Compare_Strings" that the "webCatTextVal" element is "Equal To" "Web Category" at the "ActivateDeActicateWebCategoriesPage" page "validate_Text" "HardStopOnFailure"
     #Comment: the user select the availbale check box
    And the user clicks the "availabelChkBox" element at the "ActivateDeActicateWebCategoriesPage" page
    #Comment: the user click save Button
    And the user clicks the "save" element at the "ActivateDeActicateWebCategoriesPage" page
    And the user waits "3000" seconds
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "WebCategoriesPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "actAndDeact" element at the "WebCategoriesPage" page
    And the user waits "3000" seconds
   #Comment: the user validate the Web model text
    And the user validates "Compare_Strings" that the "webCatTextVal" element is "Equal To" "Web Category" at the "ActivateDeActicateWebCategoriesPage" page "validate_Text" "HardStopOnFailure"
    #Comment: the user validate checkbox is not selected
    And the user validates the item in the "availabelChkBoxIsSelected" checkbox is Not checked at the "ActivateDeActicateWebCategoriesPage" page "validate_CheckBx_Selected" "HardStopOnFailure"
    #Comment: the user click on Edit Button
    And the user clicks the "cancel" element at the "ActivateDeActicateWebCategoriesPage" page
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "WebCategoriesPage" page
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(nameUS_value1)" and store with new dictionary key "nameUS_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Activate / Deactivate']//following-sibling::td)[5]" with data dictionary key "#(nameUS_value1_Latest)" and store with new dictionary key "nameUS_value1_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(nameUS_value1_Latest_1)"

    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Available" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "True" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "3" of the "tableDetails" table at the "HistoryPage" page "Equal To" "False" "validate_Item_Changed_Details" "HardStopOnFailure"

    #Comment: the user click on close button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on close button
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
     #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "WebCategoriesPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user click on Delete Button
    And the user clicks the "delete" element at the "WebCategoriesPage" page
    #Comment: the user click on Delete Button on Warning popup
    And the user clicks the "deleteOnWarning" element at the "WebCategoriesPage" page