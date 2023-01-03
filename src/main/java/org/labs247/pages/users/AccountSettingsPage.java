package org.labs247.pages.users;

import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountSettingsPage extends BasePage {

    private final By accountDetailsPageTitle = By.xpath("//h2[text()='Account Details']");

    public AccountSettingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountDetailsTitleVisible() {
        return elementVisible(accountDetailsPageTitle);
    }
}
