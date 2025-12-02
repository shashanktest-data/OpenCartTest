package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	//public WebDriver driver;
	public WebDriverWait wait;
	
	public HomePage(WebDriver driver)
	{
		
		
		//this.driver=driver;
		//PageFactory.initElements(driver, this);
		super(driver);
		this.wait= new WebDriverWait(driver,Duration.ofSeconds(3));
	}
	@FindBy (xpath="//span[text()='My Account']")
	WebElement myAccountLink;
	
	
	
	@FindBy (xpath="//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy (xpath="//a[text()='Login']")
	WebElement loginLink;
	
	public void clickMyAccount()
	{
		
		
		wait.until(ExpectedConditions.elementToBeClickable(myAccountLink)).click();
		//myacc.click();
	}
	
	public void clickRegister()
	{
		wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
	}
	
	public void clickLogin()
	{
		
		wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
	}

}
