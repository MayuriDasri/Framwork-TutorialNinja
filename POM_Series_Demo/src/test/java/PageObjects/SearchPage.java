package PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	
	WebDriver driver;
	Properties prop;
	private WebElement searchButton;
	private WebElement searchInputBox;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public WebDriver clickOnSearchButton()
	{
		searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		searchButton.click();
		return driver;
	}
	
	public WebDriver sendValidSearchProduct(String validProductName)
	{
		searchInputBox = driver.findElement(By.xpath("//input[@name='search']"));
		searchInputBox.sendKeys(validProductName);
		
		return driver;
	}

}
