package example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    private static final String BASE_URL = System.getProperty("api.baseUrl", "https://jsonplaceholder.typicode.com");

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/json")
                .addFilter(new AllureRestAssured()) // <-- logowanie request/response do Allure
                .build();
    }
}
