Feature: Basic Order Entry - Delivery Cash  - with all lines disabled
  This script is to validate Dine in delivery with cash

  @Basic_Order_Entry_Dine_In_Delivery_Cash_With_All_Lines_Disabled @RegressionSuite @BOE @BOE_ALD @BOE_ALD_Delivery
  Scenario: Basic_Order_Entry_Dine_In_Delivery_Cash_With_All_Lines_Disabled_Testcase
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
    #Comment: user click On the orderEntry Button
    And the user clicks the "orderEntry" element at the "HomeScreenPage" page
    #Comment: validate background color
    And the user validates the background color of the "dinInColor" element is "rgba(153, 255, 204, 1)" at the "OrderEntry" page "validate_background_color" "HardStopOnFailure"
    #Comment: user select suprimePizza
    And the user clicks the "automationPizzaPMC" element at the "OrderEntry" page
    #Comment: user select veggiePizza
    And the user clicks the "chicagoSylPizzaM" element at the "OrderEntry" page
    #Comment: The user selected Supreme Pizza
    And the user validates the "automationPizzaPMC" element is present at the "OrderEntry" page "validate_Pizza_Selected" "HardStopOnFailure"
    #Comment: The user selected Veggie Pizza
    And the user validates the "chicagoSylPizzaM" element is present at the "OrderEntry" page "validate_Pizza_Selected" "HardStopOnFailure"
    #Comment: Validate the amount
    Then the user validates "Compare_Strings" that the "amount" element is "Equal To" "#(amount)" at the "OrderEntry" page "validate_Amount" "HardStopOnFailure"
    #Comment: user click on Devilery
    And the user clicks the "delivery" element at the "OrderEntry" page
    #Comment: the user enter phone Number
    Then the user enters "#(textPhone)" into the "textPhone" textbox at the "CustomerInfoPage" page
    #Comment: The user enter at textPhone field
    And the user sends keys "Key_enter" to the "textPhone" element on the "CustomerInfoPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: The user save the address into dictionary key
    And store the displayed text of the "address" element at the "CustomerInfoPage" page into the data dictionary with key "address_value"
    #Comment: user click on Finish
    And the user clicks the "OK" element at the "CustomerInfoPage" page
    #Comment: user click on Finish
    And the user clicks the "finishBtn" element at the "OrderEntry" page
    #Comment: user click on Cash
    And the user clicks the "cash" element at the "PaymentPage" page
    #Comment: The user save the order number into dictionary key
    And store the displayed text of the "orderNum" element at the "OrderEntry" page into the data dictionary with key "order_Number"
    #Comment: The user save the transaction number into dictionary key
    And store the displayed text of the "transactionNum" element at the "OrderEntry" page into the data dictionary with key "transaction_Number"
    #Comment: user click on Close
    And the user clicks the "closeForDelivery" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: The user click on Adora header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the "clockIn" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on ClockIn
    And the user hovers over the "clockIn" element at the "AdoraHeaderPage" page
    #Comment: user click on ClockIn
    And the user clicks the "clockIn" element at the "AdoraHeaderPage" page
    #Comment: user click on two digit
    And the user clicks the "oneDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on Enter
    And the user clicks the "enter" element at the "ClockInPage" page
     #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user validate time record message successfully
    And the user validates "Compare_Strings" that the "timeCardRecordSuccessMsg" element is "Equal To" "#(timeRecordSuccessMsg)" at the "ClockInPage" page "validate_Time_Record_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockInPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: The user click on Adore header page
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click on dispatch
    And the user clicks the "dispatch" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment : user click on orderNumber and dispatch
    And the user clicks the "table" element with dictionary key "#(order_Number)" at the "DispatchPage" page with xpath1 "#(orderNumberXpath1)" and xpath2 "#(orderNumberXpath2)"
    #Comment: user click on Driver o Dispatch page
    And the user clicks the "bobTheDriver" element at the "DispatchPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click on Driver o Dispatch page
    And the user clicks the "bobTheDriver" element at the "DispatchPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the "clockOut" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on ClockIn
    And the user hovers over the "clockOut" element at the "AdoraHeaderPage" page
    #Comment: user click on ClockOut
    And the user clicks the "clockOut" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click on two digit
    And the user clicks the "oneDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on Enter
    And the user clicks the "enter" element at the "ClockOutPage" page
    #Comment: the user enter gratuity amount
    Then the user enters "#(gatuityAmt)" into the "gratuityAmt" textbox at the "ClockOutPage" page
    #Comment: user click on ClockOut
    And the user clicks the "clockOut" element at the "ClockOutPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user validate clock out message successfully
    And the user validates "Compare_Strings" that the "clockOutSuccessMsg" element is "Equal To" "#(timeClockOutSuccessMsg)" at the "ClockOutPage" page "validate_Clock_Out_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockOutPage" page

    #Comment: the user visible element
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "orderList" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: User validate the order list element is present.
    And the user validates the "orderList" element is present at the "AdoraHeaderPage" page "validate_order_list_present" "HardStopOnFailure"
    #Comment: user click on Order List
    And the user clicks the "orderList" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user validate the transaction Number
    And store text of the cell having unique rowVal comes from Data Dictionary "#(transaction_Number)" and columnHeader " Transaction#" from the "tableOrderList" table at the "OrderListPage" page into the data dictionary with key "transaction_Num"
    #Comment: user validate the transaction Number
    And store text of the cell having unique rowVal comes from Data Dictionary "#(order_Number)" and columnHeader " Order#" from the "tableOrderList" table at the "OrderListPage" page into the data dictionary with key "order_Num"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(transaction_Number)" is "Equal To" data dictionary value of "#(transaction_Num)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(order_Number)" is "Equal To" data dictionary value of "#(order_Num)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: the user validate the ID number in History
    And the user validates Exact expected value "Compare_Strings" that the "table" element is "Equal To" "Cash" at the "OrderListPage" page based on datadictionary "#(order_Number)" and xpath1 "#(orderIdXpath)" and xpath2 "']//parent::td//following-sibling::td)[9]//div[text()='Cash']" "validate_ID_Number" "HardStopOnFailure"
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(order_Number)" at the "OrderListPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(DetailsClickXpath2)"
    #Comment: the user validate the visibility of Page
    And the user waits for the "orderDetailTxt" element to be "VISIBLE" on the "OrderDetailsPage" page
    #Comment: Validate the amount
    Then the user validates "Compare_Strings" that the "amount" element is "Equal To" "#(amtVal)" at the "OrderDetailsPage" page "validate_Amount" "HardStopOnFailure"
    #Comment: The user save the transaction number into dictionary key
    And store the displayed text of the "transactionNum" element at the "OrderDetailsPage" page into the data dictionary with key "transaction_Number2"
    #Comment: The user save the order number into dictionary key
    And store the displayed text of the "orderNum" element at the "OrderDetailsPage" page into the data dictionary with key "order_Number2"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(transaction_Number)" is "Equal To" data dictionary value of "#(transaction_Number2)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(order_Number)" is "Equal To" data dictionary value of "#(order_Number2)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: user click Close Button
    And the user clicks the "close" element at the "OrderDetailsPage" page


