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

public class eSignatureLegalContract extends Base {
	private String type;
	private String agreementname;

	@Test
	public void eSignature() throws IOException, InterruptedException {

		driver = initialiseDriver();
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		landingPage lp = new landingPage(driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Search...']")));
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@role='listbox']/search_lightning-instant-result-item/div/div[2]/span[@title='"+contractid+"']")).click();
		Thread.sleep(10000);
		type = driver
				.findElement(By
						.xpath("//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span"))
				.getText();

		System.out.println("Contract Type is: " + type);

		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,500);");
		Thread.sleep(10000);

		WebElement upload = driver
				.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));

		if (upload.isDisplayed()) {
			System.out.println("File is not attached");
			Assert.assertTrue(false);
		} else {
			String file = cp.getFiles().getAttribute("title");
			System.out.println(file);
			System.out.println("File is attached");
		}
		adminlogin(usernameadmin,pwd);
		String URL= driver.getCurrentUrl();
		if(URL.contains("uat")){
		if (type.contains("Legal")) {
			driver.get("https://neustar--uat.lightning.force.com/" + useridlegaladmin);
			Thread.sleep(10000);
		} else {
			driver.get("https://neustar--uat.lightning.force.com/" + useridprocurementadmin);
			Thread.sleep(10000);
		}
		}
		else{
			if (type.contains("Legal")) {
				driver.get("https://neustar--ltnstage.lightning.force.com/" + useridlegaladmin);
				Thread.sleep(10000);
			} else {
				driver.get("https://neustar--ltnstage.lightning.force.com/" + useridprocurementadmin);
				Thread.sleep(10000);
			}
			
		}
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();

		Thread.sleep(20000);
		int j = driver.findElements(By.xpath("//iframe")).size();
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
		Thread.sleep(15000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Search...']")));
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@role='listbox']/search_lightning-instant-result-item/div/div[2]/span[@title='"+contractid+"']")).click();
	
		waitForWebElementPresent(cp.getRelated());
		Thread.sleep(10000);
		cp.getdropdown().click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", cp.getesign());
		Thread.sleep(80000);
		 agreementname = cp.getagreement().getText();
		System.out.println("Agreement Name:" + agreementname);
		System.out.println("Agreement Status:" + cp.getagreementstat().getText());
