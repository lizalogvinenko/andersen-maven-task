package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import src.test.authData.Data;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginInAndersen {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://qa-course-01.andersenlab.com/");
    }

    @Test
    void passwordFieldShouldAcceptValueFrom5Char() {
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));

        assertTrue(email.isDisplayed());
        email.sendKeys(Data.getEmail());

        assertTrue(password.isDisplayed());
        password.sendKeys(Data.getPassword());

        loginButton.click();

        WebElement name = driver.findElement(By.xpath("//h1[contains(@class, 'font-thin')]"));

        assertTrue(name.getText().contains(Data.getName()));

        driver.quit();
    }
}
