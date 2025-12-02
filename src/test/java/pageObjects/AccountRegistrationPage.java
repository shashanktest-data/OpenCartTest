package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="#input-firstname")
	WebElement firstName;
	
	@FindBy(css="#input-lastname")
	WebElement lastName;
	
	
	@FindBy(css="#input-email")
	WebElement email;
	
	@FindBy(css="#input-telephone")
	WebElement phone;
	
	
	
	
	
	@FindBy(css="#input-password")
	WebElement pwd;
	
	@FindBy(css="#input-confirm")
	WebElement pwdconfirm;
	
	
	@FindBy(css="[name='agree']")
	WebElement agree;
	
	@FindBy(css="[value='Continue']")
	WebElement continueButton; 
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement successMessage;
	
	public void accountRegistration(String fname,String lname,String emailid,String phonenum,String pwddata,String confirmpwddata)
	{
		
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		email.sendKeys(emailid);
		phone.sendKeys(phonenum);
		pwd.sendKeys(pwddata);
		pwdconfirm.sendKeys(confirmpwddata);
		agree.click();
		continueButton.click();
		
	}
	
	public String getConfirmationMessage()
	{
		try {
			return successMessage.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

}
