package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddTeamPage;
import pages.LoginPage;
import utils.TestContext;

public class AddTeamSteps {

  WebDriver driver;
  LoginPage loginPage;
  AddTeamPage addTeamPage;

  public AddTeamSteps(TestContext context) {
    this.driver = context.getDriver();
    this.loginPage = new LoginPage(driver);
    this.addTeamPage = new AddTeamPage(driver);
  }

  @Given("User is logged in with valid credentials in dashboard")
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

  @When("User scrolls down on the homepage and clicks on add new team button")
  public void userOnTheHomepageAndClicksOnAddNewTeamButton() {
    driver.get("https://simapro.fahrulhehehe.my.id/home");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    js.executeScript("window.scrollBy(0, 250)", "");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Selenium 4.x
    WebElement addNewProjectButton = wait.until(
      ExpectedConditions.elementToBeClickable(
        By.xpath("//button[contains(., 'Add Team')]")
      )
    );

    addNewProjectButton.click();
  }

  @Then("User is redirected to the add team page")
  public void userIsRedirectedToAddTeamPage() {
    String currentUrl = driver.getCurrentUrl();
    assert currentUrl.equals(
      "https://simapro.fahrulhehehe.my.id/team/add-profile-team"
    );
  }

  @When("User fills in team details:")
  public void userFillsInTeamDetails(DataTable dataTable) {
    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : data) {
      String field = row.get("Field");
      String value = row.get("Value");

      switch (field) {
        case "Nama Team":
          addTeamPage.enterTeamName(value);
          break;
        case "Project Manager":
          addTeamPage.enterTeamPM(value);
          break;
        case "Front End":
          addTeamPage.enterTeamFE(value);
          break;
        case "Back End":
          addTeamPage.enterTeamBE(value);
          break;
        case "UI/UX":
          addTeamPage.enterTeamUI(value);
          break;
        default:
          throw new IllegalArgumentException("Field " + field + " is not recognized.");
      }
    }
  }

  @When("User submits the team")
  public void userClicksOnAddTeamButton() {
    addTeamPage.clickAddTeamButton();
    addTeamPage.clickConfirmModalButton();
  }

  @Then("The team is added successfully")
  public void theTeamIsAddedSuccessfully() {
    String successMessage = addTeamPage.getMessage();

    Assert.assertNotNull("Team successfully added!", successMessage);
  }


//  SKENARIO GAGAL //
  @When("User leaves all team detail fields empty")
  public void userLeavesAllTeamDetailFieldsEmpty() {
    System.out.println("Intentionally leaving form fields empty to test validation.");
  }

  @Then("The team is not added successfully")
  public void theTeamIsAddedFailure() {
    String successMessage = addTeamPage.getMessage();

    Assert.assertNotNull("Team failed to upload!", successMessage);
  }
}
