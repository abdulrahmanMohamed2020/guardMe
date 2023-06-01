package tests;

import org.labs247.pages.onboarding.LoginPage;
import static org.testng.Assert.*;

import org.labs247.pages.onboarding.VerifyPinPage;
import org.testng.annotations.Test;
import org.labs247.uitils.Constants;

public class LoginTest extends BaseTestSetup {

    private LoginPage loginPage;

    @Test(description = "Verify the user is redirected to the Pin Code page when entering valid email")
    public void loginWithValidEmail(){
        loginPage = new LoginPage(getDriver());

        loginPage.enterEmail(Constants.VALID_EMAIL);
        loginPage.clickOnContinue();

        String pinPageTitle = new VerifyPinPage(getDriver()).getPageTitleMessage();
        assertEquals(pinPageTitle,"Verify your pin code");
        assertEquals(getDriver().getCurrentUrl(),"https://school.testing-internal.guardme-jarvis.dev/auth/pin-otp");

    }

    @Test(description = "Verify that login field accept valid email address format")
    public void loginWithInvalidEmailFormat(){
        loginPage = new LoginPage(getDriver());

        loginPage.enterEmail(Constants.INVALID_EMAIL);
        loginPage.clickOnContinue();

        assertEquals(loginPage.getErrorMessage(),"The email must be a valid email address.");
    }

    @Test(description = "Verify the login with not exist email address")
    public void loginWithNotExistEmail(){
        loginPage = new LoginPage(getDriver());

        loginPage.enterEmail(Constants.NOT_EXIST_EMAIL);
        loginPage.clickOnContinue();

        assertEquals(loginPage.getErrorMessage(),"The selected email is invalid.");
    }
}
