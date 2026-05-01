package com.mobile.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mobile.base.BaseMobileTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/**
 * CalculatorTest
 *
 * Sample test demonstrating automation of the built-in Android Calculator app.
 * This test performs a simple addition operation (2 + 3) and validates the result.
 */
public class CalculatorTest extends BaseMobileTest {

    @Test
    public void testAddition() {

        AppiumDriver driver = getDriver();

        // Tap 1
         driver.findElement(MobileBy.id("com.google.android.calculator:id/digit_7")).click();
        driver.findElement(MobileBy.id("com.google.android.calculator:id/digit_2")).click();

        // Tap +
        driver.findElement(MobileBy.id("com.google.android.calculator:id/op_add")).click();

        // Tap 5
        driver.findElement(MobileBy.id("com.google.android.calculator:id/digit_2")).click();
         driver.findElement(MobileBy.id("com.google.android.calculator:id/digit_4")).click();

        // Tap =
       // driver.findElement(MobileBy.id("com.google.android.calculator:id/eq")).click();

        // Wait for result_preview to update
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement resultElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        MobileBy.id("com.google.android.calculator:id/result_preview")
                )
        );

        String result = resultElement.getText().trim();
        System.out.println("Result = " + result);

        Assert.assertEquals(result, "6", "Addition result is incorrect");


          }
}