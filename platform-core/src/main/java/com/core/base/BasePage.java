package com.core.base;
import com.core.ai.SelfHealingClient;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected final Page page;
   private final SelfHealingClient healer = new SelfHealingClient();

    protected BasePage(Page page) {
        this.page = page;
    }

    // ---------------------------------------------------------
    // Core healing wrapper
    // ---------------------------------------------------------
    private Locator resolveLocator(String selector) {
        Locator locator = page.locator(selector);

        try {
            locator.waitFor(new Locator.WaitForOptions().setTimeout(2000));
            return locator;

        } catch (Exception e) {
            System.out.println("!!! Locator failed: " + selector);
            System.out.println(" Attempting self‑healing...");

           // String dom = page.content();
            String dom = page.locator("html").innerHTML();
            String healedSelector = healer.getHealedLocator(selector, dom);

            System.out.println(" Healed locator: " + healedSelector);

            return page.locator(healedSelector);
        }
    }

    // ---------------------------------------------------------
    // Actions (auto‑healing)
    // ---------------------------------------------------------
    public void click(String selector) {
    Locator loc = resolveLocator(selector);
    loc.click();
    }

    public String getText(String selector) {
        Locator loc = resolveLocator(selector);
        return loc.textContent();
    }

    public void fill(String selector, String text) {
        Locator loc = resolveLocator(selector);
        loc.fill(text);
    }

    
    public boolean isVisible(String selector) {
        Locator loc = resolveLocator(selector);
        return loc.isVisible();
    }

    // ---------------------------------------------------------
    // Navigation
    // ---------------------------------------------------------
    public void navigate(String url) {
        page.navigate(url);
    }
}