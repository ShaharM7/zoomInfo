package pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * this class represents the main calculator page
 * @author Shlomi
 */

public class CalculatorPage extends BaseFunctions {

    // constructor
    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    By addition = By.id("BtnPlus");
    By subtraction = By.id("BtnMinus");
    By multiplication = By.id("BtnMult");
    By sinusBTN = By.id("BtnSin");
    By calculateBTN = By.id("BtnCalc");
    By clearBTN = By.id("BtnClear");
    By resultField = By.id("input");
    By historyBTN = By.cssSelector(".history");
    By historyElements = By.xpath("//p[@title and @class='r']");
    List<Double> actualResultList = new ArrayList<>();
    Double actualResult;

    // navigate to the site
    public Boolean getWebSite(String site) {
        return navigateToURL(site);
    }

    // addition test
    public Boolean addition(String numA, String numB) {
        try {
            clickOnElement(clearBTN);
            getNumFromString(numA);
            clickOnElement(addition);
            getNumFromString(numB);
//        for(char num: numA.toCharArray()){
//            String btn = "Btn"+num;
//            arg = By.id(btn);
//            clickOnElement(arg);
//        }
//        String firstNum = "Btn" +numA;
//        String secondNum = "Btn" +numB;
//        firstArg = By.id(firstNum);
//        secondArg = By.id(secondNum);
//        clickOnElement(firstArg);

//        for(char num: numB.toCharArray()){
//            String btn = "Btn"+num;
//            arg = By.id(btn);
//            clickOnElement(arg);
//        }

//        clickOnElement(secondArg);
            clickOnElement(calculateBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            actualResult = Double.parseDouble(getAttributeFromElement(resultField, "value"));
            actualResultList.add(actualResult);
            System.out.println("Addition: " + actualResult);
            return true;
        } catch (Exception e) {
            System.out.println("didn't succeed with the addition");
            return false;
        }
    }

    // subtraction test
    public String subtraction(String numA, String numB) {
        try {
            clickOnElement(clearBTN);
            getNumFromString(numA);
            clickOnElement(subtraction);
            getNumFromString(numB);
            clickOnElement(calculateBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            actualResult = Double.parseDouble(getAttributeFromElement(resultField, "value"));
            actualResultList.add(actualResult);
            System.out.println("subtraction: "+actualResult);
            return String.valueOf(actualResult);
        } catch (Exception e) {
            System.out.println("didn't succeed with the subtraction");
            return null;
        }
    }

    // multiplication test
    public Boolean multiplication(String multNum, String otherNum) {
        try {
            clickOnElement(clearBTN);
            getNumFromString(otherNum);
            clickOnElement(multiplication);
            getNumFromString(multNum);
            clickOnElement(calculateBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            actualResult = Double.parseDouble(getAttributeFromElement(resultField, "value"));
            actualResultList.add(actualResult);
            System.out.println("multiplication: "+actualResult);
            return true;
        } catch (Exception e) {
            System.out.println("didn't succeed with the multiplication");
            return null;
        }
    }

    // sinus test
    public Boolean sinus(String num) {
        try {
            clickOnElement(clearBTN);
            clickOnElement(sinusBTN);
            getNumFromString(num);
            clickOnElement(calculateBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            actualResult = Double.parseDouble(getAttributeFromElement(resultField, "value"));
            actualResultList.add(actualResult);
            System.out.println("sinus: "+actualResult);
            return true;
        } catch (Exception e) {
            System.out.println("didn't succeed with the sinus");
            return null;
        }
    }

    // history test
    public Boolean historyTest() {
        try {
            clickOnElement(historyBTN);
            List<Double> expectedResultList = getNumberTextFromElements(historyElements);
            Collections.sort(expectedResultList);
            Collections.sort(actualResultList);
            return actualResultList.equals(expectedResultList);
        } catch (Exception e) {
            System.out.println("didn't succeed with the list compare");
            return false;
        }
    }



}
