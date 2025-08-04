package task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.AndersenBy;

public class ActionPage {
    WebDriver driver;

    public ActionPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public By pageTitle = By.xpath("//h1[text() = 'Your application has been accepted!']");
    public By confirmButton = By.id("AlertButton");

    public WebElement getConfirmButton() {
        return driver.findElement(By.id("AlertButton"));
    }

    public WebElement getDiscountButton() {
        return driver.findElement(By.xpath("//button[text()='Get Discount']"));
    }

    public WebElement getCancelCourse() {
        return driver.findElement(AndersenBy.testId("PromptButton"));
    }

    public WebElement getMessage1(){
        return driver.findElement(By.xpath("//span[contains(., 'Congratulations, you have successfully enrolled in the course!')]"));
    }

    public WebElement getMessage2(){
        return driver.findElement(By.xpath("//span[contains(., 'You received a 10% discount on the second course.')]"));
    }

    public WebElement getMessage3(){
        return driver.findElement(By.xpath("//span[contains(., 'Your course application has been cancelled. Reason:')]"));
    }


}

