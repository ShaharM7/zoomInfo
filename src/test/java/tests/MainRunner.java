package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * this class represents the test runner
 * @author Shlomi
 */

public class MainRunner extends BaseTest {

	private String siteURL = "https://web2.0calc.com/";
	private String firstNum = "10";
	private String secondNum = "2";

	@Test(priority = 1, description = "addition test")
	public void additionTest() {
		mainAppPage.getWebSite(siteURL);
		mainAppPage.addition(firstNum, secondNum);
	}

	@Test(priority = 2, description = "subtraction test")
	public void subtractionTest() {
		mainAppPage.getWebSite(siteURL);
		mainAppPage.subtraction(firstNum, secondNum);
	}

	@Test(priority = 3, description = "multiplication test")
	public void multiplicationTest() {
		mainAppPage.getWebSite(siteURL);
		mainAppPage.multiplication(secondNum, mainAppPage.subtraction(firstNum, secondNum));
	}

	@Test(priority = 4, description = "sinus test")
	public void sinusTest() {
		mainAppPage.getWebSite(siteURL);
		mainAppPage.sinus("30");
	}

	@Test(priority = 5, description = "history test")
	public void historyTest() {
		mainAppPage.getWebSite(siteURL);
		mainAppPage.historyTest();
	}

}
