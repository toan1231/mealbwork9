package ui_automation.utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class Helper {


    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getInstance().getDriver());
        actions.moveToElement(element).perform();
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getInstance().getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }



    public static void verifyElementDisplayed(By by) {
        try {
            assertTrue("Element not visible: "+by, Driver.getInstance().getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + by);

        }
    }

    public static void verifyElementDisplayed(WebElement element) {
        try {
            assertTrue("Element not visible: "+element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + element);

        }
    }



    public WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getInstance().getDriver()).executeScript("arguments[0].click();", element);
    }


    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void doubleClick(WebElement element) {
        new Actions(Driver.getInstance().getDriver()).doubleClick(element).build().perform();
    }


    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) Driver.getInstance().getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }



    public static boolean isClickable(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), 10);
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//
//        }catch(Exception e){
//            return false;
//        }
        Driver.getInstance().getDriver().manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        return true;
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getInstance().getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        element.click();
    }

    public void jSClick(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor)Driver.getInstance().getDriver();
        jse.executeScript("arguments[0].click();", element);
    }

    public static void rightClick(WebElement element) {
        Actions actions = new Actions(Driver.getInstance().getDriver());
        actions.contextClick(element).perform();
    }

}
