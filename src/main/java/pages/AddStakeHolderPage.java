package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Nama kelas disesuaikan dengan konvensi Java (CamelCase)
public class AddStakeHolderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Locators for Add Stakeholder Form ---
    private final By nameField = By.id("name");
    private final By typeDropdown = By.id("type");
    private final By contactField = By.id("contact");
    private final By emailField = By.id("email");
    private final By submitButton = By.xpath("//button[@type='submit']");

    // Lokator untuk verifikasi halaman
    private final By pageHeader = By.xpath("//h1[normalize-space()='Add Stakeholder']");

    // --- Locators for Stakeholder List ---
    private final By searchBar = By.cssSelector("input[type='search']");
    private final By tableBody = By.tagName("tbody");

    // Constructor
    public AddStakeHolderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * === METODE BARU ===
     * Memeriksa apakah pengguna berada di halaman Add Stakeholder.
     */
    public boolean isOnAddStakeholderPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // --- Actions on Add Stakeholder Form ---
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void selectType(String type) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(typeDropdown));
        new Select(dropdownElement).selectByVisibleText(type);
    }

    public void enterContact(String contact) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactField)).sendKeys(contact);
    }

    public void enterStakeholderEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void clickSubmitButton() {
        WebElement button = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    // --- Actions on Stakeholder List ---
    public void searchFor(String stakeholderName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.clear();
        searchInput.sendKeys(stakeholderName);
    }

    public boolean isStakeholderVisibleInList(String stakeholderName) {
        // Tunggu hingga teks yang dicari muncul di dalam tabel
        wait.until(ExpectedConditions.textToBePresentInElementLocated(tableBody, stakeholderName));
        By stakeholderRow = By.xpath(String.format("//tbody/tr[td[contains(., '%s')]]", stakeholderName));
        return !driver.findElements(stakeholderRow).isEmpty();
    }

    public int getVisibleRowCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr")));
        return driver.findElements(By.xpath("//tbody/tr")).size();
    }
}