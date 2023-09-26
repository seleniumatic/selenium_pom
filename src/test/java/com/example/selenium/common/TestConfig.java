package com.example.selenium.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestConfig {

    private static Properties testConfig = new Properties();

    
    static String testConfigFile = "com/example/selenium/resources/test.config";
    static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    static String testConfigPath = rootPath + testConfigFile;

    static {
        try (FileInputStream input = new FileInputStream(testConfigPath)) {
             testConfig.load(input);
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

    public static String getTestUrl() {
        return testConfig.getProperty("test.url");
    }

    public static String getUsername() {
        return testConfig.getProperty("site.username");
    }

    public static String getPassword() {
        return testConfig.getProperty("site.password");
    }
}
