package Regression;

import java.util.List;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

//import javax.swing.text.html.HTMLDocument.Iterator;

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
import Pageobjects.reusablemethods;
import Regression.Base;
import java.util.Iterator;

public class eSignature extends Base {
	//Properties prop = new Properties();
	reusablemethods rm= new reusablemethods(driver);
	//JavascriptExecutor js = (JavascriptExecutor)driver;
	//contractPage cp= new contractPage(driver);
	private String type;
	@Test
	public void eSignature() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		contractPage cp= new contractPage(driver);
		
		//driver.get("https://neustar--ltnstage.lightning.force.com/lightning/r/echosign_dev1__SIGN_Agreement__c/a9Z7j000000CilwEAC/view");
		//contracturl=prop.getProperty("contracturl");
		driver.get(contracturl);
		Thread.sleep(10000);
		type= driver.findElement(By.xpath("//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span")).getText();
		System.out.println("Contract Type is: "+type);
		
		 js.executeScript("arguments[0].click();", cp.getRelated()); 
	        Thread.sleep(10000);

			((JavascriptExecutor)driver).executeScript("scroll(0,700);");
			Thread.sleep(10000);
	        
	        WebElement upload = driver.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));
	       
	        
	        if(upload.isDisplayed())
	        {
	        	System.out.println("File is not attached");
		
		cp.getdropdown().click();
		js.executeScript("arguments[0].click();", cp.getGenerateDoc());
		 Thread.sleep(80000);
		 rm.generatedoc(driver);
		
	        Thread.sleep(10000);
	        js.executeScript("arguments[0].click();", cp.getRelated()); 
	        Thread.sleep(10000);
			((JavascriptExecutor)driver).executeScript("scroll(0,800);");
			Thread.sleep(10000);
	        String file=cp.getFiles().getAttribute("title");
       	 System.out.println(file);
   		if(file.endsWith("- "
						+ contractid
						+ ".docx"))
   			System.out.println("File is attached");
	        }
	        else
	        {
	        	String file=cp.getFiles().getAttribute("title");
	        	 System.out.println(file);
        		if(file.endsWith("- "
							+ contractid
							+ ".docx"))
     
        System.out.println("File is attached");
	        }
	        
	        
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
			//rm.otherUserlogin(driver);
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
			
			driver.get(contracturl);
			Thread.sleep(10000);
	        cp.getdropdown().click();
		
		js.executeScript("arguments[0].click();", cp.getesign());
		Thread.sleep(80000);
//		waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='slds-truncate'][contains(text(),'Sign in Order')]")));
		String agreementname= cp.getagreement().getText();
		System.out.println("Agreement Name:"+agreementname);
		System.out.println("Agreement Status:"+cp.getagreementstat().getText());
