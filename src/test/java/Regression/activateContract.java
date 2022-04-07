package Regression;

import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

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

public class activateContract extends Base {

	public String type;

	// Attention required
	@Test(priority = 1)
	public void attentionrequired() throws IOException, InterruptedException {
		driver = initialiseDriver();
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lp.getcontracts());
		Thread.sleep(5000);
		lp.getcontracttxtbox().sendKeys(contractid);
		lp.getcontracttxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		// String contractid = prop.getProperty("contractid");
		driver.findElement(By.xpath("//*[@title='" + contractid + "']")).click();
		Thread.sleep(10000);
		type = driver.findElement(By.xpath(
				"//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span"))
				.getText();
		System.out.println(type);
		// Contract can't be sent for eSignature after Submit for Signature
		if (cp.Contract_Status().getText().equalsIgnoreCase("Submission Under Review")) {
			cp.getdropdown().click();
			js.executeScript("arguments[0].click();", cp.getesign());
			Thread.sleep(5000);
			try {
				waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")));
				System.out.print("Error message for eSignature : ");
				System.out.println(
						driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")).getText());
				
				driver.findElement(By.xpath(
						"//button[@class='slds-button slds-button_icon toastClose slds-notify__close slds-button--icon-inverse slds-button_icon-bare']"))
						.click();
			} catch (Exception e) {
				Assert.assertTrue(false);
			}
		}

		((JavascriptExecutor) driver).executeScript("scroll(0,100);");
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.doubleClick(cp.getstatus()).build().perform();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.select")));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", cp.getARdrop());

		cp.getARcomments().sendKeys(ARcomments);
		Thread.sleep(5000);
		try {
			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("(//*[@dir='ltr'][contains(text(),'Save')])[2]")));
		} catch (Exception e) {

			js.executeScript("arguments[0].click();", cp.geteditsave());
		}
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);

		String AR = cp.getARchev().getAttribute("aria-checked");
		if (AR.equals("true")) {
			System.out.println("Contract stage is moved to Attention Required");
		} else
			System.out.println("Contract stage is not moved to Attention Required");

	}

	// Awaiting activation
	@Test(priority = 2)
	public void awaitingactivation() throws IOException, InterruptedException {

		contractPage cp = new contractPage(driver);
		((JavascriptExecutor) driver).executeScript("scroll(0,100);");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		action.doubleClick(cp.getstatus()).build().perform();
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.select")));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", cp.getAAdrop());
		js.executeScript("arguments[0].click();", cp.geteditsave());
		Thread.sleep(10000);
		for (int m = 0; m <= 1; m++) {
			driver.navigate().refresh();
			Thread.sleep(10000);

		}

		String AA = cp.getAAchev().getAttribute("aria-checked");
		if (AA.equals("true")) {
			System.out.println("Contract stage is moved to Awaiting Activation");
		} else
			System.out.println("Contract stage is not moved to Awaiting Activation");
	}

	// contract activation
	@Test(priority = 3)
	public void contractactivation() throws IOException, InterruptedException {

		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Contract can't be sent for Submit Edit/Submit for Signature when Contract is
		// in Awaiting Activation
		if (cp.Contract_Status().getText().equalsIgnoreCase("Awaiting Activation")) {
			cp.getdropdown().click();
			js.executeScript("arguments[0].click();", cp.SubmitEdit());
			cp.SubmitEditDetails().sendKeys(SubmitEditDetails);
			Thread.sleep(5000);
			cp.Submit_SubmitEdit().click();
			try {
				waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")));
				System.out.print("Error message for Submit Edit : ");
				System.out
						.println(driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")).getText());
				driver.findElement(By.xpath(
						"//button[@class='slds-button slds-button_icon slds-modal__close closeIcon slds-button_icon-bare slds-button_icon-inverse']"))
						.click();
			} catch (Exception e) {
				Assert.assertTrue(false);
			}
		}
		Thread.sleep(5000);
		if (cp.Contract_Status().getText().equalsIgnoreCase("Awaiting Activation")) {
			cp.Dropdown().click();
			js.executeScript("arguments[0].click();", cp.SubmitForSignature());
			Thread.sleep(15000);
			try{
			driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
			driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
			driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@type='submit']")).click();
			try {
				waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")));
				System.out.print("Error message for Submit for Signature : ");
				System.out.println(
						driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")).getText());
				driver.findElement(By.xpath(
						"//button[@class='slds-button slds-button_icon slds-modal__close closeIcon slds-button_icon-bare slds-button_icon-inverse']"))
						.click();
			} 
			catch(Exception e){
				Assert.assertTrue(false);}
			}
			catch(Exception e){
				try{
				waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")));
				System.out.print("Error message for Submit for Signature : ");
				System.out.println(
						driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span")).getText());
				}
				catch(Exception e1){
					Assert.assertTrue(false);}
				}
			
		
		}
		Thread.sleep(5000);

		js.executeScript("arguments[0].click();", cp.getActivate());
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", cp.getActivatepopup());
		Thread.sleep(5000);
		// driver.navigate().refresh();
		Thread.sleep(10000);

		String act = cp.getActchev().getAttribute("aria-checked");
		if (act.equals("true")) {
			System.out.println("Contract is Activated");
		} else
			System.out.println("Contract is not Activated");
		// error validation in contract record

		if (type.equals("Service Order")) {
			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//*[@class='slds-form-element__control']/div/div/a")));
			Thread.sleep(10000);
			driver.navigate().refresh();
			Thread.sleep(10000);

			String chev = driver.findElement(By.xpath("//*[@title='7 - Won']")).getAttribute("aria-checked");
			if (chev.equals("true")) {
				System.out.println("Opportunity is moved to 7-won stage");
			} else
				System.out.println("Opportunity is not moved to 7-won stage");
			
			driver.navigate().back();
		
		Thread.sleep(10000);
		//Edit Activated Contract
//		driver.get(contracturl);
//		Thread.sleep(10000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		//login as admin
		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
			}
			catch(Exception e) {
				Thread.sleep(1000);
			}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		}
			catch(Exception e) { 
				Thread.sleep(1000);
		}
		String URL= driver.getCurrentUrl();
		if(URL.contains("uat"))
	    {
	    	//System.out.println(userid);
	    driver.get("https://neustar--uat.lightning.force.com/"+AEuserid);
	    Thread.sleep(10000);
	    }
	    else{
	    	//System.out.println(userid);
	    	driver.get("https://neustar--ltnstage.lightning.force.com/"+AEuserid);	
	    Thread.sleep(10000);
	    }
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();
		
		Thread.sleep(10000);
		int j=driver.findElements(By.xpath("//iframe")).size();
		//System.out.println(j);
	    for(int a=0; a<j; a++){
	    	driver.switchTo().frame(a);
		try{	
	    		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
	    		Thread.sleep(5000);
	    		}
	    	catch (Exception e){
	    		
	    		driver.switchTo().defaultContent();
	    	}
	    	
	    }
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.get(contracturl);
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//span[text()='Opportunity']/parent::div/div/div/div/a")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		driver.findElement(By.xpath("//*[text()='Quote']/parent::div/following-sibling::div/span/div/a")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String Contract = it.next();
		String Quote = it.next();
		String Opportunity = it.next();
		
		driver.switchTo().window(Quote);
		Thread.sleep(15000);
		createQuotePage qp= new createQuotePage(driver);
		qp.getdropdown().click();
		driver.findElement(By.xpath("//a[@name='Edit']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[text()='Subscription Term']/parent::lightning-input/div/input")).clear();
		driver.findElement(By.xpath("//label[text()='Subscription Term']/parent::lightning-input/div/input")).sendKeys("13");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		String error1 = driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println(driver.findElement(By.xpath("//div[@class='container']")).getText());
		Assert.assertEquals("Close error dialog\n"
				+ "We hit a snag.\n"
				+ "Review the errors on this page.\n"
				+ "Oops...you don't have the necessary privileges to edit this record. See your administrator for help.", error1);
		driver.findElement(By.xpath("//button[@title='Close this window']")).click();
		
		driver.switchTo().window(Opportunity);
		Thread.sleep(15000);
		js.executeScript("window.scrollBy(0,300)", "");
		driver.findElement(By.xpath("//span[text()='Opportunity Type']/parent::div/following-sibling::div/button")).click();
		js.executeScript("window.scrollBy(0,100)", "");
		Thread.sleep(5000);
		createOppPage cop = new createOppPage(driver);
		js.executeScript("arguments[0].click();", cop.getOppType());
		driver.findElement(By.xpath("//*[@title='"
									+ ChangeOppType
									+ "']")).click();;
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='container']")).getText());
		String error2 = driver.findElement(By.xpath("//div[@class='container']")).getText();
//		Assert.assertEquals("Close error dialog\n"
//				+ "We hit a snag.\n"
//				+ "Review the errors on this page.\n"
//				+ "You do not have the permissions to update this record anymore. Please reach out to Sales Operations for assistance", error2);
		driver.findElement(By.xpath("//button[@name='CancelEdit']")).click();
		
		driver.switchTo().window(Contract);
		cp.Dropdown().click();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title = 'Edit'] //*[@role='button']")));
		Thread.sleep(10000);
		js.executeScript("document.querySelector('div.actionBody').scrollTop=1200");
		driver.findElement(By.xpath("//span[text()='Term without Free Period']/parent::label/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[text()='Term without Free Period']/parent::label/following-sibling::input")).sendKeys("13");
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='desktop forcePageError']")).getText());
		String error3 = driver.findElement(By.xpath("//div[@class='desktop forcePageError']")).getText();
