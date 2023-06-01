package org.labs247.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait driverWait;
    private static final Duration TIMEOUT = Duration.ofSeconds(20);
    private static final Duration POLLING_TIMEOUT = Duration.ofMillis(200);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, TIMEOUT);
    }

    public BasePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, timeout);
    }

    public WebElement findElement(By locator) {
        Wait<WebDriver> wait = getFluentWait();
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        Wait<WebDriver> wait = getFluentWait();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean elementVisible(By locator) {
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

    public boolean elementClickable(By locator) {
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

        WebElement dropDownElement = findElement(dropDown);
        driverWait.until(ExpectedConditions.visibilityOf(dropDownElement));

        Select select = new Select(findElement(dropDown));
        select.selectByVisibleText(item);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    private Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }
}
