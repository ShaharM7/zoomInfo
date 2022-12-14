package utilities;


import java.io.File;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * this class represents the main functions of all pages
 * @author Shlomi
 */


public abstract class BaseFunctions {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	// constructor
	public BaseFunctions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	// function to navigate to URL
	public Boolean navigateToURL(String URL) {
		try{
			driver.navigate().to(URL);
			return true;
		} catch (Exception e) {
			System.out.println("Site was not loaded");
			return false;
		}
	}
	
	// function to get the current URL
	public String getURLCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	// function to get back webElement
	public WebElement getWebElement(By elem) {
		return driver.findElement(elem);
	}
	
	// function to click on element
	public Boolean clickOnElement(By elem) {
		try {
			driver.findElement(elem).click();
			return true;
		} catch (Exception e){
			System.out.println("element "+elem+" was not clicked");
			return false;
		}
	}
	
	// function to clear field and then type text
	public Boolean clearAndTypeTextToElem(By elem, String text) {
		try {
			WebElement textField = driver.findElement(elem);
			textField.clear();
			textField.sendKeys(text);
			return true;
		} catch (Exception e) {
			System.out.println("element was not clear and text was not send to");
			return false;
		}
	}
	
	// function to click enter
	public Boolean clickEnterOnElem(By elem) {
		try {
			driver.findElement(elem).sendKeys(Keys.ENTER);
			return true;
		} catch (Exception e){
			System.out.println("Enter was not clicked");
			return false;
		}
	}
	
	// function to send any key
	public Boolean clickOnAnyKeyElem(By elem, Keys key ) {
		try{
			driver.findElement(elem).sendKeys(key);
			return true;
		} catch (Exception e){
			System.out.println(key + " was not clicked");
			return false;
		}
	}
		
	// function to wait for element to be clickable
	public Boolean waitForElementToBeClickable(By elem) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elem));
			return true;
		} catch (Exception e) {
			System.out.println("wait for element to be clickable was not worked correct");
			return false;
		}
	}

	// function to wait for element to be visible
	public Boolean waitForElementToBeVisible(By elem) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			return true;
		} catch (Exception e) {
			System.out.println("wait for element to be visible was not worked correct");
			return false;
		}
	}
	
	// function to wait for element to be presented
	public Boolean waitForElementToBePresented(By elem) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(elem));
			return true;
		} catch (Exception e) {
			System.out.println("wait for element to be present was not worked correct");
			return false;
		}
	}
	
	// function to validate if element is displayed
	public boolean IsElementDisplayed(By elem) {
		try {
			driver.findElement(elem).isDisplayed();
			return true;
		} catch (Exception e) {
			System.out.println("element was not displayed");
			return false;
		}
	}
	
	// function to get the number of elements under the DOM
	public String getNumberOfElementsFromDom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.getElementsByClassName('auto-complete-item').length;").toString(); 
	}

	// function to get text from element
	public String getTextFromElement(By elem) {
		return driver.findElement(elem).getText();
	}

	// function to get color from element
	public String getColorFromElement(By elem) {
		return driver.findElement(elem).getCssValue("color");
	}

	// function to move courser to element
	public boolean mouseHooverFromElement(By elem) {
		action = new Actions(driver);
		try {
			WebElement element1 = driver.findElement(elem);
			action.moveToElement(element1).build().perform();
			return true;
		} catch (Exception e) {
			System.out.println("mouse hover was not worked properly");
			return false;
		}
	}
	
	// function to move courser to element and click on him
	public boolean mouseHooverFromElementToElementAndClick(By elem) {
		action = new Actions(driver);
		try {
			WebElement element1 = driver.findElement(elem);
			action.moveToElement(element1).click().build().perform();
			return true;
		} catch (Exception e) {
			System.out.println("mouse hover and click was not worked properly");
			return false;
		}
	}

	// function to get any attribute from element
	public String getAttributeFromElement(By elem, String attribute) {
		return driver.findElement(elem).getAttribute(attribute);
	}

	// function to get icon from element
	public String getIcon(By elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
		return (String) js.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('content');",icon);
	}

	// function to get number of elements
	public int countElements(By elem, int numberOfElementsToBE) {
		wait.until(ExpectedConditions.numberOfElementsToBe(elem, numberOfElementsToBE));
		List<WebElement> list = driver.findElements(elem);
		return list.size();
	}

	// function to get number text of elements
	public List<Double> getNumberTextFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<Double> doubleList = new ArrayList<>();
		for (WebElement element : elementList) {
			doubleList.add(Double.valueOf(element.getText().split(" ")[1]));
		}
		return doubleList;
	}

	// function to get text of elements
	public List<String> getTextFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<String> stringList = new ArrayList<>();
		for (WebElement element : elementList) {
			stringList.add(element.getText());
		}
		return stringList;
	}


	// function to scroll to element
	public boolean scrollToElement(By elemToScroll) {
		try {
			WebElement element1 = driver.findElement(elemToScroll);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			return true;
		} catch (Exception e) {
			System.out.println("Scroll to element was not worked correct");
			return false;
		}
	}

	// function to take screenshot
	public Boolean takeScreenShot() {
		try{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			//Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot =((TakesScreenshot) getDriver());
			//Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
			// take the tile and path
			String path = System.getProperty("user.dir") + File.separator + "Pictures" + File.separator +timestamp.getTime()+".png";
			//Move image file to new destination
			File DestFile = new File(path);
			//Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
			return true;
		} catch (Exception e) {
			System.out.println("No screenshot was made");
			return false;
		}

	}

	public Boolean getNumFromString(String elem){
		try{
			for(char num: elem.toCharArray()){
				if(num=='.'){
					String btn = "BtnDot";
					clickOnElement(By.id(btn));
					continue;
				}
				String btn = "Btn"+num;
				clickOnElement(By.id(btn));
			}
			return true;
		} catch (Exception e){
			System.out.println("Didn't succeed to click on number");
			return false;
		}
	}



}
