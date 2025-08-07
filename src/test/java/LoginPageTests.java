import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;
import util.AuthData;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTests {
    ChromeDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    RegisterPage registerPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void shouldCheckPageTitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        assertEquals(loginPage.title().getText(), "Sign In");
    }

    @Test
    public void shouldCheckFieldsPlaceholders() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));
        assertEquals(loginPage.emailField().getAttribute("placeholder"), AuthData.EMAIL_PLACEHOLDER);

        assertTrue(loginPage.passwordField().isDisplayed());
        assertEquals(loginPage.passwordField().getAttribute("placeholder"), AuthData.PASSWORD_PLACEHOLDER);
    }

    @Test
    public void shouldNotAcceptNotEmailFormat() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterNotEmailFormat()
                .clickOnPasswordField();

        assertTrue(loginPage.errorMessageNotValidEmail().isDisplayed());
    }

    @Test
    public void shouldNotAcceptNotValidEmail() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterInvalidEmail()
                .enterValidPassword()
                .clickOnLoginButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.errorMessageNotValidCredentials));
        assertTrue(loginPage.errorMessageNotValidCredentials().isDisplayed());
    }

    @Test
    public void shouldNotAcceptNotValidPassword() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterValidEmail()
                .enterInvalidPassword()
                .clickOnLoginButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.errorMessageNotValidCredentials));
        assertTrue(loginPage.errorMessageNotValidCredentials().isDisplayed());
    }

    @Test
    public void shouldCheckIfRegisterLinkIsAnable() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.registerLink));

        loginPage.clickOnRegistrationLink();

        assertFalse(loginPage.title().getText().contains("Sign in"));
    }

    @Test
    public void shouldLeadToRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.registerLink));

        loginPage.clickOnRegistrationLink();

        assertTrue(registerPage.title().isDisplayed());
        assertTrue(registerPage.title().getText().contains("Registration"));
    }

    @Test
    public void shouldCheckWhenSignInButtonActivates() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.emailField));

        loginPage
                .clickOnEmailField()
                .clickOnPasswordField();

        assertFalse(loginPage.loginButton().isEnabled());

        loginPage.enterValidEmail();

        assertFalse(loginPage.loginButton().isEnabled());

        loginPage.enterValidPassword();

        assertTrue(loginPage.loginButton().isEnabled());
    }

    @Test
    public void shouldNotAcceptPasswordLess8Char() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        loginPage
                .enterShortPassword()
                .clickOnEmailField();

        assertTrue(loginPage.errorMessageShortPassword().isDisplayed());
    }

    @Test
    public void shouldNotAcceptPasswordMore21Char() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        loginPage
                .enterLongPassword()
                .clickOnEmailField();

        assertTrue(loginPage.errorMessageLongPassword().isDisplayed());
    }

    @AfterEach
    void quiteBrowser() {
        driver.quit();
    }
}
