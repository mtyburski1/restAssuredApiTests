import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestOne {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testGetUser() {
        Response response = RestAssured
                .given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract().response();


        Assert.assertEquals(response.jsonPath().getString("data.email"), "janet.weaver@reqres.in");
        Assert.assertNotNull(response.jsonPath().getString("support.text"));
    }

    @Test
    public void testCreateUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
    }

    @Test
    public void testDeleteUser() {
        RestAssured
                .given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
