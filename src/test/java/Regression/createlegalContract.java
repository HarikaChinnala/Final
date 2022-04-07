package Regression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


public class createlegalContract extends Base {

	private String primdoc;

	@Test
	public void createlegalContract() throws IOException, InterruptedException {

		driver = initialiseDriver();
		landingPage lp = new landingPage(driver);
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

	//	Thread.sleep(3000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//lightning-breadcrumb/a[contains(text(),'Home')]")));
		try {
			driver.findElement(By.id("username")).sendKeys(legalid);
			driver.findElement(By.id("password")).sendKeys(legalpwd);
			driver.findElement(By.xpath("//*[@name='login']")).click();
			
		} catch (Exception e) {

		}
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//h2[@class='ng-binding'][contains(text(),'" + functionalgroup + "')]")));
		Thread.sleep(5000);

		if (!functionalgroup.equals("Procurement support")){
			if(!functionalgroup.equals("NDA")){
			driver.findElement(By.xpath("//*[@title='" + listmem + "']")).click();}}
		
			Thread.sleep(80000);
		String currenturl= driver.getCurrentUrl();
		if(currenturl.contains("category")){
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='s2id_sp_formfield_subcategory']")).click();
			driver.findElement(By.xpath("(//*[@id='select2-drop']/ul/li/div[starts-with(text(),'"+ serviceline + "')])")).click();
			Thread.sleep(3000);
			
			Date date = requesteddate;  
		    DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");  
		    String requestedDate = dateFormat.format(date);
			
			driver.findElement(By.xpath("//*[@id='sp_formfield_requested_due_by']")).sendKeys(requestedDate);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='sp_formfield_comments']")).sendKeys("Testing comments");
			Thread.sleep(3000);
		
			if(upload.equals("yes")){
				driver.findElement(By.xpath("//*[@title='Add attachment']")).click();
				//to upload doc manually
				Thread.sleep(45000);
			}
			
