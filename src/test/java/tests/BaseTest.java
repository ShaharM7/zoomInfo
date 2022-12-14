package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;
import pages.CalculatorPage;


public class BaseTest implements ITestListener {

	WebDriver driver;
	CalculatorPage mainAppPage;

	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void setup(String browser) {

		driver = new ChromeDriver();

		// maximize the browser window
		driver.manage().window().maximize();

		mainAppPage = new CalculatorPage(driver);
		
	}

	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();

	}

}
