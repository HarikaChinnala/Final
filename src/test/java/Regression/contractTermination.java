package Regression;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

public class contractTermination extends Base {
	 
	
	@Test(priority = 1)
	public void Termination() throws IOException, InterruptedException, ParseException {
		driver = initialiseDriver();
		Date date = cancdate;  
	    DateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");  
	    String cancelDate = dateFormat.format(date);
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		userlogin();
		driver.get(contracturl);
		Thread.sleep(15000);
		String type = driver
				.findElement(By
						.xpath("//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span"))
				.getText();
		System.out.println(type);

		String status = driver.findElement(By.xpath("//*[@aria-checked='true']")).getAttribute("title");
		// System.out.println(status);
		if (status.equals("Activated")) {
			cp.Dropdown().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			js.executeScript("arguments[0].click();", cp.getTerminatecon());
			WebElement ceffdate = driver.findElement(By.xpath("//*[contains(@name,'Cancellation_Effective_Date__c')]"));
			// WebElement msg =
			// driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
			if (ceffdate.isEnabled()) {
				ceffdate.clear();
				
				ceffdate.sendKeys(cancelDate);
				driver.findElement(By.xpath("//*[contains(@name,'TerminationComments__c')]")).clear();
				driver.findElement(By.xpath("//*[contains(@name,'TerminationComments__c')]"))
						.sendKeys(terminationcomments);
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='save']")));
				String msg=getsuccessmessage();
				if(msg.contains("will be terminated.")){
					System.out.println(msg);
				}
				else{
					System.out.println(msg);
					Assert.assertTrue(false);
				}
				Thread.sleep(10000);
				
				driver.navigate().refresh();
				Thread.sleep(30000);
				float diff= daysdiff();
				if (type.equals("Service Order")) {
					String canctype = driver
							.findElement(By
									.xpath("(//span[contains(text(),'Cancellation Type')]//parent::div)/following-sibling::div/span/span"))
							.getText();
					System.out.println("Cancellation type is " + canctype);
					WebElement acvcommit = driver.findElement(By.xpath(
							"(//*[contains(text(),'Contract - ACV Committed')]//parent::div)/following-sibling::div/span/span"));
					Double ac = priceval(acvcommit);
					// System.out.println(ac);
					WebElement acvTotal = driver.findElement(By.xpath(
							"(//*[contains(text(),'Contract - ACV Total')]//parent::div)/following-sibling::div/span/span"));
					Double at = priceval(acvTotal);
					// System.out.println(at);
					WebElement tcvcommit = driver.findElement(By.xpath(
							"(//*[contains(text(),'Contract - TCV Committed')]//parent::div)/following-sibling::div/span/span"));
					Double tc = priceval(tcvcommit);
					// System.out.println(tc);
					WebElement tcvTotal = driver.findElement(By.xpath(
							"(//*[contains(text(),'Contract - TCV Total')]//parent::div)/following-sibling::div/span/span"));
					Double tt = priceval(tcvTotal);
					// System.out.println(tt);
					cp.Related_Tab().click();
					Thread.sleep(5000);
					cp.getsubscriptions().click();
					 Thread.sleep(5000);
					String subtermdate= driver.findElement(By.xpath("(//td/span/span[@class='slds-truncate uiOutputDate'])[3]")).getText();
					System.out.println("Diff from current date: "+diff);
					if(diff<=0){
					if(subtermdate.equals(cancelDate)){
						driver.navigate().back();
					 Assert.assertTrue(true);}
					 else
						 driver.navigate().back();
				//		 Assert.assertTrue(false);	 
					contractstatus();
					if (ac == 0.0 && at == 0.0 && tc == 0.0 && tt == 0.0 && canctype.equals("Full")) {
						System.out.println("Contract terminated successfully");
					} else{
						System.out.println("Contract is not terminated");
					Assert.assertTrue(false);
				}
					
					}
					else
						System.out.println("Contract will be future terminated");
					
				}	
// Non revenue accounts				
				else{
					if(diff<=0){
						contractstatus();
							System.out.println("Contract terminated successfully");
					}
						else
							System.out.println("Contract will be future terminated");
					
				}
				

			}

		else {
			WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			System.out.println(msg.getText());
		}
	}else
	{
		System.out.println("Contract stage is" + status);
		cp.Dropdown().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		js.executeScript("arguments[0].click();", cp.getTerminatecon());
		WebElement ceffdate = driver.findElement(By.xpath("//*[contains(@name,'CancellationEffectiveDate')]"));
		// WebElement msg =
		// driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if (ceffdate.isEnabled()) {
			ceffdate.clear();
			ceffdate.sendKeys(cancelDate);
			driver.findElement(By.xpath("//*[contains(@name,'TerminationComments')]")).clear();
			driver.findElement(By.xpath("//*[contains(@name,'TerminationComments')]"))
					.sendKeys(terminationcomments);
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='save']")));
			String msg=getsuccessmessage();
			System.out.println(msg);
		}
		else
			System.out.println(getsuccessmessage());
	}
		driver.close();

	}

	public void contractstatus(){
		contractPage cp= new contractPage(driver);
		String terminate=cp.getTermchev().getAttribute("aria-checked");
		  if (terminate.equals("true")){
	        	System.out.println("Contract stage is moved to Terminated");
	        }
	        else 
	        	System.out.println("Contract stage is not moved to Terminated");
	     
	}

	public Double priceval(WebElement element) {
		// oppPage op= new oppPage(driver);
		String price = element.getText().substring(4);
		String pv = price.replace(",", "");
		Double pricevalue = Double.parseDouble(pv);
//		System.out.println(pricevalue);
		return pricevalue;
	}
	
	public void userlogin() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		//login as admin
		
		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
			}
			catch(Exception e) {
				Thread.sleep(1000);
			 // driver.navigate().refresh();
			}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		}
			catch(Exception e) { 
				Thread.sleep(1000);
		}
		String URL= driver.getCurrentUrl();
	    //System.out.println(URL);
	    //userid= prop.getProperty("userid");
	    if(URL.contains("uat"))
	    {
	    	//System.out.println(userid);
	    driver.get("https://neustar--uat.lightning.force.com/"+userid);
	    Thread.sleep(10000);
	    }
	    else{
	    	//System.out.println(userid);
	    	driver.get("https://neustar--ltnstage.lightning.force.com/"+userid);	
	    Thread.sleep(10000);
	    }
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();
		
		Thread.sleep(10000);
		int j=driver.findElements(By.xpath("//iframe")).size();
		//System.out.println(j);
	    for(int a=0; a<j; a++){
	    	driver.switchTo().frame(a);
		try{	
	    		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
	    		//System.out.println("clicked login");
	    		Thread.sleep(5000);
	    		}
	    	catch (Exception e){
	    		
	    		driver.switchTo().defaultContent();
	    	}
	    	
	    }
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
	}
	private static String gettodaydate(){  
	      
		Calendar cal = Calendar.getInstance();  
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		String todayDate = dateFormat.format(cal.getTime()); 	
		return todayDate;  
		} 
	public float daysdiff() throws ParseException{
		Date date = cancdate;  
	    DateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");  
	    String cancelDate = dateFormat.format(date);
	    
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
		 String dateBeforeString = gettodaydate();
		 String dateAfterString = cancelDate;

		
		       Date dateBefore = (Date) myFormat.parse(dateBeforeString);
		       Date dateAfter = (Date) myFormat.parse(dateAfterString);
		       long difference = dateAfter.getTime() - dateBefore.getTime();
		       float daysBetween = (difference / (1000*60*60*24));
	               /* You can also convert the milliseconds to days using this method
	                * float daysBetween = 
	                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
	                */
	//	       System.out.println("Number of Days between dates: "+daysBetween);
		      return daysBetween;
		    	
	}
	
	public String getsuccessmessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		String msgct = msg.getText();
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if (sm.getText().contains("success"))
			return msgct;
	String msgcterr = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"))
				.getText();
		return msgcterr;

	}
}