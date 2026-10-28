package com.Baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Library {
    
    public static WebDriver driver;
    public static Properties prop;
    public static Logger logger;
    
    public static WebDriver initDriver() throws IOException {
        FileInputStream input = new FileInputStream("src/test/resources/Properties/Config.Property");
        prop = new Properties();
        prop.load(input);
        logger = Logger.getLogger(Library.class);
        PropertyConfigurator.configure("src/test/resources/Properties/log4j.properties");
        
        try {
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("====== Chrome Browser Launched =======");
            } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                logger.info("====== Firefox Browser Launched =======");
            }
            
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.info("====== Driver Initialized =======");
            
        } catch (Exception e) {
            logger.error("Browser didn't launch", e);
        }
        
        return driver;
    }
    
    public void launchApp() throws IOException {
        initDriver();
        driver.get(prop.getProperty("url"));
        logger.info("====== Application Launched =======");
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("====== Browser Closed =======");
        }
    }
}
