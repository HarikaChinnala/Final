package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPageObject {
	

	public WebDriver driver;
	
	
	@FindBy(xpath="//*[@id='username']")
	public WebElement Username;
	
	@FindBy(xpath="//*[@id='password']")
	public WebElement Password;
	
	@FindBy(xpath="//*[@id='Login']")
	public WebElement login;
	
	@FindBy(xpath="//*[contains(text(),'Remind Me Later')]")
	public WebElement skipR;
	
	
	
	public loginPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getusername(){
		return Username;
	}
	public WebElement getpassword(){
		return Password;
	}
	public WebElement getLogin(){
		return login;
	}
	public WebElement clickskip(){
		return skipR;
	}
	
	
	
	
}
