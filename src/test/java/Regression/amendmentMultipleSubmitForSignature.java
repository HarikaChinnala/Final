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
import Pageobjects.reusablemethods;
import Regression.Base;
import Regression.amendmentSubmitForSignature;


public class amendmentMultipleSubmitForSignature extends Base {
	public String type;
	
	@Test
	public void SubmitForSignature() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		contractPage c= new contractPage(driver);
		oppPage op= new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions act = new Actions(driver);
		driver.get(amendurl);
		Thread.sleep(20000);
		type= driver.findElement(By.xpath("//*[text()='Opportunity Type']/parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
		System.out.println(type);
		
		Thread.sleep(15000);
		js.executeScript("window.scrollBy(0,1300)", "");

		act.moveToElement(driver.findElement(By.xpath("//*[@title='Edit Billing Contact']"))).build().perform();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Edit Billing Contact']")));
		
		Thread.sleep(5000);

		Thread.sleep(10000);
		WebElement bc = driver.findElement(By.xpath(
				"//*[text() = 'Billing Contact']//parent::lightning-grouped-combobox//following-sibling::div/div/lightning-base-combobox/div/div/input"));
		String bcvalue = bc.getAttribute("placeholder");
		if (bcvalue.equals("Search Contacts...")) {
			bc.sendKeys(primarycontact);

			Thread.sleep(5000);

			driver.findElement(By.xpath(
					"//*[text() = 'Billing Contact']//parent::lightning-grouped-combobox//following-sibling::div/div/lightning-base-combobox/div/div/ul/li/lightning-base-combobox-item/span/span/lightning-base-combobox-formatted-text[@title='"
							+ primarycontact + "']"))
					.click();
		}
		driver.findElement(By.xpath("//button[text() = 'Save']")).click();
		Thread.sleep(20000);
		
//		driver.navigate().refresh();
//		Thread.sleep(20000);
		
		reusablemethods s = new reusablemethods(driver);
		s.SubmitforSignature(driver);
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		
		String status = op.getAmendcontractstatus().getText();
		System.out.println(status);
		Assert.assertEquals(op.getAmendcontractstatus().getText(), "Submission Under Review");

		Thread.sleep(5000);
		if (driver.findElement(By.xpath("//*[@data-name='6 - Ops Review']")).getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		driver.findElement(By.xpath("//*[text()='Related']")).click();
		Thread.sleep(10000);

		// Cases
		js.executeScript("window.scrollBy(0,200)", "");

		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();

		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		changestatus(pendingcasestatus);
		changestatus(escalatedcasestatus);
		changestatus(closedcasestatus);
		
		
		
	driver.close();
	        
		
	}
	public void changestatus(String casestatus) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		contractPage c= new contractPage(driver);
		
		try {
			
			List<WebElement> Cases_List = driver.findElements(
					By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Contract Submission')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit For Signature is done = " + Cases_Count);
			if(Cases_Count == 1) {
				Assert.assertTrue(true);
			}
			else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath(
					"//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Contract Submission')]/parent::span/parent::td/preceding-sibling::th/span/a"))
					.click();
			Thread.sleep(10000);
			System.out.println("Case Owner: "+c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			System.out.println("Case status is: "+driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText());
			js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='"+casestatus+"']")));
			Thread.sleep(5000);
			System.out.println("Selected Case Status: "+casestatus);
			getsuccessmessage();
			driver.navigate().back();
		}
		catch(Exception e){
			
			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}
		driver.navigate().back();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[text()='Show more actions']//parent::button")).click();

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//span[text() = 'Submit For Signature']")));

		Thread.sleep(5000);
		
		//driver.findElement(By.xpath("(//*[@class='select'])[1]")).click()0		
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("(//*[@class='select'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		
		driver.findElement(By.xpath("//*[text()='Related']")).click();
		Thread.sleep(10000);

		// Cases
		js.executeScript("window.scrollBy(0,200)", "");

		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		Actions act = new Actions(driver);
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Contract Submission')]/parent::span/parent::td/preceding-sibling::th/span/a"))
				.click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		System.out.println("Case Owner: "+c.Case_Owner().getText());
		Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
		String cs=driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
		System.out.println("Case status is: "+cs);
		
		if(casestatus.contains("Pending"))	{
			if(cs.equals("Unresponded/New"))
				System.out.println("Case status successfully moved to Unresponded/New");
			else
				Assert.assertTrue(false);	
		}
		else if(casestatus.contains("Closed"))	{
			if(cs.equals("Unresponded/New"))
				System.out.println("Case status successfully moved to Unresponded/New");
			else
				Assert.assertTrue(false);	
		}
				
		else if(casestatus.equals("Escalated")){
			if(cs.equals("Escalated"))
				System.out.println("Case status is not changed from Escalated");
			else
				Assert.assertTrue(false);	
		}
		else
			Assert.assertTrue(false);
		driver.navigate().back();
		
	}
	public  void getsuccessmessage(){
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if(sm.getText().contains("success"))
		System.out.println(msg.getText());
	}
	
}
