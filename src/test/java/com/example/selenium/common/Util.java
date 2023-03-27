package com.example.selenium.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Util {
    public static String sendGET(URL url) throws IOException
    {
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

    public static Customer createRandomCustomer() throws IOException
    {
        JsonNode customerJsonNode = getRandomCustomerJsonNode();
        
        Customer customer = new Customer();

        customer.setName(customerJsonNode.get("first_name").asText() + " " + customerJsonNode.get("last_name").asText());
        customer.setGender(customerJsonNode.get("gender").asText());
        customer.setDOB(customerJsonNode.get("date_of_birth").asText());
        customer.setAddress(customerJsonNode.get("address").get("street_address").asText());
        customer.setCity(customerJsonNode.get("address").get("city").asText());
        customer.setState(customerJsonNode.get("address").get("state").asText());
        customer.setPIN("123456");
        customer.setMobileNumber(customerJsonNode.get("phone_number").asText().replaceAll("[^0-9]", ""));
        customer.setEmail(customerJsonNode.get("email").asText());
        customer.setPassword("password1");

        return customer;
    }

    public static void manageCookieAcceptBanner(WebDriver driver)
    {
        driver.switchTo().frame("ccpa-consent-notice");
        driver.findElement(By.xpath("//a[@class='do-not-sell mat-button mat-button-base mat-stroked-button']")).click();
        driver.findElement(By.xpath("//button[@class='okButton mat-raised-button mat-button-base mat-primary']")).click();
        driver.switchTo().defaultContent();
    }
}
