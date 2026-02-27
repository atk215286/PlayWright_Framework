package com.mobile.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.mobile.drivers.AndroidDriverManager;
//import com.mobile.drivers.IOSDriverManager;
import com.mobile.utils.DeviceUtils;

import io.appium.java_client.AppiumDriver;

/**
 * BaseMobileTest
 *
 * Abstract base class for all mobile test classes.
 * Provides:
 *  - Thread-safe driver lifecycle management
 *  - Platform-specific driver initialization
 *  - Unified setup and teardown logic
 *  - Integration point for reporting and logging
 *
 * This class ensures consistent driver handling across
 * Android and iOS test suites and supports parallel execution.
 */
public abstract class BaseMobileTest {

    /**
     * ThreadLocal driver instance to support parallel execution.
     */
    private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    /**
     * Initializes the driver before each test method.
     *
     * @param platformName The mobile platform (Android or iOS)
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"platform"})
    public void setUp(@Optional("Android") String platformName) {

        AppiumDriver driver;

        if (platformName.equalsIgnoreCase("Android")) {
            driver = new AndroidDriverManager().createDriver();
        } else if (platformName.equalsIgnoreCase("iOS")) {
            driver = null;//new IOSDriverManager().createDriver();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported platform: " + platformName +
                    ". Valid values are Android or iOS."
            );
        }

        driverThread.set(driver);

        // Optional: log device summary for debugging or reporting
        System.out.println(DeviceUtils.getDeviceSummary(driver));
    }

    /**
     * Returns the driver instance for the current thread.
     */
    protected AppiumDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Cleans up the driver after each test method.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AppiumDriver driver = driverThread.get();

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
        }

        driverThread.remove();
    }
}