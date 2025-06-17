package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dashboardHeader = By.xpath("//a[contains(., 'Sistem Informasi Manajemen Proyek')]");
    private final By addStakeholderButton = By.xpath("//button[contains(., 'Add Profil Stakeholder')]");
    private final By addNewProjectButton = By.xpath("//button[contains(., 'Add New Project')]");
    private final By addMahasiswaButton = By.xpath("//button[contains(text(),'Add Profil Mahasiswa')]"); // update di sini

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnDashboard() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddNewProjectButton() {
        scrollToElementAndClick(addNewProjectButton);
    }

    public void clickAddStakeholderButton() {
        scrollToElementAndClick(addStakeholderButton);
    }

    public void clickAddMahasiswaButton() {
        scrollToElementAndClick(addMahasiswaButton); // pakai locator baru
    }

    private void scrollToElementAndClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            try {
                button.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }
        } catch (TimeoutException e) {
            System.err.println("ERROR: Elemen dengan lokator '" + locator + "' tidak ditemukan atau tidak bisa diklik.");
            throw new RuntimeException("Gagal menemukan atau mengklik elemen setelah scroll: " + locator, e);
        }
    }
}
