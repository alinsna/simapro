package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AddStakeHolderPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestContext;

public class ManageStakeholderSteps {

    private final TestContext context;
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final AddStakeHolderPage addStakeholderPage;

    public ManageStakeholderSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.addStakeholderPage = new AddStakeHolderPage(driver);
    }

    // CATATAN: Langkah ini sama dengan di AddProjectSteps, Cucumber akan menemukan dan menggunakannya kembali.
    @Given("User is logged in with valid credentials")
    public void userIsLoggedInWithValidCredentials() {
        if (!homePage.isOnDashboard()) { // Hanya login jika belum di dashboard
            driver.get("https://simapro.fahrulhehehe.my.id/login");
            loginPage.enterEmail("syafiq.abdillah@ugm.ac.id");
            loginPage.enterPassword("adminpassword");
            loginPage.clickLogin();
            Assert.assertTrue("Login failed, not on dashboard.", homePage.isOnDashboard());
            context.getTest().log(Status.INFO, "User successfully logged in.");
        }
    }

    @Given("User is on the stakeholder list page")
    public void userIsOnTheStakeholderListPage() {
        driver.get("https://simapro.fahrulhehehe.my.id/stakeholder");
        context.getTest().log(Status.INFO, "Navigated to Stakeholder List page.");
    }

    @When("User clicks the {string} button")
    public void userClicksTheButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Stakeholder")) {
            homePage.clickAddStakeholderButton(); // Asumsi tombol ini ada di HomePage atau halaman stakeholder
            context.getTest().log(Status.INFO, "Clicked 'Add Stakeholder' button.");
        }
    }

    @Then("User should be redirected to the add stakeholder page")
    public void userShouldBeRedirectedToAddStakeholderPage() {
        Assert.assertTrue("Not redirected to Add Stakeholder page.", addStakeholderPage.isOnAddStakeholderPage());
        context.getTest().log(Status.PASS, "Successfully redirected to Add Stakeholder page.");
    }

    @When("User fills the stakeholder form with unique valid data")
    public void userFillsTheStakeholderFormWithUniqueValidData() {
        // Membuat nama dan email unik untuk setiap eksekusi tes
        String uniqueName = "Stakeholder " + System.currentTimeMillis();
        String uniqueEmail = "stakeholder" + System.currentTimeMillis() + "@test.com";

        // Simpan nama unik ke context untuk digunakan di langkah selanjutnya
        context.set("newStakeholderName", uniqueName);

        addStakeholderPage.enterName(uniqueName);
        addStakeholderPage.selectType("Internal");
        addStakeholderPage.enterContact("08123456789");
        addStakeholderPage.enterStakeholderEmail(uniqueEmail);
        context.getTest().log(Status.INFO, "Filled stakeholder form with unique name: " + uniqueName);
    }

    @When("User clicks the submit button")
    public void userClicksTheSubmitButton() {
        addStakeholderPage.clickSubmitButton();
        context.getTest().log(Status.INFO, "Clicked the submit button.");
    }

    @Then("User should be redirected back to the stakeholder list page")
    public void userShouldBeRedirectedBackToTheStakeholderListPage() {
        // Verifikasi bisa berdasarkan URL atau elemen khas di halaman list
        Assert.assertTrue("URL is not for the stakeholder list page.", driver.getCurrentUrl().contains("/stakeholder"));
        context.getTest().log(Status.PASS, "Successfully redirected back to stakeholder list.");
    }

    @Then("The new stakeholder should appear in the list")
    public void theNewStakeholderShouldAppearInTheList() {
        // Ambil nama yang disimpan dari context
        String stakeholderName = (String) context.get("newStakeholderName");
        boolean isVisible = addStakeholderPage.isStakeholderVisibleInList(stakeholderName);
        Assert.assertTrue("Newly added stakeholder '" + stakeholderName + "' is not visible in the list.", isVisible);
        context.getTest().log(Status.PASS, "Verified that new stakeholder '" + stakeholderName + "' is in the list.");
    }

    @When("User searches for the newly added stakeholder")
    public void userSearchesForTheNewlyAddedStakeholder() {
        String stakeholderName = (String) context.get("newStakeholderName");
        Assert.assertNotNull("Could not find stakeholder name from context to search for.", stakeholderName);
        addStakeholderPage.searchFor(stakeholderName);
        context.getTest().log(Status.INFO, "Searched for stakeholder: " + stakeholderName);
    }

    @Then("Only the searched stakeholder should be displayed in the results")
    public void onlyTheSearchedStakeholderShouldBeDisplayedInTheResults() {
        // Memastikan hanya ada satu baris hasil yang tampil
        int rowCount = addStakeholderPage.getVisibleRowCount();
        Assert.assertEquals("Search result should display exactly 1 row, but found " + rowCount + ".", 1, rowCount);
        context.getTest().log(Status.PASS, "Verified that search result contains exactly one record.");
    }
}