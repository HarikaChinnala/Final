package Regression;

import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import Pageobjects.contractPage;
import Pageobjects.createOppPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Pageobjects.oppPage;
import Regression.Base;


public class contractSubmitForSignature extends Base {
	public String type;
	
	@Test
	public void SubmitForSignature() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		contractPage c= new contractPage(driver);
		oppPage op= new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get(contracturl);
		Thread.sleep(20000);
		type= driver.findElement(By.xpath("//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span")).getText();
		System.out.println(type);
		if(type.equals("Service Order")){
					//js.executeScript("window.scrollBy(0,2700)", "");
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[text() = 'Billing Contact']"))).build().perform();
		js.executeScript("window.scrollBy(0,200)", "");
		WebElement ele = driver.findElement(By.xpath("((//*[text() = 'Billing Contact'])//parent::div)//following-sibling::div/span[@class='test-id__field-value slds-form-element__static slds-grow ']"));
		if(ele.getText().isEmpty())
		{
		act.doubleClick(ele).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/input")).sendKeys(primarycontact);
		Thread.sleep(5000);
		//String primarycontact = prop.getProperty("primarycontact");
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/div/div[@class='listContent']/ul/li/a/div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']/div[@title='"+primarycontact+"']")));
		driver.findElement(By.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/div/div[@class='listContent']/ul/li/a/div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']/div[@title='"
				+ primarycontact
				+"']")).click();
		driver.findElement(By.xpath("//*[text() = 'Save']")).click();
		}
		Thread.sleep(15000);
		driver.navigate().refresh();
		
		Thread.sleep(15000);
		c.Dropdown().click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
		Thread.sleep(5000);
		try{
			WebElement subsignpop=driver.findElement(By.xpath("//*[@class='modal-body scrollable slds-modal__content slds-p-around--medium']"));	
		if(subsignpop.isDisplayed()){
			
		}
		}
		catch(Exception e){
		System.out.println(driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")).getText());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Close']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='slds-form-element__control']/div/div/a")));
		Thread.sleep(10000);
		if(sellinglane.equals("Communications")){
			String acvtotal=op.getacvtotal().getText().substring(4);
			String acvt=acvtotal.replace(",","");
			Double at=Double.parseDouble(acvt);
			if(at>=50000){
				userprop();	
			}
			
		}
		else if(sellinglane.equals("Security")) 
		{
			String acvcommited=op.getacvcommitted().getText().substring(4);
			String acv=acvcommited.replace(",","");
			Double acvcc=Double.parseDouble(acv);
			if(acvcc>=25000){	userprop();
						}	
				
				
			}
			else{
			String acvcommit=op.getacvcommitted().getText().substring(4);
				String acvc=acvcommit.replace(",","");
				Double ac=Double.parseDouble(acvc);
				if(ac>=50000){	
					userprop();
			}
		}
		
		driver.navigate().refresh();
		Thread.sleep(15000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@class='container forceRelatedListSingleContainer']/article/div[2]/div/div/div/div//a")));
		Thread.sleep(15000);
		c.Dropdown().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(10000);
		
		}
		}
		
		else{
		c.Dropdown().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(10000);
		}
		//driver.findElement(By.xpath("(//*[@class='select'])[1]")).click()0		
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("(//*[@class='select'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(5000);
		//driver.navigate().refresh();
		
		for(int i=1;i<=5;i++) { 
			if(c.Details_Status().getText().equalsIgnoreCase("Client Signed") && c.Contract_Status().getText().equalsIgnoreCase("Client Signed")) { 
				driver.navigate().refresh(); 
				Thread.sleep(25000); 
			}
			else{ 
				System.out.println(i); 
				break; 
			} 
		}
		
		Thread.sleep(15000);
		//To Check the status of the Chevron
		if(c.Chevron_SubmissionUnderReview().getAttribute("class").contains("active"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		
		//Details_Status
		System.out.println("Contract Status after Submit for Signature in Details Section = " +c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Submission Under Review");
		
		//Contract_Status
		System.out.println("Contract Status after Submit for Signature = " +c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Submission Under Review");
		
		c.Related_Tab().click();
		
		//Cases
		try {
			c.Cases_Section().click();
		}
		catch(Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(c.Cases_Section()).build().perform();
			js.executeScript("window.scrollBy(0,175)","");
			c.Cases_Section().click();
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		try {
			
			List<WebElement> Cases_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Contract Submission')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit for Signature is done = " + Cases_Count);
			if(Cases_Count == 1) {
				Assert.assertTrue(true);
			}
			else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			c.Case_SubmitForSignature().click();
			Thread.sleep(10000);
			System.out.println(c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			driver.navigate().back();
		}
		catch(Exception e){
			
			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}
		driver.navigate().back();
		Thread.sleep(10000);
		
	driver.close();
	        
		
	}
	public void userprop() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		oppPage op= new oppPage(driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Edit Why Neustar Won']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[contains(text(),'Why Neustar Won')]//following-sibling::div/textarea")).sendKeys("Test");
		driver.findElement(By.xpath("//label[contains(text(),'How did Neustar Improve their Process')]//following-sibling::div/textarea")).sendKeys("Test");
	    driver.findElement(By.xpath("//label[contains(text(),'Solution Description')]//following-sibling::div/textarea")).sendKeys("Test");
		driver.findElement(By.xpath("//label[contains(text(),'Customer Current Process')]//following-sibling::div/textarea")).sendKeys("Test");
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//input")));
		driver.findElement(By.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//div/div[2]/lightning-base-combobox-item[@data-value='"+competitor+"']")).click();
		if(competitor.equals("Other"))
			driver.findElement(By.xpath("//label[contains(text(),'Competitor (If Other)')]//following-sibling::div/input")).sendKeys("Test");
		op.getSaveedit().click();
		Thread.sleep(10000);
	}
}
