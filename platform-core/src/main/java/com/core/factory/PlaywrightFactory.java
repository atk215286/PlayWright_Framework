package com.core.factory;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    public Playwright getPlaywright() {
        return Playwright.create();
    }

    public Browser getBrowser(Playwright playwright) {
        return playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    public BrowserContext getBrowserContext(Browser browser) {
        return browser.newContext();
    }
}