//		Assert.assertEquals("Review the errors on this page.\n"
//				+ "Cannot Update an Active Contract. Please reach out to the Sales Ops Team for Assistance", error3);
		driver.findElement(By.xpath("//button[@title='Close this window']")).click();
		}
		Thread.sleep(3000);
//		driver.quit();
//		driver.close();
	
	}
	
	@Test(dependsOnMethods={"contractactivation"})
	public void agreementstatus() throws IOException, InterruptedException {
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
		//Thread.sleep(4000);
		//((JavascriptExecutor) driver).executeScript("scroll(0,200);");
		driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		List<WebElement> agmnts = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/th/span/a[contains(@title,'" + contractid + "')]"));
		int count = agmnts.size();
		if (count >= 1) {
			for (int i=1; i <= count; i++) {
				String agreementstatus = driver.findElement(By.xpath("((//*[contains(text(),'Agreements')]//parent::div//parent::div//parent::div//parent::div//parent::div//parent::div)/following-sibling::div)[4]//tbody/tr["+i+"]/td[4]")).getText();
			
//				 String agreementstatus= driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[4]")).getText();
				if(agreementstatus.equals("Signed"))
					System.out.println("Agreement" + i + ":" + agreementstatus);
				else if(agreementstatus.equals("Cancelled / Declined")){
					System.out.println("Agreement" + i + ":" + agreementstatus);
					//Assert.assertTrue(false);
				}   
				else{
					System.out.println("Agreement" + i + ":" + agreementstatus);
					Assert.assertTrue(false);
				}
		}}
			else {
			System.out.println("No agreements found");
			Assert.assertTrue(false);
		}
	
	driver.navigate().back();
		
		driver.quit();
	}
	


}