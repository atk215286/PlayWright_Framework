package com.core.listeners;

import org.testng.IAlterSuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import io.qameta.allure.Allure;

public class AllureTestListener implements ITestListener, IAlterSuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
        String browser = System.getProperty("browser", "chromium");
        Allure.label("browser", browser);
        Allure.parameter("Browser", browser);
    }

    @Override
    public void alter(java.util.List<XmlSuite> suites) {
        System.setProperty("allure.results.directory", "target/allure-results");
    }
}