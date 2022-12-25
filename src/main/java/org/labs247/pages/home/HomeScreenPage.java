package org.labs247.pages.home;

import io.qameta.allure.Step;
import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeScreenPage extends BasePage {

    private final By homeScreenTitle = By.xpath("//h1[contains(text(),'Good morning, School Owner')]");
    private final By orderPoliciesButton = By.xpath("//h3[contains(text(),'Order Policies')]//parent::div");
    private final By managePoliciesButton = By.xpath("//h3[contains(text(),'Manage Policies')]//parent::div");
    private final By invoicesPaymentsButton = By.xpath("//h3[contains(text(),'Invoices/Payments')]//parent::div");
    private final By manageUsersButton = By.xpath("//h3[contains(text(),'Manage Users')]//parent::div");
    private final By myGuardMeDocumentsButton = By.xpath("//h3[contains(text(),'My Guard.me Documents')]//parent::div");
    private final By accountSettingsButton = By.xpath("//h3[contains(text(),'Account Settings')]//parent::div");
    private final By orderNewPolicyMenu = By.xpath("//span[contains(text(),'Order New Policy')]//parent::div");
    private final By newPolicyDropDown = By.xpath("//parent::div//input[contains(@required,'required')]");
    private final By newPolicyDropDownValue = By.xpath("//div[contains(@class,'v-select__selections')]//following-sibling::input[contains(@required,'required')]//parent::div//child::div");
    private static final String NEW_POLICY_PRODUCTS_STR ="//div[text()='@val']";
    private final By logo = By.xpath("//parent::a//img");
    private final By notificationIcon = By.xpath("//parent::button[contains(@role,'button')]//parent::span//i");
    private final By hamburgerIcon = By.xpath("//parent::button[not(contains(@role,'button'))]//parent::span//i");
    private final By campus = By.xpath("//span[contains(text(),'Castle Park School')]");

    public HomeScreenPage(WebDriver driver) {
        super(driver);
    }
    public String getHomeScreenTitle() {
        return findElement(homeScreenTitle).getText();
    }

    @Step("Click on the Order Policies button")
    public void clickOnOrderPolicies() {
        actionClick(orderPoliciesButton);
    }

    @Step("Click on the Manage Policies button")
    public void clickOnManagePolicies() {
        actionClick(managePoliciesButton);
    }

    @Step("Click on the Invoices/Payments button")
    public void clickOnInvoicesAndPayments() {
        actionClick(invoicesPaymentsButton);
    }

    @Step("Click on the Manage Users button")
    public void clickOnManageUsers() {
        actionClick(manageUsersButton);
    }

    @Step("Click on the My Guard.me Documents button")
    public void clickOnMyGuardMeDocuments() {
        actionClick(myGuardMeDocumentsButton);
    }

    @Step("Click on the Account Settings button")
    public void clickOnAccountSettings() {
        actionClick(accountSettingsButton);
    }

    @Step("Click on the Hamburger button")
    public void clickOnHamburgerIcon() {
        actionClick(hamburgerIcon);
    }

    @Step("Click on the Select Drop-Down button")
    public void clickOnSelectDropDown() {
        actionClick(newPolicyDropDown);
    }

    @Step("Select a/an {0} product")
    public void selectProduct(String productName) {
        actionClick(By.xpath(NEW_POLICY_PRODUCTS_STR.replace("@val",productName)));
    }

    public boolean isOrderNewPolicyMenuVisible() {
        return elementVisible(orderNewPolicyMenu);
    }

    public boolean isLogoVisible() {
        return elementVisible(logo);
    }

    public boolean isNotificationsIconVisible() {
        return elementVisible(notificationIcon);
    }

    public boolean isNotificationsIconClickable() {
        return elementClickable(notificationIcon);
    }

    public boolean isHamburgerIconVisible() {
        return elementVisible(hamburgerIcon);
    }

    public boolean isHamburgerIconClickable() {
        return elementClickable(hamburgerIcon);
    }

    public String getSelectedProduct() {
        return findElement(newPolicyDropDownValue).getText();
    }

    public boolean isCampusVisible() {
        return elementVisible(campus);
    }

    public String getCampusName() {
        return findElement(campus).getText();
    }
}
