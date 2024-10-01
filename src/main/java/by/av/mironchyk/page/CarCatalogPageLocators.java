package by.av.mironchyk.page;

import org.openqa.selenium.By;

public class CarCatalogPageLocators {

    public static final By VOLKSWAGEN_LINK = By.xpath("//span[normalize-space()='Volkswagen']");
    public static final By PASSAT_LINK = By.xpath("//span[normalize-space()='Passat']");
    public static final By PASSAT_B3_LINK = By.xpath("//span[normalize-space()='B3, 1988-1994']");
    public static final By LISTING_COUNT_BUTTON = By.xpath("//button[@type='submit' and contains(text(), 'Показать')]");

}
