package Regression;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class createContract extends Base {
	

	@Test
	public void createContract() throws IOException, InterruptedException {
		

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
		Thread.sleep(5000);
		System.out.println("Opportunity Pagelayout values");
		// 1. Opportunity
		String oppname1 = op.getopportunityname().getText();
		System.out.println("opportunityname: " + oppname1);
		// 2. Account name
		String accname1 = op.getaccountname().getText();
		String accname1op="";  
		if (accname1.isEmpty()) {
			 accname1op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Account Name')]//parent::div)/following-sibling::div/span/slot/slot//a/slot/slot/span"))
					.getText();
			System.out.println("accountname: " + accname1op);
		} else{
			 accname1op = op.getaccountname().getText();
			System.out.println("accountname: " + accname1op);}
		
		// 3. Selling lane
		String sellinglane1 = op.getsellinglane().getText();
		System.out.println("sellinglane: " + sellinglane1);
		// 4. Entity
		String entity1 = op.getentity().getText();
		System.out.println("entity: " + entity1);
		// 5. MSA Date
		String msadate1 = op.getmsadate().getText();
		System.out.println("msadate: " + msadate1);
		// 6. Service Order date
		String servicedate1 = op.getservicedate().getText();
		System.out.println("servicedate: " + servicedate1);
		// 7. Quote
		String quote1 = op.getquote().getText();
		String quote1op ="";
		if (quote1.isEmpty()) {
			 quote1op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Primary Quote')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup/div//a/slot/slot/span"))
					.getText();
			System.out.println("quote: " + quote1op);
		} else{
			quote1op=quote1;
			System.out.println("quote: " + quote1op);}
		// 8. Contract term
		String term1 = op.getterm().getText();
		System.out.println("term: " + term1);
		// 9. primary Contact
		String primecontact1 = op.getprimecontact().getText();
		String primecontact1op ="";
		if (primecontact1.isEmpty()) {
			primecontact1op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Primary Contact')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup//a/slot/slot/span"))
					.getText();
			System.out.println("primarycontact: " + primecontact1op);
		} else{
			primecontact1op=primecontact1;
			System.out.println("primarycontact: " + primecontact1);}
		// 10 Currency
		String currency1 = op.getcurrency().getText();
		if (currency1.isEmpty()) {
			String currency1or = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Currency')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup//a/slot/slot/span"))
					.getText();
			System.out.println("currency: " + currency1or);
		} else
			System.out.println("currency: " + currency1);
		// 11.Billing contact
		String billcontact1 = op.getbillcontact().getText();
		String billcontact1op="";
		if (billcontact1.isEmpty()) {
			billcontact1op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Billing Contact')]//parent::div)/following-sibling::div/span")).getText();
			///slot/slot/force-lookup//a/slot/slot/span
			System.out.println("billingcontact: " + billcontact1op);
		} else{
			billcontact1op =billcontact1;
			System.out.println("billingcontact: " + billcontact1);}

		// code to calculate ACV TCV values
		// 1. Committed TCV = Total price
		Double TCV_committed = priceval(op.gettcvcommitted());
		System.out.println("TCV committed: " + TCV_committed);
		Double Total_price = priceval(driver.findElement(
				By.xpath("//*[@title='Reporting ACV Total']/following-sibling::p//lightning-formatted-text")));
		// Assert.assertEquals(TCV_committed,Total_price);
		// 2. Committed ACV = If term is > 12 (Total Price / Term * 12) else
		// Total Price
		Double ACV_committed = priceval(op.getACVcommitted());
		System.out.println("ACV committed: " + ACV_committed);
		Double Term = Double.parseDouble(op.getterm().getText());
		if (Term > 12) {
			Double Exp_ACV_committed = Math.round((Total_price / Term) * 12 * 100.0) / 100.0;
			// Assert.assertEquals(ACV_committed,Exp_ACV_committed);
		} else {
			Double Exp_ACV_committed = priceval(driver.findElement(
					By.xpath("//*[@title='Reporting ACV Total']/following-sibling::p//lightning-formatted-text")));
			// Assert.assertEquals(ACV_committed,Exp_ACV_committed);
		}
		// 4.NRR=OpportunityLineItem.NRR
		Double NRR = priceval(op.getNRR());
		System.out.println("NRR: " + NRR);
		// 3. MRR = If NRR <= 0 then total price / term
		Double MRR = priceval(op.getMRR());
		System.out.println("MRR: " + MRR);
		Double Exp_MRR = Math.round((Total_price / Term) * 100.0) / 100.0;
		// Assert.assertEquals(MRR,Exp_MRR);
		// 8.Usage ACV = Usage entered at quote/opportunity
		Double ACV_usage = priceval(op.getacvusage());
		System.out.println("ACV usage: " + ACV_usage);
		// 9.Usage TCV = (Usage entered at quote/opportunity / (# of products
		// where quantity > 0)) /12 * Term
		Double TCV_usage = priceval(op.gettcvusage());
		System.out.println("TCV usage: " + TCV_usage);
		// 6. Total TCV = Total Price + (Usage entered at quote/opportunity / (#
		// of products where quantity > 0)) /12 * Term
		Double TCV_total = priceval(op.gettcvtotal());
		System.out.println("TCV total: " + TCV_total);
		Double Exp_TCV_total = Total_price + TCV_usage;
		// Assert.assertEquals(TCV_total,Exp_TCV_total);
		// 7. Total ACV =If term is > 12 (Total Price / Term * 12) else Total
		// Price + Usage entered at quote/opportunity
		Double ACV_total = priceval(op.getACVtotal());
		System.out.println("ACV total: " + ACV_total);
		if (Term > 12) {
			Double Exp_ACV_total = Math.round((Total_price / Term) * 12 * 100.0) / 100.0;
			// Assert.assertEquals(ACV_total,Exp_ACV_total);
		} else {
			Double Exp_ACV_total = Total_price + ACV_usage;
			// Assert.assertEquals(ACV_total,Exp_ACV_total);
		}
		// 5. Incremental values = same as ACV , TCV ,MRR & NRR
		Double IncTCV_committed = priceval(op.getInctcvcommitted());
		Assert.assertEquals(IncTCV_committed, TCV_committed);
		Double IncACV_committed = priceval(op.getIncacvcommitted());
		Assert.assertEquals(IncACV_committed, ACV_committed);
		Double IncACV_usage = priceval(op.getIncacvusage());
		Assert.assertEquals(IncACV_usage, ACV_usage);
		Double IncTCV_usage = priceval(op.getInctcvusage());
		Assert.assertEquals(IncTCV_usage, TCV_usage);
		Double IncTCV_total = priceval(op.getIncacvtotal());
		Assert.assertEquals(IncTCV_total, TCV_total);
		Double IncACV_total = priceval(op.getIncacvtotal());
		Assert.assertEquals(IncACV_total, ACV_total);
		Double IncMRR = priceval(op.getIncMRR());
		Assert.assertEquals(IncMRR, MRR);
		Double IncNRR = priceval(op.getIncNRR());
		Assert.assertEquals(IncNRR, NRR);
		Thread.sleep(5000);
		waitForWebElementPresent(op.getselection());

		js.executeScript("arguments[0].click();", op.getselection());
		js.executeScript("arguments[0].click();", op.getmark());
		Thread.sleep(1000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")));
		String err2 = driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")).getText();
		if (!err2.contains("success")) {
			System.out.println(
					"Error displayed while trying to move to Selection stage without filling competitor field:-");
			System.out.println(err2);
			//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='Close']")));
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
		else {
			Assert.assertTrue(false);
		}
		
		Thread.sleep(7000);

		js.executeScript("arguments[0].click();", op.getcontracting());
		js.executeScript("arguments[0].click();", op.getmark());
		Thread.sleep(1000);
		//waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")));
		String err1 = driver.findElement(By.xpath("//*[@class='slds-align-middle slds-hyphenate']")).getText();
		System.out.println("Error displayed while trying to move to contracting stage without creating contract:-");
		System.out.println(err1);
		//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='Close']")));

		js.executeScript("arguments[0].click();", op.getcontract());
		Thread.sleep(10000);
		waitForWebElementPresent(op.getcontractsave());
		js.executeScript("arguments[0].click();", op.getcontractsave());
		Thread.sleep(20000);

		waitForWebElementPresent(op.getcontracting());
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
		
		/*
		 * String url=driver.getCurrentUrl(); if(url.contains("ltnstage")){
		 * js.executeScript("arguments[0].click();", op.getcontractlink());
		 * Thread.sleep(5000); } else js.executeScript("arguments[0].click();",
		 * op.getcontractlinkuat());
		 */
		try {
			js.executeScript("arguments[0].click();", op.getcontractlink());
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", op.getcontractlinkuat());
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		// code for contract page layout validations
		Thread.sleep(10000);
		System.out.println("Contract Pagelayout Values");
		// 1. Opportunity
		String oppname2 = cp.getopportunityname().getText();
		System.out.println("opportunityname: " + oppname2);
		// 2. Account name
		String accname2 = cp.getaccountname().getText();
		String accname2op="";
		if (accname2.isEmpty()) {
			 accname2op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Account Name')]//parent::div)/following-sibling::div/span/slot/slot//a/slot/slot/span"))
					.getText();
			System.out.println("accountname: " + accname2op);
		} else{
			accname2op=accname2;
		System.out.println("accountname: " + accname2);}
		// 3. Selling lane
		String sellinglane2 = cp.getsellinglane().getText();
		System.out.println("sellinglane: " + sellinglane2);
		// 4. Entity
		String entity2 = cp.getentity().getText();
		System.out.println("entity: " + entity2);
		// 5. MSA Date
		String msadate2 = cp.getmsadate().getText();
		System.out.println("msadate: " + msadate2);
		// 6. Service Order date
		String servicedate2 = cp.getservicedate().getText();
		System.out.println("servicedate: " + servicedate2);
		// 7. Quote
		String quote2 = cp.getquote().getText();
		String quote2op="";
		if (quote2.isEmpty()) {
			quote2op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Primary Quote')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup/div//a/slot/slot/span"))
					.getText();
			System.out.println("quote: " + quote2op);
		} else{
			quote2op =quote2;
		System.out.println("quote: " + quote2);}
		// 8. Contract term
		String term2 = cp.getterm().getText();
		System.out.println("term: " + term2);
		// 9. primary Contact
		String primecontact2 = cp.getprimecontact().getText();
		String primecontact2op ="";
		if (primecontact2.isEmpty()) {
		primecontact2op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Primary Contact')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup//a/slot/slot/span"))
					.getText();
			System.out.println("primarycontact: " + primecontact2op);
		} else{
		primecontact2op =primecontact2;
		System.out.println("primarycontact: " + primecontact2);}
		// 10.Billing contact
		String billcontact2 = cp.getbillcontact().getText();
		String billcontact2op = "";
		if (billcontact2.isEmpty()) {
			billcontact2op = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Billing Contact')]//parent::div)/following-sibling::div/span"))
					.getText();///slot/slot/force-lookup//a/slot/slot/span
			System.out.println("billingcontact: " + billcontact2op);
		} else{
			billcontact2op =billcontact2;
		
		System.out.println("billingcontact: " + billcontact2);
		}
				
		if (oppname2.equals(oppname1) && accname2op.equals(accname1op) && sellinglane2.equals(sellinglane1)
				&& entity2.equals(entity1) && msadate2.equals(msadate1) && servicedate2.equals(servicedate1)
				&& quote2op.equals(quote1op) && primecontact2op.equals(primecontact1op) && billcontact2op.equals(billcontact1op))
			System.out.println(
					"All Cross object values in Opportunity page are reflected correctly in Contract Pagelayout");
		else
			Assert.assertTrue(false);

		String stat = driver.findElement(By.xpath("//*[@title='Status']/following-sibling::div")).getText();
		System.out.println("Contract Status is:" + stat);
		// to be sent to data.prop file
		String curl = driver.getCurrentUrl();
		String id = driver
				.findElement(By
						.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span"))
				.getText();
		//----------------String id1="'"+id;-------------------
		System.out.println("Contractid is:" + id);
		System.out.println("Contracturl is:" + curl);
		
		
		FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
		sheet.getRow(107).createCell(1).setCellValue(id);
		sheet.getRow(108).createCell(1).setCellValue(curl);
		wb.write(fout);
		//wb.close();
		//prop.setProperty("contractid", id);
		//prop.setProperty("contracturl", curl);
//		try (final OutputStream outputstream = new FileOutputStream("src/main/java/Regression/data.properties");) {
//			prop.store(outputstream, "File Updated");
//			outputstream.close();
//		}
		Thread.sleep(5000);

		// code to calculate ACV TCV values
		Double NRR1 = priceval(cp.getNRR());
		Assert.assertEquals(NRR1, NRR);
		Double MRR1 = priceval(cp.getMRR());
		Assert.assertEquals(MRR1, MRR);
		Double ACV_usage1 = priceval(cp.getacvusage());
		Assert.assertEquals(ACV_usage1, ACV_usage);
		Double TCV_usage1 = priceval(cp.gettcvusage());
		Assert.assertEquals(TCV_usage1, TCV_usage);
		Double TCV_total1 = priceval(cp.gettcvtotal());
		Assert.assertEquals(TCV_total1, TCV_total);
		Double ACV_total1 = priceval(cp.gettcvtotal());
		Assert.assertEquals(ACV_total1, ACV_total);
		Double TCV_committed1 = priceval(cp.gettcvcommitted());
		Assert.assertEquals(TCV_committed1, TCV_committed);
		Double ACV_committed1 = priceval(cp.getacvcommitted());
		Assert.assertEquals(ACV_committed1, ACV_committed);
		System.out.println("TCV ACV values in Contract are in sync with values in Opportunity");

		System.out.println("Contract Deal Valuation Values");
		System.out.println("Contract - ACV Committed: " + cp.getaconacvcom().getText());
		System.out.println("Contract - TCV Committed: " + cp.getcontcvcom().getText());
		System.out.println("Contract - ACV Total: " + cp.getconacvtot().getText());
		System.out.println("Contract - TCV Total: " + cp.getcontcvtot().getText());
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
		Thread.sleep(10000);
		cp.getDetail().click();
		// driver.close();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[text()='Opportunity']/parent::div/div/div/div/a"))
				.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		driver.findElement(By.xpath("//*[text()='Quote']/parent::div/following-sibling::div/span/div/a"))
				.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));

		js.executeScript("window.scrollBy(0,-200)", "");
		cp.Related_Tab().click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Subscriptions']")).click();
		int Subscriptions_size = driver.findElements(By.xpath("//tbody/tr/td[5]/span/a")).size();
		ArrayList<String> Subscriptions = new ArrayList<String>();
		// Set<String> Subscriptions = new HashSet<String>();
		if (Subscriptions_size >= 1) {
			for (int i = 1; i <= Subscriptions_size; i++) {
				Subscriptions.add(driver.findElement(By.xpath("(//tbody/tr/td[5]/span/a)[" + i + "]")).getText());
			}
			System.out.print("SubscriptionLines : ");
			// System.out.println(Subscriptions);

		} else {
			System.out.println("No Subscription Records");
			Assert.assertTrue(false);
		}
		List SubscriptionLines = new ArrayList();
		SubscriptionLines = Subscriptions.stream().sorted().collect(Collectors.toList());
		System.out.println(SubscriptionLines);

		Thread.sleep(5000);

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String Contract = it.next();
		String Quote = it.next();
		String Opportunity = it.next();

		driver.switchTo().window(Opportunity);
		driver.findElement(By.xpath("//*[text()='Related']")).click();
		Thread.sleep(10000);
		js.executeScript("window.scrollBy(0,100)", "");
		Actions act = new Actions(driver);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
				.click();
		act.moveByOffset(10, 0).build().perform();
		Thread.sleep(10000);
		try{
		driver.findElement(By.xpath("//span[contains(text(),'Products')]//parent::a")).click();}
		catch(Exception e){
			driver.findElement(By.xpath("//span[contains(text(),'Products')]/parent::slot/parent::a")).click();
		}

		int Products_size = driver.findElements(By.xpath("//tbody/tr/th/span/a")).size();
		List<String> Products = new ArrayList<String>();
		// Set<String> Products = new HashSet<String>();
		if (Products_size >= 1) {
			for (int i = 1; i <= Products_size; i++) {
				Products.add(driver.findElement(By.xpath("(//tbody/tr/th/span/a)[" + i + "]")).getText());
			}
			System.out.print("ProductLines : ");
			// System.out.println(Products);
		} else {
			System.out.println("No Product Records");
			Assert.assertTrue(false);
		}
		List ProductLines = new ArrayList();
		ProductLines = Products.stream().sorted().collect(Collectors.toList());
		System.out.println(ProductLines);

		Thread.sleep(5000);

		driver.switchTo().window(Quote);
		driver.findElement(By.xpath("//*[text()='Related']")).click();
		driver.findElement(By.xpath("//span[text()='Quote Lines']")).click();

		int QuoteLines_size = driver.findElements(By.xpath("//tbody/tr/td[2]/span/a")).size();
		ArrayList<String> Quote_Lines = new ArrayList<String>();
		// Set<String> QuoteLines = new HashSet<String>();
		if (QuoteLines_size >= 1) {
			for (int i = 1; i <= QuoteLines_size; i++) {
				Quote_Lines.add(driver.findElement(By.xpath("(//tbody/tr/td[2]/span/a)[" + i + "]")).getText());
			}
			System.out.print("QuoteLines : ");
			// System.out.println(Quote_Lines);
		} else {
			System.out.println("No QuoteLine Records");
			Assert.assertTrue(false);
		}
		List QuoteLines = new ArrayList();
		QuoteLines = Quote_Lines.stream().sorted().collect(Collectors.toList());
		System.out.println(QuoteLines);

		Thread.sleep(5000);

		boolean Contract_To_Opportunity = SubscriptionLines.equals(ProductLines);
		System.out.println(Contract_To_Opportunity);

		boolean Contract_To_Quote = SubscriptionLines.equals(QuoteLines);
		System.out.println(Contract_To_Quote);

		if (Contract_To_Opportunity == true && Contract_To_Quote == true) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		driver.quit();

	}

	public Double priceval(WebElement element) {
		String price = element.getText().substring(4);
		String pv = price.replace(",", "");
		Double pricevalue = Double.parseDouble(pv);
		return pricevalue;
	}
}