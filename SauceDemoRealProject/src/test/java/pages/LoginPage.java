package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BaseClass;

public class LoginPage extends BaseClass {

	@FindBy(xpath="//div[@class='login_logo']") private WebElement logo;
	@FindBy(id="user-name")private WebElement UN;
	@FindBy(id="password")private WebElement pwd;
	@FindBy(id="login-button")private WebElement loginbtn;
	@FindBy(xpath="	//div[@class='error-message-container error']") private WebElement Errormsg;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//To Verify the URL
	public String VerifyURL()
	{
		String url = driver.getCurrentUrl();
		return url;
	}
	//To Verify the Logo
	public boolean VerifyLogo()
	{
		boolean lg =logo.isDisplayed();
		return lg;
	}
	//To Verify the Title
	public String VerifyPageTitle()
	{
		return driver.getTitle();	
	}
	
	public void EnterCredentials(String username, String password)
	{
		UN.sendKeys(username);
		pwd.sendKeys(password);
		loginbtn.click();
	}
	public String getErrorMsg()
	{
		return Errormsg.getText();
	}
}
