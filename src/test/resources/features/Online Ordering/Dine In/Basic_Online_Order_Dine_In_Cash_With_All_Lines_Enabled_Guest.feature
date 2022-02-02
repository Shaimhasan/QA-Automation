Feature: Basic Online Order - Dine In Cash - with all lines enabled - Guest
  This script is to validate Basic Online Order - Dine In Cash - with all lines enabled - Guest

  @issue=1780
  @Basic_Online_Order_Dine_In_Cash_With_All_Lines_Enabled_Guest @RegressionSuite @OLO @OLO_ALE @OLO_ALE_DineIn
  Scenario: Basic_Online_Order_Dine_In_Cash_With_All_Lines_Enabled_Guest_Testcase
    #Comment: User launch online ordering web application in chrome browser
    Given the web application "Online_Ordering_Web_URL" is launched in a "NewWindow"
    #Comment: User wait to visible the page
    And the user waits for the "continueAsGuest" element to be "VISIBLE" on the "LoginOLOPage" page
    #Comment: User click on continue as guest
    And the user clicks the "continueAsGuest" element at the "LoginOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "orderType" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: the user wait hover element
    And the user hovers over the "dineIn" element at the "OrderTypeOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "dineIn" element at the "OrderTypeOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "orderType" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: User click on dine in
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

