package tests;

import org.labs247.pages.DocumentsPage;
import org.labs247.pages.home.HomeScreenPage;
import org.labs247.pages.invoices.InvoicePaymentsPage;
import org.labs247.pages.users.AccountSettingsPage;
import org.labs247.pages.users.ManageUsersPage;
import org.testng.annotations.Test;
import uitils.Constants;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeScreenTest extends LoggedInUserBaseTest{

    HomeScreenPage homeScreenPage;

    @Test(description = "Verify the user is on the Home page")
    public void verifyHomePageUrl(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.isSalutationMessageVisible(),"The Salutation Message is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/home");
    }

    @Test(description = "Verify that the user name is displayed to on Home screen ")
    public void verifyNameOfUser(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.getSalutationMessage().contains("School Owner"),"The name of the user is not displayed");
    }

    @Test(description = "Verify that the New Policy Menu is shown when clicking on Order Policies button")
    public void verifyNewPolicyMenuVisibility(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnOrderPolicies();

        assertTrue(homeScreenPage.isOrderNewPolicyMenuVisible(),"The New Policy Menu is not shown");
    }

//    @Test(description = "Verify the user is redirected to the Manage Policies page when clicking on Manage Policies button")
//    public void verifyManagePoliciesUrl(){
//        homeScreenPage = new HomeScreenPage(getDriver());
//
//        homeScreenPage.clickOnManagePolicies();
//
//        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/manage-policies");
//    }

    @Test(description = "Verify the user is redirected to the Invoice Payments page when clicking on Invoice Payments button")
    public void verifyInvoiceAndPaymentsUrl(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnInvoicesAndPayments();
        InvoicePaymentsPage invoicePaymentsPage = new InvoicePaymentsPage(getDriver());

        assertTrue(invoicePaymentsPage.isInvoiceTitleVisible(),"The Title is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/invoice-payments");
    }

    @Test(description = "Verify the user is redirected to the Account Details page when clicking on Manage Users button")
    public void verifyManageUsersUrl(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnManageUsers();

        ManageUsersPage manageUsersPage = new ManageUsersPage(getDriver());

        assertTrue(manageUsersPage.isUsersPageTitleVisible(),"The Title is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/profile?tab=manage-users");
    }

    @Test(description = "Verify the user is redirected to the Guard.me Documents page when clicking on Guard.me Documents button")
    public void verifyGuardMeDocumentsUrl(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnMyGuardMeDocuments();
        DocumentsPage documentsPage = new DocumentsPage(getDriver());

        assertTrue(documentsPage.isDocumentsTitleVisible(),"The Title is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/documents");
    }

    @Test(description = "Verify the user is redirected to the Account Details page when clicking on Account Settings button")
    public void verifyAccountSettingsUrl(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnAccountSettings();
        AccountSettingsPage accountSettingsPage = new AccountSettingsPage(getDriver());

        assertTrue(accountSettingsPage.isAccountDetailsTitleVisible(),"The Title is not visible");
        assertEquals(getDriver().getCurrentUrl(),"https://school.guardme.247demo.ca/profile?tab=account-details");
    }

    @Test(description = "Verify that the page Logo is displayed at the page corner")
    public void verifyPageLogo(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.isLogoVisible(),"The Logo is not visible");
    }

    @Test(description = "Verify that the page Campus Name is displayed at the page corner")
    public void verifyCampusIsDisplayed(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.isCampusVisible(),"The Campus Name is not visible");
        assertEquals(homeScreenPage.getCampusName(),"Westminster School", "The Campus Name is not correct");
    }

    @Test(description = "Verify that the Hamburger Icon is displayed and clickable at the page corner")
    public void verifyHamburgerIcon(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.isHamburgerIconVisible(),"The Hamburger Icon is not visible");
        assertTrue(homeScreenPage.isHamburgerIconClickable(),"The Hamburger Icon is not clickable");
    }

    @Test(description = "Verify that the Notifications Icon is displayed and clickable at the page corner")
    public void verifyNotificationsIcon(){
        homeScreenPage = new HomeScreenPage(getDriver());

        assertTrue(homeScreenPage.isNotificationsIconVisible(),"The Notifications Icon is not visible");
        assertTrue(homeScreenPage.isNotificationsIconClickable(),"The Notifications Icon is not clickable");
    }

    @Test(description = "Verify when the user selects the Ireland product is shown successfully")
    public void verifyOrderNewPolicyWithIrelandProduct(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnOrderPolicies();
        homeScreenPage.clickOnSelectDropDown();
        homeScreenPage.selectProduct(Constants.IRELAND_PRODUCT);

        assertEquals(homeScreenPage.getSelectedProduct(),Constants.IRELAND_PRODUCT);
    }

    @Test(description = "Verify when the user selects the Multi Risk product is shown successfully")
    public void verifyOrderNewPolicyWithMultiRiskProduct(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnOrderPolicies();
        homeScreenPage.clickOnSelectDropDown();
        homeScreenPage.selectProduct(Constants.MULTI_RISK_PRODUCT);

        assertEquals(homeScreenPage.getSelectedProduct(),Constants.MULTI_RISK_PRODUCT);
    }

    @Test(description = "Verify when the user selects the Multi Risk + Cancellation product is shown successfully")
    public void verifyOrderNewPolicyWithMultiRiskAndCancellationProduct(){
        homeScreenPage = new HomeScreenPage(getDriver());

        homeScreenPage.clickOnOrderPolicies();
        homeScreenPage.clickOnSelectDropDown();
        homeScreenPage.selectProduct(Constants.MULTI_RISK_CANCELLATION_PRODUCT);

        assertEquals(homeScreenPage.getSelectedProduct(),Constants.MULTI_RISK_CANCELLATION_PRODUCT);
    }

}
