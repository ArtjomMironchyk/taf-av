package by.av.mironchyk.page.utils;

import org.openqa.selenium.WebElement;

public class InputUtils {

    public static void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
