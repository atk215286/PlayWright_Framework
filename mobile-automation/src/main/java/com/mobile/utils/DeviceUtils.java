package com.mobile.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * DeviceUtils (Appium 7.6.0 + Selenium 3.141.59 compatible)
 *
 * Utility class for retrieving device-level information for Android and iOS.
 * Uses Selenium 3.x capability access patterns and avoids Selenium 4 APIs.
 */
public final class DeviceUtils {

    private DeviceUtils() {
        // Prevent instantiation
    }

    /**
     * Returns the platform name (Android or iOS).
     */
    public static String getPlatformName(AppiumDriver driver) {
        Capabilities caps = driver.getCapabilities();
        Object platform = caps.getCapability("platformName");
        return platform != null ? platform.toString() : "Unknown Platform";
    }

    /**
     * Returns the device name as reported by the driver capabilities.
     */
    public static String getDeviceName(AppiumDriver driver) {
        Object name = driver.getCapabilities().getCapability("deviceName");
        return name != null ? name.toString() : "Unknown Device";
    }

    /**
     * Returns the platform version (Android version or iOS version).
     */
    public static String getPlatformVersion(AppiumDriver driver) {
        Object version = driver.getCapabilities().getCapability("platformVersion");
        return version != null ? version.toString() : "Unknown Version";
    }

    /**
     * Returns the device UDID.
     */
    public static String getUDID(AppiumDriver driver) {
        Object udid = driver.getCapabilities().getCapability("udid");
        return udid != null ? udid.toString() : "Unknown UDID";
    }

    /**
     * Returns the screen size of the device.
     */
    public static Dimension getScreenSize(AppiumDriver driver) {
        return driver.manage().window().getSize();
    }

    /**
     * Returns true if the device is an Android emulator.
     */
    public static boolean isAndroidEmulator(AppiumDriver driver) {
        if (driver instanceof AndroidDriver) {
            Object avd = driver.getCapabilities().getCapability("avd");
            return avd != null;
        }
        return false;
    }

    /**
     * Returns true if the device is an iOS simulator.
     */
    public static boolean isIOSSimulator(AppiumDriver driver) {
        if (driver instanceof IOSDriver) {
            Object sim = driver.getCapabilities().getCapability("simulator");
            return sim != null && Boolean.parseBoolean(sim.toString());
        }
        return false;
    }

    /**
     * Returns the Android app package if available.
     */
    public static String getAndroidAppPackage(AppiumDriver driver) {
        if (driver instanceof AndroidDriver) {
            Object pkg = driver.getCapabilities().getCapability("appPackage");
            return pkg != null ? pkg.toString() : "Unknown Package";
        }
        return "Not Applicable";
    }

    /**
     * Returns the Android app activity if available.
     */
    public static String getAndroidAppActivity(AppiumDriver driver) {
        if (driver instanceof AndroidDriver) {
            Object activity = driver.getCapabilities().getCapability("appActivity");
            return activity != null ? activity.toString() : "Unknown Activity";
        }
        return "Not Applicable";
    }

    /**
     * Returns the iOS bundleId if available.
     */
    public static String getIOSBundleId(AppiumDriver driver) {
        if (driver instanceof IOSDriver) {
            Object bundle = driver.getCapabilities().getCapability("bundleId");
            return bundle != null ? bundle.toString() : "Unknown BundleId";
        }
        return "Not Applicable";
    }

    /**
     * Returns a formatted summary of device information.
     */
    public static String getDeviceSummary(AppiumDriver driver) {
        StringBuilder sb = new StringBuilder();
        sb.append("Platform: ").append(getPlatformName(driver)).append("\n");
        sb.append("Device Name: ").append(getDeviceName(driver)).append("\n");
        sb.append("Platform Version: ").append(getPlatformVersion(driver)).append("\n");
        sb.append("UDID: ").append(getUDID(driver)).append("\n");
        sb.append("Screen Size: ").append(getScreenSize(driver)).append("\n");

        if (driver instanceof AndroidDriver) {
            sb.append("App Package: ").append(getAndroidAppPackage(driver)).append("\n");
            sb.append("App Activity: ").append(getAndroidAppActivity(driver)).append("\n");
        }

        if (driver instanceof IOSDriver) {
            sb.append("Bundle ID: ").append(getIOSBundleId(driver)).append("\n");
        }

        return sb.toString();
    }
}