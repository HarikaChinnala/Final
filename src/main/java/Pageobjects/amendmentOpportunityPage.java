package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class amendmentOpportunityPage {
	
	@FindBy(xpath="//div[@title='Amend']")
	public WebElement AmendButton;
	
	@FindBy(xpath="//input[@type='submit'][@value='Amend']")
	public WebElement Amend;
	
	@FindBy(xpath="//div[@id='fullScreenMode']")
	public WebElement Toggle;
	
	@FindBy(xpath="//button[@id='actionButton1']")
	public WebElement Clone;

	
	public amendmentOpportunityPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	
	public WebElement AmendButton(){
		return AmendButton;
	}

	public WebElement Amend() {
		return Amend;
	}
	
	public WebElement Toggle() {
		return Toggle;
	}
	
	public WebElement Clone() {
		return Clone;
	}

}
