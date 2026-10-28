package com.StepDefinitions;

import com.Baseclass.Library;

import com.Pages.CustomerPage;
import com.ReusableFunctions.SeleniumReusable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;


public class CustomerFormSteps extends Library{
	CustomerPage cp;
	SeleniumReusable se;
	
	@Given("the user is on the Create Customer page")
	public void the_user_is_on_the_create_customer_page() {
		se = new SeleniumReusable(driver);
		cp = new CustomerPage(driver);
	}
	
	// TC03 - Successful customer creation with all required fields
	@When("the user fills in all required fields correctly")
	public void the_user_fills_in_all_required_fields_correctly() {
		cp.enterName("Lei");
		cp.uploadPhoto("C:/Users/user/Downloads/ava1.jpg");
		cp.enterDescription("BS Computer Science || QA Automation");
		cp.selectBusinessType("Retail");
		cp.enterContactName("John Lei");
		cp.enterContactPhone("0123456789");
		cp.clickSubmit();
	}
	@Then("the customer should be created successfully")
	public void the_customer_should_be_created_successfully() {
		 cp.scrollToSuccessMessage();
	    boolean isDisplayed = cp.isSuccessDisplayed();
	    System.out.println("Is Success Displayed? " + isDisplayed);
	    Assert.assertTrue(isDisplayed, "Success message should be displayed");
	}
	
	// TC05 - Name field is required
	@When("the user submits the form without entering Name")
	public void the_user_submits_the_form_without_entering_name() {
		cp.enterName("");
		cp.uploadPhoto("C:/Users/user/Downloads/ava1.jpg");
		cp.enterDescription("BS Computer Science || QA Automation");
		cp.selectBusinessType("Retail");
		cp.enterContactName("John Lei");
		cp.enterContactPhone("0123456789");
		cp.clickSubmit();
	}

	@Then("a validation error for Name should be displayed")
	public void a_validation_error_for_name_should_be_displayed() {
		boolean isDisplayed = cp.isNameErrorDisplayed();
	    System.out.println("Is Name Error Displayed? " + isDisplayed);
	    Assert.assertTrue(isDisplayed, "Name error message should be displayed");
	    

	}
	
	// TC06 - Business Type is required
	@When("the user submits the form without entering Business Type")
	public void the_user_submits_the_form_without_entering_business_type() {
		cp.enterName("Lei");
		cp.uploadPhoto("C:/Users/user/Downloads/ava1.jpg");
		cp.enterDescription("BS Computer Science || QA Automation");
		cp.enterContactName("John Lei");
		cp.enterContactPhone("0123456789");
		cp.clickSubmit();
	}
	@Then("a validation error for Business Type should be displayed")
	public void a_validation_error_for_business_type_should_be_displayed() {
	    boolean isDisplayed = cp.isBusinessErrorDisplayed();
	    System.out.println("Is Business Type Error Displayed?" + isDisplayed);
	    Assert.assertTrue(isDisplayed, "Business Type error message should be displayed");
	}
	
	// TC07 - Contact Person Name is required
	@When("the user submits the form without entering Contact Person Name")
	public void the_user_submits_the_form_without_entering_contact_person_name() {
		cp.enterName("Lei");
		cp.uploadPhoto("C:/Users/user/Downloads/ava1.jpg");
		cp.enterDescription("BS Computer Science || QA Automation");
		cp.selectBusinessType("Retail");
		cp.enterContactName("");
		cp.enterContactPhone("0123456789");
		cp.clickSubmit();
	}
	@Then("a validation error for Contact Person Name should be displayed")
	public void a_validation_error_for_contact_person_name_should_be_displayed() {
	    boolean isDisplayed = cp.isContactNameErrorDisplayed();
	    System.out.println("Is Contact Person Name Error Displayed?" + isDisplayed);
	    Assert.assertTrue(isDisplayed, "Contact Person Name error message should be displayed");
	}
	
