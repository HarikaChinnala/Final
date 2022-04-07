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

public class createQuoteandConfigure extends Base {
	//public String existquote;
	//createOpportunity cp=new createOpportunity();
	
	@Test(priority=1)
	public void createQuote() throws IOException, InterruptedException
	{
		driver= initialiseDriver();
		landingPage lp= new landingPage(driver);
		createQuotePage qp= new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		Thread.sleep(3000);
		try{
		js.executeScript("arguments[0].click();", lp.getOpportunities());
		}
		catch (Exception e1){
			
			driver.findElement(By.cssSelector("[title='Opportunities']")).click();
		}
		//lp.getOpportunities().click();
		Thread.sleep(5000);
		lp.getopptxtbox().sendKeys(opportunityname);
		lp.getopptxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//lp.getsrchresult().click();
		//String OppName = prop.getProperty("OppName");
		driver.findElement(By.xpath("//*[@title='"
								+ opportunityname
								+ "']")).click();
		Thread.sleep(20000);
		waitForWebElementPresent(lp.getnewQuote());
		js.executeScript("arguments[0].click();", lp.getnewQuote()); 
		
		Thread.sleep(15000);
		js.executeScript("arguments[0].click();",qp.getRNperiod());
		Thread.sleep(5000);
		//String RNperiod = prop.getProperty("RNperiod");
		driver.findElement(By.xpath("//*[@title='"
				+ RNperiod
				+ "']")).click();
		
		qp.getsave().click();
		getsuccessmessage();
		String quote = driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span")).getText();		

		System.out.println(quote);
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(45).createCell(1).setCellValue(quote);
		wb.write(fout);
		
//		prop.setProperty("quote", quote);
//		try (final OutputStream outputstream = new FileOutputStream("src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}
		driver.navigate().refresh();
		Thread.sleep(10000);
	}
		@Test(priority=2)
		public void configureQuote() throws IOException, InterruptedException
		{
//			driver= initialiseDriver();
 
			JavascriptExecutor js = (JavascriptExecutor)driver;
			oppPage op= new oppPage(driver);
			//productConfigurationtestdemo pc= new productConfigurationtestdemo(driver);
//			JavascriptExecutor js = (JavascriptExecutor)driver;

		
		
		js.executeScript("arguments[0].click();", op.getDetail());
		Thread.sleep(10000);
		//driver.findElement(By.xpath("(//*[contains(text(),'Primary Quote')]/parent::div)/following-sibling::div[1]/span/slot/slot/force-lookup/div/force-hoverable-link/div/a/span[@id='window']")).click();
		js.executeScript("arguments[0].click();", op.getquotelink());
		Thread.sleep(15000);
		try{
			op.geteditlines().click();
		}
		catch(Exception e){
			
		js.executeScript("arguments[0].click();", op.geteditlines());}
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(30000);
	// configured products
		Thread.sleep(10000);
		//pc.productConfiguration(sellinglane, productname,freeperiod, Termstartdate, Subscriptionterm, startdate,discount,discountoption);
//		getsuccessmessage();
		configuration pc= new configuration(driver);
		pc.proconfiguration(sellinglane, productname,freeperiod, Termstartdate, Subscriptionterm, startdate,discount,discountoption,subproducts,subsubproducts, scopeofrecords, billingoption, billingfrequency,CustomersTNOwnership,Maxseats,interfaceType,OCN,solutiondescription,threshold,monthlyminfee,monthlyminlistvol,nameofsubclient,additionalFileSliceCriteria,ActiveStatuscb,HandsetStatuscb,RoamingIdentifierscb,SMSEnabledcb,InternationalRevShareFraudIdentifiercb,CFCAorGSMAMembercb,billingoptionBRMmonthly,billingfrequencyBRMmonthly
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
		
        driver.navigate().refresh();
 
        Thread.sleep(10000);
  
		}
		
		@Test(dependsOnMethods={"configureQuote"})
		//@Test(priority=2)
		public void generatequoteproposal() throws IOException, InterruptedException
		{
		createQuotePage qp= new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		reusablemethods rm= new reusablemethods(driver);
		Thread.sleep(5000);
        js.executeScript("arguments[0].click();", qp.getGenquoteprop());
        Thread.sleep(60000);
        rm.generatedoc(driver);
        js.executeScript("arguments[0].click();", qp.getRelated()); 
        Thread.sleep(10000);
        
        Actions a = new Actions(driver);
        a.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(15000);
        
        String file=qp.getFiles().getAttribute("title");
        if(file.startsWith("Quote Proposal -"))
        System.out.println("File is attached");
        else
        	System.out.println("File is not attached");
        driver.navigate().refresh();
        Thread.sleep(15000);
		}
		@Test(dependsOnMethods={"generatequoteproposal"})
		public void approveQuote() throws IOException, InterruptedException
		{
			
			createQuotePage qp= new createQuotePage(driver);
			JavascriptExecutor js = (JavascriptExecutor)driver;
        qp.getdropdown().click();
        Thread.sleep(5000);
  
        try{
        js.executeScript("arguments[0].click();", qp.getsubmitapproval());
       // Thread.sleep(10000);
        }
        catch(Exception e){
        	js.executeScript("arguments[0].click();", qp.getsubmitapprovaluat());
       // Thread.sleep(10000);
        }
        Thread.sleep(25000);
        String app = qp.getapproved().getAttribute("aria-checked");
       // System.out.println(app);
        if (app.equals("true")){
        	System.out.println("Quote is approved");
        }
        else {
        	System.out.println("Quote is not approved");
        Assert.assertTrue(false);
        }
//        getsuccessmessage();
      driver.close();
        }
		
		public  void getsuccessmessage(){
			WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			waitForWebElementPresent(msg);
			WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
			if(sm.getText().contains("success"))
			System.out.println(msg.getText());
		}
	
}