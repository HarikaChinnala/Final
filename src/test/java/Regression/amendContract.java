package Regression;

import java.awt.List;
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

public class amendContract extends Base {
	
	@Test
	public void amendContract() throws IOException, InterruptedException
	{
		driver= initialiseDriver();
		createAmendmentOpportunity cap=new createAmendmentOpportunity();
		oppPage op= new oppPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get(amendurl);
		Thread.sleep(15000);
		String amendstatus = op.getAmendcontractstatus().getText();
		
		if(amendstatus.equals("Activated"))
		{
			System.out.println(amendstatus);
		}
		else{
			System.out.println(amendstatus);
			Thread.sleep(5000);
		Actions action= new Actions(driver);
		
		//action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	//	Thread.sleep(10000);
		//((JavascriptExecutor)driver).executeScript("scroll(0,4000);");
		action.moveToElement(op.getAmendcontractstatus()).build().perform();
		action.doubleClick(op.getAmendcontractstatus()).build().perform();
	//	js.executeScript("window.scrollBy(0,50)", "");
		Thread.sleep(5000);
		//action.moveToElement(driver.findElement(By.xpath("((//*[contains(text(),'Amendment Contract Status')]//parent::div)/following-sibling::div)/span/slot/slot/lightning-formatted-text"))).build().perform();
		//Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,80)", "");
		Thread.sleep(10000);
		//driver.findElement(By.xpath("//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div")).click();
		try{
			driver.findElement(By.xpath("//*[@title='Edit Amendment Contract Status']")).click();
		}
		catch(Exception e){
			driver.findElement(By.xpath("//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div")).click();
		}
		js.executeScript("window.scrollBy(0,80)", "");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div/lightning-base-combobox/div/div/input")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[contains(text(),'Amendment Contract Status')]//parent::lightning-combobox/div/lightning-base-combobox/div/div)/following-sibling::div/lightning-base-combobox-item[@data-value='Activated']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", op.getSaveedit());
		}
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
	
		js.executeScript("arguments[0].click();", op.getClosedchev());
		js.executeScript("arguments[0].click();", op.selectstage);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='modal-footer slds-modal__footer']/button[2]")));
		Thread.sleep(10000);
		String act=op.get7won().getAttribute("aria-checked");
		  if (act.equals("true")){
	        	System.out.println("Opportunity is moved to 7-won stage");
	        }
	        else 
	        	System.out.println("Opportunity is not moved to 7-won stage");
		  
		  js.executeScript("arguments[0].click();", op.getcontract());
		  Thread.sleep(10000);
		  js.executeScript("arguments[0].click();", op.getcontractsave());
		  //Thread.sleep(5000);
		  //driver.navigate().refresh();
		  Thread.sleep(10000);
		  
		  ((JavascriptExecutor)driver).executeScript("scroll(0,1000);");
		  Thread.sleep(5000);
		  js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//*[contains(text(),'Amended Contract')]//parent::div)/following-sibling::div/span/slot/slot/force-lookup/div/force-hoverable-link/div/a")));
		  Thread.sleep(10000); 
		  
		  String amendcontract = driver.findElement(By.xpath("((//*[contains(text(),'Latest Amendment Opportunity')])/parent::div)/following-sibling::div/span")).getText();
		  
		  if(amendcontract.contains("Amendment for contract"))
		  {
			  System.out.println(amendcontract);
			  System.out.println("contract is amended");
		  }
		  else
		  {
			  System.out.println("contract is not amended"); 
		  }
		  driver.close();
		
	}
}