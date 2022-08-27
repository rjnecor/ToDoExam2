Feature: ToDo Management
  In order to remember the things I want to do, as a ToDo MVC user,
  I want to manage my todo list

  Scenario: Add an entry
    Given the ToDo page is loaded
    When an entry with value "To do stuff" is added
    Then the added entry should be found in the list
      And the item count is "incremented"

  Scenario: Edit an entry
    Given the ToDo page is loaded
      And prefilled with data
    When an entry with value "To Edit" is edited to "Changed by robot"
    Then the edited entry should be found in the list

  Scenario: Delete an entry
    Given the ToDo page is loaded
      And prefilled with data
    When an entry with value "To Delete" is deleted
    Then the deleted entry should not be found in the list
      And the item count is "decremented"

  Scenario: Mark an entry
    Given the ToDo page is loaded
      And prefilled with data
    When an entry with value "To mark as done" is marked as done
    Then the entry should be marked as completed
      And the item count is "decremented"
    When an entry with value "To mark as done" is marked as not done
    Then the entry should be marked as not completed
    And the item count is "incremented"

  Scenario: Mark all entries
    Given the ToDo page is loaded
    And prefilled with data
    When all entries are marked as done
    Then all entries should be marked as completed
      And the item count is "decremented"
    When all entries are marked as not done
    Then all entries should be marked as not completed
      And the item count is "incremented"

  Scenario: Show all active
    Given the ToDo page is loaded
      And prefilled with data
    When the Active filter is clicked
    Then all active entries should be displayed

  Scenario: Show all completed
    Given the ToDo page is loaded
    And prefilled with data
    When the Completed filter is clicked
    Then all completed entries should be displayed

  Scenario: Clear completed entries
    Given the ToDo page is loaded
      And prefilled with data
    When the Clear completed is clicked
    Then all active entries should be displayed
