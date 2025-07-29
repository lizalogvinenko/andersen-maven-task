package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Open5Pages {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    void shouldSwitchBetweenTabs() {
        driver.get("http://www.automationpractice.pl/index.php");

        ((ChromeDriver) driver).executeScript("window.open('https://zoo.waw.pl/');");
        ((ChromeDriver) driver).executeScript("window.open('https://www.w3schools.com/');");
        ((ChromeDriver) driver).executeScript("window.open('https://www.clickspeedtester.com/click-counter/');");
        ((ChromeDriver) driver).executeScript("window.open('https://andersenlab.com/');");

        List<String> allTabs = new ArrayList<>(driver.getWindowHandles());

        for (int i = 1; i < allTabs.size(); i++) {
            driver.switchTo().window(allTabs.get(i));
            System.out.println(driver.getTitle());

            if (driver.getTitle().contains("Zoo")) {
                driver.close();
            }
        }

        driver.quit();
    }
}
