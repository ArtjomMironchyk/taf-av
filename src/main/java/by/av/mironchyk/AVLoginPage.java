package by.av.mironchyk;

import com.github.javafaker.Faker;

public class AVLoginPage {

    private static Faker faker = new Faker();

    public static String getValidEmail() {
        return "artjommironchyk@gmail.com";
    }

    public static String getValidPassword() {
        return "Artem555";
    }

    public static String getRandomEmail() {
        return faker.name().firstName().toLowerCase() + faker.number().randomDigit() + "@gmail.com";
    }

    public static String getRandomPassword() {
        return faker.internet().password(8, 12, true, true);
    }

    public static String getSpaceForEmail() {
        return " ";
    }

    public static String getSpaceForPassword() {
        return " ";
    }

    public static String getOneForEmailAndPassword() {
        return "1";
    }
}
