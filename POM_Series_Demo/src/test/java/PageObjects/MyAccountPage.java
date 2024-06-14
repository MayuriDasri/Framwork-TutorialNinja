package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {
	
	WebDriver driver;
	private WebElement editYourAccountInformation;
	public MyAccountPage(WebDriver driver )
	{
		this.driver = driver;
	}

	
			
	public boolean loggedInStatus()
	{
		editYourAccountInformation = driver.findElement(By.linkText("Edit your account information"));
		return editYourAccountInformation.isDisplayed();
	}
}