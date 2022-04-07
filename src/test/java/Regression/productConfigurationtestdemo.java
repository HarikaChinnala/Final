package Regression;

import java.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.lang.String;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.createOppPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Pageobjects.oppPage;
import Pageobjects.quoteLineEditor;
import Pageobjects.reusablemethods;
import Regression.Base;

public class productConfigurationtestdemo extends Base {

	public String startdate1;
	public String expenddate;
	public String termstrtdate;
	public String stDate = "";
	public String[] array;
	public String[] subarray;

	public productConfigurationtestdemo(WebDriver driver) {
		this.driver = driver;

	}

	public WebDriver proconfiguration(String sellinglane, String productname, String freeperiod, Date Termstartdate,
			double Subscriptionterm, Date startdate, double discount, String discountoption, String subproducts,
			String subsubproducts, double scopeofrecords, String billingoption, String billingfrequency,
			double CustomersTNOwnership, String Maxseats, String interfaceType, double OCN, String solutiondescription,
			double threshold, double monthlyminfee, double monthlyminlistvol, String nameofsubclient,
			double additionalFileSliceCriteria, String ActiveStatuscb, String HandsetStatuscb,
			String RoamingIdentifierscb, String SMSEnabledcb, String InternationalRevShareFraudIdentifiercb,
			String CFCAorGSMAMembercb, String billingoptionBRMmonthly, String billingfrequencyBRMmonthly,
			String billingoptionBRMyearlyservice, String billingfrequencyBRMyearlyservice,
			String billingoptionBRMyearlylist, String billingfrequencyBRMyearlylist, String billingoptionUSIYP,
			String billingfrequencyUSIYP, String billingoptionUSEBR, String billingfrequencyUSEBR,
			String billingoptionUSAPI, String billingfrequencyUSAPI, String billingoptionCanadaIYP,
			String billingfrequencyCanadaIYP, String datadeliveryfrequencyUSIYP, String datadeliveryfrequencyUSEBR,
			String datadeliveryfrequencycanadaIYP, String existingclient, double NumbofPlatform, double NumbofAudience,
			String geography, String fileattributes, double frequency, String feedtype, String impressions,
			String platformdescription, String audiencedescription, String unlimitedimpressions, double numbofDMA,
			double numbofadvisoryservicehrs, String listofDMA) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		quoteLineEditor ql = new quoteLineEditor(driver);
		createQuotePage qp = new createQuotePage(driver);
		Thread.sleep(15000);
		// waitForWebElementPresent(driver.findElement(By.xpath("//*[@name='Add
		// Products']/paper-button")));
		
	
		int x = driver.findElements(By.xpath("//iframe")).size();
		for (int i = 0; i < x; i++) {
			driver.switchTo().frame(i);
			try {
				js.executeScript("arguments[0].click();", ql.getaddproducts());
			}

			catch (Exception e) {

				driver.switchTo().defaultContent();
			}
		}
		Thread.sleep(5000);
		
		
		// String sl=driver.findElement(By.xpath("//*[text()='Product
		// Catalog']/following-sibling::div//sb-select")).getText();
		// System.out.println(sl);
		driver.findElement(By.xpath("//*[text()='Product Catalog']/following-sibling::div//sb-select/select")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[text()='Product Catalog']/following-sibling::div//sb-select/select/option[@title='"
						+ sellinglane + "']"))
				.click();
		driver.findElement(By.xpath("//*[@id='suggest']/*[text()='Suggest']")).click();

		// driver.findElement(By.xpath("(//i[@class='sf-icon-close style-scope
		// sb-dialog'])[3]")).click();
		Thread.sleep(5000);
		int r = driver.findElements(By.xpath("//*[@id='rowDiv']")).size();
		// System.out.println(r);
		for (int rs = 0; rs < r; rs++) {
			String row = driver.findElement(By.xpath("//*[@id='rowDiv']")).getText();
			String[] rows = row.split("\n");
			if (!rows[0].equals(sellinglane))
				Assert.assertTrue(false);
		}
		driver.switchTo().defaultContent();
		// Thread.sleep(3000);
		// Actions act = new Actions(driver);
		Thread.sleep(5000);

		int j = driver.findElements(By.xpath("//iframe")).size();
		for (int a = 0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				driver.findElement(By.xpath("(//*[@id='itemLabel'])[2]")).sendKeys(productname);
				driver.findElement(By.xpath("(//*[@id='itemLabel'])[2]")).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='" + productname
						+ "']/sb-swipe-container/div/div/sb-group/div/div/sb-table-cell-select/div/paper-checkbox")));

			} catch (Exception e) {
				driver.switchTo().defaultContent();
			}

		}

		driver.findElement(By.xpath("//*[text()='Select']")).click();
		Thread.sleep(15000);

		if (!subproducts.isEmpty())
			array = subproducts.split(";");
		if (!subsubproducts.isEmpty())
			subarray = subsubproducts.split(";");
		if (sellinglane.equals("Risk")) {
			if (productname.equals("Contact Compliance Risk")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {
							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}

					}
				}

			} else if (productname.equals("Contact Tracing Solutions")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} else if (productname.equals("Digital Identity Risk Flex")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} else if (productname.equals("Digital Identity Risk Pro")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}
			} else if (productname.equals("Domestic Phone Takeover Risk")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}
			} else if (productname.equals("EveryoneAPI")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);
			} else if (productname.equals("Inbound Authentication")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				// js.executeScript("arguments[0].value='5';",
				// driver.findElement(By.xpath("//*[@label='ACV
				// Usage']/div/div/sb-field/span/div/sb-input")));

				select(subproducts);
			} else if (productname.equals("Initial Account Database Processing - Authentication")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}
			} else if (productname.equals("Initial Account Database Processing - Compliance")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}
			} else if (productname.equals("Initial Account Database Processing - Customer Operations")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}
			} else if (productname.equals("International Phone Takeover Risk")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} else if (productname.equals("Insight Engine - SmartDial")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} else if (productname.equals("iTact")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				select(subproducts);
				// driver.findElement(By.xpath("//*[@name='Overage
				// Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("iTact (per seat pricing)")) {
				select(subproducts);
			} else if (productname.equals("Phone Behavior Intelligence")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Proactive Change Notification Monitoring")) {

							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification Monitoring']/sb-swipe-container/div/div[2]/sb-actions"))
									.click();
							js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
									.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
					}
				}

			} else if (productname.equals("Risk - Custom Solution - Authentication")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);
			} else if (productname.equals("Risk - Custom Solution - Compliance")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);
			} else if (productname.equals("Risk - Custom Solution - Customer Operations")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);
			} else if (productname.equals("SmartTrace")) {
				// driver.findElement(By.xpath("//*[@label='Scope of Records
				// (Millions)']/div/div/sb-field/span/div/sb-input")).click();
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				Thread.sleep(2000);
				if (billingoption.equals("Custom Billing"))
					driver.findElement(By.xpath(
							"(//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "'])[2]"))
							.click();
				else
					driver.findElement(By
							.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
							.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);
			}

		}
		// Marketing products
		else if (sellinglane.equals("Marketing")) {
			if (productname.equals("AdAdvisor"))

			{
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("AdAdvisor Platform")) {
							driver.findElement(By
									.xpath("//*[@name='AdAdvisor Platform']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();

							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}

						if (element.equals("AdAdvisor Subscription")) {
							driver.findElement(By
									.xpath("//*[@name='AdAdvisor Subscription']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							driver.findElement(
									By.xpath("//*[@label='Number of Platforms']/div/div/sb-field/span/div/sb-input"))
									.sendKeys(String.valueOf(NumbofPlatform));
							driver.findElement(
									By.xpath("//*[@label='Number of Audiences']/div/div/sb-field/span/div/sb-input"))
									.sendKeys(String.valueOf(NumbofAudience));

							driver.findElement(By.xpath("//*[@label='Impressions']/div/div/sb-field/span/div/sb-input"))
									.sendKeys(impressions);
							driver.findElement(
									By.xpath("//*[@label='Platform Description']/div/div//*[@id='textarea']"))
									.sendKeys(platformdescription);
							driver.findElement(
									By.xpath("//*[@label='Audience Description']/div/div//*[@id='textarea']"))
									.sendKeys(audiencedescription);

							if (unlimitedimpressions.equals("yes"))
								driver.findElement(
										By.xpath("//*[@label='Unlimited Impressions']/div/div//*[@id='checkbox']"))
										.click();

							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}

					}
				}

			} else if (productname.equals("Customer Identity File (CIF)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Geography']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + geography + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='File Attributes']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + fileattributes + "']"))
						.click();

				select(subproducts);

			} else if (productname.equals("Customer Identity Management (CIM)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				// driver.findElement(By
				// .xpath("//*[@id='swipe-header']//*[@class='td sb-action-icon
				// sf-icon-custom_apps --desktop style-scope sb-actions']"))
				// .click();
				Thread.sleep(10000);
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Initial Database Processing (Up Front Scrub)")) {
							driver.findElement(By
									.xpath("//*[@name='Initial Database Processing (Up Front Scrub)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}

								driver.findElement(By
										.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input"))
										.sendKeys(String.valueOf(scopeofrecords));
								driver.findElement(By
										.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
										.click();
								driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
										+ frequency + "']")).click();
							}

							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);
						}
						if (element.equals("Periodic Database Refresh")) {
							driver.findElement(By
									.xpath("//*[@name='Periodic Database Refresh']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}

								js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
										.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
								driver.findElement(By
										.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
										.click();
								driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
										+ frequency + "']")).click();
							}

							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("Proactive Change Notification")) {
							driver.findElement(By
									.xpath("//*[@name='Proactive Change Notification']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}

								js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
										.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
								driver.findElement(By
										.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
										.click();
								driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
										+ frequency + "']")).click();

							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("New / Incremental Records")) {
							driver.findElement(By
									.xpath("//*[@name='New / Incremental Records']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}

								js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(By
										.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
								driver.findElement(By
										.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
										.click();
								driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
										+ frequency + "']")).click();

							}

							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
					}
				}

			} else if (productname.equals("ElementOne Analytics")) {

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("e1x Platform")) {
							WebElement sp = driver.findElement(
									By.xpath("(//*[@name='" + element + "']//sb-group[@id='selection'])[3]"));
							if (!sp.isSelected())
								sp.click();

						}
						if (element.equals("Customer Targeting Analysis")) {
							WebElement sp = driver.findElement(
									By.xpath("(//*[@name='" + element + "']//sb-group[@id='selection'])[2]"));
							if (!sp.isSelected())
								sp.click();

						}
						if (element.equals("On-Site Training (4hr session)")) {

							driver.findElement(By
									.xpath("//*[@name='On-Site Training (4hr session)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {

									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}
						if (element.equals("Mobile Location Intelligence - One Time Fee")
								|| element.equals("Mobile Location Intelligence - Monthly Fee")) {
							driver.findElement(
									By.xpath("//*[@label='Number of DMAs']/div/div/sb-field/span/div/sb-input"))
									.sendKeys(String.valueOf(numbofDMA));
							driver.findElement(By
									.xpath("//*[@label='Number of Advisory Service Hours']/div/div/sb-field/span/div/sb-input"))
									.sendKeys(String.valueOf(numbofadvisoryservicehrs));
							driver.findElement(By.xpath("//*[@label='List of DMAs']//*[@id='textarea']"))
									.sendKeys(listofDMA);

						}

					}
				}
			} else if (productname.equals("Financial Spectrum")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} else if (productname.equals("Identity Data Management Platform (IDMP)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);

						if (element.equals("1st Party Brands / Websites / Domains")) {
							WebElement p = driver.findElement(By.xpath(
									"//*[@name='1st Party Brands  / Websites / Domains']//sb-group[@id='selection']"));
							if (!p.isSelected())
								p.click();

							Thread.sleep(5000);
						} else {
							WebElement p = driver
									.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
							if (!p.isSelected())
								p.click();

							Thread.sleep(5000);
						}
					}
				}

				Thread.sleep(10000);
			} else if (productname.equals("Omni Channel Remarketing")) {
				/*
				 * driver.findElement(By
				 * .xpath("//*[@label='Use Case']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"
				 * )) .click(); driver.findElement( By.
				 * xpath("//option[@class='sbOption style-scope sb-select'][@title='Direct to Advertiser']"
				 * )) .click();
				 */
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);

						if (element.equals("Pilot")) {
							WebElement p = driver
									.findElement(By.xpath("(//*[@name='Pilot']//sb-group[@id='selection'])[2]"));
							if (!p.isSelected())
								p.click();
							// Thread.sleep(5000);
						} else {
							WebElement p = driver
									.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
							if (!p.isSelected())
								p.click();
							// Thread.sleep(5000);
						}
					}
				}

				Thread.sleep(10000);
			} else if (productname.equals("Onboarding")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Package A (Up to 20M Input Records / Month)")) {
							driver.findElement(By
									.xpath("//*[@name='Package A (Up to 20M Input Records / Month)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									if (ele.equals("Data Feeds (Per Month)")
											|| ele.equals("Campaign Audience Lists (Per Month)")
											|| ele.equals("Standard Activation Platforms (Per Month)")) {
										WebElement sp = driver.findElement(
												By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("Package B (20M - 50M Input Records / Month)")) {
							driver.findElement(By
									.xpath("//*[@name='Package B (20M - 50M Input Records / Month)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									if (ele.equals("Data Feeds (Per Month)")
											|| ele.equals("Campaign Audience Lists (Per Month)")
											|| ele.equals("Standard Activation Platforms (Per Month)")) {
										WebElement sp = driver.findElement(
												By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("Package C (50M - 100M Input Records / Month)")) {
							driver.findElement(By
									.xpath("//*[@name='Package C (50M - 100M Input Records / Month)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									if (ele.equals("Data Feeds (Per Month)")
											|| ele.equals("Campaign Audience Lists (Per Month)")
											|| ele.equals("Standard Activation Platforms (Per Month)")) {
										WebElement sp = driver.findElement(
												By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}

							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}

					}
				}
			} else if (productname.equals("Other Identity Data")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Combo")) {
							driver.findElement(By
									.xpath("//*[@name='Combo']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(5000);
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();

						}
						if (element.equals("Directory Listings (NDA, DLP)")) {
							driver.findElement(By
									.xpath("//*[@name='Directory Listings (NDA, DLP)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							driver.findElement(By
									.xpath("//*[@label='Feed Type']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(By.xpath(
									"//option[@class='sbOption style-scope sb-select'][@title='" + feedtype + "']"))
									.click();
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
						}

					}
				}
			} else if (productname.equals("RealTime Identity")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				driver.findElement(By
						.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + frequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				select(subproducts);

			} else if (productname.equals("Realtime Website Personalizations")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Package A (Up to 12M Queries / Year)")) {
							driver.findElement(By
									.xpath("//*[@name='Package A (Up to 12M Queries / Year)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									if (ele.equals("1st Party Brands / Websites / Domains")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[@name='1st Party Brands  / Websites / Domains']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();
									}

									else {
										WebElement sp = driver.findElement(
												By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();
									}
								}

							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("Package B (Up to 36M Queries / Year)")) {
							driver.findElement(By
									.xpath("//*[@name='Package B (Up to 36M Queries / Year)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									if (ele.equals("1st Party Brands / Websites / Domains")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[@name='1st Party Brands  / Websites / Domains']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();
									}

									else {
										WebElement sp = driver.findElement(
												By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
										if (!sp.isSelected())
											sp.click();
									}
								}

							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}

					}
				}
			} else if (productname.equals("Unified Analytics - MMM")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						// System.out.println(element);
						try {
							driver.findElement(By.xpath("//*[@label='Configurations']")).click();
							if (element.equals("Touchpoints")) {
								WebElement sp = driver.findElement(
										By.xpath("(//*[@name='" + element + "']//sb-group[@id='selection'])[2]"));
								if (!sp.isSelected())
									sp.click();

							} else {
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							}
						} catch (Exception e) {
							try {

								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e1) {

								driver.findElement(By.xpath("//*[@label='Other']")).click();
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							}

						}
					}
				}

			} else if (productname.equals("Unified Analytics - MTA")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				if (!subproducts.isEmpty()) {
					for (String element : array) {

						try {
							driver.findElement(By.xpath("//*[@label='Configurations']")).click();
							WebElement sp = driver
									.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
							if (!sp.isSelected())
								sp.click();

						} catch (Exception e) {
							try {

								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e1) {

								driver.findElement(By.xpath("//*[@label='Other']")).click();
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							}

						}
					}
				}
			}
		} else if (sellinglane.equals("Communications")) {
			// communication products

			if (productname.equals("Branded Call Display")) {

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				select(subproducts);

			} 
			
			else if (productname.equals("CNO/BCD Combo")) {
				
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						if(element.equals("Branded Call Display")){

							// Branded Call Display
							driver.findElement(By
									.xpath("//*[@name='Branded Call Display']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									WebElement p = driver.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!p.isSelected())
										p.click();
								}
							}

							driver.findElement(By
									.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(
									By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
									.click();

							driver.findElement(By
									.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(By
									.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
									.click();
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

							Thread.sleep(10000);	
						}
		 if (element.equals("Caller Name Optimization")){

				// Caller Name Optimization
				driver.findElement(By
						.xpath("//*[@name='Caller Name Optimization']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(3000);
				int elementin = Arrays.asList(array).indexOf(element);
				String subelement = subarray[elementin];
				String xin[] = subelement.split("__");
				if (!subelement.isEmpty()) {
					for (String ele : xin) {
						WebElement p = driver.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
					}
				}

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
		 }
					}}
			} else if (productname.equals("CC/RM Combo")) {
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						if(element.equals("Certified Caller")){

				// Certified Caller
				driver.findElement(By
						.xpath("//*[@name='Certified Caller']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
		Thread.sleep(3000);
		int elementin = Arrays.asList(array).indexOf(element);
		String subelement = subarray[elementin];
		String xin[] = subelement.split("__");
		if (!subelement.isEmpty()) {
			for (String ele : xin) {
						
						if (!subelement.isEmpty()) {
							if(ele.contains("--")){
							String in[] = ele.split("--");
							String zin[]= in[1].split("#");
							WebElement sp1 = driver.findElement(
									By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
							if (!sp1.isSelected())
								sp1.click();
							if (ele.contains("Standalone (Premises) - Perpetual")) {
								driver.findElement(By
										.xpath("//*[@name='Standalone (Premises) - Perpetual']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
										.click();
								Thread.sleep(3000);
							
							if (!in[1].isEmpty()) {
								for (String Z : zin) {

									WebElement sp = driver
											.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();

							Thread.sleep(10000);

						

						}

					
							else if (ele.contains("Standalone (Premises) - SaaS")) {
						driver.findElement(By
								.xpath("//*[@name='Standalone (Premises) - SaaS']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);	if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
										driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
							else if (ele.contains("Hosted (Managed)")) {
						driver.findElement(By
								.xpath("//*[@name='Hosted (Managed)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();

						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
							else if (ele.contains("Software Development Kit")) {
						driver.findElement(By
								.xpath("//*[@name='Software Development Kit']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
							else if (ele.contains("Neustar Certificate Manager")) {
						driver.findElement(By
								.xpath("//*[@name='Neustar Certificate Manager']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
							else if (ele.contains("Subscriber Data and Policy Repository")) {
						driver.findElement(By
								.xpath("//*[@name='Subscriber Data and Policy Repository']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(5000);

					}
							}
							else{
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
				}
			}}
		driver.findElement(By
				.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
				.click();
		driver.findElement(By
				.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
				.click();
		driver.findElement(By
				.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
				.click();
		driver.findElement(
				By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
				.click();
		driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

		Thread.sleep(10000);	
						}
						if(element.equals("Robocall Mitigation")){

				// Robocall Mitigation
				driver.findElement(By
						.xpath("//*[@name='Robocall Mitigation']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();

				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				int elementin = Arrays.asList(array).indexOf(element);
				String subelement = subarray[elementin];
				String xin[] = subelement.split("__");
			
				if (!subelement.isEmpty()) {
					for (String ele : xin) {
						
								if (!subelement.isEmpty()) {
									if(ele.contains("--")){
									String in[] = ele.split("--");
									String zin[]= in[1].split("#");
									
					if (ele.contains("Subscriber Data and Policy Repository")) {
						WebElement sp1 = driver.findElement(
								By.xpath("(//*[@name='" + in[0] + "']//sb-group[@id='selection'])[2]"));
						if (!sp1.isSelected())
							sp1.click();
						driver.findElement(By
								.xpath("//*[@name='Subscriber Data and Policy Repository']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					else{
						WebElement sp1 = driver.findElement(
								By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
						if (!sp1.isSelected())
							sp1.click();
					}
									}
							else{
								if(ele.equals("Robocall Mitigation Dashboard")){
								WebElement sp = driver.findElement(
										By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
								if (!sp.isSelected())
									sp.click();
								}
								else{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									
								}
							}

				// driver.findElement(By.xpath("//*[@class='style-scope
				// sb-product-config'][text()='Save']")).click();

			}}}
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

				Thread.sleep(10000);	
				
						}}}}

			else if (productname.equals("CNO/BCD/ECC Combo")) {
				
				if (!subproducts.isEmpty()) {
					for (String element : array) {
						if(element.equals("Branded Call Display")){

							// Branded Call Display
							driver.findElement(By
									.xpath("//*[@name='Branded Call Display']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(3000);
							int elementin = Arrays.asList(array).indexOf(element);
							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									WebElement p = driver.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!p.isSelected())
										p.click();
								}
							}

							driver.findElement(By
									.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(
									By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
									.click();

							driver.findElement(By
									.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(By
									.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
									.click();
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

							Thread.sleep(10000);	
						}
		 if (element.equals("Caller Name Optimization")){

				// Caller Name Optimization
				driver.findElement(By
						.xpath("//*[@name='Caller Name Optimization']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(3000);
				int elementin = Arrays.asList(array).indexOf(element);
				String subelement = subarray[elementin];
				String xin[] = subelement.split("__");
				if (!subelement.isEmpty()) {
					for (String ele : xin) {
						WebElement p = driver.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
					}
				}

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
		 }
		 
		 if (element.equals("Enterprise Certified Caller")){
			 driver.findElement(By
						.xpath("//*[@name='Enterprise Certified Caller']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
			 Thread.sleep(3000);

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				int elementin = Arrays.asList(array).indexOf(element);
				String subelement = subarray[elementin];
				String xin[] = subelement.split("__");
			
				if (!subelement.isEmpty()) {
					for (String ele : xin) {
						
								if (!subelement.isEmpty()) {
									if(ele.contains("--")){
									String in[] = ele.split("--");
									String zin[]= in[1].split("#");
									
					if (ele.contains("Subscriber Data and Policy Repository")) {
						WebElement sp1 = driver.findElement(
								By.xpath("(//*[@name='" + in[0] + "']//sb-group[@id='selection'])[2]"));
						if (!sp1.isSelected())
							sp1.click();
						driver.findElement(By
								.xpath("//*[@name='Subscriber Data and Policy Repository']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						if (!in[1].isEmpty()) {
							for (String Z : zin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(5000);

					}
					else{
						
						WebElement sp1 = driver.findElement(
								By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
						if (!sp1.isSelected())
							sp1.click();
						}
					}
							else{
								WebElement sp = driver.findElement(
										By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}

		// driver.findElement(By.xpath("//*[@class='style-scope
		// sb-product-config'][text()='Save']")).click();

		}
						}

					}
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);
				}

				
				// driver.findElement(By.xpath("//*[@class='style-scope
				// sb-product-config'][text()='Save']")).click();

			
		 }
		 
		 
					}}
			

			else if (productname.equals("Caller ID (CNAM)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
						if (element.equals("Storage and Delivery")) {
							driver.findElement(By
									.xpath("//*[@name='Storage and Delivery']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(5000);
							int elementin = Arrays.asList(array).indexOf(element);

							String subelement = subarray[elementin];
							String xin[] = subelement.split("__");
							if (!subelement.isEmpty()) {
								for (String ele : xin) {
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);

						}
						if (element.equals("Toll Free Storage and Delivery")) {
							driver.findElement(By
									.xpath("//*[@name='Toll Free Storage and Delivery']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(5000);
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);
						}

					}
					// driver.findElement(By.xpath("//*[@class='style-scope
					// sb-product-config'][text()='Save']")).click();
					Thread.sleep(10000);
				}
			}

			else if (productname.equals("Caller Name Optimization")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				select(subproducts);

			}

			else if (productname.equals("Certified Caller")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Standalone (Premises) - Perpetual")) {
						driver.findElement(By
								.xpath("//*[@name='Standalone (Premises) - Perpetual']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Standalone (Premises) - SaaS")) {
						driver.findElement(By
								.xpath("//*[@name='Standalone (Premises) - SaaS']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);

						Thread.sleep(3000);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Hosted (Managed)")) {
						driver.findElement(By
								.xpath("//*[@name='Hosted (Managed)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();

						int elementin = Arrays.asList(array).indexOf(element);

						Thread.sleep(3000);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Software Development Kit")) {
						driver.findElement(By
								.xpath("//*[@name='Software Development Kit']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();

						int elementin = Arrays.asList(array).indexOf(element);

						Thread.sleep(3000);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Neustar Certificate Manager")) {
						driver.findElement(By
								.xpath("//*[@name='Neustar Certificate Manager']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);

						Thread.sleep(3000);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Subscriber Data and Policy Repository")) {
						driver.findElement(By
								.xpath("//*[@name='Subscriber Data and Policy Repository']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);

						Thread.sleep(3000);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}

				}

			}

			else if (productname.equals("Communications Professional Services")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				// driver.findElement(By.xpath("//*[@name='e911
				// Audit']//sb-group[@id='selection']")).click();

				if (!subproducts.isEmpty()) {
					for (String element : array) {
						System.out.println(element);
						try {
							driver.findElement(By.xpath("//*[@label='Audits']")).click();
							WebElement p = driver
									.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
							Thread.sleep(1000);
							if (!p.isSelected())
								p.click();
						} catch (Exception e) {
							try {
								driver.findElement(By.xpath("//*[@label='Bulk Order Processing']")).click();
								WebElement p = driver.findElement(
										By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
								Thread.sleep(1000);
								if (!p.isSelected())
									p.click();
							} catch (Exception e1) {
								try {
									driver.findElement(By.xpath("//*[@label='Consulting']")).click();
									WebElement p = driver.findElement(
											By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
									Thread.sleep(1000);
									if (!p.isSelected())
										p.click();
								} catch (Exception e2) {
									try {
										driver.findElement(By.xpath("//*[@label='Staff & Training']")).click();
										WebElement p = driver.findElement(
												By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
										Thread.sleep(1000);
										if (!p.isSelected())
											p.click();
									} catch (Exception e3) {
										try {
											driver.findElement(By.xpath("//*[@label='Support Services']")).click();
											WebElement p = driver.findElement(By
													.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
											Thread.sleep(1000);
											if (!p.isSelected())
												p.click();
										} catch (Exception e4) {
											try {
												driver.findElement(
														By.xpath("//*[@label='Service Delivery / Deployment']"))
														.click();
												// try{
												WebElement p = driver.findElement(By.xpath(
														"//*[@name='" + element + "']//sb-group[@id='selection']"));
												Thread.sleep(1000);
												if (!p.isSelected())
													p.click();
												/*
												 * } catch(Exception e6){
												 * js.executeScript(
												 * "window.scrollBy(0,1000)");
												 * WebElement p =
												 * driver.findElement(By.xpath(
												 * "//*[@name='" + element +
												 * "']//sb-group[@id='selection']"
												 * )); Thread.sleep(1000); if
												 * (!p.isSelected()) p.click();
												 * 
												 * }
												 */

											} catch (Exception e5) {
												driver.findElement(By.xpath("//*[@label='Other Options']")).click();
												WebElement p = driver.findElement(By.xpath(
														"//*[@name='" + element + "']//sb-group[@id='selection']"));
												Thread.sleep(1000);
												if (!p.isSelected())
													p.click();
											}

										}
									}
								}
							}
						}
					}
				}
			}

			else if (productname.equals("Directory Listings")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Directory Listings - Band 1")) {
						driver.findElement(By
								.xpath("//*[@name='Directory Listings - Band 1']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Directory Listings - Band 2")) {
						driver.findElement(By
								.xpath("//*[@name='Directory Listings - Band 2']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Directory Listings - Band 3")) {
						driver.findElement(By
								.xpath("//*[@name='Directory Listings - Band 3']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Directory Listings - Band 4")) {
						driver.findElement(By
								.xpath("//*[@name='Directory Listings - Band 4']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Directory Listings - Band 5")) {
						driver.findElement(By
								.xpath("//*[@name='Directory Listings - Band 5']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
				}

			}

			else if (productname.equals("Enterprise Certified Caller")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Subscriber Data and Policy Repository")) {
						for (String subelement : subarray) {
							WebElement sp = driver.findElement(
									By.xpath("//*[@name='" + subelement + "']//sb-group[@id='selection']"));
							if (!sp.isSelected())
								sp.click();
							driver.findElement(By
									.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
									.click();
							driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
									+ billingfrequency + "']")).click();
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);
						}

					}
				}
			}

			else if (productname.equals("Global Numbering Intelligence Services (GNIS)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Global Number Porting History Service")) {
						driver.findElement(By
								.xpath("//*[@name='Global Number Porting History Service']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Number Identifiers Service")) {
						driver.findElement(By
								.xpath("//*[@name='Number Identifiers Service']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						if (InternationalRevShareFraudIdentifiercb.equals("yes"))
							driver.findElement(
									By.xpath("//*[@label='International Rev Share Fraud Identifier']//paper-checkbox"))
									.click();
						if (CFCAorGSMAMembercb.equals("yes"))
							driver.findElement(By.xpath("//*[@label='CFCA or GSMA Member']//paper-checkbox")).click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Real-Time Identifiers Service")) {
						driver.findElement(By
								.xpath("//*[@name='Real-Time Identifiers Service']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						if (ActiveStatuscb.equals("yes"))
							driver.findElement(By.xpath("//*[@label='Active Status']//paper-checkbox")).click();

						if (HandsetStatuscb.equals("yes"))
							driver.findElement(By.xpath("//*[@label='Handset Status']//paper-checkbox")).click();

						if (RoamingIdentifierscb.equals("yes"))
							driver.findElement(By.xpath("//*[@label='Roaming Identifiers']//paper-checkbox")).click();

						if (SMSEnabledcb.equals("yes"))
							driver.findElement(By.xpath("//*[@label='SMS  Enabled']//paper-checkbox")).click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
				}
			}

			else if (productname.equals("Hosted OMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("ASR Receive")) {
						driver.findElement(By
								.xpath("//*[@name='ASR Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(5000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(5000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]= y[1].split("#");
										if (y[0].equals("ASR Receive Order")) {
												for (String Z : zin) {

													WebElement sp = driver
															.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
													if (!sp.isSelected())
														sp.click();
												}
											
										} else if (y[0].equals("ASR Receive PreOrder")) {
											
												for (String Z : zin) {

													WebElement sp = driver
															.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
													if (!sp.isSelected())
														sp.click();
												}
											
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
				//	if (element.equals("ESR Commercial")) {}
					if (element.equals("CARE")) {
						driver.findElement(By
								.xpath("//*[@name='CARE']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

						Thread.sleep(10000);

					}
					if (element.contains("ESR ")) {
						
						if(element.equals("ESR Resi/SMB")){
						driver.findElement(By
								.xpath("//*[@name='ESR Resi/SMB']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);}
						else if(element.equals("ESR Commercial")){
							driver.findElement(By
									.xpath("//*[@name='ESR Commercial']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(5000);
							
						}
						else if(element.equals("ESR Wireless")){
							driver.findElement(By
									.xpath("//*[@name='ESR Wireless']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
							Thread.sleep(5000);
						}
						
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(5000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {
									try {
										driver.findElement(By.xpath("//*[@label='Transactions']")).click();
										Thread.sleep(5000);
										if(ele.contains("--")){
											//int elein = Arrays.asList(subarray).indexOf(ele);
											//String subsubelement = subarray[elein];
											String in[] = ele.split("--");
											String zin[]= in[1].split("#");
											WebElement sp1 = driver.findElement(
													By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
											if (!sp1.isSelected())
												sp1.click();
			
										if (ele.contains("CARE")) {

											driver.findElement(By
													.xpath("//*[@name='CARE']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											
											if (!in[1].isEmpty()) {
												for (String Z : zin) {

													WebElement sp = driver
															.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
													if (!sp.isSelected())
														sp.click();
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										} else if (ele.contains("E911")) {

											driver.findElement(By
													.xpath("//*[@name='E911']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(5000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										}
										else if (ele.contains("ICP")) {

											driver.findElement(By
													.xpath("//*[@name='ICP']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(5000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										} else if (ele.contains("IMM")) {

											driver.findElement(By
													.xpath("//*[@name='IMM']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {

													WebElement sp = driver
															.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
													if (!sp.isSelected())
														sp.click();
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										

										} else if (ele.contains("LIDB")) {

											driver.findElement(By
													.xpath("//*[@name='LIDB']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]=Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(5000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										} else if (ele.contains("SOA")) {

											driver.findElement(By
													.xpath("//*[@name='SOA']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {
														try{

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
														catch(Exception e2){
															driver.findElement(By.xpath("//*[@label='Features']")).click();
															Thread.sleep(3000);
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
														
															
														}
												}
											}}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										}
										}
										else{
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
											
										}
									} catch (Exception e2) {
										try {
											driver.findElement(By.xpath("//*[@label='Features']")).click();
											Thread.sleep(5000);
										
											if(ele.contains("--")){
												//int elein = Arrays.asList(subarray).indexOf(ele);
												//String subsubelement = subarray[elein];
												String in[] = ele.split("--");
												String zin[]= in[1].split("#");
												WebElement sp1 = driver.findElement(
														By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
												if (!sp1.isSelected())
													sp1.click();
											
											Thread.sleep(3000);
											if (ele.contains("Non-Bonded Automation")) {
												driver.findElement(By
														.xpath("//*[@name='Non-Bonded Automation']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();

												Thread.sleep(3000);
												
												if (!in[1].isEmpty()) {
													for (String Z : zin) {

														WebElement sp = driver
																.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
													}
												}
												driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
														.click();

												Thread.sleep(10000);

											

											

											} else if (ele.contains("Self-Service Portal")) {
												driver.findElement(By
														.xpath("//*[@name='Self-Service Portal']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();

												Thread.sleep(3000);
												
												if (!in[1].isEmpty()) {
													for (String Z : zin) {

														WebElement sp = driver
																.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
													}
												}
												driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
														.click();

												Thread.sleep(10000);

											


											}}
											else{WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();}
										} catch (Exception e3) {
											driver.findElement(By.xpath("//*[@label='Managed Services']")).click();
											Thread.sleep(5000);
											if(ele.contains("--")){
												//int elein = Arrays.asList(subarray).indexOf(ele);
												//String subsubelement = subarray[elein];
												String in[] = ele.split("--");
												String zin[]= in[1].split("#");
												WebElement sp1 = driver.findElement(
														By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
												if (!sp1.isSelected())
													sp1.click();
											
											Thread.sleep(3000);
											if (ele.contains("BPO Fallout Handling")) {
												driver.findElement(By
														.xpath("//*[@name='BPO Fallout Handling']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();

												Thread.sleep(3000);
												
												if (!in[1].isEmpty()) {
													for (String Z : zin) {
														
														if(Z.equals("Recurring Service Fee")){
															WebElement sp = driver
																	.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
															if (!sp.isSelected())
																sp.click();
															
														}
														else{
														WebElement sp = driver
																.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
													}
												}
												js.executeScript("arguments[0].value='" + threshold + "';", driver
														.findElement(By.xpath("//*[@label='Threshold']/div/div/sb-field/span/div/sb-input")));
												driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
														.click();

												Thread.sleep(10000);

											


											} else if (ele.contains("BPO Special Handling")) {
												driver.findElement(By
														.xpath("//*[@name='BPO Special Handling']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();

												Thread.sleep(3000);
												
												if (!in[1].isEmpty()) {
													for (String Z : zin) {
														
														if(Z.equals("Recurring Service Fee")){
															WebElement sp = driver
																	.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
															if (!sp.isSelected())
																sp.click();
															
														}
														else{
														WebElement sp = driver
																.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
													}
												}
												js.executeScript("arguments[0].value='" + threshold + "';", driver
														.findElement(By.xpath("//*[@label='Threshold']/div/div/sb-field/span/div/sb-input")));
												driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
														.click();

												Thread.sleep(10000);

											


											} else if (ele.contains("BPO Project Management")) {
												driver.findElement(By
														.xpath("//*[@name='BPO Project Management']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();

												Thread.sleep(3000);
												
												if (!in[1].isEmpty()) {
													for (String Z : zin) {
														
														if(Z.equals("Recurring Service Fee")){
															WebElement sp = driver
																	.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
															if (!sp.isSelected())
																sp.click();
															
														}
														else{
														WebElement sp = driver
																.findElement(By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
													}
												}
												js.executeScript("arguments[0].value='" + threshold + "';", driver
														.findElement(By.xpath("//*[@label='Threshold']/div/div/sb-field/span/div/sb-input")));
												driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
														.click();

												Thread.sleep(10000);

											


											} }
											else{
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();}
										}
									}

								}
							}
						}
						try{
						driver.findElement(
								By.xpath("//*[@label='Solution Description']/div/div/sb-field/span/div//textarea")).sendKeys(solutiondescription);
						}
						catch(Exception e){
						driver.findElement(By.xpath("//*[@label='Solution Description']/div/div/sb-field/span/div//input")).sendKeys(solutiondescription);
						}
						
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

						Thread.sleep(10000);

					}
					if (element.equals("CSR")) {
						driver.findElement(By
								.xpath("//*[@name='CSR']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]= y[1].split("#");
										if (y[0].equals("CSR Bonded Transaction Fees")) {
											for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("CSR Non-Bonded Transaction Fees")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
										
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

						Thread.sleep(10000);

					}
					if (element.equals("ASR Send")) {
						driver.findElement(By
								.xpath("//*[@name='ASR Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]=y[1].split("#");
										if (y[0].equals("ASR Send PreOrder")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("ASR Send Order")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
										else if (y[0].equals("Non-Bonded ASR Support")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[3]"));
											if (!sp.isSelected())
												sp.click();
										}}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("E911")) {
						driver.findElement(By
								.xpath("//*[@name='E911']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}
								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

						Thread.sleep(10000);

					}
					if (element.equals("Managed ASR")) {
						driver.findElement(By
								.xpath("//*[@name='Managed ASR']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
				//	if (element.equals("ESR Wireless")) {}
					if (element.equals("BOOM")) {
						driver.findElement(By
								.xpath("//*[@name='BOOM']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("LIDB")) {
						driver.findElement(By
								.xpath("//*[@name='LIDB']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("BPO Fallout Handling")) {
						driver.findElement(By
								.xpath("//*[@name='BPO Fallout Handling']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								
								if(ele.equals("Recurring Service Fee")){
									WebElement sp = driver
											.findElement(By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
									if (!sp.isSelected())
										sp.click();
									
								}
								else{
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
								}
							}
						}
						js.executeScript("arguments[0].value='" + threshold + "';", driver
								.findElement(By.xpath("//*[@label='Threshold']/div/div/sb-field/span/div/sb-input")));
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("OI (Access Customers)")) {
						driver.findElement(By
								.xpath("//*[@name='OI (Access Customers)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("LSR Receive")) {
						driver.findElement(By
								.xpath("//*[@name='LSR Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {
									try{
									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]=y[1].split("#");
										if (y[0].equals("LSR Receive Order")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("LSR Receive Pre-Order")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							 catch (Exception e2) {

										driver.findElement(By.xpath("//*[@label='Features']")).click();
										Thread.sleep(3000);
										if (ele.contains("--")) {
											String y[] = ele.split("--");
											String zin[]=y[1].split("#");
											if (y[0].equals("Auto-Response Order")) {for (String Z : zin) {

												WebElement sp = driver
														.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
												if (!sp.isSelected())
													sp.click();
											}} else if (y[0].equals("Auto-Response Pre-Order")) {for (String Z : zin) {

												WebElement sp = driver
														.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
												if (!sp.isSelected())
													sp.click();
											}}
										} else {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										}
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("BPO Special Handling")) {
						driver.findElement(By
								.xpath("//*[@name='BPO Special Handling']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								
								if(ele.equals("Recurring Service Fee")){
									WebElement sp = driver
											.findElement(By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
									if (!sp.isSelected())
										sp.click();
									
								}
								else{
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
								}
							

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						js.executeScript("arguments[0].value='" + threshold + "';", driver
								.findElement(By.xpath("//*[@label='Threshold']/div/div/sb-field/span/div/sb-input")));
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("OI (Access Providers)")) {
						driver.findElement(By
								.xpath("//*[@name='OI (Access Providers)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("LSR Send")) {
						driver.findElement(By
								.xpath("//*[@name='LSR Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {
									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]=y[1].split("#");
										if (y[0].equals("LSR Send Pre-Order")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("LSR Send Order (Original)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
										else if (y[0].equals("LSR Send Order (Support)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[3]"));
											if (!sp.isSelected())
												sp.click();
										}}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("Order Validation Tool (OVT)")) {
						driver.findElement(By
								.xpath("//*[@name='Order Validation Tool (OVT)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("ICP")) {
						driver.findElement(By
								.xpath("//*[@name='ICP']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {

									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("TAM Receive")) {
						driver.findElement(By
								.xpath("//*[@name='TAM Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {
									
									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]=y[1].split("#");
										if (y[0].equals("Monthly Transaction Fees (New Tickets and MLT Requests)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("Monthly Transaction Fees (Ticket Updates)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							
								
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("IMM")) {
						driver.findElement(By
								.xpath("//*[@name='IMM']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("Self-Service Portal")) {
						driver.findElement(By
								.xpath("//*[@name='Self-Service Portal']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("TAM Send")) {
						driver.findElement(By
								.xpath("//*[@name='TAM Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {
									
									driver.findElement(By.xpath("//*[@label='Transactions']")).click();
									Thread.sleep(3000);
									if (ele.contains("--")) {
										String y[] = ele.split("--");
										String zin[]=y[1].split("#");
										if (y[0].equals("Monthly Transaction Fees (New Tickets and MLT Requests)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[1]"));
											if (!sp.isSelected())
												sp.click();
										}} else if (y[0].equals("Monthly Transaction Fees (Ticket Updates)")) {for (String Z : zin) {

											WebElement sp = driver
													.findElement(By.xpath("(//*[@name='" + Z + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							
								
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("Non-Bonded Automation")) {
						driver.findElement(By
								.xpath("//*[@name='Non-Bonded Automation']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}

					if (element.contains("UOM ")) {
						
						if (element.equals("UOM Receive"))
						driver.findElement(By
								.xpath("//*[@name='UOM Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						if (element.equals("UOM Send"))
							driver.findElement(By
									.xpath("//*[@name='UOM Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
									.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if (ele.contains("Bonded")) {
										String y[] = ele.split("-");
										if (y[1].equals("Bonded")) {
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
										} else {
											WebElement sp = driver.findElement(By.xpath(
													"(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();
										}
									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									}

								} catch (Exception e) {
									try {
										driver.findElement(By.xpath("//*[@label='Transactions']")).click();
										Thread.sleep(5000);
										if(ele.contains("--")){
											//int elein = Arrays.asList(subarray).indexOf(ele);
											//String subsubelement = subarray[elein];
											String in[] = ele.split("--");
											String zin[]= in[1].split("#");
											WebElement sp1 = driver.findElement(
													By.xpath("//*[@name='" + in[0] + "']//sb-group[@id='selection']"));
											if (!sp1.isSelected())
												sp1.click();
			
										if (ele.contains("ASR Receive")) {

											driver.findElement(By
													.xpath("//*[@name='ASR Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														if (Z.contains("::")) {
															String a[] = Z.split("::");
															String lin[]= a[1].split("'");
															if (a[0].equals("ASR Receive Order")) {
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[1]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
																
															} else if (a[0].equals("ASR Receive PreOrder")) {
																
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[2]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
																
															}
														} else {
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
														}
													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										} else if (ele.contains("ASR Send")) {

											driver.findElement(By
													.xpath("//*[@name='ASR Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														if (Z.contains("::")) {
															String a[] = Z.split("::");
															String lin[]= a[1].split("'");
															if (a[0].equals("ASR Send Order")) {
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[2]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
																
															} else if (a[0].equals("Non-Bonded ASR Support")) {
																
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[3]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
															}
																	else if (a[0].equals("ASR Send PreOrder")) {
																		
																		for (String S : lin) {

																			WebElement sp = driver
																					.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[1]"));
																			if (!sp.isSelected())
																				sp.click();
																		}
																
															}
														} else {
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
														}
													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										}
										else if (ele.contains("Managed ASR")) {

											driver.findElement(By
													.xpath("//*[@name='Managed ASR']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														
													} 
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										} else if (ele.contains("BOOM")) {

											driver.findElement(By
													.xpath("//*[@name='BOOM']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]=Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										

										} else if (ele.contains("OI (Access Customers)")) {

											driver.findElement(By
													.xpath("//*[@name='OI (Access Customers)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]=Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										} else if (ele.contains("OI (Access Providers)")) {

											driver.findElement(By
													.xpath("//*[@name='OI (Access Providers)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											Thread.sleep(3000);
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {
														

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();

												
														
												}
											}}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										}
										else if(ele.contains("TAM Receive")||ele.contains("TAM Send")){
											
											if(ele.contains("TAM Receive"))
											driver.findElement(By
													.xpath("//*[@name='TAM Receive']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
													.click();
											if(ele.contains("TAM Send"))
												driver.findElement(By
														.xpath("//*[@name='TAM Send']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
														.click();
											Thread.sleep(3000);
											
											if (!in[1].isEmpty()) {
												for (String Z : zin) {
													try {
														driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
														Thread.sleep(3000);
														if(Z.contains("Bonded")){
															String y[]= Z.split("-");
															if(y[1].equals("Bonded"))
															{
														WebElement sp = driver.findElement(
																By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
														if (!sp.isSelected())
															sp.click();
														}
															else{
																WebElement sp = driver.findElement(
																		By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
																if (!sp.isSelected())
																	sp.click();	
															}
														}
														else{
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
															
														}
													} catch (Exception e1) {

														driver.findElement(By.xpath("//*[@label='Transactions']")).click();
														Thread.sleep(3000);
														if (Z.contains("::")) {
															String y[] = Z.split("::");
															String lin[]= y[1].split("'");
															if (y[0].equals("Monthly Transaction Fees (New Tickets and MLT Requests)")) {
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[1]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
																
															} else if (y[0].equals("Monthly Transaction Fees (Ticket Updates)")) {
																
																	for (String S : lin) {

																		WebElement sp = driver
																				.findElement(By.xpath("(//*[@name='" + S + "']//sb-group[@id='selection'])[2]"));
																		if (!sp.isSelected())
																			sp.click();
																	}
																
															}
														} else {
															WebElement sp = driver.findElement(
																	By.xpath("//*[@name='" + Z + "']//sb-group[@id='selection']"));
															if (!sp.isSelected())
																sp.click();
														}
													}
												}
											}
											driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
													.click();

											Thread.sleep(10000);

										

										}
										}
										else{
											WebElement sp = driver.findElement(
													By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
											if (!sp.isSelected())
												sp.click();
											
										}
									} catch (Exception e2) {
										driver.findElement(By.xpath("//*[@label='Features']")).click();
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						try{
							driver.findElement(
									By.xpath("//*[@label='Solution Description']/div/div/sb-field/span/div//textarea")).sendKeys(solutiondescription);
							}
							catch(Exception e){
							driver.findElement(By.xpath("//*[@label='Solution Description']/div/div/sb-field/span/div//input")).sendKeys(solutiondescription);
							}
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
				//	if (element.equals("UOM Send")) {}
					if (element.equals("SOA")) {
						driver.findElement(By
								.xpath("//*[@name='SOA']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									driver.findElement(By.xpath("//*[@label='Trading Partner and Platform']")).click();
									Thread.sleep(3000);
									if(ele.contains("Bonded")){
										String y[]= ele.split("-");
										if(y[1].equals("Bonded"))
										{
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + y[0] + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
									}
										else{
											WebElement sp = driver.findElement(
													By.xpath("(//*[@name='" + y[0] + "']//sb-group[@id='selection'])[2]"));
											if (!sp.isSelected())
												sp.click();	
										}
									}
									else{
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
										
									}

								} catch (Exception e) {
									try {
										driver.findElement(By.xpath("//*[@label='Transactions']")).click();
										Thread.sleep(3000);
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();

									} catch (Exception e2) {
										driver.findElement(By.xpath("//*[@label='Features']")).click();
										Thread.sleep(3000);
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("vProv")) {
						driver.findElement(By
								.xpath("//*[@name='vProv']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("LSMS")) {
						driver.findElement(By
								.xpath("//*[@name='LSMS']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}
					if (element.equals("PWP and ezLD (Canada Only)")) {
						driver.findElement(By
								.xpath("//*[@name='PWP and ezLD (Canada Only)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}

					if (element.equals("Request Flow (Canada Only)")) {
						driver.findElement(By
								.xpath("//*[@name='Request Flow (Canada Only)']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);

					}

				}
			}

			else if (productname.equals("Licensed OMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("NumeriTrack")) {
						driver.findElement(By
								.xpath("//*[@name='NumeriTrack']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						// System.out.println(elementin);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						for (String ele : xin) {
							try {
								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e) {
								try {
									driver.findElement(By.xpath("//*[@label='Licensing']")).click();

									if (ele.contains("Licensed Feature")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Webservices API-License")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[text()='Webservices API']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group)[2]"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("High Availability Software License")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Optional NumeriTrack License")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("SOAC Adapter License Feature")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("LNP Integration to OrderPath")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("LNP Integration to Neustar Hosted SOA")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}

								catch (Exception e2) {
									driver.findElement(By.xpath("//*[@label='AppsManager']")).click();
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("OrderPath")) {
						driver.findElement(By
								.xpath("//*[@name='OrderPath']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						for (String ele : xin) {
							try {
								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e) {
								try {
									driver.findElement(By.xpath("//*[@label='Licensing']")).click();
									if (ele.contains("Licensed Feature")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Webservices API-License")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[text()='Webservices API']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group)[2]"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele
											.equals("Modification of Disconnect Pending SV Dates Licensed Software")) {
										WebElement sp = driver.findElement(By.xpath(
												"//*[text()='Modification of Disconnect Pending SV Dates  Licensed Software']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("SOAC Adapter")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Bulk Download Latest View Licensed Feature")) {
										WebElement sp = driver.findElement(By.xpath(
												"//*[text()='Bulk Download Latest View  Licensed Feature']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Outbound Request Throttling Licensed Software")) {
										WebElement sp = driver.findElement(By.xpath(
												"//*[text()='Outbound Request Throttling   Licensed Software']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("LNP Integration to Neustar Hosted SOA")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}
								}

								catch (Exception e2) {
									driver.findElement(By.xpath("//*[@label='AppsManager']")).click();
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}
							}
						}
						driver.findElement(By
								.xpath("//*[@label='Interface Type']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + interfaceType + "']"))
								.click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("NumberManager")) {
						driver.findElement(By
								.xpath("//*[@name='NumberManager']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						for (String ele : xin) {
							try {
								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e) {
								try {
									driver.findElement(By.xpath("//*[@label='Licensing']")).click();
									if (ele.contains("Licensed Feature")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Webservices API-License")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[text()='Webservices API']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group)[2]"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele
											.equals("National Number Pooling Efficient Data Representation (EDR)")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("LOMS - Optional NumberManager License")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("Asynchronous Database Provisioning Adapter")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("XML Socket Adapter-License")) {
										WebElement sp = driver.findElement(By.xpath(
												"(//*[text()='XML Socket Adapter']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group)[2]"));
										if (!sp.isSelected())
											sp.click();

									} else if (ele.equals("CDW Billing Adapters")) {
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} else {
										WebElement sp = driver.findElement(
												By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
										if (!sp.isSelected())
											sp.click();
									}

								} catch (Exception e2) {
									driver.findElement(By.xpath("//*[@label='AppsManager']")).click();
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();
								}

							}
						}
						driver.findElement(By
								.xpath("//*[@label='Interface Type']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + interfaceType + "']"))
								.click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("VeriPort")) {
						driver.findElement(By
								.xpath("//*[@name='VeriPort']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						for (String ele : xin) {
							try {
								driver.findElement(By.xpath("//*[@label='Components']")).click();
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							} catch (Exception e) {

								driver.findElement(By.xpath("//*[@label='Licensing']")).click();
								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();

							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("DataServer")) {
						driver.findElement(By
								.xpath("//*[@name='DataServer']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						for (String ele : xin) {

							driver.findElement(By.xpath("//*[@label='Components']")).click();
							WebElement sp = driver
									.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
							if (!sp.isSelected())
								sp.click();

						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("Package A (Up to 12M Queries / Year)")) {
						for (String subelement : subarray) {
							WebElement sp = driver.findElement(
									By.xpath("//*[@name='" + subelement + "']//sb-group[@id='selection']"));
							if (!sp.isSelected())
								sp.click();
							driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
									.click();
							Thread.sleep(10000);
						}
					}

				}
				// driver.findElement(By.xpath("//*[@class='style-scope
				// sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);
			}

			else if (productname.equals("Localeze")) {

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Business Registry Manager Service (BRM) - Monthly")) {
						driver.findElement(By
								.xpath("//*[@name='Business Registry Manager Service (BRM) - Monthly']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionBRMmonthly + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyBRMmonthly + "']")).click();

						driver.findElement(
								By.xpath("//*[@label='Monthly Minimum Listing Volume #']//input[@type='text']"))
								.sendKeys(String.valueOf(monthlyminlistvol));
						driver.findElement(By.xpath("//*[@label='Monthly Minimum Fee $']//input[@type='text']"))
								.sendKeys(String.valueOf(monthlyminfee));
						if (existingclient.equals("yes"))
							driver.findElement(By.xpath("//*[@label='Existing Yearly Client']//*[@id='checkbox']"))
									.click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Business Registry Manager Service (BRM) - Yearly Service Order")) {
						driver.findElement(By
								.xpath("//*[@name='Business Registry Manager Service (BRM) - Yearly Service Order']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {
								try {
									WebElement sp = driver.findElement(
											By.xpath("(//*[@name='" + ele + "']//sb-group[@id='selection'])[2]"));
									if (!sp.isSelected())
										sp.click();
								} catch (Exception e) {
									WebElement sp = driver.findElement(
											By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
									if (!sp.isSelected())
										sp.click();

								}
							}
						}
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionBRMyearlyservice + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyBRMyearlyservice + "']")).click();
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("Business Registry Manager Service (BRM) - Yearly Listing Order")) {
						driver.findElement(By
								.xpath("//*[@name='Business Registry Manager Service (BRM) - Yearly Listing Order']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@label='Name of Sub-Client']//input[@type='text']"))
								.sendKeys(nameofsubclient);

						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionBRMyearlylist + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyBRMyearlylist + "']")).click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						// Thread.sleep(10000);
					}
					if (element.equals("Localeze Data - U.S. IYP")) {
						driver.findElement(By
								.xpath("//*[@name='Localeze Data - U.S. IYP']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionUSIYP + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyUSIYP + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Data Delivery Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						// Thread.sleep(2000);
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ datadeliveryfrequencyUSIYP + "']")).click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						// Thread.sleep(10000);
					}
					if (element.equals("Localeze Data - U.S. EBR")) {
						driver.findElement(By
								.xpath("//*[@name='Localeze Data - U.S. EBR']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionUSEBR + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyUSEBR + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Data Delivery Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						// Thread.sleep(2000);
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ datadeliveryfrequencyUSEBR + "']")).click();

						driver.findElement(By.xpath("//*[@label='Additional File Slice Criteria']//*[@id='textarea']"))
								.sendKeys(String.valueOf(additionalFileSliceCriteria));

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						// Thread.sleep(10000);
					}

					if (element.equals("Localeze Data - U.S. API")) {
						driver.findElement(By
								.xpath("//*[@name='Localeze Data - U.S. API']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						/*
						 * int elementin =
						 * Arrays.asList(array).indexOf(element); String
						 * subelement = subarray[elementin]; String xin[] =
						 * subelement.split("__"); if (!subelement.isEmpty()) {
						 * 
						 * for (String ele : xin) {
						 * 
						 * WebElement sp = driver
						 * .findElement(By.xpath("//*[@name='" + ele +
						 * "']//sb-group[@id='selection']")); if
						 * (!sp.isSelected()) sp.click(); } }
						 */
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionUSAPI + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyUSAPI + "']")).click();
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}
					if (element.equals("Localeze Data - Canada IYP")) {
						driver.findElement(By
								.xpath("//*[@name='Localeze Data - Canada IYP']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(3000);
						/*
						 * int elementin =
						 * Arrays.asList(array).indexOf(element); String
						 * subelement = subarray[elementin]; String xin[] =
						 * subelement.split("__"); if (!subelement.isEmpty()) {
						 * for (String ele : xin) {
						 * 
						 * WebElement sp = driver
						 * .findElement(By.xpath("//*[@name='" + ele +
						 * "']//sb-group[@id='selection']")); if
						 * (!sp.isSelected()) sp.click(); } }
						 */
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingoptionCanadaIYP + "']")).click();

						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ billingfrequencyCanadaIYP + "']")).click();
						driver.findElement(By
								.xpath("//*[@label='Data Delivery Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='"
								+ datadeliveryfrequencycanadaIYP + "']")).click();

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
				}

				// driver.findElement(By.xpath("//*[@class='style-scope
				// sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("Neustar Certificate Manager")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				select(subproducts);
			}

			else if (productname.equals("Pathfinder")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("By Country")) {
						driver.findElement(By
								.xpath("//*[@name='By Country']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								try {
									driver.findElement(By.xpath("//*[@label='Onboard']")).click();
									WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
											+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
									if (!sp.isSelected())
										sp.click();

								} catch (Exception e) {
									try {

										driver.findElement(By.xpath("//*[@label='Remote A']")).click();
										WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
												+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
										if (!sp.isSelected())
											sp.click();

									} catch (Exception e1) {
										try {
											driver.findElement(By.xpath("//*[@label='Remote B']")).click();
											WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
													+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
											if (!sp.isSelected())
												sp.click();

										} catch (Exception e2) {
											try {

												driver.findElement(By.xpath("//*[@label='Remote C']")).click();
												WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
														+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
												if (!sp.isSelected())
													sp.click();

											} catch (Exception e3) {
												try {

													driver.findElement(By.xpath("//*[@label='Remote D']")).click();
													WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
															+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
													if (!sp.isSelected())
														sp.click();

												} catch (Exception e4) {
													driver.findElement(By.xpath("//*[@label='Remote E']")).click();
													WebElement sp = driver.findElement(By.xpath("//*[text()='" + ele
															+ "']/parent::div/parent::span/parent::sb-field/parent::sb-option-cell/parent::div/parent::div/parent::sb-group/parent::div/preceding-sibling::div[@id='selectionContainer']/sb-group"));
													if (!sp.isSelected())
														sp.click();

												}
											}
										}
									}
								}
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Country Transaction - Onboard Non-US")) {
						driver.findElement(By
								.xpath("//*[@name='Country Transaction - Onboard Non-US']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
				}
			}

			else if (productname.equals("PortPS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("Basic - TN per Month")) {
						driver.findElement(By
								.xpath("//*[@name='Basic - TN per Month']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}

					if (element.equals("Basic - TN per Year")) {
						driver.findElement(By
								.xpath("//*[@name='Basic - TN per Year']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("API - Monthly Tiers")) {
						driver.findElement(By
								.xpath("//*[@name='API - Monthly Tiers']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
					if (element.equals("Query Manager")) {
						driver.findElement(By
								.xpath("//*[@name='Query Manager']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(2000);
						driver.findElement(
								By.xpath("//*[@label='Customers TN Ownership (Millions)']//input[@type='text']"))
								.sendKeys(String.valueOf(CustomersTNOwnership));
						driver.findElement(By
								.xpath("//*[@label='Max Seats']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By
								.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + Maxseats + "']"))
								.click();
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
						Thread.sleep(10000);
					}

				}

			}

			else if (productname.equals("PortPS RIMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();

				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("RIMS - GUI")) {
						driver.findElement(By
								.xpath("//*[@name='RIMS - GUI']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Max Seats']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By
								.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + Maxseats + "']"))
								.click();

						driver.findElement(By.xpath("//*[@label='Number of OCNs']//input[@type='text']")).sendKeys(String.valueOf(OCN));

						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					}
				}
			}

			else if (productname.equals("Robocall Mitigation")) {
				js.executeScript("arguments[0].value='" + scopeofrecords + "';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				for (String element : array) {
					System.out.println(element);

					if (element.equals("Robocall Mitigation Dashboard")) {
						try {
							driver.findElement(By
									.xpath("(//*[@name='Robocall Mitigation Dashboard']//sb-group[@id='selection'])[2]"))
									.click();
						} catch (Exception e) {
							driver.findElement(By
									.xpath("(//*[@name='Robocall Mitigation Dashboard']//sb-group[@id='selection'])[1]"))
									.click();

						}

					}
					if (element.equals("Subscriber Data and Policy Repository")) {
						try {
							driver.findElement(By
									.xpath("(//*[@name='Subscriber Data and Policy Repository']//sb-group[@id='selection'])[2]"))
									.click();
						} catch (Exception e) {
							driver.findElement(By
									.xpath("(//*[@name='Subscriber Data and Policy Repository']//sb-group[@id='selection'])[1]"))
									.click();

						}
						driver.findElement(By
								.xpath("//*[@name='Subscriber Data and Policy Repository']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						driver.findElement(By
								.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
								.click();
						driver.findElement(By
								.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
								.click();
						driver.findElement(By.xpath(
								"//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
								.click();
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}

						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();

					} else {
						WebElement p = driver
								.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
						if (!p.isSelected())
							p.click();
					}
				}

			}

			else if (productname.equals("SIP-IX")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				for (String element : array) {
					System.out.println(element);
					WebElement p = driver
							.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
					if (!p.isSelected())
						p.click();
					if (element.equals("SIP-IX Subscribe Interface - Contract One")) {
						driver.findElement(By
								.xpath("//*[@name='SIP-IX Subscribe Interface - Contract One']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("SIP-IX Subscribe Interface - Contract All")) {
						driver.findElement(By
								.xpath("//*[@name='SIP-IX Subscribe Interface - Contract All']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}
					if (element.equals("SIP-IX Query Interface")) {
						driver.findElement(By
								.xpath("//*[@name='SIP-IX Query Interface']//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
								.click();
						Thread.sleep(5000);
						int elementin = Arrays.asList(array).indexOf(element);
						String subelement = subarray[elementin];
						String xin[] = subelement.split("__");
						if (!subelement.isEmpty()) {
							for (String ele : xin) {

								WebElement sp = driver
										.findElement(By.xpath("//*[@name='" + ele + "']//sb-group[@id='selection']"));
								if (!sp.isSelected())
									sp.click();
							}
						}
						driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']"))
								.click();
					}

				}

			}

			// 20 - else if(productname.equals("System Admin Guides")) { }

			else if (productname.equals("Trusted Call Vetting")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingfrequency + "']"))
						.click();
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='" + billingoption + "']"))
						.click();
				select(subproducts);
			}
			Thread.sleep(10000);

		} //else if (sellinglane.equals("Security")) {}

		if (!productname.equals("System Admin Guides")) {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
		}
		if (productname.equalsIgnoreCase("UltraDNS Firewall")) {
			driver.findElement(By.xpath("//*[text()='Continue']")).click();
		}

	

		Thread.sleep(20000);
		try{
			driver.switchTo().alert().accept();
		}
		catch(Exception e){}
		ql.getSubscriptionterm().clear();
		Thread.sleep(5000);
		
		ql.getSubscriptionterm().sendKeys(String.valueOf(Subscriptionterm));
		
		try{
			driver.switchTo().alert().accept();}
			catch(Exception e){
				Thread.sleep(1000);
			}
		
		driver.findElement(By.xpath("//*[@tooltip='Subscription Term']")).click();

				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='Quick Save']/paper-button")));
				Thread.sleep(30000);

				// SAVE
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='Save']/paper-button")));
				// try {
				// driver.navigate().refresh();
				Thread.sleep(30000);
				// js.executeScript("arguments[0].click();",
				// driver.findElement(By.xpath("//*[@name='Save']/paper-button")));
				// } catch (Exception exc) {
				// Thread.sleep(1000);
				driver.switchTo().defaultContent();

				// }
				driver.navigate().refresh();
				Thread.sleep(10000);

			
				return driver;

			}


	
	public Double priceval(WebElement element) {
		String price = element.getText().substring(4);
		String pv = price.replace(",", "");
		Double pricevalue = Double.parseDouble(pv);
		return pricevalue;
	}

	public void select(String subproducts) {
		if (!subproducts.isEmpty()) {
			for (String element : array) {
				System.out.println(element);
				WebElement p = driver.findElement(By.xpath("//*[@name='" + element + "']//sb-group[@id='selection']"));
				if (!p.isSelected())
					p.click();
			}
		}
	}

}
