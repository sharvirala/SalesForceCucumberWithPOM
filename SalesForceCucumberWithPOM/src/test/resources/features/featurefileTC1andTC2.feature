

Feature: Login into salesforce application
Background:
Given User open salesforce application
  @smoke
  Scenario: Login Error Message
    
    
    When user on "LoginPage"
    When user enter username "salesforce@tekarch.com"
    And user clear password field
    And user click on login button
    Then error message should be "Please enter your password"
    
@regression
 Scenario: Login with valid user and valid password
     
    When user on "LoginPage"
    When user enter username "salesforce@tekarch.com"
    When user enter password "Akshit05*"
    And user click on login button
    When user on "Homepage"
    Then message should be "welcom to your free trial"
    
    @smoke
    Scenario: Check Remember Me CheckBox
     
    When user on "LoginPage"
    When user enter username "salesforce@tekarch.com"
    When user enter password "Akshit05*"
    And user select the remember me checkbox
    And user click on login button
    When user on "Homepage"
   	And user click on usermenu dropdown
   	And user click on logout button
   	When user on "LoginPage"
   	Then "salesforce@tekarch.com" should be displayed on username field