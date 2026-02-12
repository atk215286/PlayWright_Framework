package com.core.base;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.core.factory.PlaywrightFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import io.qameta.allure.Attachment;

public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        PlaywrightFactory factory = new PlaywrightFactory();
        playwright = factory.getPlaywright();
        browser = factory.getBrowser(playwright);
        context = factory.getBrowserContext(browser);

        // Start tracing
        context.tracing().start(new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true)
            .setSources(true));

        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        // Attach screenshot on failure
        if (!result.isSuccess()) {
        attachScreenshot();
        attachTrace();
        }

        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
public byte[] attachScreenshot() {
    return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
}

@Attachment(value = "Playwright Trace", type = "application/zip")
public byte[] attachTrace() {
    try {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/allure-results/trace.zip")));
        return Files.readAllBytes(Paths.get("target/allure-results/trace.zip"));
    } catch (Exception e) {
        return null;
    }
}
}