package by.av.mironchyk.UITest.APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CarFilterTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.av.by";
    }

    @Test
    public void testCarFilter() {
        String requestBody = "{\n" +
                "    \"page\": 1,\n" +
                "    \"properties\": [\n" +
                "        {\n" +
                "            \"name\": \"year\",\n" +
                "            \"value\": {\n" +
                "                \"max\": null,\n" +
                "                \"min\": 2024\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"price_usd\",\n" +
                "            \"value\": {\n" +
                "                \"max\": 10000,\n" +
                "                \"min\": null\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"price_currency\",\n" +
                "            \"value\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"engine_type\",\n" +
                "            \"value\": [7]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sorting\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/offer-types/cars/filters/main/apply")
                .then()
                .statusCode(200)
                .body("count", equalTo(1))
                .body("adverts[0].status", equalTo("active"))
                .body("adverts[0].price.usd.amount", equalTo(6500))
                .body("adverts[0].year", equalTo(2024));
    }
}
