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
import java.util.Date;
import java.util.Properties;
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

public class productConfiguration extends Base {

	public String startdate1;
	public String expenddate;
	public String termstrtdate;
	public String stDate = "";

	public productConfiguration(WebDriver driver) {
		this.driver = driver;

	}

	public WebDriver productConfiguration(String sellinglane, String productname, String freeperiod,
			String Termstartdate, String Subscriptionterm, String startdate, String discount, String discountoption)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		quoteLineEditor ql = new quoteLineEditor(driver);
		createQuotePage qp = new createQuotePage(driver);
		Thread.sleep(30000);
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

		driver.findElement(By.xpath("(//i[@class='sf-icon-close style-scope sb-dialog'])[3]")).click();
		Thread.sleep(5000);
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
				// act.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).build().perform();
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
			}

		}
		driver.findElement(By.xpath("//*[text()='Select']")).click();

		Thread.sleep(10000);
		if (sellinglane.equals("Risk")) {
			if (productname.equals("Contact Compliance Risk")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				// driver.findElement(By.xpath("//*[@name='Change Notification
				// Platform']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Contact Tracing Solutions")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Change Notification Platform']//sb-group[@id='selection']"))
						.click();

			} else if (productname.equals("Digital Identity Risk Flex")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Identity Verification (MPIC 3.0)']//sb-group[@id='selection']"))
						.click();

			} else if (productname.equals("Digital Identity Risk Pro")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Email Attributes']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Domestic Phone Takeover Risk")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Call Forwarding']//sb-group[@id='selection']")).click();
			} else if (productname.equals("EveryoneAPI")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Address Attributes']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Inbound Authentication")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				js.executeScript("arguments[0].value='5';",
						driver.findElement(By.xpath("//*[@label='ACV Usage']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By.xpath("//*[@name='Setup Fee']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Initial Account Database Processing - Authentication")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("Initial Account Database Processing - Compliance")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Initial Account Database Processing - Customer Operations")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("International Phone Takeover Risk")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By
						.xpath("//*[@name='Carrier Change Information (International & Domestic)']//sb-group[@id='selection']"))
						.click();

			} else if (productname.equals("Insight Engine - SmartDial")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			} else if (productname.equals("iTact")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("iTact (per seat pricing)")) {
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("Phone Behavior Intelligence")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Custom Billing']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Change Notification Platform']//sb-group[@id='selection']"))
						.click();

			} else if (productname.equals("Risk - Custom Solution - Authentication")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();

			} else if (productname.equals("Risk - Custom Solution - Compliance")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Risk - Custom Solution - Customer Operations")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Overage Rate']//sb-group[@id='selection']")).click();
			} else if (productname.equals("SmartTrace")) {
				// driver.findElement(By.xpath("//*[@label='Scope of Records
				// (Millions)']/div/div/sb-field/span/div/sb-input")).click();
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Phone Attributes']//sb-group[@id='selection']")).click();

			}

		}
		// Marketing products
		else if (sellinglane.equals("Marketing")) {
			if (productname.equals("AdAdvisor"))

			{
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
			} else if (productname.equals("Customer Identity File (CIF)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Geography']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Basic State']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='File Attributes']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='PII Only']"))
						.click();

				driver.findElement(By.xpath("//*[@id='offRadio']")).click();

			} else if (productname.equals("Customer Identity Management (CIM)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By
						.xpath("//*[@name='Initial Database Processing (Up Front Scrub)']//sb-group[@id='selection']"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By
						.xpath("//*[@id='swipe-header']//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);

				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				driver.findElement(By
						.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='1']")).click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			} else if (productname.equals("ElementOne Analytics")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@name='Users (5)']//sb-group[@id='selection']")));

			} else if (productname.equals("Financial Spectrum")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();
				driver.findElement(By
						.xpath("//*[@name='AdAdvisor Audience (Stand Alone or with DMP)']//sb-group[@id='selection']"))
						.click();

			} else if (productname.equals("Identity Data Management Platform (IDMP)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Package D - Up to 3B Impressions']//sb-group[@id='selection']"))
						.click();
				driver.findElement(By
						.xpath("//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//*[@name='Data Feeds (Per Month)']//sb-group[@id='selection']")).click();
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

				Thread.sleep(10000);
			} else if (productname.equals("Omni Channel Remarketing")) {
				driver.findElement(By
						.xpath("//*[@label='Use Case']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Direct to Advertiser']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Pilot']//sb-group[@id='selection']")).click();
			} else if (productname.equals("Onboarding")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(
						By.xpath("//*[@name='Package A (Up to 20M Input Records / Month)']//sb-group[@id='selection']"))
						.click();
				driver.findElement(By
						.xpath("//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			} else if (productname.equals("Other Identity Data")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();
				driver.findElement(By.xpath("//*[@name='US Cons. Enh.']//sb-group[@id='selection']")).click();

			} else if (productname.equals("RealTime Identity")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));
				driver.findElement(By
						.xpath("//*[@label='Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='1']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Verification and Identification']//sb-group[@id='selection']"))
						.click();
				Thread.sleep(2000);

			} else if (productname.equals("Realtime Website Personalizations")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(
						By.xpath("//*[@name='Package A (Up to 12M Queries / Year)']//sb-group[@id='selection']"))
						.click();
				driver.findElement(By
						.xpath("//*[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

				Thread.sleep(30000);

			} else if (productname.equals("Unified Analytics - MMM")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@id='offRadio']")).click();

			} else if (productname.equals("Unified Analytics - MTA")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}
		} else if (sellinglane.equals("Communications")) {
			// communication products

			if (productname.equals("Branded Call Display")) {

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Platform Fee']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Branded Call Display and Caller Name Optimization Combo")) {

				// Configure Branded Call Display
				driver.findElement(By
						.xpath("//*[@name='Branded Call Display']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Platform Fee']//sb-group[@id='selection']")).click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

				Thread.sleep(10000);

				// Configure Caller Name Optimization
				driver.findElement(By
						.xpath("//*[@name='Caller Name Optimization']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();

				Thread.sleep(10000);

			}

			else if (productname.equals("CNO/BCD/ECC Combo")) {

				// Configure Caller Name Optimization
				driver.findElement(By
						.xpath("//*[@name='Caller Name Optimization']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

				// Configure Branded Call Display
				driver.findElement(By
						.xpath("//*[@name='Branded Call Display']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Platform Fee']//sb-group[@id='selection']")).click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

				// Configure Enterprise Certified Caller
				driver.findElement(By
						.xpath("//*[@name='Enterprise Certified Caller']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				Thread.sleep(10000);
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("Caller ID (CNAM)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@id='radioContainer'][1]")).click();
				driver.findElement(By
						.xpath("//*[@name='Storage and Delivery']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Access Fee (Monthly Minimum)']//sb-group[@id='selection']"))
						.click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("Caller Name Optimization")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("Certified Caller")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(
						By.xpath("//*[@name='Standalone (Premises) - Perpetual']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Communications Professional Services")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='e911 Audit']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Directory Listings")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Directory Listings - Band 1']//sb-group[@id='selection']"))
						.click();

			}

			else if (productname.equals("Enterprise Certified Caller")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("Global Numbering Intelligence Services (GNIS)")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("Hosted OMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='ESR Commercial']//sb-group[@id='selection']")).click();
				driver.findElement(By
						.xpath("//*[@name='ESR Commercial']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				driver.findElement(
						By.xpath("//*[@name='Per Trading Partner Configuration']//sb-group[@id='selection']")).click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("Licensed OMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='NumeriTrack']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Localeze")) {

				driver.findElement(By
						.xpath("//*[@name='Business Registry Manager Service (BRM) - Monthly']//div[@id='radioContainer']"))
						.click();
				driver.findElement(By
						.xpath("//*[@name='Business Registry Manager Service (BRM) - Monthly']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				js.executeScript("arguments[0].value='1';", driver
						.findElement(By.xpath("//*[@label='Monthly Minimum Listing Volume #']//input[@type='text']")));
				js.executeScript("arguments[0].value='1';",
						driver.findElement(By.xpath("//*[@label='Monthly Minimum Fee $']//input[@type='text']")));

				driver.findElement(
						By.xpath("//*[@name='Monthly Minimum Listings Commitment']//sb-group[@id='selection']"))
						.click();

				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("Neustar Certificate Manager")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Certificate Authority']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Pathfinder")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Setup Fee']//sb-group[@id='selection']")).click();
				driver.findElement(By.xpath("//*[@id='radioContainer'][1]")).click();
				driver.findElement(By.xpath("//*[@name='Country Transaction - Onboard US']//sb-group[@id='selection']"))
						.click();

			}

			else if (productname.equals("PortPS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='Query Manager']//sb-group[@id='selection']")).click();
				driver.findElement(By
						.xpath("//*[@name='Query Manager']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				js.executeScript("arguments[0].value='1';", driver
						.findElement(By.xpath("//*[@label='Customers TN Ownership (Millions)']//input[@type='text']")));
				driver.findElement(By
						.xpath("//*[@label='Max Seats']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By.xpath("//option[@class='sbOption style-scope sb-select'][@title='5']")).click();
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			else if (productname.equals("PortPS RIMS")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@name='RIMS - API Only']//sb-group[@id='selection']")).click();

			}

			else if (productname.equals("Robocall Mitigation")) {
				js.executeScript("arguments[0].value='5';", driver.findElement(
						By.xpath("//*[@label='Scope of Records (Millions)']/div/div/sb-field/span/div/sb-input")));

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("SIP-IX")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

				driver.findElement(By.xpath("//*[@id='radioContainer'][1]")).click();
				driver.findElement(By
						.xpath("//*[@name='SIP-IX Subscribe Interface - Contract One']//span[@class='td sb-action-icon sf-icon-custom_apps   --desktop  style-scope sb-actions']"))
						.click();
				driver.findElement(By.xpath("//*[@id='radioContainer'][1]")).click();
				driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
				Thread.sleep(10000);

			}

			// 20 - else if(productname.equals("System Admin Guides")) { }

			else if (productname.equals("Trusted Call Vetting")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();
				driver.findElement(By.xpath("//*[@name='Enterprise Vetting']//sb-group[@id='selection']")).click();

			}

		} else if (sellinglane.equals("Security")) {
			// Security products
			if (productname.equals("DNS Audit")) {

				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("InfoBlox")) {

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

			}

			else if (productname.equals("IP Intelligence")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("Security Professional Services")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='One-time PreBill']"))
						.click();

			}

			else if (productname.equals("Threat Feeds")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

			}

			else if (productname.equals("UltraDDoS Protect")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

			}

			else if (productname.equals("UltraDNS")) {
				Thread.sleep(5000);
				// driver.findElement(By.xpath("//div[@class='tab-content
				// style-scope paper-tab'][text()='Dedicated
				// Resolvers']")).click();
				// driver.findElement(By.xpath("//*[@id='radioContainer']")).click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("UltraDNS Firewall")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

			}

			else if (productname.equals("Valimail")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly PreBill']"))
						.click();

			}

			else if (productname.equals("Web Performance Monitoring")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Arrears']"))
						.click();

			}

			else if (productname.equals("Webmetrics")) {
				driver.findElement(By
						.xpath("//*[@label='Billing Option']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(By
						.xpath("//option[@class='sbOption style-scope sb-select'][@title='Monthly Threshold with Overages']"))
						.click();

				driver.findElement(By
						.xpath("//*[@label='Billing Frequency']/div/div/sb-field/span/div/sb-select/select[@class='myselect style-scope sb-select --desktop']"))
						.click();
				driver.findElement(
						By.xpath("//option[@class='sbOption style-scope sb-select'][@title='Annual PreBill']")).click();

			}
		}

		if (!productname.equals("System Admin Guides")) {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@class='style-scope sb-product-config'][text()='Save']")).click();
		}
		if (productname.equalsIgnoreCase("UltraDNS Firewall")) {
			driver.findElement(By.xpath("//*[text()='Continue']")).click();
		}

		Thread.sleep(5000);
		// quote line configurations
		if (!startdate.isEmpty()) {
			;
			ql.getStartDate().click();
			String calendr = "SBQQ__StartDate__c";
			dateselection(startdate, calendr);
			Thread.sleep(5000);
		} else {
			String strtdate = ql.getselectedstart().getAttribute("value");
		}

		if (!Termstartdate.isEmpty()) {
			termstrtdate = Termstartdate;
			ql.getTermstartdate().click();
			String calendr = "In_Service_Date__c";
			dateselection(termstrtdate, calendr);
			Thread.sleep(5000);
		} else {
			termstrtdate = ql.getselectedterm().getAttribute("value");
		}

		ql.getSubscriptionterm().clear();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		ql.getSubscriptionterm().sendKeys(Subscriptionterm);
		Select s = new Select(ql.getFreeperiod());
		if (!freeperiod.isEmpty()) {
			s.selectByVisibleText(freeperiod);
			if (freeperiod.contains("1")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 1);
				} else
					startdate1 = startdate(Termstartdate, 1);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else if (freeperiod.contains("2")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 2);
				} else
					startdate1 = startdate(Termstartdate, 2);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else if (freeperiod.contains("3")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 3);
				} else
					startdate1 = startdate(Termstartdate, 3);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else if (freeperiod.contains("4")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 4);
				} else
					startdate1 = startdate(Termstartdate, 4);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else if (freeperiod.contains("5")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 5);
				} else
					startdate1 = startdate(Termstartdate, 5);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else if (freeperiod.contains("6")) {
				if (Termstartdate.isEmpty()) {
					stDate = startdateempty(termstrtdate);
					startdate1 = startdate(stDate, 6);
				} else
					startdate1 = startdate(Termstartdate, 6);
				expenddate = enddate(Subscriptionterm, startdate1);
			}
			System.out.println("Free Period" + freeperiod);
		} else {
			if (!Termstartdate.isEmpty()) {
				startdate1 = startdate(termstrtdate, 0);
				expenddate = enddate(Subscriptionterm, startdate1);
			} else {
				stDate = startdateempty(termstrtdate);
				startdate1 = startdate(stDate, 0);
				expenddate = enddate(Subscriptionterm, startdate1);
			}
		}
		Thread.sleep(5000);

		String tablestartdate = driver.findElement(By.xpath("(//*[@field='SBQQ__StartDate__c']//div)[2]")).getText();
		String tableenddate = driver.findElement(By.xpath("(//*[@field='SBQQ__EndDate__c']//div)[2]")).getText();
		Assert.assertEquals(tablestartdate, startdate1);
		Assert.assertEquals(tableenddate, expenddate);
		Thread.sleep(5000);
		// code for discount
		String quoteline = "";
		Double price = 0.0;
		if (!discount.isEmpty()) {
			ql.gettoggle().click();
			Thread.sleep(5000);
			// int y = driver.findElements(By.xpath("//*[@class='row style-scope
			// sf-le-table-row']")).size();
			for (int i = 2; i <= x + 1; i++) {
				WebElement LP = driver.findElement(By.xpath("(//*[@field='SBQQ__ListPrice__c'])[" + i + "]/div"));
				price = priceval(LP);
				if (price > 0) {
					Thread.sleep(5000);
					js.executeScript("arguments[0].click();",
							driver.findElement(By.xpath("(//*[@field='SBQQ__AdditionalDiscount__c'])[" + i + "]/div")));
					try {
						js.executeScript("arguments[0].click();", driver
								.findElement(By.xpath("(//*[@field='SBQQ__AdditionalDiscount__c'])[" + i + "]/div")));
					} catch (Exception e) {
						js.executeScript("arguments[0].click();", driver.findElement(
								By.xpath("(//*[@field='SBQQ__AdditionalDiscount__c'])[" + i + "]/div/span")));
					}
					Thread.sleep(1000);
					Select ds = new Select(ql.getdiscountoptions());
					Thread.sleep(1000);
					ds.selectByValue(discountoption);
					// ql.getdiscounttext().click();
					// ql.getdiscounttext().clear();
					// ql.getdiscounttext().click();
					Thread.sleep(1000);
					// js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@field='SBQQ__AdditionalDiscount__c'])["+i+"]/div")));
					ql.getdiscounttext().sendKeys(Keys.chord(Keys.CONTROL, "a"), discount);
					Thread.sleep(1000);
					js.executeScript("arguments[0].click();",
							driver.findElement(By.xpath("(//*[@field='Target_Local_Unit_Price__c'])[" + i + "]/div")));
					quoteline = driver
							.findElement(By
									.xpath("(((//*[@field='SBQQ__AdditionalDiscount__c'])[2])//preceding-sibling::div[@field='SBQQ__ProductName__c'])//span[2]"))
							.getText();
					ql.gettoggle().click();
				}

			}

		}

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

		if (!discount.isEmpty()) {
			Double disc = 0.0;
			qp.getRelated().click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@class='slds-media__body slds-align-middle']//*[@title='Quote Lines']"))
					.click();
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("((//*[@title='" + quoteline + "']//parent::span//parent::td)/preceding-sibling::th)//a"))
					.click();
			Thread.sleep(3000);
			driver.navigate().refresh();
			Double Discent = Double.parseDouble(discount);
			Double amt = 0.0;
			if (discountoption.equals("Percent")) {
				String per = driver
						.findElement(By
								.xpath("//*[@title='Edit Additional Disc. (%)']/preceding-sibling::span/slot/slot/lightning-formatted-number"))
						.getText();
				String p1 = per.replace("%", "");
				disc = Double.parseDouble(p1);
				// System.out.println(disc);
				amt = (price / 100) * Discent;

			}

			else {

				String usd = (String) js.executeScript("return arguments[0].text;", driver.findElement(By.xpath(
						"//*[@title='Edit Additional Disc. (Amt)']//preceding-sibling::span/slot/slot/lightning-formatted-text")));
				disc = priceval(driver.findElement(By.xpath(
						"//*[@title='Edit Additional Disc. (Amt)']//preceding-sibling::span/slot/slot/lightning-formatted-text")));
				// System.out.println(disc);
				amt = Double.parseDouble(discount);

			}
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);

			if (!discount.isEmpty()) {
				Double Total = priceval(driver.findElement(By.xpath(
						"(//*[contains(text(),'Regular Amount')]//parent::div)/following-sibling::div/span/slot/slot//lightning-formatted-text")));
				Double Actual = priceval(driver.findElement(By.xpath(
						"(//*[contains(text(),'Customer Amount')]//parent::div)/following-sibling::div/span/slot/slot//lightning-formatted-text")));

				if (Discent.equals(disc) && (Total - amt) == (Actual)) {
					System.out.println("Discount Applied");
					System.out.println("Total Amount Before Discount is:" + Total);
					System.out.println("Discount Amount is:" + amt);
					System.out.println("Total Amount After Discount is:" + Actual);

				} else {
					System.out.println("Discount not Applied");
					Assert.assertTrue(false);
				}
			}
		}
		// driver.close();
		return driver;

	}

	public void dateselection(String startdate, String calendr) {
		String indate = startdate;
		// System.out.println(indate);
		String array1[] = indate.split("/");
		String mo = array1[0];
		String outDate = "";
		SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		try {
			date = inSDF.parse(indate);
			outDate = outSDF.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate currentDate = LocalDate.parse(outDate);
		int day = currentDate.getDayOfMonth();
		// Get month from date
		Month month = currentDate.getMonth();
		// Get year from date
		int year = currentDate.getYear();

		String mon = month.toString();

		while (!driver.findElement(By.xpath("//*[@item='" + calendr + "'] //span[@id='monthName']")).getText()
				.contains(mon)) {
			driver.findElement(By
					.xpath("//*[@item='" + calendr + "'] //span[@class='sb-icon-right-dir style-scope sb-datepicker']"))
					.click();
		}
		Select s = new Select(driver.findElement(By.xpath("//*[@item='" + calendr + "'] //*[@id='years']")));
		// driver.findElement(By.xpath("//*[@item='"+calendr+"']
		// //*[@id='years']")).click();
		s.selectByVisibleText(String.valueOf(year));
		;
		try {
			driver.findElement(By.xpath(
					"//*[@item='" + calendr + "'] //*[@class='td  " + mo + "-" + day + " style-scope sb-datepicker']"))
					.click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@item='" + calendr + "'] //*[@class='td selected current " + mo + "-" + day
					+ " style-scope sb-datepicker']")).click();

		}

	}

	public String enddate(String Subscriptionterm, String startdate1) {

		String indate = startdate1;
		// System.out.println(startdate1);
		String outDate = "";
		String endDate = "";
		SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		try {
			date = inSDF.parse(indate);
			outDate = outSDF.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate currentDate = LocalDate.parse(outDate);
		LocalDate returnvalue = currentDate.plusMonths(Integer.parseInt(Subscriptionterm));
		LocalDate return2 = returnvalue.minusDays(1);
		String return1 = return2.toString();
		Date exdate;
		try {
			exdate = outSDF.parse(return1);
			endDate = inSDF.format(exdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String e = formatdate(endDate);
		System.out.println("End Date is : " + e);
		return e;

	}

	public String startdate(String Termstartdate, int x) {
		String indate = Termstartdate;
		String outDate = "";
		SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		try {
			date = inSDF.parse(indate);
			outDate = outSDF.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate currentDate = LocalDate.parse(outDate);
		LocalDate returnvalue = currentDate.plusMonths(x);
		String return1 = returnvalue.toString();
		Date exdate;
		try {
			exdate = outSDF.parse(return1);
			stDate = inSDF.format(exdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = formatdate(stDate);
		System.out.println("Start Date is : " + s);
		return s;

	}

	public String startdateempty(String termstrtdate) {
		SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
		LocalDate currentDate = LocalDate.parse(termstrtdate);
		String return1 = currentDate.toString();
		Date exdate;
		try {
			exdate = outSDF.parse(return1);
			stDate = inSDF.format(exdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stDate;
	}

	public String formatdate(String indate) {
		String array1[] = indate.split("/");
		String exendDate = "";
		if (array1[0].startsWith("0") || array1[1].startsWith("0")) {
			if (array1[0].startsWith("0") && !array1[1].startsWith("0")) {
				exendDate = indate.substring(1);
			}
			if (!array1[0].startsWith("0") && array1[1].startsWith("0")) {
				String part1 = array1[0];
				String part2 = array1[1].replace("0", "");
				String part3 = array1[2];
				exendDate = part1 + "/" + part2 + "/" + part3;

			}
			if (array1[0].startsWith("0") && array1[1].startsWith("0")) {
				String part1 = array1[0].replace("0", "");
				String part2 = array1[1].replace("0", "");
				String part3 = array1[2];
				exendDate = part1 + "/" + part2 + "/" + part3;
			}
			return exendDate;
		} else
			return indate;
	}

	public Double priceval(WebElement element) {
		String price = element.getText().substring(4);
		String pv = price.replace(",", "");
		Double pricevalue = Double.parseDouble(pv);
		return pricevalue;
	}
}
