package tests;

import org.labs247.pages.onboarding.PasswordPage;
import org.labs247.pages.onboarding.VerifyPinPage;
import org.testng.annotations.Test;
import org.labs247.uitils.Constants;
import org.labs247.uitils.LoginHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VerifyPinTest extends BaseTestSetup {

    private VerifyPinPage verifyPinPage;

    @Test(description = "Verify the user is redirected to the Password page when entering a valid Pin Code")
    public void verifyThePinCode() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());

        verifyPinPage.enterSixPinCode(Constants.MASTER_PIN_CODE);
        verifyPinPage.clickOnVerifyPinButton();

        String pinPageTitle = new PasswordPage(getDriver()).getPageTitleMessage();
        assertEquals(pinPageTitle,"Enter Password");
        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/auth/login-pwd");
    }

    @Test(description = "Verify that the email of the user displayed correctly if user used email method to login")
    public void verifyThatTheEmailIsDisplayedCorrectly() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());

        assertEquals(verifyPinPage.getPageSubTitleMessage(),Constants.VALID_EMAIL,"The user email is not displayed correctly");
    }

    @Test(description = "Verify that the pin code input fields accept valid format code digits")
    public void verifyWhenEnteringAnySixCharacters() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());

        verifyPinPage.enterSixPinCode(Constants.INVALID_PIN_CODE);
        verifyPinPage.clickOnVerifyPinButton();

        assertEquals(verifyPinPage.getErrorMessage(),"One time pin is incorrect.");
    }

    @Test(description = "Verify the error message when entering incorrect pin code")
    public void verifyWhenEnteringIncorrectPinCode() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());

        verifyPinPage.enterSixPinCode(Constants.INCORRECT_PIN_CODE);
        verifyPinPage.clickOnVerifyPinButton();

        assertEquals(verifyPinPage.getErrorMessage(),"Entered pin is incorrect.", "The error message is incorrect or not displayed");
    }

    @Test(description = "Verify that the resend pin button is clickable")
    public void verifyResendPinButton() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());

        assertTrue(verifyPinPage.isResendPinButtonClickable(),"The resend pin button should be clickable");
    }

    @Test(description = "Verify that the user is redirected to the login page when clicking on the Go Back button")
    public void verifyGoBackButton() {
        LoginHelper.loginWithEmail(getDriver());

        verifyPinPage = new VerifyPinPage(getDriver());
        verifyPinPage.clickOnGoBackButton();

        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/auth/login");
    }
}
