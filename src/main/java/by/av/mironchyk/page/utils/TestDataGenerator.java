package by.av.mironchyk.page.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    public static String generateValidEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateInvalidEmail() {
        return faker.lorem().characters(10); // Генерирует строку из 10 случайных символов
    }

    public static String generateEmailWithoutAtSymbol() {
        return faker.internet().emailAddress().replace("@", "");
    }

    public static String generateEmailWithoutDomain() {
        return faker.internet().emailAddress().split("@")[0] + "@";
    }

    public static String generateOnlyDigitsEmail() {
        return faker.number().digits(10);
    }

    public static String generateSpaces(int count) {
        return " ".repeat(count);
    }

    public static String generateSpecialCharacters(int count) {
        String specialChars = "!@#$%^&*()_+[]{}|;':,.<>?/~`-=";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int index = faker.random().nextInt(specialChars.length());
            result.append(specialChars.charAt(index));
        }
        return result.toString();
    }

    public static String generateValidPassword() {
        return faker.internet().password(8, 16);
    }

    public static String generateInvalidPassword() {
        return faker.lorem().characters(5); // Генерирует короткий пароль
    }
}
