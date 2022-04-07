package Regression;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.createOppPage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Regression.Base;

public class createOpportunity extends Base {
	
	@Test
	public void createOpportunity () throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		landingPage lp= new landingPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", lp.getAccount());
//		Thread.sleep(5000);
		waitForWebElementPresent(lp.getAcctbox());
		lp.getAcctbox().sendKeys(Accountname);
		lp.getAcctbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//lp.getsrchresult().click();
		//String Accountname = prop.getProperty("Accountname");
		driver.findElement(By.xpath("//*[text()='"
								+ Accountname
								+ "']")).click();
		accountPage ap= new accountPage(driver);
		Thread.sleep(20000);
		js.executeScript("arguments[0].click();",ap.getOppsCont());
//		Thread.sleep(5000);
		waitForWebElementPresent(ap.getOppsNew());
		js.executeScript("arguments[0].click();", ap.getOppsNew());
//		Thread.sleep(5000);
		createOppPage cop = new createOppPage(driver);
		waitForWebElementPresent(cop.getOppName());		
		cop.getOppName().sendKeys(opportunityname);
		cop.getcurrency().sendKeys(currency);
		Thread.sleep(5000);
//		cop.getcurrency().sendKeys(Keys.ENTER);
//		Thread.sleep(5000);
		
		String usd=currency;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[@title='"
									+ usd
									+ "'])[3]")));
		
		js.executeScript("arguments[0].click();", cop.getcloseDate());
		cop.getToday().click();
		
		js.executeScript("arguments[0].click();", cop.getstage());
		cop.getstage0().click();
		
		//String opptype=prop.getProperty("opptype");
		js.executeScript("arguments[0].click();", cop.getOppType());
		driver.findElement(By.xpath("//*[@title='"
									+ opptype
									+ "']")).click();;
									
		js.executeScript("arguments[0].click();",cop.getpilot());
		//String pilotvalue=prop.getProperty("pilotvalue");
		driver.findElement(By.xpath("//*[@title='"
											+ pilotvalue
											+ "']")).click();
		//String sellinglane=prop.getProperty("sellinglane");
		js.executeScript("arguments[0].click();", cop.getsellinglane());
		driver.findElement(By.xpath("//*[@title='"
				+ sellinglane
				+ "']")).click();;
		//cop.getSecuritySL().click();
		
		//String usecase=prop.getProperty("usecase");
		js.executeScript("arguments[0].click();", cop.getusecase());
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='"
				+ usecase
				+ "']")));
		//cop.getucDNS().click();
		//String entity=prop.getProperty("entity");
		js.executeScript("arguments[0].click();", cop.getentity());
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='"
				+ entity
				+ "']")));
		//cop.getenSSL().click();
		
		js.executeScript("arguments[0].click();", cop.getMSADate());
		cop.getToday().click();
		
		js.executeScript("arguments[0].click();", cop.getSODate());
		cop.getToday().click();
		
		//String primarycontact=prop.getProperty("primarycontact");
		js.executeScript("arguments[0].click();", cop.getprimcntct());
		cop.getprimcntct().sendKeys(primarycontact);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[contains(text(),'Primary Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
				+ primarycontact
				+ "']")).click();
		js.executeScript("arguments[0].click();", cop.getBillcntct());
		cop.getBillcntct().sendKeys(primarycontact);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//label[contains(text(),'Billing Contact')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
				+ primarycontact
				+ "']")).click();
		//System.out.println(cop.getfoundbychannel().isEnabled());
		if(cop.getfoundbychannel().isEnabled()){
		js.executeScript("arguments[0].click();",cop.getfoundbychannel());
		//String foundbychannel=prop.getProperty("foundbychannel");
		driver.findElement(By.xpath("(//*[@title='"
				+ foundbychannel
				+ "'])[2]")).click();
		}
		cop.getsave().click();
		getsuccessmessage();
//		Thread.sleep(10000);
		driver.close();
	
		
	}
	public  void getsuccessmessage(){
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if(sm.getText().contains("success"))
		System.out.println(msg.getText());
	}
}