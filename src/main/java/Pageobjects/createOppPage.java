package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createOppPage {
	
	

@FindBy(xpath="//*[@placeholder='Search Currencies...']")
public WebElement currency;

@FindBy(xpath="//*[@name='Name']")
public WebElement OppName;

@FindBy(xpath="//*[@name='CloseDate']")
public WebElement closeDate;

@FindBy(xpath="//label[contains(text(),'Stage')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement stage;

@FindBy(xpath="//*[@title='0 - Prospecting']")
public WebElement stage0;

@FindBy(xpath="//label[contains(text(),'Opportunity Type')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement OppType;

@FindBy(xpath="//label[contains(text(),'Selling Lane')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement sellinglane;

@FindBy(xpath="//label[contains(text(),'Product / Use Case')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement usecase;

@FindBy(xpath="//label[contains(text(),'Entity')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement entity;

@FindBy(xpath="//label[contains(text(),'MSA Date')]/following-sibling::div[1]/input")
public WebElement MSADate;

@FindBy(xpath="//label[contains(text(),'Service Order Date')]/following-sibling::div[1]/input")
public WebElement SODate;

@FindBy(xpath="//label[contains(text(),'Primary Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div[1]/div/input")
public WebElement primcntct;

@FindBy(xpath="//label[contains(text(),'Billing Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div[1]/div/input")
public WebElement Billcntct;


@FindBy(xpath="//label[contains(text(),'Pilot?')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement pilot;

@FindBy(xpath="//label[contains(text(),'Found by Channel?')]/following-sibling::div[1]/lightning-base-combobox/div/div/input")
public WebElement foundbychannel;

@FindBy(xpath="(//*[@role='listbox'])[2]/div[2]")
public WebElement selectcurrency;

//@FindBy(xpath="(//*[@role='listbox'])[4]/div[1]")
@FindBy(xpath="(//*[@role='listbox'])[4]/div[2]/ul/li/a/div[2]/div[@title='QA']")
public WebElement selectcntct;

//@FindBy(xpath="(//*[@role='listbox'])[5]/div[1]")
@FindBy(xpath="(//*[@role='listbox'])[5]/div[2]/ul/li/a/div[2]/div[@title='QA']")
public WebElement selectbillcntct;

@FindBy(xpath="//*[@title='Save']")
public WebElement save;

@FindBy(xpath="(//*[@title='USD'])[2]")
public WebElement USD;

@FindBy(xpath="//button[@name='today']")
public WebElement Today;

@FindBy(xpath="//*[@title='New Service']")
public WebElement Newservice;

@FindBy(xpath="//*[@title='New Logo']")
public WebElement Newlogo;

@FindBy(xpath="//*[@title='Security']")
public WebElement Security;

@FindBy(xpath="//*[@title='Communications']")
public WebElement CommSL;

@FindBy(xpath="//*[@title='Marketing']")
public WebElement MarketSL;

@FindBy(xpath="//*[@title='Risk']")
public WebElement RiskSL;

@FindBy(xpath="//*[@title='DNS']")
public WebElement ucDNS;

@FindBy(xpath="//*[@title='Security Services, LLC']")
public WebElement enSSL;

@FindBy(xpath="//*[@title='--None--']")
public WebElement None;

@FindBy(xpath="//*[@title='Yes']")
public WebElement Yes;

@FindBy(xpath="//*[@title='No']")
public WebElement No;

public createOppPage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//this.driver=driver;
	super();
	//setTitle();
	PageFactory.initElements(driver, this);
}

public WebElement getcurrency(){
	return currency;
}

public WebElement getOppName(){
	return OppName;
}

public WebElement getcloseDate(){
	return closeDate;
}

public WebElement getstage(){
	return stage;
}

public WebElement getOppType(){
	return OppType;
}

public WebElement getsellinglane(){
	return sellinglane;
}

public WebElement getusecase(){
	return usecase;
}

public WebElement getentity(){
	return entity;
}

public WebElement getMSADate(){
	return MSADate;
}

public WebElement getSODate(){
	return SODate;
}

public WebElement getprimcntct(){
	return primcntct;
}

public WebElement getBillcntct(){
	return Billcntct;
}

public WebElement getselectcurrency(){
	return selectcurrency;
}

public WebElement getselectcntct(){
	return selectcntct;
}


public WebElement getselectbillcntct(){
	return selectbillcntct;
}

public WebElement getsave(){
	return save;
}

public WebElement getUSD(){
	return USD;
}

public WebElement getToday(){
	return Today;
}

public WebElement getstage0(){
	return stage0;
}

public WebElement getNewservice(){
	return Newservice;
}

public WebElement getNewlogo(){
	return Newlogo;
}

public WebElement getSecuritySL(){
	return Security;
}

public WebElement getCommSL(){
	return CommSL;
} 

public WebElement getMarketSL(){
	return  MarketSL;
}

public WebElement getRiskSL(){
	return  RiskSL;
}

public WebElement getucDNS(){
	return  ucDNS;
}

public WebElement getenSSL(){
	return  enSSL;
} 

public WebElement getpilot(){
	return  pilot;
} 

public WebElement getfoundbychannel(){
	return  foundbychannel;
} 

public WebElement getNone(){
	return  None;
}

public WebElement getYes(){
	return  Yes;
}

public WebElement getNo(){
	return  No;
}

	
}
