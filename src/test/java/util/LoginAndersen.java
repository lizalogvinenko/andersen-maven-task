package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAndersen {

    public static void shouldLoginInAndersen(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://qa-course-01.andersenlab.com/");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));

        assertTrue(email.isDisplayed());
        email.sendKeys("lili@example.com");

        assertTrue(password.isDisplayed());
        password.sendKeys("12345678");

        loginButton.click();
    }
}
