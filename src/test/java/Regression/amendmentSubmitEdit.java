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

public class amendmentSubmitEdit extends Base {

	@Test(priority = 1)
	public void amendmentSubmitEdit() throws IOException, InterruptedException {

		driver = initialiseDriver();
		contractPage c = new contractPage(driver);
		driver.get(amendurl);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(20000);

		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click(); // Changed
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text() = 'Submit Edit']")));
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("Check and Approve");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='slds-card__footer']/button[@type='submit']")).click();
		Thread.sleep(10000);

		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
		Thread.sleep(10000);

		// Cases
		js.executeScript("window.scrollBy(0,150)", "");
		Actions act = new Actions(driver);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();

		Thread.sleep(10000);
		driver.navigate().refresh();
		try {
			List<WebElement> Cases_List = driver.findElements(
					By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit Edit is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			driver.findElement(By
					.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]/parent::span/parent::td/preceding-sibling::th/span/a"))
					.click();
			Thread.sleep(20000);
			System.out.println(c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			driver.navigate().back();
		} catch (Exception e) {

			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}

		driver.navigate().back();
		Thread.sleep(10000);

		oppPage op = new oppPage(driver);
		String status = op.getAmendcontractstatus().getText();
		System.out.println(status);
		Assert.assertEquals(op.getAmendcontractstatus().getText(), "Edit Under Review");

		// logout fron existing user
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
		// contractPage c= new contractPage(driver);
		driver.get(amendurl);
		Thread.sleep(5000);
		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
		Thread.sleep(10000);

		js.executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();

		Thread.sleep(10000);

		// Approving the Approval Records
		try {
			driver.navigate().refresh();
			List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
			int Approval_Count = Approval_List.size();
			System.out.println("Number of Approval records = " + Approval_Count);
			for (int i = 1; i <= Approval_Count; i++) {

				Thread.sleep(10000);
				c.Approvals_Requested().click();
				Thread.sleep(5000);
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
				Thread.sleep(20000);
				try {
					driver.findElement(By.xpath("//*[text()='Related']")).click();
				}
				catch(Exception e) {
				    js.executeScript("window.scrollBy(0,100)", "");
				    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
				}
				Thread.sleep(10000);
				js.executeScript("window.scrollBy(0,150)", "");
				Thread.sleep(10000);
				driver.findElement(
						By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
						.click();
				act.moveByOffset(10, 0).build().perform();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();
				Thread.sleep(10000);
			}

		} catch (Exception e) {

			System.out.println("No Approval Records");
			Assert.assertFalse(true);

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
		Thread.sleep(5000);
		// contracturl=prop.getProperty("contracturl");
		driver.get(amendurl);
		Thread.sleep(10000);

		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
		Thread.sleep(10000);

		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();

		Thread.sleep(10000);
		driver.navigate().refresh();
		List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
		int Approval_Count = Approval_List.size();
		System.out.println("Number of Approval records = " + Approval_Count);
		List<WebElement> Approved = driver
				.findElements(By.xpath("//*[@class='slds-truncate'][contains(text(),'Approved')]"));
		int Approved_List = Approved.size();
		System.out.println("Number of Approval records Approved = " + Approved_List);
		if (Approval_Count == Approved_List) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(10000);

		String status1 = op.getAmendcontractstatus().getText();
		System.out.println(status1);
		Assert.assertEquals(op.getAmendcontractstatus().getText(), "Edit Complete");

		driver.close();

	}

}
