package com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Baseclass.Library;
import com.ReusableFunctions.SeleniumReusable;

public class CustomerPage extends Library {
    
    SeleniumReusable se;
    
    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        se = new SeleniumReusable(driver);
    }
    
    @FindBy(xpath = "//label[text()='Name *']/following::input[1]")
    WebElement nameField;
    
    @FindBy(xpath = "//label[text()='Photo']/following::input[1]")
    WebElement photoField;
    
    @FindBy(xpath = "//label[text()='Description']/following::textarea[1]")
    WebElement descField;
    
    @FindBy(xpath = "//label[text()='Business Type *']/following::select[1]")
    WebElement businessType;
    
    @FindBy(xpath = "//label[text()='Contact Person Name *']/following::input[1]")
    WebElement contactName;
    
    @FindBy(xpath = "//label[text()='Contact Person Phone Number *']/following::input[1]")
    WebElement contactPhone;
    
    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitBtn;
    
    @FindBy(xpath = "//div[text()='Name is required.']")
    WebElement nameError;
    
    @FindBy(xpath = "//div[text()='Business Type is required.']")
    WebElement businessError;
    
    @FindBy(xpath = "//div[text()='Contact Person Name is required.']")
    WebElement contactNameError;
    
    @FindBy(xpath = "//div[text()='Contact Person Phone Number is required.']")
    WebElement contactPhoneError;
    
    @FindBy(xpath = "//div[text()='Customer successfully created!']")
    WebElement successMsg;
    
    public void enterName(String name) {
        se.enterText(nameField, name);
    }
    
    public void uploadPhoto(String filePath) {
        photoField.sendKeys(filePath);
    }
    
    public void enterDescription(String desc) {
        se.enterText(descField, desc);
    }
    
    public void selectBusinessType(String type) {
        se.selectDropdown(businessType, type);
    }
    
    public void enterContactName(String name) {
        se.enterText(contactName, name);
    }
    
    public void enterContactPhone(String phone) {
        se.enterText(contactPhone, phone);
    }
    
    public void clickSubmit() {
        se.click(submitBtn);
    }
    
    public boolean isNameErrorDisplayed() {
        return se.isErrorDisplayed(nameError);
    }
    
    public boolean isBusinessErrorDisplayed() {
        return se.isErrorDisplayed(businessError);
    }
    
    public boolean isContactNameErrorDisplayed() {
        return se.isErrorDisplayed(contactNameError);
    }
    
    public boolean isContactPhoneErrorDisplayed() {
        return se.isErrorDisplayed(contactPhoneError);
    }
    
    public boolean isSuccessDisplayed() {
        return se.isErrorDisplayed(successMsg);
    }
    
    public void scrollToSuccessMessage() {
        se.scrollToElement(successMsg);
    }
    
    public int getNameFieldLength() {
        return se.getFieldValue(nameField).length();
    }
    
    public int getContactNameFieldLength() {
        return se.getFieldValue(contactName).length();
    }
    
    public int getContactPhoneFieldLength() {
        return se.getFieldValue(contactPhone).length();
    }
    
    public String getPhotoAcceptAttribute() {
        return se.getAttribute(photoField, "accept");
    }
    
    public String getBusinessTypeMultipleAttr() {
        return se.getAttribute(businessType, "multiple");
    }
}
