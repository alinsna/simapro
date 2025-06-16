package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators (sudah benar dari sebelumnya)
    private final By dashboardHeader = By.xpath("//a[contains(., 'Sistem Informasi Manajemen Proyek')]");
    private final By addStakeholderButton = By.xpath("//button[contains(., 'Add Profil Stakeholder')]");
    private final By addNewProjectButton = By.xpath("//button[contains(., 'Add New Project')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        // Kita gunakan instance wait ini di seluruh kelas
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

    /**
     * Metode bantuan yang diperbarui sesuai dengan pola yang Anda minta.
     * Logika: Scroll 250px ke bawah, lalu tunggu elemen hingga bisa diklik.
     *
     * @param locator Lokator (By) dari elemen yang ingin diklik.
     */
    private void scrollToElementAndClick(By locator) {
        try {
            // 1. Lakukan scroll ke bawah sejauh 250px (sesuai permintaan)
            // Casting driver ke JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 550);");
            System.out.println("Melakukan scroll ke bawah sejauh 250px.");

            // 2. Tunggu elemen muncul dan bisa diklik setelah scroll
            // Menggunakan instance 'wait' yang sudah ada di kelas
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("Elemen ditemukan dan bisa diklik: " + locator);

            // 3. Lakukan klik, dengan fallback ke JavaScript jika ada interupsi
            try {
                button.click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Klik normal gagal, mencoba klik dengan JavaScript.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

        } catch (TimeoutException e) {
            System.err.println("ERROR: Elemen dengan lokator '" + locator + "' tidak ditemukan dalam 10 detik setelah scroll 250px.");
            throw new RuntimeException("Gagal menemukan atau mengklik elemen setelah scroll: " + locator, e);
        }
    }
}