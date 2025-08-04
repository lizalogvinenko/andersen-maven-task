package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectPage {
    WebDriver driver;

    public SelectPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public By pageTitle = By.xpath("//h1[text() = 'Choose your course']");

    public WebElement getPageTitle() {
        return driver.findElement(By.xpath("//h1[text() = 'Choose your course']"));
    }

    public WebElement getSelectCountry() {
        return driver.findElement(By.cssSelector("select[title=\"Select country\"]"));
    }

    public WebElement getSelectLanguage() {
        return driver.findElement(By.id("SelectLanguage"));
    }

    public WebElement getSelectType() {
        return driver.findElement(By.cssSelector("select[title=\"Select type\"]"));
    }

    public WebElement getStartDate() {
        return driver.findElement(By.cssSelector("input[title=\"Start date\"]"));
    }

    public WebElement getEndDate() {
        return driver.findElement(By.cssSelector("input[title=\"End date\"]"));
    }

    public WebElement getSelectCourse() {
        return driver.findElement(By.id("MultipleSelect"));
    }

    public WebElement getSearchButton() {
        return driver.findElement(By.name("SelectPageSearchButton"));
    }

    public WebElement getMessage() {
        return driver.findElement(By.xpath("//h2[starts-with(text(), 'Unfortunately, we did not find')]"));
    }
}
