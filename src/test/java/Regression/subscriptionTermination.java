package Regression;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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

public class subscriptionTermination extends Base {

	@Test(priority = 1)
	public void Termination() throws IOException, InterruptedException, ParseException {
		driver = initialiseDriver();
		
		Date date = cancdate;  
	    DateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
	    		//MMM d, yyyy");  
	    String cancelDate = dateFormat.format(date);
	    
		contractPage cp = new contractPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		userlogin();
		driver.get(contracturl);
		System.out.println("Contract Deal valuation - before termintaion values");
		WebElement acvcommit = driver.findElement(By.xpath(
				"(//*[contains(text(),'Contract - ACV Committed')]//parent::div)/following-sibling::div/span/span"));
		Double ac = priceval(acvcommit);
		System.out.println("ACV commited: "+ac);
		WebElement acvTotal = driver.findElement(By.xpath(
				"(//*[contains(text(),'Contract - ACV Total')]//parent::div)/following-sibling::div/span/span"));
		Double at = priceval(acvTotal);
		System.out.println("ACV Total: "+at);
		WebElement tcvcommit = driver.findElement(By.xpath(
				"(//*[contains(text(),'Contract - TCV Committed')]//parent::div)/following-sibling::div/span/span"));
		Double tc = priceval(tcvcommit);
		System.out.println("TCV commited: "+tc);
		WebElement tcvTotal = driver.findElement(By.xpath(
				"(//*[contains(text(),'Contract - TCV Total')]//parent::div)/following-sibling::div/span/span"));
		Double tt = priceval(tcvTotal);
		System.out.println("TCV Total: "+tt);
		Thread.sleep(15000);
		String status = driver.findElement(By.xpath("//*[@aria-checked='true']")).getAttribute("title");
		// System.out.println(status);
		if (status.equals("Activated")) {
			
			cp.Related_Tab().click();
			Thread.sleep(5000);
			cp.getsubscriptions().click();
			 Thread.sleep(10000);	int subs=driver.findElements(By.xpath("//tbody/tr/th/span/a")).size();
//			System.out.println(subs);
			 WebElement subscription= driver.findElement(By.xpath("//*[@title='"+subid +"']"));
				WebElement netprice=driver.findElement(By.xpath("//*[@title='"+subid+"']//parent::span//parent::th//following-sibling::td[8]/span/span"));
				Double npc=priceval(netprice);
				System.out.println("Net price of subscription selected is: "+npc);
			
			try{
			js.executeScript("arguments[0].click();",subscription);
			Thread.sleep(5000);
			}
			catch(Exception e){
				js.executeScript("scroll(0,500);");
				js.executeScript("arguments[0].click();",subscription);
				Thread.sleep(5000);	
			}
			
			waitForWebElementPresent(cp.getTerminatesub());
			cp.getTerminatesub().click();
			
			WebElement ceffdate = driver.findElement(By.xpath("//*[contains(@name,'Cancellation_Effective_Date')]"));
			// WebElement msg =
			// driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
			if (ceffdate.isEnabled()) {
				try{
					ceffdate.clear();
				ceffdate.sendKeys(cancelDate);}
				catch(Exception e){
				js.executeScript("arguments[0].value='"+cancelDate+"';",ceffdate);
				}
//				driver.findElement(By.xpath("//button[@name='today']")).click();
				driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")).click();
				driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")).clear();
				js.executeScript("arguments[0].value='terminationcomments';",driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")));
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='save']")));
				String msg=getsuccessmessage();
				if(msg.contains("The Subscription will be terminated.")){
					System.out.println(msg);
				}
				else{
					System.out.println(msg);
					Assert.assertTrue(false);
				}
				Thread.sleep(10000);
				driver.navigate().back();
				Thread.sleep(3000);
				driver.navigate().refresh();
				Thread.sleep(5000);
				String subtermdate= driver.findElement(By.xpath("//*[@title='"+subid+"']//parent::span//parent::th//following-sibling::td[9]/span/span")).getText();
				driver.navigate().back();
				Thread.sleep(5000);
				driver.navigate().refresh();
				Thread.sleep(25000);
				driver.navigate().refresh();
				Thread.sleep(30000);
					String canctype = driver
							.findElement(By
									.xpath("(//span[contains(text(),'Cancellation Type')]//parent::div)/following-sibling::div/span/span"))
							.getText();
					System.out.println("Cancellation type is " + canctype);
						Double EXPac=ac-npc;
						Double EXPat=at-npc;
						Double EXPtc=tc-npc;
						Double EXPtt=tt-npc;
						System.out.println("Contract Deal valuation - After termintaion Expected values");
						System.out.println("ACV commited: "+EXPac);
						System.out.println("ACV Total: "+EXPat);
						System.out.println("TCV commited: "+EXPtc);
						System.out.println("TCV Total: "+EXPtt);
						WebElement acvcommit1 = driver.findElement(By.xpath(
								"(//*[contains(text(),'Contract - ACV Committed')]//parent::div)/following-sibling::div/span/span"));
						WebElement acvTotal1 = driver.findElement(By.xpath(
								"(//*[contains(text(),'Contract - ACV Total')]//parent::div)/following-sibling::div/span/span"));
						WebElement tcvcommit1 = driver.findElement(By.xpath(
								"(//*[contains(text(),'Contract - TCV Committed')]//parent::div)/following-sibling::div/span/span"));
						WebElement tcvTotal1 = driver.findElement(By.xpath(
								"(//*[contains(text(),'Contract - TCV Total')]//parent::div)/following-sibling::div/span/span"));
						Double ACTac = priceval(acvcommit1);
						Double ACTat = priceval(acvTotal1);
						Double ACTtc = priceval(tcvcommit1);
						Double ACTtt = priceval(tcvTotal1);	
						System.out.println("Contract Deal valuation - After termintaion actual values");
						System.out.println("ACV commited: "+ACTac);
						System.out.println("ACV Total: "+ACTat);
						System.out.println("TCV commited: "+ACTtc);
						System.out.println("TCV Total: "+ACTtt);
//					String subtermdate= driver.findElement(By.xpath("(//td/span/span[@class='slds-truncate uiOutputDate'])[3]")).getText();
 
						float diff= daysdiff();
						System.out.println("Diff from current date: "+diff);
						if(diff<=0){
							//System.out.println(subtermdate);
							//System.out.println(cancdate);
							
							if(subtermdate.equals(cancelDate)){
					if (EXPac.equals(ACTac) && EXPat.equals(ACTat) && EXPtc.equals(ACTtc) && EXPtt.equals(ACTtt) && canctype.equals("Partial")){
						System.out.println("Subscription terminated successfully");
					} else
					{
						System.out.println("Subscription is not terminated");
					Assert.assertTrue(false);
					}
						}
							else
								Assert.assertTrue(false);	
						}
						else 
							System.out.println("Subscription will be future terminated");	
				}
			

		else {
			WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
			System.out.println(msg.getText());
		}
	}
	else{
		System.out.println("Contract stage is: " + status );
		cp.Related_Tab().click();
		Thread.sleep(5000);
		cp.getsubscriptions().click();
		 Thread.sleep(10000);	int subs=driver.findElements(By.xpath("//tbody/tr/th/span/a")).size();
//		System.out.println(subs);
		 WebElement subscription= driver.findElement(By.xpath("//*[@title='"+subid +"']"));
		try{
		js.executeScript("arguments[0].click();",subscription);
		Thread.sleep(5000);
		}
		catch(Exception e){
			js.executeScript("scroll(0,500);");
			js.executeScript("arguments[0].click();",subscription);
			Thread.sleep(5000);	
		}
		
		cp.getTerminatesub().click();
		
		WebElement ceffdate = driver.findElement(By.xpath("//*[contains(@name,'Cancellation_Effective_Date')]"));
		if (ceffdate.isEnabled()) {
			try{
				ceffdate.clear();
			ceffdate.sendKeys(cancelDate);}
			catch(Exception e){
			js.executeScript("arguments[0].value='"+cancelDate+"';",ceffdate);
			}
			driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")).click();
			driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")).clear();
			js.executeScript("arguments[0].value='terminationcomments';",driver.findElement(By.xpath("//*[contains(@name,'Termination_Comments')]")));
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name='save']")));
			String msg=getsuccessmessage();
			System.out.println(msg);}
		else
			System.out.println(getsuccessmessage());
	}
		driver.close();

	}

	public void contractstatus() {
		contractPage cp = new contractPage(driver);
		String terminate = cp.getTermchev().getAttribute("aria-selected");
		if (terminate.equals("true")) {
			System.out.println("Contract stage is moved to Terminated");
		} else
			System.out.println("Contract stage is not moved to Terminated");

	}

	public Double priceval(WebElement element) {
		// oppPage op= new oppPage(driver);
		String price = element.getText().substring(4);
		String pv = price.replace(",", "");
		Double pricevalue = Double.parseDouble(pv);
		// System.out.println(pricevalue);
		return pricevalue;
	}

	public void userlogin() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
		Thread.sleep(10000);
		// login as admin

		loginPageObject l = new loginPageObject(driver);
		l.getusername().sendKeys(usernameadmin);
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			Thread.sleep(1000);
			// driver.navigate().refresh();
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
			Thread.sleep(1000);
		}
		String URL = driver.getCurrentUrl();
		// System.out.println(URL);
		// userid= prop.getProperty("userid");
		if (URL.contains("uat")) {
			// System.out.println(userid);
			driver.get("https://neustar--uat.lightning.force.com/" + userid);
			Thread.sleep(10000);
		} else {
			// System.out.println(userid);
			driver.get("https://neustar--ltnstage.lightning.force.com/" + userid);
			Thread.sleep(10000);
		}
		driver.findElement(By.xpath("//*[@title='User Detail']")).click();

		Thread.sleep(10000);
		int j = driver.findElements(By.xpath("//iframe")).size();
		// System.out.println(j);
		for (int a = 0; a < j; a++) {
			driver.switchTo().frame(a);
			try {
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
				// System.out.println("clicked login");
				Thread.sleep(5000);
			} catch (Exception e) {

				driver.switchTo().defaultContent();
			}

		}
		driver.switchTo().defaultContent();
		Thread.sleep(5000);

	}

	public static String gettodaydate() {

		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todayDate = dateFormat.format(cal.getTime());
		return todayDate;
	}

	/*
	 * public String getPastDate(){ int n= Integer.parseInt(pastdate);
	 * DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); Date myDate =
	 * new Date(System.currentTimeMillis()); Date oneDayBefore = new
	 * Date(myDate.getTime() - (n * 24 * 60 * 60 * 1000)); String dateStr =
	 * dateFormat.format(oneDayBefore); return dateStr;
	 * 
	 * }
	 */
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

	public float daysdiff() throws ParseException {
		
		Date date = cancdate;  
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
	    String cancelDate = dateFormat.format(date);
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateBeforeString = gettodaydate();
		String dateAfterString = cancelDate;

		Date dateBefore = (Date) myFormat.parse(dateBeforeString);
		Date dateAfter = (Date) myFormat.parse(dateAfterString);
		long difference = dateAfter.getTime() - dateBefore.getTime();
		float daysBetween = (difference / (1000 * 60 * 60 * 24));
		/*
		 * You can also convert the milliseconds to days using this method float
		 * daysBetween = TimeUnit.DAYS.convert(difference,
		 * TimeUnit.MILLISECONDS)
		 */
//		System.out.println("Number of Days between dates: " + daysBetween);
		return daysBetween;

	}
}