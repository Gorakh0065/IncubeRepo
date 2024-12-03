Feature: Compose and Send Email 

Scenario: Positive Test - Send an email successfully 
Given I am logged into Gmail 
When I compose an email with the subject "Incubyte" and body "QA test for Incubyte" 
And I send the email to "recipient@example.com" 
Then the email should be sent successfully 

Scenario: Negative Test - Fail to send an email 
Given I am logged into Gmail 
When I compose an email without a recipient 
And I attempt to send the email 
Then I should see an error message indicating a recipient is required