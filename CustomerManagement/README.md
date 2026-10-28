# Customer Management Test Automation Framework

A comprehensive Selenium-based test automation framework for Customer Management form validation using BDD approach with Cucumber and Java.

## Overview

This project automates end-to-end testing of a Customer Management web application, covering form field validations, data integrity checks, file upload validation, and user input constraints to ensure proper customer data entry and management.

## Technologies Used

- **Java** - Programming language
- **Selenium WebDriver 4.18** - Browser automation
- **Cucumber 7.15** - BDD framework with Gherkin syntax
- **TestNG 7.9** - Test execution framework
- **Maven** - Build and dependency management
- **Page Object Model with PageFactory** - Design pattern using @FindBy annotations
- **WebDriverManager 5.6** - Automatic browser driver management
- **Log4j 1.2** - Logging framework
- **ExtentReports 5.1** - HTML test reporting with screenshots

## Project Structure

```
src/
├── main/java/com/
│   ├── Baseclass/          # Base configuration and WebDriver setup
│   ├── Hooks/              # Cucumber hooks for setup/teardown with screenshots
│   ├── ReusableFunctions/  # Selenium wrapper methods and utilities
│   └── Utilities/          # Excel utility for data-driven testing
└── test/
    ├── java/com/
    │   ├── Pages/          # Page Object Model classes with PageFactory
    │   ├── StepDefinitions/# Cucumber step definitions (10+ test scenarios)
    │   └── TestRunner/     # TestNG runner with Cucumber integration
    └── resources/
        ├── Features/       # Cucumber feature files (BDD scenarios)
        ├── Properties/     # Configuration files (Config.Property, log4j)
        └── Reports/        # Cucumber HTML/JSON reports

target/
├── cucumber-reports/       # Generated Cucumber reports
├── screenshots/            # Test execution screenshots (auto-cleared per run)
└── logs/                   # Application logs
```

## Key Features

- **BDD Approach**: Gherkin-based feature files for readable and business-friendly test scenarios
- **PageFactory Pattern**: Modern Page Object Model using @FindBy annotations for cleaner code
- **Smart Screenshot Management**: Automatic screenshot capture with one-time cleanup per test run
- **Reusable Components**: Custom Selenium wrapper methods for common actions (click, enterText, selectDropdown, etc.)
- **Comprehensive Logging**: Log4j integration with console and file appenders for debugging
- **Tag-Based Execution**: Run specific test scenarios using Cucumber tags (e.g., @TC14, @TC16)
- **Property-Based Configuration**: Externalized test data and browser settings
- **Scroll-to-Element**: Automatic scrolling for elements outside viewport

## Test Coverage

The framework covers the following Customer Management form validations:

### ✅ Required Field Validations
- **TC05**: Name field is required
- **TC06**: Business Type is required
- **TC07**: Contact Person Name is required
- **TC08**: Contact Person Phone Number is required

### ✅ Field Length Validations
- **TC13**: Name field max length (100 characters)
- **TC17**: Contact Person Name max length (100 characters)
- **TC18**: Contact Person Phone Number max length (15 characters)

### ✅ File Upload & Format Validation
- **TC14**: Photo uploader accepts valid formats (PNG, JPG, JPEG, BMP)

### ✅ Dropdown Behavior Validation
- **TC16**: Business Type allows only single selection

### ✅ Successful Submission
- **TC03**: Customer creation with all required fields

**Total Test Scenarios: 10+**

## Form Fields Tested

- **Name** (required, max 100 chars)
- **Photo** (file upload, format validation)
- **Description** (textarea)
- **Business Type** (dropdown, single selection)
- **Contact Person Name** (required, max 100 chars)
- **Contact Person Phone Number** (required, max 15 chars)

## Prerequisites

- Java JDK 8 or higher
- Maven 3.6+
- Chrome browser (default) or Firefox

## Setup & Execution

### 1. Clone the repository
```bash
git clone https://github.com/johnleiiult/Customer-Automation-Selenium.git
cd CustomerManagement
```

### 2. Update Configuration
Edit `src/test/resources/Properties/Config.Property` with your application URL and credentials

### 3. Run All Tests
```bash
mvn clean test
```

### 4. Run Specific Test Scenarios (Using Tags)
```bash
# Run only TC14
mvn test -Dcucumber.filter.tags="@TC14"

# Run TC16 and TC17
mvn test -Dcucumber.filter.tags="@TC16 or @TC17"
```

### 5. Run from Eclipse IDE
- Right-click `runner.java` → **Run As → TestNG Test**
- To run specific scenarios: Add `tags = "@TC14"` in `@CucumberOptions`

## Test Reports

### Cucumber Reports
- **HTML Report**: `target/cucumber-reports/report.html`
- **JSON Report**: `target/cucumber-reports/report.json`

### Screenshots
- Location: `target/screenshots/`
- Naming convention: `{ScenarioName}.png`
- Auto-cleanup: Old screenshots cleared before each test run

### Logs
- Console output with INFO level logging
- File logs: `target/logs/application.log`

## Sample Test Scenario (Gherkin)

```gherkin
Feature: Customer Form Field Validation

  Background:
    Given the user is on the Create Customer page

  Scenario: TC05 - Name field is required
    When the user submits the form without entering Name
    Then a validation error for Name should be displayed

  Scenario: TC13 - Name field should not exceed 100 characters
    When the user enters 101 characters in the Name field
    Then the Name field value should not exceed 100 characters
```

## Code Architecture

### Page Object Model (PageFactory)
```java
@FindBy(xpath = "//label[text()='Name *']/following::input[1]")
WebElement nameField;

public void enterName(String name) {
    se.enterText(nameField, name);
}
```

### Reusable Selenium Methods
```java
public void enterText(WebElement element, String text)
public void click(WebElement element)
public void selectDropdown(WebElement element, String value)
public void scrollToElement(WebElement element)
public boolean isErrorDisplayed(WebElement element)
```

### Hooks (Setup/Teardown)
```java
@Before - Launch browser and navigate to application
@After - Capture screenshot + close browser
```

## Best Practices Implemented

✅ **Page Object Model with PageFactory** - Cleaner and more maintainable code
✅ **BDD with Cucumber** - Business-readable test scenarios
✅ **Separation of Concerns** - Page objects, step definitions, and utilities separated
✅ **Reusable Methods** - DRY principle with SeleniumReusable class
✅ **Explicit Waits** - Proper synchronization (where needed)
✅ **Screenshot on Every Test** - Visual evidence of test execution
✅ **Logging** - Comprehensive logging for debugging
✅ **Tag-Based Execution** - Flexible test execution with Cucumber tags

## Troubleshooting

### Issue: ChromeDriver version mismatch
**Solution**: WebDriverManager automatically handles driver downloads

### Issue: Element not found
**Solution**: Check XPath locators or add explicit waits

### Issue: Screenshot folder too large
**Solution**: Screenshots auto-clear before each test run (handled in @Before hook)

## Future Enhancements

- [ ] Parallel test execution with TestNG
- [ ] Data-driven testing using Excel utility
- [ ] Cross-browser testing (Chrome, Firefox, Edge)
- [ ] CI/CD integration (Jenkins/GitHub Actions)
- [ ] ExtentReports integration for richer HTML reports

## Author

Developed as part of test automation practice for web-based customer management systems.

## License

This project is for educational and practice purposes.