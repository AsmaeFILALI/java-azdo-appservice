Feature: the book can be retrieved
  Scenario: client makes call to GET /books
    When the client calls /books/1
    Then the client receives status code of 200
    And the client receives book author Asmae