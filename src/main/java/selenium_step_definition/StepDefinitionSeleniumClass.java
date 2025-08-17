package selenium_step_definition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class StepDefinitionSeleniumClass {
    ChromeDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @Given("Open browser")
    public void set_up_driver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("Opening Login Page")
    public void opening_login_page() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.manage().window().maximize();
    }

    @And("Set valid email")
    public void set_valid_email() {
        loginPage.enterValidEmail();
    }

    @And("Set valid Password")
    public void set_valid_password() {
        loginPage.enterValidPassword();
    }

    @And("Set invalid Password")
    public void setInvalidPassword() {
        loginPage.enterInvalidPassword();
    }

    @And("Click on Sign in button")
    public void click_on_sign_in_button() {
        loginPage.clickOnLoginButton();
    }

    @And("Check activation status without valid input")
    public void check_activation_status_without_valid_input() {
        loginPage
                .clickOnEmailField()
                .clickOnPasswordField();

        assertFalse(loginPage.loginButton().isEnabled());
    }

    @Then("Check activation status")
    public void checkActivationStatus() {
        assertTrue(loginPage.loginButton().isEnabled());
    }

    @Then("Check name on Profile Page")
    public void check_name_on_profile_page() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'font-thin')]")));
        WebElement name = driver.findElement(By.xpath("//h1[contains(@class, 'font-thin')]"));
        assertTrue(name.isDisplayed());
    }

    @Then("Check if not valid password error message appears")
    public void checkIfNotValidPasswordErrorMessageAppears() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.errorMessageNotValidCredentials));
        assertTrue(loginPage.errorMessageNotValidCredentials().isDisplayed());
    }

    @Then("Close browser")
    public void quit_browser() {
        driver.quit();
    }
}
