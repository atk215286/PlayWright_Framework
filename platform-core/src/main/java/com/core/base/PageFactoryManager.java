package com.core.base;

import com.microsoft.playwright.Page;
//import com.web.pages.PlaywrightHomePage;


public class PageFactoryManager {

    private final Page page;

    public PageFactoryManager(Page page) {
        this.page = page;
    }

   // public PlaywrightHomePage homePage() {
       //return new PlaywrightHomePage(page);
    //}

    // Add more pages here

}