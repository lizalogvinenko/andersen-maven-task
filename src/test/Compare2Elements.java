package src.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Compare2Elements {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://qa-course-01.andersenlab.com/");

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));

        shouldCompareTwoElements(email, password);

        driver.quit();
    }

    public static void shouldCompareTwoElements(WebElement email, WebElement password) {

        Point emailLocation = email.getLocation();
        Point passwordLocation = password.getLocation();

        int emailHeight = emailLocation.getY();
        int passwordHeight = passwordLocation.getY();

        if (emailHeight < passwordHeight) {
            System.out.println("Element email is higher");
        } else {
            System.out.println("Element password is higher");
        }

        int emailHorizontalPosition = emailLocation.getX();
        int passwordHorizontalPosition = passwordLocation.getX();

        if (emailHorizontalPosition < passwordHorizontalPosition) {
            System.out.println("The email element is located to the left");
        } else if (emailHorizontalPosition > passwordHorizontalPosition) {
            System.out.println("The password element is located to the left");
        } else {
            System.out.println("Elements located one below the other");
        }

        Dimension emailSpace = email.getSize();

        int emailSpaceWidth = emailSpace.getWidth();
        int emailSpaceHeight = emailSpace.getHeight();
        int emailArea = emailSpaceHeight * emailSpaceWidth;

        Dimension passwordSpace = password.getSize();

        int passwordSpaceWidth = passwordSpace.getWidth();
        int passwordSpaceHeight = passwordSpace.getHeight();
        int passwordArea = passwordSpaceHeight * passwordSpaceWidth;

        if (emailArea > passwordArea) {
            System.out.println("Element email takes up more space");
        } else if (emailArea < passwordArea) {
            System.out.println("Element password takes up more space");
        } else {
            System.out.println("Elements take up equal space");
        }
    }
}
