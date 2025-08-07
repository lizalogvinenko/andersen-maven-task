package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.AuthData;

public class LoginPage {
    WebDriver driver;

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public By title = By.cssSelector("h1[class=\"text-2xl\"]");

    public WebElement title() {
        return driver.findElement(By.cssSelector("h1[class=\"text-2xl\"]"));
    }

    public By emailField = By.name("email");

    public WebElement emailField() {
        return driver.findElement(By.name("email"));
    }

    public WebElement passwordField() {
        return driver.findElement(By.name("password"));
    }

    public WebElement loginButton() {
        return driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));
    }

    public By registerLink = By.xpath("//span[contains(text(), 'Registration')]");

    public WebElement registerLink() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Registration')]"));
    }

    public WebElement errorMessageNotValidEmail() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Invalid email')]"));
    }

    public By errorMessageNotValidCredentials = By.xpath("//span[contains(text(), 'Email or password is not valid')]");

    public WebElement errorMessageNotValidCredentials() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Email or password is not valid')]"));
    }

    public WebElement errorMessageLongPassword() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Maximum 20 characters')]"));
    }

    public WebElement errorMessageShortPassword() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Minimum 8 characters')]"));
    }

    public LoginPage enterValidEmail() {
        emailField().sendKeys(AuthData.VALID_EMAIL);
        return this;
    }

    public LoginPage enterValidPassword() {
        passwordField().sendKeys(AuthData.VALID_PASSWORD);
        return this;
    }

    public LoginPage enterInvalidEmail() {
        emailField().sendKeys(AuthData.INVALID_EMAIL);
        return this;
    }

    public LoginPage enterNotEmailFormat() {
        emailField().sendKeys(AuthData.NOT_EMAIL_FORMAT);
        return this;
    }

    public LoginPage enterInvalidPassword() {
        passwordField().sendKeys(AuthData.INVALID_PASSWORD);
        return this;
    }

    public LoginPage enterLongPassword() {
        passwordField().sendKeys(AuthData.LONG_PASSWORD);
        return this;
    }

    public LoginPage enterShortPassword() {
        passwordField().sendKeys(AuthData.SHORT_PASSWORD);
        return this;
    }

    public void clickOnLoginButton() {
        loginButton().click();
    }

    public void clickOnRegistrationLink() {
        registerLink().click();
    }

    public LoginPage clickOnEmailField() {
        emailField().click();
        return this;
    }

    public LoginPage clickOnPasswordField() {
        passwordField().click();
        return this;
    }
}
