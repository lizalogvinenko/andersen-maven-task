import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {
    ChromeDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    RegisterPage registerPage;
    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);

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
        logger.info("Test passed. Page title is correct.");
        logger.error("Test failed. Page title doesn't correct.");
    }

    @Test
    public void shouldCheckFieldsPlaceholders() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));
        assertEquals(loginPage.emailField().getAttribute("placeholder"), AuthData.EMAIL_PLACEHOLDER);
        logger.info("Test passed. Email field placeholder is correct.");
        logger.error("Test failed. Email field placeholder doesn't correct.");

        assertTrue(loginPage.passwordField().isDisplayed());
        assertEquals(loginPage.passwordField().getAttribute("placeholder"), AuthData.PASSWORD_PLACEHOLDER);
        logger.info("Test passed. Password field placeholder is correct.");
        logger.error("Test failed. Password field placeholder doesn't correct.");
    }

    @Test
    public void shouldNotAcceptNotEmailFormat() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterNotEmailFormat()
                .clickOnPasswordField();
        logger.info("Entered not email format.");

        assertTrue(loginPage.errorMessageNotValidEmail().isDisplayed());
        logger.info("Test passed. Email field doesn't accept invalid format.");
        logger.error("Test failed. Email field accepts invalid format.");
    }

    @Test
    public void shouldNotAcceptNotValidEmail() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterInvalidEmail()
                .enterValidPassword()
                .clickOnLoginButton();
        logger.info("Entered wrong email, correct password, clicked on Sign in button.");

        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.errorMessageNotValidCredentials));
        assertTrue(loginPage.errorMessageNotValidCredentials().isDisplayed());
        logger.info("Test passed. Email field doesn't wrong email.");
        logger.error("Test failed. Email field accepts wrong email.");
    }

    @Test
    public void shouldNotAcceptNotValidPassword() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.emailField));

        loginPage
                .enterValidEmail()
                .enterInvalidPassword()
                .clickOnLoginButton();
        logger.info("Entered correct email, wrong password, clicked on Sign in button.");

        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.errorMessageNotValidCredentials));
        assertTrue(loginPage.errorMessageNotValidCredentials().isDisplayed());
        logger.info("Test passed. Password field doesn't wrong email.");
        logger.error("Test failed. Password field accepts wrong email.");
    }

    @Test
    public void shouldCheckIfRegisterLinkIsAnable() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.registerLink));

        loginPage.clickOnRegistrationLink();
        logger.info("Clicked on registration link.");

        assertFalse(loginPage.title().getText().contains("Sign in"));
        logger.info("Test passed. Registration link is available.");
        logger.error("Test failed. Registration link doesn't available.");
    }

    @Test
    public void shouldLeadToRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.registerLink));

        loginPage.clickOnRegistrationLink();
        logger.info("Clicked on registration link.");

        assertTrue(registerPage.title().isDisplayed());
        assertTrue(registerPage.title().getText().contains("Registration"));
        logger.info("Test passed. Registration link leads to Registration page.");
        logger.error("Test failed. Registration link doesn't lead to Registration page.");
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
        logger.info("Test passed. Sign in button activates after both fields are filled.");
        logger.error("Test failed. Sign in button activates before both fields are filled.");
    }

    @Test
    public void shouldNotAcceptPasswordLess8Char() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        loginPage
                .enterShortPassword()
                .clickOnEmailField();
        logger.info("Entered less then 8 char password.");

        assertTrue(loginPage.errorMessageShortPassword().isDisplayed());
        logger.info("Test passed. Password field doesn't accept less than 8 characters.");
        logger.error("Test failed. Password field accepts less than 8 characters.");
    }

    @Test
    public void shouldNotAcceptPasswordMore21Char() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        loginPage
                .enterLongPassword()
                .clickOnEmailField();
        logger.info("Entered more then 21 char password.");

        assertTrue(loginPage.errorMessageLongPassword().isDisplayed());
        logger.info("Test passed. Password field doesn't accept more than 21 characters.");
        logger.error("Test failed. Password field accepts more than 21 characters.");
    }

    @AfterEach
    void tearDown() {
        logger.info("This is an informational message.");
        logger.error("This is an error message.");

        driver.quit();
    }
}
