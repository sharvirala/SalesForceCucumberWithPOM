package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.salesforce.base.BaseTest;
import com.salesforce.utility.CommonUtilities;
import com.salesforce.utility.Constants;
//import com.salesforce.utility.GenerateReports;
//import com.salesforce.utility.GenerateReports;
import com.salesforce.utility.GenerateReportsPOM;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestPOM
{

	protected  static WebDriver driver;
	 protected static  Logger log= Logger.getLogger(BaseTestPOM.class);
	
	 public 	static ExtentHtmlReporter htmlReporter;
		public static ExtentReports extent;
		public static ExtentTest logger;
		public static  GenerateReportsPOM report;

	//@BeforeMethod
	/*public static void setup() throws IOException
	{
		//getDriver();

		waitforPageLoadTimeOut(80);
		windowMaximize();
		String loginUrl=CommonUtilities.getPropertyValue("loginUrl");
		goToUrl(loginUrl);
		
		String username=CommonUtilities.getPropertyValue("username");
		String password=CommonUtilities.getPropertyValue("password");
		salesForcelogin( username, password);
	}*/
		@BeforeTest
		public  static void initialTestSetup()
		{
			report=GenerateReportsPOM.getInstance();
			report.startExtentReport();
			
		}

		@AfterTest
		public  void finalTestTearDown()
		{
			report.endReport();
		}
	
		@BeforeMethod
		@Parameters("browser")
		public  void getDriver(String  browser, Method method) throws IOException
		{
			GenerateReportsPOM.startSingleTestReport(method.getName());
			//waitforPageLoadTimeOut(50);
			if(browser.equalsIgnoreCase("Firefox"))
					{
						WebDriverManager.firefoxdriver().setup();
						driver=new FirefoxDriver();
						report.logTestInfo("Firefox Driver instance is created");
					}
					else if(browser.equalsIgnoreCase("chrome"))
					{
						WebDriverManager.chromedriver().setup();
						 driver=new ChromeDriver();
						 report.logTestInfo("chrome Driver instance is created");
					}
					else if(browser.equalsIgnoreCase("safari"))
					{
						//WebDriverManager.safaridriver().setup();
						driver=new SafariDriver();
						report.logTestInfo("safari Driver instance is created");
														
					}
					else if(browser.equalsIgnoreCase("edge"))
					{
						WebDriverManager.edgedriver().setup();
						driver=new EdgeDriver();
						report.logTestInfo("edge Driver instance is created");
						
					}
					else
					{
						report.logTestInfo("browser not found");
					}
			String loginUrl=CommonUtilities.getPropertyValue("loginUrl");
			goToUrl(loginUrl);
		
		}	
		@AfterMethod
		public void tearDown()
		{
			closeAllDriver();
			report.logTestInfo("driver closed");
		}
		
		public static  void takeScreenShot(String jpgfilename) throws IOException
		{
			 TakesScreenshot ScreenCapture=(TakesScreenshot)driver;
			File sourceFile= ScreenCapture.getScreenshotAs(OutputType.FILE);
			String path=Constants.SCREENSHOT_PATH+jpgfilename;
			 File destinationFile= new File(path);
			 FileUtils.copyFile(sourceFile, destinationFile);
		
		
		}
		public static String getTiltle()
		
		{	
			String title=driver.getTitle();
			return title;
		}
		
		public static void switchToWindow()
		{
		//	driver.switchTo().w
		}
		
		
		public  void goToUrl(String url)
		 {
			driver.get(url);
			report.logTestInfo("Url entered is"+url);
			
		 }
		
		
		protected static void waitForImplicitWait(int seconds)
		{
			driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS );
		}

		
		public static void windowMaximize()
		{
			driver.manage().window().maximize();
			
		}
		
		public static void enterText(WebElement element,String text,String objName)
		{
			if(element.isDisplayed())
			{
			clearElement(element,objName);
			element.sendKeys(text);
			report.logTestInfo("pass:"+objName+" text entered");
			}
			else
			{
				report.logTestInfo("fail:"+objName+"text not entered");
			}
		}
		public static void salesForcelogin(String username,String password)
		{
			WebElement emailEle=driver.findElement(By.id("username"));
			waitUntilVisibilityOf(emailEle,"user name");
			enterText(emailEle,username,"user name");
			WebElement passwordEle=driver.findElement(By.id("password"));
			enterText(passwordEle,password,"password");
			WebElement login=driver.findElement(By.id("Login"));
			clickElement(login,"login button");
		}
		public static void usermenudropdown()
		{

			WebElement usermenuEle=driver.findElement(By.id("userNavLabel"));
			 waitUntilVisibilityOf(usermenuEle,"usemenu");
			clickElement(usermenuEle,"usermenu");
		}
		public static void pageScrollUp()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,250)", "");
		}
		public static void SFDCLogout()
		{
			usermenudropdown();
			WebElement logoutEle=driver.findElement(By.xpath(" //a[contains(text(),'Logout')]"));
			 waitUntilVisibilityOf(logoutEle,"logout");
			clickElement(logoutEle,"logout");
			log.info("Logout successfully");
		}
		
		
		public static void selectByValue(WebElement element,String value,String objName)
		{
			Select s=new Select(element);
			s.selectByValue(value);
		}
		
		
		public static void selectByIndex(WebElement element,int index,String objName)
		{
			Select s=new Select(element);
			s.selectByIndex(index);
		}
		
		public static void selectByVisibleText(WebElement element,String text,String objName)
		{
			Select s=new Select(element);
			s.selectByValue(text);
		}
		
		
		public static void clickElement(WebElement element,String objName)
		{
			element.click();
			log.info("click operation done on "+objName);
			
		}
		
		
		public static void waitUntilVisibilityOfElementLocated(By locator,String objName)
		{
			 WebDriverWait wait=new WebDriverWait(driver,30);
			  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public static void waitUntilElementToBeClickable(By locator,String objName)
		{
			 WebDriverWait wait=new WebDriverWait(driver,30);
			  wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		
		public static void waitUntilAlertIsPresent()
		{
			 WebDriverWait wait=new WebDriverWait(driver,30);
			  wait.until(ExpectedConditions.alertIsPresent());
		}
		
		
		public static void clearElement(WebElement element,String objName)
		{
			if(element.isDisplayed())
			{
			element.clear();
			log.info("pass: "+objName+ " element cleared");
			}else
			{
			log.info("fail: "+objName+" element is not displayed");
			}
		}
		public  void findElement1(By locator,String value)
		{
			
		}
		
		//public static void login()
		public static void mouseOverAndClick(WebElement element,String objName)
		{
			waitUntilVisibilityOf( element, objName);
				Actions action=new Actions(driver);
			   action.moveToElement(element).build().perform();
			   action.click().build().perform();
			   log.info(" cursor moved to "+objName+" and click operation done");
		    
			}
		
		public static Alert switchToAlert()
		{
		return	driver.switchTo().alert();
		}
		
		
		public static void alertAccept(Alert alert,String objName)
		{
			alert.accept();
		}
		
		public static void alertDismiss(Alert alert,String objName)
		{
			alert.dismiss();
		}
		
		public static void waitUntilVisibilityOf(WebElement element,String objName)
		{
			 WebDriverWait wait=new WebDriverWait(driver,30);
			  wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		
		protected   void waitforPageLoadTimeOut(int seconds)
		{
			driver .manage().timeouts().pageLoadTimeout(seconds,TimeUnit.SECONDS);
		}
		
		
		public static void findElement(By locator,String text)
		{
			
		}
		protected static String getText(WebElement element ,String objName)
		{
			
			String text=element.getText();
			return text;
		}
		

		public static void closeDriver()
		{
			log.info("driver closed");
			driver.close();
			
			
		}
		public static void closeAllDriver()
		{
			driver.quit();
		}
	}