//code to validate- By default neustar user  should be displayed as receipient along with billing contact
		driver.findElement(By.xpath("//*[@class='slds-form-element__control']//*[@type='number']"));
		driver.findElement(By.xpath("//*[@class='slds-form-element__control']//*[@name='user']"));
		driver.findElement(By.xpath("//*[@class='slds-form-element__control']//*[@name='contact']"));
		int ct= driver.findElements(By.xpath("//*[@class='slds-form_inline esign-flex-container esign-recipient-row']")).size();
		if(ct==2)
			System.out.println("Two Recepients are added");
		else
			Assert.assertTrue(false);
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[1]/div/div/div//input")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[3]/div/div/div//input")));
		Set<String> s = driver.getWindowHandles();
		Iterator<String> i = s.iterator();
		String parent = i.next();
		Thread.sleep(10000);
		while (i.hasNext()) {

			String child1 = i.next();
			String child2 = i.next();
			driver.switchTo().window(child1);
			Thread.sleep(10000);
			System.out.println(driver.getCurrentUrl());
			waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(text(),'Contract Number')]")));
			String Con = driver
					.findElement(By
							.xpath("//*[contains(text(),'Contract Number')]//parent::div/following-sibling::div/span/span"))
					.getText();
			System.out.println("Contract id is :" + Con);
			driver.close();
			driver.switchTo().window(child2);
			Thread.sleep(10000);
			System.out.println(driver.getCurrentUrl());
			waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']")));
			String Acc = driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']")).getText();
			System.out.println("Account Name is :" + Acc);
			driver.close();
			Assert.assertEquals(Con, contractid);
			Assert.assertEquals(Acc, Accountname);

			driver.switchTo().window(parent);
		}
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", cp.getNext());
		Thread.sleep(30000);
		System.out.println(driver.getCurrentUrl());
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		Thread.sleep(10000);
		while (i1.hasNext()) {
			String Window1 = i1.next();
			String Window2 = i1.next();
			driver.switchTo().window(Window2);
			System.out.println("switched to childwindow");
			// Thread.sleep(10000);
			System.out.println(driver.getCurrentUrl());
			List<WebElement> page = driver.findElements(By.xpath("//*[@id='document']/ul/Li"));
			int x = page.size();

			js.executeScript("arguments[0].click();",
					driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
			Thread.sleep(5000);
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
				Thread.sleep(5000);
				
			} catch (Exception e) {

			}
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='control-button']")));
			Thread.sleep(20000);
			driver.switchTo().window(Window1);
			Thread.sleep(5000);
		}
		System.out.println("Agreement Name after sent for customer signature:" + agreementname);
		System.out.println("Agreement Status after sent for customer signature:" + cp.getagreementstat().getText());

		Thread.sleep(5000);

		/* 
		 * while (i.hasNext()) { String ChildWindow = i.next();
		 * driver.switchTo().window(ChildWindow); } Thread.sleep(5000);
		 */
		adminlogin(usernameadmin,pwd);
		String URL1 = driver.getCurrentUrl();
		if (URL1.contains("uat")) {
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
		int l = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int b = 0; b < l; b++) {
			driver.switchTo().frame(b);
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
		Thread.sleep(15000);
		driver.findElement(By.xpath("//*[@class='setupGear']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='related_setup_app_home']")).click();
		// work around
				driver.get("https://neustardev2.service-now.com/login.do");
				driver.findElement(By.id("user_name")).sendKeys(legalid);
				driver.findElement(By.id("user_password")).sendKeys(legalpwd);
				driver.findElement(By.id("sysverb_login")).click();
				
		//work around	
		Set<String> s2 = driver.getWindowHandles();
		Iterator<String> i2 = s2.iterator();
		Thread.sleep(10000);
		while (i2.hasNext()) {
			String ChildWindow = i2.next();
			driver.switchTo().window(ChildWindow);
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='filter-box input']")).sendKeys("All Sites");
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a/*[contains(text(),'All Sites')]")));
		Thread.sleep(10000);
		int k = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int c = 0; c < k; c++) {
			driver.switchTo().frame(c);
			try {

				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@class='net-url']")));

				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		Set<String> s3 = driver.getWindowHandles();
		Iterator<String> i3 = s3.iterator();
		Thread.sleep(10000);
		while (i3.hasNext()) {
			String ChildWindow = i3.next();
			driver.switchTo().window(ChildWindow);
		}
		Thread.sleep(5000);

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//lightning-breadcrumb/a[contains(text(),'Home')]")));
		try {
			driver.findElement(By.id("username")).sendKeys(legalid);
			driver.findElement(By.id("password")).sendKeys(legalpwd);
			driver.findElement(By.xpath("//*[@name='login']")).click();

		} catch (Exception e) {

		}
		Thread.sleep(5000);
	//	waitForWebElementPresent(driver.findElement(By.xpath("//*[@alt='Portal Logo']")));
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//h2[@class='ng-binding'][contains(text(),'Document Search')]")));
		Thread.sleep(5000);

		String docsearchurl = driver.getCurrentUrl();
		if (!docsearchurl.contains("my-contracts")) {
			System.out.println("Page is not navigated to Document search");
			Assert.assertTrue(false);
		}
		System.out.println("Contract: " + contractid);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(contractid);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a[@title='" + contractid + "']")));

		Thread.sleep(15000);
		waitForWebElementPresent(cp.getRelated());
		String status1 = driver
				.findElement(
						By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span"))
				.getText();

		if (status1.equals("Pending Customer Signature")) {
			System.out.println("Stage moved to Pending Customer Signature");
		} else {
			System.out.println("Stage not moved to Pending Customer Signature");
			Assert.assertTrue(false);
		}
		
		cp.getRelated().click();
		Thread.sleep(3000);
		String agg1 = agreementstatus();
		Assert.assertEquals(agg1, "Out for Signature", "Agreement Status is: " + agg1);
		System.out.println("Agreement Status:"+agg1);

		// to compensate signature manual interruption
		for (int m = 0; m < 3; m++) {
			driver.navigate().refresh();
			Thread.sleep(30000);
		}
		
		cp.getDetail().click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);

		String status2 = driver
				.findElement(
						By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		if (status2.equals("Pending NSR Signature")) {
			System.out.println("Stage moved to Pending NSR Signature");
		} else {
			System.out.println("Stage not moved to Pending NSR Signature");
			Assert.assertTrue(false);
		}
		
		cp.getRelated().click();
		Thread.sleep(3000);
		String agg2 = agreementstatus();
		Assert.assertEquals(agg2, "Waiting for Counter-Signature", "Agreement Status is: " + agg2);
		System.out.println("Agreement Status:"+agg2);
		// to compensate signature
		for (int m = 0; m < 3; m++) {
			driver.navigate().refresh();
			Thread.sleep(40000);
		}

		cp.getDetail().click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		String status3 = driver
				.findElement(
						By.xpath("(//*[starts-with(text(),'Status')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		if (status3.equals("Activated")) {
			System.out.println("Stage moved to Activated");
		} else {
			System.out.println("Stage not moved to Activated");
			Assert.assertTrue(false);
		}
		cp.getRelated().click();
		Thread.sleep(3000);
		String agg3 = agreementstatus();
		Assert.assertEquals(agg3, "Signed", "Agreement Status is: " + agg3);
		System.out.println("Agreement Status:"+agg3);
		driver.quit();
	}

	public void adminlogin(String usernameadmin1, String pwd1 ) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin1);
		l.getpassword().sendKeys(pwd1);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
	}

	public String agreementstatus() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage cp = new contractPage(driver);
		String agreementstatus = "";
		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
		driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
		Thread.sleep(5000);
		try {
			List<WebElement> agmnts = driver.findElements(
					By.xpath("//table[@role='grid']/tbody/tr"));
			int count = agmnts.size();
		//	System.out.println(count);
			if (count == 1) {
				agreementstatus = driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[4]")).getText();
			} else if (count > 1) {
				System.out.println("More than 1 agreements found");
				Assert.assertTrue(false);
			}

			else {
				System.out.println("No agreements found");
				Assert.assertTrue(false);
			}
		} catch (Exception exc) {
			Thread.sleep(1000);
		}

		driver.navigate().back();
		return agreementstatus;

	}
}
