package by.av.mironchyk.page;

import by.av.mironchyk.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    protected void click(By locator) {
        WebElement element = waitUtils.waitForElementClickable(locator);
        element.click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitUtils.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        WebElement element = waitUtils.waitForElementVisible(locator);
        return element.getText();
    }
}
