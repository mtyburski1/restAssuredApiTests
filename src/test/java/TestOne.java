import clients.PostClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TestOne {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final String API_KEY = "reqres-free-v1";
    private final PostClient client = new PostClient();

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

        Response response = client.getPostById(1);
        Assert.assertEquals(response.statusCode(), 200);

        Post postById1 = client.getPostById1(1);
        int id = postById1.getId();
        Assert.assertEquals(id, 1);
        System.out.println();
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
