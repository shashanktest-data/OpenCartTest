package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class LoginPageTest extends BaseClass {
	
	@Test(groups={"sanity","master"})
	public void verify_login()
	{
		logger.info("verify login test case started");
		//hOME PAGE
	try {	HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//lOGIN Page
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(prop.getProperty("EMAIL"));
		lp.setPassword(prop.getProperty("PASSWORD"));
		lp.clickLoginButton();
		//My account page
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetpage=map.isMyAccountPageExist();
		Assert.assertTrue(targetpage, "Login failed - MyAccount page not displayed");
		logger.info("===== Login Test Passed =====");

	}
	catch(Exception e){
		logger.error("Exception occurred: " + e.getMessage());
		  Assert.fail("Test failed due to exception: " + e.getMessage());
		
	}
	logger.info("******************finished verify login test**********");
	}

}
