package Regression;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

public class createContractBeforeQuoteApproval extends Base {
	@Test
	public void createContract() throws IOException, InterruptedException {

		createOpportunity co = new createOpportunity();
		createQuoteandConfigure cq = new createQuoteandConfigure();
		// co.createOpportunity();
		// cq.createQuote();
		// cq.configureQuote();
		/*
		 * driver.close(); // cq.approveQuote();
		 */
		driver = initialiseDriver();
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		createQuotePage cqp = new createQuotePage(driver);

		js.executeScript("arguments[0].click();", lp.getQuote());
		Thread.sleep(5000);
		lp.getquotetxtbox().sendKeys(quote);
		lp.getquotetxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@title='" + quote + "']")).click();
		Thread.sleep(10000);
//		Actions a= new Actions(driver);
//		a.moveToElement(cqp.getrequireapproval()).build().perform();
		js.executeScript("window.scrollBy(0,1800)");
		Thread.sleep(10000);
			WebElement checkbox= cqp.getrequireapproval();
			String display;
			display = js.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('display');",checkbox).toString();
//			System.out.println(display);
			
		if (display.contains("block")) {
			System.out.println("Requires Approval checkbox is checked");
		} else
			System.out.println("Requires Approval checkbox is not checked");
		driver.findElement(By.xpath("//*[@slot='primaryField']//div/a/slot/slot/span")).click();
		// driver.findElement(By.xpath("//*[@title='Edit Primary
		// Quote']/preceding-sibling::span//div/a")).click();
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", op.getselection());
		js.executeScript("arguments[0].click();", op.getmark());
		Thread.sleep(3000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")));
		String err2 = driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")).getText();
		if (!err2.contains("success")) {
			System.out.println(
					"Error displayed while trying to move to Selection stage without filling competitor field:-");
			System.out.println(err2);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='Close']")));
			// code for filling competitor field
			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//button[@title='Edit Why Neustar Won']")));
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();", driver
					.findElement(By.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//input")));
			driver.findElement(By
					.xpath("//label[contains(text(),'Competitor')]//following-sibling::div//div/div[2]/lightning-base-combobox-item[@data-value='"
							+ competitor + "']"))
					.click();
			if (competitor.equals("Other"))
				driver.findElement(
						By.xpath("//label[contains(text(),'Competitor (If Other)')]//following-sibling::div/input"))
						.sendKeys("Test");
			op.getSaveedit().click();
			Thread.sleep(10000);

			js.executeScript("arguments[0].click();", op.getselection());
			js.executeScript("arguments[0].click();", op.getmark());
			Thread.sleep(3000);

		}


		js.executeScript("arguments[0].click();", op.getcontract());
		Thread.sleep(10000);

		js.executeScript("arguments[0].click();", op.getcontractsave());
		try {
			WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			waitForWebElementPresent(msg);
			// WebElement compmsg=
			// driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
			System.out.println(msg.getText());
			// System.out.println(compmsg.getText());
			js.executeScript("arguments[0].click();", op.getcontracting());
			js.executeScript("arguments[0].click();", op.getmark());
			Thread.sleep(10000);
			driver.navigate().refresh();
			Thread.sleep(20000);
			try {

				js.executeScript("arguments[0].click();", op.getcontractlink());
			} catch (Exception e) {
				js.executeScript("arguments[0].click();", op.getcontractlinkuat());
			}

		} catch (Exception e) {
			WebElement msg = driver.findElement(By.xpath("//*[@class='pageLevelErrors']"));
			waitForWebElementPresent(msg);
			WebElement errormsg = driver.findElement(By.xpath("//*[@class='pageLevelErrors']//li"));
			// System.out.println(msg.getText());
			System.out.println(errormsg.getText());
			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//*[@class='slds-modal__footer']//*[contains(text(),'Cancel')]")));
			Thread.sleep(5000);
			js.executeScript("window.scrollBy(0,1400)");
			Thread.sleep(5000);
		try{
			driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span")).click();
		}
		catch(Exception e3){
			driver.findElement(By.xpath("(//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span)[2]")).click();
		}
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		waitForWebElementPresent(cqp.getdropdown());
			cqp.getdropdown().click();
	        Thread.sleep(5000);
	        try{
	        js.executeScript("arguments[0].click();", cqp.getsubmitapproval());
	        Thread.sleep(5000);
	        }
	        catch(Exception e2){
	        	js.executeScript("arguments[0].click();", cqp.getsubmitapprovaluat());
	        Thread.sleep(5000);
	        }
	        Thread.sleep(10000);
	        String app = cqp.getapproved().getAttribute("aria-checked");
	        if (app.equals("true")){
	        	System.out.println("Quote is approved");
	        }
	        else {
	        	System.out.println("Quote is not approved");
	        Assert.assertTrue(false);
	        }
	        driver.findElement(By.xpath("//*[@slot='primaryField']//div/a/slot/slot/span")).click();
	        Thread.sleep(10000);
			js.executeScript("arguments[0].click();", op.getcontract());
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", op.getcontractsave());
			js.executeScript("arguments[0].click();", op.getcontracting());
			js.executeScript("arguments[0].click();", op.getmark());
			Thread.sleep(10000);
			driver.navigate().refresh();
			Thread.sleep(20000);
			WebElement contracted=driver.findElement(By.xpath("//*[@name='SBQQ__Contracted__c']/following-sibling::label/span[@class='slds-checkbox_faux']"));
			String display1;
			display1 = js.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('display');",contracted).toString();
			if (display1.contains("block")) {
				System.out.println("Contracted checkbox is checked");
			} else
				System.out.println("Contracted checkbox is not checked");
			driver.navigate().refresh();
			Thread.sleep(20000);
			try {
				js.executeScript("arguments[0].click();", op.getcontractlink());
			} catch (Exception e1) {
				js.executeScript("arguments[0].click();", op.getcontractlinkuat());
			}
		}
		Thread.sleep(5000);

		String stat = driver.findElement(By.xpath("//*[@title='Status']/following-sibling::div")).getText();
		System.out.println("Contract Status is:" + stat);
		// to be sent to data.prop file
		String curl = driver.getCurrentUrl();
		String id = driver
				.findElement(By
						.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		System.out.println("Contractid is:" + id);
		System.out.println("Contracturl is:" + curl);

		driver.close();

	}
}