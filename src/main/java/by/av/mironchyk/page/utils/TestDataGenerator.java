package by.av.mironchyk.page.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16);
    }
}
