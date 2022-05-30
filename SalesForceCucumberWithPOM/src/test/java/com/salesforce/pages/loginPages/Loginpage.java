package com.salesforce.pages.loginPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcepom.base.BasePage;

public class Loginpage extends BasePage
{
	WebDriver driver;
	@FindBy(id="username")WebElement userName;
	@FindBy(id="password")WebElement passWord;
	@FindBy(id="Login")WebElement logInbutton;
	@FindBy(id="error")WebElement errorMsg;
	@FindBy(name="rememberUn")WebElement checboxEle;
	@FindBy(xpath="//span[@id='idcard-identity']")WebElement unameele;
	//WebElement unameele=driver.findElement(By.cssSelector("#idcard-identity"));

		
	public Loginpage(WebDriver driver)
	{
		super(driver);
	}
	public void enterUsername(String username)
	{
		waitUntilVisibilityOf(userName, "username field");
		
		
		enterText(userName, username,"username field");
	}
	public void clearpasswordfield()
	{
		clearElement(passWord,"passwordfield");
	}
	public void enterPassword(String Password)
	
	{
		waitUntilVisibilityOf(passWord," Password field");
		
		enterText(passWord, Password, "passwordfield");
	}
	
	
	public void clickLoginBtn() throws InterruptedException
	{
		//waitUntilVisibilityOf(logInbutton,"login btn");
		Thread.sleep(5000);
		mouseOverAndClick(logInbutton,"loginbtn");
	}
	public void salesforceLogin(String username,String password) throws InterruptedException
	{
		enterUsername(username);
		enterPassword(password);
		clickLoginBtn();
	}
	
	public String getErrormsg()
	{
		String errorText=getText(errorMsg,"errormsgtext");
		return errorText;
	}
	public void clickOnRememberMeCheckBox()
	{
		waitUntilVisibilityOf(checboxEle,"rememberme");
		mouseOverAndClick(checboxEle,"checkboxele");
	}
	
	public String getusername()
	{
		waitUntilVisibilityOf(unameele,"username");
	String text=getText(unameele,"username");
	return text;
	}
	
	
}
