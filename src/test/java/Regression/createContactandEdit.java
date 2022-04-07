package Regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Pageobjects.accountPage;
import Pageobjects.contactPage;
import Pageobjects.createLeadPage;
import Pageobjects.createOppPage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Regression.Base;

public class createContactandEdit extends Base {
	
	
	@Test(priority=1)
	public void createContact() throws IOException, InterruptedException
	{
			driver= initialiseDriver();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			contactPage cp= new contactPage(driver);
			landingPage lp= new landingPage(driver);
		js.executeScript("arguments[0].click();", lp.getcontacts());
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", cp.getNewContact());
		cp.getfirstname().sendKeys(firstname1);
		cp.getlastnamee().sendKeys(lastname1);
		cp.getemail().sendKeys(email1);
		cp.getAccountname().sendKeys(Accountname1);
		Thread.sleep(5000);
//		cp.getAccountname().sendKeys(Keys.ENTER);
//		Thread.sleep(5000);
		driver.findElement(By.xpath("//div/ul/li/lightning-base-combobox-item/span/span/lightning-base-combobox-formatted-text[@title='"
				+ Accountname1
				+ "']")).click();
		
		cp.getcountry().sendKeys(country);
		cp.getsavecontact().click();
		getsuccessmessage();
		Thread.sleep(5000);
		driver.navigate().refresh();

	}
	//edit contact
	@Test(priority=2)
	public void editContact() throws IOException, InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		contactPage cp= new contactPage(driver);		
		landingPage lp= new landingPage(driver);
		js.executeScript("arguments[0].click();", lp.getcontacts());
//		Thread.sleep(5000);
		//String existingcontactname = prop.getProperty("existingcontactname");
		driver.findElement(By.xpath("//*[text()='"
				+ existingcontactname
				+ "']")).click();
//		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", cp.getedit());
			cp.getfirstname().clear();
			cp.getfirstname().sendKeys(newfirstname);
			cp.getsavecontact().click();
			getsuccessmessage();
			Thread.sleep(5000);
			driver.close();
		
	}
	public void getsuccessmessage(){
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if(sm.getText().contains("success"))
		System.out.println(msg.getText());
	}
}