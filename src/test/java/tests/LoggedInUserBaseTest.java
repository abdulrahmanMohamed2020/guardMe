package tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import uitils.LoginHelper;
import uitils.TestListener;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public class LoggedInUserBaseTest extends BaseTest {

    @Parameters(value={"browser"})
    @BeforeMethod(description = "initialize the web drive", alwaysRun = true)
    @Override
    public void setUp (@Optional("firefox") String browser) throws MalformedURLException {
        LoginHelper.loginToApplicationApi();
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));

        Cookie cookie = new Cookie(
                "auth._token.school","Bearer%20"+LoginHelper.getToken().substring(0,2)+
                "%7C"+LoginHelper.getToken().substring(3));

        driver.get().get(BASE_URL);
        driver.get().manage().addCookie(cookie);
        driver.get().get(BASE_URL);
    }

    @Override
    public WebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod(description = "close the web drive", alwaysRun = true)
    @Override
    public void tearDown() {
        getDriver().quit();
    }
}