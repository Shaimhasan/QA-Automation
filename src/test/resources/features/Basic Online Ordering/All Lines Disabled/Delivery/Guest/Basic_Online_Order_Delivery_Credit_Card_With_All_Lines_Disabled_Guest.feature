Feature: Basic Online Order - Delivery Credit Card - with all lines disabled - Guest
  This script is to validate Basic Online Order - Delivery Credit Card - with all lines disabled - Guest

  @issue=1757
  @Basic_Online_Order_Delivery_Credit_Card_With_All_Lines_Disabled_Guest @RegressionSuite @Delivery @OLO @OLO_ALD @OLO_ALD_Delivery @OLO_ALD_Delivery_Guest
  Scenario: Basic_Online_Order_Delivery_Credit_Card_With_All_Lines_Disabled_Guest_Testcase
    #Comment: User launch online ordering web application in chrome browser
    Given the web application "Online_Ordering_Web_URL" is launched in a "NewWindow"
    #Comment: the User wait page to load
    And the user waits for the page to load
    #Comment: the user Click on Error Message if Exists
    And the user click on OK button if error exists
    #Comment: User wait to visible the page
    And the user waits for the "continueAsGuest" element to be "VISIBLE" on the "LoginOLOPage" page
    #Comment: User click on continue as guest
    And the user clicks the "continueAsGuest" element at the "LoginOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "orderType" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: the user enter the address
    And the user enters "#(address)" into the "address" textbox at the "OrderTypeOLOPage" page
    #Comment: the user click continue button
    And the user waits for the "continueBtn" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: the user validate the text
    And the user validates "Compare_Strings" that the "orderTypeValidate" element is "Equal To" "#(orderTypeValidate)" at the "OrderTypeOLOPage" page "validate_Txt" "HardStopOnFailure"
    #Comment: the user validate the text
    And the user validates "Compare_Strings" that the "asap" element is "Equal To" "#(ASAP)" at the "OrderTypeOLOPage" page "validate_Txt" "HardStopOnFailure"
    #Comment: the user click continue button
    And the user clicks the "continueBtn" element at the "OrderTypeOLOPage" page
    #Comment: the user enter the address
    And the user enters "#(unit)" into the "unit" textbox at the "OrderTypeOLOPage" page
    #Comment: the user click continue button
    And the user waits for the "continueBtn" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: the user click continue button
    And the user clicks the "continueBtn" element at the "OrderTypeOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "automationPizzaPMC" element to be "VISIBLE" on the "HomeOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "automationPizzaPMC" element at the "HomeOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "addToOrder" element to be "VISIBLE" on the "AddToOrderOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "addToOrder" element at the "AddToOrderOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "cheesePizzaPMC" element to be "VISIBLE" on the "HomeOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "cheesePizzaPMC" element at the "HomeOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "addToOrder" element to be "VISIBLE" on the "AddToOrderOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "addToOrder" element at the "AddToOrderOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "checkOut" element to be "VISIBLE" on the "HomeOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "checkOut" element at the "HomeOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "existingCustLogin" element to be "VISIBLE" on the "ExistingCustLoginOLOPage" page
    #Comment: the user enter text
    And the user enters "#(firstName)" into the "firstName" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter text
    And the user enters "#(lastName)" into the "lastName" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter email
    And the user enters "#(email)" into the "email" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter re-email
    And the user enters "#(reEnterEmail)" into the "reEnterEmail" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter phone number
    And the user enters random Ten digit number into the "phoneNo" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter 10 digit number
    And store the displayed text of the "phoneNo" element at the "ExistingCustLoginOLOPage" page into the data dictionary with key "phoneNo1"
    #Comment: the user enter re enter phone
    And the user enters "#(phoneNo1)" into the "reEnterPhoneNo" textbox at the "ExistingCustLoginOLOPage" page

    #Comment: the user click on Credit Card Information
    And the user clicks the "creditCard" element at the "ExistingCustLoginOLOPage" page
    #Comment: the user validates the checkbox is selected
    And the user validates the item in the "creditCard" checkbox is checked at the "ExistingCustLoginOLOPage" page "validate_Text" "HardStopOnFailure"
    #Comment: the user switched to the frame
    And the user switches to frame "cardNumber"
    #Comment: the user enter Card Number
    And the user enters "#(cardNumber)" into the "cardNumber" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: The user Switches out side the frame
    And the user switches to the default window content
    #Comment: User switches to the frame
    And the user switches to frame "cardExpiration"
    #Comment: the user enter Expiration date
    And the user enters "#(cardExpiration)" into the "cardExpiration" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: The user swtiches out side the frame
    And the user switches to the default window content
    #Comment: User switches to the frame
    And the user switches to frame "cardCvv"
    #Comment: the user enter CVV
    And the user enters "#(cardCvv)" into the "cardCvv" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: The user swtiches out side the frame
    And the user switches to the default window content
    #Comment: the user enter Billing Address
    And the user enters "#(billingAddress)" into the "billingAddress" textbox at the "ExistingCustLoginOLOPage" page
    #Comment: the user enter Billing Address
    And the user enters "#(zipCode)" into the "zipCode" textbox at the "ExistingCustLoginOLOPage" page


    #comment: the user click on termsAndCondition
    And the user clicks the "termsAndCondition" element at the "ExistingCustLoginOLOPage" page
    #Comment: the user click on termsAndCondition
    And the user clicks the "placeYourOrder" element at the "ExistingCustLoginOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "successMsg" element to be "VISIBLE" on the "OrderModelPopupOLOPage" page
    #Comment: the user wait to store the element
    And store the displayed text of the "tranAndOrdNo" element at the "OrderModelPopupOLOPage" page into the data dictionary with key "tranAndOrdNo1"
    #Comment: the user substring
    And store the sub string "#(tranAndOrdNo1)" of text with start index "15" and last index "24" into the data dictionary with key "transactionNum"
    #Comment: the user substring
    And store the sub string "#(tranAndOrdNo1)" of text with start index "34" and last index "37" into the data dictionary with key "orderNum1"
    #Comment: the user click on OrderModelPopupOLOPage
    And the user clicks the "OK" element at the "OrderModelPopupOLOPage" page

    #Comment: Launch Adora Web URL in CHROME browser
    Given the web application "Adora_Web_URL" is launched in a "NewTab"
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

    #Comment: the user validate the visibility of element
    And the user waits for the "orderEntry" element to be "VISIBLE" on the "HomeScreenPage" page
    #Comment: user click On the orderEntry Button
    And the user clicks the "orderEntry" element at the "HomeScreenPage" page
    #Comment: the user validate the visibility of element
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: The user click on Adore header page
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page

    #Comment: the user validate the visibility of popup
    And the user waits for the "clockIn" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on ClockIn
    And the user hovers over the "clockIn" element at the "AdoraHeaderPage" page
    #Comment: user click on ClockIn
    And the user clicks the "clockIn" element at the "AdoraHeaderPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "oneDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "oneDigit" element at the "ClockInPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockInPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockInPage" page
    #Comment: the user validate the visibility of EmployeeNum
    And the user waits for the "employeeNo" element to be "VISIBLE" on the "ClockInPage" page
    #Comment: user click on enter
    And the user hovers over the "enter" element at the "ClockInPage" page
    #Comment: user click on Enter
    And the user clicks the "enter" element at the "ClockInPage" page
     #Comment: The user wait until page is loading
    And the user waits for the "clockInPopup" element to be "VISIBLE" on the "ClockInPage" page
    #Comment: user validate time record message successfully
    And the user validates "Compare_Strings" that the "timeCardRecordSuccessMsg" element is "Equal To" "#(timeRecordSuccessMsg)" at the "ClockInPage" page "validate_Time_Record_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockInPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment: The user wait until page is loading
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: The user click on Adore header page
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: The user wait until page is loading
    And the user waits for the "dispatch" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: user click on dispatch
    And the user clicks the "dispatch" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the page to load
    #Comment : user click on orderNumber and dispatch
    And the user clicks the "table" element with dictionary key "#(orderNum1)" at the "DispatchPage" page with xpath1 "#(orderNumberXpath1)" and xpath2 "#(orderNumberXpath2)"
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
    And the user waits for the "cloutOutVisible" element to be "VISIBLE" on the "ClockOutPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "oneDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "oneDigit" element at the "ClockOutPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "zeroDigit" element at the "ClockOutPage" page
    #Comment: user click on two digit
    And the user clicks the "zeroDigit" element at the "ClockOutPage" page
    #Comment: the user validate the visibility of employeeNo
    And the user waits for the "employeeNo" element to be "VISIBLE" on the "ClockOutPage" page
    #Comment: user click on oneDigit
    And the user hovers over the "enter" element at the "ClockOutPage" page
    #Comment: user click on Enter
    And the user clicks the "enter" element at the "ClockOutPage" page
    #Comment: The user wait until page is loading
    And the user waits for the "cloutOutVisible" element to be "VISIBLE" on the "ClockOutPage" page
    #Comment: the user enter gratuity amount
    Then the user enters "#(gatuityAmt)" into the "gratuityAmt" textbox at the "ClockOutPage" page
    #Comment: user click on ClockOut
    And the user clicks the "clockOut" element at the "ClockOutPage" page
    #Comment: The user wait until page is loading
    And the user waits for the "cloutOutTxtVisible" element to be "VISIBLE" on the "ClockOutPage" page
    #Comment: user validate clock out message successfully
    And the user validates "Compare_Strings" that the "clockOutSuccessMsg" element is "Equal To" "#(timeClockOutSuccessMsg)" at the "ClockOutPage" page "validate_Clock_Out_Successfully" "HardStopOnFailure"
    #Comment: user click on Driver
    And the user clicks the "OKBtn" element at the "ClockOutPage" page

    #Comment: the user validate the visibility of popup
    And the user waits for the "adoraHeaderSVG" element to be "VISIBLE" on the "OrderEntry" page
    #Comment: user click on Adora Header
    And the user clicks the "adoraHeaderSVG" element at the "OrderEntry" page
    #Comment: the user validate the visibility of popup
    And the user waits for the "orderList" element to be "VISIBLE" on the "AdoraHeaderPage" page
    #Comment: User validate the order list element is present.
    And the user validates the "orderList" element is present at the "AdoraHeaderPage" page "validate_order_list_present" "HardStopOnFailure"
    #Comment: the user hover the Order List
    And the user hovers over the "orderList" element at the "AdoraHeaderPage" page
    #Comment: user click on Order List
    And the user clicks the "orderList" element at the "AdoraHeaderPage" page
    #Comment: The user wait until page is loading
    And the user waits for the "orderListVisible" element to be "VISIBLE" on the "OrderListPage" page
    #Comment: user validate the transaction Number
    And the user waits "3000" seconds
    #Comment: the user click on Details Elements
    And the user clicks the "table" element with dictionary key "#(orderNum1)" at the "OrderListPage" page with xpath1 "#(DetailsClickXpath1)" and xpath2 "#(DetailsClickXpath2)"
    #Comment: the user validate the visibility of Page
    And the user waits for the "orderDetailTxt" element to be "VISIBLE" on the "OrderDetailsPage" page
    #Comment: Validate the amount
    Then the user validates "Compare_Strings" that the "amount" element is "Equal To" "#(amtVal)" at the "OrderDetailsPage" page "validate_Amount" "HardStopOnFailure"
    #Comment: The user save the transaction number into dictionary key
    And store the displayed text of the "transactionNum" element at the "OrderDetailsPage" page into the data dictionary with key "transaction_Number2"
    #Comment: The user save the order number into dictionary key
    And store the displayed text of the "orderNum" element at the "OrderDetailsPage" page into the data dictionary with key "order_Number2"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(transactionNum)" is "Equal To" data dictionary value of "#(transaction_Number2)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: User validate data dictionary values
    And the user validates the data dictionary value of "#(orderNum1)" is "Equal To" data dictionary value of "#(order_Number2)" "validate_data_dictionary_values" "HardStopOnFailure"
    #Comment: user click Close Button
    And the user clicks the "close" element at the "OrderDetailsPage" page


