Feature: Getting list of orders
  As Employee I want to get a list of orders

  Background: 
    Given the employee is logged in

  Scenario Outline: The customer made an order
    When the customer created their order "<steak>" "<steak temperature>" "<side>" "<sauce>"
    Then the employee can get a list of containing that order

    Examples: 
      | steak                 | steak temperature | side                       | sauce      |
      | 10oz Rib Eye - €19.99 | Medium            | Sweet Potato Fries - €4.99 | Peppercorn |

      
      
      
      