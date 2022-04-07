package Regression;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import Pageobjects.contractPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.oppPage;
import Pageobjects.quoteLineEditor;
import Regression.productReConfiguration;
import Regression.Base;

public class editQuoteandConfigure extends Base {
	public String quoteline;
	//public String existquote;
	//createOpportunity cp=new createOpportunity();
	
	@Test(priority=1)
	public void createQuote() throws IOException, InterruptedException
	{
		createQuoteandConfigure cqc = new createQuoteandConfigure();
		cqc.createQuote();
		cqc.configureQuote();
		cqc.generatequoteproposal();
		driver.close();
	}
	
	@Test(priority=1)
	public void reconfigQuote() throws IOException, InterruptedException
	{
		driver= initialiseDriver();
		createQuotePage qp= new createQuotePage(driver);
		oppPage op= new oppPage(driver);
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		quoteLineEditor ql=new quoteLineEditor(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		productReConfiguration prc=new productReConfiguration(driver);
		js.executeScript("arguments[0].click();", lp.getQuote());
		Thread.sleep(5000);
		lp.getquotetxtbox().sendKeys(quote);
		lp.getquotetxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@title='" + quote + "']")).click();
		Thread.sleep(10000);
		Thread.sleep(15000);
		waitForWebElementPresent(op.geteditlines());
		js.executeScript("arguments[0].click();", op.geteditlines());
		Thread.sleep(30000);
		int x = driver.findElements(By.xpath("//iframe")).size();
		for (int i = 0; i < x; i++) {
			driver.switchTo().frame(i);
			try {
		ql.gettoggle().click();
		Thread.sleep(15000);
		waitForWebElementPresent(ql.getreconfig());
		js.executeScript("arguments[0].click();", ql.getreconfig());
		Thread.sleep(5000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@id='bundleContainer']/div/h1")));
		 quoteline = driver.findElement(By.xpath("//*[@id='bundleContainer']/div/h1")).getText();
		System.out.println(quoteline);
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
			}
			
		}
		Thread.sleep(5000);
		
		prc.productReConfiguration(sellinglane, productname,freeperiod, Termstartdate, Subscriptionterm, startdate,discount,discountoption,subproducts,subsubproducts, scopeofrecords, billingoption, billingfrequency,CustomersTNOwnership,Maxseats,interfaceType,OCN,solutiondescription,threshold,monthlyminfee,monthlyminlistvol,nameofsubclient,additionalFileSliceCriteria,ActiveStatuscb,HandsetStatuscb,RoamingIdentifierscb,SMSEnabledcb,InternationalRevShareFraudIdentifiercb,CFCAorGSMAMembercb,billingoptionBRMmonthly,billingfrequencyBRMmonthly
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
		
		driver.close();
	}
}