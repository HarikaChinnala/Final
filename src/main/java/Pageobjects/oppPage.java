package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class oppPage {
	@FindBy(xpath="//button[contains(text(),'Next')]")
	public WebElement Next;	
	
@FindBy(xpath="//*[@class='slds-button__icon'][@viewBox='0 0 52 52'][@data-key='down']")	
public WebElement dropdown;

@FindBy(xpath="//*[contains(@name,'Opportunity.Send_For_Signature')]")	
public WebElement esign;

	
@FindBy(xpath="//*[@id='relatedListsTab__item']")
public WebElement Related;

@FindBy(xpath="//*[@id='detailTab__item']")
public WebElement Details;

@FindBy(xpath="((//*[contains(text(),'Amendment Contract Status')]//parent::div)/following-sibling::div)/span/slot/slot/lightning-formatted-text")
public WebElement Amendcontractstatus;

@FindBy(xpath="//*[@id='window'][contains(text(),'Quotes')]")
public WebElement quotequicklinks;


@FindBy(xpath="(//*[starts-with(text(),'Q-')])[1]")
public WebElement quotelink;

@FindBy(xpath="(//*[contains(@id,'dropdown-element')]//parent::div)/div[2]/lightning-base-combobox-item/span[@title='Activated']")
public WebElement Activated;

@FindBy(xpath="//*[@title='7 - Won']")
public WebElement  wonchev;

@FindBy(xpath="//*[@title='Closed']")
public WebElement  Closedchev;

@FindBy(xpath="//*[contains(text(),'Select Closed Stage')]")
public WebElement  selectstage;

@FindBy(xpath="//*[@title='3 - Selection']")
public WebElement selection;

@FindBy(xpath="//*[contains(text(),'Mark as Current Stage')]")
public WebElement mark;

@FindBy(xpath="//*[@name='Opportunity.Contract']")
public WebElement contract;

@FindBy(xpath="//*[contains(@class,'slds-modal__footer')]/button[2]")
public WebElement contractsave;

@FindBy(xpath="//*[@title='4 - Contracting']")
public WebElement contracting;

@FindBy(xpath="//*[@class='container forceRelatedListSingleContainer']/article/div[2]/div/div/div/div/ul/li/article/div/div[1]/h3/div/a")
public WebElement contractlinkuat;

@FindBy(xpath="//*[@class='container forceRelatedListSingleContainer']/article/div[2]/div/div/div/div/ul/li/div[2]/h3/div/a")
public WebElement contractlink;

@FindBy(xpath="(//*[@class='slds-media__body']/*[@class='slds-tile__detail slds-truncate esign-title-width'])/li/span[2]")
public WebElement agreementstat;

@FindBy(xpath="(//*[@class='slds-media__body']/*[@class='slds-tile__detail slds-truncate esign-title-width'])/li/span[1]")
public WebElement agreement;

@FindBy(xpath="//button[contains(text(),'Edit Lines')]")
public WebElement editlines;

@FindBy(xpath="//*[contains(@name,'Opportunity.Generate_Amendment')]")
public WebElement Genamend;

@FindBy(xpath="((((((//*[@title='Files'])/parent::a)/parent::h2)/parent::div)/parent::header)/parent::div)/following-sibling::div/div/div/div/div/div/div[2]/ul/li/a")
public WebElement Files;

@FindBy(xpath="//*[@name='SaveEdit']")
public WebElement Saveedit;

@FindBy(xpath="//*[@name='Opportunity.Contract_Sync']")
public WebElement contractSync;

@FindBy(xpath="(//*[starts-with(text(),'Incremental ACV Committed')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement acvcommitted;	

@FindBy(xpath="(//*[starts-with(text(),'Incremental ACV Total')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement acvtotal;

@FindBy(xpath="(//*[contains(text(),'Opportunity Name')]//parent::div)/following-sibling::div/span/slot/slot")
public WebElement opportunityname;

@FindBy(xpath="(//*[contains(text(),'Account Name')]//parent::div)/following-sibling::div/span/slot/slot//a")
public WebElement accountname;

@FindBy(xpath="(//*[contains(text(),'Selling Lane')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")
public WebElement sellinglane;

@FindBy(xpath="(//*[contains(text(),'Entity')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")
public WebElement entity;

@FindBy(xpath="(//*[contains(text(),'MSA Date')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")
public WebElement msadate;

@FindBy(xpath="(//*[contains(text(),'Service Order Date')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")
public WebElement serviceorderdate;

@FindBy(xpath="(//*[contains(text(),'Primary Quote')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup/div//a")
public WebElement quote;

@FindBy(xpath="(//*[contains(text(),'Primary Quote Term')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-number")
public WebElement term;

@FindBy(xpath="(//*[contains(text(),'Primary Contact')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup//a")
public WebElement primecontact;

@FindBy(xpath="(//*[contains(text(),'Billing Contact')]//parent::div)/following-sibling::div/span")
///slot/slot/force-lookup//a
public WebElement billcontact;

@FindBy(xpath="(//*[contains(text(),'Currency')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup//a")
public WebElement currency;

@FindBy(xpath="(//*[starts-with(text(),'ACV Committed')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement ACVcommitted;	

@FindBy(xpath="(//*[starts-with(text(),'ACV Total')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement ACVtotal;

@FindBy(xpath="//*[@class='test-id__field-label'][contains(text(),'Monthly Recurring Revenue (MRR)')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement MRR;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Non Recurring Revenue (NRR)')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement NRR;
	
@FindBy(xpath="(//*[starts-with(text(),'TCV Committed')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement tcvcommitted;	

@FindBy(xpath="(//*[starts-with(text(),'TCV Total')]//parent::div)/following-sibling::div//lightning-formatted-text")
public WebElement tcvtotal;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'ACV Usage')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement acvusage;
	
@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'TCV Usage')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement tcvusage;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental Monthly Recurring Revenue')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement IncMRR;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental Non Recurring Revenue (NRR)')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement IncNRR;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental ACV Committed')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Incacvcommitted;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental ACV Usage')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Incacvusage;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental ACV Total')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Incacvtotal;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental TCV Committed')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Inctcvcommitted;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental TCV Usage')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Inctcvusage;

@FindBy(xpath="//*[@class='test-id__field-label'][starts-with(text(),'Incremental TCV Total')]//parent::div/following-sibling::div//lightning-formatted-text")
public WebElement Inctcvtotal;


public oppPage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//this.driver=driver;
	super();
	//setTitle();
	PageFactory.initElements(driver, this);
}

public WebElement getRelated(){
	return Related;
}

public WebElement getDetail(){
	return Details;
}

public WebElement getquotequicklinks(){
	return quotequicklinks;
}

public WebElement getquotelink(){
	return quotelink;
}


public WebElement geteditlines(){
	return editlines;
}


public WebElement getselection(){
	return selection;
}
public WebElement getmark(){
	return mark;
}

public WebElement getcontract(){
	return contract;
}

public WebElement getcontractsave(){
	return contractsave;
}

public WebElement getcontracting(){
	return contracting;
}

public WebElement getcontractlink(){
	return contractlink;
}

public WebElement getcontractlinkuat(){
	return contractlinkuat;
}

public WebElement getGenamend(){
	return Genamend;
}


public WebElement getFiles(){
	return Files;
}

public WebElement getdropdown(){
	return dropdown;
}

public WebElement getesign(){
	return esign;
}

public WebElement getagreementstat(){
	return agreementstat;
}

	public WebElement getagreement(){
		return agreement;
	}

	public WebElement getNext(){
		return Next;
	}
	
	public WebElement getAmendcontractstatus(){
		return Amendcontractstatus;
}
	
	public WebElement getActivated(){
		return Activated;
}
	
	public WebElement getSaveedit(){
		return Saveedit;
}
	
	public WebElement getClosedchev(){
		return Closedchev;
}
	
	public WebElement getselectstage(){
		return selectstage;
}

	
	public WebElement get7won(){
		return wonchev;
}
	
	public WebElement getacvcommitted(){
		return acvcommitted;
		
}	
	public WebElement getacvtotal(){
		return acvtotal;
}
	public WebElement getcontractSync(){
		return contractSync;
}
	public WebElement getopportunityname(){
		return opportunityname;
}
	
	public WebElement getaccountname(){
		return accountname;
}

	public WebElement getsellinglane(){
		return sellinglane;
}
	public WebElement getentity(){
		return entity;
}
	public WebElement getmsadate(){
		return msadate;
}
	public WebElement getservicedate(){
		return serviceorderdate;
}
	public WebElement getquote(){
		return quote;
}
	public WebElement getterm(){
		return term;
}
	public WebElement getprimecontact(){
		return primecontact;
}
	public WebElement getbillcontact(){
		return billcontact;
}
	public WebElement getcurrency(){
		return currency;
}
	public WebElement getMRR(){
		return MRR;
}
	public WebElement getNRR(){
		return NRR;
}
	public WebElement getIncMRR(){
		return IncMRR;
}
	public WebElement getIncNRR(){
		return IncNRR;
}
	public WebElement gettcvcommitted(){
		return tcvcommitted;
}
	public WebElement gettcvusage(){
		return tcvusage;
}
	public WebElement gettcvtotal(){
		return tcvtotal;
}
	public WebElement getacvusage(){
		return acvusage;
}
	public WebElement getInctcvcommitted(){
		return Inctcvcommitted;
}
	public WebElement getInctcvusage(){
		return Inctcvusage;
}
	public WebElement getInctcvtotal(){
		return Inctcvtotal;
}
	public WebElement getIncacvusage(){
		return Incacvusage;
}
	
	public WebElement getIncacvcommitted(){
		return Incacvcommitted;
}

	public WebElement getIncacvtotal(){
		return Incacvtotal;
}
	public WebElement getACVtotal(){
		return ACVtotal;
}
	public WebElement getACVcommitted(){
		return ACVcommitted;
}
}
