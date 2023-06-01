package org.labs247.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagePoliciesPage extends BasePage {

    private final By managePoliciesPageTitle = By.xpath("//p[contains(text(),'Policies')]//parent::div");

    public ManagePoliciesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isManagePoliciesTitleVisible() {
        return elementVisible(managePoliciesPageTitle);
    }
}
