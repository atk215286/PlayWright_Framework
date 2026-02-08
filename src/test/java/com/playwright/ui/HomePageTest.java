package com.playwright.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageHeader() {
        pages.homePage().open();
        String header = pages.homePage().getHeader();

        Assert.assertTrue(header.contains("Playwright"), 
                "Expected header to contain 'Playwright' but got: " + header);
    }

    @Test
    public void verifyGetStartedNavigation() {
        pages.homePage().open();
        pages.homePage().clickGetStarted();

        Assert.assertTrue(page.url().contains("playwright.dev/docs"),
                "Expected URL to contain 'docs' but got: " + page.url());
    }

    @Test
    public void validateGetStartedButtonNavigation() {
        // Step 1: Open the homepage
        pages.homePage().open();
        // Step 2: Click the Get Started button
        // If the locator is broken, self‑healing will fix it
        pages.homePage().clickGetStarted();

        // Step 3: Validate navigation to the docs page
        String currentUrl = page.url();

        Assert.assertTrue(
            currentUrl.contains("docs"),
            "Expected navigation to docs page, but got: " + currentUrl
        );
    }
}

