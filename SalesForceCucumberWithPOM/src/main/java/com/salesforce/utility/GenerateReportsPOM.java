package com.salesforce.utility;

import java.io.IOException;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReportsPOM 
{
	static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static  ExtentTest logger;
   private static  GenerateReportsPOM ob;
    
    // creating one private constructor and one public method
    //because am making GenerateReportsPOM as a singleton class
    private GenerateReportsPOM()
    {
    	
    }
    // creating getinstance method with return type is GenerateReports
    public  static  GenerateReportsPOM getInstance()
    {
    	if(ob==null)
    	{
    		 ob=new GenerateReportsPOM();
    	}
    	return ob;
    }
     static String testcaseName=null;
   // Generate single report file
    public  void startExtentReport()
    {
    	htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
      	 extent = new ExtentReports();

    htmlReporter.config().setDocumentTitle("Test Execution Report");
      	 htmlReporter.config().setReportName("sales force application tests");
      	 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
      	 htmlReporter.config().setTheme(Theme.STANDARD);

      	 extent.attachReporter(htmlReporter);
      	 extent.setSystemInfo("Host Name", "Salesforce");
      	 extent.setSystemInfo("Environment", "Automation Testing");
      	 extent.setSystemInfo("User Name", "Swathi");
    }
    //generate single test report in report file
    public static    void startSingleTestReport( String testName)
    {
    	testcaseName = testName;
    	logger = extent.createTest(testName);
    	

    }
    //log information
    public  void logTestInfo(String message)
    {
    	logger.log(Status.INFO, message);
    }
    // passed
    public void logTestPassed()
    {
    	logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
    }
    //failed
    public void logTestFailed()
    {
    	logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "is not passTest", ExtentColor.RED));
    }
    public void endReport()
    {
    	extent.flush();
    }
    public static void attachScreenshot(String imagename) throws IOException
    {
    	logger.addScreenCaptureFromPath("C:\\Selenium\\SalesForceAutomationPOM\\Screenshots\\"+imagename);
    }
	public void initialTestSetup() {
		// TODO Auto-generated method stub
		
	}

}


