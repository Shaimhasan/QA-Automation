Feature: Basic Order Entry - Take Out Credit Card  - with all lines enabled
  This script is to validate Basic Order Entry - Take Out Credit Card  - with all lines enabled

  @Basic_Order_Entry_Take_Out_Credit_Card_With_All_Lines_Enabled @RegressionSuite @BOE @BOE_ALD @BOE_ALD_TakeOut
  Scenario: Basic_Order_Entry_Take_Out_Credit_Card_With_All_Lines_Enabled_Testcase
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
    #Comment: user click on Take Out
    And the user clicks the "takeOut" element at the "OrderEntry" page
    #Comment: validate background color
    And the user validates the background color of the "takeOutColor" element is "rgba(255, 255, 224, 1)" at the "OrderEntry" page "validate_background_color" "HardStopOnFailure"
    #Comment: user select suprimePizza
    And the user clicks the "automationPizzaPMC" element at the "OrderEntry" page
    #Comment: user select veggiePizza
    And the user clicks the "cheesePizzaPMC" element at the "OrderEntry" page
    #Comment: The user selected Supreme Pizza
    And the user validates the "automationPizzaPMCSelected" element is present at the "OrderEntry" page "validate_Pizza_Selected" "HardStopOnFailure"
    #Comment: The user selected Veggie Pizza
    And the user validates the "cheesePizzaPMCSelected" element is present at the "OrderEntry" page "validate_Pizza_Selected" "HardStopOnFailure"
    #Comment: Validate the amount
    Then the user validates "Compare_Strings" that the "amount" element is "Equal To" "#(amount)" at the "OrderEntry" page "validate_Amount" "HardStopOnFailure"
    #Comment: user click on Finish
    And the user clicks the "finishBtn" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
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
    #Comment: The user Switches out side the frame
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
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user validate the visibility of popup
    And the user waits for the "headerPopUpChangeDue" element to be "VISIBLE" on the "OrderEntry" page
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
    #Comment: the user validate the visibility of popup
    And the user waits for the "prepStation" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: User validate the order list element is present.
    And the user validates the "prepStation" element is present at the "AdoraHeaderPage" page "validate_Prep_Station_present" "HardStopOnFailure"
    #Comment: user click on prep Station
    And the user clicks the "prepStation" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click prepstation until order comes on console
    And the user click prepstation "order" element until "#(transaction_Number)" expected value based on attribute "id" found at the page "PrepStationPage"
    #Comment: the user validate the visibility of popup
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "makeLine" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on makeLine
    And the user clicks the "makeLine" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: user click makeline until order comes on console
    And the user click makeline "order" element until "#(transaction_Number)" expected value based on attribute "data-full-key" found at the page "MakeLinePage"
    #Comment: the user validate the visibility of popup
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "cutAndWrap" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on Cut and Wrap
    And the user clicks the "cutAndWrap" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: User validate the adoraHeaderSVG element is present.
    And the user validates the "inOven" element is present at the "CutAndWrapPage" page "validate_In_Oven_Present" "HardStopOnFailure"
    #Comment: user click on cut wrap based on order Number
    And the user custom clicks on row with order number "#(order_Number)" and category value "1" from the "table" table on the "CutAndWrapPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: User validate the adoraHeaderSVG element is present.
    And the user order number "#(order_Number)" category value "1" cut and wrap validates the "table" element is present at the "CutAndWrapPage" page "validate_Cut_And_Wrap_Present" "HardStopOnFailure"
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user click on the row
    And the user custom clicks on row with order number "#(order_Number)" and category value "2" from the "table" table on the "CutAndWrapPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: the user validate the cut and wrap
    And the user order number "#(order_Number)" category value "2" cut and wrap validates the "table" element is present at the "CutAndWrapPage" page "validate_Cut_And_Wrap_Present" "HardStopOnFailure"