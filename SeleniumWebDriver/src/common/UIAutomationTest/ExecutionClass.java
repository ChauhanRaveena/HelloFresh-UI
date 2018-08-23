package common.UIAutomationTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

public class ExecutionClass  {

	public static WebDriver driver;
	WebTestLogic test;

	//Fetch browser from testng.xml 
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		test = new WebTestLogic(driver);
		test.setUp(browser);
		}		
	
	@Test
	@Parameters("browser")
	public void signUp(String browser) throws Exception
	{
		test.signUp(browser);
	}
	
	@Test
	@Parameters("browser")
	public void logInTest(String browser)
	{
		test.logInTest(browser);
	}
	
	
	@Test
	@Parameters("browser")
	public void checkoutTest(String browser) 
	{
		test.checkoutTest(browser);
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
	{
		test.takeScreenShotOnFailure(testResult);
	}
	
	@AfterMethod(dependsOnMethods = "takeScreenShotOnFailure")
	public void close() {
		test.close();
	}
}
	