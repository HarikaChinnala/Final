package Regression;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Pageobjects.loginPageObject;
import junit.framework.Assert;

public class Base {
	

	public WebDriver driver;

	protected String userid;
	protected String userid1;
	protected String contracturl;
	protected String contractid;
	protected String ARcomments;
	protected String NewAccountname;
	protected String websitename;
	protected String existingaccountname;
	protected String firstname1;
	protected String lastname1;
	protected String email1;
	protected String Accountname1;
	protected String country;
	protected String newfirstname;
	protected String existingcontactname;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String company;
	protected long phone;
	protected String website;
	protected String street;
	protected String city;
	protected String state;
	protected long postalcode;
	protected String Accountname;
	protected String currency;
	protected String opportunityname;
	protected String opptype;
	protected String pilotvalue;
	public String sellinglane;
	protected String usecase;
	protected String entity;
	protected String primarycontact;
	protected String foundbychannel;
	protected String quote;
	protected String RNperiod;
	protected String discountoption;
	protected String amendurl;
	protected String ApprovalComments;
	protected String SubmitEditDetails;
	protected String usernameadmin;
	protected String pwd;
	protected String username;
	protected String productname;
	protected String serviceline;
	protected String doctype;
	protected String legalentity;
	protected String countercontact;
	protected String competitor;
	protected String terminationcomments;
	protected Date cancdate;
	protected String subid;
	protected String amendtype;
	protected String pendingcasestatus;
	protected String escalatedcasestatus;
	protected String closedcasestatus;
	protected String freeperiod;
	protected Date startdate;
	protected Date Termstartdate;
	protected int Subscriptionterm;
	protected double discount;
	public String subproducts;
	public String subsubproducts;
	protected String upload;
	protected String paper;
	protected String legaluserid;
	protected String legalid;
	protected String legalpwd;
	protected String functionalgroup;
	protected String listmem;
	protected String documenttypelegal;
	protected Date requesteddate;
	protected String existpo;
	protected String ponumb;
	protected String AEuserid;
	protected String ChangeOppType;
	protected String useridprocurementadmin;
	protected String useridlegaladmin;
	protected double scopeofrecords;
	protected String billingoption;
	protected String billingfrequency;
	protected double CustomersTNOwnership;
	protected String Maxseats;
	protected String interfaceType;
	protected double OCN;
	protected String solutiondescription;
	protected double threshold;
	protected double monthlyminfee;
	protected double monthlyminlistvol;
	protected String nameofsubclient;
	protected String datadeliveryfrequencyUSIYP;
	protected String datadeliveryfrequencyUSEBR;
	protected String datadeliveryfrequencycanadaIYP;
	protected double additionalFileSliceCriteria;
	protected String ActiveStatuscb;
	protected String HandsetStatuscb;
	protected String RoamingIdentifierscb;
	protected String SMSEnabledcb;
	protected String InternationalRevShareFraudIdentifiercb;
	protected String CFCAorGSMAMembercb;
	protected String billingoptionBRMmonthly;
	protected String billingfrequencyBRMmonthly;
	protected String billingoptionBRMyearlyservice;
	protected String billingfrequencyBRMyearlyservice;
	protected String billingoptionBRMyearlylist;
	protected String billingfrequencyBRMyearlylist;
	protected String billingoptionUSIYP;
	protected String billingfrequencyUSIYP;
	protected String billingoptionUSEBR;
	protected String billingfrequencyUSEBR;
	protected String billingoptionUSAPI;
	protected String billingfrequencyUSAPI;
	protected String billingoptionCanadaIYP;
	protected String billingfrequencyCanadaIYP;
	protected String existingclient;
	protected String newLeadName;
	protected double NumbofPlatform;

	protected double NumbofAudience;

	protected String geography;

	protected String fileattributes;

	protected double frequency;

	protected String feedtype;

	protected String impressions;

	protected String platformdescription;

	protected String audiencedescription;

	protected String unlimitedimpressions;

	protected double numbofDMA;

	protected double numbofadvisoryservicehrs;

	protected String listofDMA;

	public Properties prop = new Properties();

	public XSSFSheet sheet;
	public XSSFWorkbook wb ;
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

