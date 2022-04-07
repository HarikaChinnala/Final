package Regression;

import java.awt.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.createOppPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Pageobjects.oppPage;
import Pageobjects.reusablemethods;
import Regression.productConfiguration;
import Regression.Base;

public class Quote extends Base {
	
	@Test
	public void createQuote() throws IOException, InterruptedException
	{
		driver= initialiseDriver();
		landingPage lp= new landingPage(driver);
		createQuotePage qp= new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		oppPage op= new oppPage(driver);
	//	String txt= driver.findElement(By.xpath("//*[text()='Selling Lane']/parent::div/following-sibling::div/span/slot/slot/records-formula-output/slot/lightning-formatted-text")).getText();
	//System.out.println(txt);
		driver.get("https://neustar--ltnstage.lightning.force.com/lightning/r/SBQQ__Quote__c/a837j0000004m2FAAQ/view");
		Thread.sleep(15000);
		try{
			op.geteditlines().click();
		}
		catch(Exception e){
		js.executeScript("arguments[0].click();", op.geteditlines());}
		Thread.sleep(10000);
		configuration pc= new configuration(driver);
		pc.proconfiguration(sellinglane, productname,freeperiod, Termstartdate, Subscriptionterm, startdate,discount,discountoption,subproducts,subsubproducts,scopeofrecords, billingoption, billingfrequency,CustomersTNOwnership,Maxseats,interfaceType,OCN,solutiondescription,threshold,monthlyminfee,monthlyminlistvol,nameofsubclient,additionalFileSliceCriteria,ActiveStatuscb,HandsetStatuscb,RoamingIdentifierscb,SMSEnabledcb,InternationalRevShareFraudIdentifiercb,CFCAorGSMAMembercb,billingoptionBRMmonthly,billingfrequencyBRMmonthly
				,billingoptionBRMyearlyservice
				,billingfrequencyBRMyearlyservice
				,billingoptionBRMyearlylist
				,billingfrequencyBRMyearlylist
				,billingoptionUSIYP
				,billingfrequencyUSIYP
				,billingoptionUSEBR
				,billingfrequencyUSEBR
				,billingoptionUSAPI
				,billingfrequencyUSAPI
				,billingoptionCanadaIYP
				,billingfrequencyCanadaIYP
				,datadeliveryfrequencyUSIYP
				,datadeliveryfrequencyUSEBR
				,datadeliveryfrequencycanadaIYP,existingclient,NumbofPlatform
				,NumbofAudience
				,geography
				,fileattributes
				,frequency
				,feedtype
				,impressions
				,platformdescription
				,audiencedescription
				,unlimitedimpressions
				,numbofDMA
				,numbofadvisoryservicehrs
				,listofDMA);
	}
}
