package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.className("invalid-feedback");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifies that all critical elements on the login page are visible.
     */
    public void isOnLoginPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    /**
     * Enters the user's email into the email field.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Enters the user's password into the password field.
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Gets the text from a validation error message.
     * @return The error message text or a default message if not found.
     */
    public String getErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return errorElement.getText();
        } catch (TimeoutException e) {
            return "No error message displayed";
        }
    }

    /**
     * Checks for a JavaScript alert, gets its text, and accepts it.
     * @return The text of the alert, or null if no alert is present.
     */
    public String getAlertTextIfPresent() {
        try {
            // Use a shorter wait for alerts as they should appear quickly.
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept(); // Close the alert
            return alertText;
        } catch (TimeoutException e) {
            return null; // No alert found
        }
    }
}