package org.labs247.pages.invoices;

import org.labs247.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvoicePaymentsPage extends BasePage {

    private final By invoicePaymentsTitle = By.xpath("//h2[text()='Invoice Payments']");

    public InvoicePaymentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInvoiceTitleVisible() {
        return elementVisible(invoicePaymentsTitle);
    }
}
