package com.Hooks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.Baseclass.Library;
import com.ReusableFunctions.SeleniumReusable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks extends Library {
    
    SeleniumReusable se;
    public static Scenario scenario;
    private static boolean screenshotsCleared = false;
    
    @Before
    public void setUp(Scenario cucumberScenario) throws IOException {
        scenario = cucumberScenario;
        
        // Delete old screenshots ONCE per test run (not per scenario)
        if (!screenshotsCleared) {
            File screenshotDir = new File("target/screenshots");
            if (screenshotDir.exists()) {
                File[] oldScreenshots = screenshotDir.listFiles();
                if (oldScreenshots != null) {
                    for (File file : oldScreenshots) {
                        file.delete();
                    }
                }
                System.out.println("=== Old screenshots cleared ===");
            }
            screenshotsCleared = true;
        }
        
        launchApp();
    }
    
    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        se = new SeleniumReusable(driver);
        
        // Take screenshot for EVERY test (pass or fail)
        try {
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String screenshotName = scenarioName + ".png";  // Simple name, NO timestamp
            
            // Save to file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("target/screenshots/" + screenshotName));
            
            // Attach to Cucumber report
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", scenarioName);
            
            System.out.println("Screenshot saved: " + screenshotName);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        
        Thread.sleep(2000);
        quitDriver();
    }
}
