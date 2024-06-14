package PageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	WebDriver driver = null;
//	private WebElement firstNameInput;
//	private WebElement lastNameInput;
//	private WebElement emailIdInput;
//	private WebElement telephoneInput;
//	private WebElement passwordInput;
//	private WebElement confirmPasswordInput;
//	private WebElement noButtonOption;
//	private WebElement checkBoxClick;
//	private WebElement continueButton;
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void firstName(String firstNameText)
	{
		driver.findElement(By.id("input-firstname")).sendKeys(firstNameText);
	}
	public void LastName(String lastNameText)
	{
		driver.findElement(By.id("input-lastname")).sendKeys(lastNameText);
	}
	public void emailId(String newEmailIdText)
	{
		driver.findElement(By.id("input-email")).sendKeys(newEmailIdText);
	}
	public void telephone(String telephoneText)
	{
		driver.findElement(By.id("input-telephone")).sendKeys(telephoneText);
	}
	public void password(String passwordText)
	{
		driver.findElement(By.id("input-password")).sendKeys(passwordText);
	}
	public void confirmPassword(String confirmPasswordText)
	{
		driver.findElement(By.id("input-confirm")).sendKeys(confirmPasswordText);
	}
	public void clickOnNoButton()
	{
		driver.findElement(By.xpath("//input[@value='0']")).click();
	}
	public void clickOnCheckbox()
	{
		driver.findElement(By.xpath("//input[@name='agree']")).click();
	}
	public WebDriver clickOnContinueButton()
	{
		 driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		 return driver;
	}
}
