package com.salesforce.utility;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class listener implements ITestListener

{
	public static  GenerateReportsPOM report;
	

	public void onTestStart(ITestResult result) {
		report=GenerateReportsPOM.getInstance();
		report.startExtentReport();
	}

	public void onTestSuccess(ITestResult result) {
		report.logTestPassed();
	}

	public void onTestFailure(ITestResult result) {
		report.logTestFailed();
		//create screenshot method in generate reports
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onStart(String method) {
		//report.startSingleTestReport( method);
	}

	public void onFinish(ITestContext context) {
		//report.endReport();
		
	}
	

}
