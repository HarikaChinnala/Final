package Regression;

import java.util.List;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

//import javax.swing.text.html.HTMLDocument.Iterator;

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
import java.util.Iterator;

public class multipleeSignatureAmendment extends Base {
	reusablemethods rm = new reusablemethods(driver);

	@Test
	public void eSignature() throws IOException, InterruptedException {

		driver = initialiseDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage cp = new contractPage(driver);
		oppPage op = new oppPage(driver);
		Thread.sleep(5000);
		driver.get(amendurl);

		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,1500);");
		Thread.sleep(10000);

		WebElement upload = driver
				.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));

		if (upload.isDisplayed()) {
			System.out.println("File is not attached");

			js.executeScript("arguments[0].click();", op.getGenamend());
			Thread.sleep(80000);
			rm.generatedoc(driver);
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", cp.getRelated());
			Thread.sleep(10000);
			((JavascriptExecutor) driver).executeScript("scroll(0,800);");
			Thread.sleep(10000);
			String file = cp.getFiles().getAttribute("title");
			System.out.println(file);
			System.out.println("File is attached");

		} else {
			String file = op.getFiles().getAttribute("title");
			System.out.println(file);
			/*
			 * if(file.endsWith("- " + contractid + ".docx"))
			 */

			System.out.println("File is attached");
		}
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
			driver.get("https://neustar--uat.lightning.force.com/" + userid);
			Thread.sleep(10000);
		} else {
			driver.get("https://neustar--ltnstage.lightning.force.com/" + userid);
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

		esign();
		driver.get(amendurl);
		agreementview();

		Thread.sleep(10000);

		esign();
		driver.get(amendurl);
		// loop to compensate manual interruption for signing document
		for (int m = 0; m < 5; m++) {
			driver.navigate().refresh();
			/*
			 * String stat= driver.findElement(By.xpath(
			 * "//*[@title='Status']/following-sibling::div")).getText();
			 * if(stat.equals("Client Signed")) break;
			 */
			Thread.sleep(20000);

		}
		js.executeScript("arguments[0].click();", op.getRelated());
		try {
			Actions a = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("scroll(0,1750);");
			a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
			driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
			} catch (Exception e1) {
				((JavascriptExecutor) driver).executeScript("scroll(0,500);");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
			}
		}
		Thread.sleep(5000);
		try {

			List<WebElement> agmnts = driver.findElements(
					By.xpath("//table[@role='grid']/tbody/tr/th/span/a[contains(@title,'" + contractid + "')]"));
			int count = agmnts.size();
			System.out.println(count);
			int inc = 0;
			if (count >= 1) {
				for (int i = 1; i <= count; i++) {
					String agreementstatus = driver.findElement(By
							.xpath("((//*[contains(text(),'Agreements')]//parent::div//parent::div//parent::div//parent::div//parent::div//parent::div)/following-sibling::div)[4]//tbody/tr["
									+ i + "]/td[5]"))
							.getText();
					if (agreementstatus.equals("Signed")) {
						inc++;
						System.out.println("Agreement" + i + ":" + agreementstatus);
					} else {
						System.out.println("Agreement" + i + ":" + agreementstatus);
					}
				}
				if (inc >= 1)
					System.out.println("Agreement moved to Signed");
				else
					Assert.assertTrue(false);

			} else {
				System.out.println("No agreements found");
				Assert.assertTrue(false);
			}
		} catch (Exception exc) {
			Thread.sleep(1000);
		}
		driver.navigate().back();

		driver.close();
	}

	public void esign() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage cp = new contractPage(driver);
		oppPage op = new oppPage(driver);
		driver.get(amendurl);
		Thread.sleep(15000);
		op.getdropdown().click();
		Thread.sleep(2000);

		js.executeScript("arguments[0].click();", op.getesign());
		Thread.sleep(100000);
		String agreementname = op.getagreement().getText();
		System.out.println(agreementname);
		System.out.println(op.getagreementstat().getText());
		js.executeScript("arguments[0].click();", op.getNext());
		Thread.sleep(30000);
		System.out.println(driver.getCurrentUrl());
		// String parentwindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		Thread.sleep(10000);
		while (i1.hasNext()) {
			String Window1 = i1.next();
			String Window2 = i1.next();
			driver.switchTo().window(Window2);
			System.out.println("switched to childwindow");
			Thread.sleep(10000);
			System.out.println(driver.getCurrentUrl());
			List<WebElement> page = driver.findElements(By.xpath("//*[@id='document']/ul/Li"));
			int x = page.size();
			js.executeScript("arguments[0].click();",
					driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
			Thread.sleep(10000);
			try {
				waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(@id,'modal-title-view')]")));
				System.out.println(driver.findElement(By.xpath("//*[@class='modal-body']")).getText());
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//button[contains(text(),'Review')]")));
				System.out.println("Drag and drop Signature Fields");
				Thread.sleep(5000);
				WebElement signdrag = driver.findElement(By.xpath(
						"//*[@class='rhp-panel-list-item draggable-form-field field-signature ui-draggable-handle']"));
				WebElement inidrag = driver.findElement(By.xpath(
						"//*[@class='rhp-panel-list-item draggable-form-field field-initials ui-draggable-handle']"));
				WebElement signdrop = driver.findElement(By.xpath("//*[@id='document']/ul/Li[" + x + "]"));
				WebElement inidrop = driver.findElement(By.xpath("//*[@id='document']/ul/Li[" + x + "]"));
				Actions a = new Actions(driver);
				a.dragAndDrop(signdrag, signdrop).build().perform();
				Thread.sleep(5000);
				a.dragAndDrop(inidrag, inidrop).build().perform();
				js.executeScript("arguments[0].click();",
						driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
				Thread.sleep(10000);
			} catch (Exception e) {

			}
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='control-button']")));
			Thread.sleep(20000);
			driver.switchTo().window(Window1);
			Thread.sleep(5000);
		}
		System.out.println(op.getagreementstat().getText());

		Thread.sleep(5000);
	}

	public void agreementview() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage cp = new contractPage(driver);
		oppPage op = new oppPage(driver);
		Thread.sleep(10000);
		// String agreementname= cp.getagreement().getText();
		js.executeScript("arguments[0].click();", op.getRelated());
		Thread.sleep(10000);
		try {
			Actions a = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("scroll(0,1750);");
			a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
			driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
				} catch (Exception e1) {
				((JavascriptExecutor) driver).executeScript("scroll(0,500);");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
			}
		}

		Thread.sleep(5000);
		List<WebElement> agmnts = driver.findElements(
				By.xpath("//table[@role='grid']/tbody/tr/th/span/a[contains(@title,'" + contractid + "')]"));
		int count = agmnts.size();
		if (count >= 1) {
			for (int i = 1; i <= count; i++) {
				String agreementstatus = driver.findElement(By
						.xpath("((//*[contains(text(),'Agreements')]//parent::div//parent::div//parent::div//parent::div//parent::div//parent::div)/following-sibling::div)[4]//tbody/tr["
								+ i + "]/td[5]"))
						.getText();
				System.out.println("Agreement" + i + ":" + agreementstatus);
			}
		} else {
			System.out.println("No agreements found");
			Assert.assertTrue(false);
		}

		driver.navigate().back();

	}

}
