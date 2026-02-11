package com.core.factory;

import com.microsoft.playwright.Page;

public class PageFactoryManager {

    public static <T> T get(Class<T> pageClass, Page page) {
        try {
            return pageClass.getDeclaredConstructor(Page.class).newInstance(page);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create page object: " + pageClass.getName(), e);
        }
    }
}