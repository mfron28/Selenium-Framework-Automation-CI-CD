
@tag
Feature: Error Validation

Background:
	Given I landed on Ecommerce Page

  Scenario Outline: Test for error validation
  
    Given Logged in with username "<name>" and password "<password>"
    When I add product "<productName>" to Cart
    Then "Incorrect email or password." message is displayed
    Examples: 
      | name  		     |  password		|
      | mada@yahoo.com |  Madalina    |
