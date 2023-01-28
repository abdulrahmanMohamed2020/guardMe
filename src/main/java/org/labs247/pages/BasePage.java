package org.labs247.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait driverWait;
    private static final Duration TIMEOUT = Duration.ofSeconds(30);
    private static final Duration POLLING_TIMEOUT = Duration.ofMillis(200);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, TIMEOUT);
    }

    protected WebElement findElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElements(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected boolean elementVisible(By locator) {
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    protected boolean elementClickable(By locator) {
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public void actionClick(By locator) {
        WebElement element = findElement(locator);
        scrollToElement(element);

        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        driverWait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            findElement(locator).click();
        }
    }

    public void typeText(By locator, String text) {
        WebElement element = findElement(locator);
        scrollToElement(element);

        driverWait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.clear();
            element.sendKeys(text);
        } catch (StaleElementReferenceException ex) {
            findElement(locator).clear();
            findElement(locator).sendKeys(text);
        }
    }

    public void selectItem(String item, By dropDown) {
        Select select = new Select(findElement(dropDown));
        select.selectByVisibleText(item);
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
