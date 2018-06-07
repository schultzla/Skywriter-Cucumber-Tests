Feature: User Authentication

	Scenario Outline: User is logging into Skywriter
		Given User is on the main login page
		When User enters "<user>" and "<password>" as credentials
		Then Logging in "<result>"
		
		Examples:
    		|  user	 		|   password	 	|  result 				|
    		|  dev.admin  	|  admin  		|  Passes			   	|