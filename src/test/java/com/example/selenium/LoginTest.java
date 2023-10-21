package com.example.selenium;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.example.selenium.common.RetryAnalyzer;
import com.example.selenium.pages.*;

public class LoginTest extends BaseClass {

    @Test(priority=1, retryAnalyzer = RetryAnalyzer.class)
    public void testLoginHappyPath() throws InterruptedException
    {
        LoginPage login = new LoginPage(driver);

        Assert.assertTrue(login.getLoginTitle().contains("Guru99 Bank"));

        login.logInto(username, password);

        HomePage homepage = new HomePage(driver);

        Assert.assertEquals(homepage.getHomePageDashboardUsername(), "Manger Id : mngr532946");

        homepage.logout();
    }

    @Test(priority=2, retryAnalyzer = RetryAnalyzer.class)
    public void testIncorrectPassword() throws InterruptedException
    {
        LoginPage login = new LoginPage(driver);
        login.logInto("incorrect", "login");
    
        Assert.assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        
        driver.switchTo().alert().accept();

        Assert.assertTrue(login.getLoginTitle().contains("Guru99 Bank"));
    }
}
