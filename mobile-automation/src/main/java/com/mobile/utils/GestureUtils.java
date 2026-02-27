package com.mobile.utils;

import java.util.Map;

import io.appium.java_client.AppiumDriver;

/**
 * Utility class providing reusable gesture actions for both Android and iOS.
 * Uses Appium 9.x W3C-compliant "mobile:" commands instead of deprecated TouchActions.
 * All gestures here are device-agnostic and work across platforms.
 */
public class GestureUtils {

    /**
     * Performs a swipe up gesture on the screen.
     *
     * @param driver  Active AppiumDriver instance
     * @param percent Swipe distance as a percentage of screen height (0.0 - 1.0)
     */
    public static void swipeUp(AppiumDriver driver, double percent) {
        driver.executeScript("mobile: swipeGesture", Map.of(
                "direction", "up",
                "percent", percent
        ));
    }

    /**
     * Performs a swipe down gesture.
     */
    public static void swipeDown(AppiumDriver driver, double percent) {
        driver.executeScript("mobile: swipeGesture", Map.of(
                "direction", "down",
                "percent", percent
        ));
    }

    /**
     * Performs a swipe left gesture.
     */
    public static void swipeLeft(AppiumDriver driver, double percent) {
        driver.executeScript("mobile: swipeGesture", Map.of(
                "direction", "left",
                "percent", percent
        ));
    }

    /**
     * Performs a swipe right gesture.
     */
    public static void swipeRight(AppiumDriver driver, double percent) {
        driver.executeScript("mobile: swipeGesture", Map.of(
                "direction", "right",
                "percent", percent
        ));
    }

    /**
     * Performs a tap at specific screen coordinates.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public static void tap(AppiumDriver driver, int x, int y) {
        driver.executeScript("mobile: clickGesture", Map.of(
                "x", x,
                "y", y
        ));
    }

    /**
     * Performs a long press at specific coordinates.
     *
     * @param durationMs Duration in milliseconds
     */
    public static void longPress(AppiumDriver driver, int x, int y, int durationMs) {
        driver.executeScript("mobile: longClickGesture", Map.of(
                "x", x,
                "y", y,
                "duration", durationMs
        ));
    }
}