package android_tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AndroidPage {
    AndroidDriver driver;
    WebDriverWait wait;

    public AndroidPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    static LocalDate tomorrow = LocalDate.now().plusDays(1);
    static String tomorrowDate = DateTimeFormatter.ofPattern("d MMMM yyyy")
            .withLocale(Locale.ENGLISH)
            .format(tomorrow);

    private static final class Locators {
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By dateWidgets = AppiumBy.accessibilityId("Date Widgets");
        private static final By dialog = AppiumBy.accessibilityId("1. Dialog");
        private static final By changeTheDate = AppiumBy.id("io.appium.android.apis:id/pickDate");
        private static final By changeTheTime = AppiumBy.id("io.appium.android.apis:id/pickTimeSpinner");
        private static final By tomorrowDatePick = AppiumBy.accessibilityId(tomorrowDate);
        private static final By confirmDate = AppiumBy.id("android:id/button1");
        private static final By confirmTime = AppiumBy.id("android:id/button1");
        private static final By textSwitcher = AppiumBy.accessibilityId("TextSwitcher");
        private static final By nextButton = AppiumBy.id("io.appium.android.apis:id/next");
    }


    public void clickOnViews() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
    }

    public void clickOnDataWidget() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dateWidgets)).click();
    }

    public void clickOnDialog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dialog)).click();
    }

    public void clickOnChangeTheDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTheDate)).click();
    }

    public void clickOnChangeTheTime() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTheTime)).click();
    }

    public void clickOnTomorrowDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.tomorrowDatePick)).click();
    }

    public void clickOnConfirmDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.confirmDate)).click();
    }

    public void clickOnConfirmTime() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.confirmTime)).click();
    }

    public void clickOnTextSwitcher() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.textSwitcher)).click();
    }

    public void clickOnNextButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.nextButton)).click();
    }

    public String getNumber() {
        List<WebElement> numbers = driver.findElements(By.xpath("//android.widget.TextView"));
        WebElement text = numbers.get(1);
        return text.getText();
    }

    public String getChangedNumber() {
        List<WebElement> numbers = driver.findElements(By.xpath("//android.widget.TextView"));
        WebElement text = numbers.get(1);
        return text.getText();
    }
}
