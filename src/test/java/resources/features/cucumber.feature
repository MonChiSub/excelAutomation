Feature: Writing an email for a reference

  Scenario: I send an email asking for an employee reference
    Given The employee has provided their details to me
    When I am writing an email for the reference
    Then Send the email to the recipient
