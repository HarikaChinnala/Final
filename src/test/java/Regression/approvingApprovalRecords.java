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

public class approvingApprovalRecords extends Base {
	public String type;
	
	@Test(priority = 1)
	public void approvingApprovalRecords() throws IOException, InterruptedException {
		
		driver = initialiseDriver();
		contractPage c = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(contracturl);
		
		try {
			c.Related_Tab().click();
			c.Cases_Section().click();
			Thread.sleep(5000);

			List<WebElement> Cases_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Edit Request')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit for Signature is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} 
			else {
				Assert.assertFalse(true);
			}
			Thread.sleep(10000);
			c.Case_SubmitEdit().click();
			Thread.sleep(10000);
			System.out.println("Case Owner: " + c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			System.out.println("Case status is: " + driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText());
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='" + pendingcasestatus + "']")));
			Thread.sleep(2000);
			System.out.println("Selected Case Status: " + pendingcasestatus);
			
			//driver.navigate().back();
		} 
		catch (Exception e) {
			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}
		

		// logout from existing user
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		
		// login as admin
		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} 
		catch (Exception e) {
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} 
		catch (Exception e) {
			Thread.sleep(1000);
		}
		
		driver.get(contracturl);
		Thread.sleep(10000);
		
		c.Related_Tab().click();
		c.Approvals_Section().click();

		// Approving the Approval Records
		try {
			driver.navigate().refresh();
			List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
			int Approval_Count = Approval_List.size();
			System.out.println("Number of Approval records = " + Approval_Count);
			for (int i = 1; i <= Approval_Count; i++) {
				if(Approval_Count==2) {
				
					//a.approving();
					
					c.Approvals_Requested().click();
					Thread.sleep(5000);
				
					WebElement Rule = driver.findElement(By.xpath("//*[text()='Rule']/parent::div/following-sibling::div/span/slot/slot/force-lookup/div/force-hoverable-link/div/a[@class='flex-wrap-ie11']/span"));
					if(Rule.getText().contains("Legal"))
					{
						System.out.println(Rule.getText());
						
						try {
							c.Approve_Button().click();
							Thread.sleep(2000);
							System.out.println(driver.findElement(By.xpath("//*[@class='toastMessage forceActionsText']")).getText());
						}
						catch(Exception e){
							System.out.println("User able to Approve even without DGQ");
							Assert.assertTrue(false);
						}
						
						
						driver.findElement(By.xpath("//a[text()='Related']")).click();
						Thread.sleep(5000);
						driver.findElement(By.xpath("//span[contains(text(),'Data')]/parent::a/parent::h2/parent::div/parent::div/parent::div/following-sibling::div[2]")).click();
						driver.findElement(By.xpath("//*[@placeholder='Search Contracts...']")).sendKeys(contractid);
						driver.findElement(By.xpath("//label[contains(text(),'Contract')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
								+ contractid
								+ "']")).click();
						driver.findElement(By.xpath("//*[text()='Paper Source']/following-sibling::div")).click();
						driver.findElement(By.xpath("//*[@title='Third-Party Paper']")).click();
						driver.findElement(By.xpath("//*[@name='SaveEdit']")).click();
						Thread.sleep(10000);
						
						
						driver.navigate().refresh();
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
						Thread.sleep(10000);
						c.Related_Tab().click();
						c.Cases_Section().click();
						Thread.sleep(5000);
						c.Case_SubmitEdit().click();
						Thread.sleep(10000);
						System.out.println("Case Owner: " + c.Case_Owner().getText());
						Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
						String cs = driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
						System.out.println("Case status is: " + cs);

						if (pendingcasestatus.contains("Pending")) {
							if (cs.equals("Unresponded/New"))
								System.out.println("Case status successfully moved to Unresponded/New");
							else
								Assert.assertTrue(false);
						} 
						else if (pendingcasestatus.contains("Closed")) {
							if (cs.equals("Unresponded/New"))
								System.out.println("Case status successfully moved to Unresponded/New");
							else
								Assert.assertTrue(false);
						}

						else if (pendingcasestatus.equals("Escalated")) {
							if (cs.equals("Escalated"))
								System.out.println("Case status is not changed from Escalated");
							else
								Assert.assertTrue(false);
						} 
						else
							Assert.assertTrue(false);
						driver.get(contracturl);
						c.Related_Tab().click();
						c.Approvals_Section().click();
					}
					else {
						driver.navigate().refresh();
						Thread.sleep(20000);
					
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
						Thread.sleep(10000);
						
					}
				}
				else {
					Assert.assertTrue(false);
				}
			}
		} 
		catch(Exception e) {
			Assert.assertTrue(false);
		}
		Thread.sleep(5000);
		// user login
		String URL = driver.getCurrentUrl();
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + userid1);
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
			driver.get("https://neustar--ltnstage.lightning.force.com/" + userid1);
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
		// contracturl=prop.getProperty("contracturl");
		driver.get(contracturl);

		for (int i = 1; i <= 5; i++) {
			if (c.Details_Status().getText() != "Edit Under Review"
					&& c.Contract_Status().getText() != "Edit Under Review") {
				System.out.println(i);
				break;
			} else {
				driver.navigate().refresh();
				Thread.sleep(10000);
			}
		}

		Thread.sleep(10000);
		// To Check the status of the Chevron
		if (c.Chevron_EditComplete.getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// Details_Status
		System.out.println("Contract Status after Approving the Approval records in Details Section = "
				+ c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Edit Complete");

		// Contract_Status
		System.out.println("Contract Status after Approving the Approval records = " + c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Edit Complete");
		
		driver.close();
	}

}