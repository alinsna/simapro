package stepdefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddTeamPage;
import pages.LoginPage;

public class AddTeamSteps {

  WebDriver driver;
  LoginPage loginPage;
  AddTeamPage addTeamPage;

  @BeforeAll
  public void setup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  @Given("User is logged in with valid credentials")
  public void userIsLoggedInWithValidCredentials() {
    driver.get("https://simapro.fahrulhehehe.my.id/login");
    driver.manage().window().maximize();
    loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
    loginPage.enterPassword("adminpassword");
    loginPage.clickLogin();

    // Handle alert after login
    String alertText = loginPage.getAlertTextIfPresent();
    if (alertText != null) {
      System.out.println("Alert message: " + alertText);
    }
  }

  @When("User on the homepage and clicks on add new team button")
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
}
