package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setUserName(String username)
    {
        driver.findElement(By.name("uid")).sendKeys(username);
    }

    public void setPassword(String password)
    {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void clickLogin()
    {
        driver.findElement(By.name("btnLogin")).click();

    }

    public String getLoginTitle()
    {
        return    driver.findElement(By.className("barone")).getText();
    }
    
    public void logInto(String username, String password) throws InterruptedException
    {
        setUserName(username);
        setPassword(password);
        clickLogin();
    }
}
