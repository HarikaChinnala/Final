package Regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import Pageobjects.accountPage;
import Pageobjects.createLeadPage;
import Pageobjects.createOppPage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Regression.Base;

public class createAccountandEdit extends Base {
	
	
	@Test(priority=1)
	public void createAccount() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		
		landingPage lp= new landingPage(driver);
		accountPage ap= new accountPage(driver);
		waitForWebElementPresent(lp.getAccount());
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", lp.getAccount());
//		Thread.sleep(5000);
		
		js.executeScript("arguments[0].click();", ap.getNewAccount());
		ap.getAccountNamefield().sendKeys(NewAccountname);
		ap.getwebsite().sendKeys(websitename);
		ap.getsaveAccount().click();
		getsuccessmessage();	
		Thread.sleep(5000);
	driver.navigate().refresh();
//	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
	@Test(priority=2)
	public void editAccount() throws IOException, InterruptedException
	{
//	Thread.sleep(5000);
		
		
	JavascriptExecutor js = (JavascriptExecutor)driver;
	landingPage lp= new landingPage(driver);
	accountPage ap= new accountPage(driver);
	
			js.executeScript("arguments[0].click();", lp.getAccount());
			waitForWebElementPresent(driver.findElement(By.xpath("//*[@title='Account Name']")));
//			Thread.sleep(10000);
			//String existingaccount = prop.getProperty("existingaccountname");
			driver.findElement(By.xpath("//*[text()='"
									+ existingaccountname
									+ "']")).click();
			waitForWebElementPresent(ap.getedit());
			ap.getedit().click();
			ap.getwebsite().clear();
			ap.getwebsite().sendKeys(websitename);
//			Thread.sleep(3000);
			ap.getsaveAccount().click();
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