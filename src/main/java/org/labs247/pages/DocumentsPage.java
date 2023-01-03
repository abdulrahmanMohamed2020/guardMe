package org.labs247.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocumentsPage extends BasePage {

    private final By documentsPageTitle = By.xpath("//h2[text()='Guard.me Documents']");

    public DocumentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDocumentsTitleVisible() {
        return elementVisible(documentsPageTitle);
    }
}
