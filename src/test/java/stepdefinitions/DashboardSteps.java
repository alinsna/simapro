package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import utils.TestContext;

public class DashboardSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboard;

    public DashboardSteps(TestContext context) {
        this.driver = context.getDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboard = new DashboardPage(driver);
    }

    @Given("User is logged in with valid credentials in homepage")
    public void userIsLoggedInWithValidCredentials() {
        driver.get("https://simapro.fahrulhehehe.my.id/login");
        loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
        loginPage.enterPassword("adminpassword");
        loginPage.clickLogin();

        String alertText = loginPage.getAlertTextIfPresent();
        if (alertText != null) {
            System.out.println("Alert message: " + alertText);
        }
    }

    @When("User on the homepage and clicks on search bar and enters \"Smart Home Automation\"")
    public void userEntersTheProjectNameHomeAutomation() {
        dashboard.searchProject("Smart Home Automation");
    }

    @And("User clicks enter")
    public void userClicksEnter() {
        dashboard.clickSearchButton();
    }

    @Then("User is redirected to the project list page with the keyword \"Smart Home Automation\"")
    public void userIsRedirectedToTheProjectListPage() {
        Assert.assertEquals("https://simapro.fahrulhehehe.my.id/search-results/projects?query=Smart%20Home%20Automation", dashboard.isOnResultPage());
    }

    @And("The project with the keyword \"Smart Home Automation\" should be displayed")
    public void theProjectWithTheKeywordHomeAutomationShouldBeDisplayed() {
        System.out.println("ini tampillllllll"+dashboard.isProjectDisplayed("Smart Home Automation"));
        Assert.assertTrue("Project found", dashboard.isProjectDisplayed("Smart Home Automation"));
    }

    @When("User on the homepage and clicks on search bar and enters \"abcdefg\"")
    public void userEntersTheProjectNameAbcdefg() {
        dashboard.searchProject("abcdefg");
    }

    @Then("User is redirected to the project list page with the keyword \"abcdefg\"")
    public void userIsRedirectedToTheProjectListPageWithTheKeywordAbcdefg() {
        Assert.assertEquals("https://simapro.fahrulhehehe.my.id/search-results/projects?query=abcdefg", dashboard.isOnResultPage());
    }

    @And("The project with the keyword \"abcdefg\" should not be displayed")
    public void theProjectWithTheKeywordAbcdefgShouldNotBeDisplayed() {
        Assert.assertFalse("Project not found", dashboard.isProjectDisplayed("abcdefg"));
    }
}
