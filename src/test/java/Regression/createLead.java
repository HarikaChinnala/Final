package Regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

public class createLead extends Base {

	@Test(priority=1)
	public void createLead() throws IOException, InterruptedException
	{
		driver= initialiseDriver();
//		Thread.sleep(2000);
		landingPage lp= new landingPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", lp.getLeads());
//		Thread.sleep(5000);
		waitForWebElementPresent(lp.getleadnew());
		lp.getleadnew().click();;
		createLeadPage clp= new createLeadPage(driver);
//		Thread.sleep(5000);
		
		waitForWebElementPresent(clp.getfirstname());
		clp.getfirstname().sendKeys(firstname);
		clp.getlastname().sendKeys(lastname);
		clp.getemail().sendKeys(email);
		clp.getcompany().sendKeys(company);
		clp.getphone().sendKeys(String.valueOf(phone));
		clp.getwebsite().sendKeys(website);
		clp.getstreet().sendKeys(String.valueOf(street));
		clp.getcity().sendKeys(city);
		clp.getstate().sendKeys(state);
		clp.getpostalcode().sendKeys(String.valueOf(postalcode));
		clp.getcountry().sendKeys(country);
		clp.getsave().click();
		getsuccessmessage();
		Thread.sleep(10000);
	}
	
	//Edit Lead
	@Test(priority=2)
	public void editLead() throws IOException, InterruptedException
	{
		Thread.sleep(5000);	
		contactPage cp= new contactPage(driver);
		WebElement edit = driver.findElement(By.xpath("//*[@name='Edit']"));
	waitForWebElementPresent(edit);
	edit.click();
	Thread.sleep(10000);
	cp.getfirstname().clear();
	cp.getfirstname().sendKeys(newLeadName);
	cp.getsavecontact().click();
	getsuccessmessage();
	Thread.sleep(5000);
	}
	
		//convert lead
		@Test(priority=3)
		public void convertLead() throws IOException, InterruptedException
		{
			Thread.sleep(5000);	
		createLeadPage clp= new createLeadPage(driver);
		waitForWebElementPresent(clp.getconvert());
		clp.getconvert().click();
		Thread.sleep(10000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@id='opptyCheckBox']")));
		driver.findElement(By.xpath("//*[@id='opptyCheckBox']")).click();		
		waitForWebElementPresent(clp.getcon());
		clp.getcon().click();
		Thread.sleep(20000);
		waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='title']/span")));
		System.out.println(driver.findElement(By.xpath("//*[@class='title']/span")).getText());

//	driver.close();
		
	}
		public void getsuccessmessage(){
			WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			waitForWebElementPresent(msg);
			WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
			if(sm.getText().contains("success"))
			System.out.println(msg.getText());
		}
}