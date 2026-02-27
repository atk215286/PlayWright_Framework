package com.mobile.drivers;

import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mobile.config.CapabilityLoader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDriverManager {

    private static final String CAPABILITY_PATH =
            "src/main/resources/android/capabilities.json";

    private static final String APPIUM_SERVER_URL =
            "http://127.0.0.1:4723/wd/hub";

    public AppiumDriver createDriver() {
        try {
            JsonObject caps = CapabilityLoader.load(CAPABILITY_PATH);

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Correct type-safe capability loading
            for (String key : caps.keySet()) {
                JsonElement value = caps.get(key);

                if (value.isJsonPrimitive()) {
                    if (value.getAsJsonPrimitive().isBoolean()) {
                        capabilities.setCapability(key, value.getAsBoolean());
                    } else if (value.getAsJsonPrimitive().isNumber()) {
                        capabilities.setCapability(key, value.getAsNumber());
                    } else {
                        capabilities.setCapability(key, value.getAsString());
                    }
                } else {
                    capabilities.setCapability(key, value.toString());
                }
            }

            return new AndroidDriver(
                    new URL(APPIUM_SERVER_URL),
                    capabilities
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to initialize AndroidDriver (Appium 7.6.0). " +
                    "Check Appium server, capabilities, and device configuration.",
                    e
            );
        }
    }
}