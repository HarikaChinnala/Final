package Regression;

import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
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

public class amendmentMultipleSubmitEdit extends Base {
	public String type;

	@Test(priority = 1)
	public void Submitedit() throws IOException, InterruptedException {
		driver = initialiseDriver();
		
        contractPage c = new contractPage(driver);
        driver.get(amendurl);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(20000);

        type= driver.findElement(By.xpath("//*[text()='Opportunity Type']/parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
		System.out.println(type);
        
        driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click(); // Changed
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text() = 'Submit Edit']")));
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("Check and Approve");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@class='slds-card__footer']/button[@type='submit']")).click();
        Thread.sleep(10000);

        try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(10000);

        // Cases
        js.executeScript("window.scrollBy(0,150)", "");
        Actions act = new Actions(driver);
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();

        Thread.sleep(10000);
        driver.navigate().refresh();
        try {
            List<WebElement> Cases_List = driver.findElements(
                    By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]"));
            int Cases_Count = Cases_List.size();
            System.out.println("Number of Case records when Submit Edit is done = " + Cases_Count);
            if (Cases_Count == 1) {
                Assert.assertTrue(true);
            } else {
                Assert.assertFalse(true);
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath(
                    "//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]/parent::span/parent::td/preceding-sibling::th/span/a"))
                    .click();
            Thread.sleep(20000);
            System.out.println(c.Case_Owner().getText());
            Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
            driver.navigate().back();
        } catch (Exception e) {
        	
            System.out.println("No Case Records");
            Assert.assertFalse(true);
        }

        driver.navigate().back();
        Thread.sleep(10000);
        
        oppPage op = new oppPage(driver);
        String status = op.getAmendcontractstatus().getText();
        System.out.println(status);
        Assert.assertEquals(op.getAmendcontractstatus().getText(), "Edit Under Review");
		
		
		//code to check error message 
		driver.navigate().refresh();
		Thread.sleep(20000);
		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click();

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//span[text() = 'Submit For Signature']")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("(//*[@class='select'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).
		sendKeys("eSignature"); 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@type='submit']")).click(); 
		System.out.print("Error message when trying to do Submit for signature without approving Approval records: ");
		geterrormessage();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close']")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close this window']")));
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click();

		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//span[text() = 'eSignature']")));
		Thread.sleep(5000);
		
		System.out.print("Error message when trying to do eSignature without approving Approval records: ");
		geterrormessage();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close']")));
		Thread.sleep(10000);
		  
		 
		// Multiple Submit Edits
		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(10000);

        js.executeScript("window.scrollBy(0,150)", "");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();

        Thread.sleep(10000);

		// Approval Records
		driver.navigate().refresh();
		List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
		int Approval_Count = Approval_List.size();

		try {
			if (Approval_Count == 1) {
				System.out.println("Number of Approval records before Checking Legal checkbox = " + Approval_Count);
			}
		} 
		catch (Exception e) {
			System.out.println("Number of Approval records before Checking Legal checkbox = " + Approval_Count);
			Assert.assertTrue(false);
		}

		driver.navigate().back();
		Thread.sleep(10000);
		
		driver.navigate().refresh();
		Thread.sleep(20000);
		
		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(15000);

        js.executeScript("window.scrollBy(0,150)", "");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();
		
		driver.navigate().refresh();
		Thread.sleep(10000);

		changestatus(pendingcasestatus);
		changestatus(escalatedcasestatus);
		changestatus(closedcasestatus);
		
		//Approving the Last created Approval record to proceed forward with other steps
		// logout fron existing user
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();",
                driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
        Thread.sleep(10000);
        
     // login as admin

        loginPageObject l = new loginPageObject(driver);
        driver.navigate().refresh();
        Thread.sleep(10000);
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
        // contractPage c= new contractPage(driver);
        driver.get(amendurl);
        Thread.sleep(5000);
        try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(10000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();

        Thread.sleep(10000);

        // Approving the Approval Records
        try {
            //driver.navigate().refresh();
            //List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
            //int Approval_Count = Approval_List.size();
            System.out.println("Number of Approval records = " + Approval_Count);
            for (int i = 1; i <= Approval_Count; i++) {

                Thread.sleep(10000);
                c.Approvals_Requested().click();
                //Thread.sleep(5000);
                //driver.navigate().refresh();
                Thread.sleep(10000);
                c.Approve_Button().click();
                Thread.sleep(20000);
                int x = driver.findElements(By.xpath("//iframe")).size();
                // System.out.println(x);
                for (int j = 0; j < x; j++) {
                    driver.switchTo().frame(j);

                    try {
                        c.Approve_Text().sendKeys(ApprovalComments);
                        c.Approval_Submit().click();
                    }

                    catch (Exception e) {
                    	
                        driver.switchTo().defaultContent();
                    }
                    driver.switchTo().defaultContent();
                }
                Thread.sleep(15000);
                driver.navigate().refresh();
                Thread.sleep(20000);
                try {
        			driver.findElement(By.xpath("//*[text()='Related']")).click();
        		}
        		catch(Exception e) {
        		    js.executeScript("window.scrollBy(0,100)", "");
        		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
        		}
                Thread.sleep(10000);
                js.executeScript("window.scrollBy(0,150)", "");
                Thread.sleep(10000);
                driver.findElement(
                        By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                        .click();
                act.moveByOffset(10, 0).build().perform();
                Thread.sleep(10000);
                driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();
                Thread.sleep(10000);
            }

        } catch (Exception e) {
        	
            System.out.println("No Approval Records");
            Assert.assertFalse(true);

        }
        
        String statusAfterApproving = op.getAmendcontractstatus().getText();
        System.out.println(statusAfterApproving);
        Assert.assertEquals(op.getAmendcontractstatus().getText(), "Edit Complete");
		
		
		driver.close();
	}

	public void changestatus(String casestatus) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage c = new contractPage(driver);

		try {

			List<WebElement> Cases_List = driver.findElements(
					By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when SubmitEdit is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} 
			else {
				Assert.assertFalse(true);
			}
			Thread.sleep(10000);
			driver.findElement(By.xpath(
                    "//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]/parent::span/parent::td/preceding-sibling::th/span/a"))
                    .click();
            Thread.sleep(20000);
			System.out.println("Case Owner: " + c.Case_Owner().getText());
			Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
			System.out.println("Case status is: " + driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText());
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='" + casestatus + "']")));
			Thread.sleep(2000);
			System.out.println("Selected Case Status: " + casestatus);
			getsuccessmessage();
			driver.navigate().back();
		} 
		catch (Exception e) {
			System.out.println("No Case Records");
			Assert.assertFalse(true);
		}
		driver.navigate().back();
		Thread.sleep(5000);

		// logout fron existing user
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();",
                driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
        Thread.sleep(10000);
        
     // login as admin

        loginPageObject l = new loginPageObject(driver);
        driver.navigate().refresh();
        Thread.sleep(10000);
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
        // contractPage c= new contractPage(driver);
        driver.get(amendurl);
        Thread.sleep(5000);
        try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(10000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        Actions act = new Actions(driver);
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();

        Thread.sleep(10000);

        // Approving the Approval Records
        try {
            //driver.navigate().refresh();
            List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
            int Approval_Count = Approval_List.size();
            System.out.println("Number of Approval records = " + Approval_Count);
            for (int i = 1; i <= Approval_Count; i++) {

                Thread.sleep(10000);
                c.Approvals_Requested().click();
                //Thread.sleep(5000);
                //driver.navigate().refresh();
                Thread.sleep(10000);
                c.Approve_Button().click();
                Thread.sleep(20000);
                int x = driver.findElements(By.xpath("//iframe")).size();
                // System.out.println(x);
                for (int j = 0; j < x; j++) {
                    driver.switchTo().frame(j);

                    try {
                        c.Approve_Text().sendKeys(ApprovalComments);
                        c.Approval_Submit().click();
                    }

                    catch (Exception e) {
                    	
                        driver.switchTo().defaultContent();
                    }
                    driver.switchTo().defaultContent();
                }
                Thread.sleep(15000);
                driver.navigate().refresh();
                Thread.sleep(20000);
                try {
        			driver.findElement(By.xpath("//*[text()='Related']")).click();
        		}
        		catch(Exception e) {
        		    js.executeScript("window.scrollBy(0,100)", "");
        		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
        		}
                Thread.sleep(10000);
                js.executeScript("window.scrollBy(0,150)", "");
                Thread.sleep(10000);
                driver.findElement(
                        By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                        .click();
                act.moveByOffset(10, 0).build().perform();
                Thread.sleep(10000);
                driver.findElement(By.xpath("//span[contains(text(),'Approvals')]//parent::slot//parent::a")).click();
                Thread.sleep(10000);
            }

        } catch (Exception e) {
        	
            System.out.println("No Approval Records");
            Assert.assertFalse(true);

        }

        Thread.sleep(5000);

        // user login
        String URL = driver.getCurrentUrl();
        if (URL.contains("uat")) {
            // System.out.println(userid);
            driver.get("https://neustar--uat.lightning.force.com/" + userid1);
            Thread.sleep(10000);
        } else {
            // System.out.println(userid);
            driver.get("https://neustar--ltnstage.lightning.force.com/" + userid1);
            Thread.sleep(10000);
        }
        driver.findElement(By.xpath("//*[@title='User Detail']")).click();
        Thread.sleep(10000);
        int j = driver.findElements(By.xpath("//iframe")).size();
        //System.out.println(j);
        for (int a = 0; a < j; a++) {
            driver.switchTo().frame(a);
            try {
                js.executeScript("arguments[0].click();",
                        driver.findElement(By.xpath("//*[@id='topButtonRow']/input[4][@name='login']")));
                //System.out.println("clicked login");
                Thread.sleep(5000);
            } catch (Exception e) {
            	
                driver.switchTo().defaultContent();
            }

        }
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        // contracturl=prop.getProperty("contracturl");
        driver.get(amendurl);
        Thread.sleep(20000);
		
		
		
		
		driver.findElement(By.xpath("//span[contains(text(),'Show more actions')]//parent::button")).click(); // Changed
        Thread.sleep(10000);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text() = 'Submit Edit']")));
        Thread.sleep(20000);
//        driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).sendKeys("Check and Approve");
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@class='slds-card__footer']/button[@type='submit']")).click();
//        Thread.sleep(10000);
		
		c.SubmitEditDetails().sendKeys(SubmitEditDetails);
		Thread.sleep(5000);
		c.Submit_SubmitEdit().click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		
		try {
			driver.findElement(By.xpath("//*[text()='Related']")).click();
		}
		catch(Exception e) {
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.xpath("//*[text()='Related']")).click(); // Tag name changed
		}
        Thread.sleep(15000);

        // Cases
        js.executeScript("window.scrollBy(0,200)", "");
        
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@class='rlql-toggle slds-text-align_center']/a[contains(text(),'Show All')]"))
                .click();
        act.moveByOffset(10, 0).build().perform();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(text(),'Cases')]//parent::slot//parent::a")).click();

		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(
                "//table[@role='grid']/tbody/tr/td[3]/span/span[contains(text(),'Edit Request')]/parent::span/parent::td/preceding-sibling::th/span/a"))
                .click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		System.out.println("Case Owner: " + c.Case_Owner().getText());
		Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
		String cs=driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
		System.out.println("Case status is: "+cs);

		if (casestatus.contains("Pending")) {
			if (cs.equals("Unresponded/New"))
				System.out.println("Case status successfully moved to Unresponded/New");
			else
				Assert.assertTrue(false);
		} 
		else if (casestatus.contains("Closed")) {
			if (cs.equals("Unresponded/New"))
				System.out.println("Case status successfully moved to Unresponded/New");
			else
				Assert.assertTrue(false);
		}

		else if (casestatus.equals("Escalated")) {
			if (cs.equals("Escalated"))
				System.out.println("Case status is not changed from Escalated");
			else
				Assert.assertTrue(false);
		} 
		else
			Assert.assertTrue(false);
		driver.navigate().back();
	}

	public void geterrormessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		String msgct = msg.getText();
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		// if (sm.getText().contains("Error"))
		System.out.println(msgct);
	}

	public void getsuccessmessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if (sm.getText().contains("success"))
			System.out.println(msg.getText());
	}

}