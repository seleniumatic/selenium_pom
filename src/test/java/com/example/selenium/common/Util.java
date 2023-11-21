package com.example.selenium.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Util {
    public static String sendGET(URL url) throws IOException {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        return content.toString();
    }

    public static JsonNode getRandomCustomerJsonNode() throws IOException {
        URL url = new URL("https://random-data-api.com/api/v2/users?size=1&is_xml=false");
        
        String customerJson = sendGET(url);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode customerJsonNode = objectMapper.readTree(customerJson);

        return customerJsonNode;
    }

    public static CustomerInfo createRandomCustomer() throws IOException {

        JsonNode customerJsonNode = getRandomCustomerJsonNode();
        
        CustomerInfo customer = new CustomerInfoBuilder()
                            .setName(customerJsonNode.get("first_name").asText() + " " + customerJsonNode.get("last_name").asText())
                            .setGender(customerJsonNode.get("gender").asText())
                            .setDob(customerJsonNode.get("date_of_birth").asText())
                            .setAddress(customerJsonNode.get("address").get("street_address").asText())
                            .setCity(customerJsonNode.get("address").get("city").asText())
                            .setState(customerJsonNode.get("address").get("state").asText())
                            .setPin("123456")
                            .setMobileNumber(customerJsonNode.get("phone_number").asText().replaceAll("[^0-9]", ""))
                            .setEmail(customerJsonNode.get("email").asText())
                            .setPassword("password1")
                            .build();

        return customer;
    }

    public static void manageCookieAcceptBanner(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ccpa-consent-notice"));

        driver.findElement(By.xpath("//a[@class='do-not-sell mat-button mat-button-base mat-stroked-button']")).click();
        driver.findElement(By.xpath("//button[@class='okButton mat-raised-button mat-button-base mat-primary']")).click();
        driver.switchTo().defaultContent();
    }

    public static WebDriver setUpDriver(WebDriver driver) throws IOException {

        String browser = System.getProperty("browser", "Firefox");
        String gridUrl = System.getProperty("gridUrl", null);

        return WebDriverFactory.createWebDriver(browser, gridUrl);
    }

    public static String formatDateString(String date, String pattern) {
        
        LocalDate localDate = java.time.LocalDate.parse(date);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(pattern);
       
        return localDate.format(outputFormatter);
    }
}
