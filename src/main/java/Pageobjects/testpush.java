package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class testpush {
	
	@FindBy(xpath="//*[@id='customTab2__item']")
	public WebElement OppsCont;
	
	//@FindBy(xpath="(//*[@class='actionsContainer'])[1]")
	@FindBy(xpath="(//*[@title='New'])[3]")
	public WebElement OppsNew;
	
	@FindBy(xpath="//*[@title='New']/div")
	public WebElement NewAccount;
	
	@FindBy(xpath="//*[@class='uiInput forceSearchInputLookupDesktop uiInput--default']/div/div/div/input")
	public WebElement AccountNamefield;
	
	@FindBy(xpath="(//*[@class=' input'][@type='url'])[1]")
	public WebElement website;
	
	@FindBy(xpath=("//*[@title='Save']"))
	public WebElement saveAccount;
	
	@FindBy(xpath="//*[@name='Edit']")
	public WebElement edit;
	
	
	public testpush(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOppsCont(){
		return OppsCont;
	}
	
	public WebElement getOppsNew(){
		return OppsNew;
	}
	
	public WebElement getNewAccount(){
		return NewAccount;
	}
	
	public WebElement getAccountNamefield(){
		return AccountNamefield;
	}
	
	public WebElement getwebsite(){
		return website;
	}
	
	public WebElement getsaveAccount(){
		return saveAccount;
	}


	public WebElement getedit(){
		return edit;
	}


}
