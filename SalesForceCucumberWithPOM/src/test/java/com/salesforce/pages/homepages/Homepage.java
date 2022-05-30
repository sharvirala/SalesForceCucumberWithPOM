package com.salesforce.pages.homepages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforcepom.base.BasePage;

public class Homepage extends BasePage
{
	@FindBy(xpath="//h2[contains(text(),'Getting Started')]")WebElement homepagetext;
	@FindBy(id="userNavLabel")WebElement usermenuEle;
	@FindBy(xpath="//a[contains(text(),'Logout')]")WebElement logoutEle;
	

	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	public String gethomepagetext()
	{
		waitUntilVisibilityOf(homepagetext,"homepagetext");
		String homepageText=getText(homepagetext,"homepagetext");
		
		return homepageText;
	}
	public void usermenu()
	{
		 waitUntilVisibilityOf(usermenuEle,"usemenu");
			mouseOverAndClick(usermenuEle,"usermenu");
	}
	public void sfcdLogOut()
	{
		 waitUntilVisibilityOf(logoutEle,"logout");
			mouseOverAndClick(logoutEle,"logout");
	}
}
