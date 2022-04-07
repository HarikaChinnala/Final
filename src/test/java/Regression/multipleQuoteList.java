package Regression;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pageobjects.contractPage;
import Pageobjects.landingPage;
import Pageobjects.oppPage;
import Pageobjects.reusablemethods;
import Regression.Base;

public class multipleQuoteList extends Base {

	@Test(priority=1)
	public void multiplequotecreation() throws IOException, InterruptedException {
		
		
		createOpportunity copp = new createOpportunity();
		createQuoteandConfigure cqc = new createQuoteandConfigure();
		
	    copp.createOpportunity();
		  	
		 for(int i=1; i<=2;i++){ 
		  cqc.createQuote(); 
		  cqc.configureQuote(); 
		  //cqc.generatequoteproposal();
		  //cqc.approveQuote();
		 }
	}
	@Test(priority=2)
	public void editprimaryquote() throws IOException, InterruptedException {
	//	 write code to load opp page
		driver = initialiseDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		landingPage lp = new landingPage(driver);
		js.executeScript("arguments[0].click();", lp.getOpportunities());
		Thread.sleep(5000);
		lp.getopptxtbox().sendKeys(opportunityname);
		Thread.sleep(5000);
		lp.getopptxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@title='"+ opportunityname+ "']")).click();
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//*[@title='Edit Primary Quote']"))).build().perform();
Thread.sleep(5000);
		String newquote = driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span")).getText();
		System.out.println("Primary Quote is : " + newquote);
		// write code to change primary quote
		//js.executeScript("scroll(0,1250);");
		try{
			a.moveToElement(driver.findElement(By.xpath("//lst-common-list//span[@title='Quotes']"))).build().perform();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//lst-common-list//span[@title='Quotes']")));}
		catch(Exception e){
			a.moveToElement(driver.findElement(By.xpath("//lst-common-list-internal//span[@title='Quotes']"))).build().perform();
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//lst-common-list-internal//span[@title='Quotes']")));
		}
		Thread.sleep(5000);

	Thread.sleep(5000);
	driver.navigate().refresh();
	Thread.sleep(5000);
	waitForWebElementPresent(driver.findElement(By.xpath("//*[@title='Change Owner']")));
	List <WebElement> checkbox=driver.findElements(By.xpath("//*[@class='uiImage uiOutputCheckbox']"));
	int x=checkbox.size();
	if(x>1){
		for(int i=1; i<=x; i++){
			WebElement box=driver.findElement(By.xpath("(//*[@class='uiImage uiOutputCheckbox'])["+i+"]/img"));
			String checked=box.getAttribute("alt");
			if(checked.equals("True")){}
			else{
				WebElement drop=driver.findElement(By.xpath("((//*[@class='uiImage uiOutputCheckbox'])["+i+"]//parent::span//parent::td)/following-sibling::td[5]"));
				WebElement quot=driver.findElement(By.xpath("((//*[@class='uiImage uiOutputCheckbox'])["+i+"]//parent::span//parent::td)/preceding-sibling::th/span/a"));
				String quotetitle=quot.getAttribute("title");
				System.out.println("Quote selected is : "+quot.getAttribute("title"));
				drop.click();
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='uiMenuItem']//*[@title='Edit']")));
				Thread.sleep(15000);
			//	waitForWebElementPresent(driver.findElement(By.xpath("//*[text()='Authentication']")));
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='SBQQ__Primary__c']")));
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='SaveEdit']")));
				WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
				waitForWebElementPresent(msg);
				System.out.println(msg.getText());
				Thread.sleep(10000);
				driver.navigate().refresh();
				Thread.sleep(10000);
				waitForWebElementPresent(driver.findElement(By.xpath("//*[@title='Change Owner']")));
				WebElement test=driver.findElement(By.xpath("(//*[@title='"+quotetitle+"']//parent::span//parent::th//following-sibling::td[4])/span/span/img"));
				String chck= test.getAttribute("alt");
				if(chck.equals("True"))
					System.out.println("Primary Quote is changed from "+newquote+ " to "+quotetitle);
				else
					Assert.assertTrue(false);
				driver.navigate().back();
//				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@name='CancelEdit']")));
				driver.navigate().back();
				driver.navigate().back();
				Thread.sleep(5000);
				js.executeScript("scroll(0,1600);");
				Assert.assertEquals(driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span")).getText(), quotetitle);
				System.out.println("Primary quote in Opportunity record is updated to "+driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a/slot/slot/span")).getText());
				break;
				
				
				
			}
				
		}
		
	}
	else{
		System.out.println("Opportunity has only one Quote");
	}
	
		driver.close();

		}
}