package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTeamPage {

  // Locators
  private By teamNameField = By.id("teamName");
  private By teamPMField = By.id("projectManager");
  private By teamFEField = By.id("frontEnd");
  private By teamBEField = By.id("backEnd");
  private By teamUIField = By.id("uiux");
  private By addTeamButton = By.xpath("//button[contains(text(),'Upload')]");

  // Constructor
  public AddTeamPage(org.openqa.selenium.WebDriver driver) {
    this.driver = driver;
  }

  public void enterTeamName(String teamName) {
    driver.findElement(teamNameField).sendKeys(teamName);
  }

  public void enterTeamPM(String teamPM) {
    driver.findElement(teamPMField).sendKeys(teamPM);
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

  org.openqa.selenium.WebDriver driver;
}
