package com.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.core.base.BaseTest;
import com.core.factory.PageFactoryManager;
import com.web.pages.PlaywrightHomePage;

import io.qameta.allure.Allure;


public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageHeader() {
        PlaywrightHomePage homePage = PageFactoryManager.get(PlaywrightHomePage.class, page);

        homePage.open();
        String header = homePage.getHeader();
        System.out.println("Header text: " + header);
        Assert.assertTrue(
                header.contains("Playwright"),
                "Expected header to contain 'Playwright' but got: " + header
        );
        Allure.step("Name: " + header);
        System.out.println("TEST CASE 01 " + header);
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
        System.out.println("TEST CASE 02 " + page.url());
    }

    @Test
    public void validateGetStartedButtonNavigation() {
        PlaywrightHomePage homePage = PageFactoryManager.get(PlaywrightHomePage.class, page);

        homePage.open();
        homePage.clickGetStarted();
        String header=homePage.getHeader();
        String currentUrl = page.url();

        Assert.assertTrue(
                currentUrl.contains("docs"),
                "Expected navigation to docs page, but got: " + currentUrl
        );
        System.out.println("TEST CASE 03 " + header);
    }
}