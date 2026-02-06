package com.playwright.pages;

import com.microsoft.playwright.Page;
import com.playwright.pages.app1.PlaywrightHomePage;

public class PageFactoryManager {

    private final Page page;

    public PageFactoryManager(Page page) {
        this.page = page;
    }

    public PlaywrightHomePage homePage() {
        return new PlaywrightHomePage(page);
    }

    // Add more pages here

}