package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StakeholderListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // === LOKATOR UNTUK HALAMAN DAFTAR STAKEHOLDER ===
    // Verifikasi halaman dengan memeriksa link navigasi yang aktif
    private final By pageVerificationElement = By.xpath("//a[@aria-current='page' and text()='Stakeholder']");
    private final By stakeholderGridContainer = By.xpath("//div[contains(@class, 'grid-cols-4')]");

    public StakeholderListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Memverifikasi apakah pengguna berada di halaman daftar stakeholder.
     * @return true jika link navigasi "Stakeholder" aktif.
     */
    public boolean isOnStakeholderListPage() {
        try {
            // Menunggu container grid stakeholder muncul, sebagai tanda halaman sudah dimuat
            wait.until(ExpectedConditions.visibilityOfElementLocated(stakeholderGridContainer));
            // Verifikasi tambahan dengan memeriksa link navigasi
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageVerificationElement)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Memeriksa apakah stakeholder dengan nama tertentu muncul di halaman daftar.
     * @param stakeholderName Nama stakeholder yang dicari.
     * @return true jika ditemukan, false jika tidak.
     */
    public boolean isStakeholderVisibleInList(String stakeholderName) {
        // Lokator dinamis untuk mencari <h1> dengan nama yang sesuai di dalam kartu stakeholder
        By stakeholderCardHeader = By.xpath(String.format("//div[contains(@class, 'bg-[#FBF9F1]')]//h1[text()='%s']", stakeholderName));
        try {
            // Scroll ke bawah untuk memastikan semua kartu dimuat jika ada lazy loading
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            wait.until(ExpectedConditions.visibilityOfElementLocated(stakeholderCardHeader));
            return true;
        } catch (Exception e) {
            System.err.println("Stakeholder dengan nama '" + stakeholderName + "' tidak ditemukan di daftar.");
            return false;
        }
    }
}