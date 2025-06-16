package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

//    Locators
    private By dashboardHeader = By.xpath("//a[contains(., 'Sistem Informasi Manajemen Proyek')]");
    private By searchBar = By.xpath("//label[span[text()='Search']]//input");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public boolean isOnDashboard() {
        return driver.findElement(dashboardHeader).isDisplayed();
    }

    public void searchProject(String projectName) {
        driver.findElement(searchBar).sendKeys(projectName);
    }

    public void clickSearchButton() {
        driver.findElement(searchBar).submit();
    }

    public String isOnResultPage() {
        return driver.getCurrentUrl();
    }

    public boolean isProjectDisplayed(String projectName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., '"+ projectName +"')]")));

            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