	public WebDriver initialiseDriver() throws IOException, InterruptedException {

//		FileInputStream fis = new FileInputStream("src/main/java/Regression/data.properties");
//		prop.load(fis);
		File src = new File("data/DataFile.xlsx");
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet("Sheet1");
		
		
		String browser = sheet.getRow(0).getCell(1).getStringCellValue();
		
		String url = sheet.getRow(1).getCell(1).getStringCellValue();
		
		username = sheet.getRow(3).getCell(1).getStringCellValue();
		usernameadmin = sheet.getRow(4).getCell(1).getStringCellValue();		
		pwd = sheet.getRow(5).getCell(1).getStringCellValue();
		AEuserid = sheet.getRow(6).getCell(1).getStringCellValue();
		userid1 = sheet.getRow(7).getCell(1).getStringCellValue();
		
		firstname = sheet.getRow(9).getCell(1).getStringCellValue();
		lastname = sheet.getRow(10).getCell(1).getStringCellValue();
		newLeadName = sheet.getRow(11).getCell(1).getStringCellValue();
		postalcode = (long) sheet.getRow(12).getCell(1).getNumericCellValue();
		email = sheet.getRow(13).getCell(1).getStringCellValue();
		country = sheet.getRow(14).getCell(1).getStringCellValue();
		website = sheet.getRow(15).getCell(1).getStringCellValue();
		company = sheet.getRow(16).getCell(1).getStringCellValue();
		city = sheet.getRow(17).getCell(1).getStringCellValue();
		street = sheet.getRow(18).getCell(1).getStringCellValue();
		state = sheet.getRow(19).getCell(1).getStringCellValue();
		phone = (long) sheet.getRow(20).getCell(1).getNumericCellValue();
		
		Accountname = sheet.getRow(22).getCell(1).getStringCellValue();
		websitename = sheet.getRow(23).getCell(1).getStringCellValue();
		existingaccountname = sheet.getRow(24).getCell(1).getStringCellValue();
		NewAccountname = sheet.getRow(25).getCell(1).getStringCellValue();
		
		firstname1 = sheet.getRow(27).getCell(1).getStringCellValue();
		lastname1 = sheet.getRow(28).getCell(1).getStringCellValue();
		email1 = sheet.getRow(29).getCell(1).getStringCellValue();
		Accountname1 = sheet.getRow(30).getCell(1).getStringCellValue();
		newfirstname = sheet.getRow(31).getCell(1).getStringCellValue();
		existingcontactname = sheet.getRow(32).getCell(1).getStringCellValue();
		
		opportunityname = sheet.getRow(34).getCell(1).getStringCellValue();
		opptype = sheet.getRow(35).getCell(1).getStringCellValue();
		currency = sheet.getRow(36).getCell(1).getStringCellValue();
		sellinglane = sheet.getRow(37).getCell(1).getStringCellValue();
		entity = sheet.getRow(38).getCell(1).getStringCellValue();
		foundbychannel = sheet.getRow(39).getCell(1).getStringCellValue();
		usecase = sheet.getRow(40).getCell(1).getStringCellValue();
		primarycontact = sheet.getRow(41).getCell(1).getStringCellValue();
		competitor = sheet.getRow(42).getCell(1).getStringCellValue();
		ChangeOppType = sheet.getRow(43).getCell(1).getStringCellValue();
		
		quote = sheet.getRow(45).getCell(1).getStringCellValue();
		productname = sheet.getRow(46).getCell(1).getStringCellValue();
		RNperiod = sheet.getRow(47).getCell(1).getStringCellValue();
		Subscriptionterm = (int) sheet.getRow(48).getCell(1).getNumericCellValue();
		startdate = sheet.getRow(49).getCell(1).getDateCellValue();
		Termstartdate = sheet.getRow(50).getCell(1).getDateCellValue();
		freeperiod = sheet.getRow(51).getCell(1).getStringCellValue();
		subproducts = sheet.getRow(52).getCell(1).getStringCellValue();
		subsubproducts = sheet.getRow(53).getCell(1).getStringCellValue();
		discountoption = sheet.getRow(54).getCell(1).getStringCellValue();
		discount = sheet.getRow(55).getCell(1).getNumericCellValue();
		billingoption = sheet.getRow(56).getCell(1).getStringCellValue();
		billingoptionUSIYP = sheet.getRow(57).getCell(1).getStringCellValue();
		datadeliveryfrequencyUSIYP = sheet.getRow(58).getCell(1).getStringCellValue();
		billingfrequencyUSAPI = sheet.getRow(59).getCell(1).getStringCellValue();
		audiencedescription = sheet.getRow(60).getCell(1).getStringCellValue();
		frequency = sheet.getRow(61).getCell(1).getNumericCellValue();
		monthlyminlistvol = sheet.getRow(62).getCell(1).getNumericCellValue();
		unlimitedimpressions = sheet.getRow(63).getCell(1).getStringCellValue();
		billingfrequencyUSEBR = sheet.getRow(64).getCell(1).getStringCellValue();
		numbofadvisoryservicehrs = sheet.getRow(65).getCell(1).getNumericCellValue();
		nameofsubclient = sheet.getRow(66).getCell(1).getStringCellValue();
		RoamingIdentifierscb = sheet.getRow(67).getCell(1).getStringCellValue();
		OCN = sheet.getRow(68).getCell(1).getNumericCellValue();
		billingoptionCanadaIYP = sheet.getRow(69).getCell(1).getStringCellValue();
		NumbofPlatform = sheet.getRow(70).getCell(1).getNumericCellValue();
		CustomersTNOwnership = sheet.getRow(71).getCell(1).getNumericCellValue();
		billingfrequencyUSIYP = sheet.getRow(72).getCell(1).getStringCellValue();
		billingoptionBRMyearlyservice = sheet.getRow(73).getCell(1).getStringCellValue();
		HandsetStatuscb = sheet.getRow(74).getCell(1).getStringCellValue();
		SMSEnabledcb = sheet.getRow(75).getCell(1).getStringCellValue();
		billingoptionBRMmonthly = sheet.getRow(76).getCell(1).getStringCellValue();
		ActiveStatuscb = sheet.getRow(77).getCell(1).getStringCellValue();
		billingoptionBRMyearlylist = sheet.getRow(78).getCell(1).getStringCellValue();
		pilotvalue = sheet.getRow(79).getCell(1).getStringCellValue();
		billingfrequencyBRMyearlyservice = sheet.getRow(80).getCell(1).getStringCellValue();
		additionalFileSliceCriteria = sheet.getRow(81).getCell(1).getNumericCellValue();
		scopeofrecords = sheet.getRow(82).getCell(1).getNumericCellValue();
		geography = sheet.getRow(83).getCell(1).getStringCellValue();
		NumbofAudience = sheet.getRow(84).getCell(1).getNumericCellValue();
		InternationalRevShareFraudIdentifiercb = sheet.getRow(85).getCell(1).getStringCellValue();
		interfaceType = sheet.getRow(86).getCell(1).getStringCellValue();
		CFCAorGSMAMembercb = sheet.getRow(87).getCell(1).getStringCellValue();
		billingfrequencyBRMyearlylist = sheet.getRow(88).getCell(1).getStringCellValue();
		billingoptionUSAPI = sheet.getRow(89).getCell(1).getStringCellValue();
		platformdescription = sheet.getRow(90).getCell(1).getStringCellValue();
		threshold = sheet.getRow(91).getCell(1).getNumericCellValue();
		Maxseats = sheet.getRow(92).getCell(1).getStringCellValue();
		numbofDMA = sheet.getRow(93).getCell(1).getNumericCellValue();
		datadeliveryfrequencycanadaIYP = sheet.getRow(94).getCell(1).getStringCellValue();
		billingfrequencyBRMmonthly = sheet.getRow(95).getCell(1).getStringCellValue();
		impressions = sheet.getRow(96).getCell(1).getStringCellValue();
		billingoptionUSEBR = sheet.getRow(97).getCell(1).getStringCellValue();
		datadeliveryfrequencyUSEBR = sheet.getRow(98).getCell(1).getStringCellValue();
		billingfrequency = sheet.getRow(99).getCell(1).getStringCellValue();
		billingfrequencyCanadaIYP = sheet.getRow(100).getCell(1).getStringCellValue();
		fileattributes = sheet.getRow(101).getCell(1).getStringCellValue();
		solutiondescription = sheet.getRow(102).getCell(1).getStringCellValue();
		listofDMA = sheet.getRow(103).getCell(1).getStringCellValue();
		monthlyminfee = sheet.getRow(104).getCell(1).getNumericCellValue();
		feedtype = sheet.getRow(105).getCell(1).getStringCellValue();
		
		contractid = sheet.getRow(107).getCell(1).getStringCellValue();
		contracturl = sheet.getRow(108).getCell(1).getStringCellValue();
		SubmitEditDetails = sheet.getRow(109).getCell(1).getStringCellValue();
		pendingcasestatus = sheet.getRow(110).getCell(1).getStringCellValue();
		escalatedcasestatus = sheet.getRow(111).getCell(1).getStringCellValue();
		closedcasestatus = sheet.getRow(112).getCell(1).getStringCellValue();
		ApprovalComments = sheet.getRow(113).getCell(1).getStringCellValue();
		userid = sheet.getRow(114).getCell(1).getStringCellValue();
		ARcomments = sheet.getRow(115).getCell(1).getStringCellValue();
		subid = sheet.getRow(116).getCell(1).getStringCellValue();
		cancdate = sheet.getRow(117).getCell(1).getDateCellValue();
		terminationcomments = sheet.getRow(118).getCell(1).getStringCellValue();
		
		amendurl = sheet.getRow(120).getCell(1).getStringCellValue();
		amendtype = sheet.getRow(121).getCell(1).getStringCellValue();
	
//---------------amendmentSubscription = sheet.getRow(123).getCell(1).getStringCellValue();---------------
	
		upload = sheet.getRow(125).getCell(1).getStringCellValue();
		doctype = sheet.getRow(126).getCell(1).getStringCellValue();
		serviceline = sheet.getRow(127).getCell(1).getStringCellValue();
		legalentity = sheet.getRow(128).getCell(1).getStringCellValue();
		countercontact = sheet.getRow(129).getCell(1).getStringCellValue();
		
		legalid = sheet.getRow(131).getCell(1).getStringCellValue();
		legalpwd = sheet.getRow(132).getCell(1).getStringCellValue();
		legaluserid = sheet.getRow(133).getCell(1).getStringCellValue();
		useridlegaladmin = sheet.getRow(134).getCell(1).getStringCellValue();
		useridprocurementadmin = sheet.getRow(135).getCell(1).getStringCellValue();
		functionalgroup = sheet.getRow(136).getCell(1).getStringCellValue();
		listmem = sheet.getRow(137).getCell(1).getStringCellValue();
		documenttypelegal = sheet.getRow(138).getCell(1).getStringCellValue();
		paper = sheet.getRow(139).getCell(1).getStringCellValue();
		requesteddate = sheet.getRow(140).getCell(1).getDateCellValue();
		existpo = sheet.getRow(141).getCell(1).getStringCellValue();
		ponumb = sheet.getRow(142).getCell(1).getStringCellValue();
		
		//wb.close();
		
		
		//String browserName = prop.getProperty("browser");
		System.out.println(browser);
		if (browser.equals("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			opt.addArguments("--disable-web-security");
			System.setProperty("webdriver.chrome.driver", "C://chromedriver//chromedriver.exe");
			driver = new ChromeDriver(opt);

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C://geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("IE")) {
			// exceute in IE
		}

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.get(prop.getProperty("url"));
		driver.get(url);
		loginPageObject l = new loginPageObject(driver);
		//l.getusername().sendKeys(prop.getProperty("username"));
		l.getusername().sendKeys(username);
		//l.getpassword().sendKeys(prop.getProperty("pwd"));
		l.getpassword().sendKeys(pwd);
		l.getLogin().click();
		try {
			l.clickskip().click();
		} catch (Exception e) {
			// driver.navigate().refresh();
		}
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@class='switch-to-lightning']")).click();
		} catch (Exception e) {
		}
		Thread.sleep(2000);
		/*
		userid = prop.getProperty("userid");
		userid1 = prop.getProperty("userid1");
		contracturl = prop.getProperty("contracturl");
		contractid = prop.getProperty("contractid");
		ARcomments = prop.getProperty("ARcomments");
		NewAccountname = prop.getProperty("NewAccountname");
		websitename = prop.getProperty("websitename");
		existingaccountname = prop.getProperty("existingaccountname");
		firstname1 = prop.getProperty("firstname1");
		lastname1 = prop.getProperty("lastname1");
		email1 = prop.getProperty("email1");
		Accountname1 = prop.getProperty("Accountname1");
		country = prop.getProperty("country");
		newfirstname = prop.getProperty("newfirstname");
		existingcontactname = prop.getProperty("existingcontactname");
		firstname = prop.getProperty("firstname");
		lastname = prop.getProperty("lastname");
		email = prop.getProperty("email");
		company = prop.getProperty("company");
		phone = prop.getProperty("phone");
		website = prop.getProperty("website");
		street = prop.getProperty("street");
		city = prop.getProperty("city");
		state = prop.getProperty("state");
		postalcode = prop.getProperty("postalcode");
		Accountname = prop.getProperty("Accountname");
		opportunityname = prop.getProperty("opportunityname");
		currency = prop.getProperty("currency");
		opptype = prop.getProperty("opptype");
		pilotvalue = prop.getProperty("pilotvalue");
		sellinglane = prop.getProperty("sellinglane");
		usecase = prop.getProperty("usecase");
		entity = prop.getProperty("entity");
		primarycontact = prop.getProperty("primarycontact");
		foundbychannel = prop.getProperty("foundbychannel");
		quote = prop.getProperty("quote");
		RNperiod = prop.getProperty("RNperiod");
		discountoption = prop.getProperty("discountoption");
		discount = prop.getProperty("discount");
		amendurl = prop.getProperty("amendurl");
		ApprovalComments = prop.getProperty("ApprovalComments");
		SubmitEditDetails = prop.getProperty("SubmitEditDetails");
		usernameadmin = prop.getProperty("usernameadmin");
		pwd = prop.getProperty("pwd");
		username = prop.getProperty("username");
		productname = prop.getProperty("productname");
		serviceline = prop.getProperty("serviceline");
		doctype = prop.getProperty("doctype");
		legalentity = prop.getProperty("legalentity");
		countercontact = prop.getProperty("countercontact");
		competitor = prop.getProperty("competitor");
		terminationcomments = prop.getProperty("terminationcomments");
		cancdate = prop.getProperty("cancdate");
		subid = prop.getProperty("subid");
		amendtype = prop.getProperty("amendtype");
		pendingcasestatus = prop.getProperty("pendingcasestatus");
		escalatedcasestatus = prop.getProperty("escalatedcasestatus");
		closedcasestatus = prop.getProperty("closedcasestatus");
		freeperiod = prop.getProperty("freeperiod");
		startdate = prop.getProperty("startdate");
		Termstartdate = prop.getProperty("Termstartdate");
		Subscriptionterm = prop.getProperty("Subscriptionterm");
		subproducts = prop.getProperty("subproducts");
		subsubproducts = prop.getProperty("subsubproducts");
		upload = prop.getProperty("upload");
		paper = prop.getProperty("paper");
		legaluserid = prop.getProperty("legaluserid");
		legalid = prop.getProperty("legalid");
		legalpwd = prop.getProperty("legalpwd");
		functionalgroup = prop.getProperty("functionalgroup");
		listmem = prop.getProperty("listmem");
		documenttypelegal = prop.getProperty("documenttypelegal");
		requesteddate = prop.getProperty("requesteddate");
		existpo = prop.getProperty("existpo");
		ponumb = prop.getProperty("ponumb");
		AEuserid = prop.getProperty("AEuserid");
		ChangeOppType = prop.getProperty("ChangeOppType");
		useridlegaladmin = prop.getProperty("useridlegaladmin");
		useridprocurementadmin = prop.getProperty("useridprocurementadmin");
		scopeofrecords = prop.getProperty("scopeofrecords");
		billingoption = prop.getProperty("billingoption");
		billingfrequency = prop.getProperty("billingfrequency");
		// FileOutputStream fout = new
		// FileOutputStream("C://Users//966790//git//newrepo//FOBO//src//main//java//Regression//data.properties");
		CustomersTNOwnership = prop.getProperty("CustomersTNOwnership");
		Maxseats = prop.getProperty("Maxseats");
		interfaceType = prop.getProperty("interfaceType");
		OCN = prop.getProperty("OCN");
		solutiondescription = prop.getProperty("solutiondescription");
		threshold = prop.getProperty("threshold");
		monthlyminfee = prop.getProperty("monthlyminfee");
		monthlyminlistvol = prop.getProperty("monthlyminlistvol");
		nameofsubclient = prop.getProperty("nameofsubclient");
		datadeliveryfrequencyUSIYP = prop.getProperty("datadeliveryfrequencyUSIYP");
		datadeliveryfrequencyUSEBR = prop.getProperty("datadeliveryfrequencyUSEBR");
		datadeliveryfrequencycanadaIYP = prop.getProperty("datadeliveryfrequencycanadaIYP");
		additionalFileSliceCriteria = prop.getProperty("additionalFileSliceCriteria");
		ActiveStatuscb = prop.getProperty("ActiveStatuscb");
		HandsetStatuscb = prop.getProperty("HandsetStatuscb");
		RoamingIdentifierscb = prop.getProperty("RoamingIdentifierscb");
		SMSEnabledcb = prop.getProperty("SMSEnabledcb");
		InternationalRevShareFraudIdentifiercb = prop.getProperty("InternationalRevShareFraudIdentifiercb");
		CFCAorGSMAMembercb = prop.getProperty("CFCAorGSMAMembercb");
		billingoptionBRMmonthly = prop.getProperty("billingoptionBRMmonthly");
		billingfrequencyBRMmonthly = prop.getProperty("billingfrequencyBRMmonthly");
		billingoptionBRMyearlyservice = prop.getProperty("billingoptionBRMyearlyservice");
		billingfrequencyBRMyearlyservice = prop.getProperty("billingfrequencyBRMyearlyservice");
		billingoptionBRMyearlylist = prop.getProperty("billingoptionBRMyearlylist");
		billingfrequencyBRMyearlylist = prop.getProperty("billingfrequencyBRMyearlylist");
		billingoptionUSIYP = prop.getProperty("billingoptionUSIYP");
		billingfrequencyUSIYP = prop.getProperty("billingfrequencyUSIYP");
		billingoptionUSEBR = prop.getProperty("billingoptionUSEBR");
		billingfrequencyUSEBR = prop.getProperty("billingfrequencyUSEBR");
		billingoptionUSAPI = prop.getProperty("billingoptionUSAPI");
		billingfrequencyUSAPI = prop.getProperty("billingfrequencyUSAPI");
		billingoptionCanadaIYP = prop.getProperty("billingoptionCanadaIYP");
		billingfrequencyCanadaIYP = prop.getProperty("billingfrequencyCanadaIYP");
		existingclient = prop.getProperty("existingclient");
		NumbofPlatform = prop.getProperty("NumbofPlatform");
		NumbofAudience = prop.getProperty("NumbofAudience");
		geography = prop.getProperty("geography");
		fileattributes = prop.getProperty("fileattributes");
		frequency = prop.getProperty("frequency");
		feedtype = prop.getProperty("feedtype");
		impressions = prop.getProperty("impressions");
		platformdescription = prop.getProperty("platformdescription");
		audiencedescription = prop.getProperty("audiencedescription");
		unlimitedimpressions = prop.getProperty("unlimitedimpressions");
		numbofDMA = prop.getProperty("numbofDMA");
		numbofadvisoryservicehrs = prop.getProperty("numbofadvisoryservicehrs");
		listofDMA = prop.getProperty("listofDMA");
		newLeadName=prop.getProperty("newLeadName");
		*/

		return driver;
	}

	@SuppressWarnings("deprecation")
	public void waitForWebElementPresent(WebElement element) {
		WebDriverWait Wait = new WebDriverWait(driver, 90);
		Wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getScreenShot(String methodname, WebDriver driver){

        TakesScreenshot ss= (TakesScreenshot) driver;
        File src = ss.getScreenshotAs(OutputType.FILE);
        String dest= System.getProperty("user.dir")+"\\reports\\"+methodname+".png";
        try {
           FileUtils.copyFile(src, new File(dest));
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

        return dest;

      }
	 

	 @BeforeSuite
	 public static ExtentReports getreport() {
	     htmlReporter = new ExtentHtmlReporter("demo.html");
	     extent = new ExtentReports();
	     extent.attachReporter(htmlReporter);
	 
	     htmlReporter.config().setDocumentTitle("Automation Test Report");
	     htmlReporter.config().setTheme(Theme.DARK);
	 
	     extent.setSystemInfo("ProjectName", "FOBO");
	     extent.setSystemInfo("Browser", "Chrome");
	     return extent;
	 
	     }

}
