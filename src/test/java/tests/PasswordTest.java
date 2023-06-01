package tests;

import org.labs247.pages.home.HomeScreenPage;
import org.labs247.pages.onboarding.PasswordPage;
import org.testng.annotations.Test;
import org.labs247.uitils.Constants;
import org.labs247.uitils.LoginHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PasswordTest extends BaseTestSetup {

    private PasswordPage passwordPage;

    @Test(description = "Verify the user is redirected to the Home page when entering valid password")
    public void verifyTheValidPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.CORRECT_PASSWORD);
        passwordPage.clickOnContinue();

        assertTrue(new HomeScreenPage(getDriver()).isSalutationMessageVisible(),"The Salutation Message is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/home");
    }

    @Test(description = "Verify the error message when entering password with a few than 8 characters")
    public void verifyTheInValidPasswordLength() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INVALID_PASSWORD_LENGTH);
        passwordPage.clickOnContinue();

        assertEquals(passwordPage.getErrorMessage(), "Password must contain at least 8 characters.");
    }

    @Test(description = "Verify the error message when entering invalid password")
    public void verifyTheInValidPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INVALID_PASSWORD);
        passwordPage.clickOnContinue();

        assertEquals(passwordPage.getErrorMessage(), "Password must contain at least 1 special charcter.");
    }

    @Test(description = "Verify the error message when entering incorrect password")
    public void verifyTheIncorrectPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.enterPassword(Constants.INCORRECT_PASSWORD);
        passwordPage.clickOnContinue();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(passwordPage.getErrorMessage(), "The provided password is invalid.","The error message is incorrect or not displayed");
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

        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/auth/forgot-password");
    }

    @Test(description = "Verify the user is redirected to the Password page when clicking on I remember my password button")
    public void verifyIRememberMyPassword() {
        LoginHelper.loginWithEmail(getDriver());
        LoginHelper.enterThePinCode(getDriver());

        passwordPage = new PasswordPage(getDriver());

        passwordPage.clickOnForgotPassword();
        passwordPage.clickOnIRememberMyPassword();

        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/auth/login-pwd");
    }
}