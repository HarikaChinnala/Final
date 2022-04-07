package Regression;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.contractPage;
import Pageobjects.createOppPage;
import Pageobjects.landingPage;
import Pageobjects.legalDocumentPage;
import Pageobjects.loginPageObject;
import Regression.Base;

public class createNonRevenueContract extends Base {

	@Test
	public void  createNonRevenueContract() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		landingPage lp= new landingPage(driver);
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", lp.getAccount());
		Thread.sleep(5000);
		lp.getAcctbox().sendKeys(Accountname);
		lp.getAcctbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//lp.getsrchresult().click();
		//String Accountname = prop.getProperty("Accountname");
		driver.findElement(By.xpath("//*[text()='"
								+ Accountname
								+ "']")).click();
		accountPage ap= new accountPage(driver);
		Thread.sleep(20000);
		ap.getmore().click();
		js.executeScript("arguments[0].click();",ap.getlegal());
		Thread.sleep(5000);
		legalDocumentPage ldp=new legalDocumentPage(driver);
		js.executeScript("arguments[0].click();",ldp.getServiceline());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@name='Service_Line__c']//parent::div)/following-sibling::div/lightning-base-combobox-item[@data-value='"+serviceline+"']")));
		
		js.executeScript("arguments[0].click();",ldp.getDoctype());
		Select s= new Select(ldp.getDoctype());
		s.selectByVisibleText(""+doctype+"");
		Thread.sleep(5000);
		System.out.println("Document type Selected is :"+doctype);
		
		js.executeScript("arguments[0].click();",ldp.getlegalentity());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@name='NDA_Entity__c']//parent::div)/following-sibling::div/lightning-base-combobox-item[@data-value='"+legalentity+"']")));
		Thread.sleep(3000);
		ldp.getcountercontact().sendKeys("Sales Ops Test Contact");	
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(((//*[@for='custSignedBy']//following-sibling::lightning-input-field)//input)/parent::div)/following-sibling::div/ul/li/lightning-base-combobox-item/span[2]/span/*[@title='Sales Ops Test Contact']")));
		WebElement msg1= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg1);
		System.out.println("Message on wrong counter contact selection:"+"\n"+msg1.getText());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@for='custSignedBy']//following-sibling::lightning-input-field)//*[contains(text(),'Clear Selection')]")));
		ldp.getcountercontact().sendKeys(""+countercontact+"");
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(((//*[@for='custSignedBy']//following-sibling::lightning-input-field)//input)/parent::div)/following-sibling::div/ul/li/lightning-base-combobox-item/span[2]/span/*[@title='"+countercontact+"']")));
		Thread.sleep(5000);
		
		ldp.getcomments().sendKeys("Testing comments");
		Thread.sleep(5000);
        js.executeScript("window.scrollBy(0,200)", "");
		if(doctype.equals("Assignment document from Customer")||doctype.equals("Other Non-Revenue Document")){
			js.executeScript("arguments[0].click();", ldp.getsubmit());
			WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			waitForWebElementPresent(msg);
			System.out.println("Message displayed to Upload files:"+"\n"+msg.getText());
			ldp.getupload().click();
			System.out.println("Upload Document Manually");
			// for manual upload
			Thread.sleep(45000);
			if(paper.equals("neustar")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Neustar')]//preceding-sibling::span")));
			}
			else if(paper.equals("3rd")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Third-Party')]//preceding-sibling::span")));
				
			}
			driver.findElement(By.xpath("//*[@name='Document__c']")).clear();
			driver.findElement(By.xpath("//*[@name='Document__c']")).sendKeys(doctype+"-uploaded");
	
		}
		else if(upload.equals("yes")){
			ldp.getupload().click();
			System.out.println("Upload Document Manually");
			// for manual upload
			Thread.sleep(45000);
			if(paper.equals("neustar")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Neustar')]//preceding-sibling::span")));
			}
			else if(paper.equals("3rd")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Third-Party')]//preceding-sibling::span")));
				
			}
			driver.findElement(By.xpath("//*[@name='Document__c']")).clear();
			driver.findElement(By.xpath("//*[@name='Document__c']")).sendKeys(doctype+"-uploaded");
		}
		Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ldp.getsubmit());
			Thread.sleep(10000);
			try{
			waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='divSection']")));
			System.out.println("Message on Contract creation:"+"\n"+driver.findElement(By.xpath("//*[@class='divSection']")).getText());
			}
			catch(Exception e){
				
			}
			
			//		try{
//			js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[starts-with(text(),'here')]")));
//		}
//		catch(Exception e){
		Thread.sleep(80000);
//		}
		String  curl = driver.getCurrentUrl();
	       String  id= driver.findElement(By.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span")).getText();
	  //      System.out.println("Contractid is:"+id);
	  //      System.out.println("Contracturl is:"+curl);
	       
	       FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		   sheet.getRow(107).createCell(1).setCellValue(id);
		   sheet.getRow(108).createCell(1).setCellValue(curl);
		   wb.write(fout);
	       
	       
//	        prop.setProperty("contractid", id);
//	        prop.setProperty("contracturl", curl);
//			try (final OutputStream outputstream = new FileOutputStream("src/main/java/Regression/data.properties");) {
//				prop.store(outputstream, "File Updated");
//				outputstream.close();
//			}
			System.out.println("Contract Pagelayout Values:-");
			// Contract Number
			System.out.println("Contract Number: " + id);
			// Account name
			String accname = cp.getaccountname().getText();
			System.out.println("accountname: " + accname);
			// Type
			String type = driver.findElement(By.xpath("(//*[contains(text(),'Type')]//parent::div)/following-sibling::div/span/span")).getText();
			System.out.println("Type: " + type);
			// Status
			String status = driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/span")).getText();
			System.out.println("Status: " + status);
			// Billing contact
			String billcontact = cp.getbillcontact().getText();
			System.out.println("billingcontact: " + billcontact);
			if(doctype.equals("Master Services Agreement (MSA)")||doctype.equals("Master Services Agreement (MSA) - Amendment")){
				Assert.assertEquals(type,"Master Service Agreement (MSA)");
			}
			else if(doctype.equals("Reseller Partner Agreement - Security")){
				Assert.assertEquals(type,"Reseller Partner Agreement");
			}
			else{
				Assert.assertEquals(type,"Legal");
			}
			Assert.assertEquals(accname, Accountname);
			Assert.assertEquals(billcontact, countercontact);
			if(upload.equals("yes")||doctype.equals("Assignment document from Customer")||doctype.equals("Other Non-Revenue Document")){
				Assert.assertEquals(status, "Edit Under Review");
			}
			else
			Assert.assertEquals(status, "Draft");
			try {
				js.executeScript("arguments[0].click();", cp.getRelated());
			} catch (Exception e3) {

				driver.findElement(By.xpath("//*[@class='tabs__nav']/li/a[@title='Related']")).click();
			}
			Thread.sleep(5000);
			Actions a = new Actions(driver);
			a.moveToElement(driver.findElement(By.xpath("//*[@title='Files']"))).build().perform();
			Thread.sleep(2000);
			
			String file=cp.getFiles().getAttribute("title");
			System.out.println("File attached is :"+file);
	//		System.out.println(id+".docx");
			if(doctype.equals("Assignment document from Customer")||doctype.equals("Other Non-Revenue Document")){
				if(file.isEmpty())	
					Assert.assertTrue(false);
				else
					System.out.println("File is attached");
				}
			else if(upload.equals("yes")){
				if(file.isEmpty())	
					Assert.assertTrue(false);
				else
					System.out.println("File is attached");
				
				
			}
				else{
   		if(file.endsWith(id+".docx"))
   			System.out.println("File is attached");
   		else
   			Assert.assertTrue(false);
				}
			if(upload.equals("yes")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a/*[@title='Approvals']")));
				Thread.sleep(5000);
				int x=driver.findElements(By.xpath("//*[starts-with(text(),'A-')]/parent::span/parent::th/parent::tr")).size();
				if(x==1){
					System.out.println("Approval record created");
					driver.navigate().back();
					Thread.sleep(10000);
				}
				else
					Assert.assertTrue(false);
			}
			
			if(!doctype.equals("Privacy - BAA") || !doctype.equals("Privacy - CCPA IPA") || !doctype.equals("Privacy - GDPR DPA (NIS)") || !doctype.equals("Privacy - GDPR DPA (Security Services, LLC)"))
			{try {
				js.executeScript("arguments[0].click();", cp.getmanageclauses());
			} catch (Exception e2) {

				driver.findElement(By.xpath("(//*[@title='Managed Clauses'])[2]")).click();
			}

			Thread.sleep(5000);

			int count = driver.findElements(By.xpath("//*[@role='grid']/tbody/tr")).size();
			// System.out.println(count);

			if (count > 0)
				System.out.println("Manage clauses are generated");
			else
				System.out.println("Manage clauses are not generated");
			driver.navigate().back();
			}
		driver.close();
		
	}
}