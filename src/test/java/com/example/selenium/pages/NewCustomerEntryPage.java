package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.selenium.common.*;

public class NewCustomerEntryPage {
    WebDriver driver;

    public NewCustomerEntryPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setName(String name)
    {
        driver.findElement(By.name("name")).sendKeys(name);
    }

    public void setBirthdate(String dob)
    {
        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        
        // calendar in Chrome use different date format than Firefox
        if (browser.equalsIgnoreCase("chrome")) {
            dob = Util.formatDateString(dob, "MM-dd-yyyy");
        }

        driver.findElement(By.name("dob")).sendKeys(dob);       
    }

    public void setAddress(String address)
    {
        driver.findElement(By.name("addr")).sendKeys(address);
    }

    public void setCity(String city)
    {
        driver.findElement(By.name("city")).sendKeys(city);
    }

    public void setState(String state)
    {
        driver.findElement(By.name("state")).sendKeys(state);
    }

    public void setPIN(String PIN)
    {
        driver.findElement(By.name("pinno")).sendKeys(PIN);
    }

    public void setMobilePhone(String mobilePhone)
    {
        driver.findElement(By.name("telephoneno")).sendKeys(mobilePhone);
    }

    public void setEmail(String email)
    {
        driver.findElement(By.name("emailid")).sendKeys(email);
    }

    public void setPassword(String password)
    {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void clickSubmit()
    {
        driver.findElement(By.name("sub")).click();
    }

    public void enterCustomerInfo(CustomerInfo customer)
    {
        setName(customer.getName());
        setBirthdate(customer.getDob());
        setAddress(customer.getAddress());
        setCity(customer.getCity());
        setState(customer.getState());
        setPIN(customer.getPin());
        setMobilePhone(customer.getMobileNumber());
        setEmail(customer.getEmail());
        setPassword(customer.getPassword());
    }

    public String getPageTitle()
    {
        return driver.findElement(By.xpath("//p[@class='heading3']")).getText();
    }
}
