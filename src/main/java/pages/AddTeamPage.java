package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTeamPage {
  private WebDriver driver;
  private WebDriverWait wait;

  // Locators
  private By pageVerificationElement = By.xpath("//label[@for='teamName']");
  private By teamNameField = By.id("teamName");
  private By teamPMField = By.id("projectManager");
  private By teamFEField = By.id("frontEnd");
  private By teamBEField = By.id("backEnd");
  private By teamUIField = By.id("uiux");
  private By addTeamButton = By.xpath("//button[contains(text(),'Upload')]");
  private By confirmModalButton = By.xpath("//div[@id='confirmModal']//button[@type='submit']");

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

    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", optionElement);
      wait.until(ExpectedConditions.elementToBeClickable(optionElement));
      Thread.sleep(300); // Small delay for UI stabilization
      optionElement.click();
    } catch (ElementClickInterceptedException e) {
      System.out.println("Standard click failed for PM option '" + teamPM + "', trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (TimeoutException toe) {
      System.err.println("Timeout waiting for PM option '" + teamPM + "' to be clickable. Trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted while selecting PM: " + teamPM);
      throw new RuntimeException("Interrupted during PM selection", e);
    }
  }

  public void enterTeamFE(String teamFE) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(teamFEField)).sendKeys(teamFE);

    By feOptionLocator = By.xpath(String.format("//div[contains(@class, 'cursor-pointer')]//div[text()='%s']", teamFE));
    WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(feOptionLocator));

    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", optionElement);
      wait.until(ExpectedConditions.elementToBeClickable(optionElement));
      Thread.sleep(300); // Small delay for UI stabilization
      optionElement.click();
    } catch (ElementClickInterceptedException e) {
      System.out.println("Standard click failed for FE option '" + teamFE + "', trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (TimeoutException toe) {
      System.err.println("Timeout waiting for FE option '" + teamFE + "' to be clickable. Trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted while selecting FE: " + teamFE);
      throw new RuntimeException("Interrupted during FE selection", e);
    }
  }

  public void enterTeamBE(String teamBE) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(teamBEField)).sendKeys(teamBE);

    By beOptionLocator = By.xpath(String.format("//div[contains(@class, 'cursor-pointer')]//div[text()='%s']", teamBE));
    WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(beOptionLocator));

    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", optionElement);
      wait.until(ExpectedConditions.elementToBeClickable(optionElement));
      Thread.sleep(300); // Small delay for UI stabilization
      optionElement.click();
    } catch (ElementClickInterceptedException e) {
      System.out.println("Standard click failed for BE option '" + teamBE + "', trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (TimeoutException toe) {
      System.err.println("Timeout waiting for BE option '" + teamBE + "' to be clickable. Trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted while selecting BE: " + teamBE);
      throw new RuntimeException("Interrupted during BE selection", e);
    }
  }

  public void enterTeamUI(String teamUI) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(teamUIField)).sendKeys(teamUI);

    By uiOptionLocator = By.xpath(String.format("//div[contains(@class, 'cursor-pointer')]//div[text()='%s']", teamUI));
    WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(uiOptionLocator));

    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", optionElement);
      wait.until(ExpectedConditions.elementToBeClickable(optionElement));
      Thread.sleep(300); // Small delay for UI stabilization
      optionElement.click();
    } catch (ElementClickInterceptedException e) {
      System.out.println("Standard click failed for UI option '" + teamUI + "', trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (TimeoutException toe) {
      System.err.println("Timeout waiting for UI option '" + teamUI + "' to be clickable. Trying JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted while selecting UI: " + teamUI);
      throw new RuntimeException("Interrupted during UI selection", e);
    }
  }

  public void clickAddTeamButton() {
    driver.findElement(addTeamButton).click();
  }

  public void clickConfirmModalButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement confirmModalButton = wait.until(
      ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[@id='confirmModal']//button[@type='submit']")
      )
    );
    confirmModalButton.click();
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
