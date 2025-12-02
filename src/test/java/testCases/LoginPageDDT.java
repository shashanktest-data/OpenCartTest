package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginPageDDT extends BaseClass {
	@Test(dataProvider="testdata",dataProviderClass=DataProviders.class,groups="datadriven")//getting data provider from different class
	public void verify_loginDDT(String email,String pass,String exp)
	{
		logger.info("verify login DDT test case started");
try {		//hOME PAGE
HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//lOGIN Page
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clickLoginButton();
		

		// after login, send ESC key to dismiss popup
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ESCAPE).perform();

		//My account page
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetpage=map.isMyAccountPageExist();
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				Assert.assertTrue(true);
				map.clickLogout();
			}else
			{
				Assert.assertTrue(false);
				
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==false)
			{
				
				boolean status=lp.getErrorMessage();
				Assert.assertTrue(status);
				
			}
		}
}catch(Exception e)
{
Assert.fail();	
}
		logger.info("********login page ddt finished*****");
	
	}

}
