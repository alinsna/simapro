package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddProjectPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ... (Semua lokator Anda sudah benar dan tidak perlu diubah) ...
    private final By pageVerificationElement = By.xpath("//label[@for='project-name']");
    private final By uploadImageInput = By.id("image-upload-1");
    private final By projectNameField = By.id("project-name");
    private final By stakeholderField = By.id("stakeholder");
    private final By groupNameField = By.id("group-name");
    private final By descriptionField = By.id("description");
    private final By initialSubmitButton = By.xpath("//button[text()='Submit']");
    private final By confirmationModal = By.id("defaultModal");
    private final By finalSubmitButton = By.xpath("//div[@id='defaultModal']//button[@type='submit']");
    private final By yearField = By.id("year");
    private final By projectTypeDropdownButton_Xpath = By.xpath("//button[contains(.,'Select Project')]");

    private final By failedModal = By.id("failedModal");
    private final By failedModalMessageText = By.xpath("//div[@id='failedModal']//h3");
    private final By failedModalCloseButton = By.xpath("//div[@id='failedModal']//button[text()='Close']");

    private final By successModal = By.xpath("//h3[text()='Project upload success!']/ancestor::div[contains(@class, 'bg-primary')]");
    private final By successModalMessageText = By.xpath("//h3[text()='Project upload success!']");
    private final By successModalCloseButton = By.xpath("//button[@data-modal-hide='successModal']");

    public AddProjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ... (Metode lain tidak perlu diubah) ...

    public boolean isOnAddProjectPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageVerificationElement)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToForm() {
        WebElement formElement = wait.until(ExpectedConditions.presenceOfElementLocated(pageVerificationElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formElement);
    }

    public void uploadImage(String absoluteImagePath) {
        driver.findElement(uploadImageInput).sendKeys(absoluteImagePath);
    }

    public void enterProjectName(String projectName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectNameField)).sendKeys(projectName);
    }

    public void enterYear(String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(yearField)).sendKeys(year);
    }

    public void enterStakeholder(String stakeholder) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stakeholderField)).sendKeys(stakeholder);
    }

    public void enterGroupName(String groupName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(groupNameField)).sendKeys(groupName);
    }

    public void enterDescription(String description) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField)).sendKeys(description);
    }

    public void selectProjectType(String projectType) {
        try {
            WebElement dropdownBtn = wait.until(ExpectedConditions.elementToBeClickable(projectTypeDropdownButton_Xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownBtn);
            try {
                Thread.sleep(300);
                dropdownBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownBtn);
            }
            By optionLocator = By.xpath(String.format("//button[@role='menuitem' and text()='%s']", projectType));
            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            System.err.println("Gagal memilih '" + projectType + "' dari dropdown.");
            throw new RuntimeException(e);
        }
    }

    /**
     * === METODE SUBMIT DIPERBAIKI DENGAN LOGIKA SCROLL DAN JAVASCRIPT CLICK ===
     */
    public void submitForm() {
        try {
            // 1. Temukan tombol "Submit" awal
            WebElement initialBtn = wait.until(ExpectedConditions.presenceOfElementLocated(initialSubmitButton));

            // 2. Scroll tombol tersebut ke tengah layar untuk memastikan tidak terhalang
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", initialBtn);

            // 3. Beri jeda singkat untuk stabilisasi
            Thread.sleep(300);

            // 4. Coba klik tombol "Submit" awal, dengan fallback JavaScript
            try {
                initialBtn.click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Klik tombol Submit awal gagal, mencoba dengan JavaScript.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", initialBtn);
            }

            // 5. Setelah modal muncul, tunggu dan klik tombol konfirmasi final
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
            wait.until(ExpectedConditions.elementToBeClickable(finalSubmitButton)).click();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Proses submit terganggu.", e);
        }
    }

    public String getFailedModalMessage() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(failedModal));
        return modal.findElement(failedModalMessageText).getText();
    }

    public void closeFailedModal() {
        wait.until(ExpectedConditions.elementToBeClickable(failedModalCloseButton)).click();
    }

    public String getSuccessModalMessage() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return modal.findElement(successModalMessageText).getText();
    }

    public void closeSuccessModal() {
        wait.until(ExpectedConditions.elementToBeClickable(successModalCloseButton)).click();
    }
}