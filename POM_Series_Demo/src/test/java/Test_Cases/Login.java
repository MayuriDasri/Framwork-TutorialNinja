package Test_Cases;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;

public class Login {
		
	WebDriver driver = null;
	Properties prop;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		prop = new Properties();
		prop.load(new FileInputStream(new File("src\\test\\java\\Properties_File\\ProjectData.properties")));
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if(browser.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		
		HomePage home = new HomePage(driver);
		driver = home.clickOnMyAccountDropMenu();
		driver = home.selectLoginOption();
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	@Test(priority = 1)
	public void login()
	{
		LoginPage loginPageRef = new LoginPage(driver);
		
		String emailId = prop.getProperty("validEmailId");
		loginPageRef.enterLoginEmailAddress(emailId);
		
		String password = prop.getProperty("validPassword");
		loginPageRef.enterLoginPassword(password);
		
		driver = loginPageRef.clickOnLoginButton();
		
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		
		Assert.assertTrue(myAccountPage.loggedInStatus());
	}
	
	@Test(priority = 2)
	public void loginWithInvalidCredentials()
	{
		
		LoginPage loginPageRef = new LoginPage(driver);
		
		String emailId = prop.getProperty("inValidEmailId");
		loginPageRef.enterLoginEmailAddress(emailId);
		
		String password = prop.getProperty("inValidPassword");
		loginPageRef.enterLoginPassword(password);
		
		driver = loginPageRef.clickOnLoginButton();
		
		String actual = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expected = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertEquals(actual ,expected," Invalid Email ID  or Password");
		
	}
	
	@Test(priority = 3)
	public void loginWithValidEmailAndInvalidPasswordCredentials()
	{
		LoginPage loginPageRef = new LoginPage(driver);
		
		String emailId = prop.getProperty("validEmailId");
		loginPageRef.enterLoginEmailAddress(emailId);
		
		String password = prop.getProperty("inValidPassword");
		loginPageRef.enterLoginPassword(password);
		
		driver = loginPageRef.clickOnLoginButton();
		
		String actual = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expected = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertEquals(actual ,expected," Invalid Email ID  or Password");
		
	}
	
	@Test(priority = 4)
	public void loginWithInvalidEmailAndValidPasswordCredentials()
	{
		LoginPage loginPageRef = new LoginPage(driver);
		
		String emailId = prop.getProperty("inValidEmailId");
		loginPageRef.enterLoginEmailAddress(emailId);
		
		String password = prop.getProperty("validPassword");
		loginPageRef.enterLoginPassword(password);
		
		driver = loginPageRef.clickOnLoginButton();
		
		String actual = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expected = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertEquals(actual ,expected," Invalid Email ID  or Password");
	}
	
	
	@Test(priority = 5)
	public void loginWithoutAnyCredentials()
	{
		LoginPage loginPageRef = new LoginPage(driver);
		
		loginPageRef.enterLoginEmailAddress(" ");
		loginPageRef.enterLoginPassword(" ");
		
		driver = loginPageRef.clickOnLoginButton();
		
		String actual = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expected = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertEquals(actual ,expected," Invalid Email ID  or Password");
		
	}
	
}
