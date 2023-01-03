package org.labs247.pages.users;

import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageUsersPage extends BasePage {

    private final By usersPageTitle = By.xpath("//h2[text()='Users']");

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUsersPageTitleVisible() {
        return elementVisible(usersPageTitle);
    }
}
