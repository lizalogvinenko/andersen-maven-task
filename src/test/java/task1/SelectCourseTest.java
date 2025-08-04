package task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LoginAndersen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SelectCourseTest {
    ChromeDriver driver;
    WebDriverWait wait;
    SelectPage selectPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--guest");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        selectPage = new SelectPage(driver);
    }

    @Test
    public void shouldCheckIfThereIsNoCourse() {
        LoginAndersen.shouldLoginInAndersen(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".justify-center.cursor-pointer")));
        WebElement practice = driver.findElement(By.cssSelector(".justify-center.cursor-pointer"));

        new Actions(driver)
                .moveToElement(practice)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text() = 'Select']")));

        WebElement optionSelect = driver.findElement(By.xpath("//div[text() = 'Select']"));
        optionSelect.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(selectPage.pageTitle));

        assertTrue(selectPage.getPageTitle().isDisplayed());

        selectPage.getSelectCountry().click();
        Select selectCountry = new Select(selectPage.getSelectCountry());
        selectCountry.selectByVisibleText("USA");

        selectPage.getSelectLanguage().click();
        Select selectLanguage = new Select(selectPage.getSelectLanguage());
        selectLanguage.selectByVisibleText("English");

        selectPage.getSelectType().click();
        Select selectType = new Select(selectPage.getSelectType());
        selectType.selectByVisibleText("Testing");

        LocalDate today = LocalDate.now();
        int howManyDaysUntilNextMonday = (DayOfWeek.MONDAY.getValue() - today.getDayOfWeek().getValue() + 7);

        if (howManyDaysUntilNextMonday == 0) {
            howManyDaysUntilNextMonday = 7;
        }

        LocalDate nextMonday = today.plusDays(howManyDaysUntilNextMonday);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = nextMonday.format(formatter);

        selectPage.getStartDate().sendKeys(formattedStartDate);

        LocalDate twoWeeksAfterStart = nextMonday.plusWeeks(2);
        String formattedLastDate = twoWeeksAfterStart.format(formatter);

        selectPage.getEndDate().sendKeys(formattedLastDate);

        Select selectCourses = new Select(selectPage.getSelectCourse());
        selectCourses.selectByVisibleText("AQA Python");
        selectCourses.selectByVisibleText("AQA Java");

        selectPage.getSearchButton().click();

        assertTrue(selectPage.getMessage().isDisplayed());
    }

    @AfterEach
    public void quiteBrowser() {
        driver.quit();
    }
}
