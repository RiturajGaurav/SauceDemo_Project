package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import library.BaseClass;
import library.Utilityclass;
import pages.LoginPage;

public class LoginPageTest extends BaseClass {

	int TCID;
	LoginPage login;
	
	@BeforeMethod
	public void setUp()
	{
		login = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Utilityclass.CaptureSS(TCID);
		}
		driver.quit();
	}
	
	@Test
	public void VerifyURL() throws IOException
	{
		String actURL = login.VerifyURL();
		String expURL = Utilityclass.readPFData("URL");
		Assert.assertEquals(actURL, expURL);
	}
	
	@Test
	public void VerifyLogo()
	{
		boolean actlogo = login.VerifyLogo();
		Assert.assertEquals(actlogo, true);
	}
	@Test
	public void VerifyTitle()
	{
		login.VerifyPageTitle();
	}
	
	@DataProvider(name="credential")
	public Object[][] getData()
	{
		return new Object[][] { 
			{ "Valid", "standard_user", "secret_sauce" }, 
			{ "invalid", "adal#er34", "5554dfaf" },
			{ "invalidUN", "adfare@128", "secret_sauce" },
			{ "invalidpwd", "standard_user", "854@3reg" },
			{ "blank", "", "" } 
			};
	}
	@Test(dataProvider="credential")
	public void VerifyLoginFunctionality(String scenario, String username, String password) throws IOException
	{
		login.EnterCredentials(username, password);
		if(scenario.equals("Valid"))
		{
			String actResult = login.VerifyURL();
			String expResult = Utilityclass.readPFData("ProductPageURL");
			Assert.assertEquals(actResult, expResult);
		}
		else if(scenario.equals("inValid"))
		{
			String actResult = login.getErrorMsg();
			String expResult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actResult, expResult);
		}
		else if(scenario.equals("inValidUN"))
		{
			String actResult = login.getErrorMsg();
			String expResult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actResult, expResult);
		}
		else if(scenario.equals("inValidPassword"))
		{
			String actResult = login.getErrorMsg();
			String expResult = "Epic sadface: Username and password do not match any user in this service";
			Assert.assertEquals(actResult, expResult);
		}
		else if(scenario.equals("blank"))
		{
			String actResult = login.getErrorMsg();
			String expResult = "Epic sadface: Username is required";
			Assert.assertEquals(actResult, expResult);
		}
	}
}