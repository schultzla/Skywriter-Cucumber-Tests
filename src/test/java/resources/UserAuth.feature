Feature: Skywriter user authentication

Scenario: User is logging into Skywriter as an admin
Given User is on the main login page
When User enters dev.admin into user and password
Then Skywriter logs in successfully