package com.ReusableFunctions;

import com.Baseclass.Library;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class SeleniumReusable extends Library{
	
	public SeleniumReusable(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterText (WebElement element, String text) {
		try {
			element.sendKeys(text);
			logger.info("===== Entered Value =====");
		}catch (Exception e) {
			System.out.println("No such element Exception");
		}
	}
	
	public void uploadFile(WebElement element, String filePath) {
	    try {
	        element.sendKeys(filePath);
	        logger.info("===== File Uploaded =====");
	    } catch (Exception e) {
	        System.out.println("File upload failed");
	    }
	}

	
	public void click(WebElement element) {
		try {
			element.click();
			logger.info("===== Clicked Button =====");
		} catch (Exception e) {
			System.out.println("No such element Exception");
		}
	}
	
	public boolean isErrorDisplayed(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        System.out.println("No such element Exception");
	        return false;
	    }
	}
	
	public void selectDropdown(WebElement element, String value) {
	    try {
	        WebElement option = element.findElement(By.xpath(".//option[text()='" + value + "']"));
	        option.click();
	        logger.info("===== Selected Dropdown =====");
	    } catch (Exception e) {
	        System.out.println("Dropdown selection failed");
	    }
	}

	
	public String getFieldValue(WebElement element) {
	    try {
	        return element.getAttribute("value");
	    } catch (Exception e) {
	        System.out.println("No such element Exception");
	        return null;
	    }
	}

	public String getAttribute(WebElement element, String attribute) {
	    try {
	        return element.getAttribute(attribute);
	    } catch (Exception e) {
	        System.out.println("No such element Exception");
	        return null;
	    }
	}
	
	public void scrollToElement(WebElement element) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        Thread.sleep(500);
	        logger.info("===== Scrolled to Element =====");
	    } catch (Exception e) {
	        System.out.println("Scroll failed");
	    }
	}


	
}
