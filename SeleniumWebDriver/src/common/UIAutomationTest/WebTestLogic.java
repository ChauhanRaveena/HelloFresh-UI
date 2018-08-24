package common.UIAutomationTest;

import org.testng.ITestResult;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.ConsoleAppender;
import common.Config.BaseConfiguration;
import common.UIAutomationTest.GenerateRandom;
import com.webelements.WebElements;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTestLogic {
	
	//Instantiate classes
	WebDriver driver;
	WebDriverWait wait;
	ConsoleAppender appender;
	WebElements elements;
	WebElement heading;
	BaseConfiguration baseConfiguration;
	WebTestLogic prac;
	GenerateRandom random;
	static Logger logger = Logger.getLogger(WebTestLogic.class.getName());
	
	//Declare variables
    String email;
	String password;
	String webdriverGeckoDriver;
	String webdriverChromeDriver;
	String webdriverIEDriver;
	String URL;
	String firstname;
	String lastname;
	Select select;
	String fullname;
	String browserCurrent;
	
	public WebTestLogic(WebDriver driver) {
		this.driver = driver;
		
	}


	public void setUp(String browser) {
		try {
				//Configure log4j.xml file to log the INFO details in file at location "eclipse-workspace\SeleniumWebDriver"
			    DOMConfigurator.configure("log4j.xml");
			    
			    //Pass the browser details -firefox/chrome/ie from testng.xml as a parameter
				if(browser.equalsIgnoreCase("firefox")) 
				{
					browserCurrent = browser;
					baseConfiguration = new BaseConfiguration();
					webdriverGeckoDriver = baseConfiguration.getPropertyConfig("webdriver.gecko.driver");
					System.setProperty("webdriver.gecko.driver", webdriverGeckoDriver);
					driver = new FirefoxDriver();
				}
				else if(browser.equalsIgnoreCase("chrome"))
				{
					browserCurrent = browser;
					baseConfiguration = new BaseConfiguration();
					webdriverChromeDriver = baseConfiguration.getPropertyConfig("webdriver.chrome.driver");
					System.setProperty("webdriver.chrome.driver", webdriverChromeDriver);
					driver = new ChromeDriver();
				}
				
				//Instantiate the WebElements class under com.webelements. WebElements class holds all the locators for web elements used in this test
				elements=new WebElements();	
				//Get URL and navigate to the page
				URL = baseConfiguration.getPropertyConfig("webdriver.base.url");
				driver.get(URL);
				
				//Maximize Window
				driver.manage().window().maximize();
				
				//Wait Until the page is completely loaded and "Image YourLogo is found"
				wait = new WebDriverWait(driver, 100);			
			
		}
		catch (Exception e) {
			
		}		
	}	
	
	
	public void signUp(String browser) throws Exception
	{
		try {
			
			//Click on Sign in, provided locater being fetched from WebElements class
			wait.until(ExpectedConditions.visibilityOfElementLocated(elements.signIn)).click();
			
			//Create a new Account by adding new email address
			random = new GenerateRandom();
			email = random.getEmail()+"@gmail.com";
			
			WebElement emailBox = driver.findElement(elements.email);
			emailBox.sendKeys(email);
			emailBox.sendKeys(Keys.ENTER);
			
			//Enter the details to Register new user
			
			//Enter the gender salutation
			wait.until(ExpectedConditions.visibilityOfElementLocated(elements.radioButton)).click();
			
			//Fetch data for firstname from properties file and enter the firstname
			firstname = baseConfiguration.getPropertyData("firstname");
			driver.findElement(elements.firstname).sendKeys(firstname);
	
			
			//Fetch data for lastname from properties file and enter the lastname
			lastname = baseConfiguration.getPropertyData("lastname");
			driver.findElement(elements.lastname).sendKeys(lastname);
			
			
			//Calling class GenerateRandom to generate password randomly 
			password = random.getPassword();
			driver.findElement(elements.password).sendKeys(password);
			
			//Fetch data from data.properties file and enter DOB
			select = new Select(driver.findElement(elements.date));
	        select.selectByValue(baseConfiguration.getPropertyData("date"));
	        select = new Select(driver.findElement(elements.month));
	        select.selectByValue(baseConfiguration.getPropertyData("months"));
	        select = new Select(driver.findElement(elements.years));
	        select.selectByValue(baseConfiguration.getPropertyData("year"));
	        
	        //Fetch data from data.properties file and Company Details
	        driver.findElement(elements.company).sendKeys(baseConfiguration.getPropertyData("company"));
	        
			//Fetch data from data.properties file and add Address line 1 and 2
			driver.findElement(elements.address1).sendKeys(baseConfiguration.getPropertyData("address1"));
			driver.findElement(elements.address2).sendKeys(baseConfiguration.getPropertyData("address2"));
			
			//Fetch data from data.properties file and add City
			driver.findElement(elements.city).sendKeys(baseConfiguration.getPropertyData("city"));
			
			//Fetch data from data.properties file and add State
			select = new Select(driver.findElement(elements.state));
			select.selectByVisibleText(baseConfiguration.getPropertyData("state"));
			
			//Fetch data from data.properties file and add Zip
			 driver.findElement(elements.zip).sendKeys(baseConfiguration.getPropertyData("zip"));
			 
			 //Fetch data from data.properties file and add additional Information
			 driver.findElement(elements.additionalInfo).sendKeys(baseConfiguration.getPropertyData("additional"));
			 
			//Fetch data from data.properties file and add Home Phone
			 driver.findElement(elements.homePhone).sendKeys(random.getMobile());
			 
			//Fetch data from data.properties file and add Mobile
			driver.findElement(elements.mobilePhone).sendKeys(random.getMobile());
			
			//Fetch data from data.properties file and add Address Alias
			driver.findElement(elements.alias).sendKeys(firstname);
			
			//Fetch data from data.properties file and add Click on Register
			driver.findElement(elements.submit).click();
			
			//Call the assertLogin method to verify if user has been logged in successfully
			String mainWindow=driver.getWindowHandle();		
			Set<String> handles = driver.getWindowHandles();
			assertLogin(handles, mainWindow);
			logger.info("User Signed Up Successfully using browser = "+browser+System.lineSeparator());
			System.out.println("User Signed Up Successfully using browser = "+browser+System.lineSeparator());
		}
		catch (Exception e) {
			logger.info("Exception:"+e+System.lineSeparator());
			logger.info("Driver could not be initialized"+System.lineSeparator());
		}		
	}	

	public void assertLogin(Set<String> handles, String mainWin)
	{
		Iterator<String> itr= handles.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();		    
			if(!mainWin.equals(childWindow)){
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		driver.switchTo().window(mainWin);		
		
		//Wait until the text "My account" is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(elements.loginHeading));
		//Assertion to check proper username is shown in the header
		fullname = baseConfiguration.getPropertyData("firstname")+" "+baseConfiguration.getPropertyData("lastname");
		assertEquals(fullname, (driver.findElement(elements.loginAccount).getText()), "Account could not be verified for logged in user");
        //Verify the text element is available after login
        assertTrue(driver.findElement(elements.loginMessage).getText().contains(baseConfiguration.getPropertyData("expectedLoginText")));
        //Verify that the log out button is present
        assertTrue(driver.findElement(elements.logout).isDisplayed());
	}
	
	  
	    public void logInTest(String browser) {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.signIn)).click();
	        driver.findElement(elements.exisitingLoginEmail).sendKeys(baseConfiguration.getPropertyData("existingUserEmail"));
	        driver.findElement(elements.existingLoginPassword).sendKeys(baseConfiguration.getPropertyData("existingUserPassword"));
	        driver.findElement(elements.submitLogin).click();

	      //Call the assertLogin method to verify if user has been logged in successfully
			String mainWindow=driver.getWindowHandle();		
			Set<String> handles = driver.getWindowHandles();
			assertLogin(handles, mainWindow);
			logger.info("Existing User logged in Successfully using browser = "+browser+System.lineSeparator());
			System.out.println("Existing User logged in Successfully using browser = "+browser+System.lineSeparator());
			
	    }

	   
	    public void checkoutTest(String browser)  {	  
	    	logInTest(browser);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.women)).click();
	       
	        // The element "elements.tshirt" needs to be handled differently on Chrome and Firefox
	        //Below is the conditional execution depending on the browser type
	        
	        if(browser.toString().equalsIgnoreCase("chrome"))
	        {
	        	WebElement clickTShirtElement = driver.findElement(elements.tShirt);
	        	clickTShirtElement.click();
	        	clickTShirtElement.click();
	        }
	        else if (browser.toString().equalsIgnoreCase("firefox"))
	        {
	        	driver.findElement(elements.tShirt).click();
	        }
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.AddToCart)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.proceedToCheckout)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.shoppingCartSummaryCheckout)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.addressCheckout)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.agreeTermsCheckbox)).click();
	        driver.findElement(elements.shippingCheckout).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.paymentTypeBankWire)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(elements.orderConfirmationButton)).click();
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(elements.orderPageHeader));
	        assertEquals(baseConfiguration.getPropertyData("orderConfirmationHeading"), heading.getText());
	        assertTrue(driver.findElement(elements.paymentVerification1).isDisplayed());
	        assertTrue(driver.findElement(elements.paymentVerification2).isDisplayed());
	        assertTrue(driver.findElement(elements.orderConfirmationMessage).getText().contains(baseConfiguration.getPropertyData("orderConfirmationMessage")));
	        System.out.println("User checkedOut Successfully using browser = "+browser+System.lineSeparator());
	        
	    }
	
	    
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		String timeStamp;
    	File screenShotName;
		if (testResult.getStatus() == ITestResult.FAILURE) {
			logger.info(testResult.getStatus());
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
	    	screenShotName = new File("screenshots\\"+timeStamp+".png");
			FileUtils.copyFile(scrFile, screenShotName);	
			logger.info("Failure occured.Refer screenshot(s) for more details"+System.lineSeparator());
	   }        
	}
	
	
	public void close() {
		driver.close();     
	}
}
