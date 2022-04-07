package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createLeadPage {
	
	

@FindBy(xpath="//*[@name='firstName']")
public WebElement firstname;

@FindBy(xpath="//*[@name='lastName']")
public WebElement lastname;

@FindBy(xpath="//*[@name='Email']")
public WebElement email;

@FindBy(xpath="//*[@name='Company']")
public WebElement company;

@FindBy(xpath="//*[@name='Phone']")
public WebElement phone;

@FindBy(xpath="//*[@name='Website']")
public WebElement website;

@FindBy(xpath="//*[@name='street']")
public WebElement street;

@FindBy(xpath="//*[@name='city']")
public WebElement city;

@FindBy(xpath="//*[@name='province']")
public WebElement state;

@FindBy(xpath="//*[@name='postalCode']")
public WebElement postalcode;

@FindBy(xpath="//*[@name='country']")
public WebElement country;

@FindBy(xpath="//*[@title='Save']")
public WebElement save;

@FindBy(xpath="//*[@name='Convert']")
public WebElement convert;

@FindBy(xpath="//*[@type='button']/*[contains(text(),'Convert')]")
public WebElement con;

public createLeadPage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	//this.driver=driver;
	super();
	//setTitle();
	PageFactory.initElements(driver, this);
}

public WebElement getfirstname(){
	return firstname;
}

public WebElement getlastname(){
	return lastname;
}

public WebElement getemail(){
	return email;
}

public WebElement getcompany(){
	return company;
}

public WebElement getphone(){
	return phone;
}

public WebElement getstreet(){
	return street;
}

public WebElement getcity(){
	return city;
}

public WebElement getcountry(){
	return country;
}

public WebElement getpostalcode(){
	return postalcode;
}

public WebElement getstate(){
	return state;
}

public WebElement getsave(){
	return save;
}

public WebElement getwebsite(){
	return website;
}


public WebElement getconvert(){
	return convert;
}

public WebElement getcon(){
	return con;
}

}
