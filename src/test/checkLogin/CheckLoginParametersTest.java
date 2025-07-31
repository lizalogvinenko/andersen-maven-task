package src.test.checkLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import src.test.data.AuthData;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckLoginParametersTest {

    WebDriver driver;
    String emailValue;
    String passwordValue;

    @BeforeClass
    @Parameters({"user"})
    public void varInit(String user) {
        switch (user) {
            case "firstUser" -> {
                emailValue = AuthData.EMAIL_1;
                passwordValue = AuthData.PASSWORD_1;
            }
            case "secondUser" -> {
                emailValue = AuthData.EMAIL_2;
                passwordValue = AuthData.PASSWORD_2;
            }
            case "thirdUser" -> {
                emailValue = AuthData.EMAIL_3;
                passwordValue = AuthData.PASSWORD_3;
            }
        }
    }

    @Test
    public void checkingLogin() {
        driver = new ChromeDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));

        assertTrue(email.isDisplayed());
        email.sendKeys(emailValue);

        assertTrue(password.isDisplayed());
        password.sendKeys(passwordValue);

        loginButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.font-thin")));

        WebElement name = driver.findElement(By.cssSelector("h1.font-thin"));
        assertTrue(name.isDisplayed());

        driver.quit();
    }
}
