package com.example.selenium;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.example.selenium.common.TestConfig;
import com.example.selenium.common.Util;

public class BaseClass {
    protected Logger logger;
    protected String testName;

    WebDriver driver;
    static String testUrl = TestConfig.getTestUrl();
    static String username = TestConfig.getUsername();
    static String password = TestConfig.getPassword();

    @BeforeClass
    public void setUp() throws Exception 
    {
        logger = LogManager.getLogger(this.getClass());

        driver = Util.setUpDriver(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(testUrl);

        Util.manageCookieAcceptBanner(driver);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        testName = result.getMethod().getMethodName();
        logger.info("Running test: {}", testName);
    }
}
