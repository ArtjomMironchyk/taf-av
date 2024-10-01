package by.av.mironchyk.UITest;

import by.av.mironchyk.page.HomePage;
import by.av.mironchyk.page.LoginPage;
import by.av.mironchyk.constants.ErrorMessages;
import by.av.mironchyk.utils.TestDataGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("invalidEmails")
    public void testInvalidEmails(String email) {
        String validPassword = TestDataGenerator.generateValidPassword();

        loginPage.inputEmail(email);
        loginPage.inputPassword(validPassword);
        loginPage.clickButtonEnter();

        String errorMessage = loginPage.getErrorMessage();
        assertEquals(ErrorMessages.INVALID_LOGIN_OR_PASSWORD, errorMessage);
    }

    static Stream<String> invalidEmails() {
        return Stream.of(
                TestDataGenerator.generateInvalidEmail(),
                TestDataGenerator.generateEmailWithoutAtSymbol(),
                TestDataGenerator.generateEmailWithoutDomain(),
                TestDataGenerator.generateOnlyDigitsEmail(),
                TestDataGenerator.generateSpecialCharacters(10)
        );
    }

    @Test
    public void testInvalidPasswordGenerated() {
        String validEmail = TestDataGenerator.generateValidEmail();
        String invalidPassword = TestDataGenerator.generateInvalidPassword();

        loginPage.inputEmail(validEmail);
        loginPage.inputPassword(invalidPassword);
        loginPage.clickButtonEnter();

        String errorMessage = loginPage.getErrorMessage();
        assertEquals(ErrorMessages.INVALID_LOGIN_OR_PASSWORD, errorMessage);
    }

    @Test
    public void testSpacesInBothFields() {
        String spacesEmail = TestDataGenerator.generateSpaces(1);
        String spacesPassword = TestDataGenerator.generateSpaces(1);

        loginPage.inputEmail(spacesEmail);
        loginPage.inputPassword(spacesPassword);
        loginPage.clickButtonEnter();

        String errorEmail = loginPage.getEmailErrorMessage();
        assertEquals(ErrorMessages.FILL_BOTH_FIELDS, errorEmail);

        String errorPassword = loginPage.getPasswordErrorMessage();
        assertEquals(ErrorMessages.FILL_FIELD, errorPassword);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
