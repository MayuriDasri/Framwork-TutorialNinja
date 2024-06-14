package Test_Cases;

import java.io.File;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Element_Utilites.ElementUtilities;
import PageObjects.HomePage;
import PageObjects.SearchPage;

public class Search {
	
	WebDriver driver;
	Properties prop;
	SearchPage searchPageRef;
	HomePage homePageRef; 
	ElementUtilities utilitesRef;
	
	@BeforeMethod
	public void setup() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream(new File("src\\test\\java\\Properties_File\\ProjectData.properties")));
		
		
		utilitesRef= new ElementUtilities();
		driver = utilitesRef.initdriver(prop.getProperty("browser"), prop.getProperty("url"));
		
	}
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	@Test(priority=1)
	public void serachForValidProduct() throws InterruptedException 
	{
		searchPageRef = new SearchPage(driver);
		String validProductName = prop.getProperty("validProduct");
		driver = searchPageRef.sendValidSearchProduct(validProductName);
		driver = searchPageRef.clickOnSearchButton();
	
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='MacBook']")).isDisplayed(),"Product Not Displayed On Page");
	}
	
	
		
		
	@Test(priority=2)
	public void serchForInvalidProduct()
	{
		searchPageRef = new SearchPage(driver);
		String inValidProductName = prop.getProperty("inValidProduct");
		driver = searchPageRef.sendValidSearchProduct(inValidProductName);
		driver = searchPageRef.clickOnSearchButton();
		
		String expectedMessage = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]")).getText();
		String actualMessage = "There is no product that matches the search criteria.";
	
		Assert.assertEquals(actualMessage,expectedMessage,"Product Not Displayed On Page");
		
	}
	
	@Test(priority=3)
	public void searchWithoutProduct()
	{
		searchPageRef = new SearchPage(driver);
		
		driver = searchPageRef.clickOnSearchButton();
		
		String expectedMessage = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]")).getText();
		String actualMessage = "There is no product that matches the search criteria.";
	
		Assert.assertEquals(actualMessage,expectedMessage);
		
		
	}

}
