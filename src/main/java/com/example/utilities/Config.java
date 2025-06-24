package com.example.utilities;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties file in the classpath.");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    // Base URL from config.properties
    public static final String BASE_URL = properties.getProperty("base_url", "https://new.rayyan.ai/");

    // ChromeDriver path from environment variable or config.properties
    public static final String CHROME_DRIVER_PATH = System.getenv("CHROME_DRIVER_PATH") != null
            ? System.getenv("CHROME_DRIVER_PATH")
            : properties.getProperty("chrome_driver_path", "Applications/chromedriver");

    public static final String EMAIL = properties.getProperty("email");
    public static final String PASSWORD = properties.getProperty("password");
}