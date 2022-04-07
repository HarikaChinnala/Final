package Regression;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

public class contractSync extends Base {

	@Test
	public void sync() throws IOException, InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		oppPage op = new oppPage(driver);
		createOpportunity copp = new createOpportunity();
		createQuoteandConfigure cqc = new createQuoteandConfigure();
		
		  copp.createOpportunity();
		  
		  
		  
		  cqc.createQuote(); 
		  cqc.configureQuote(); 
		  cqc.generatequoteproposal();
		  cqc.approveQuote();
		  
		 createContract cc = new createContract(); 
		 cc.createContract();
		
		driver = initialiseDriver();
		
		String oldquote =quote;
		System.out.println("Quote1 is : " + oldquote);
		driver.get(contracturl);
		Thread.sleep(10000);
		String filename1 = gendoc();
		System.out.println(filename1);
		driver.close();  
		
		cqc.createQuote();
		cqc.configureQuote();
		cqc.generatequoteproposal();
		cqc.approveQuote();
		//write code to load opp page
		driver = initialiseDriver();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		oppPage op1 = new oppPage(driver);
		driver.get(contracturl);
		Thread.sleep(10000);
		js1.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//*[@class='slds-form-element__control']/div/div/a")));

		Thread.sleep(10000);
		// write code to add validation for contracted checkbox
		WebElement checkbox=driver.findElement(By.xpath("(//*[contains(text(),'Contracted')]//parent::div)/following-sibling::div/span"));
		if(checkbox.isSelected()){
			System.out.println("Checkbox is checked");
		Assert.assertTrue(false);}
		else
			System.out.println("Checkbox is not checked");
		// write code to check primary quote

		String newquote = driver.findElement(By.xpath("//*[@title='Edit Primary Quote']/preceding-sibling::span//div/a")).getText();
		System.out.println("Quote2 is : " + newquote);
//		System.out.println(oldquote);
		if (oldquote.equals(newquote)) {
			Assert.assertTrue(false);
		} else {
			op1.getdropdown().click();
			Thread.sleep(3000);
			op1.getcontractSync().click();
			Thread.sleep(5000);
			WebElement syncb = driver.findElement(By.xpath("//button[contains(text(),'Sync contract')]"));
			waitForWebElementPresent(syncb);

			syncb.click();
			Thread.sleep(20000);
			// write code to get successmessage
			WebElement sucm = driver.findElement(By.xpath("//*[@class='slds-m-around_small cContract_Sync']/div/lightning-formatted-text"));
			waitForWebElementPresent(sucm);
			
			if(sucm.getText().contains("Contract synced successfully."))
				System.out.println(sucm.getText());
			else{
				System.out.println(sucm.getText());
				Assert.assertTrue(false);
			}
			js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button/span[contains(text(),'Close this window')]")));

			driver.navigate().refresh();
			Thread.sleep(10000);

			try {
				js1.executeScript("arguments[0].click();", op1.getcontractlink());
			} catch (Exception e) {
				js1.executeScript("arguments[0].click();", op1.getcontractlinkuat());
			}
	
			Thread.sleep(10000);
			String id = driver
					.findElement(By
							.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span"))
					.getText();
			System.out.println("New Contractid is:" + id);

			if (contractid.equals(id)) {
				System.out.println("Contract sync is not successful");
				Assert.assertTrue(false);
			}

			else {
				System.out.println("Contract sync is successful");
				driver.navigate().refresh();
				Thread.sleep(15000);
				String stat= driver.findElement(By.xpath("//*[@title='Status']/following-sibling::div")).getText();
		        System.out.println("Contract Status is:"+stat);
		        // to be sent to data.prop file
		        String  curl = driver.getCurrentUrl();
		       String  id1= driver.findElement(By.xpath("(//*[contains(text(),'Contract Number')]//parent::div)/following-sibling::div/span/span")).getText();
		        System.out.println("Contractid is:"+id);
		        System.out.println("Contracturl is:"+curl);
		        
		        FileOutputStream fout = new FileOutputStream("data/DataFile.xlsx");
				sheet.getRow(107).createCell(1).setCellValue(id);
				sheet.getRow(108).createCell(1).setCellValue(curl);
				wb.write(fout);
		        
//		        prop.setProperty("contractid", id1);
//		        prop.setProperty("contracturl", curl);
//				try (final OutputStream outputstream = new FileOutputStream("src/main/java/Regression/data.properties");) {
//					prop.store(outputstream, "File Updated");
//					outputstream.close();
//				}
				String filename2 = gendoc();
				System.out.println(filename2);
				 driver.navigate().refresh();
				 Thread.sleep(15000);
				js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@class='slds-form-element__control']/div/div/a")));
				Thread.sleep(10000);

				op1.getRelated().click();
				js1.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(10000);
				
					String file2 = op1.getFiles().getAttribute("title");
					System.out.println(file2);
					System.out.println("File is attached");

					if (file2.contains(contractid))
						System.out.println("File of Old contract is archieved");
					else
						System.out.println("File of Old contract is not archieved");
				}

			}
		driver.close();

		}
	

	public String gendoc() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		contractPage cp = new contractPage(driver);
		reusablemethods rm = new reusablemethods(driver);
		cp.getRelated().click();
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//*[@title='Files']"))).build().perform();
		Thread.sleep(3000);
		WebElement upload = driver
				.findElement(By.xpath("//*[@data-key='upload'][@class='slds-button__icon slds-button__icon_left']"));
		if (upload.isDisplayed()) {
			System.out.println("File is not attached");
			cp.getdropdown().click();
			js.executeScript("arguments[0].click();", cp.getGenerateDoc());
			Thread.sleep(80000);
			rm.generatedoc(driver);
			Thread.sleep(20000);
		} else {
			System.out.println("File is attached");

		}
		driver.navigate().refresh();
		Thread.sleep(20000);
		cp.getRelated().click();
		Thread.sleep(5000);
		a.moveToElement(driver.findElement(By.xpath("//*[@title='Files']"))).build().perform();
		Thread.sleep(3000);
		String file = cp.getFiles().getAttribute("title");
		//System.out.println(file);
		return file;
	}

}
