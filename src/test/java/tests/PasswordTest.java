package tests;

import org.labs247.pages.onboarding.PasswordPage;
import org.testng.annotations.Test;
import uitils.Constants;
import uitils.LoginHelper;

import static org.testng.Assert.assertEquals;

public class PasswordTest extends BaseTest{

    private PasswordPage passwordPage;

    @Test(description = "Verify the user is redirected to the Home page when entering valid password")
    public void verifyTheValidPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.CORRECT_PASSWORD);
        passwordPage.clickOnContinue();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/home");
    }

    @Test(description = "Verify the error message when entering password with a few than 8 characters")
    public void verifyTheInValidPasswordLength() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INVALID_PASSWORD_LENGTH);
        passwordPage.clickOnContinue();

        assertEquals(passwordPage.getErrorMessage(), "The password must be at least 8 characters.");
    }

    @Test(description = "Verify the error message when entering invalid password")
    public void verifyTheInValidPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INVALID_PASSWORD);
        passwordPage.clickOnContinue();

        assertEquals(passwordPage.getErrorMessage(), "The provided password is invalid.");
    }

    @Test(description = "Verify the error message when entering incorrect password")
    public void verifyTheIncorrectPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INCORRECT_PASSWORD);
        passwordPage.clickOnContinue();

        assertEquals(passwordPage.getErrorMessage(), "The provided password is invalid.");
    }

    @Test(description = "Verify the user can show the password")
    public void verifyShowPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.CORRECT_PASSWORD);
        passwordPage.clickOnEyeIcon();

        assertEquals(passwordPage.getPasswordType(), "text");
    }

    @Test(description = "Verify the user can hide the password after showing it")
    public void verifyHidePassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.CORRECT_PASSWORD);
        passwordPage.clickOnEyeIcon();
        passwordPage.clickOnEyeIcon();

        assertEquals(passwordPage.getPasswordType(), "password");
    }

    @Test(description = "Verify the user is redirected to the Forgot Password page when clicking on forgot password button")
    public void verifyForgotPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        new PasswordPage(getDriver()).clickOnForgotPassword();

        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/auth/forgot-password");
    }

    @Test(description = "Verify the user is redirected to the Password page when clicking on I remember my password button")
    public void verifyIRememberMyPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.clickOnForgotPassword();
        passwordPage.clickOnIRememberMyPassword();

        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/auth/login-pwd");
    }
}