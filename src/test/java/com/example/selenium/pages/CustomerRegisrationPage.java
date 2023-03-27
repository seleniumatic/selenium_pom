package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerRegisrationPage {
    
    WebDriver driver;

    public CustomerRegisrationPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String getSuccessMessage() {
        return driver.findElement(By.xpath("//p[@class='heading3']")).getText();
    }
}
