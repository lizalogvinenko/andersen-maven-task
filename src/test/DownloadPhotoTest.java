package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.test.authData.Data;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadPhotoTest {
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        assertTrue(email.isDisplayed());
        email.sendKeys(Data.EMAIL);

        assertTrue(password.isDisplayed());
        password.sendKeys(Data.PASSWORD);

        loginButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[contains(@src,'upload_photo')]")));

        WebElement addPhotoButton = driver.findElement(By.xpath("//img[contains(@src,'upload_photo')]"));

        Actions actions = new Actions(driver);

        actions.moveToElement(addPhotoButton).click();

        WebElement inputImage = driver.findElement(By.xpath("//input[@type='file']"));

        File image = new File("assets/image_to_download.JPG").getAbsoluteFile();

        inputImage.sendKeys(image.toString());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//p[contains(text(), 'Your photo has been updated')])")));

        WebElement successMessage = driver.findElement(By.xpath("(//p[contains(text(), 'Your photo has been updated')])"));

        assertTrue(successMessage.isDisplayed());

        driver.quit();
    }
}
