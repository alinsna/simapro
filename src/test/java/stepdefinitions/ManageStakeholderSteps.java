package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.AddStakeholderPage;
import pages.HomePage;
import pages.StakeholderListPage;
import utils.TestContext;

import java.nio.file.Paths;
import java.util.Map;

public class ManageStakeholderSteps {

    private final TestContext context;
    private final HomePage homePage;
    private final AddStakeholderPage addStakeholderPage;
    private final StakeholderListPage stakeholderListPage;

    public ManageStakeholderSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context.getDriver());
        this.addStakeholderPage = new AddStakeholderPage(context.getDriver());
        this.stakeholderListPage = new StakeholderListPage(context.getDriver());
    }

    @When("User clicks on the {string} button on the homepage")
    public void userClicksOnTheButtonOnTheHomepage(String buttonText) {
        if (buttonText.equalsIgnoreCase("Add Profil Stakeholder")) {
            homePage.clickAddStakeholderButton();
        }
        context.getTest().log(Status.INFO, "Clicked '" + buttonText + "' button.");
    }

    @Then("User should be redirected to the add stakeholder page")
    public void userShouldBeRedirectedToAddStakeholderPage() {
        Assert.assertTrue("Not redirected to Add Stakeholder page.", addStakeholderPage.isOnAddStakeholderPage());
        context.getTest().log(Status.PASS, "Successfully redirected to Add Stakeholder page.");
    }

    @When("User uploads a profile image and enters the following stakeholder details:")
    public void userUploadsAProfileImageAndEntersTheFollowingStakeholderDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        String uniqueName = data.get("Name") + " " + System.currentTimeMillis();
        context.set("newStakeholderName", uniqueName);
        String imagePath = Paths.get("src/test/resources/test_data/stakeholder_photo.png").toAbsolutePath().toString();
        addStakeholderPage.uploadProfileImage(imagePath);
        addStakeholderPage.enterName(uniqueName);
        addStakeholderPage.enterEmail(data.get("Email"));
        addStakeholderPage.enterContact(data.get("Number"));
        context.getTest().log(Status.INFO, "Filled stakeholder form with unique name: " + uniqueName);
    }

    @And("User selects {string} as the category")
    public void userSelectsAsTheCategory(String category) {
        addStakeholderPage.selectCategory(category);
        context.getTest().log(Status.INFO, "Selected category: " + category);
    }

    @And("User clicks the submit stakeholder button")
    public void userClicksTheSubmitStakeholderButton() {
        addStakeholderPage.clickSubmitButton();
        context.getTest().log(Status.INFO, "Clicked the submit button.");
    }

    @Then("User should be redirected to the stakeholder list page")
    public void userShouldBeRedirectedToTheStakeholderListPage() {
        Assert.assertTrue("Not redirected to the stakeholder list page.", stakeholderListPage.isOnStakeholderListPage());
        context.getTest().log(Status.PASS, "Successfully redirected to stakeholder list page.");
    }

    @And("The new stakeholder should appear in the list")
    public void theNewStakeholderShouldAppearInTheList() {
        String stakeholderName = (String) context.get("newStakeholderName");
        boolean isVisible = stakeholderListPage.isStakeholderVisibleInList(stakeholderName);
        Assert.assertTrue("Newly added stakeholder '" + stakeholderName + "' is not visible in the list.", isVisible);
        context.getTest().log(Status.PASS, "Verified that new stakeholder '" + stakeholderName + "' appears in the list.");
    }

    @When("User leaves the stakeholder form empty")
    public void userLeavesTheStakeholderFormEmpty() {
        // Tidak ada aksi yang dilakukan, ini adalah langkah yang disengaja.
        context.getTest().log(Status.INFO, "Leaving form empty as per the test case.");
    }

    @Then("a {string} message should be displayed")
    public void aMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = addStakeholderPage.getFailedModalMessage();

        Assert.assertTrue(
                "Pesan error modal tidak sesuai. Expected to contain: '" + expectedMessage + "', Actual: '" + actualMessage + "'",
                actualMessage.toLowerCase().contains(expectedMessage.toLowerCase())
        );

        context.getTest().log(Status.PASS, "Validation message appeared as expected: " + actualMessage);

        addStakeholderPage.closeFailedModal();
    }
}