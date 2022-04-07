package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createQuotePage {
	
	

@FindBy(xpath="(//*[@class='select'])[2]")
public WebElement RNperiod;

@FindBy(xpath="//*[contains(@class,'slds-modal__footer')]/button[2]")
public WebElement save;

@FindBy(xpath="//button[contains(text(),'Generate Quote Proposal')]")
public WebElement Genquoteprop;

@FindBy(xpath="//*[@class='slds-tabs_default__nav']/li[@title='Related']")
public WebElement Related;

@FindBy(xpath="(((((//*[contains(text(),'Files')]/parent::a)/parent::h2)/parent::div)/parent::header)/parent::div)/following-sibling::div/div/div/div/div/div/div[2]/ul/li/a[starts-with(@title,'Quote Proposal -')]")
public WebElement Files;

@FindBy(xpath="//*[@data-key='down'][@class='slds-button__icon'][@viewBox='0 0 52 52']")
public WebElement dropdown;

@FindBy(xpath="(//*[@data-key='down'][@class='slds-button__icon'][@viewBox='0 0 52 52'])[2]")
public WebElement dropdown2;

@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-executor-page-reference/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='Submit_for_Approval']")
public WebElement submitapprovaluat;

@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-page-reference-action/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='Submit_for_Approval']")
public WebElement submitapproval;


@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-executor-page-reference/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='SBQQ__CloneWithRelated']")
public WebElement clonequoteuat;

@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-page-reference-action/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='SBQQ__CloneWithRelated']")
public WebElement clonequote;

@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-page-reference-action/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='Edit']")
public WebElement Edit;

@FindBy(xpath="//*[@class='slds-dropdown slds-dropdown_right']/div/slot/runtime_platform_actions-action-renderer/runtime_platform_actions-executor-page-reference/slot/slot/runtime_platform_actions-ribbon-menu-item/a[@name='Edit']")
public WebElement Edituat;

@FindBy(xpath="(//*[contains(text(),'Primary')]/parent::label)/following-sibling::div/span/*[@type='checkbox']")
public WebElement primcheck;

@FindBy(xpath="//*[@id='window']")
public WebElement oppname;


@FindBy(xpath="//*[@value='Clone']")
public WebElement clone;

@FindBy(xpath="//*[@name='SaveEdit']")
public WebElement SaveEdit;

@FindBy(xpath="//*[@title='Approved']")
public WebElement approved;

@FindBy(xpath="//*[@name='Requires_Approval__c']/following-sibling::label/span[@class='slds-checkbox_faux']")
public WebElement reqapp;

public createQuotePage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//this.driver=driver;
	super();
	//setTitle();
	PageFactory.initElements(driver, this);
}

public WebElement getRNperiod(){
	return RNperiod;
}


public WebElement getsave(){
	return save;
}

public WebElement getGenquoteprop(){
	return Genquoteprop;
}

public WebElement getdropdown(){
	return dropdown;
}

public WebElement getdropdown2(){
	return dropdown2;
}
public WebElement getsubmitapproval(){
	return submitapproval;
}

public WebElement getclonequote(){
	return clonequote;
}

public WebElement getsubmitapprovaluat(){
	return submitapprovaluat;
}

public WebElement getclonequoteuat(){
	return clonequoteuat;
}
public WebElement getclone(){
	return clone;
}

public WebElement getEdit(){
	return Edit;
}

public WebElement getEdituat(){
	return Edit;
}

public WebElement getapproved(){
	return approved;
}
	

public WebElement getRelated(){
	return Related;
}

public WebElement getFiles(){
	return Files;
}

public WebElement getprimcheck(){
	return primcheck;
}

public WebElement getSaveEdit(){
	return SaveEdit;
}


public WebElement getoppname(){
	return oppname;
}

public WebElement getrequireapproval(){
	return reqapp;
}
}
