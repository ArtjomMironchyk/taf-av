package by.av.mironchyk.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookieBannerHandler {
    private WebDriver driver;
    private WaitUtils waitUtils;

    public CookieBannerHandler(WebDriver driver, WaitUtils waitUtils) {
        this.driver = driver;
        this.waitUtils = waitUtils;
    }

    public void acceptCookies() {
        try {
            WebElement acceptButton = waitUtils.waitForElementClickable(
                    By.xpath("//button[@class = 'button button--primary button--block button--large']")
            );
            acceptButton.click();

            waitUtils.waitForElementInvisible(
                    By.xpath("//div[contains(@class, 'cookie')]")
            );
        } catch (TimeoutException e) {
            System.out.println("Баннер cookies не найден или уже закрыт. Продолжаем выполнение теста.");
        } catch (Exception e) {
            System.out.println("Ошибка при обработке баннера cookies: " + e.getMessage());
        }
    }
}
