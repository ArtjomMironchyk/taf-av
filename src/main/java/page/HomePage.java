package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By buttonLoginBy = By.xpath(HomePageXPath.BUTTON_LOGIN_XPATH);
        WebElement buttonLogin = wait.until(ExpectedConditions.elementToBeClickable(buttonLoginBy));
        buttonLogin.click();
    }
}
