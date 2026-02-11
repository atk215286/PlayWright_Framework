package com.web;

import com.core.base.BaseTest;
import com.core.factory.PageFactoryManager;
import com.web.pages.PlaywrightHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageHeader() {
        PlaywrightHomePage homePage = PageFactoryManager.get(PlaywrightHomePage.class, page);

        homePage.open();
        String header = homePage.getHeader();

        Assert.assertTrue(
                header.contains("Playwright"),
                "Expected header to contain 'Playwright' but got: " + header
        );
    }

    @Test
    public void verifyGetStartedNavigation() {
        PlaywrightHomePage homePage = PageFactoryManager.get(PlaywrightHomePage.class, page);

        homePage.open();
        homePage.clickGetStarted();

        Assert.assertTrue(
                page.url().contains("playwright.dev/docs"),
                "Expected URL to contain 'docs' but got: " + page.url()
        );
    }

    @Test
    public void validateGetStartedButtonNavigation() {
        PlaywrightHomePage homePage = PageFactoryManager.get(PlaywrightHomePage.class, page);

        homePage.open();
        homePage.clickGetStarted();

        String currentUrl = page.url();

        Assert.assertTrue(
                currentUrl.contains("docs"),
                "Expected navigation to docs page, but got: " + currentUrl
        );
    }
}