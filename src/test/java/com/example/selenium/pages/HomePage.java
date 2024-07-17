package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//tr[@class='heading3']/td")
    WebElement homePageDashboardUsername;

    @FindBy(xpath = "//a[@href='Logout.php']")
    WebElement btnLogout;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageDashboardUsername()
    {
        return homePageDashboardUsername.getText();
    }

    public void logout()
    {
        btnLogout.click();
        driver.switchTo().alert().accept();
    }
}
