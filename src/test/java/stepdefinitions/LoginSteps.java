package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Otomatis download driver yang sesuai
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver.get("https://simapro.fahrulhehehe.my.id/login"); // URL sistem login kamu
        loginPage = new LoginPage(driver);
    }

    @When("User enters valid email and password")
    public void userEntersValidCredentials() {
        loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
        loginPage.enterPassword("adminpassword");
    }

    @When("User enters invalid email {string} and valid password")
    public void userEntersInvalidEmail(String email) {
        loginPage.enterEmail(email);
        loginPage.enterPassword("adminpassword");
    }

    @When("User enters valid email and incorrect password {string}")
    public void userEntersIncorrectPassword(String password) {
        loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
        loginPage.enterPassword(password);
    }

    @When("User leaves email and password fields empty")
    public void userLeavesFieldsEmpty() {
        loginPage.enterEmail("");
        loginPage.enterPassword("");
    }

    @When("User enters boundary email {string} and valid password")
    public void userEntersBoundaryEmail(String email) {
        loginPage.enterEmail(email);
        loginPage.enterPassword("adminpassword");
    }

    @And("User clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the dashboard")
    public void userShouldBeRedirected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/home"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
    }

    @Then("An email format error should be displayed")
    public void emailFormatErrorDisplayed() {
        // Periksa jika alert muncul dan tutup dulu
        String alertText = loginPage.getAlertTextIfPresent();
        if (alertText != null) {
            System.out.println("Alert ditemukan: " + alertText);
            Assert.assertEquals("Login failed", alertText);
            return;
        }

        // Validasi pesan error di halaman
        assertTrue(loginPage.getErrorMessage().toLowerCase().contains("email"));
    }

    @Then("An invalid credential error should be displayed")
    public void invalidCredentialErrorDisplayed() {
        // Cek jika ada alert "Login failed"
        String alertText = loginPage.getAlertTextIfPresent();
        if (alertText != null) {
            System.out.println("Alert ditemukan: " + alertText);
            Assert.assertTrue(alertText.toLowerCase().contains("login failed"));
            return;
        }

        // Kalau tidak ada alert, cari pesan error biasa
        String msg = loginPage.getErrorMessage().toLowerCase();
        assertTrue(msg.contains("invalid") || msg.contains("salah") || msg.contains("tidak"));
    }

    @Then("Required field validation should be displayed")
    public void requiredFieldValidationDisplayed() {
        // Periksa alert terlebih dahulu
        String alertText = loginPage.getAlertTextIfPresent();
        if (alertText != null) {
            System.out.println("Alert ditemukan: " + alertText);
            Assert.assertTrue(alertText.toLowerCase().contains("login failed") || alertText.toLowerCase().contains("required"));
            return;
        }

        // Jika tidak ada alert, validasi pesan error di halaman
        assertTrue(loginPage.getErrorMessage().toLowerCase().contains("required"));
    }
}
