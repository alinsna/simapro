package stepdefinitions;

import pages.LoginPage;
import pages.AddProjectPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AddProjectSteps {

    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);
    AddProjectPage addProjectPage = new AddProjectPage(driver);

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

    @When("User scrolls down on the homepage and clicks on add new project button")
    public void userScrollsDownAndClicksAddNewProjectButton() {
        driver.get("https://simapro.fahrulhehehe.my.id/home");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down to reveal the button
        js.executeScript("window.scrollBy(0, 250)", "");

        // Use WebDriverWait to wait until the "Add New Project" button is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Selenium 4.x
        WebElement addNewProjectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add New Project')]")));

        // Click the add project button
        addNewProjectButton.click();
    }

    @Then("User is redirected to the add project page")
    public void userIsRedirectedToAddProjectPage() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("https://simapro.fahrulhehehe.my.id/home/project/add-project");
    }

    @When("User uploads an image and enters project details")
    public void userUploadsImageAndEntersProjectDetails() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 60; i++) {js.executeScript("window.scrollBy(0, 500)", "");}
        // Wait for the elements to be visible and clickable before interacting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Upload image 1
        WebElement inputImage1 = driver.findElement(By.cssSelector("input#image-upload-1")); // Find input element
        inputImage1.sendKeys("C:/Users/fiqab/Downloads/web.jpeg"); // Set path to the image file

        // Upload image 2
        WebElement inputImage2 = driver.findElement(By.cssSelector("input#image-upload-2"));
        inputImage2.sendKeys("C:/Users/fiqab/Downloads/web.jpeg");

        // Upload image 3
        WebElement inputImage3 = driver.findElement(By.cssSelector("input#image-upload-3"));
        inputImage3.sendKeys("C:/Users/fiqab/Downloads/web.jpeg");

        // Upload image 4
        WebElement inputImage4 = driver.findElement(By.cssSelector("input#image-upload-4"));
        inputImage4.sendKeys("C:/Users/fiqab/Downloads/web.jpeg");

        // Upload image 5
        WebElement inputImage5 = driver.findElement(By.cssSelector("input#image-upload-5"));
        inputImage5.sendKeys("C:/Users/fiqab/Downloads/web.jpeg");

        // Enter project name
        WebElement projectNameField = driver.findElement(By.id("project-name"));
        projectNameField.clear();
        projectNameField.sendKeys("Project ABC");

        // Enter year
        WebElement yearField = driver.findElement(By.id("year"));
        yearField.clear();
        yearField.sendKeys("2022");

        // Enter stakeholder
        WebElement stakeholderField = driver.findElement(By.id("stakeholder"));
        stakeholderField.clear();
        stakeholderField.sendKeys("Stakeholder Name");

        // Enter group name
        WebElement groupNameField = driver.findElement(By.id("group-name"));
        groupNameField.clear();
        groupNameField.sendKeys("Group Name");

        // Enter description
        WebElement descriptionField = driver.findElement(By.id("description"));
        descriptionField.clear();
        descriptionField.sendKeys("This is a test description.");
    }

    @When("User selects a project from the dropdown")
    public void userSelectsProject() {
        Actions actions = new Actions(driver);

        // Mengirimkan tombol Page Down berulang untuk menggulirkan halaman
        for (int i = 0; i < 2; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).perform();
        }

        // Wait for the "Select Project" button to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectProjectButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("headlessui-menu-button-:Rf4kvfff9tkq:")));
        selectProjectButton1.click();

        // Wait for the dropdown items to be visible and clickable
        WebElement projectOption1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("headlessui-menu-item-:r0:")));
        projectOption1.click();  // Click "Projek Aplikasi Dasar 1"

        // Alternatively, you can select "Projek Aplikasi Dasar 2" if you need to test the other option
        // WebElement projectOption2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("headlessui-menu-item-:r1:")));
        // projectOption2.click();
    }

    @And("User submits the project")
    public void userSubmitsTheProject() {

        // Menunggu dan menekan tombol 'Submit'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-modal-toggle='defaultModal']")));
        submitButton1.click(); // Klik tombol Submit

        // Menunggu tombol 'Yes, I'm sure' untuk muncul dan menekan tombol tersebut
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-modal-hide='defaultModal']")));
        yesButton.click(); // Klik tombol "Yes, I'm sure"
    }

    @Then("The project is added successfully")
    public void theProjectIsAddedSuccessfully() {
        // Verify success message or project list update here
    }

}
