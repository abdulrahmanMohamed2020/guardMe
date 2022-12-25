package org.labs247.pages.onboarding;

import io.qameta.allure.Step;
import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailEle = By.id("email");
    private final By continueButton = By.id("submit");
    private final By errorMessage = By.xpath("//div[contains(@class,'v-messages__message')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter the email address")
    public void enterEmail(String email) {
        typeText(emailEle,email);
    }
    @Step("Click on the continue button")
    public void clickOnContinue() {
        actionClick(continueButton);
    }

    public String getErrorMessage() {
        return findElement(errorMessage).getText();
    }

}
