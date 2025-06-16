package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddStakeholderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // === LOKATOR HANYA UNTUK FORMULIR TAMBAH STAKEHOLDER ===
    private final By pageHeader = By.xpath("//div[text()='Profile Stakeholder']");
    private final By nameField = By.id("stakeholderName");
    private final By categoryDropdownButton = By.xpath("//button[contains(., 'Pilih kategori')]");
    private final By emailField = By.id("email");
    private final By contactField = By.id("noTelepon");
    private final By photoUploadInput = By.id("foto");
    private final By submitButton = By.xpath("//div[contains(@class, 'p-20')]//button[text()='Submit']");

    // Lokator untuk modal konfirmasi
    private final By confirmationModal = By.xpath("//h3[contains(., 'Are you sure')]//ancestor::div[@role='dialog' or @role='alertdialog' or contains(@class, 'fixed')]");
    private final By finalSubmitButton = By.xpath("//div[contains(@class, 'fixed')]//button[text()=\"Yes, I'm sure\"]");

    private final By failedModal = By.id("failedModal");
    private final By failedModalMessageText = By.xpath("//div[@id='failedModal']//h3");
    private final By failedModalCloseButton = By.xpath("//div[@id='failedModal']//button[text()='Close']");


    public AddStakeholderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnAddStakeholderPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadProfileImage(String absoluteImagePath) {
        driver.findElement(photoUploadInput).sendKeys(absoluteImagePath);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterContact(String number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactField)).sendKeys(number);
    }

    public void selectCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(categoryDropdownButton)).click();
        By optionLocator = By.xpath(String.format("//button[@role='menuitem' and text()='%s']", category));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public void clickSubmitButton() {
        WebElement initialBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", initialBtn);
        initialBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
        wait.until(ExpectedConditions.elementToBeClickable(finalSubmitButton)).click();
    }

    public String getFailedModalMessage() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(failedModal));
        return modal.findElement(failedModalMessageText).getText();
    }

    public void closeFailedModal() {
        wait.until(ExpectedConditions.elementToBeClickable(failedModalCloseButton)).click();
    }
}