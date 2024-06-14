package Test_Cases;

import java.io.File;


import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Element_Utilites.ElementUtilities;
import PageObjects.HomePage;
import PageObjects.RegisterPage;

public class Register {
	
	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage;
	RegisterPage registerPage;
	ElementUtilities elementRef;
	

	@BeforeMethod
	public void setup() throws Exception
	{
		
		prop = new Properties();
		prop.load(new FileInputStream(new File("src\\test\\java\\Properties_File\\ProjectData.properties")));
		
		elementRef = new ElementUtilities();
		driver = elementRef.initdriver(prop.getProperty("browser"),prop.getProperty("url"));
		
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	@Test
	public void registerWithAllCredentials() throws Exception
	{
		registerPage = new RegisterPage(driver);
		
		registerPage.firstName(prop.getProperty("firstName"));
		registerPage.LastName(prop.getProperty("lastName"));
		registerPage.emailId((timeStamp()+prop.getProperty("emailId")));
		registerPage.telephone(prop.getProperty("telephone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.confirmPassword(prop.getProperty("confirmPassword"));
		registerPage.clickOnNoButton();
		registerPage.clickOnCheckbox();
		registerPage.clickOnContinueButton();
		
		Assert.assertEquals("Your Account Has Been Created!",driver.findElement(By.xpath("//div[@id='content']/h1")).getText());
	}
	
	@Test(priority = 2 )
	public void registerWithMandatoryCredentials() throws Exception
	{
		registerPage = new RegisterPage(driver);
		
		registerPage.firstName(prop.getProperty("firstName"));
		registerPage.LastName(prop.getProperty("lastName"));
		registerPage.emailId(timeStamp()+prop.getProperty("emailId"));
		registerPage.telephone(prop.getProperty("telephone"));
		registerPage.password(prop.getProperty("password"));
		registerPage.confirmPassword(prop.getProperty("confirmPassword"));
		registerPage.clickOnNoButton();
		registerPage.clickOnCheckbox();
		driver = registerPage.clickOnContinueButton();
			
		Assert.assertEquals("Your Account Has Been Created!",driver.findElement(By.xpath("//div[@id='content']/h1")).getText());
	}
	
	@Test(priority = 3)
	public void registerWithoutCredentials() throws Exception
	{
		registerPage = new RegisterPage(driver);
		driver = registerPage.clickOnContinueButton();	
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(),"First Name must be between 1 and 32 characters!");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),"Last Name must be between 1 and 32 characters!");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),"E-Mail Address does not appear to be valid!");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),"Telephone must be between 3 and 32 characters!");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),"Password must be between 4 and 20 characters!");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),"Warning: You must agree to the Privacy Policy!");
	}
	
	public static String timeStamp()
	{
		Date date = new Date();
		//String date1= date.toString();
		return (date.toString().replace(" ", "_").replace(":","_"));
		
	}
	
	
}
