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

public class amendmentSubmitForSignature extends Base {

	@Test
	public void amendmentSubmitForSignature() throws IOException, InterruptedException {

		driver = initialiseDriver();

		contractPage c = new contractPage(driver);
		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get(amendurl);
		Thread.sleep(15000);
//		js.executeScript("window.scrollBy(0,1300)", "");
		Actions act = new Actions(driver);
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

			driver.findElement(By
					.xpath("//*[text() = 'Billing Contact']//parent::lightning-grouped-combobox//following-sibling::div/div/lightning-base-combobox/div/div/ul/li/lightning-base-combobox-item/span/span/lightning-base-combobox-formatted-text[@title='"
							+ primarycontact + "']"))
					.click();
		}
		driver.findElement(By.xpath("//button[text() = 'Save']")).click();
		Thread.sleep(20000);
		driver.navigate().refresh();
		Thread.sleep(15000);

		String type = driver
				.findElement(By
						.xpath("(//span[@class='test-id__field-label'][contains(text(),'Opportunity Type')])//parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text"))
				.getText();
		System.out.println(type);

		if (type.equals("Upgrade") || type.equals("Renewal")) {
			Thread.sleep(5000);
			SubmitforSignature();

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

				SubmitforSignature();

				Thread.sleep(5000);
				OpsReview();
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}

		String status = op.getAmendcontractstatus().getText();
		System.out.println(status);
		Assert.assertEquals(op.getAmendcontractstatus().getText(), "Submission Under Review");

		Thread.sleep(5000);
		if (driver.findElement(By.xpath("//*[@data-name='6 - Ops Review']")).getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag
																		// name
																		// changed
		Thread.sleep(10000);

		// Cases
		js.executeScript("window.scrollBy(0,150)", "");

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
					By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Contract Submission')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit For Signature is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			driver.findElement(By
					.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Contract Submission')]/parent::span/parent::td/preceding-sibling::th/span/a"))
					.click();
			Thread.sleep(10000);
			System.out.println(c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			driver.navigate().back();
		} catch (Exception e) {

			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}

		driver.navigate().back();
		Thread.sleep(10000);

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
		driver.findElement(By
				.xpath("//label[contains(text(),'How did Neustar Improve their Process')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		driver.findElement(
				By.xpath("//label[contains(text(),'Solution Description')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		driver.findElement(
				By.xpath("//label[contains(text(),'Customer Current Process')]//following-sibling::div/textarea"))
				.sendKeys("Test");
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//input")));
		driver.findElement(By
				.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//div/div[2]/lightning-base-combobox-item[@data-value='"
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
								 * xpath("//div[@class='toastTitle slds-text-heading--small']"
								 * )) .getText() + System.lineSeparator() +
								 */driver
					.findElement(
							By.xpath("//div[@class='toastTitle slds-text-heading--small']//following-sibling::span"))
					.getText();
			System.out.println(ErrorMsg_2);
			Assert.assertEquals(ErrorMsg_2,
					"Competitor , Solution Description , Customer Current Process , Why Neustar Won , How did Neustar Improve their Process.");
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//button[@title='Close this window']")));
			Thread.sleep(5000);
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

	public void SubmitforSignature() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
	}

}
