package tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.labs247.uitils.LoginHelper;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListenerSetup.class})
public class LoggedInUserBaseTestSetup extends BaseTestSetup {

    @Parameters(value={"browser"})
    @BeforeMethod(description = "initialize the web drive", alwaysRun = true)
    @Override
    public void setUp (@Optional("firefox") String browser) throws MalformedURLException {
        LoginHelper.loginToApplicationApi();
        driverThreadLocal.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));

        String[] tokenSplitter = LoginHelper.getToken().split("\\|");

        Cookie cookie = new Cookie("auth._token.school","Bearer%20"+tokenSplitter[0]+"%7C"+tokenSplitter[1]);

        driverThreadLocal.get().get(BASE_URL);
        driverThreadLocal.get().manage().addCookie(cookie);
        driverThreadLocal.get().get(BASE_URL);
    }

    @Override
    public WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    @AfterMethod(description = "close the web drive", alwaysRun = true)
    @Override
    public void tearDown() {
        getDriver().quit();
    }
}