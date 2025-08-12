import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests {
    ChromeDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void shouldAuthorize() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.title));

        loginPage
                .enterValidEmail()
                .enterValidPassword()
                .clickOnLoginButton();

        Thread.sleep(3000);

        WebElement name = driver.findElement(By.xpath("//h1[contains(@class, 'font-thin')]"));

        assertTrue(name.isDisplayed());
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

    @AfterEach
    void quitBrowser() {
        driver.quit();
    }
}