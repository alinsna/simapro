package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import pages.AddMahasiswaPage;
import pages.HomePage;
import utils.TestContext;

import java.nio.file.Paths;

public class AddMahasiswaSteps {

    private final TestContext context;
    private final HomePage homePage;
    private final AddMahasiswaPage addPage;

    public AddMahasiswaSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context.getDriver());
        this.addPage = new AddMahasiswaPage(context.getDriver());
    }

    @When("User scrolls down and clicks Add Profil Mahasiswa button")
    public void userClicksAddProfilMahasiswa() {
        JavascriptExecutor js = (JavascriptExecutor) context.getDriver();
        js.executeScript("window.scrollBy(0,300)");
        homePage.clickAddMahasiswaButton();
    }

    @Then("User should be redirected to the add mahasiswa page")
    public void userShouldBeRedirected() {
        Assert.assertTrue(addPage.isOnAddMahasiswaPage());
    }

    @When("User fills in valid nama and nim and uploads photo")
    public void userFillsForm() {
        addPage.enterName("Alin Septiani Nur Aisyah");
        addPage.enterNIM("23/520895/SV/23320");
        String imagePath = Paths.get("src/test/resources/test_data/fotoku.jpeg").toAbsolutePath().toString();
        System.out.println("Uploading image from: " + imagePath);
        addPage.uploadPhoto(imagePath);
    }

    @When("User leaves the mahasiswa form empty")
    public void userLeavesFormEmpty() {
        // intentionally left blank
    }

    @When("User fills in nama and nim without photo")
    public void userFillsNamaAndNimWithoutPhoto() {
        addPage.enterName("Testing Nama Sembarangan");
        addPage.enterNIM("99/999999/SV/99999");
        // Tidak upload foto
    }

    @And("User clicks submit button on add mahasiswa page for positive case")
    public void userClicksSubmitPositive() {
        addPage.clickSubmit();
    }

    @And("User clicks submit button on add mahasiswa page for negative case")
    public void userClicksSubmitNegative() {
        addPage.clickSubmit();
    }

    @Then("A {string} error message should appear")
    public void errorMessageShouldAppear(String expectedMessage) {
        String actualMessage = addPage.getFailedMessage();

        Assert.assertTrue(
                "Pesan error modal tidak sesuai. Expected to contain: '" + expectedMessage + "', Actual: '" + actualMessage + "'",
                actualMessage.toLowerCase().contains(expectedMessage.toLowerCase())
        );

        context.getTest().log(Status.PASS, "Validation error message appeared as expected: " + actualMessage);

        // Tutup modal setelah verifikasi
        addPage.closeFailedModal();
    }

    @Then("User should be redirected to the mahasiswa list page")
    public void userShouldBeRedirectedToMahasiswaListPage() {
        Assert.assertTrue(context.getDriver().getCurrentUrl().contains("/mahasiswa"));
    }
}
