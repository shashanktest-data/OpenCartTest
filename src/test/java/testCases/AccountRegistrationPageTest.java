package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class AccountRegistrationPageTest extends BaseClass
 {
	//public WebDriver driver;
	
@Test(groups={"regression"})
	public void verifyUserRegistration()
	
	{
	logger.info("*******verify user registration test cases started");
	
	try {
	
	String password=randomlphanumeric();
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	logger.info("*******CLICKED ON MYACCOUNT LINK");
	hp.clickRegister();
	logger.info("*******CLICKED ON Register LINK");
	AccountRegistrationPage arp=new AccountRegistrationPage(driver);
	logger.info("******providing customer details");
	arp.accountRegistration(randomeString().toUpperCase(), randomeString(), randomeString()+"@gmail.com", randomeNumber(), password, password);
	logger.info("*******Validating expected message");
	String actualText=arp.getConfirmationMessage();
	if(actualText.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}else
	{
		logger.error("Test Failed");
		logger.debug("test debug");
		Assert.assertTrue(false);
	}
	//Assert.assertEquals(actualText, "Your Account Has Been Created!");
	
	
	} catch (AssertionError e) {
        logger.error("User registration test failed!", e);  // logs error + stacktrace
        throw e; // re-throw to mark test as failed
    } catch (Exception e) {
        logger.error("Unexpected error during registration test!", e);
        throw e; // re-throw to fail test
    }
		
	
	logger.info("************verify account registration test finished********");
		
		
	}
	
		
	}




