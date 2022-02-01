Feature: Basic Online Order - Dine In Cash - with all lines enabled - Guest
  This script is to validate Basic Online Order - Dine In Cash - with all lines enabled - Guest

  @Basic_Online_Order_Dine_In_Cash_With_All_Lines_Enabled_Guest
  Scenario: Basic_Online_Order_Dine_In_Cash_With_All_Lines_Enabled_Guest_Testcase
    #Comment: User launch online ordering web application in chrome browser
    Given the web application "Online_Ordering_Web_URL" is launched in a "NewWindow"
    #Comment: User wait to visible the page
    And the user waits for the "continueAsGuest" element to be "VISIBLE" on the "LoginOLOPage" page
    #Comment: User click on continue as guest
    And the user clicks the "continueAsGuest" element at the "LoginOLOPage" page
    #Comment: User wait to visible the page
    And the user waits for the "orderType" element to be "VISIBLE" on the "OrderTypeOLOPage" page
    #Comment: User click on dine in
    And the user clicks the "dineIn" element at the "OrderTypeOLOPage" page

