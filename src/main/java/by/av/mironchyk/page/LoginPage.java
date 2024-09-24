package by.av.mironchyk.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static class LoginPageLocators {
        public static final By TAB_LOGIN_BY_EMAIL = By.xpath("//button[text()='почте или логину']");
        public static final By INPUT_EMAIL = By.id("authLogin");
        public static final By INPUT_PASSWORD = By.id("loginPassword");
        public static final By BUTTON_ENTER = By.xpath("//button[@type='submit']");
        public static final By EMAIL_ERROR_MESSAGE = By.xpath("//input[@id='authLogin']/following-sibling::div[@class='error-message']");
        public static final By PASSWORD_ERROR_MESSAGE = By.xpath("//input[@id='loginPassword']/following-sibling::div[@class='error-message']");
        public static final By ERROR_MESSAGE = By.xpath("//div[contains(@class,'error-message')]");
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickTabLoginByEmail() {
        click(LoginPageLocators.TAB_LOGIN_BY_EMAIL);
    }

    public void inputEmail(String email) {
        type(LoginPageLocators.INPUT_EMAIL, email);
    }

    public void inputPassword(String password) {
        type(LoginPageLocators.INPUT_PASSWORD, password);
    }

    public void clickButtonEnter() {
        click(LoginPageLocators.BUTTON_ENTER);
    }

    public String getEmailErrorMessage() {
        return getText(LoginPageLocators.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        return getText(LoginPageLocators.PASSWORD_ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return getText(LoginPageLocators.ERROR_MESSAGE);
    }
}
