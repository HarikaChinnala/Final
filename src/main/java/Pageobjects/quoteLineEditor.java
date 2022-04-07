package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class quoteLineEditor {
	
	

@FindBy(xpath="//*[@tooltip='Free Period']//following-sibling::div//select")
public WebElement Freeperiod;

@FindBy(xpath="//*[@tooltip='Start Date']//following-sibling::div//*[@id='selectedDate']")
public WebElement selectedstartdate;

@FindBy(xpath="//*[@tooltip='Term Start Date']//following-sibling::div//*[@id='selectedDate']")
public WebElement selectedtermdate;


@FindBy(xpath="//*[@tooltip='Start Date']//following-sibling::div//*[@id='open']")
public WebElement StartDate;

@FindBy(xpath="//*[@tooltip='Term Start Date']//following-sibling::div//*[@id='open']")
public WebElement Termstartdate;

@FindBy(xpath="//*[@tooltip='Subscription Term']//following-sibling::div//input")
public WebElement Subscriptionterm;

@FindBy(xpath="//*[@tooltip='Auto Renew Term']//following-sibling::div//select")
public WebElement Autorenew;

@FindBy(xpath="//*[@tooltip='Renewal Notice Period']//following-sibling::div//select")
public WebElement Renewnoticeperiod;

@FindBy(xpath="//*[@tooltip='Retro Billing']//following-sibling::div//div[@id='checkbox']")
public WebElement Retrobilling;

@FindBy(xpath="//*[@tooltip='Payment Terms']//following-sibling::div//select")
public WebElement paymentterms;

@FindBy(xpath="//*[@tooltip='Termination for Convenience']//following-sibling::div//select")
public WebElement terminationforconvenience;


@FindBy(xpath="//*[@tooltip='Case Study']//following-sibling::div//select")
public WebElement casestudy;

@FindBy(xpath="//button[@name='Reconfigure Line']")
public WebElement reconfig;

@FindBy(xpath="//*[@tooltip='Renewal Uplift (%)']//following-sibling::div//select")
public WebElement renwaluplift;

@FindBy(xpath="//*[@tooltip='Marketing Language']//following-sibling::div//select")
public WebElement Marketinglanguage;

@FindBy(xpath="//*[@tooltip='Deal Parameters Valid Through']//following-sibling::div//div[@id='datePicker']")
public WebElement Dealparameters;

@FindBy(xpath="//div[@id='fullScreenMode']")
public WebElement toggle;

@FindBy(xpath="//div[@field='SBQQ__AdditionalDiscount__c']//*[@class='pencil style-scope sf-le-table-row']")
public WebElement discount;


@FindBy(xpath="//*[@class='myselect discount style-scope sb-select --desktop style-scope sf-le-table-row']")
public WebElement discountoptions;

@FindBy(xpath="//*[@id='discount'] //*[@is='iron-input']")
public WebElement discounttext;

@FindBy(xpath="//*[@name='Add Products']/paper-button")
public WebElement addproducts;

@FindBy(xpath="//*[@id='mainButton'][contains(text(),'Add Group')]")
public WebElement addgroup;

@FindBy(xpath="//*[@id='mainButton'][contains(text(),'Quick Save')]")
public WebElement quicksave;

@FindBy(xpath="//*[@id='mainButton'][contains(text(),'Calculate')]")
public WebElement calculate;

@FindBy(xpath="//*[@id='mainButton'][contains(text(),'Cancel')]")
public WebElement cancel;

@FindBy(xpath="//*[@id='mainButton'][contains(text(),'Save')]")
public WebElement save;

public quoteLineEditor(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//this.driver=driver;
	super();
	//setTitle();
	PageFactory.initElements(driver, this);
}

public WebElement getFreeperiod(){
	return Freeperiod;
}


public WebElement getStartDate(){
	return StartDate;
}

public WebElement getTermstartdate(){
	return Termstartdate;
}

public WebElement getSubscriptionterm(){
	return Subscriptionterm;
}

public WebElement getAutorenew(){
	return Autorenew;
}
public WebElement getRenewnoticeperiod(){
	return Renewnoticeperiod;
}

public WebElement getRetrobilling(){
	return Retrobilling;
}

public WebElement getpaymentterms(){
	return paymentterms;
}

public WebElement getterminationforconvenience(){
	return terminationforconvenience;
}
public WebElement getcasestudy(){
	return casestudy;
}

public WebElement getrenwaluplift(){
	return renwaluplift;
}

public WebElement getMarketinglanguage(){
	return Marketinglanguage;
}

public WebElement getDealparameters(){
	return Dealparameters;
}
	

public WebElement gettoggle(){
	return toggle;
}

public WebElement getdiscount(){
	return discount;
}

public WebElement getdiscountoptions(){
	return discountoptions;
}

public WebElement getdiscounttext(){
	return discounttext;
}


public WebElement getaddproducts(){
	return addproducts;
}

public WebElement getaddgroup(){
	return addgroup;
}

public WebElement getquicksave(){
	return quicksave;
}

public WebElement getcalculate(){
	return calculate;
}

public WebElement getcancel(){
	return cancel;
}

public WebElement getsave(){
	return save;
}


public WebElement getselectedstart(){
	return selectedstartdate;
}

public WebElement getselectedterm(){
	return selectedtermdate;
	
}

public WebElement getreconfig(){
	return reconfig;

}
}
