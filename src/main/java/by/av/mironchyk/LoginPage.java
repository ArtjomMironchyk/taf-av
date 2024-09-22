package by.av.mironchyk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTabLoginByEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By tabLoginByEmailBy = By.xpath(LoginPageXPath.TAB_LOGIN_BY_EMAIL_XPATH);
        WebElement tabLoginByEmail = wait.until(ExpectedConditions.elementToBeClickable(tabLoginByEmailBy));
        tabLoginByEmail.click();
    }

    public void inputEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By inputEmailBy = By.xpath(LoginPageXPath.INPUT_EMAIL_XPATH);
        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmailBy));
        inputEmail.sendKeys(email);
    }

    public void inputPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By inputPasswordBy = By.xpath(LoginPageXPath.INPUT_PASSWORD_XPATH);
        WebElement inputPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(inputPasswordBy));
        inputPassword.sendKeys(password);
    }

    public void clickButtonEnter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By buttonEnterBy = By.xpath(LoginPageXPath.BUTTON_ENTER_XPATH);
        WebElement buttonEnter = wait.until(ExpectedConditions.elementToBeClickable(buttonEnterBy));
        buttonEnter.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By errorMessageBy = By.xpath(LoginPageXPath.ERROR_MESSAGE_XPATH);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
        return errorMessage.getText();
    }
}
