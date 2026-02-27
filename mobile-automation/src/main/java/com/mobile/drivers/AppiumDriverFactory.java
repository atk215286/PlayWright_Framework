package com.mobile.drivers;

import com.mobile.enums.PlatformType;

import io.appium.java_client.AppiumDriver;

public class AppiumDriverFactory {

    public static AppiumDriver createDriver(PlatformType platform) {

        return switch (platform) {
            case ANDROID -> new AndroidDriverManager().createDriver();
            //case IOS -> new IOSDriverManager().createDriver();
            default -> throw new IllegalArgumentException("Unexpected value: " + platform);
        };
    }
}