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

public class clm_SubmitEdit extends Base {
	public String type;

	@Test(priority = 1)
	public void clm_SubmitEdit() throws IOException, InterruptedException {
		driver = initialiseDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		loginPageObject l = new loginPageObject(driver);
		contractPage c = new contractPage(driver);
		driver.get(contracturl);
		
		Thread.sleep(35000);
		
		type= driver.findElement(By.xpath("//*[@class='test-id__field-label'][starts-with(text(),'Type')]//parent::div/following-sibling::div/span/span")).getText();
		System.out.println(type);
		
		if(upload.equals("no")) {  
		//To Check the status of the Chevron
		if(c.Chevron_Draft.getAttribute("class").contains("active")) {
			Assert.assertTrue(true); 
		}
		else { 
			Assert.assertTrue(false); 
		}
		  
		//Details_Status
		System.out.println("Initial Contract Status in Details Section = "+c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Draft");
		  
		//Contract_Status System.out.println("Initial Contract Status = "+c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Draft");
		  
		//Submit Edit
		c.Dropdown().click(); 
		  
		js.executeScript("arguments[0].click();", c.SubmitEdit());
		  
		c.SubmitEditDetails().sendKeys(SubmitEditDetails);
		Thread.sleep(5000);
		c.Submit_SubmitEdit().click();
		}  
		for(int i=1;i<=5;i++) { 
			if(c.Details_Status().getText().equalsIgnoreCase("Draft") && c.Contract_Status().getText().equalsIgnoreCase("Draft")) { 
				driver.navigate().refresh(); 
				Thread.sleep(25000); 
			}
			else{ 
				System.out.println(i); 
				break;
			} 
		}
		  
		//To Check the status of the Chevron
		if(c.Chevron_EditUnderReview.getAttribute("class").contains("active")) {
		  Assert.assertTrue(true); 
		 }
		else {
			Assert.assertTrue(false); 
		}
		  
		//Details_Status System.out.println("Contract Status after doing Submit Edit in Details Section = "+c.Details_Status().getText());
		Assert.assertEquals(c.Details_Status().getText(), "Edit Under Review");
		  
		//Contract_Status
		System.out.println("Contract Status after doing Submit Edit = "+c.Contract_Status().getText());
		Assert.assertEquals(c.Contract_Status().getText(), "Edit Under Review"); 
		
		
		//code to check error message 
		c.Dropdown().click(); 
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", c.SubmitForSignature());
		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[@id='parent_Signature_Tool__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Neustar Adobe Signature']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='parent_Signing_Order__c2']/div/div")).click();
		driver.findElement(By.xpath("//*[@title='Customer then Neustar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='slds-form-element']/div/textarea")).
		sendKeys("eSignature"); 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@type='submit']")).click(); System.out.print("Error message when trying to do Submit for signature without approving Approval records: ");
		geterrormessage();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close']")));
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close this window']")));
		Thread.sleep(10000);
		c.getdropdown().click();
		js.executeScript("arguments[0].click();",c.getesign());
		System.out.print("Error message when trying to do eSignature without approving Approval records: ");
		geterrormessage();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title='Close']")));
		Thread.sleep(10000);
		  
		c.Related_Tab().click();
	
		if(type.equals("Legal")){
			//js.executeScript("arguments[0].scrollIntoView(true);",c.Cases_Section());
			//c.Cases_Section().click();
			try {
				c.Cases_Section().click();
			}
			catch(Exception e) {
				Actions act = new Actions(driver);
				act.moveToElement(c.Cases_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Cases_Section().click();
			}
			List<WebElement> Cases_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Edit Request')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit Edit is done = " +Cases_Count);
			Assert.assertEquals(Cases_Count,0);
			
			driver.navigate().back();
			Thread.sleep(10000);
			 
			driver.navigate().refresh();
			Thread.sleep(20000);
			
			c.Related_Tab().click();
			Thread.sleep(5000);
			try {
			c.Approvals_Section().click();
			}
			catch(Exception e) {
				Thread.sleep(5000);
				Actions act = new Actions(driver);
				act.moveToElement(c.Approvals_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Approvals_Section().click();
			}

			// Approval Records
			driver.navigate().refresh();
			List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
			int Approval_Count = Approval_List.size();

			try {
				if (Approval_Count == 1) {
					System.out.println("Number of Approval records = " + Approval_Count);
				}
			} 
			catch (Exception e) {
				System.out.println("Number of Approval records = " + Approval_Count);
				Assert.assertTrue(false);
			}
			// logout from existing user
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='User']")));
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a.profile-link-label.logout.uiOutputURL")));
			Thread.sleep(10000);
			
			// login as admin
			//loginPageObject l = new loginPageObject(driver);
			l.getusername().sendKeys(usernameadmin);
			l.getpassword().sendKeys(pwd);
			l.getLogin().click();
			try {
				l.clickskip().click();
			} 
			catch (Exception e) {
				Thread.sleep(1000);
			}
			Thread.sleep(3000);
			try {
				driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
			} 
			catch (Exception e) {
				Thread.sleep(1000);
			}
			
			driver.get(contracturl);
			Thread.sleep(10000);
			
			c.Related_Tab().click();
			try {
				c.Approvals_Section().click();
				}
				catch(Exception e) {
					Thread.sleep(5000);
					Actions act = new Actions(driver);
					act.moveToElement(c.Approvals_Section()).build().perform();
					js.executeScript("window.scrollBy(0,175)","");
					c.Approvals_Section().click();
				}

			// Approving the Approval Records
			try {
				//driver.navigate().refresh();
				//List<WebElement> Approval_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
				//int Approval_Count = Approval_List.size();
				//System.out.println("Number of Approval records = " + Approval_Count);
				for (int i = 1; i <= Approval_Count; i++) {
					if(Approval_Count==1) {
					
						//a.approving();
						
						c.Approvals_Requested().click();
						Thread.sleep(5000);
					
						WebElement Rule = driver.findElement(By.xpath("//*[text()='Rule']/parent::div/following-sibling::div/span/slot/slot/force-lookup/div/force-hoverable-link/div/a[@class='flex-wrap-ie11']/slot/slot/span"));
						if(Rule.getText().contains("Legal"))
						{
							System.out.println(Rule.getText());
							
//							try {
//								c.Approve_Button().click();
//								Thread.sleep(2000);
//								System.out.println(driver.findElement(By.xpath("//*[@class='toastMessage forceActionsText']")).getText());
//							}
//							catch(Exception e){
//								System.out.println("User able to Approve even without DGQ");
//								Assert.assertTrue(false);
//							}
//							
//							
//							driver.findElement(By.xpath("//a[text()='Related']")).click();
//							Thread.sleep(5000);
//							driver.findElement(By.xpath("//span[contains(text(),'Data')]/parent::a/parent::h2/parent::div/parent::div/parent::div/following-sibling::div[2]")).click();
//							driver.findElement(By.xpath("//*[@placeholder='Search Contracts...']")).sendKeys(contractid);
//							driver.findElement(By.xpath("//label[contains(text(),'Contract')]/following-sibling::div[1]/div[1]/lightning-base-combobox/div/div[2]/ul/li/lightning-base-combobox-item/span[2]/span/lightning-base-combobox-formatted-text[@title='"
//									+ contractid
//									+ "']")).click();
//							driver.findElement(By.xpath("//*[text()='Paper Source']/following-sibling::div")).click();
//							driver.findElement(By.xpath("//*[@title='Third-Party Paper']")).click();
//							driver.findElement(By.xpath("//*[@name='SaveEdit']")).click();
//							Thread.sleep(10000);
							
							
//							driver.navigate().refresh();
//							Thread.sleep(10000);
					
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
							Thread.sleep(10000);	
							driver.navigate().refresh();
							Thread.sleep(10000);
					}
					else {
						Assert.assertTrue(false);
					}
				}
			} 
			}
			catch(Exception e) {
				Assert.assertTrue(false);
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
			Thread.sleep(10000);
			// contracturl=prop.getProperty("contracturl");
			driver.get(contracturl);

			for(int i=1;i<=5;i++) { 
				if(c.Details_Status().getText().equalsIgnoreCase("Edit Under Review") && c.Contract_Status().getText().equalsIgnoreCase("Edit Under Review")) { 
					driver.navigate().refresh(); 
					Thread.sleep(25000); 
				}
				else{ 
					System.out.println(i); 
					break;
				} 
			}

			Thread.sleep(10000);
			// To Check the status of the Chevron
			if (c.Chevron_EditComplete.getAttribute("class").contains("active")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

			// Details_Status
			System.out.println("Contract Status after Approving the Approval records in Details Section = "
					+ c.Details_Status().getText());
			Assert.assertEquals(c.Details_Status().getText(), "Edit Complete");

			// Contract_Status
			System.out.println("Contract Status after Approving the Approval records = " + c.Contract_Status().getText());
			Assert.assertEquals(c.Contract_Status().getText(), "Edit Complete");
			
			driver.close();
		}
		  
		else if(type.equals("Master Service Agreement (MSA)")||type.equals("Reseller Partner Agreement")){
			try {
				c.Cases_Section().click();
			}
			catch(Exception e) {
				Actions act = new Actions(driver);
				act.moveToElement(c.Cases_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Cases_Section().click();
			}
			List<WebElement> Cases_List =driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Edit Request')]"));
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit Edit is done = " +Cases_Count);
			Assert.assertEquals(Cases_Count,1);
			
			multiple();
			
		}
	
	}
	public void geterrormessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		String msgct = msg.getText();
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		// if (sm.getText().contains("Error"))
		System.out.println(msgct);
	}
	public void multiple() throws InterruptedException {
		contractPage c = new contractPage(driver);
		//contractMultipleSubmitEdit m = new contractMultipleSubmitEdit();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.navigate().back();
		Thread.sleep(5000);
		 
		driver.navigate().refresh();
		Thread.sleep(20000);
		// Multiple Submit Edits
		c.Related_Tab().click();
		try {
			c.Approvals_Section().click();
			}
			catch(Exception e) {
				Thread.sleep(5000);
				Actions act = new Actions(driver);
				act.moveToElement(c.Approvals_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Approvals_Section().click();
			}

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
		c.Related_Tab().click();
		try {
			c.Cases_Section().click();
		}
		catch(Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(c.Cases_Section()).build().perform();
			js.executeScript("window.scrollBy(0,175)","");
			c.Cases_Section().click();
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(10000);

		changestatus(pendingcasestatus);
		changestatus(escalatedcasestatus);
		changestatus(closedcasestatus);

		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		WebElement legal = driver.findElement(By.xpath("//span[text()='Legal Approval']//parent::div"));
		js.executeScript("arguments[0].scrollIntoView(true);",legal);
		js.executeScript("window.scrollBy(0,-200)", "");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[text()='Legal Approval']//parent::div//following-sibling::div/button")).click();
		Thread.sleep(5000);
		
		//js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[text()='Legal Approval']//parent::label//following-sibling::input")));
		driver.findElement(By.xpath("//span[text()='Legal Approval']//parent::label//following-sibling::input")).click();
		driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']")).click();
		Thread.sleep(10000);

		driver.navigate().refresh();
		Thread.sleep(10000);
		
		c.Dropdown().click();
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@title = 'Submit for Approval']//*[@role='button']")));
		Thread.sleep(15000);

		int TotalCheckBox = driver.findElements(By.xpath("//span[text()='Approval Information']//parent::button//parent::h3//following-sibling::div/div/div/div/div/div[2]/span/span[@class='uiImage uiOutputCheckbox']")).size();
		int Checked = driver.findElements(By.xpath("//span[text()='Approval Information']//parent::button//parent::h3//following-sibling::div/div/div/div/div/div[2]/span/span[@class='uiImage uiOutputCheckbox']/img[@class=' checked']")).size();

		c.Related_Tab().click();
		try {
			c.Approvals_Section().click();
			}
			catch(Exception e) {
				Thread.sleep(5000);
				Actions act = new Actions(driver);
				act.moveToElement(c.Approvals_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Approvals_Section().click();
			}
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		List<WebElement> Approval_List_1 = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr"));
		int Approval_Count_1 = Approval_List_1.size();
		driver.navigate().back();
		Thread.sleep(5000);
		try {
			if (Approval_Count_1 == Checked) {
				System.out.println("After checking Legal Checkbox");
				System.out.println("TotalCheckBoxes = " + TotalCheckBox);
				System.out.println("TotalCheckBoxes Checked = " + Checked);
				c.Related_Tab().click();
				try {
					c.Cases_Section().click();
				}
				catch(Exception e) {
					Actions act = new Actions(driver);
					act.moveToElement(c.Cases_Section()).build().perform();
					js.executeScript("window.scrollBy(0,175)","");
					c.Cases_Section().click();
				}
				Thread.sleep(5000);
				driver.navigate().refresh();
				Thread.sleep(10000);

				changestatus(pendingcasestatus);
				//changestatus(escalatedcasestatus);
				//changestatus(closedcasestatus);
			}
		} 
		catch (Exception e) {
			Assert.assertTrue(false);
		}
		
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
		        driver.get(contracturl);
		        Thread.sleep(5000);
		        c.Related_Tab().click();
		        try {
					c.Approvals_Section().click();
					}
					catch(Exception e) {
						Thread.sleep(5000);
						Actions act = new Actions(driver);
						act.moveToElement(c.Approvals_Section()).build().perform();
						js.executeScript("window.scrollBy(0,175)","");
						c.Approvals_Section().click();
					}
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
		                c.Related_Tab().click();
		                try {
		        			c.Approvals_Section().click();
		        			}
		        			catch(Exception e) {
		        				Thread.sleep(5000);
		        				Actions act = new Actions(driver);
		        				act.moveToElement(c.Approvals_Section()).build().perform();
		        				js.executeScript("window.scrollBy(0,175)","");
		        				c.Approvals_Section().click();
		        			}
		        		Thread.sleep(10000);
		            }

		        } catch (Exception e) {
		        	
		            System.out.println("No Approval Records");
		            Assert.assertFalse(true);

		        }
		        
		        driver.navigate().back();
		        Thread.sleep(5000);
		        driver.navigate().refresh();
		        Thread.sleep(20000);
		        
		        for(int i=1;i<=5;i++) { 
					if(c.Details_Status().getText().equalsIgnoreCase("Edit Under Review") && c.Contract_Status().getText().equalsIgnoreCase("Edit Under Review")) { 
						driver.navigate().refresh(); 
						Thread.sleep(25000); 
					}
					else{ 
						System.out.println(i); 
						break;
					} 
				}
				
				Thread.sleep(10000);
				//To Check the status of the Chevron
				if(c.Chevron_EditComplete.getAttribute("class").contains("active"))
				{
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
				
				//Details_Status
				System.out.println("Contract Status after Approving the Approval records in Details Section = " +c.Details_Status().getText());
				Assert.assertEquals(c.Details_Status().getText(), "Edit Complete");
				
				//Contract_Status
				System.out.println("Contract Status after Approving the Approval records = " +c.Contract_Status().getText());
				Assert.assertEquals(c.Contract_Status().getText(), "Edit Complete");
	
		driver.close();
	}
	public void changestatus(String casestatus) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage c = new contractPage(driver);

		try {

			List<WebElement> Cases_List = driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/td[3]/span/a[contains(@title,'Edit Request')]"));
			
			int Cases_Count = Cases_List.size();
			System.out.println("Number of Case records when Submit Edit is done = " + Cases_Count);
			if (Cases_Count == 1) {
				Assert.assertTrue(true);
			} 
			else {
				Assert.assertFalse(true);
			}
			Thread.sleep(10000);
			c.Case_SubmitEdit().click();
			Thread.sleep(10000);
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
		Thread.sleep(10000);
		
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
        driver.get(contracturl);
        Thread.sleep(5000);
        c.Related_Tab().click();
        try {
			c.Approvals_Section().click();
			}
			catch(Exception e) {
				Thread.sleep(5000);
				Actions act = new Actions(driver);
				act.moveToElement(c.Approvals_Section()).build().perform();
				js.executeScript("window.scrollBy(0,175)","");
				c.Approvals_Section().click();
			}

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
                Thread.sleep(20000);
		        driver.navigate().refresh();
				Thread.sleep(20000);
				c.Related_Tab().click();
				Thread.sleep(10000);
				try {
					c.Approvals_Section().click();
					}
					catch(Exception e) {
						Thread.sleep(5000);
						Actions act = new Actions(driver);
						act.moveToElement(c.Approvals_Section()).build().perform();
						js.executeScript("window.scrollBy(0,175)","");
						c.Approvals_Section().click();
					}
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
        driver.get(contracturl);
        Thread.sleep(20000);
		
		
		
		
		c.Dropdown().click();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		js.executeScript("arguments[0].click();", c.SubmitEdit());

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		c.SubmitEditDetails().sendKeys(SubmitEditDetails);
		Thread.sleep(5000);
		c.Submit_SubmitEdit().click();
		Thread.sleep(20000);
		driver.navigate().refresh();
		Thread.sleep(20000);
		c.Related_Tab().click();
		try {
			c.Cases_Section().click();
		}
		catch(Exception e) {
			Actions act = new Actions(driver);
			act.moveToElement(c.Cases_Section()).build().perform();
			js.executeScript("window.scrollBy(0,175)","");
			c.Cases_Section().click();
		}
		Thread.sleep(5000);
		c.Case_SubmitEdit().click();
		Thread.sleep(10000);
		System.out.println("Case Owner: " + c.Case_Owner().getText());
		Assert.assertEquals("Sales Operations", c.Case_Owner().getText());
		String cs = driver.findElement(By.xpath("(//*[contains(text(),'Status')]//parent::div)/following-sibling::div/span/slot/slot/lightning-formatted-text")).getText();
		System.out.println("Case status is: " + cs);

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
	public void getsuccessmessage() {
		WebElement msg = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']/div/div/div/div/span"));
		waitForWebElementPresent(msg);
		WebElement sm = driver.findElement(By.xpath("//*[@class='forceVisualMessageQueue']"));
		if (sm.getText().contains("success"))
			System.out.println(msg.getText());
	}
}
