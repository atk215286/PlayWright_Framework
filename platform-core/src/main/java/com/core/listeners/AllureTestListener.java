package com.core.listeners;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

public class AllureTestListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        System.setProperty("allure.results.directory",
                "target/allure-results");
    }
}