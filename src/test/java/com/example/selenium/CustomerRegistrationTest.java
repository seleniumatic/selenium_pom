package com.example.selenium;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.example.selenium.common.Customer;
import com.example.selenium.common.Util;
import com.example.selenium.pages.NewCustomerEntryPage;
import com.example.selenium.pages.CustomerRegisrationSuccessPage;
import com.example.selenium.pages.LoginPage;

public class CustomerRegistrationTest extends BaseClass {

    @Test(priority = 1)
    public void testCustomerRegisterationHappyPath() throws IOException, InterruptedException
    {

        LoginPage login = new LoginPage(driver);
        login.logInto(username, password);

        driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).click();

        NewCustomerEntryPage entryPage = new NewCustomerEntryPage(driver);

        Customer customer = Util.createRandomCustomer();

        entryPage.submitCustomer(customer);

        CustomerRegisrationSuccessPage success = new CustomerRegisrationSuccessPage(driver);
        Assert.assertTrue(success.getSuccessMessage().contains("Customer Registered Successfully!!!"));
    }

    @Test(priority = 2, dependsOnMethods = {"testCustomerRegisterationHappyPath"})
    public void testCustomerRegisterationEmptyForm() throws IOException
    {
        driver.get("https://demo.guru99.com/V4/manager/addcustomerpage.php");
        NewCustomerEntryPage entryPage = new NewCustomerEntryPage(driver);
        entryPage.clickSubmit();
        driver.switchTo().alert().accept();

        Assert.assertTrue(entryPage.getPageTitle().contains("Add New Customer"));
    }
}
