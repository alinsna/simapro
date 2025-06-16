package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AddProjectPage {
    WebDriver driver;

    // Locators
    private By uploadImageButton = By.id("uploadImage");
    private By projectNameField = By.id("projectName");
    private By padButton = By.id("padButton");
    private By selectProjectButton = By.id("selectProjectButton");
    private By projectOption1 = By.xpath("//option[contains(text(),'Proyek Aplikasi Dasar 1')]");
    private By projectOption2 = By.xpath("//option[contains(text(),'Proyek Aplikasi Dasar 2')]");
    private By stakeholderField = By.id("stakeholder");
    private By groupNameField = By.id("groupName");
    private By descriptionField = By.id("description");
    private By submitButton = By.id("submitButton");

    // Constructor
    public AddProjectPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadImage(String imagePath) {
        driver.findElement(uploadImageButton).sendKeys(imagePath);
    }

    public void enterProjectName(String projectName) {
        driver.findElement(projectNameField).sendKeys(projectName);
    }

    public void clickPadButton() {
        driver.findElement(padButton).click();
    }

    public void selectProject(String project) {
        driver.findElement(selectProjectButton).click();
        if (project.equals("Proyek Aplikasi Dasar 1")) {
            driver.findElement(projectOption1).click();
        } else if (project.equals("Proyek Aplikasi Dasar 2")) {
            driver.findElement(projectOption2).click();
        }
    }

    public void enterStakeholder(String stakeholder) {
        driver.findElement(stakeholderField).sendKeys(stakeholder);
    }

    public void enterGroupName(String groupName) {
        driver.findElement(groupNameField).sendKeys(groupName);
    }

    public void enterDescription(String description) {
        driver.findElement(descriptionField).sendKeys(description);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }
}
