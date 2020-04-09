Feature: Deleting an order
  As Customer I want to update an order

  Background: 
    Given the user is logged in

  Scenario Outline: The customer made an order
    When the customer created their order "<steak>" "<steak temperature>" "<side>" "<sauce>"
    And they click UPDATE button
    And update their order "<new steak>"
    Then a confirmation modal will appear
    And when the customer clicks Confirm the order will be updated

    Examples: 
      | steak                 | steak temperature | side                       | sauce      | new steak            |
      | 10oz Rib Eye - €19.99 | Medium            | Sweet Potato Fries - €4.99 | Peppercorn | 8oz Sirloin - €19.99 |

      
      
      
      