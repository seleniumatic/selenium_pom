package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String getHomePageDashboardUsername()
    {
        return driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
    }

    public void logout() throws InterruptedException
    {
        driver.findElement(By.xpath("//a[@href='Logout.php']")).click();

        Thread.sleep(2000);

        driver.switchTo().alert().accept();
    }
}
