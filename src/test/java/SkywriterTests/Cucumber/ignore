Feature: Adding a contact to an account on Skywriter

Scenario Outline: User is adding a contact
	Given User is on the add contact page
	When User enters "<first>" and "<last>" name
	Then Skywriter "<result>" creating contact

  	Examples:
    		|  first 		|   last	 		|  result 				|
    		|  First   		|  Last  		|  Passes			   	|
    		|  FirstOnly  	|    	 		|  Fails				   	|
    		|     			|  LastOnly		|  Passes			   	|
    		