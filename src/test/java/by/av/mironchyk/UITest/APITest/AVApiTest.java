package by.av.mironchyk.UITest.APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AVApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.av.by"; // Устанавливаем базовый URL API
    }

    @Test
    public void testFailedLogin() {
        String requestBody = "{ \"login\": \"valid_login\", \"password\": \"invalid_password\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/login/sign-in")
                .then()
                .statusCode(400)
                .body("messageText", equalTo("Неверный логин или пароль. Если забыли пароль, восстановите его"));
    }

    @Test
    public void testEmptyFields() {
        String requestBody = "{ \"login\": \"\", \"password\": \"\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/login/sign-in")
                .then()
                .statusCode(400)
                .body("message", equalTo("exception.validation.failed"))
                .body("context.errors.login[0]", equalTo("Заполните оба поля"));
    }

    @Test
    public void testLoginWithDeletedAccount() {
        String requestBody = "{ \"login\": \"1\", \"password\": \"1\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/login/sign-in")
                .then()
                .statusCode(400)
                .body("messageText", equalTo("Вы не можете использовать для входа логин или почту удалённого аккаунта"));
    }

    @Test
    public void testLoginAttemptsLimit() {
        String requestBody = "{ \"login\": \"valid_login\", \"password\": \"invalid_password\" }";

        for (int i = 0; i < 5; i++) {
            given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/login/sign-in")
                    .then()
                    .statusCode(429);
        }

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/login/sign-in")
                .then()
                .statusCode(429)
                .body("messageText", equalTo("Слишком часто вводится неверный пароль. Повторите попытку позже"));
    }
}
