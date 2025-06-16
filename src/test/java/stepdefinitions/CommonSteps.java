package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.TestContext;

public class CommonSteps {

    private final TestContext context;
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;

    public CommonSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
    }

    /**
     * Langkah umum untuk login.
     * Dapat digunakan oleh feature manapun sebagai prasyarat.
     */
    @Given("User is logged in with valid credentials")
    public void userIsLoggedInWithValidCredentials() {
        if (!homePage.isOnDashboard()) {
            driver.get("https://simapro.fahrulhehehe.my.id/login");
            loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
            loginPage.enterPassword("adminpassword");
            loginPage.clickLogin();
        }
    }

    /**
     * Langkah umum untuk verifikasi pengalihan ke dashboard.
     */
    @Then("User should be redirected to the dashboard")
    public void userShouldBeRedirectedToTheDashboard() {
        Assert.assertTrue("Not redirected to the dashboard.", homePage.isOnDashboard());
        context.getTest().log(Status.PASS, "Successfully redirected to the dashboard.");
    }
}