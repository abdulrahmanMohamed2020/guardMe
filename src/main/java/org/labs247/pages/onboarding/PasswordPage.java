package org.labs247.pages.onboarding;

import io.qameta.allure.Step;
import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage extends BasePage {

    private final By passwordEle = By.id("password");
    private final By continueButton = By.id("submit");
    private final By forgotPasswordButton = By.xpath("//span[contains(text(),'Forgot Password ?')]");
    private final By iRememberMyPasswordButton = By.xpath("//span[contains(text(),'I remember my password')]");
    private final By pageTitle = By.xpath("//div[contains(text(),'Enter Password')]");
    private final By errorMessage = By.xpath("//div[contains(@class,'v-messages__message')]");
    private final By showHideButton = By.xpath("//button[contains(@aria-label,'append icon')]");

    public PasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter the password")
    public void enterPassword(String password) {
        typeText(passwordEle,password);
    }

    @Step("Click on the continue button")
    public void clickOnContinue() {
        actionClick(continueButton);
    }

    @Step("Click on the forgot password button")
    public void clickOnForgotPassword() {
        actionClick(forgotPasswordButton);
    }

    @Step("Click on the forgot password button")
    public void clickOnIRememberMyPassword() {
        actionClick(iRememberMyPasswordButton);
    }

    @Step("Click on the show/hide icon")
    public void clickOnEyeIcon() {
        actionClick(showHideButton);
    }

    public String getPageTitleMessage() {
        return findElement(pageTitle).getText();
    }

    public String getErrorMessage() {
        return findElement(errorMessage).getText();
    }

    public String getPasswordType() {
        return findElement(passwordEle).getAttribute("type");
    }
}
