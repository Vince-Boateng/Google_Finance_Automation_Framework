package com.google.finance.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.finance.utilities.ConfigurationReader;

import java.io.File;

public class ExtentReportManager {

    public static ExtentSparkReporter googleFinanceReporter = null;
    static ExtentReports extent = null;
    public static ExtentTest test = null;

    public static ExtentReports getExtentReportObject(){

        try{
            String path = System .getProperty("user.dir")+"\\reports\\Google_Finance_Automation_Report.html";
            googleFinanceReporter = new ExtentSparkReporter(path);
            extent = new ExtentReports();

            final File CONF = new File("src\\test\\resources\\xmlRunners\\extentConfig.xml");

            googleFinanceReporter.loadXMLConfig(CONF);

            extent.attachReporter(googleFinanceReporter);
            extent.setSystemInfo("Testing Environment", ConfigurationReader.getProperty("testEnvUrl"));
            extent.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
            extent.setSystemInfo("Architecture",  System.getProperty("os.arch"));
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version",  System.getProperty("java.version"));
            extent.setSystemInfo("Tester",   ConfigurationReader.getProperty("tester"));

        }catch (Exception e){
            e.getStackTrace();
        }
        return extent;
    }
}
