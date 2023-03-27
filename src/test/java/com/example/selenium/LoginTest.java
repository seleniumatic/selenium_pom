package com.example.selenium;

import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.example.selenium.common.Util;
import com.example.selenium.pages.*;

public class LoginTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception
    {
        System.setProperty("webdriver.firefox.driver", ".//driver//geckodriver.exe");

        FirefoxProfile customProfile = new FirefoxProfile();

        customProfile.setPreference("dom.disable_beforeunload", true);  
        
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://demo.guru99.com/V4/");

        Util.manageCookieAcceptBanner(driver);
    }

    @Test(priority=1)
    public void testLoginHappyPath() throws InterruptedException
    {
        LoginPage login = new LoginPage(driver);
        
        Assert.assertTrue(login.getLoginTitle().contains("Guru99 Bank"));
        
        login.logInto("mgr123", "mgr!23");

        HomePage homepage = new HomePage(driver);

        Assert.assertEquals(homepage.getHomePageDashboardUsername(), "Manger Id : mgr123");

        homepage.logout();
    }

    @Test(priority=2)
    public void testIncorrectPassword() throws InterruptedException
    {
        LoginPage login = new LoginPage(driver);
        login.logInto("mgr123", "mgr");
        
        new WebDriverWait(driver, Duration.ofSeconds(5)).ignoring(NoAlertPresentException.class)
            .until(ExpectedConditions.alertIsPresent());
    
        driver.switchTo().alert().accept();

        Assert.assertTrue(login.getLoginTitle().contains("Guru99 Bank"));
    }
    
    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
