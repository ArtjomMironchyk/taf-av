package by.av.mironchyk.UITest;

import by.av.mironchyk.page.CarCatalogPageLocators;
import by.av.mironchyk.utils.CookieBannerHandler;
import by.av.mironchyk.utils.WaitUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarBrandTest {

    private WebDriver driver;
    private CookieBannerHandler cookieBannerHandler;
    private WaitUtils waitUtils;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080)); // Установка размера окна браузера
        driver.get("https://cars.av.by");
        waitUtils = new WaitUtils(driver, 20);  // Используем WaitUtils с таймаутом 20 секунд

        // Инициализируем CookieBannerHandler с использованием waitUtils
        cookieBannerHandler = new CookieBannerHandler(driver, waitUtils);

        // Принятие cookies
        cookieBannerHandler.acceptCookies();
    }

    @Test
    public void testCountVolkswagenPassatB3Listings() throws InterruptedException {
        // Клик на Volkswagen
        WebElement volkswagenLink = waitUtils.waitForElementClickable(CarCatalogPageLocators.VOLKSWAGEN_LINK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", volkswagenLink);
        Thread.sleep(500);
        volkswagenLink.click();

        // Ожидание исчезновения оверлеев
        waitForOverlaysToDisappear();

        // Ожидание загрузки элементов модели
        waitUtils.waitForElementVisible(CarCatalogPageLocators.PASSAT_LINK);

        // Клик на Passat
        WebElement passatLink = waitUtils.waitForElementClickable(CarCatalogPageLocators.PASSAT_LINK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passatLink);
        Thread.sleep(500);
        passatLink.click();

        // Ожидание загрузки поколений модели
        waitUtils.waitForElementVisible(CarCatalogPageLocators.PASSAT_B3_LINK);

        // Клик на поколение B3
        WebElement passatB3Link = waitUtils.waitForElementClickable(CarCatalogPageLocators.PASSAT_B3_LINK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passatB3Link);
        Thread.sleep(500);
        passatB3Link.click();

        // Ожидание исчезновения оверлеев перед кликом на кнопку "Показать объявления"
        waitForOverlaysToDisappear();

        // Ожидание кнопки "Показать объявления" и клик по ней
        if (isElementPresent(CarCatalogPageLocators.LISTING_COUNT_BUTTON)) {
            WebElement showListingsButton = waitUtils.waitForElementClickable(CarCatalogPageLocators.LISTING_COUNT_BUTTON);

            // Прокрутка до кнопки
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showListingsButton);
            Thread.sleep(500);

            // Клик с помощью JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showListingsButton);

            // Ожидание исчезновения кнопки
            waitUtils.waitForElementInvisible(CarCatalogPageLocators.LISTING_COUNT_BUTTON);
        }

        // Ожидание загрузки объявлений
        waitUtils.waitForElementVisible(By.cssSelector("div.listing-item"));

        // Подсчет количества объявлений
        int count = getListingsCount();
        System.out.println("Количество объявлений: " + count);

        // Проверка, что количество объявлений больше 0
        assertTrue(count > 0, "Количество объявлений должно быть больше нуля.");
    }

    private void waitForOverlaysToDisappear() {
        // Ожидание исчезновения всех возможных оверлеев
        waitUtils.waitForElementInvisible(By.cssSelector("div.overlay"));
        waitUtils.waitForElementInvisible(By.cssSelector("div.modal-dialog"));
        // Добавьте дополнительные селекторы, если обнаружите другие перекрывающие элементы
    }

    private int getListingsCount() {
        return driver.findElements(By.cssSelector("div.listing-item")).size();
    }

    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
