package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver driver;
	private WebElement myAccountMenu;
	private WebElement loginOption;
	private WebElement registerOption;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public WebDriver clickOnMyAccountDropMenu()
	{
		myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccountMenu.click();
		return driver;
	}
	
	public WebDriver selectLoginOption()
	{	
		loginOption = driver.findElement(By.xpath("//a[text()='Login']"));
		loginOption.click();	
		return driver;
	}
	
	public WebDriver selectRegisterOption()
	{
		registerOption  = driver.findElement(By.linkText("Register"));
		registerOption.click();
		return driver;
	}
}
