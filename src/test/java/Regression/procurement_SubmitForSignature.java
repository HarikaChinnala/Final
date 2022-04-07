package Regression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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


public class procurement_SubmitForSignature extends Base {

	private String primdoc;

	@Test
	public void procurement_SubmitForSignature() throws IOException, InterruptedException {
		
		driver = initialiseDriver();
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//GovindLogin
		Govindlogin();
		String type = cp.gettype().getText();
		System.out.println(type);
		Assert.assertEquals(type, "Procurement");
		//Submit for Signature
		SubmitForSignature();
		
		for(int i=1;i<=5;i++) { 
			if(cp.Details_Status().getText().equalsIgnoreCase("Draft") && cp.Contract_Status().getText().equalsIgnoreCase("Draft")) { 
				driver.navigate().refresh(); 
				Thread.sleep(25000); 
			}
			else{ 
				System.out.println(i); 
				Assert.assertEquals(cp.Details_Status().getText(), "Submission Under Review");
                System.out.println(cp.Contract_Status().getText());
                Assert.assertEquals(cp.Contract_Status().getText(), "Submission Under Review");
				break; 
			} 
		}
		/*
		cp.Related_Tab().click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@title='Approvals']"));
		String appid="";
		String app = driver.findElement(By.xpath("//th[@scope='row']//a")).getText();
		int a = driver.findElements(By.xpath("//th[@scope='row']//a")).size();
		if (a == 1) {
			if (app.startsWith("A-")) {
				System.out.println("Approval record is created");
				appid=driver.findElement(By.xpath("//th/div/a")).getText();
				System.out.println("Approval Record:" + appid);
				System.out.println("Approval Record Status is: "+ driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText());
				Assert.assertEquals(driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText(), "Requested", "Approval record status is : "+driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText());
				String approval_assigned = driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[2]/span")).getText();
				System.out.println("Approval Record is assigned to :" +driver
						.findElement(By.xpath("//th[@scope='row']//following-sibling::td[2]/span")).getText());
				Assert.assertEquals(approval_assigned, "Procurement Admin Group");
			}
		} else if (a > 1) {
			System.out.println(a + "Approval records are created");
			Assert.assertTrue(false);
		}

		else {
			System.out.println("Approval record is not created");
			Assert.assertTrue(false);
		}
		
		driver.navigate().refresh();
		Thread.sleep(20000);
*/		
/*		
		//Error message when Submit for Signature is done without approving the approval record
		driver.findElement(By.xpath("//*[@title='Submit For Signature']")).click();
		Thread.sleep(5000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@type='submit']")));
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
//		Thread.sleep(5000);
		
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		System.out.println("Message when trying to do Submit for Signature without Approving revord: "+msg.getText());
		Assert.assertEquals(msg.getText(), "This contract needs approval and cannot be sent for signature.");
*/	
		driver.quit();
		
		NatashaLogin();
		contractPage cp1 = new contractPage(driver);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		
		//To Check the status of the Chevron
				if(cp1.Chevron_SubmissionUnderReview().getAttribute("class").contains("active"))
				{
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
				
				//Details_Status
				System.out.println("Contract Status after Submit for Signature in Details Section = " +cp1.Details_Status().getText());
				Assert.assertEquals(cp1.Details_Status().getText(), "Submission Under Review");
				
				//Contract_Status
				System.out.println("Contract Status after Submit for Signature = " +cp1.Contract_Status().getText());
				Assert.assertEquals(cp1.Contract_Status().getText(), "Submission Under Review");
/*				
				//Error message when eSignature is done without approving the approval record
				Thread.sleep(10000);
				cp1.getdropdown().click();
				JavascriptExecutor js2 = (JavascriptExecutor) driver;
				js2.executeScript("arguments[0].click();", cp1.getesign());
				System.out.print("Error message when trying to do eSignature without approving Approval records: ");
				geterrormessage();
				js2.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close']")));
				Thread.sleep(10000);
*/				
				cp1.Related_Tab().click();
				
				
				
				
				
				
				
				//Cases
				Thread.sleep(10000);
				cp1.Cases_Section().click();
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
					cp1.Case_SubmitForSignature().click();
					Thread.sleep(15000);
					System.out.println(driver.findElement(By.xpath("(((//*[text() = 'Case Owner'])//parent::div)//following-sibling::div)/span/slot/slot/force-owner-lookup/div/span/force-lookup/div/span/slot")).getText());
					Assert.assertEquals("Procurement Admin", driver.findElement(By.xpath("(((//*[text() = 'Case Owner'])//parent::div)//following-sibling::div)/span/slot/slot/force-owner-lookup/div/span/force-lookup/div/span/slot")).getText());
/*					String CaseRecordType = driver.findElement(By.xpath("(((//*[text() = 'Case Record Type'])//parent::div)//following-sibling::div)/span/slot/slot/force-record-type/div/div/span")).getText();
					System.out.println(CaseRecordType);
					Assert.assertEquals(CaseRecordType,"Community Cases");
*/					
					//driver.navigate().back();
				}
				catch(Exception e){
					
					System.out.println("No Case Records");
					Assert.assertFalse(true);
				}
				//driver.navigate().back();
				//Thread.sleep(10000);
				changestatus(pendingcasestatus);
				changestatus(escalatedcasestatus);
				changestatus(closedcasestatus);
				
				driver.close();
		
	}
	public void changestatus(String casestatus) throws InterruptedException, IOException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		contractPage c= new contractPage(driver);
		
