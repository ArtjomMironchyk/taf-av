package by.av.mironchyk;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestMethodOrder(OrderAnnotation.class)
public class AVLoginTest {

    WebDriver driver;
    AVLoginMethods loginMethods;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://av.by/");
        loginMethods = new AVLoginMethods(driver);
    }

    @Test
    @Order(1)
    public void testLoginWithValidData() {
        loginMethods.openLoginPage();
        loginMethods.enterEmail(AVLoginPage.getValidEmail());
        loginMethods.enterPassword(AVLoginPage.getValidPassword());

        String expectedUrlAfterLogin = "https://av.by/";
        Assertions.assertEquals(expectedUrlAfterLogin, driver.getCurrentUrl(), "Успешный вход не выполнен.");
        System.out.println("Успешный вход выполнен.");
    }

    @Test
    @Order(2)
    public void testLoginWithGeneratedInvalidData() {
        loginMethods.openLoginPage();

        loginMethods.enterEmail(AVLoginPage.getRandomEmail());
        loginMethods.enterPassword(AVLoginPage.getRandomPassword());
        loginMethods.clickSubmit();

        String errorMessage = loginMethods.getEmailErrorMessage();
        String expectedEmailError = "Неверный логин или пароль. Если забыли пароль, восстановите его";

        if (errorMessage != null) {
            Assertions.assertEquals(expectedEmailError, errorMessage, "Сообщение об ошибке под полем email неверное.");
            System.out.println("Сообщение об ошибке: " + errorMessage);
        } else {
            System.out.println("Ошибок нет.");
        }
    }

    @Test
    @Order(3)
    public void testLoginWithSpacesInFields() {
        loginMethods.openLoginPage();

        loginMethods.enterEmail(AVLoginPage.getSpaceForEmail());
        loginMethods.enterPassword(AVLoginPage.getSpaceForPassword());
        loginMethods.clickSubmit();

        String expectedEmailError = "Заполните оба поля";
        String expectedPasswordError = "Заполните поле";

        String emailError = loginMethods.getEmailErrorMessage();
        if (emailError != null) {
            Assertions.assertEquals(expectedEmailError, emailError, "Сообщение об ошибке под полем email неверное.");

        }

        String passwordError = loginMethods.getPasswordErrorMessage();
        if (passwordError != null) {
            Assertions.assertEquals(expectedPasswordError, passwordError, "Сообщение об ошибке под полем пароля неверное.");
        }

        if (expectedEmailError.equals(emailError) && expectedPasswordError.equals(passwordError)) {
            System.out.println("Ошибок нет.");
        }
    }

    @Test
    @Order(4)
    public void testLoginWithOneInFields() {
        loginMethods.openLoginPage();

        loginMethods.enterEmail(AVLoginPage.getOneForEmailAndPassword());
        loginMethods.enterPassword(AVLoginPage.getOneForEmailAndPassword());
        loginMethods.clickSubmit();

        String expectedEmailError = "";
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
