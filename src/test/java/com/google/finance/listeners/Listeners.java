package com.google.finance.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.finance.extentReport.ExtentReportManager;
import com.google.finance.tests.TestBase;

public class Listeners extends TestBase implements ITestListener {

	ExtentTest test;

	ExtentReports extent = ExtentReportManager.getExtentReportObject();

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		extentTest.get().pass(MarkupHelper.createLabel("All steps were executed successfully.", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();
		extentTest.get()
				.fail(MarkupHelper.createLabel("Test failed. Review the details and the logs.", ExtentColor.RED));
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName), testMethodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test skipped.");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
