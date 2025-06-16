package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AddProjectPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestContext;

import java.nio.file.Paths;
import java.util.Map;

public class ManageProjectSteps {

    private final TestContext context;
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final AddProjectPage addProjectPage;

    public ManageProjectSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.addProjectPage = new AddProjectPage(driver);
    }

    @Given("User scrolls down on the homepage and clicks on add new project button")
    public void userScrollsAndClicksAddNewProject() {
        homePage.clickAddNewProjectButton();
        context.getTest().log(Status.INFO, "Clicked 'Add New Project' button.");
    }

    @Then("User is redirected to the add project page")
    public void userIsRedirectedToTheAddProjectPage() {
        Assert.assertTrue("Not redirected to the Add Project page.", addProjectPage.isOnAddProjectPage());
        context.getTest().log(Status.PASS, "Successfully redirected to the add project page.");
    }

    @When("User uploads an image and enters the following project details:")
    public void userUploadsImageAndEntersProjectDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        String uniqueProjectName = data.get("Project Name") + " - " + System.currentTimeMillis();
        context.set("newProjectName", uniqueProjectName);

        addProjectPage.scrollToForm();

        String imagePath = Paths.get("src/test/resources/test_data/project_image.png").toAbsolutePath().toString();

        addProjectPage.uploadImage(imagePath);
        addProjectPage.enterProjectName(uniqueProjectName);
        addProjectPage.enterYear(data.get("Year"));
        addProjectPage.enterStakeholder(data.get("Stakeholder"));
        addProjectPage.enterGroupName(data.get("Group Name"));
        addProjectPage.enterDescription(data.get("Description"));

        context.getTest().log(Status.INFO, "Filled in all project details for project: " + uniqueProjectName);
    }

    /**
     * === INI METODE YANG HILANG/BELUM TERSIMPAN ===
     * Error terjadi karena metode untuk langkah ini belum ada di file Anda.
     * Kode di bawah ini adalah implementasi yang benar.
     */
    @When("User selects {string} from the dropdown")
    public void userSelectsAProjectFromTheDropdown(String projectType) {
        addProjectPage.selectProjectType(projectType);
        context.getTest().log(Status.INFO, "Selected '" + projectType + "' from the dropdown.");
    }

    @And("User submits the project")
    public void userSubmitsTheProject() {
        addProjectPage.submitForm();
        context.getTest().log(Status.INFO, "Submitted the project form.");
    }

    @Then("The project is added successfully")
    public void theProjectIsAddedSuccessfully() {
        String successMessage = addProjectPage.getSuccessModalMessage();

        Assert.assertNotNull("Success modal did not appear.", successMessage);
        Assert.assertTrue("Success message is incorrect. Actual: '" + successMessage + "'",
                successMessage.toLowerCase().contains("project upload success"));
        context.getTest().log(Status.PASS, "Verified that project was added successfully. Message: " + successMessage);
        addProjectPage.closeSuccessModal();
    }

    @When("User leaves all project detail fields empty")
    public void userLeavesAllProjectDetailFieldsEmpty() {
        // Tidak melakukan apa-apa adalah aksi yang diinginkan.
        // Cukup scroll untuk memastikan tombol submit terlihat.
        addProjectPage.scrollToForm();
        context.getTest().log(Status.INFO, "Leaving all fields empty to test validation.");
    }

    @Then("a {string} message should be displayed in the modal")
    public void aMessageShouldBeDisplayedInTheModal(String expectedMessage) {
        String actualMessage = addProjectPage.getFailedModalMessage();

        Assert.assertTrue(
                "Pesan error modal tidak sesuai. Expected to contain: '" + expectedMessage + "', Actual: '" + actualMessage + "'",
                actualMessage.toLowerCase().contains(expectedMessage.toLowerCase())
        );

        context.getTest().log(Status.PASS, "Validation message appeared as expected: " + actualMessage);

        // Tutup modal setelah verifikasi agar tidak mengganggu tes selanjutnya
        addProjectPage.closeFailedModal();
    }
}