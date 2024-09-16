package by.av.mironchyk;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class AVLoginMethods {

    WebDriver driver;
    WebDriverWait wait;

    public AVLoginMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPage() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Войти']")));
        loginButton.click();
        WebElement emailLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='почте или логину']")));
        emailLoginButton.click();
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPassword")));
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'button--action')]")));
        submitButton.click();
    }


    public String getEmailErrorMessage() {
        try {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login']/following-sibling::div[contains(@class, 'error-message')]")));
            return emailError.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getPasswordErrorMessage() {
        try {
            WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginPassword']/following-sibling::div[contains(@class, 'error-message')]")));
            return passwordError.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }
}
