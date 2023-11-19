package com.example.selenium.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
    
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    public static WebDriver createWebDriver(String browser, String gridUrl) throws MalformedURLException {
        if (gridUrl == null) {
            return createLocalWebDriver(browser);
        } else {
            return createRemotWebDriver(browser, gridUrl);
        }
    }

    private static WebDriver createLocalWebDriver(String browser) {
        if (browser.equalsIgnoreCase(CHROME)) {
            // currently webdrivermanager doesn't support latest Chrome
            // WebDriverManager.firefoxdriver().setup();
            
            System.setProperty("webdriver.chrome.driver", "src\\test\\java\\com\\example\\selenium\\drivers\\chromedriver.exe");
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase(FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemotWebDriver(String browser, String gridUrl) throws MalformedURLException {
        if (browser.equalsIgnoreCase(CHROME)) {
            return new RemoteWebDriver(new URL(gridUrl), new ChromeOptions());
        } else if (browser.equalsIgnoreCase(FIREFOX)) {
            return new RemoteWebDriver(new URL(gridUrl), new FirefoxOptions());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
