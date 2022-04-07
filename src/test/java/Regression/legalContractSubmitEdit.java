package Regression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
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

public class legalContractSubmitEdit extends Base {

	@Test
	public void ContractSubmitEdit() throws IOException, InterruptedException {

		driver = initialiseDriver();
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
		
		legalportaluser();

		// Thread.sleep(3000);
		waitForWebElementPresent(cp.getRelated());
		String type = cp.gettype().getText();
		if(!type.equals("Procurement")){
			String status = cp.getStatus().getText();
			System.out.println("Contract Status Before Submit Edit is : "+status);
			if(upload.equals("no")){
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@title='Submit Edit']")));
		driver.findElement(By.xpath("//*[@title='Submit Edit']")).click();
		Thread.sleep(3000);
		cp.SubmitEditDetails().sendKeys(SubmitEditDetails);
		Thread.sleep(3000);
		cp.Submit_SubmitEdit().click();
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		Thread.sleep(5000);
		if(status.equals("Draft")){
			Thread.sleep(10000);
		for(int h=1; h<3; h++ ){
		driver.navigate().refresh();
		Thread.sleep(10000);
		if(!driver.findElement(By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span")).getText().equals("Draft"))
			break;
		}
		
		}
		String status1 = driver.findElement(By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span")).getText();
//		System.out.println(status1);
		Assert.assertEquals(status1, "Edit Under Review", "Contract Status after Submit Edit is : "+status1);
		System.out.println("Contract Status after Submit Edit is : "+status1);
			}
		cp.getRelated().click();
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
					System.out.println("Approval Record is assigned to :" +driver
							.findElement(By.xpath("//th[@scope='row']//following-sibling::td[2]/span")).getText());
				}
			} else if (a > 1) {
				System.out.println(a + "Approval records are created");
				Assert.assertTrue(false);
			}

			else {
				System.out.println("Approval record is not created");
				Assert.assertTrue(false);
			}
			
			driver.findElement(By.xpath("//*[@title='Submit Edit']")).click();

			cp.SubmitEditDetails().sendKeys(SubmitEditDetails);
			Thread.sleep(3000);
			cp.Submit_SubmitEdit().click();
			Thread.sleep(2000);
			WebElement msg1= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			waitForWebElementPresent(msg1);
			System.out.println("Message when trying to do SubmitEdit without Approving record: "+msg1.getText());
		//	driver.quit();
			approve();
			legalportaluser();
			contractPage cp2 = new contractPage(driver);
			driver.findElement(By.xpath("//*[@title='Submit Edit']")).click();

			cp2.SubmitEditDetails().sendKeys(SubmitEditDetails);
			Thread.sleep(3000);
			cp2.Submit_SubmitEdit().click();
			Thread.sleep(5000);
			
			driver.navigate().refresh();
			Thread.sleep(10000);
			cp2.getDetail().click();
			Thread.sleep(10000);
			String status2 = driver.findElement(By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span")).getText();
			Assert.assertEquals(status2, "Edit Under Review", "Contract Status after 2nd Submit Edit is : "+status2);
			System.out.println("Contract Status after 2nd Submit Edit is : "+status2);

			cp2.getRelated().click();
			Thread.sleep(10000);
			
			String appidnew= driver.findElement(By.xpath("//th/div/a")).getText();
			System.out.println("New Approval Record is :" +appidnew);
			System.out.println("New Approval Record Status is: "+ driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText());
			Assert.assertEquals(driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText(), "Requested", "Approval record status is : "+driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText());
			Assert.assertNotEquals(appid, appidnew, "New Approval record is not created");
			System.out.println("New Approval Record is assigned to : "+driver
					.findElement(By.xpath("//th[@scope='row']//following-sibling::td[2]/span")).getText());
			
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
			//driver.quit();
			approve();
			legalportaluser();
			contractPage cp1 = new contractPage(driver);
			String contractstatus=driver.findElement(By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span")).getText();
			Assert.assertEquals(contractstatus, "Edit Complete", "Contract status is : "+contractstatus);
			cp1.getRelated().click();
			Thread.sleep(10000);
			System.out.println("Approval Record:" + driver.findElement(By.xpath("//th/div/a")).getText());
			System.out.println("Approval Record is assigned to :" +driver
					.findElement(By.xpath("//th[@scope='row']//following-sibling::td[2]/span")).getText());
			String stat= driver.findElement(By.xpath("//th[@scope='row']//following-sibling::td[1]/span")).getText();
			Assert.assertEquals(stat, "Approved", "Approval record status is : "+stat);
			System.out.println("Approval Record Status after approving record is: "+stat );
			
		}
		else
			System.out.println("Type of contract is: "+type);
		
		driver.quit();
	

}
	
	public void approve() throws IOException, InterruptedException{
		
		driver = initialiseDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		landingPage lp= new landingPage(driver);
		contractPage cp = new contractPage(driver);
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
/*		js.executeScript("arguments[0].click();", lp.getcontracts());
		Thread.sleep(5000);
		lp.getcontracttxtbox().sendKeys(contractid);
		lp.getcontracttxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		// String contractid = prop.getProperty("contractid");
		driver.findElement(By.xpath("//*[@title='" + contractid + "']")).click();
		*/
		// driver.get("https://neustar--ltnstage.lightning.force.com/lightning/r/Contract/8006s0000005Dg0AAE/view");
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Search...']")));
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@role='listbox']/search_lightning-instant-result-item/div/div[2]/span[@title='"+contractid+"']")).click();

		Thread.sleep(5000);
		cp.getRelated().click();
		Thread.sleep(10000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//*[@title='Approvals']"))).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//th/div/a[starts-with(text(),'A-')]")).click();
		Thread.sleep(5000);
		cp.Approve_Button().click();
		Thread.sleep(20000);
		int x = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(x);
		for (int j = 0; j < x; j++) {
			driver.switchTo().frame(j);

			try {
				cp.Approve_Text().sendKeys(ApprovalComments);
				cp.Approval_Submit().click();
			}

			catch (Exception e) {
				driver.switchTo().defaultContent();
			}
			driver.switchTo().defaultContent();
		}
		Thread.sleep(10000);	
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		
	}
	
	public void legalportaluser() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
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

		Thread.sleep(20000);
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
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='setupGear']")).click();
		Thread.sleep(5000);
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
		Thread.sleep(5000);
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
		
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//lightning-breadcrumb/a[contains(text(),'Home')]")));
		try {
			driver.findElement(By.id("username")).sendKeys(legalid);
			driver.findElement(By.id("password")).sendKeys(legalpwd);
			driver.findElement(By.xpath("//*[@name='login']")).click();
			Thread.sleep(2000);
			waitForWebElementPresent(driver.findElement(By.xpath("//*[@alt='Portal Logo']")));
			
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
		System.out.println("Contract: "+contractid);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(Keys.ENTER);

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a[@title='"+contractid+"']")));

		Thread.sleep(5000);
		
	}
}