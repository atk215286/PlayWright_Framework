package com.core.base;

import java.util.Arrays;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected PageFactoryManager pages;

    @BeforeSuite
    public void setupSuite() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized"))
        );
    }

    @BeforeMethod
    public void setupTest() {
        context = browser.newContext(
         new Browser.NewContextOptions()
            .setViewportSize(null)
        );
        page = context.newPage();
        pages = new PageFactoryManager(page);
    }

    @AfterMethod
    public void tearDownTest() {
        if (context != null) {
            context.close();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}

