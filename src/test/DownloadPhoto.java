package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import src.test.authData.Data;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadPhoto {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-cache");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://qa-course-01.andersenlab.com/");
    }

    @Test
    void passwordFieldShouldAcceptValueFrom5Char() {
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));

        email.sendKeys(Data.getEmail());
        password.sendKeys(Data.getPassword());
        loginButton.click();

        System.out.println(driver.getPageSource());

        WebElement addPhotoButton = driver.findElement(By.xpath("//div[contains(@class, 'relative') and contains(@class, 'group')]//div[contains(@class, 'absolute') and contains(@class, 'group-hover:opacity-100')]"));

        addPhotoButton.click();
        addPhotoButton.sendKeys("C:\\Users\\Елзавета\\Desktop\\Владивосток\\DSC_6873.JPG");

        WebElement successMessage = driver.findElement(By.xpath("(//p[contains(text(), 'Your photo has been updated')])"));

        assertTrue(successMessage.isDisplayed());

        driver.quit();
    }
}
