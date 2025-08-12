package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import authData.AuthData;

public class LoginPage {
    WebDriver driver;

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public By title = By.cssSelector("h1[class=\"text-2xl\"]");

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

    public By errorMessageNotValidCredentials = By.xpath("//span[contains(text(), 'Email or password is not valid')]");

    public WebElement errorMessageNotValidCredentials() {
        return driver.findElement(By.xpath("//span[contains(text(), 'Email or password is not valid')]"));
    }

    public LoginPage enterValidEmail() {
        emailField().sendKeys(AuthData.VALID_EMAIL);
        return this;
    }

    public LoginPage enterValidPassword() {
        passwordField().sendKeys(AuthData.VALID_PASSWORD);
        return this;
    }

    public LoginPage enterInvalidPassword() {
        passwordField().sendKeys(AuthData.INVALID_PASSWORD);
        return this;
    }

    public void clickOnLoginButton() {
        loginButton().click();
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