	// TC08 - Contact Person Phone Number is required
	@When("the user submits the form without entering Phone Number")
	public void the_user_submits_the_form_without_entering_phone_number() {
		cp.enterName("Lei");
		cp.uploadPhoto("C:/Users/user/Downloads/ava1.jpg");
		cp.enterDescription("BS Computer Science || QA Automation");
		cp.selectBusinessType("Retail");
		cp.enterContactName("John Lei");
		cp.clickSubmit();
	}
	@Then("a validation error for Contact Phone should be displayed")
	public void a_validation_error_for_contact_phone_should_be_displayed() {
	   boolean isDisplayed = cp.isContactPhoneErrorDisplayed();
	   System.out.println("Is Contact Phone Error Displayed?" + isDisplayed);
	   Assert.assertTrue(isDisplayed, "Contact Phone error message should be displayed");
	}
	
	// TC13 - Name field should not exceed 100 characters	
	@When("the user enters {int} characters in the Name field")
	public void the_user_enters_characters_in_the_name_field(Integer characterCount) {
		String longName = "A".repeat(characterCount); 
	    cp.enterName(longName);
	    System.out.println("Entered " + characterCount + " characters in Name field");
	}
	@Then("the Name field value should not exceed {int} characters")
	public void the_name_field_value_should_not_exceed_characters(Integer maxLength) {
		 int actualLength = cp.getNameFieldLength();
		 System.out.println("Name field length: " + actualLength);
		 System.out.println("Max allowed length: " + maxLength);
		 Assert.assertTrue(actualLength <= maxLength, "Name field should not exceed " + maxLength + " characters but found " + actualLength);
	}
	
	// TC14 - Photo uploader accepts PNG format
	@When("the user uploads a PNG file")
	public void the_user_uploads_a_png_file() {
		cp.uploadPhoto("C:/Users/user/Downloads/speedtest.png");
	}
	@Then("the file should be uploaded successfully")
	public void the_file_should_be_uploaded_successfully() {
		String fileName = cp.getPhotoAcceptAttribute();
	    Assert.assertNotNull(fileName, "File should be uploaded");
	}
	
	// TC16 - Business Type allows only single selection
	@Then("the Business Type dropdown should not allow multiple selection")
	public void the_business_type_dropdown_should_not_allow_multiple_selection() {
		String multipleAttr = cp.getBusinessTypeMultipleAttr();
	    System.out.println("Business Type 'multiple' attribute: " + multipleAttr);
	    
	    Assert.assertNull(multipleAttr, "Business Type should NOT have 'multiple' attribute (single selection only)");
	}
	
	// TC17 - Contact Person Name should not exceed 100 characters
	@When("the user enters {int} characters in the Contact Person Name field")
	public void the_user_enters_characters_in_the_contact_person_name_field(Integer characterCount) {
		String longName = "A".repeat(characterCount);
	    cp.enterContactName(longName);
	    System.out.println("Entered " + characterCount + " characters in Contact Person Name field");
	}
	@Then("the Contact Person Name field value should not exceed {int} characters")
	public void the_contact_person_name_field_value_should_not_exceed_characters(Integer maxLength) {
		int actualLength = cp.getContactNameFieldLength();
	    System.out.println("Contact Person Name field length: " + actualLength);
	    System.out.println("Max allowed length: " + maxLength);
	    Assert.assertTrue(actualLength <= maxLength, 
	        "Contact Person Name field should not exceed " + maxLength + " characters but found " + actualLength);
	}
	
	// TC18 - Contact Person Phone Number should not exceed 15 characters
	@When("the user enters {int} characters in the Contact Phone field")
	public void the_user_enters_characters_in_the_contact_phone_field(Integer characterCount) {
		 String longPhone = "1".repeat(characterCount);  // Use numbers for phone field
		 cp.enterContactPhone(longPhone);
		 System.out.println("Entered " + characterCount + " characters in Contact Phone field");
	}
	@Then("the Contact Phone field value should not exceed {int} characters")
	public void the_contact_phone_field_value_should_not_exceed_characters(Integer maxLength) {
		int actualLength = cp.getContactPhoneFieldLength();
	    System.out.println("Contact Phone field length: " + actualLength);
	    System.out.println("Max allowed length: " + maxLength);
	    Assert.assertTrue(actualLength <= maxLength, "Contact Phone field should not exceed " + maxLength + " characters but found " + actualLength);
	}

}
