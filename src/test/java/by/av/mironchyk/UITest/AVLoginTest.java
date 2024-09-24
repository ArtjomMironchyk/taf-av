package by.av.mironchyk.UITest;

import by.av.mironchyk.page.HomePage;
import by.av.mironchyk.page.LoginPage;
import by.av.mironchyk.page.utils.TestDataGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AVLoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://av.by");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.clickButtonLogin();
        loginPage.clickTabLoginByEmail();
    }

    @Test
    public void testInvalidEmailWithoutAtSymbol() {
        loginPage.inputEmail("emailwithoutat.com");
        loginPage.inputPassword("validPassword123");
        loginPage.clickButtonEnter();
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Неверный логин или пароль. Если забыли пароль, восстановите его", errorMessage);
    }

    @Test
    public void testInvalidEmailWithoutDomain() {
        loginPage.inputEmail("user@");
        loginPage.inputPassword("validPassword123");
        loginPage.clickButtonEnter();
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Неверный логин или пароль. Если забыли пароль, восстановите его", errorMessage);
    }

    @Test
    public void testSpacesInBothFields() {
        loginPage.inputEmail(" ");
        loginPage.inputPassword(" ");
        loginPage.clickButtonEnter();
        String errorEmail = loginPage.getEmailErrorMessage();
        assertEquals("Заполните оба поля", errorEmail);
        String errorPassword = loginPage.getPasswordErrorMessage();
        assertEquals("Заполните поле", errorPassword);
    }

    @Test
    public void testOnlyDigitsInEmail() {
        loginPage.inputEmail("1234567890");
        loginPage.inputPassword("1");
        loginPage.clickButtonEnter();
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Вы не можете использовать для входа логин или почту удаленного аккаунта", errorMessage);
    }

    @Test
    public void testGeneratedEmailAndPassword() {
        String generatedEmail = TestDataGenerator.generateEmail();
        String generatedPassword = TestDataGenerator.generatePassword();

        loginPage.inputEmail(generatedEmail);
        loginPage.inputPassword(generatedPassword);
        loginPage.clickButtonEnter();

        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Неверный логин или пароль. Если забыли пароль, восстановите его", errorMessage);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