		System.out.println("Case status is: "+driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText());
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='"+casestatus+"']")));
		Thread.sleep(3000);
		System.out.println("Selected Case Status: "+casestatus);
		getsuccessmessage();
		
		//Logout
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			Thread.sleep(1000);
			// driver.navigate().refresh();
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Search...']")));
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@role='listbox']/search_lightning-instant-result-item/div/div[2]/span[@title='"+contractid+"']")).click();
	        Thread.sleep(5000);
	        c.Related_Tab().click();
	        try {
				c.Approvals_Section().click();
				}
				catch(Exception e) {
					Thread.sleep(5000);
					Actions act = new Actions(driver);
					act.moveToElement(c.Approvals_Section()).build().perform();
					js.executeScript("window.scrollBy(0,175)","");
					c.Approvals_Section().click();
				}

	        // Approving the Approval Records
	        try {
	            //driver.navigate().refresh();
	            List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
	            int Approval_Count = Approval_List.size();
	            System.out.println("Number of Approval records = " + Approval_Count);
	            for (int i = 1; i <= Approval_Count; i++) {

	                Thread.sleep(10000);
	                c.Approvals_Requested().click();
	                //Thread.sleep(5000);
	                //driver.navigate().refresh();
	                Thread.sleep(10000);
	                c.Approve_Button().click();
	                Thread.sleep(20000);
	                int x = driver.findElements(By.xpath("//iframe")).size();
	                // System.out.println(x);
	                for (int j = 0; j < x; j++) {
	                    driver.switchTo().frame(j);

	                    try {
	                        c.Approve_Text().sendKeys(ApprovalComments);
	                        c.Approval_Submit().click();
	                    }

	                    catch (Exception e) {
	                    	
	                        driver.switchTo().defaultContent();
	                    }
	                    driver.switchTo().defaultContent();
	                }
	                Thread.sleep(10000);
			        driver.navigate().refresh();
					Thread.sleep(20000);
					c.Related_Tab().click();
					Thread.sleep(20000);
					try {
						c.Approvals_Section().click();
						}
						catch(Exception e) {
							Thread.sleep(5000);
							Actions act = new Actions(driver);
							act.moveToElement(c.Approvals_Section()).build().perform();
							js.executeScript("window.scrollBy(0,175)","");
							c.Approvals_Section().click();
						}
					Thread.sleep(10000);
	            }

	        } catch (Exception e) {
	        	
	            System.out.println("No Approval Records");
	            Assert.assertFalse(true);

	        }

	        Thread.sleep(5000);
		
		
		Govindlogin();
		SubmitForSignature();
		driver.quit();
		NatashaLogin();
		
		Thread.sleep(10000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		//contractPage c= new contractPage(driver);
		c.Related_Tab().click();
		Thread.sleep(10000);
		c.Cases_Section().click();
		Thread.sleep(5000);
		c.Case_SubmitForSignature().click();
		Thread.sleep(10000);
		String caseOwner=driver.findElement(By.xpath("(((//*[text() = 'Case Owner'])//parent::div)//following-sibling::div)/span/slot/slot/force-owner-lookup/div/span/force-lookup/div/span/slot")).getText();
		System.out.println("Case Owner: "+caseOwner);
		Assert.assertEquals("Procurement Admin", caseOwner);
//		String CaseRecordType = driver.findElement(By.xpath("(((//*[text() = 'Case Record Type'])//parent::div)//following-sibling::div)/span/slot/slot/force-record-type/div/div/span")).getText();
//		System.out.println(CaseRecordType);
//		Assert.assertEquals(CaseRecordType,"Community Cases");
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
	
	public void SubmitForSignature() throws InterruptedException {
		driver.findElement(By.xpath("//div[@title='Submit For Signature']")).click();
		Thread.sleep(5000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@type='submit']")));
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(1000);
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if(sm.getText().contains("success"))
		System.out.println(msg.getText());
		//String text = driver.findElement(By.xpath("//*[@class='toastMessage forceActionsText']")).getText();
		//System.out.println(text);
		Assert.assertEquals(msg.getText(), "Contract has successfully been submitted to Procurement Admin. You will be notified through Email or other external channels if Procurement Admin has any questions/concerns.");
	
	}
	
	
	public void NatashaLogin() throws InterruptedException, IOException {
		driver = initialiseDriver();
		contractPage cp1 = new contractPage(driver);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		
		js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js1.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			Thread.sleep(1000);
			// driver.navigate().refresh();
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
		
		//Natasha login
		
		String URL = driver.getCurrentUrl();
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + "0055w00000AjVBKAA3");
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
			driver.get("https://neustar--ltnstage.lightning.force.com/" + "0055w00000AjVBKAA3");
			Thread.sleep(10000);
		}
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();

		Thread.sleep(15000);
		int j = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int a = 0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				js1.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
				// System.out.println("clicked login");
				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Search...']")));
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@role='listbox']/search_lightning-instant-result-item/div/div[2]/span[@title='"+contractid+"']")).click();
	}
	
	
	public void Govindlogin() throws InterruptedException, IOException {
		
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			Thread.sleep(1000);
			// driver.navigate().refresh();
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
		String URL = driver.getCurrentUrl();
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + legaluserid);
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
			driver.get("https://neustar--ltnstage.lightning.force.com/" + legaluserid);
			Thread.sleep(10000);
		}
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();

		Thread.sleep(10000);
		int j = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int a = 0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
				// System.out.println("clicked login");
				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='setupGear']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='related_setup_app_home']")).click();
		
		// work around
        driver.get("https://neustardev2.service-now.com/login.do");
        driver.findElement(By.id("user_name")).sendKeys(legalid);
        driver.findElement(By.id("user_password")).sendKeys(legalpwd);
        driver.findElement(By.id("sysverb_login")).click();
       //work around
		
		
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i = s1.iterator();
		Thread.sleep(10000);
		while (i.hasNext()) {
			String ChildWindow = i.next();
			driver.switchTo().window(ChildWindow);
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='filter-box input']")).sendKeys("All Sites");
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a/*[contains(text(),'All Sites')]")));
		Thread.sleep(10000);
		int k = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int a = 0; a < k; a++) {
			driver.switchTo().frame(a);
			try {

				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@class='net-url']")));

				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		Set<String> s2 = driver.getWindowHandles();
		Iterator<String> i1 = s2.iterator();
		Thread.sleep(10000);
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			driver.switchTo().window(ChildWindow);
		}
		Thread.sleep(5000);

	//	Thread.sleep(3000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//lightning-breadcrumb/a[contains(text(),'Home')]")));
		try {
			driver.findElement(By.id("username")).sendKeys(legalid);
			driver.findElement(By.id("password")).sendKeys(legalpwd);
			driver.findElement(By.xpath("//*[@name='login']")).click();
			
		} catch (Exception e) {

		}
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//h2[@class='ng-binding'][contains(text(),'Document Search')]")));
		Thread.sleep(5000);

		String docsearchurl = driver.getCurrentUrl();
		if (!docsearchurl.contains("my-contracts")) {
			System.out.println("Page is not navigated to Document search");
			Assert.assertTrue(false);
		}
		
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(contractid);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		String idlink = driver
				.findElement(By.xpath("//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a"))
				.getAttribute("title");
		Thread.sleep(5000);
		if (idlink.equals(contractid))
			driver.findElement(By.xpath("//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a")).click();
		else
			Assert.assertTrue(false);                                                                                                                                                                                                                                                                          
		Thread.sleep(3000);
	}
	public void geterrormessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		String msgct = msg.getText();
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		//if (sm.getText().contains("Error"))
			System.out.println(msgct);
	
	}
	
	
}

