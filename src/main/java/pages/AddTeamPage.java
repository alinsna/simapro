package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTeamPage {
  private WebDriver driver;
  private WebDriverWait wait;

  // Locators
  private By pageVerificationElement = By.xpath("//label[@for='teamName']")
  private By teamNameField = By.id("teamName");
  private By teamPMField = By.id("projectManager");
  private By teamFEField = By.id("frontEnd");
  private By teamBEField = By.id("backEnd");
  private By teamUIField = By.id("uiux");
  private By addTeamButton = By.xpath("//button[contains(text(),'Upload')]");

  // Constructor
  public AddTeamPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public boolean isOnAddTeamPage() {
    try {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(pageVerificationElement)).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  public void scrollToForm() {
    WebElement formElement = wait.until(ExpectedConditions.presenceOfElementLocated(pageVerificationElement));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formElement);
  }

  public void enterTeamName(String teamName) {
    driver.findElement(teamNameField).sendKeys(teamName);
  }

  public void enterTeamPM(String teamPM) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(teamPMField)).sendKeys(teamPM);

    By pmOptionLocator = By.xpath(String.format("//div[contains(@class, 'cursor-pointer')]//div[text()='%s']", teamPM));
    WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(pmOptionLocator));
  }

  public void enterTeamFE(String teamFE) {
    driver.findElement(teamFEField).sendKeys(teamFE);
  }

  public void enterTeamBE(String teamBE) {
    driver.findElement(teamBEField).sendKeys(teamBE);
  }

  public void enterTeamUI(String teamUI) {
    driver.findElement(teamUIField).sendKeys(teamUI);
  }

  public void clickAddTeamButton() {
    driver.findElement(addTeamButton).click();
  }

  public String getMessage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement modalTextEl = wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[contains(@class, 'flex')]//h3")
      )
    );

    return modalTextEl.getText();
  }

}
