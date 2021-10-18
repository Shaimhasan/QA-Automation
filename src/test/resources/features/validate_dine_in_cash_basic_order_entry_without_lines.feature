@dineInCashBasicOrderEntryWithoutLines
Feature: Dine in cash basic order entry without lines
  This script is to validate Dine in cash basic order entry without lines

  @validate_dine_in_cash_basic_order_entry_without_lines @RegressionSuite
  Scenario: validate_dine_in_cash_basic_order_entry_without_lines_Testcase
    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewWindow"
    #Comment: Enter the Store_Key into username textbox present on Login Page
    When the user enters the user credential "#(Store_Key)" into the "storeKey" textbox at the "LoginPage" page
    #Comment: Enter the Station_Key into Station_Key textbox present on Login Page
    When the user enters the secure credential "#(Station_Key)" into the "stationKey" textbox at the "LoginPage" page
    #Comment: user click On the Connect Button
    And the user clicks the "connect" element at the "LoginPage" page
    #Comment: The user wait until page is loading
    And the user validates the "waitTillLoading" element is present at the "LoginPage" page "wait_Untill_Loading" "HardStopOnFailure"
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
    #Comment: user click on Finish
    And the user clicks the "`finishBtn`" element at the "OrderEntry" page
    #Comment: The user can see the table menu popup
    And the user validates the "tableNoPopUpMenu" element is present at the "OrderEntry" page "validate_Table_Menu_popUp" "HardStopOnFailure"
    #Comment: the user enter the table number
    Then the user enters "#(tableNo)" into the "tableNo" textbox at the "OrderEntry" page
    #Comment: user click on OK
    And the user clicks the "OK" element at the "OrderEntry" page
    #Comment: user click on credit
    And the user clicks the "credit" element at the "PaymentPage" page

    #Comment: User switches to the frame
    And the user switches to frame "cardNumber"
     #Comment: the user enter the CreditCard Number
    Then the user enters "#(cardNum)" into the "cardNum" textbox at the "CreditCardPage" page
    #Comment: The user swtiches out side the frame
    And the user switches to the default window content

    #Comment: User switches to the frame
    And the user switches to frame "cardExpiration"
     #Comment: the user enter the expiration
    Then the user enters "#(expiration)" into the "expiration" textbox at the "CreditCardPage" page
    #Comment: The user swtiches out side the frame
    And the user switches to the default window content

    #Comment: User switches to the frame
    And the user switches to frame "cardCvv"
    #Comment: the user enter the cvv
    Then the user enters "#(cvv)" into the "cvv" textbox at the "CreditCardPage" page
    #Comment: The user swtiches out side the frame
    And the user switches to the default window content

    #Comment: user click on chargeBtn
    And the user clicks the "chargeBtn" element at the "CreditCardPage" page

    #Comment: The user validate change due popuo is present
    And the user validates the "headerPopUpChangeDue" element is present at the "OrderEntry" page "validate_Change_Due_popUp" "HardStopOnFailure"
    #Comment: The user save the transaction number into dictionary key
    And store the displayed text of the "transactionNum" element at the "OrderEntry" page into the data dictionary with key "transaction_Number"
    #Comment: The user save the order number into dictionary key
    And store the displayed text of the "orderNum" element at the "OrderEntry" page into the data dictionary with key "order_Number"
    #Comment: user click on Close
    And the user clicks the "close" element at the "OrderEntry" page
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: user click on Order List
    And the user clicks the "orderList" element at the "AdoraHeaderPage" page
    #Comment: user validate the transaction Number
    And the user validates based on dictionary key as expected Value at cell at row "7" and column "0" of the "tableOrderList" table at the "OrderListPage" page "OrderListPage" "<string>" "validate" "HardStopOnFailure"


