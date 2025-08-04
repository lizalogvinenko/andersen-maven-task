package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DragAndDropPage {
    WebDriver driver;

    public DragAndDropPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public By pageTitle = By.xpath("//h1[text()='Sort your responsibilities']");

    public WebElement getFirstElement() {
        return driver.findElement(By.id("manual1"));
    }

    public WebElement getSecondElement() {
        return driver.findElement(By.id("manual2"));
    }

    public WebElement getThirdElement() {
        return driver.findElement(By.id("auto1"));
    }

    public WebElement getForthElement() {
        return driver.findElement(By.id("auto2"));
    }

    public WebElement getPlaceManualWork1() {
        return driver.findElement(By.id("target-manual1"));
    }

    public WebElement getPlaceManualWork2() {
        return driver.findElement(By.id("target-manual2"));
    }

    public WebElement getPlaceAutomationWork1() {
        return driver.findElement(By.id("target-auto1"));
    }

    public WebElement getPlaceAutomationWork2() {
        return driver.findElement(By.id("target-auto2"));
    }

    public WebElement getMessage() {
        return driver.findElement(By.xpath("//div[text()=\"Congratulations! Let's test for the best!\"]"));
    }
}
