package com.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.homepages.Homepage;
import com.salesforce.pages.loginPages.Loginpage;
import com.salesforce.pages.homepages.Homepage;
import com.salesforce.pages.loginPages.Loginpage;
//import com.salesforce.base.BasePage;
//import com.salesforce.utility.CommonUtilities;
//import com.salesforce.utility.CommonUtilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitionTC1andTc2

{	
	static SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	Loginpage login;
	Homepage homepage;
	@Before(order=0)
	public void setUp1() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
	}
	@Before(order=1) // we can have multiple befores and afters using order parameter mention order sequence
	public void setUp2() {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	

	@Given("User open salesforce application")
	public void user_open_salesforce_application() {
	   driver.get("https://login.salesforce.com/");
	}

	@When("user on {string}")
	public void user_on(String page) {
		  if(page.equalsIgnoreCase("loginpage"))
		    	login=new Loginpage(driver);
		    else if(page.equalsIgnoreCase("homepage"))
		    	homepage=new Homepage(driver);
	}

	@When("user enter username {string}")
	public void user_enter_username(String username) {
	   login.enterUsername(username);
	}

	@When("user clear password field")
	public void user_clear_password_field() {
	    login.clearpasswordfield();
	}

	@When("user click on login button")
	public void user_click_on_login_button() throws InterruptedException {
	    login.clickLoginBtn();
	}

	@Then("error message should be {string}")
	public void error_message_should_be(String string) {
	    String expected="Please enter your password.";
	    String actual=login.getErrormsg();
	    softAssert.assertEquals(actual, expected);
	    softAssert.assertAll();
	}
	@When("user enter password {string}")
	public void user_enter_password(String password) {
	  login.enterPassword(password);
	}

	@Then("message should be {string}")
	public void message_should_be(String homepagemessage) 
	{
		String expectedtext=homepagemessage;
		//"Welcome to free trial"
		String actualtext=homepage.gethomepagetext();
		softAssert.assertEquals(actualtext, expectedtext);
		softAssert.assertAll();
	}
	@When("user select the remember me checkbox")
	public void user_select_the_remember_me_checkbox() 

	{
	    login.clickOnRememberMeCheckBox();
	}


	@When("user click on usermenu dropdown")
	public void user_click_on_usermenu_dropdown() 
	{
	   homepage.usermenu();
	}

	@When("user click on logout button")
	public void user_click_on_logout_button() 
	{
	   homepage.sfcdLogOut();
	}

	@Then("{string} should be displayed on username field")
	public void should_be_displayed_on_username_field(String username) 
	{
		String expected=username;
		String actual=login.getusername();
		softAssert.assertEquals(actual,expected);
		
		softAssert.assertAll();
	   
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	
}

