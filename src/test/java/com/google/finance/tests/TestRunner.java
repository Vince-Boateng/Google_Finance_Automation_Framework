package com.google.finance.tests;

import com.google.finance.listeners.Listeners;

import org.testng.TestNG;

public class TestRunner {

    static TestNG testNG;

    public static void main(String[] args) {

        Listeners listeners = new Listeners();
        testNG = new TestNG();

        testNG.setTestClasses(new Class[] {GoogleFinancePageTests.class});
        testNG.addListener(listeners);
        testNG.run();
    }
}
