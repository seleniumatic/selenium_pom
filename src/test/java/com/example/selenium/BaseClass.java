package com.example.selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.example.selenium.common.TestConfig;
import com.example.selenium.common.Util;

public class BaseClass {

    WebDriver driver;
    static String testUrl = TestConfig.getTestUrl();
    static String username = TestConfig.getUsername();
    static String password = TestConfig.getPassword();

    @BeforeTest
    public void setUp() throws Exception
    {
        driver = Util.setupParameters(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get(testUrl);

        Util.manageCookieAcceptBanner(driver);
    }

    @AfterTest
    public void closeBrowser()
    {
        // driver.quit();
    }
}
