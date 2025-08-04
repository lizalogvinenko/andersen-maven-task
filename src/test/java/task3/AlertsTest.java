package task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LoginAndersen;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertsTest {
    ChromeDriver driver;
    WebDriverWait wait;
    ActionPage actionPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--guest");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actionPage = new ActionPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void shouldCheckAlerts() {
        LoginAndersen.shouldLoginInAndersen(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".justify-center.cursor-pointer")));
        WebElement practice = driver.findElement(By.cssSelector(".justify-center.cursor-pointer"));

        new Actions(driver)
                .moveToElement(practice)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text() = 'Actions, Alerts & Iframes']")));

        WebElement optionSelect = driver.findElement(By.xpath("//div[text() = 'Actions, Alerts & Iframes']"));
        optionSelect.click();

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[title='Finish your registration']")));
        driver.switchTo().frame(iframe);

        actionPage.getConfirmButton().click();

        Alert alert1 = driver.switchTo().alert();

        assertEquals(alert1.getText(), "You have called alert!");

        alert1.accept();

        assertTrue(actionPage.getMessage1().isDisplayed());

        new Actions(driver)
                .doubleClick(actionPage.getDiscountButton())
                .perform();

        Alert alert2 = driver.switchTo().alert();

        assertEquals(alert2.getText(), "Are you sure you want to apply the discount?");

        alert2.accept();

        assertTrue(actionPage.getMessage2().isDisplayed());

        new Actions(driver)
                .contextClick(actionPage.getCancelCourse())
                .perform();

        Alert alert3 = driver.switchTo().alert();

        assertEquals(alert3.getText(), "Here you may describe a reason why you are cancelling your registration (or leave this field empty).");

        String myText = "My text";

        alert3.sendKeys(myText);

        alert3.accept();

        assertTrue(actionPage.getMessage3().getText().contains(myText));
    }

    @AfterEach
    public void quiteBrowser() {
        driver.quit();
    }
}
