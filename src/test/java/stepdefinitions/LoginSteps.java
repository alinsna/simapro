package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.TestContext;

public class LoginSteps {

    private final TestContext context;
    private final LoginPage loginPage;
    private final WebDriver driver;

    private static final String LOGIN_URL = "https://simapro.fahrulhehehe.my.id/login";

    public LoginSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.loginPage = new LoginPage(driver);
        // HomePage tidak lagi diperlukan di sini karena verifikasi dashboard dipindah
    }

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        driver.get(LOGIN_URL);
        context.getTest().log(Status.INFO, "Navigated to login page: " + LOGIN_URL);
    }

    @When("User enters email {string} and password {string}")
    public void userEntersEmailAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        String emailLog = email.isEmpty() ? "[EMPTY]" : email;
        context.getTest().log(Status.INFO, "Entered email: " + emailLog + " and password: [HIDDEN]");
    }

    @And("User clicks the login button")
    public void userClicksTheLoginButton() {
        loginPage.clickLogin();
        context.getTest().log(Status.INFO, "Clicked the login button.");
    }

    @Then("An error message containing {string} should be displayed")
    public void anErrorMessageContainingShouldBeDisplayed(String expectedMessage) {
        String actualMessage = loginPage.getAlertTextIfPresent();
        if (actualMessage == null) {
            actualMessage = loginPage.getErrorMessage();
        }
        Assert.assertNotNull("No error message was displayed.", actualMessage);
        boolean isMessageCorrect = actualMessage.toLowerCase().contains(expectedMessage.toLowerCase());
        Assert.assertTrue(
                String.format("Error message did not contain the expected text. Expected: '%s', Actual: '%s'", expectedMessage, actualMessage),
                isMessageCorrect
        );
        context.getTest().log(Status.PASS, "Correct error message was displayed: " + actualMessage);
    }
}