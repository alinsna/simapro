package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddMahasiswaPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.id("mahasiswaName");
    private final By nimField = By.id("nim");
    private final By photoUpload = By.id("foto");
    private final By submitButton = By.xpath("//button[text()='Submit']");
    private final By swalText = By.className("swal-text");
    private final By swalButton = By.className("swal-button");

    public AddMahasiswaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnAddMahasiswaPage() {
        try {
            return wait.until(ExpectedConditions.urlContains("/mahasiswa/add-mahasiswa"));
        } catch (Exception e) {
            return false;
        }
    }

    public void enterName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterNIM(String nim) {
        WebElement nimInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nimField));
        nimInput.clear();
        nimInput.sendKeys(nim);
    }

    public void uploadPhoto(String path) {
        WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(photoUpload));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadInput);
        uploadInput.sendKeys(path);
    }

    public void clickSubmit() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        btn.click();
    }

    public String getFailedMessage() {
        WebElement modalText = wait.until(ExpectedConditions.visibilityOfElementLocated(swalText));
        return modalText.getText();
    }

    public void closeFailedModal() {
        wait.until(ExpectedConditions.elementToBeClickable(swalButton)).click();
    }

    private final By confirmYesButton = By.xpath("//button[text()=\"Yes, I'm sure\"]");

    public void confirmSubmission() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        btn.click();
    }
}
