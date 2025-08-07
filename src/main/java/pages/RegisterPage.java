package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public WebElement title() {
        return driver.findElement(By.cssSelector("h1[class=\"text-2xl\"]"));
    }
}
