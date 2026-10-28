Feature: Customer Form Field Validation

  Background:
    Given the user is on the Create Customer page
    
    Scenario: TC03 - Successful customer creation with all required fields
    	When the user fills in all required fields correctly
    	Then the customer should be created successfully

	Scenario: TC05 - Name field is required
    	When the user submits the form without entering Name
    	Then a validation error for Name should be displayed
    
	Scenario: TC06 - Business Type is required
    	When the user submits the form without entering Business Type
    	Then a validation error for Business Type should be displayed
    	
	Scenario: TC07 - Contact Person Name is required
    	When the user submits the form without entering Contact Person Name
    	Then a validation error for Contact Person Name should be displayed
    	
    Scenario: TC08 - Contact Person Phone Number is required
    	When the user submits the form without entering Phone Number
    	Then a validation error for Contact Phone should be displayed

  	Scenario: TC13 - Name field should not exceed 100 characters
    	When the user enters 101 characters in the Name field
    	Then the Name field value should not exceed 100 characters
  
    @TC14
	Scenario: TC14 - Photo uploader accepts PNG format
  		When the user uploads a PNG file
  		Then the file should be uploaded successfully
  		
	@TC16
  	Scenario: TC16 - Business Type allows only single selection
    	Then the Business Type dropdown should not allow multiple selection

	@TC17
  	Scenario: TC17 - Contact Person Name should not exceed 100 characters
    	When the user enters 101 characters in the Contact Person Name field
    	Then the Contact Person Name field value should not exceed 100 characters

	@TC18
  	Scenario: TC18 - Contact Person Phone Number should not exceed 15 characters
    	When the user enters 16 characters in the Contact Phone field
    	Then the Contact Phone field value should not exceed 15 characters
##
#
##
