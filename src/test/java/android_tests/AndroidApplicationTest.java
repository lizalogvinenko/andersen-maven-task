package android_tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.*;

public class AndroidApplicationTest {
    private AndroidDriver driver;
    private AndroidPage androidPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = new AppiumDriverInit().getDriver();
        androidPage = new AndroidPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void checkElementAmount() {
        androidPage.clickOnViews();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/list")));

        WebElement listView = driver.findElement(By.id("android:id/list"));

        Set<String> uniqueTexts = new LinkedHashSet<>();

        List<WebElement> items1 = listView.findElements(By.xpath(".//android.widget.ListView/*"));
        for (WebElement row : items1) {
            uniqueTexts.add(row.getText());
        }

        scroll1();

        List<WebElement> items2 = listView.findElements(By.xpath(".//android.widget.ListView/*"));
        for (WebElement row : items2) {
            uniqueTexts.add(row.getText());
        }

        scroll2();

        List<WebElement> items3 = listView.findElements(By.xpath(".//android.widget.ListView/*"));
        for (WebElement row : items3) {
            uniqueTexts.add(row.getText());
        }

        Assertions.assertEquals(42, uniqueTexts.size());
    }

    public void scroll1() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"ScrollBars\"))"));
    }

    public void scroll2() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"WebView3\"))"));
    }

    @Test
    public void setData() {
        androidPage.clickOnViews();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"Date Widgets\"]")));

        androidPage.clickOnDataWidget();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"1. Dialog\"]")));

        androidPage.clickOnDialog();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"change the date\"]")));

        androidPage.clickOnChangeTheDate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));

        androidPage.clickOnTomorrowDate();

        androidPage.clickOnConfirmDate();

        androidPage.clickOnChangeTheTime();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));

        WebElement minutesPicker = driver.findElements(By.className("android.widget.NumberPicker")).get(0);
        scrollNumberPickerToValueDown(minutesPicker, "11");

        WebElement amPmPicker = driver.findElements(By.className("android.widget.NumberPicker")).get(2);
        scrollNumberPickerToValueUp(amPmPicker, "PM");

        WebElement hoursPicker = driver.findElements(By.className("android.widget.NumberPicker")).get(1);
        scrollNumberPickerToValueUp(hoursPicker, "11");

        androidPage.clickOnConfirmTime();

    }

    public void scrollNumberPickerToValueUp(WebElement picker, String targetValue) {
        for (int i = 0; i < 10; i++) {
            String current = picker.getText();
            if (current.equals(targetValue)) return;

            int centerX = picker.getLocation().getX() + picker.getSize().getWidth() / 2;
            int startY = picker.getLocation().getY() + (picker.getSize().getHeight() * 3 / 4);
            int endY = picker.getLocation().getY() + (picker.getSize().getHeight() / 4);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        }
    }

    public void scrollNumberPickerToValueDown(WebElement picker, String targetValue) {
        for (int i = 0; i < 10; i++) {
            String current = picker.getText();
            if (current.equals(targetValue)) return;

            int centerX = picker.getLocation().getX() + picker.getSize().getWidth() / 2;
            int startY = picker.getLocation().getY() + picker.getSize().getHeight() * 3 / 4 + 1;
            int endY = picker.getLocation().getY() + picker.getSize().getHeight() * 5 / 4 + 1;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        }
    }

    @Test
    public void checkTextSwitcher() {
        androidPage.clickOnViews();
        scrollToTextSwitcher();

        androidPage.clickOnTextSwitcher();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.appium.android.apis:id/next")));

        Assertions.assertEquals("0", androidPage.getNumber());

        androidPage.clickOnNextButton();
        androidPage.clickOnNextButton();
        Assertions.assertEquals("2", androidPage.getChangedNumber());
    }

    public void scrollToTextSwitcher() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}