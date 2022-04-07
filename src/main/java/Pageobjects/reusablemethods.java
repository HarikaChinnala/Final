package Pageobjects;

import java.awt.List;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;
import java.util.Set;

//import javax.swing.text.html.HTMLDocument.Iterator;

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
import java.util.Iterator;

public class reusablemethods extends Base {
	//private String userid;
	//Properties prop = new Properties();
	//public String userid;
	
	public reusablemethods(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}
	@Test
	public WebDriver generatedoc(WebDriver driver) throws InterruptedException {
		
		 int k= driver.findElements(By.xpath("//iframe")).size();
		 //System.out.println(k);
		 for(int b=0; b<k; b++){
		 	driver.switchTo().frame(b);
		 	//WebElement con =driver.findElement(By.xpath("//*[contains(text(),'Continue')]"));
		 	//if(driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).isEnabled()==true){
		 	try{	
		 		driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
		 	}
		 	catch (Exception e){
		 		
		 	
		 	 int m= driver.findElements(By.xpath("//iframe")).size();
		 	 //System.out.println(m);
		 	 if(m>0){
		 		for(int c=0; c<m; c++){
		     	driver.switchTo().frame(c); 
		        //if(driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).isEnabled()==true){	
		  try{	
			  driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
		        	}
		    	catch (Exception ex){
		    		
		    		int n= driver.findElements(By.xpath("//iframe")).size();
		    	 	// System.out.println(n);
		    	 	 if(n>0){
		    	 		for(int d=0; d<n; d++){
		    	     	driver.switchTo().frame(d); 
		    	        //if(driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).isEnabled()==true){	
		    	  try{	
		    		  driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
		    	        	}
		    	    	catch (Exception exc){
		    	    		
		      	driver.switchTo().defaultContent();
		    	}
		 		}
		        }
		 		else{
		 			driver.switchTo().defaultContent();
		 		}
		 	 }
		 	}
		 	 }
		 		else{
		 			driver.switchTo().defaultContent();
		 		}
		 	}
		 }
		 Thread.sleep(10000);
	        driver.switchTo().defaultContent();
	        Thread.sleep(20000); 
	       return driver;
	            }
	/*@Test
	/*public void waitmethod(){
	WebDriverWait wait = new WebDriverWait.WebDriverWait(WebDriver, Duration).
	wait.until(ExpectedConditions.visibilityOf(result));
		return;
		}*/
	@Test
	public void otherUserlogin(WebDriver driver) throws InterruptedException{
	// other user login
	/*	driver.findElement(By.xpath("//*[@class='setupGear']")).click();
	Thread.sleep(5000);
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='related_setup_app_home']")));
	Set<String> s = driver.getWindowHandles();
	Iterator<String> i = s.iterator();
    Thread.sleep(10000);
    while (i.hasNext()) {
        String ChildWindow = i.next();
            driver.switchTo().window(ChildWindow);
        }*/
    String URL= driver.getCurrentUrl();
    System.out.println(URL);
    //userid= prop.getProperty("userid");
    if(URL.contains("uat"))
    {
    	System.out.println(userid);
    driver.get("https://neustar--uat.lightning.force.com/"+userid);
    Thread.sleep(10000);
    }
    else{
    	System.out.println(userid);
    	driver.get("https://neustar--ltnstage.lightning.force.com/"+userid);	
    Thread.sleep(10000);
    }
	driver.findElement(By.xpath("//*[@title='User Detail']")).click();
	JavascriptExecutor js = (JavascriptExecutor)driver;
	Thread.sleep(10000);
	int j=driver.findElements(By.xpath("//iframe")).size();
	System.out.println(j);
    for(int a=0; a<j; a++){
    	driver.switchTo().frame(a);
	try{	
    		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
    		System.out.println("clicked login");
    		Thread.sleep(5000);
    		}
    	catch (Exception e){
    		
    		driver.switchTo().defaultContent();
    	}
    	
    }
	driver.switchTo().defaultContent();
	Thread.sleep(5000);
	return ;
		
	}
	@Test
	public  WebDriver getsuccessmessage(WebDriver driver){
		WebElement msg= driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if(sm.getText().contains("success"))
		System.out.println(msg.getText());
		return driver;
	}
	public WebDriver SubmitforSignature(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.xpath("//*[text()='Show more actions']//parent::button")).click();

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//span[text() = 'Submit For Signature']")));

		Thread.sleep(10000);
		// driver.findElement(By.xpath("(//*[@class='select'])[1]")).click()0
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(5000);
		// driver.findElement(By.xpath("(//*[@class='select'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("eSignature");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		return driver;
	}
	
}