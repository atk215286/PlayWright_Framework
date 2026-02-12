package com.core.factory;


import java.nio.file.Paths;
import java.util.List;

import com.core.config.ConfigManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;

    public Playwright getPlaywright() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    public Browser getBrowser(Playwright playwright) {

        String browserName = ConfigManager.get("browser").toLowerCase();
        boolean headless = ConfigManager.getBoolean("headless");

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setArgs(List.of("--start-maximized"));

        switch (browserName) {
            case "chromium":
                browser = playwright.chromium().launch(options);
                break;

            case "firefox":
                browser = playwright.firefox().launch(options);
                break;

            case "webkit":
                browser = playwright.webkit().launch(options);
                break;

            case "edge":
                options.setExecutablePath(Paths.get("C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe"));
                browser = playwright.chromium().launch(options);
            break;

            default:
                throw new RuntimeException("Invalid browser: " + browserName);
        }

        return browser;
    }

    public BrowserContext getBrowserContext(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null) // auto-maximize
        );
    }
}