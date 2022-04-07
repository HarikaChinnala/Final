package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class contactPage {
	
	
	@FindBy(xpath="//*[text()='New']")
	public WebElement NewContact;
	
	@FindBy(xpath="//*[@name='firstName']")
	public WebElement firstname;
	
	@FindBy(xpath="//*[@name='lastName']")
	public WebElement lastname;
	
	@FindBy(xpath="//*[@name='Email']")
	public WebElement email;
	
	@FindBy(xpath="//input[@name='country']")
	public WebElement country;
	
	@FindBy(xpath="//*[@placeholder='Search Accounts...']")
	public WebElement Accountname;
	
	@FindBy(xpath="//button[@name='SaveEdit']")
	public WebElement savecontact;
	
	@FindBy(xpath="//*[@name='Edit']")
	public WebElement edit;
	
	
	public contactPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	

	public WebElement getNewContact(){
		return NewContact;
	}
	
	public WebElement getfirstname(){
		return firstname;
	}
	
	public WebElement getlastnamee(){
		return lastname;
	}
	
	public WebElement getemail(){
		return email;
	}
	
	public WebElement getcountry(){
		return country;
	}
	
	public WebElement getAccountname(){
		return Accountname;
	}
	
	public WebElement getsavecontact(){
		return savecontact;
	}
	


	public WebElement getedit(){
		return edit;
	}


}
