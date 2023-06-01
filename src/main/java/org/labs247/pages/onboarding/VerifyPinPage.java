package org.labs247.pages.onboarding;

import io.qameta.allure.Step;
import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyPinPage extends BasePage {

    private final By otpInput0 = By.id("otp-input--0");
    private final By otpInput1 = By.id("otp-input--1");
    private final By otpInput2 = By.id("otp-input--2");
    private final By otpInput3 = By.id("otp-input--3");
    private final By otpInput4 = By.id("otp-input--4");
    private final By otpInput5 = By.id("otp-input--5");
    private final By verifyPinButton = By.id("submit");
    private final By resendPinButton = By.xpath("//span[contains(text(),'Resend Pin')]//parent::button[@type='button']");
    private final By goBackButton = By.xpath("//span[contains(text(),'Go back')]//parent::button[@type='button']");
    private final By pageTitle = By.xpath("//div[contains(text(),'Verify your pin code')]");
    private final By pageSubTitle = By.xpath("//span[contains(text(),'@')]");
    private final By errorMessage = By.xpath("//div[contains(@class,'v-messages__message')]");

    public VerifyPinPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter the six digits pin code")
    public void enterSixPinCode(String ... pinCode) {
        typeText(otpInput0,pinCode[0]);
        typeText(otpInput1,pinCode[1]);
        typeText(otpInput2,pinCode[2]);
        typeText(otpInput3,pinCode[3]);
        typeText(otpInput4,pinCode[4]);
        typeText(otpInput5,pinCode[5]);
    }

    @Step("Click on the Verify Pin button")
    public void clickOnVerifyPinButton() {
        actionClick(verifyPinButton);
    }

    @Step("Click on the Go Back button")
    public void clickOnGoBackButton() {
        actionClick(goBackButton);
    }

    public String getPageTitleMessage() {
        return findElement(pageTitle).getText();
    }

    public String getPageSubTitleMessage() {
        return findElement(pageSubTitle).getText();
    }

    public String getErrorMessage() {
        return findElement(errorMessage).getText();
    }

    public boolean isResendPinButtonClickable() {
        return elementClickable(resendPinButton);
    }
}
