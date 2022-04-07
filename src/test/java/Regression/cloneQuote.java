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
import Pageobjects.createOppPage;
import Pageobjects.createQuotePage;
import Pageobjects.landingPage;
import Pageobjects.loginPageObject;
import Pageobjects.oppPage;
import Regression.Base;

public class cloneQuote extends Base {
	@Test
	public void cloneQuote () throws IOException, InterruptedException
	{
		driver= initialiseDriver();
		landingPage lp= new landingPage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		/*createQuoteandConfigure cqc= new createQuoteandConfigure();
		String existquote= cqc.existquote;
		System.out.println(existquote);*/
		js.executeScript("arguments[0].click();", lp.getQuote());
		Thread.sleep(5000);
		lp.getquotetxtbox().sendKeys(quote);
		lp.getquotetxtbox().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//lp.getsrchresult().click();
		//String existquote = cqc.configureQuote().existquote;
		driver.findElement(By.xpath("//*[@title='"
								+ quote
								+ "']")).click();
		Thread.sleep(10000);
		
		
		createQuotePage qp= new createQuotePage(driver);
		Thread.sleep(20000);
        qp.getdropdown().click();
        Thread.sleep(5000);
        //qp.getsubmitapproval().click();
        String url=driver.getCurrentUrl();
        if(url.contains("ltnstage")){
        js.executeScript("arguments[0].click();", qp.getclonequote());
        Thread.sleep(10000);}
        else
        	js.executeScript("arguments[0].click();", qp.getclonequoteuat());	
        
        int i=driver.findElements(By.xpath("//iframe")).size();
        for(int a=0; a<i; a++){
        	driver.switchTo().frame(a);
        	//WebElement checkbox =driver.findElement(By.xpath("//*[@name='UltraDNS']/sb-swipe-container/div/div/sb-group/div/div/sb-table-cell-select/div/paper-checkbox"));
        	//if(driver.findElement(By.xpath("//*[@name='UltraDNS']/sb-swipe-container/div/div/sb-group/div/div/sb-table-cell-select/div/paper-checkbox")).isEnabled()==true){
        	try{	
        		js.executeScript("arguments[0].click();", qp.getclone());
        	}
        	catch (Exception e){
        		
        		driver.switchTo().defaultContent();
        	}
        	
        }
        driver.switchTo().defaultContent();
        
        
        Thread.sleep(20000);
        
        
       /* try{
        	qp.getdropdown().click();
        }
        catch (Exception ex){*/
        driver.navigate().refresh();
        Thread.sleep(20000);
        qp.getdropdown().click();
        //js.executeScript("arguments[0].click();", qp.getdropdown2());
        Thread.sleep(2000);
        //String url1=driver.getCurrentUrl();
        if(url.contains("ltnstage")){
        js.executeScript("arguments[0].click();", qp.getEdit());
        
        Thread.sleep(10000);}
        else
        	js.executeScript("arguments[0].click();", qp.getEdituat());
        js.executeScript("arguments[0].click();", qp.getprimcheck());
        
        js.executeScript("arguments[0].click();", qp.getSaveEdit());
        Thread.sleep(20000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        qp.getdropdown().click();
        js.executeScript("arguments[0].click();", qp.getsubmitapproval());
        Thread.sleep(10000);
        
        String app = qp.getapproved().getAttribute("aria-selected");
        System.out.println(app);
        if (app.equals("true")){
        	System.out.println("Quote is approved");
        }
        else 
        	System.out.println("Quote is not approved");
        driver.close();
      
        
     
	}
}