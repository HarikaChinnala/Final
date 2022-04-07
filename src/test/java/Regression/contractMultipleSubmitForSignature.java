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


public class contractMultipleSubmitForSignature extends Base {
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
		WebElement bc= driver.findElement(By.xpath("(//*[contains(text(),'Billing Contact')]//parent::div)/following-sibling::div/span"));
		if(bc.getText().isEmpty()){
					js.executeScript("window.scrollBy(0,2700)", "");
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		
		WebElement ele = driver.findElement(By.xpath("((//*[text() = 'Billing Contact'])//parent::div)//following-sibling::div/span[@class='test-id__field-value slds-form-element__static slds-grow ']"));
		act.doubleClick(ele).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/input")).sendKeys(primarycontact);
		Thread.sleep(5000);
		//String primarycontact = prop.getProperty("primarycontact");
		driver.findElement(By.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/div/div[@class='listContent']/ul/li/a/div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']/div[@title='"
				+ primarycontact
				+ "']")).click();
		driver.findElement(By.xpath("//*[text() = 'Save']")).click();
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(15000);
			}
		c.Dropdown().click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
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
		//driver.navigate().refresh();
		for(int i=1;i<=5;i++) {
			if(c.Details_Status().getText()!="Client Signed" && c.Contract_Status().getText()!="Client Signed") {
				break;
				}
			else{
				driver.navigate().refresh();
				Thread.sleep(10000);
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
		c.Dropdown().click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
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
		Thread.sleep(10000);
		c.Related_Tab().click();
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
		c.Case_SubmitForSignature().click();
		Thread.sleep(10000);
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
