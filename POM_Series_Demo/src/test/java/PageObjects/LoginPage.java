package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;
	
	public void enterLoginEmailAddress(String emailText)
	{
		emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(emailText);
	}
	public void enterLoginPassword(String passwordText)
	{
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(passwordText);
	}
	public WebDriver clickOnLoginButton()
	{
		loginButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		loginButton.click();
		return driver;
	}
	

}
