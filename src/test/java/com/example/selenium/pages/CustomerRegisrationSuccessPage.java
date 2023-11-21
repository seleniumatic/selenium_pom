package com.example.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerRegisrationSuccessPage {
    
    WebDriver driver;

    public CustomerRegisrationSuccessPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='heading3']")));
        
        return driver.findElement(By.xpath("//p[@class='heading3']")).getText();
    }
}
