Feature: Make an Order
  As a customer I want to make an order

  Scenario Outline: The customer should be able to make an order
  	Given the customer is logged in with valid credentials "<username>" "<password>"
  	When the customer selects a steak
  	And steak temperature
  	And side
  	And sauce
  	And click "Make Order" button
  	Then a modal will appear for them to confim the order

    Examples: 
      | username 	 | password |
      | Customer   | Qwert123 |
