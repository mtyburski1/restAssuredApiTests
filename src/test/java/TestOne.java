import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TestOne {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final String API_KEY = "reqres-free-v1";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetUser() {
        given()
                .header("x-api-key", API_KEY)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = """
                {
                    "name": "John Doe",
                    "job": "Tester"
                }
                """;

        given()
                .header("x-api-key", API_KEY)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("job", equalTo("Tester"));
    }

    @Test
    public void testDeleteUser() {
        given()
                .header("x-api-key", API_KEY)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
