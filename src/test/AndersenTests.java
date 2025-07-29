package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AndersenTests {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://qa-course-01.andersenlab.com/");
    }

    @Test
    void passwordFieldShouldAcceptValueFrom5Char() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));

        email.sendKeys("liza@example.com");

        assertTrue(password.isDisplayed());
        password.sendKeys("1234");

        assertFalse(loginButton.isEnabled());

        driver.quit();
    }

    @Test
    void emailFieldShouldAcceptEmailFormatOnly() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));

        email.sendKeys("@example.com");
        assertTrue(password.isDisplayed());
        password.click();

        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), 'Invalid email address')]\n"));

        assertTrue(errorMessage.isDisplayed());

        driver.quit();
    }
}
