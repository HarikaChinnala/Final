package Regression;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.amendmentOpportunityPage;
import Pageobjects.contractPage;
import Pageobjects.createOppPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Pageobjects.oppPage;
import Pageobjects.reusablemethods;
import Regression.Base;

public class LoginPage extends Base {
	
	  public String curl; public String contractid; public String amendurl;

	@Test(priority = 1)
	public void createOpp() throws IOException, InterruptedException {

		driver = initialiseDriver();
		landingPage lp = new landingPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lp.getAccount());
		Thread.sleep(5000);
		lp.getAcctbox().sendKeys(Accountname);
		lp.getAcctbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		// lp.getsrchresult().click();
		// String Accountname = prop.getProperty("Accountname");
		driver.findElement(By.xpath("//*[text()='" + Accountname + "']")).click();
		accountPage ap = new accountPage(driver);
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", ap.getOppsCont());
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", ap.getOppsNew());
		Thread.sleep(5000);
		createOppPage cop = new createOppPage(driver);
		cop.getOppName().sendKeys(opportunityname);
		cop.getcurrency().sendKeys(currency);
		Thread.sleep(5000);
		cop.getcurrency().sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		String usd = currency;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@title='" + usd + "'])[3]")));

		js.executeScript("arguments[0].click();", cop.getcloseDate());
		cop.getToday().click();

		js.executeScript("arguments[0].click();", cop.getstage());
		cop.getstage0().click();

		// String opptype=prop.getProperty("opptype");
		js.executeScript("arguments[0].click();", cop.getOppType());
		driver.findElement(By.xpath("//*[@title='" + opptype + "']")).click();

		js.executeScript("arguments[0].click();", cop.getpilot());
		// String pilotvalue=prop.getProperty("pilotvalue");
		driver.findElement(By.xpath("//*[@title='" + pilotvalue + "']")).click();
		// String sellinglane=prop.getProperty("sellinglane");
		js.executeScript("arguments[0].click();", cop.getsellinglane());
		driver.findElement(By.xpath("//*[@title='" + sellinglane + "']")).click();
		// cop.getSecuritySL().click();

		// String usecase=prop.getProperty("usecase");
		js.executeScript("arguments[0].click();", cop.getusecase());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='" + usecase + "']")));
		// cop.getucDNS().click();
		// String entity=prop.getProperty("entity");
		js.executeScript("arguments[0].click();", cop.getentity());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='" + entity + "']")));
		// cop.getenSSL().click();

		js.executeScript("arguments[0].click();", cop.getMSADate());
		cop.getToday().click();

		js.executeScript("arguments[0].click();", cop.getSODate());
		cop.getToday().click();

		// String primarycontact=prop.getProperty("primarycontact");
		js.executeScript("arguments[0].click();", cop.getprimcntct());
		cop.getprimcntct().sendKeys(primarycontact);
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("//label[contains(text(),'Primary Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
						+ primarycontact + "']"))
				.click();
		js.executeScript("arguments[0].click();", cop.getBillcntct());
		cop.getBillcntct().sendKeys(primarycontact);
		Thread.sleep(4000);
		driver.findElement(By
				.xpath("//label[contains(text(),'Billing Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
						+ primarycontact + "']"))
				.click();
		// System.out.println(cop.getfoundbychannel().isEnabled());
		if (cop.getfoundbychannel().isEnabled()) {
			js.executeScript("arguments[0].click();", cop.getfoundbychannel());
			// String foundbychannel=prop.getProperty("foundbychannel");
			driver.findElement(By.xpath("(//*[@title='" + foundbychannel + "'])[2]")).click();
		}
		cop.getsave().click();
		Thread.sleep(10000);

	}

	@Test(dependsOnMethods = { "createOpp" })
	public void createQuote() throws IOException, InterruptedException {
		// driver= initialiseDriver();
		landingPage lp = new landingPage(driver);
		createQuotePage qp = new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Thread.sleep(3000);
		try {
			js.executeScript("arguments[0].click();", lp.getOpportunities());
		} catch (Exception e1) {

			driver.findElement(By.cssSelector("[title='Opportunities']")).click();
		}
		// lp.getOpportunities().click();
		Thread.sleep(3000);
		lp.getopptxtbox().sendKeys(opportunityname);
		lp.getopptxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		// lp.getsrchresult().click();
		// String OppName = prop.getProperty("OppName");
		driver.findElement(By.xpath("//*[@title='" + opportunityname + "']")).click();
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", lp.getnewQuote());

		Thread.sleep(15000);
		js.executeScript("arguments[0].click();", qp.getRNperiod());
		Thread.sleep(5000);
		// String RNperiod = prop.getProperty("RNperiod");
		driver.findElement(By.xpath("//*[@title='" + RNperiod + "']")).click();

		qp.getsave().click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
	}

	@Test(dependsOnMethods = { "createQuote" })
	public void configureQuote() throws IOException, InterruptedException {

		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", op.getDetail());
		Thread.sleep(30000);
		// driver.findElement(By.xpath("(//*[contains(text(),'Primary
		// Quote')]/parent::div)/following-sibling::div[1]/span/slot/slot/force-lookup/div/force-hoverable-link/div/a/span[@id='window']")).click();
		js.executeScript("arguments[0].click();", op.getquotelink());
		Thread.sleep(20000);
		// to be sent to data.property file
		String quote = driver
				.findElement(By
						.xpath("//*[contains(text(),'Quote Number')]/following-sibling::p/slot/lightning-formatted-text"))
				.getText();
		System.out.println("Quote created is:" + quote);
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(45).createCell(1).setCellValue(quote);
		wb.write(fout);
		
//		prop.setProperty("existquote", quote);
//		try (final OutputStream outputstream = new FileOutputStream(
//				"src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}

		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", op.geteditlines());
		Thread.sleep(30000);
		int x = driver.findElements(By.xpath("//iframe")).size();
		for (int i = 0; i < x; i++) {
			driver.switchTo().frame(i);
			try {
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@name='Add Products']/paper-button")));
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
			}
		}

		driver.findElement(By.xpath("//*[@id='items']/sb-table-row[4]")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		int j = driver.findElements(By.xpath("//iframe")).size();
		for (int a = 0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
						"//*[@name='UltraDNS']/sb-swipe-container/div/div/sb-group/div/div/sb-table-cell-select/div/paper-checkbox")));
			} catch (Exception e) {
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
			}

		}

		driver.findElement(By.xpath("//*[text()='Select']")).click();

		Thread.sleep(5000);

		driver.findElement(By
				.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
				.click();
		driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
				.click();
		driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

		Thread.sleep(10000);

		// QUICK SAVE
	
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='Quick Save']/paper-button")));
		Thread.sleep(30000);

		// SAVE
		// driver.findElement(By.xpath("//*[@name='Save']/paper-button")).click();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='Save']/paper-button")));
		try {
			// driver.navigate().refresh();
			Thread.sleep(30000);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='Save']/paper-button")));
		} catch (Exception exc) {
			Thread.sleep(1000);
			driver.switchTo().defaultContent();

		}
		driver.navigate().refresh();
		Thread.sleep(10000);
	}

	@Test(dependsOnMethods = { "configureQuote" })
	public void generatequoteproposal() throws IOException, InterruptedException {
		createQuotePage qp = new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		reusablemethods rm = new reusablemethods(driver);
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", qp.getGenquoteprop());
		Thread.sleep(60000);
		rm.generatedoc(driver);
		js.executeScript("arguments[0].click();", qp.getRelated());
		Thread.sleep(20000);

		Actions a = new Actions(driver);
		a.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(5000);
		try{
		a.moveToElement(qp.getFiles()).build().perform();
		}
		catch(Exception e){
			js.executeScript("window.scrollBy(0,-1000)", "");
		}
		

		String file = qp.getFiles().getAttribute("title");
		if (file.startsWith("Quote Proposal -"))
			System.out.println("File is attached");
		else
			System.out.println("File is not attached");
	}

	@Test(dependsOnMethods = { "generatequoteproposal" })
	public void approveQuote() throws IOException, InterruptedException {

		createQuotePage qp = new createQuotePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		qp.getdropdown().click();
		Thread.sleep(5000);
		// qp.getsubmitapproval().click();
		String url = driver.getCurrentUrl();

		if (url.contains("ltnstage")) {
			js.executeScript("arguments[0].click();", qp.getsubmitapproval());
			Thread.sleep(10000);
		} else
			js.executeScript("arguments[0].click();", qp.getsubmitapprovaluat());
		Thread.sleep(10000);
		String app = qp.getapproved().getAttribute("aria-selected");
		System.out.println(app);
		if (app.equals("true")) {
			System.out.println("Quote is approved");
		} else
			System.out.println("Quote is not approved");
		// driver.close();
	}

	@Test(dependsOnMethods = { "approveQuote" })
	public void createContract() throws IOException, InterruptedException {

		// driver= initialiseDriver();
		landingPage lp = new landingPage(driver);
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// driver.get("https://neustar--ltnstage.lightning.force.com/lightning/r/Contract/8006s0000005DmXAAU/view");
		js.executeScript("arguments[0].click();", lp.getOpportunities());
		Thread.sleep(5000);
		lp.getopptxtbox().sendKeys(opportunityname);
		lp.getopptxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		// lp.getsrchresult().click();
		// String OppName = prop.getProperty("OppName");
		driver.findElement(By.xpath("//*[@title='" + opportunityname + "']")).click();
		oppPage op = new oppPage(driver);

		js.executeScript("arguments[0].click();", op.getselection());
		js.executeScript("arguments[0].click();", op.getmark());

		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", op.getcontract());
		Thread.sleep(20000);

		js.executeScript("arguments[0].click();", op.getcontractsave());
		Thread.sleep(20000);

		js.executeScript("arguments[0].click();", op.getcontracting());
		js.executeScript("arguments[0].click();", op.getmark());
		Thread.sleep(10000);

		driver.navigate().refresh();
		Thread.sleep(10000);
		String url = driver.getCurrentUrl();
		if (url.contains("ltnstage")) {
			js.executeScript("arguments[0].click();", op.getcontractlink());
			Thread.sleep(5000);
		} else
			js.executeScript("arguments[0].click();", op.getcontractlinkuat());

		Thread.sleep(5000);
		driver.navigate().refresh();

		Thread.sleep(10000);
		String stat = driver.findElement(By.xpath("//*[@title='Status']/following-sibling::div")).getText();
		System.out.println("Contract Status is:" + stat);
		// to be sent to data.prop file
	 curl = driver.getCurrentUrl();
		 contractid = driver
				.findElement(By
						.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		System.out.println("Contractid is:" + contractid);
		System.out.println("Contracturl is:" + curl);
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(107).createCell(1).setCellValue(contractid);
		sheet.getRow(108).createCell(1).setCellValue(curl);
		wb.write(fout);
		
//		prop.setProperty("contractid", contractid);
//		prop.setProperty("contracturl", curl);
//		try (final OutputStream outputstream = new FileOutputStream(
//				"src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}
		Thread.sleep(5000);

		try {
			js.executeScript("arguments[0].click();", cp.getRelated());
		} catch (Exception e3) {

			driver.findElement(By.xpath("//*[@class='tabs__nav']/li/a[@title='Related']")).click();
		}

		try {
			js.executeScript("arguments[0].click();", cp.getmanageclauses());
		} catch (Exception e2) {

			driver.findElement(By.xpath("(//*[@title='Managed Clauses'])[2]")).click();
		}

		Thread.sleep(5000);

		int count = driver.findElements(By.xpath("//*[@role='grid']/tbody/tr")).size();
		// System.out.println(count);

		if (count > 0)
			System.out.println("Manage clauses are generated");
		else
			System.out.println("Manage clauses are not generated");

		driver.navigate().back();

		// driver.close();

	}

	@Test(dependsOnMethods = { "createContract" })
	public void Submitedit() throws IOException, InterruptedException {

		// driver = initialiseDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		contractPage c = new contractPage(driver);
		driver.get(curl);

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// To Check the status of the Chevron
		if (c.Chevron_Draft.getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// Details_Status
		System.out.println("Initial Contract Status in Details Section = " + c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Draft");

		// Contract_Status
		System.out.println("Initial Contract Status = " + c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Draft");

		c.Dropdown().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", c.SubmitEdit());

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		c.SubmitEditDetails().sendKeys(SubmitEditDetails);
		Thread.sleep(5000);
		c.Submit_SubmitEdit().click();

		for (int i = 1; i <= 5; i++) {
			if (c.Details_Status().getText() != "Draft" && c.Contract_Status().getText() != "Draft") {
				System.out.println(i);
				break;
			} else {
				driver.navigate().refresh();
				Thread.sleep(10000);
			}
		}

		Thread.sleep(15000);
		// To Check the status of the Chevron
		if (c.Chevron_EditUnderReview.getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// Details_Status
		System.out.println(
				"Contract Status after doing Submit Edit in Details Section = " + c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Edit Under Review");

		// Contract_Status
		System.out.println("Contract Status after doing Submit Edit = " + c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Edit Under Review");

		c.Related_Tab().click();

		// Cases
		c.Cases_Section().click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		try {
			List<WebElement> Cases_List = driver.findElements(By
					.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Edit Request -- Security')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit Edit is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			c.Case_SubmitEdit().click();
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
		driver.get(curl);
		Thread.sleep(5000);
		c.Related_Tab().click();
		c.Approvals_Section().click();

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
				c.Related_Tab().click();
				Thread.sleep(20000);
				c.Approvals_Section().click();
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
		// JavascriptExecutor js = (JavascriptExecutor)driver;
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
		driver.get(curl);

		// driver.findElement(By.xpath("//span[text()='Details']")).click();
		// driver.navigate().back();

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

		// driver.close();

	}

	@Test(dependsOnMethods = { "Submitedit" })
	public void eSignature() throws IOException, InterruptedException {

		// driver = initialiseDriver();
		reusablemethods rm = new reusablemethods(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(curl);
		// contractid = prop.getProperty("contractid");
		contractPage cp = new contractPage(driver);
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,600);");
		Thread.sleep(10000);

		WebElement upload = driver
				.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));

		if (upload.isDisplayed()) {
			System.out.println("File is not attached");

			cp.getdropdown().click();
			js.executeScript("arguments[0].click();", cp.getGenerateDoc());
			Thread.sleep(80000);
			rm.generatedoc(driver);

			Thread.sleep(20000);
		} else {
			String file = cp.getFiles().getAttribute("title");
			System.out.println(file);
			if (file.endsWith("- " + contractid + ".docx"))

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
		// rm.otherUserlogin(driver);
		// other user login
		
		String URL = driver.getCurrentUrl();
		// System.out.println(URL);
		// userid= prop.getProperty("userid");
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + userid);
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
			driver.get("https://neustar--ltnstage.lightning.force.com/" + userid);
			Thread.sleep(10000);
		}
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();

		Thread.sleep(10000);
		int j = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int a=0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
		//		 System.out.println("clicked login");
				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		// contracturl=prop.getProperty("contracturl");
		driver.get(curl);
		Thread.sleep(10000);
		cp.getdropdown().click();

		js.executeScript("arguments[0].click();", cp.getesign());
		Thread.sleep(80000);
		String agreementname = cp.getagreement().getText();
		System.out.println(agreementname);
		System.out.println(cp.getagreementstat().getText());
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
			Thread.sleep(10000);
			System.out.println(driver.getCurrentUrl());
			js.executeScript("arguments[0].click();",
					driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='control-button']")));
			Thread.sleep(20000);
			driver.switchTo().window(Window1);
			Thread.sleep(5000);
		}
		System.out.println(cp.getagreementstat().getText());

		Thread.sleep(5000);
		driver.get(curl);
		Thread.sleep(10000);

		js.executeScript("arguments[0].click();", cp.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,1500);");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
		Thread.sleep(2000);
		try {
			String agreementstatus = driver.findElement(By.xpath("((((//*[contains(text(),'" + agreementname
					+ "'])/preceding-sibling::a)/parent::div)/parent::th)/following-sibling::td[3]")).getText();
			System.out.println(agreementstatus);
		} catch (Exception exc) {
			Thread.sleep(1000);
		}

		// System.out.println(cp.getagreementstatus().getText());

		String PCS = cp.getPCSchev().getAttribute("aria-selected");
		// System.out.println(PCS);
		if (PCS.equals("true")) {
			System.out.println("Stage moved to Pending Customer Signature");
		} else
			System.out.println("Stage not moved to Pending Customer Signature");

		// loop to compensate manual interruption for signing document
		for (int m = 0; m < 5; m++) {
			driver.navigate().refresh();
			
			Thread.sleep(20000);

		}
		try {
			String agreementstatus = driver.findElement(By.xpath("((((//*[contains(text(),'" + agreementname
					+ "'])/preceding-sibling::a)/parent::div)/parent::th)/following-sibling::td[3]")).getText();
			System.out.println(agreementstatus);
		} catch (Exception exc) {
			Thread.sleep(1000);
		}
		String CS = cp.getCSchev().getAttribute("aria-selected");
		// System.out.println(CS);
		if (CS.equals("true")) {
			System.out.println("Stage moved to Client Signed");
		} else
			System.out.println("Stage not moved to Client Signed");

		// driver.close();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as user
		l.getusername().sendKeys(username);
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
		 
	}

	@Test(dependsOnMethods = { "eSignature" })
	public void contractSubmitForSignature() throws IOException, InterruptedException {
		
		contractPage c = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(curl);
		Thread.sleep(15000);
		js.executeScript("window.scrollBy(0,2700)", "");
		Thread.sleep(3000);
		
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(
				"((//*[text() = 'Billing Contact'])//parent::div)//following-sibling::div/span[@class='test-id__field-value slds-form-element__static slds-grow ']"));
		try{
		act.doubleClick(ele).perform();
		}
		catch(Exception e){
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("((//*[text() = 'Billing Contact'])//parent::div)//following-sibling::div/button/lightning-primitive-icon")));
		}
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/input"))
				.sendKeys(primarycontact);
		Thread.sleep(5000);
		//String primarycontact = prop.getProperty("primarycontact");
		driver.findElement(By
				.xpath("(((//*[text() = 'Billing Contact'])//parent::label)//following-sibling::div)/div/div/div/div/div[@class='listContent']/ul/li/a/div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']/div[@title='"
						+ primarycontact + "']"))
				.click();
		driver.findElement(By.xpath("//*[text() = 'Save']")).click();
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		c.Dropdown().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		js.executeScript("arguments[0].click();", c.SubmitForSignature());

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
		Thread.sleep(5000);
		// driver.navigate().refresh();

		for (int i = 1; i <= 5; i++) {
			if (c.Details_Status().getText() != "Edit Complete" && c.Contract_Status().getText() != "Edit Complete") {
				System.out.println(i);
				break;
			} else {
				driver.navigate().refresh();
				Thread.sleep(10000);
			}
		}

		Thread.sleep(10000);
		// To Check the status of the Chevron
		if (c.Chevron_SubmissionUnderReview().getAttribute("class").contains("active")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// Details_Status
		System.out.println(
				"Contract Status after Submit for Signature in Details Section = " + c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Submission Under Review");

		// Contract_Status
		System.out.println("Contract Status after Submit for Signature = " + c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Submission Under Review");

		c.Related_Tab().click();

		// Cases
		c.Cases_Section().click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		try {

			List<WebElement> Cases_List = driver.findElements(By.xpath(
					"//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Contract Submission -- Security')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit for Signature is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
			Thread.sleep(5000);
			c.Case_SubmitForSignature().click();
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

		// driver.close();

	}

	@Test(dependsOnMethods = { "contractSubmitForSignature" })
	  public void attentionrequired() throws IOException, InterruptedException {
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
		// driver.get("https://neustar--ltnstage.lightning.force.com/lightning/r/Contract/8006s0000005Dg0AAE/view");
		Thread.sleep(5000);
		driver.navigate().refresh();
		//((JavascriptExecutor) driver).executeScript("scroll(0,100);");
		Thread.sleep(10000);
		Actions action = new Actions(driver);
	try{
		action.moveToElement(cp.getstatus()).build().perform();
		Thread.sleep(5000);
		action.doubleClick(cp.getstatus()).build().perform();
		}
		catch(Exception e){
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("((//span[contains(text(),'Status')])/parent::div)/following-sibling::div/button/lightning-primitive-icon")));


		}
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

		String AR = cp.getARchev().getAttribute("aria-selected");
		if (AR.equals("true")) {
			System.out.println("Contract stage is moved to Attention Required");
		} else
			System.out.println("Contract stage is not moved to Attention Required");

	}

	// Awaiting activation
	@Test(dependsOnMethods = { "attentionrequired" })

	public void awaitingactivation() throws IOException, InterruptedException {

		contractPage cp = new contractPage(driver);
		//((JavascriptExecutor) driver).executeScript("scroll(0,100);");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		try{
		action.moveToElement(cp.getstatus()).build().perform();
		action.doubleClick(cp.getstatus()).build().perform();
		}
		catch (Exception e){
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("((//span[contains(text(),'Status')])/parent::div)/following-sibling::div/button/lightning-primitive-icon")));

		}
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.select")));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", cp.getAAdrop());
		js.executeScript("arguments[0].click();", cp.geteditsave());
		Thread.sleep(10000);
		for (int m = 0; m <= 2; m++) {
			driver.navigate().refresh();
			Thread.sleep(5000);

		}

		String AA = cp.getAAchev().getAttribute("aria-selected");
		if (AA.equals("true")) {
			System.out.println("Contract stage is moved to Awaiting Activation");
		} else
			System.out.println("Contract stage is not moved to Awaiting Activation");
	}

	// contract activation
	@Test(dependsOnMethods = { "awaitingactivation" })
	public void contractactivation() throws IOException, InterruptedException {
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(curl);
		js.executeScript("arguments[0].click();", cp.getActivate());
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", cp.getActivatepopup());
		Thread.sleep(5000);
		// driver.navigate().refresh();
		Thread.sleep(10000);

		String act = cp.getActchev().getAttribute("aria-selected");
		if (act.equals("true")) {
			System.out.println("Contract is Activated");
		} else
			System.out.println("Contract is not Activated");
		Thread.sleep(8000);
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='slds-form-element__control']/div/div/a")));
		 
		Thread.sleep(10000);
		String chev=driver.findElement(By.xpath("//*[@title='7 - Won']")).getAttribute("aria-selected");
		  if (chev.equals("true")){
	        	System.out.println("Opportunity is moved to 7-won stage");
	        }
	        else 
	        	System.out.println("Opportunity is not moved to 7-won stage");
		// driver.close();

	}

	@Test(dependsOnMethods = { "contractactivation" })

	public void createAmendmentOpportunity() throws IOException, InterruptedException {

		// driver = initialiseDriver();
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get(curl);
		amendmentOpportunityPage ao = new amendmentOpportunityPage(driver);
		Actions a = new Actions(driver);

		Thread.sleep(20000);

		ao.AmendButton().click();

		Thread.sleep(60000);

		int x = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(x);
		for (int j = 0; j < x; j++) {
			driver.switchTo().frame(j);
			try {
				ao.Amend().click();
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
			driver.switchTo().defaultContent();
		}

		Thread.sleep(60000);

		int x1 = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(x1);
		for (int j = 0; j < x1; j++) {
			driver.switchTo().frame(j);
			try {
				ao.Toggle().click();
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
			driver.switchTo().defaultContent();
		}

		int x2 = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(x2);
		for (int j = 0; j < x2; j++) {
			driver.switchTo().frame(j);

			try {
				Thread.sleep(5000);
				ao.Clone().click();
				// System.out.println(driver.switchTo().alert().getText());
				Thread.sleep(3000);
				driver.switchTo().alert().accept();
				Thread.sleep(5000);
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
			driver.switchTo().defaultContent();
		}

		int y = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(y);
		for (int j = 0; j < y; j++) {
			driver.switchTo().frame(j);
			// System.out.println("switched to frame : " +j);
			try {
				driver.findElement(By
						.xpath("(//button[@id='actionButton1'])[1]//parent::sf-line-actions//parent::div//following-sibling::div[5]"))
						.click();
				driver.findElement(By
						.xpath("(//button[@id='actionButton1'])[1]//parent::sf-line-actions//parent::div//following-sibling::div[5]/div/span"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By
						.xpath("//input[@class='myinput numberInput style-scope sb-input --desktop style-scope sf-le-table-row']"))
						.sendKeys("" + 0);

				a.sendKeys(Keys.ENTER).build().perform();

				// System.out.println(driver.switchTo().alert().getText());
				Thread.sleep(3000);
				driver.switchTo().alert().accept();
				Thread.sleep(5000);
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
			driver.switchTo().defaultContent();
		}

		int z = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(x);
		for (int j = 0; j < z; j++) {
			driver.switchTo().frame(j);

			try {
				driver.findElement(By.xpath("//span[@class='sf-icon-chevronright style-scope sb-line-editor']"))
						.click();
				driver.findElement(By
						.xpath("//div[@id='item'][contains(text(),'Subscription Term')]//parent::div/div/sb-field/span/div/sb-input/input[@type='text']"))
						.sendKeys("" + 24);
				// driver.findElement(By.xpath("//*[contains(text(),'Quick
				// Save')]")).click();
				Thread.sleep(10000);
				// driver.findElement(By.xpath("//*[@name='Save']")).click();

				driver.findElement(By.xpath("//*[@name='Save']/paper-button")).click();
				Thread.sleep(10000);
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
			driver.switchTo().defaultContent();
		}
		Thread.sleep(20000);

		driver.navigate().refresh();
		Thread.sleep(20000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		createQuotePage qp = new createQuotePage(driver);

		js.executeScript("arguments[0].click();", qp.getGenquoteprop());
		Thread.sleep(60000);

		int k = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println("k="+k);
		for (int b = 0; b < k; b++) {
			driver.switchTo().frame(b);
			// System.out.println("b="+b);
			try {
				driver.findElement(
						By.xpath("//*[@class='x-box-inner ']/div/a/span/span/span[contains(text(),'Continue')]"))
						.click();
			} catch (Exception e) {

				int m = driver.findElements(By.xpath("//iframe")).size();
				// System.out.println("m="+m);
				if (m > 0) {
					for (int c = 0; c < m; c++) {
						driver.switchTo().frame(c);
						// System.out.println("c="+c);
						try {
							driver.findElement(By
									.xpath("//*[@class='x-box-inner ']/div/a/span/span/span[contains(text(),'Continue')]"))
									.click();
						} catch (Exception ex) {

							int n = driver.findElements(By.xpath("//iframe")).size();
							// System.out.println("n="+n);
							if (n > 0) {
								for (int d = 0; d < n; d++) {
									driver.switchTo().frame(d);
									// System.out.println("d="+d);
									try {
										driver.findElement(By
												.xpath("//*[@class='x-box-inner ']/div/a/span/span/span[contains(text(),'Continue')]"))
												.click();
									} catch (Exception exc) {

										driver.switchTo().defaultContent();
									}
								}
							} else {
								driver.switchTo().defaultContent();
							}
						}
					}
				} else {
					driver.switchTo().defaultContent();
				}
			}
		}

		Thread.sleep(10000);
		driver.switchTo().defaultContent();
		Thread.sleep(20000);

		js.executeScript("arguments[0].click();", qp.getRelated());
		Thread.sleep(10000);

		a.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(10000);

		String file = qp.getFiles().getAttribute("title");
		if (file.startsWith("Quote Proposal -"))
			System.out.println("File is attached");
		else
			System.out.println("File is not attached");

		qp.getdropdown().click();
		Thread.sleep(5000);
		// qp.getsubmitapproval().click();
		// js.executeScript("arguments[0].click();", qp.getsubmitapproval());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@name='Submit_for_Approval']")));
		Thread.sleep(20000);

		String app = qp.getapproved().getAttribute("aria-selected");
		System.out.println(app);
		if (app.equals("true")) {
			System.out.println("Quote is approved");
		} else
			System.out.println("Quote is not approved");

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//*[contains(text(),'Amendment for contract #" + contractid + "')]")));
		Thread.sleep(5000);
		// to be sent to data.prop file

		 amendurl = driver.getCurrentUrl();
		System.out.println("Amendment Opportunity url:" + amendurl);
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(120).createCell(1).setCellValue(amendurl);
		wb.write(fout);
		
//		prop.setProperty("amendurl", amendurl);
//		try (final OutputStream outputstream = new FileOutputStream(
//				"src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}

		// driver.get(amendurl);
		Thread.sleep(15000);
		// opportunity type
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("scroll(0,500);");
		try{
		a.doubleClick(driver.findElement(
				By.xpath("(//*[contains(text(),'Opportunity Type')]//parent::div)/following-sibling::div"))).build()
				.perform();
		}
		catch(Exception e){
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Opportunity Type')]//parent::div)/following-sibling::div/button")));
		}
		Thread.sleep(10000);
		// js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Opportunity
		// Type')]//parent::label)/following-sibling::div")));
		WebElement opp = driver.findElement(By.xpath(
				"(//*[contains(text(),'Opportunity Type')]//parent::label)/following-sibling::div[1]/lightning-base-combobox/div/div[1]/input"));
		// js.executeScript("arguments[0].scrollTo();",opp);
		// opp.click();
		js.executeScript("arguments[0].click();", opp);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[contains(text(),'Opportunity Type')]//parent::label)/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[@data-value='Upgrade']")));
		Thread.sleep(1000);

		// product/usecase
		// js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Product
		// / Use Case')]//parent::label)/following-sibling::div")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[contains(text(),'Product / Use Case')]//parent::label)/following-sibling::div[1]/lightning-base-combobox/div/div[1]/input")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[contains(text(),'Product / Use Case')]//parent::label)/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[@data-value='DNS']")));
		Thread.sleep(1000);

		// service date
		// js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Service
		// Order Date')]//parent::label)/following-sibling::div")));
		WebElement serv = driver.findElement(
				By.xpath("(//*[contains(text(),'Service Order Date')]//parent::label)/following-sibling::div/input"));
		// js.executeScript("arguments[0].scrollIntoView();",serv);
		js.executeScript("arguments[0].click();", serv);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='today']")));
		Thread.sleep(5000);

		// foundbychannel
		WebElement foundbychannel = driver.findElement(By.xpath(
				"(//*[contains(text(),'Found by Channel?')]//parent::label)/following-sibling::div[1]/lightning-base-combobox/div/div[1]/input"));
		// js.executeScript("arguments[0].scrollIntoView();",foundbychannel);
		if (foundbychannel.isEnabled()) {
			// js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Found
			// by Channel?')]//parent::label)/following-sibling::div")));
			js.executeScript("arguments[0].click();", foundbychannel);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
					"(//*[contains(text(),'Found by Channel?')]//parent::label)/following-sibling::div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[@data-value='No']")));
		}
		Thread.sleep(5000);

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='SaveEdit']")));
		Thread.sleep(20000);

		// driver.close();
	}

	@Test(dependsOnMethods = { "createAmendmentOpportunity" })
	public void amendmentSubmitEdit() throws IOException, InterruptedException {
		contractPage c = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(amendurl);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click(); // Changed
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text() = 'Submit Edit']")));
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("Check and Approve");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='slds-card__footer']/button[@type='submit']")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag
																		// name
																		// changed
		Thread.sleep(10000);

		// Cases
		js.executeScript("window.scrollBy(0,150)", "");
		Actions act = new Actions(driver);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::a")).click();

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
		driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag
																		// name
																		// changed
		Thread.sleep(10000);

		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::a")).click();

		Thread.sleep(10000);

		// Approving the Approval Records
		try {
			driver.navigate().refresh();
			Thread.sleep(5000);
			driver.navigate().refresh();
			Thread.sleep(5000);
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
				driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag
																				// name
																				// changed
				Thread.sleep(10000);
				js.executeScript("window.scrollBy(0,150)", "");
				Thread.sleep(10000);
				driver.findElement(
						By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
						.click();
				act.moveByOffset(10, 0).build().perform();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::a")).click();
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

		driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag
																		// name
																		// changed
		Thread.sleep(10000);

		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::a")).click();

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
		// driver.close();
	}

	@Test(dependsOnMethods = { "amendmentSubmitEdit" })

	public void eSignatureAmendment() throws IOException, InterruptedException {
		// driver= initialiseDriver();
		reusablemethods rm = new reusablemethods(driver);
		oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(amendurl);
		Thread.sleep(10000);
		String contractchev = op.getcontracting().getAttribute("aria-selected");
		if (contractchev.equals("true")) {

		} else {
			js.executeScript("arguments[0].click();", op.getcontracting());
			js.executeScript("arguments[0].click();", op.getmark());
		}
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", op.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
		Thread.sleep(10000);

		WebElement upload = driver
				.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));

		if (upload.isDisplayed()) {
			System.out.println("File is not attached");

			// cp.getdropdown().click();
			js.executeScript("arguments[0].click();", op.getGenamend());
			Thread.sleep(80000);
			rm.generatedoc(driver);

		} else {
			String file = op.getFiles().getAttribute("title");
			System.out.println(file);
			

			System.out.println("File is attached");
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(10000);
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
		// System.out.println(URL);
		// userid= prop.getProperty("userid");
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + userid);
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
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
		// contracturl=prop.getProperty("contracturl");
		driver.get(amendurl);
		Thread.sleep(10000);
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
			js.executeScript("arguments[0].click();",
					driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='control-button']")));
			Thread.sleep(20000);
			driver.switchTo().window(Window1);
			Thread.sleep(5000);
		}
		System.out.println(op.getagreementstat().getText());

		Thread.sleep(5000);
		// ((JavascriptExecutor)driver).executeScript("scroll(0,500);");
		// js.executeScript("arguments[0].click();",cp.getcontractlink());
		driver.get(amendurl);
		Thread.sleep(10000);
	
		js.executeScript("arguments[0].click();", op.getRelated());
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,1500);");
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
		Thread.sleep(2000);
		try {
			String agreementstatus = driver.findElement(By.xpath("((((//*[contains(text(),'" + agreementname
					+ "'])/preceding-sibling::a)/parent::div)/parent::th)/following-sibling::td[3]")).getText();
			System.out.println(agreementstatus);
		} catch (Exception exc) {
			Thread.sleep(1000);
		}
		js.executeScript("arguments[0].click();", op.getDetail());
		Thread.sleep(5000);
		String status = op.getAmendcontractstatus().getText();
		System.out.println(status);
		
		// loop to compensate manual interruption for signing document
		for (int m = 0; m <= 5; m++) {
			driver.navigate().refresh();
			Thread.sleep(20000);

		}

		// String status = op.getAmendcontractstatus().getText();
		System.out.println(status);
		try {
			js.executeScript("arguments[0].click();", op.getRelated());

			String agreementstatus = driver.findElement(By.xpath("((((//*[contains(text(),'" + agreementname
					+ "'])/preceding-sibling::a)/parent::div)/parent::th)/following-sibling::td[3]")).getText();
			System.out.println(agreementstatus);
		} catch (Exception exc) {
			Thread.sleep(1000);
		}

	
		// driver.close();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		
		l.getusername().sendKeys(username);
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
	}

	@Test(dependsOnMethods = { "eSignatureAmendment" })
	public void amendmentSubmitForSignature() throws IOException, InterruptedException {

		 	contractPage c = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get(amendurl);
		Thread.sleep(15000);
		js.executeScript("window.scrollBy(0,1300)", "");
		Actions act = new Actions(driver);

		driver.findElement(By.xpath("//*[text() = 'Billing Contact']//parent::div//following-sibling::div/button"))
				.click();
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

		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click(); // Changed
		Thread.sleep(5000);

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
		Thread.sleep(15000);
		// driver.navigate().refresh();

		oppPage op = new oppPage(driver);
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
		driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::a")).click();

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

		// driver.close();

	}

	@Test(dependsOnMethods = { "amendmentSubmitForSignature" })
	public void amendContract() throws IOException, InterruptedException {
			oppPage op = new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(amendurl);
		Thread.sleep(15000);
		String amendstatus = op.getAmendcontractstatus().getText();

		if (amendstatus.equals("Activated")) {
			System.out.println(amendstatus);
		} else {
			System.out.println(amendstatus);
			Thread.sleep(5000);
			Actions action = new Actions(driver);

			// action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			// Thread.sleep(10000);
			// ((JavascriptExecutor)driver).executeScript("scroll(0,4000);");
			try{
			action.moveToElement(op.getAmendcontractstatus()).build().perform();
			action.doubleClick(op.getAmendcontractstatus()).build().perform();
			}
			catch(Exception e){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("((//*[contains(text(),'Amendment Contract Status')]//parent::div)/following-sibling::div)/button")));	
			}
			// js.executeScript("window.scrollBy(0,50)", "");
			Thread.sleep(5000);
			action.moveToElement(driver.findElement(
					By.xpath("//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div")))
					.build().perform();
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,90)", "");
			Thread.sleep(5000);
			driver.findElement(
					By.xpath("//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div"))
					.click();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
					"(//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div/lightning-base-combobox/div/div)/following-sibling::div/lightning-base-combobox-item[@data-value='Activated']")));
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();", op.getSaveedit());
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(10000);

		js.executeScript("arguments[0].click();", op.getClosedchev());
		js.executeScript("arguments[0].click();", op.selectstage);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//*[@class='modal-footer slds-modal__footer']/button[2]")));
		Thread.sleep(10000);
		String act = op.get7won().getAttribute("aria-selected");
		if (act.equals("true")) {
			System.out.println("Opportunity is moved to 7-won stage");
		} else
			System.out.println("Opportunity is not moved to 7-won stage");

		js.executeScript("arguments[0].click();", op.getcontract());
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();", op.getcontractsave());
		// Thread.sleep(5000);
		// driver.navigate().refresh();
		Thread.sleep(10000);

		((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[contains(text(),'Amended Contract')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup/div/force-hoverable-link/div/a")));
		Thread.sleep(10000);

		String amendcontract = driver
				.findElement(By
						.xpath("((//*[contains(text(),'Latest Amendment Opportunity')])/parent::div)/following-sibling::div/span"))
				.getText();

		if (amendcontract.contains("Amendment for contract")) {
			System.out.println(amendcontract);
			System.out.println("contract is amended");
		} else {
			System.out.println("contract is not amended");
		}
		driver.close();

	}

}