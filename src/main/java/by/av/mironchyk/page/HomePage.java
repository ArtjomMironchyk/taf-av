package by.av.mironchyk.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static class HomePageLocators {
        public static final By BUTTON_LOGIN = By.xpath("//span[text()='Войти']");
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonLogin() {
        click(HomePageLocators.BUTTON_LOGIN);
    }
}
