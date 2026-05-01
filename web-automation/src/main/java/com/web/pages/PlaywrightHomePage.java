package com.web.pages;

import com.core.base.BasePage;
import com.microsoft.playwright.Page;

public class PlaywrightHomePage extends BasePage {

    private static final String URL = "https://playwright.dev";

    // Use STRING selectors, not Locator objects
    private static final String GET_STARTED = "text=Get started";
   // private static final String GET_STARTED2 = "text= F started";
    private static final String HEADER = "h1";

    public PlaywrightHomePage(Page page) {
        super(page);
    }

    public void open() {
        navigate(URL);
    }

    public void clickGetStarted() {
        click(GET_STARTED);   //  matches BasePage.click(String)
    }

    public String getHeader() {
        return getText(HEADER);   //  matches BasePage.getText(String)
    }
}