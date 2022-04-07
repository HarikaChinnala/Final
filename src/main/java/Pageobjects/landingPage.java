package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage {
public WebDriver driver;
	
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Accounts')]")
	//@FindBy(xpath="/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[3]/a/span")
	public WebElement Accounts;
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Leads')]")
	public WebElement Leads;
	@FindBy(xpath="//*[contains(text(),'Contacts')]")
	public WebElement Contacts;
	@FindBy(xpath="//*[@name='Account-search-input']")
	public WebElement accttxtbox;
	@FindBy(xpath="//*[@id='brandBand_1']/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr/th/span/a")
	public WebElement srchresult;
	
	@FindBy(xpath="//*[@title='New']")
	public WebElement leadnew;
	
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Contacts')]")
	public WebElement contacts;
	
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Quotes')]")
	public WebElement quote;
	
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Opportunities')]")
	public WebElement Opportunities;
	
	@FindBy(xpath="//*[@name='Opportunity-search-input']")
	public WebElement opptxtbox;
	
	@FindBy(xpath="//*[@name='SBQQ__Quote__c-search-input']")
	public WebElement quotetxtbox;
	
	@FindBy(xpath="//*[@name='Opportunity.New_Quote']")
	public WebElement newQuote;
	
	@FindBy(xpath="//*[@class='slds-truncate'][contains(text(),'Contracts')]")
	public WebElement contracts;
	
	@FindBy(xpath="//*[@name='Contract-search-input']")
	public WebElement contracttxtbox;
	
	public landingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getAccount(){
		return Accounts;
	}
	public WebElement getLeads(){
		return Leads;
	}
	public WebElement getContacts(){
		return Contacts;
	}
	
	public WebElement getcontacts(){
		return contacts;
	}
	public WebElement getAcctbox(){
		return accttxtbox;
	}
	public WebElement getsrchresult(){
		return srchresult;
	}
	
	public WebElement getleadnew(){
		return leadnew;
}
	
	public WebElement getOpportunities(){
		return Opportunities;
	}
	
	public WebElement getopptxtbox(){
		return opptxtbox;
	}
	
	public WebElement getnewQuote(){
		return newQuote;
	}
	
	public WebElement getQuote(){
		return quote;
	}
	
	public WebElement getquotetxtbox(){
		return quotetxtbox;
	}
	
	public WebElement getcontracts(){
		return contracts;
	}
	
	public WebElement getcontracttxtbox(){
		return contracttxtbox;
	}
	
}
