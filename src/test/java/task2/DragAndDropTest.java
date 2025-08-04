package task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LoginAndersen;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DragAndDropTest {
    ChromeDriver driver;
    WebDriverWait wait;
    DragAndDropPage dragAndDropPage;
    Actions actions;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--guest");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        dragAndDropPage = new DragAndDropPage(driver);
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void shouldMoveElementToTheirPlace() {
        LoginAndersen.shouldLoginInAndersen(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".justify-center.cursor-pointer")));
        WebElement practice = driver.findElement(By.cssSelector(".justify-center.cursor-pointer"));

        new Actions(driver)
                .moveToElement(practice)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Drag & Drop']")));

        WebElement optionSelect = driver.findElement(By.xpath("//div[text()='Drag & Drop']"));
        optionSelect.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dragAndDropPage.pageTitle));

        actions.pause(Duration.ofSeconds(2))
                .moveToElement(dragAndDropPage.getFirstElement())
                .clickAndHold()
                .moveToElement(dragAndDropPage.getPlaceManualWork1())
                .release()
                .build().perform();

        actions.pause(Duration.ofSeconds(2))
                .moveToElement(dragAndDropPage.getSecondElement())
                .clickAndHold()
                .moveToElement(dragAndDropPage.getPlaceManualWork2())
                .release()
                .build().perform();

        actions.pause(Duration.ofSeconds(2))
                .moveToElement(dragAndDropPage.getThirdElement())
                .clickAndHold()
                .moveToElement(dragAndDropPage.getPlaceAutomationWork1())
                .release()
                .build().perform();

        actions.pause(Duration.ofSeconds(2))
                .moveToElement(dragAndDropPage.getForthElement())
                .clickAndHold()
                .moveToElement(dragAndDropPage.getPlaceAutomationWork2())
                .release()
                .pause(Duration.ofSeconds(2))
                .build().perform();

        assertTrue(dragAndDropPage.getMessage().isDisplayed());
    }

    @AfterEach
    public void quiteBrowser() {
        driver.quit();
    }
}