	//		driver.findElement(By.xpath("//*[@name='submit']")).click();
		}
		else{
		legalDocumentPage ldp = new legalDocumentPage(driver);
		js.executeScript("arguments[0].click();", ldp.getServiceline());
		js.executeScript("arguments[0].click();",
				driver.findElement(By
						.xpath("(//*[@name='Service_Line__c']//parent::div)/following-sibling::div/lightning-base-combobox-item[@data-value='"
								+ serviceline + "']")));

		js.executeScript("arguments[0].click();", ldp.getDoctype());
		Select s = new Select(ldp.getDoctype());
		s.selectByVisibleText("" + documenttypelegal + "");
		Thread.sleep(5000);
		System.out.println("Document type Selected is :" + documenttypelegal);
		
		try{
			js.executeScript("arguments[0].click();",ldp.getlegalentity());
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@name='NDA_Entity__c']//parent::div)/following-sibling::div/lightning-base-combobox-item[@data-value='"+legalentity+"']")));
			Thread.sleep(3000);
		}
		catch(Exception e){
			
		}
		
		if(functionalgroup.equals("Procurement support")){
			driver.findElement(By.xpath("//*[@name='Existing_PO__c']")).click();
			driver.findElement(By.xpath("(//*[@name='Existing_PO__c']//parent::div)/following-sibling::div/lightning-base-combobox-item//span[@title='"+existpo+"']")).click();
		if(existpo.equals("yes"))
			driver.findElement(By.xpath("//*[@name='PO_Number__c']")).sendKeys(ponumb);
			
		}

		driver.findElement(By.xpath("//*[@placeholder='Search Accounts...']")).sendKeys(Accountname);
		try{
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					driver.findElement(By
							.xpath("(//*[@placeholder='Search Accounts...']//parent::div)/following-sibling::div/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
									+ Accountname + "']")));
		}
		catch(Exception e){
		driver.findElement(By.xpath("//*[@placeholder='Search Accounts...']")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='"+ Accountname + "']")));
		}
		
		
		
		ldp.getcountercontact().sendKeys("Sales Ops Test Contact");
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(((//*[@for='custSignedBy']//following-sibling::lightning-input-field)//input)/parent::div)/following-sibling::div/ul/li/lightning-base-combobox-item/span[2]/span/*[@title='Sales Ops Test Contact']")));
		WebElement msg1 = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg1);
		System.out.println("Message on wrong counter contact selection:" + "\n" + msg1.getText());
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(
				"(//*[@for='custSignedBy']//following-sibling::lightning-input-field)//*[contains(text(),'Clear Selection')]")));
		ldp.getcountercontact().sendKeys("" + countercontact + "");
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By
						.xpath("(((//*[@for='custSignedBy']//following-sibling::lightning-input-field)//input)/parent::div)/following-sibling::div/ul/li/lightning-base-combobox-item/span[2]/span/*[@title='"
								+ countercontact + "']")));
		Thread.sleep(5000);

		ldp.getcomments().sendKeys("Testing comments");
		if(upload.equals("yes")||documenttypelegal.equals("Vendor MSA")){
			ldp.getupload().click();
			System.out.println("Upload Document Manually");
			// for manual upload
			Thread.sleep(45000);
			if(paper.equals("neustar")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Neustar')]//preceding-sibling::span")));
			}
			else if(paper.equals("3rd")){
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label/*[starts-with(text(),'Third-Party')]//preceding-sibling::span")));
				
			}
			driver.findElement(By.xpath("//*[@name='Document__c']")).clear();
			driver.findElement(By.xpath("//*[@name='Document__c']")).sendKeys(documenttypelegal+"-uploaded");
		}

		js.executeScript("arguments[0].click();", ldp.getsubmit());
		Thread.sleep(15000);
		waitForWebElementPresent(driver.findElement(By.xpath("//a[starts-with(text(),'here')]")));
		driver.findElement(By.xpath("//a[starts-with(text(),'here')]")).click();
		Thread.sleep(10000);
		System.out.println("Page Title: "+driver.getTitle());
		String id = driver
				.findElement(By
						.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(107).createCell(1).setCellValue(id);
		wb.write(fout);
		
//		prop.setProperty("contractid", id);
//		try (final OutputStream outputstream = new FileOutputStream("src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}
		driver.findElement(By.xpath("//*[@class='logoImage']")).click();
		Thread.sleep(5000);
		// validation in document search
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//lightning-breadcrumb/a[contains(text(),'Home')]")));
		Thread.sleep(3000);
		try {
			driver.findElement(By.id("username")).sendKeys(legalid);
			driver.findElement(By.id("password")).sendKeys(legalpwd);
			driver.findElement(By.xpath("//*[@name='login']")).click();
			
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
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(id);
		driver.findElement(By.xpath("//*[@name='Contract-search-input']")).sendKeys(Keys.ENTER);

		String idlink = driver
				.findElement(By.xpath("//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a"))
				.getAttribute("title");
		Thread.sleep(3000);
		if (idlink.equals(id))
			driver.findElement(By.xpath("//th[@scope='row']/*[@class='slds-grid slds-grid--align-spread']/a")).click();
		else
			Assert.assertTrue(false);                                                                                                                                                                                                                                                                          
		Thread.sleep(3000);
/*		
		driver.findElement(By.xpath("//*[@title='Help URL']//following-sibling::div//a")).click();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			driver.switchTo().window(ChildWindow);
		}
		String helpurl=driver.getCurrentUrl();
		Assert.assertEquals(helpurl, "https://neustar.sharepoint.com/sites/LegalPortalGuide/SitePages/UserGuide.aspx");
		driver.close();
	*/	
		// contract page layout values
		System.out.println("Contract Pagelayout Values");
		// 1. Contract number
		System.out.println("Contract number: " + id);
		// 2. Account name
		String accname = cp.getaccountname().getText();
		System.out.println("accountname: " + accname);
		// 3. Type
		String type = cp.gettype().getText();
		System.out.println("Type: " + type);
		// 4. Entity
		String entity = cp.getentity().getText();
		System.out.println("Legal entity: " + entity);
		// 5. Status
		String status = cp.getStatus().getText();
		System.out.println("Status: " + status);
		// 6. Billing email
		String bemail = cp.getbillingmail().getText();
		System.out.println("Billing Email: " + bemail);
		// 7. Documnet Type
		String documenttype = cp.getdocumenttype().getText();
		System.out.println("Documnet Type: " + documenttype);
		// 8. Customer Signed By
		String custsigned = cp.getcustsignedby().getText();
		System.out.println("Customer Signed By: " + custsigned);
		// 9. Company Signed By
		String compsigned = cp.getcompsignedby().getText();
		System.out.println("Company Signed By : " + compsigned);
		// 10.Billing contact
		String billcontact = cp.getbillcontact().getText();
		System.out.println("billingcontact: " + billcontact);
		// 11.Document Title
		String doctitle = cp.getdoctitle().getText();
		System.out.println("Document Title: " + doctitle);
		// 12.Paper Source
		String pgsrc = cp.getpapersrc().getText();
		System.out.println("Paper Source : " + pgsrc);
		// 13.Primary Document
		primdoc = cp.getprimdoc().getText();
		System.out.println("Primary Document : " + primdoc);
		// 14. Legal Item Category
		String legalcat = cp.getlegalcat().getText();
		System.out.println("Legal Item Category: " + legalcat);
		// 15.Community Page Name
		String pagename = cp.getpagename().getText();
		System.out.println("Community Page Name: " + pagename);
		// 16.Service Line
		String servline = cp.getserviceline().getText();
		System.out.println("Service Line: " + servline);
		// 17.Is From Community
		String iscomm = cp.getisfromcomm().getAttribute("class");
		if(iscomm.contains("checked"))
		System.out.println("Is From Community: " + iscomm);
		else
		Assert.assertTrue(false);
		if(type.equals("Procurement")){
		// 18.Existing PO
		String exstpo = cp.getexpo().getText();
		System.out.println("Existing PO: " + exstpo);
		// 19.PO Number
		String ponum = cp.getpono().getText();
		System.out.println("PO Number: " + ponum);
		}
		
		driver.findElement(By.xpath("//*[@title='Help URL']//following-sibling::div//a"));
		driver.findElement(By.xpath("//*[@title='Follow']"));
		if(!type.equals("Procurement")){
		driver.findElement(By.xpath("//*[@title='Submit Edit']"));
		}
		else{
		driver.findElement(By.xpath("//*[@title='Edit']"));}
		driver.findElement(By.xpath("//*[@title='Submit For Signature']"));
		driver.findElement(By.xpath("//*[@title='Send for Counter-Party Review']"));
		
		if(documenttype.equals(documenttypelegal) && accname.equals(Accountname) && billcontact.equals(countercontact)){
			if(functionalgroup.equals("Procurement support")){
				Assert.assertEquals(type, "Procurement");
				if(upload.equals("yes")|| upload.equals("no"))
					Assert.assertEquals(status, "Draft");
					
			}
			else{
				Assert.assertEquals(type, "Legal");
				if(upload.equals("yes"))
					Assert.assertEquals(status, "Edit Under Review");
				else if(upload.equals("no"))
				Assert.assertEquals(status, "Draft");
			
			}
			}
		else
			Assert.assertTrue(false);
		}
		
		cp.getRelated().click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		driver.findElement(By.xpath("//*[@title='Approvals']"));
		if(upload.equals("yes")){
			if(!functionalgroup.equals("Procurement support")){
			String app= driver.findElement(By.xpath("//th[@scope='row']//a")).getText();
			if(app.startsWith("A-"))
				System.out.println("Approval record is created");
			else{
				System.out.println("Approval record is not created");
				Assert.assertTrue(false);
			}}
			else{
				try{
				String app= driver.findElement(By.xpath("//th[@scope='row']//a")).getText();
				if(app.startsWith("A-")){
					System.out.println("Approval record is created");
				Assert.assertTrue(false);}
				else{
					System.out.println("Approval record is not created");
				}}
				catch(Exception e){
					
				}
				
				}
			}
		
		driver.findElement(By.xpath("//*[@title='Files']"));
		driver.findElement(By.xpath("//*[@title='Agreements']"));
		String file= cp.getFiles().getAttribute("title");
		if(file.equals(primdoc)){
			System.out.println("File attached is :"+file);}
		else
		{
			System.out.println("File is not attached");
			Assert.assertTrue(false);
		}
		
		driver.quit();

	}
}