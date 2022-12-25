package uitils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.labs247.pages.onboarding.LoginPage;
import org.labs247.pages.onboarding.VerifyPinPage;
import org.openqa.selenium.WebDriver;
import tests.Constants;

import java.util.HashMap;
import java.util.Map;

public class LoginHelper {

    public static String token;

    public static void loginWithEmail(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(Constants.VALID_EMAIL);
        loginPage.clickOnContinue();
    }

    public static void enterThePinCode(WebDriver driver) {
        VerifyPinPage verifyPinPage = new VerifyPinPage(driver);
        verifyPinPage.enterSixPinCode(Constants.MASTER_PIN_CODE);
        verifyPinPage.clickOnVerifyPinButton();
    }

    public static void loginToApplicationApi() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(gerUserDataParams())
                .post("https://api.guardme.247demo.ca/api/school/auth/sign-in");

        token = response.body().jsonPath().getString("data.token");
    }
    private static Map<String,String> gerUserDataParams() {

        Map<String,String> userDataMap = new HashMap<>();
        userDataMap.put("email", Constants.VALID_EMAIL);
        userDataMap.put("pin", "000000");
        userDataMap.put("password", "000000");

        return userDataMap;
    }

    public static String getToken() {
        return token;
    }

}
