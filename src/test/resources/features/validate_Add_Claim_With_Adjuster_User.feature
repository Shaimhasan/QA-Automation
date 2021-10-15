@addClaimWithAdjusterUser
Feature: Add Claim With Adjuster User
  This script is to validate to add the claim with Adjuster User

  @add_Claim_With_Adjuster_User @RegressionSuite
  Scenario: validate_Add_Claim_With_Adjuster_User_Testcase
    #Comment: Launch ClaimzPay URL in CHROME browser
    Given the web application "Claimzpay_Desktop_URL" is launched in a "NewWindow"
    #Comment: Validate the ClaimzPayPageTitle
    Then the user validates "Compare_Strings" that the "claimzPageTitle" element is "Equal To" "#(claimzPayPageValidation)" at the "ClaimzPayHomePage" page "Claimzpay_HomePage_Validation" "HardStopOnFailure"
    #Comment: Enter the username into username textbox present on Login Page
    When the user enters the user credential "#(AdjusterUserName)" into the "userName" textbox at the "ClaimzPayLoginPage" page
    #Comment: Enter the password into password textbox present on Login Page
    When the user enters the secure credential "#(AdjusterPassword)" into the "password" textbox at the "ClaimzPayLoginPage" page
    #Comment: user click On the Login Button
    And the user clicks the "loginBtn" element at the "ClaimzPayLoginPage" page
    #Comment: user validate the home page title
    Then the user validates "Compare_Strings" that the "claimzPageTitle" element is "Equal To" "#(claimzPayPageValidation)" at the "ClaimzPayHomePage" page "Claimzpay_HomePage_Validation" "HardStopOnFailure"
    #Comment: user validate the Dashboard is present
    Then the user validates "Compare_Strings" that the "validateDashboardText" element is "Equal To" "#(claimzPayPageDashboardText)" at the "ClaimzPayHomePage" page "Claimzpay_Dashboard_Validation" "HardStopOnFailure"
    #Comment: user click On the ClaimzPay Tab
    And the user clicks the "claimzPayTab" element at the "ClaimzPayHomePage" page
    #Comment: User click on the Add New claim button
    And the user clicks the "addnNewClaim" element at the "AddNewClaimPage" page
    #Comment: User enter the claim Id
    And the user enters random number into the "companyClaimId" textbox at the "AddNewClaimPage" page
    #Comment: User enter the Policy Number
    And the user enters random number into the "policyNo" textbox at the "AddNewClaimPage" page
    #Comment: User enter the Current Date
    And the user enters "2" days before with current date into the "datePicker" textbox at the "AddNewClaimPage" page
    #Comment: The user perform TAB Operation
    And the user perform TAB into the "datePicker" textbox at the "AddNewClaimPage" page
    #Comment: user enter producer
    Then the user enters "#(producer)" into the "producerText" textbox at the "AddNewClaimPage" page
    #Comment: user enter Insured First Name
    Then the user enters "#(insuredFirstName)" into the "insuredFirstName" textbox at the "AddNewClaimPage" page
    #Comment: user enter Insured Last Name
    Then the user enters "#(insureLastName)" into the "insuredLastName" textbox at the "AddNewClaimPage" page
    #Comment: the user enter the dynamic Gmail Id
    Then the user enters dynamic email "#(email)" and "#(extGmail)" into the "insuredEmail" textbox at the "AddNewClaimPage" page
    #Comment: user enter Insured Phone
    Then the user enters "#(phoneNum)" into the "insuredPhone" textbox at the "AddNewClaimPage" page