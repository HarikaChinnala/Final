package Regression;

import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

public class winFlash_Opportunity extends Base {

	@Test
	public void winFlash_Opportunity() throws IOException, InterruptedException {

		driver = initialiseDriver();
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", lp.getOpportunities());
		Thread.sleep(5000);
		lp.getopptxtbox().sendKeys(opportunityname);
		lp.getopptxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		// lp.getsrchresult().click();
		// String OppName = prop.getProperty("OppName");
		driver.findElement(By.xpath("//*[@title='" + opportunityname + "']")).click();

		Thread.sleep(10000);

        driver.navigate().refresh();
        Thread.sleep(20000);

        Actions act = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath(
                "((//*[text() = 'Billing Contact'])//parent::div)//following-sibling::div/span[@class='test-id__field-value slds-form-element__static slds-grow word-break-ie11']"));
        act.doubleClick(ele).perform();
        Thread.sleep(5000);
        driver.findElement(By.xpath(
                "//*[text() = 'Billing Contact']//parent::lightning-grouped-combobox//following-sibling::div/div/lightning-base-combobox/div/div/input"))
                .sendKeys(primarycontact);
        Thread.sleep(5000);
       // String primarycontact = prop.getProperty("primarycontact");
        driver.findElement(By.xpath(
                "//*[text() = 'Billing Contact']//parent::lightning-grouped-combobox//following-sibling::div/div/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span/span/lightning-base-combobox-formatted-text[@title='"
                        + primarycontact + "']"))
                .click();
        driver.findElement(By.xpath("//button[text() = 'Save']")).click();
        Thread.sleep(15000);
        driver.navigate().refresh();
        Thread.sleep(15000);
		String type = driver.findElement(By.xpath(
				"(//span[@class='test-id__field-label'][contains(text(),'Opportunity Type')])//parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text"))
				.getText();
		System.out.println(type);

		if (type.equals("New Service") || type.equals("New Logo")) {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[text()='Show more actions']//parent::button")).click();

			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//span[text() = 'Submit For Signature']")));

			Thread.sleep(10000);
			// driver.findElement(By.xpath("(//*[@class='select'])[1]")).click()0
			driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
			driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
			Thread.sleep(5000);
			// driver.findElement(By.xpath("(//*[@class='select'])[2]")).click();
			driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
			driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@type='submit']")).click();
			Thread.sleep(2000);
			String ErrorMsg_1 = /*
								 * driver.findElement(By.
								 * xpath("//div[@class='toastTitle slds-text-heading--small']")) .getText() +
								 * System.lineSeparator() +
								 */ driver
					.findElement(
							By.xpath("//div[@class='toastTitle slds-text-heading--small']//following-sibling::span"))
					.getText();
			System.out.println(ErrorMsg_1);
			Assert.assertEquals(ErrorMsg_1, "Opportunity Type: New service/ New logo can not submit");

			Thread.sleep(5000);

			driver.findElement(By.xpath("//button[@title='Close this window']")).click();
			Thread.sleep(5000);

			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='6 - Ops Review']")));
			js.executeScript("arguments[0].click();", op.getmark());

			if (sellinglane.equals("Communications")) {
				String acvtotal = op.getacvtotal().getText().substring(4);
				String acvt = acvtotal.replace(",", "");
				Double at = Double.parseDouble(acvt);
				if (at >= 50000) {
					error_2();
					userprop();
				} else {
					OpsReview();
				}
			} else if (sellinglane.equals("Security")) {
				String acvcommited = op.getacvcommitted().getText().substring(4);
				String acv = acvcommited.replace(",", "");
				Double acvcc = Double.parseDouble(acv);
				if (acvcc >= 25000) {
					error_2();
					userprop();
				} else {
					OpsReview();
				}
			} else {
				String acvcommit = op.getacvcommitted().getText().substring(4);
				String acvc = acvcommit.replace(",", "");
				Double ac = Double.parseDouble(acvc);
				if (ac >= 50000) {
					error_2();
					userprop();
				} else {
					OpsReview();
				}
			}

			try {
				op.getSaveedit().click();
				Thread.sleep(5000);

				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='6 - Ops Review']")));
				js.executeScript("arguments[0].click();", op.getmark());
				Thread.sleep(5000);
				OpsReview();
			} catch (Exception e) {
				Thread.sleep(1000);
				Assert.assertTrue(false);
			}
		}
		driver.close();
	}

	public void userprop() throws InterruptedException {
		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//button[@title='Edit Why Neustar Won']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[contains(text(),'Why Neustar Won')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		driver.findElement(By.xpath(
				"//label[contains(text(),'How did Neustar Improve their Process')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		driver.findElement(
				By.xpath("//label[contains(text(),'Solution Description')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		driver.findElement(
				By.xpath("//label[contains(text(),'Customer Current Process')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//input")));
		driver.findElement(By.xpath(
				"//label[contains(text(),'Competitor')]//following-sibling::div//div/div[2]/lightning-base-combobox-item[@data-value='"
						+ competitor + "']"))
				.click();

		if (competitor.equals("Other")) {
			op.getSaveedit().click();
			String ErrorMsg_Competitor = driver.findElement(By.xpath("//div[@role='dialog']")).getText()
					.split("dialog")[1].trim();
			System.out.println(ErrorMsg_Competitor);
			Thread.sleep(2000);
			Assert.assertEquals(ErrorMsg_Competitor, "We hit a snag.\n" + "Review the errors on this page.\n"
					+ "Competitor(If Other) field is mandatory when Other is selected in Competitor field.");
			Thread.sleep(5000);

			driver.findElement(
					By.xpath("//label[contains(text(),'Competitor (If Other)')]//following-sibling::div/input"))
					.sendKeys("Test");
		}
	}

	public void error_2() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Thread.sleep(5000);
		try {
			String ErrorMsg_2 = /*
								 * driver.findElement(By.
								 * xpath("//div[@class='toastTitle slds-text-heading--small']")) .getText() +
								 * System.lineSeparator() +
								 */driver
					.findElement(
							By.xpath("//div[@class='toastTitle slds-text-heading--small']//following-sibling::span"))
					.getText();
			System.out.println(ErrorMsg_2);
			Assert.assertEquals(ErrorMsg_2,
					"Missing Information: Please complete the following value proposition fields: Solution Description , Customer Current Process , Why Neustar Won , How did Neustar Improve their Process..");
			Thread.sleep(5000);
			//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Close']")));
			//Thread.sleep(5000);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	public void OpsReview() throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		String chev = driver.findElement(By.xpath("//*[@title='6 - Ops Review']")).getAttribute("aria-checked");
		if (chev.equals("true")) {
			System.out.println("Opportunity is moved to 6 - Ops Review");
		} else {
			System.out.println("Opportunity is not moved to 6 - Ops Review");
			Assert.assertTrue(false);
		}
	}

}
