package by.av.mironchyk;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AVLoginTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://av.by/");
    }

    @Test
    public void testLoginByEmailButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Войти']")));

        loginButton.click();

        WebElement emailLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='почте или логину']")));

        emailLoginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));


        Assertions.assertTrue(driver.findElement(By.name("login")).isDisplayed(), "Форма для входа по почте отображается.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
