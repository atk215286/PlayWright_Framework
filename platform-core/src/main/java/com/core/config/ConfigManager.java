package com.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ConfigManager {

    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();

            try (InputStream is = ConfigManager.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties")) {

                if (is == null) {
                    throw new RuntimeException("config.properties not found in classpath");
                }

                properties.load(is);

            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    public static String get(String key) {
        loadProperties();

        // System property override
        String sysValue = System.getProperty(key);
        if (sysValue != null && !sysValue.isEmpty()) {
            return sysValue;
        }

        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}