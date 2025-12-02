package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (css="#input-email")
	WebElement emailAddress;
	
	@FindBy (css="#input-password")
	WebElement password;
	
	@FindBy (css="[value=Login]")
	WebElement loginButton;
	
	@FindBy (xpath="//div[@class='row']/ancestor::div[@class='container']/child::div[@class='alert alert-danger alert-dismissible' and text()='Warning: No match for E-Mail Address and/or Password.']")
	WebElement errorMsg;
	
	public void setEmail(String email)
	{
		emailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickLoginButton()
	{
		loginButton.click();
	}
	
	public boolean getErrorMessage()
	{
		return errorMsg.isDisplayed();
	}
	

}
