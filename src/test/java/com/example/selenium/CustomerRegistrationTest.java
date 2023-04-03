package com.example.selenium;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.selenium.common.Customer;
import com.example.selenium.common.Util;
import com.example.selenium.pages.NewCustomerEntryPage;

import com.example.selenium.pages.CustomerRegisrationPage;
import com.example.selenium.pages.LoginPage;

public class CustomerRegistrationTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception
    {
        driver = Util.setupParameters(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://demo.guru99.com/V4/");

        Util.manageCookieAcceptBanner(driver);

        LoginPage login = new LoginPage(driver);
        login.logInto("mgr123", "mgr!23");
    }

    @Test(priority = 1)
    public void testCustomerRegisterationHappyPath() throws IOException
    {
        driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).click();

        NewCustomerEntryPage entryPage = new NewCustomerEntryPage(driver);

        Customer customer = Util.createRandomCustomer();

        entryPage.submitCustomer(customer);

        //driver.switchTo().alert().accept();

        CustomerRegisrationPage success = new CustomerRegisrationPage(driver);
        Assert.assertTrue(success.getSuccessMessage().contains("Customer Registered Successfully!!!"));
    }

    @Test(priority = 2, dependsOnMethods = {"testCustomerRegisterationHappyPath"})
    public void testCustomerRegisterationEnmptyForm() throws IOException
    {
        driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
        NewCustomerEntryPage entryPage = new NewCustomerEntryPage(driver);
        entryPage.clickSubmit();
        driver.switchTo().alert().accept();

        Assert.assertTrue(entryPage.getPageTitle().contains("Add New Customer"));
    }
    
    // @AfterTest
    // public void closeBrowser()
    // {
    //     driver.close();
    // }
}
