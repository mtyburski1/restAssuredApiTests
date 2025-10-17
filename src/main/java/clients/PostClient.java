package clients;

import example.ApiConfig;
import models.Post;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostClient {

    private static final String POSTS_ENDPOINT = "/posts";

    public Response getPostById(int id) {
        return given()
                .spec(ApiConfig.requestSpec())
                .when()
                .get(POSTS_ENDPOINT + "/" + id);
    }

    public Response createPost(Post post) {
        return given()
                .spec(ApiConfig.requestSpec())
                .body(post)
                .when()
                .post(POSTS_ENDPOINT);
    }

    public Response updatePost(int id, Post post) {
        return given()
                .spec(ApiConfig.requestSpec())
                .body(post)
                .when()
                .put(POSTS_ENDPOINT + "/" + id);
    }

    public Response deletePost(int id) {
        return given()
                .spec(ApiConfig.requestSpec())
                .when()
                .delete(POSTS_ENDPOINT + "/" + id);
    }

    public Post getPostById1(int id) {
        return given()
                .spec(ApiConfig.requestSpec())
                .when()
                .get("/posts/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(Post.class); // <-- Jackson auto-deserializes to Post object
    }
}
