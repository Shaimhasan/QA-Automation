Feature: Basic Clone Coupons
  This script is to validate Clone Coupons

  @Basic_Clone_Coupons @RegressionSuite @BO_Coupons @Discounts @Back_Office
  Scenario: Basic_Clone_Coupons_Testcase
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
    #Comment: the user click Discounts
    And the user clicks the "discount" element at the "DiscountsPage" page
    #Comment: the user click on Menu Items
    And the user clicks the "coupons" element at the "DiscountsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on Add
    And the user clicks the "addBtn" element at the "CouponsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "couponsTxtPopup" element to be "VISIBLE" on the "AddCouponsPage" page
    #Comment: the user enters the name On ADD Item
    And the user enters dynamic UserName "#(nameUS)" into the "nameUS" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "AddCouponsPage" page into the data dictionary with key "nameUS_value1"
    #Comment: the user enter text
    And the user enters "#(descriptionUS)" into the "descriptionUS" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "AddCouponsPage" page into the data dictionary with key "descriptionUS_value1"
    #Comment: the user enter text
    And the user enters "#(nameSpanish)" into the "nameSpanish" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "AddCouponsPage" page into the data dictionary with key "nameSpanish_value1"
    #Comment: the user enters the name
    And the user enters "#(descriptionSpanish)" into the "descriptionSpanish" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "AddCouponsPage" page into the data dictionary with key "descriptionSpanish_value1"
    #Comment: the user enters the couponNumber
    And the user enters "#(couponNumber)" into the "couponNumber" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "couponNumber" element at the "AddCouponsPage" page into the data dictionary with key "couponNumber_value1"
    #Comment: the user enters the discount
    And the user enters "#(discount)" into the "discount" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "discount" element at the "AddCouponsPage" page into the data dictionary with key "discount_value1"
    #Comment: the user enters the backGroundColor
    And the user enters "#(backGroundColor)" into the "backGroundColor" textbox at the "AddCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "backGroundColor" element at the "AddCouponsPage" page into the data dictionary with key "backGroundColor_value1"
    #Cooment: the user click save
    And the user clicks the "save" element at the "AddCouponsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the ID number row
    And the user clicks the "table" element with dictionary key "#(nameUS_value1)" at the "CouponsPage" page with xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)"
    #Comment: the user store the id
    And store the displayed text of the "table" element at the "CouponsPage" page and get the dictionary key value "#(nameUS_value1)" based on xpath1 "#(IdNumberXpath1)" and xpath2 "#(IdNumberXpath2)" store at dictionary with key "Id_Number"
    #Comment: the user click on Clone Button
    And the user clicks the "cloneBtn" element at the "CouponsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "cloneWarningTxt" element to be "VISIBLE" on the "CouponsPage" page
    #Comment: the user click on Clone Button
    And the user clicks the "cloneConfirmationBtn" element at the "CouponsPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on History Button
    And the user clicks the "history" element at the "CouponsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "historyText" element to be "VISIBLE" on the "HistoryPage" page
    #Comment: user validate history text
    And the user validates "Compare_Strings" that the "historyText" element is "Equal To" "History" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment append value with dictionary
    And the user validates and append at leading any value ": " with data dictionary key "#(nameUS_value1)" and store with new dictionary key "nameUS_value1_Latest"
    #Comment append value with dictionary
    And the user validates and append at trailing any value "']//preceding-sibling::td[text()='Clone']//following-sibling::td)[5]" with data dictionary key "#(nameUS_value1_Latest)" and store with new dictionary key "nameUS_value1_Latest_1"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(Id_Number)" at the "HistoryPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(nameUS_value1_Latest_1)"
    #Comment: user validate the details model popup
    And the user validates "Compare_Strings" that the "detailsText" element is "Equal To" "Details" at the "HistoryPage" page "validate_Details_model" "HardStopOnFailure"
    #Comment: The user validate the changes on Hisotry Page
    And the user validates the cell at row "1" and column "0" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Cloned" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "1" of the "tableDetails" table at the "HistoryPage" page "Equal To" "Changed" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: The user validate the chages on Hisotry Page
    And the user validates the cell at row "1" and column "2" of the "tableDetails" table at the "HistoryPage" page "Equal To" "#(Id_Number)" "validate_Item_Changed_Details" "HardStopOnFailure"
    #Comment: storing the clone Id Number
    And store the displayed text of the "newCloneIdNumber" element at the "HistoryPage" page into the data dictionary with key "Id_Number_Clone"
    #Comment: the user click on Close Button Button
    And the user clicks the "close" element at the "HistoryPage" page
    #Comment: the user click on Close Button Button on history page
    And the user clicks the "closeHistoryBtn" element at the "HistoryPage" page
    #Comment: the user store the Item number
    And store the displayed text of the "table" element at the "CouponsPage" page and get the dictionary key value "#(Id_Number_Clone)" based on xpath1 "#(ItemNumberXpath3)" and xpath2 "#(ItemNumberXpath4)" store at dictionary with key "Item_Number"
    #Comment: the user click on the item number row
    And the user clicks the "table" element with dictionary key "#(Id_Number_Clone)" at the "CouponsPage" page with xpath1 "#(ItemNumberXpath1)" and xpath2 "#(ItemNumberXpath2)"
    #Comment: the user click on Edit Button
    And the user clicks the "editBtn" element at the "CouponsPage" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "couponsTxtPopup" element to be "VISIBLE" on the "EditCouponsPage" page
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameUS" element at the "EditCouponsPage" page into the data dictionary with key "nameUS_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionUS" element at the "EditCouponsPage" page into the data dictionary with key "descriptionUS_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "nameSpanish" element at the "EditCouponsPage" page into the data dictionary with key "nameSpanish_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "descriptionSpanish" element at the "EditCouponsPage" page into the data dictionary with key "descriptionSpanish_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "couponNumber" element at the "EditCouponsPage" page into the data dictionary with key "couponNumber_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "discount" element at the "EditCouponsPage" page into the data dictionary with key "discount_value2"
    #Comment: the user store the text on data dictionary
    And store the displayed text of the "backGroundColor" element at the "EditCouponsPage" page into the data dictionary with key "backGroundColor_value2"
    #Comment: user validate Name US Value
    And the user validates the data dictionary value of "#(nameUS_value1)" is "Equal To" data dictionary value of "#(nameUS_value2)" "validate_nameUS_value" "HardStopOnFailure"
    #Comment: user validate descriptionUS Value
    And the user validates the data dictionary value of "#(descriptionUS_value1)" is "Equal To" data dictionary value of "#(descriptionUS_value2)" "validate_descriptionUS_value" "HardStopOnFailure"
    #Comment: user validate Name Spanish Value
    And the user validates the data dictionary value of "#(nameSpanish_value1)" is "Equal To" data dictionary value of "#(nameSpanish_value2)" "validate_Spanish_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(descriptionSpanish_value1)" is "Equal To" data dictionary value of "#(descriptionSpanish_value2)" "validate_descriptionSpanish_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(couponNumber_value1)" is "Equal To" data dictionary value of "#(couponNumber_value2)" "validate_descriptionSpanish_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(discount_value1)" is "Equal To" data dictionary value of "#(discount_value2)" "validate_descriptionSpanish_value" "HardStopOnFailure"
    #Comment: user validate descriptionSpanish Value
    And the user validates the data dictionary value of "#(backGroundColor_value1)" is "Equal To" data dictionary value of "#(backGroundColor_value2)" "validate_descriptionSpanish_value" "HardStopOnFailure"
    #Comment: the user click on Cancel Button
    And the user clicks the "cancelBtn" element at the "EditCouponsPage" page