//		js.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('display');",checkbox).toString();
		if(type.equals("Service Order")){
//		String acc = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[1]/div/div/div//input")).getAttribute("placeholder"));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[1]/div/div/div//input")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[2]/div/div/div//input")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[3]/div/div/div//input")));
		Set<String> s = driver.getWindowHandles();
		Iterator<String> i = s.iterator();
		String parent=i.next();
	    Thread.sleep(10000);
	    while (i.hasNext()) {
	    	
	    	String child1=i.next();
	    	String child2=i.next();
	    	String child3=i.next();
	//    	System.out.println(driver.getCurrentUrl());
	            driver.switchTo().window(child1);
	            Thread.sleep(10000);
	            System.out.println(driver.getCurrentUrl());
	            waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(text(),'Contract Number')]")));
	            String Con= driver.findElement(By.xpath("//*[contains(text(),'Contract Number')]//parent::div/following-sibling::div/span/span")).getText();
	            System.out.println("Contract id is :"+Con);
	            driver.close();
	            driver.switchTo().window(child2);
	            Thread.sleep(10000);
	            System.out.println(driver.getCurrentUrl());
	            waitForWebElementPresent(driver.findElement(By.xpath("//*[@slot='primaryField']")));
	            String Oppty= driver.findElement(By.xpath("//*[@slot='primaryField']")).getText();
	            System.out.println("Opportunity Name is :"+Oppty);
	            driver.close();
	    	    Assert.assertEquals(Oppty, opportunityname);
	            
	            driver.switchTo().window(child3);
	            Thread.sleep(10000);
	            System.out.println(driver.getCurrentUrl());
	            waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']"))); 
	            String Acc= driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']")).getText();
	            System.out.println("Account Name is :"+Acc);
	            driver.close()  ;   
	            Assert.assertEquals(Con, contractid);
	    	    Assert.assertEquals(Acc, Accountname);
	    
	    driver.switchTo().window(parent);
	    }
		}
	    else{
	    	js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[1]/div/div/div//input")));
		//	js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[2]/div/div/div//input")));
			js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//*[@class='slds-form_inline']/*[@class='esign-top-object-lookup-section slds-form-element slds-grid']/div)[3]/div/div/div//input")));
			Set<String> s = driver.getWindowHandles();
			Iterator<String> i = s.iterator();
			String parent=i.next();
		    Thread.sleep(10000);
		    while (i.hasNext()) {
		    	
		    	String child1=i.next();
		    	String child2=i.next();
		//    	String child3=i.next();
	//	    	System.out.println(driver.getCurrentUrl());
		            driver.switchTo().window(child1);
		            Thread.sleep(10000);
		            System.out.println(driver.getCurrentUrl());
		            waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(text(),'Contract Number')]")));
		            String Con= driver.findElement(By.xpath("//*[contains(text(),'Contract Number')]//parent::div/following-sibling::div/span/span")).getText();
		            System.out.println("Contract id is :"+Con);
		            driver.close();
		            driver.switchTo().window(child2);
		            Thread.sleep(10000);
		            System.out.println(driver.getCurrentUrl());
		            waitForWebElementPresent(driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']"))); 
		            String Acc= driver.findElement(By.xpath("//*[@class='custom-truncate uiOutputText']")).getText();
		            System.out.println("Account Name is :"+Acc);
		            driver.close()  ;   
		            Assert.assertEquals(Con, contractid);
		    	    Assert.assertEquals(Acc, Accountname);
		    
		    driver.switchTo().window(parent);
		    }

	    	
	    }
		js.executeScript("arguments[0].click();", cp.getNext());
		Thread.sleep(30000);
		System.out.println(driver.getCurrentUrl());
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
	    Thread.sleep(10000);
	    while (i1.hasNext()) {
	        String Window1 = i1.next();
	        String Window2=i1.next();
	        driver.switchTo().window(Window2);
	        System.out.println("switched to childwindow");
//	        Thread.sleep(10000);
	        System.out.println(driver.getCurrentUrl());
	        List <WebElement> page= driver.findElements(By.xpath("//*[@id='document']/ul/Li"));
	        int x=page.size();
//	        int check=0;
	        /*	        for (int i=1; i<=x; i++) {
	      try{  
	      WebElement sign=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+i+"]//*[@data-fieldname='_es_signer1_signature']"));
	      WebElement title=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+i+"]//*[@data-fieldname='_es_signer1_title']"));
//	      js.executeScript("arguments[0].click();", sign);
//	      js.executeScript("arguments[0].click();", title);
	      check++;
	      break;
	      }
	      catch(Exception e)
	     {
	    	  
	      }
	        }
	      if(check==1){
	    	  System.out.println("Signature fields are available");
	      }
	      else{
	    	  
	    	  js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
		  		Thread.sleep(5000);
		    	  waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(@id,'modal-title-view')]")));
		    	  System.out.println(driver.findElement(By.xpath("//*[@class='modal-body']")).getText());
		    	  js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Review')]")));
		    	  System.out.println("Drag and drop Signature Fields");
		    	  Thread.sleep(5000);
		    	WebElement signdrag=driver.findElement(By.xpath("//*[@class='rhp-panel-list-item draggable-form-field field-signature ui-draggable-handle']"));
		    	WebElement inidrag=driver.findElement(By.xpath("//*[@class='rhp-panel-list-item draggable-form-field field-initials ui-draggable-handle']"));
		    	WebElement signdrop=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+x+"]"));
		    	WebElement inidrop=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+x+"]"));
		    	Actions a = new Actions(driver);
		    	a.dragAndDrop(signdrag, signdrop).build().perform();
		    	Thread.sleep(5000);
		    	a.dragAndDrop(inidrag, inidrop).build().perform();
	    	
	      }
	*/      
	      js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
		Thread.sleep(5000);
		try{
			waitForWebElementPresent(driver.findElement(By.xpath("//*[contains(@id,'modal-title-view')]")));
	    	  System.out.println(driver.findElement(By.xpath("//*[@class='modal-body']")).getText());
	    	  js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Review')]")));
	    	  System.out.println("Drag and drop Signature Fields");
	    	  Thread.sleep(5000);
	    	WebElement signdrag=driver.findElement(By.xpath("//*[@class='rhp-panel-list-item draggable-form-field field-signature ui-draggable-handle']"));
	    	WebElement inidrag=driver.findElement(By.xpath("//*[@class='rhp-panel-list-item draggable-form-field field-initials ui-draggable-handle']"));
	    	WebElement signdrop=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+x+"]"));
	    	WebElement inidrop=driver.findElement(By.xpath("//*[@id='document']/ul/Li["+x+"]"));
	    	Actions a = new Actions(driver);
	    	a.dragAndDrop(signdrag, signdrop).build().perform();
	    	Thread.sleep(5000);
	    	a.dragAndDrop(inidrag, inidrop).build().perform();
	    	js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.send-action ")));
	    	Thread.sleep(10000);
		}
		catch(Exception e){
			
		}
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@class='control-button']")));
		Thread.sleep(20000);		
		driver.switchTo().window(Window1);
		Thread.sleep(5000);
	    }
	    System.out.println("Agreement Name after sent for customer signature:"+agreementname);
		System.out.println("Agreement Status after sent for customer signature:"+cp.getagreementstat().getText());
		
		Thread.sleep(5000);
		//((JavascriptExecutor)driver).executeScript("scroll(0,500);");
		//js.executeScript("arguments[0].click();",cp.getcontractlink());
		driver.get(contracturl);		
	Thread.sleep(10000);
		/*
		 js.executeScript("arguments[0].click();",cp.getcontractlink());
		 Set<String> s2 = driver.getWindowHandles();
		
		Iterator<String> i2 = s2.iterator();
	    Thread.sleep(10000);
	    while (i2.hasNext()) {
	    	String parentwindow2 = driver.getWindowHandle();
	        String ChildWindow1 = i2.next();
	        String ChildWindow2 = i2.next();
	            driver.switchTo().window(ChildWindow2);
	            System.out.println("switched to childwindow"); */ 
		 js.executeScript("arguments[0].click();", cp.getRelated()); 
	        Thread.sleep(10000);
	     
	        Actions a= new Actions(driver);
	        a.sendKeys(Keys.PAGE_DOWN).build().perform();
	        a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
	        driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
	        Thread.sleep(5000);
	  	       try{
	    	   List<WebElement> agmnts= driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/th/span/a[contains(@title,'"+agreementname+"')]"));
	    	   int count = agmnts.size();	
	    	   if(count>=1){
	    	   String agreementstatus= driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[4]")).getText();
						if(agreementstatus.equals("Out for Signature"))
							System.out.println("Agreement Status : "+agreementstatus);
						else{
							System.out.println("Agreement Status : "+agreementstatus);
							Assert.assertTrue(false);
						}
	    		   
	       }
	    	   else{
	    		   System.out.println("No agreements found");
	    	   Assert.assertTrue(false);
	    	   }
	       }
	       catch (Exception exc){
	    	   Thread.sleep(1000);
	       }
	       driver.navigate().back();
	       
					
					String PCS = cp.getPCSchev().getAttribute("aria-checked");
			        //System.out.println(PCS);
			        if (PCS.equals("true")){
			        	System.out.println("Stage moved to Pending Customer Signature");
			        }
			        else 
			        	System.out.println("Stage not moved to Pending Customer Signature");
			        
			      //loop to compensate manual interruption for signing document
			        for(int m=0;m<5;m++)
			        {
			        	driver.navigate().refresh();
			        	Thread.sleep(20000);
			        	
			        }
			        
			        js.executeScript("arguments[0].click();", cp.getRelated()); 
			        Thread.sleep(10000);
			        a.sendKeys(Keys.PAGE_DOWN).build().perform();
			        a.moveToElement(driver.findElement(By.xpath("//a/span[@title='Agreements']"))).build().perform();
			        driver.findElement(By.xpath("//a/span[@title='Agreements']")).click();
			        Thread.sleep(5000);
			        try{
				    	   List<WebElement> agmnts= driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/th/span/a[contains(@title,'"+agreementname+"')]"));
				    	   int count = agmnts.size();	
				    	   if(count>=1){
					    	   String agreementstatus= driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[4]")).getText();
										if(agreementstatus.equals("Signed"))
											System.out.println("Agreement Status: "+agreementstatus);
										else{
											System.out.println("Agreement Status: "+agreementstatus);
											Assert.assertTrue(false);
										}     }
				    	   else{
				    		   System.out.println("No agreements found");
				    	   Assert.assertTrue(false);
				    	   }
				       }		
		       catch (Exception exc){
		    	   Thread.sleep(1000);
		       }
			        driver.navigate().back();
			        String CS = cp.getCSchev().getAttribute("aria-checked");
			        //System.out.println(CS);
			        if (CS.equals("true")){
			        	System.out.println("Stage moved to Client Signed");
			        }
			        else {
			        	System.out.println("Stage not moved to Client Signed");   
			        	Assert.assertTrue(false);
			        }
			        driver.close();
	       }
	}
