package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class legalDocumentPage {
	
	
	
	@FindBy(xpath="//*[@name='Service_Line__c']")
	public WebElement Serviceline;
	
	@FindBy(xpath="//*[@name='documentType']")
	public WebElement Doctype;
	
	@FindBy(xpath="//*[@name='NDA_Entity__c']")
	public WebElement legalentity;
	
	@FindBy(xpath="(//*[@for='custSignedBy']//following-sibling::lightning-input-field)//input")
	public WebElement countercontact;
	
	@FindBy(xpath="//*[@name='Comments_For_Legal__c']")
	public WebElement comments;
	
	@FindBy(xpath="//*[@title='Submit']")
	public WebElement submit;
	
	@FindBy(xpath="//*[@class='slds-file-selector__button slds-button slds-button_neutral']//*[@data-key='upload']")
	public WebElement upload;
	
	public legalDocumentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		super();
		//setTitle();
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getServiceline(){
		return Serviceline;
	}
	
	public WebElement getDoctype(){
		return Doctype;
	}
	
	public WebElement getcountercontact(){
		return countercontact;
	}
	
	public WebElement getlegalentity(){
		return legalentity;
	}
	
	public WebElement getcomments(){
		return comments;
	}
	
	public WebElement getsubmit(){
		return submit;
	}
	
	public WebElement getupload(){
		return upload;
	}


}
