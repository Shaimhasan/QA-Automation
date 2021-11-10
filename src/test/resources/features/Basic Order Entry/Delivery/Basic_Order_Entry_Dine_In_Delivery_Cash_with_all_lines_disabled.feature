@dineInDeliveryWithCash
Feature: Basic Order Entry - Delivery Cash  - with all lines disabled
  This script is to validate Dine in delivery with cash

  @Basic_Order_Entry_Dine_In_Delivery_Cash_with_all_lines_disabled @RegressionSuite
  Scenario: Basic_Order_Entry_Dine_In_Delivery_Cash_with_all_lines_disabled_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewWindow"
    #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key)" into the "stationKey" textbox at the "LoginPage" page
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
    #Comment: the user validate the Title of the page
    And the user validates that the page title "Equal To" "Adora" "validate_Title" "HardStopOnFailure"
    #Comment: user click On the orderEntry Button
    And the user clicks the "orderEntry" element at the "HomeScreenPage" page
    #Comment: validate background color
    And the user validates the background color of the "dinInColor" element is "rgba(153, 255, 204, 1)" at the "OrderEntry" page "validate_background_color" "HardStopOnFailure"
    #Comment: user select suprimePizza
    And the user clicks the "suprimePizza" element at the "OrderEntry" page
    #Comment: user select veggiePizza
    And the user clicks the "veggiePizza" element at the "OrderEntry" page
    #Comment: user select peperoni pizza
    And the user clicks the "paperroniPizza" element at the "OrderEntry" page
    #Comment: The user selected Supreme Pizza
    And the user validates the "suprimePizzaSelected" element is present at the "OrderEntry" page "validate_Supreme_Pizza_Selected" "HardStopOnFailure"
    #Comment: The user selected Veggie Pizza
    And the user validates the "veggiePizzaSelected" element is present at the "OrderEntry" page "validate_Supreme_Pizza_Selected" "HardStopOnFailure"
    #Comment: The user selected pepperoni Pizza
    And the user validates the "paperroniPizzaSelected" element is present at the "OrderEntry" page "validate_Supreme_Pizza_Selected" "HardStopOnFailure"
    #Comment: Validate the amount
    Then the user validates "Compare_Strings" that the "amount" element is "Equal To" "#(amount)" at the "OrderEntry" page "validate_Amount" "HardStopOnFailure"
    #Comment: user click on Devilery
    And the user clicks the "`delivery`" element at the "OrderEntry" page
    #Comment: the user enter phone Number
    Then the user enters "#(textPhone)" into the "textPhone" textbox at the "CustomerInfoPage" page
    #Comment: The user enter at textPhone field
    And the user sends keys "Key_enter" to the "textPhone" element on the "CustomerInfoPage" page
    And the user waits "2000" seconds
    #Comment: The user save the address into dictionary key
    And store the displayed text of the "address" element at the "CustomerInfoPage" page into the data dictionary with key "address_value"
    #Comment: user click on Finish
    And the user clicks the "OK" element at the "CustomerInfoPage" page

    #Comment: user click on Finish
    And the user clicks the "`finishBtn`" element at the "OrderEntry" page
    #Comment: user click on Cash
    And the user clicks the "cash" element at the "PaymentPage" page
    #Comment: The user save the order number into dictionary key
    And store the displayed text of the "orderNum" element at the "OrderEntry" page into the data dictionary with key "order_Number"
    #Comment: The user save the transaction number into dictionary key
    And store the displayed text of the "transactionNum" element at the "OrderEntry" page into the data dictionary with key "transaction_Number"
    #Comment: user click on Close
    And the user clicks the "closeForDelivery" element at the "OrderEntry" page

    #Comment: user click on Adora Header
    And the user waits "2000" seconds
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    And the user waits "2000" seconds
    #Comment: user click on ClockIn
    And the user clicks the "clockIn" element at the "AdoraHeaderPage" page
    #Comment: user click on two digit
    And the user clicks the "twoDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on Enter
    And the user clicks the "enter" element at the "ClockInPage" page
    #Comment: user click on Driver
    And the user clicks the "driver" element at the "SelectPostionPage" page
    #Comment: user validate time record message successfully
    And the user validates "Compare_Strings" that the "timeCardRecordSuccessMsg" element is "Equal To" "#(timeRecordSuccessMsg)" at the "ClockInPage" page "validate_Time_Record_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockInPage" page

    #Comment: user click on Adora Header
    And the user waits "2000" seconds
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    And the user waits "2000" seconds
    #Comment: user click on dispatch
    And the user clicks the "dispatch" element at the "AdoraHeaderPage" page
    And the user waits "2000" seconds
    #Comment : user click on orderNumber and dispatch
    And the user clicks the "table" element with dictionary key "#(order_Number)" at the "DispatchPage" page with xpath1 "#(orderNumberXpath1)" and xpath2 "#(orderNumberXpath2)"
    #Comment: user click on Driver o Dispatch page
    And the user clicks the "driverOliveGreene" element at the "DispatchPage" page
    And the user waits "2000" seconds
    #Comment: user click on Driver o Dispatch page
    And the user clicks the "driverOliveGreene" element at the "DispatchPage" page

    #Comment: user click on Adora Header
    And the user waits "2000" seconds
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    And the user waits "2000" seconds
    #Comment: user click on ClockOut
    And the user clicks the "clockOut" element at the "AdoraHeaderPage" page
    And the user waits "2000" seconds
    #Comment: user click on two digit
    And the user clicks the "twoDigit" element at the "ClockOutPage" page
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
    #Comment: user validate clock out message successfully
    And the user validates "Compare_Strings" that the "clockOutSuccessMsg" element is "Equal To" "#(timeClockOutSuccessMsg)" at the "ClockOutPage" page "validate_Clock_Out_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockOutPage" page


