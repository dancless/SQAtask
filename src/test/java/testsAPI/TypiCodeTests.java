package testsAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TypiCodeTests {
    RequestSpecification requestSpecification;

    @BeforeTest
    void setup() {
        requestSpecification = RestAssured.given();
        // specify content type
        requestSpecification.contentType(ContentType.JSON);
        // Set logger
        requestSpecification = requestSpecification.log().all();
        // Set base URI
        requestSpecification.baseUri("https://jsonplaceholder.typicode.com");
    }

    @Test(priority = 1, enabled = true)
    void addPost() {
        // Set base Path
        requestSpecification.basePath("/posts");
        // Set logger
        requestSpecification.log().all();
        // Request Payload
        requestSpecification.body(" {\n" +
                "        \"title\": \"Max Malapata\",\n" +
                "        \"body\": \"text\",\n" +
                "        \"userId\": 10\n" +
                "}");

        // get response
        Response response = requestSpecification.when().post();
        // validation RestAssured
        response.then().statusCode(201);
        // Assert TestNG
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    // Chained full call test
    @Test(priority = 1, enabled = true)
    void addPostAlt() {
        RestAssured.given()
                .log()
                .all()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .body(" {\n" +
                        "        \"title\": \"Max Malapata\",\n" +
                        "        \"body\": \"text\",\n" +
                        "        \"userId\": 10\n" +
                        "}")
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Test(priority = 2)
    void getAllPosts() {
        requestSpecification.basePath("/posts");
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostsById() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 100);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostCommentsByID() {
        requestSpecification.basePath("/posts/{id}/comments");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostPhotosByID() {
        requestSpecification.basePath("/posts/{id}/photos");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostAlbumsByID() {
        requestSpecification.basePath("/posts/{id}/albums");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostEntryByID() {
        requestSpecification.basePath("/posts/{id}/posts");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    void getPostCommentsByPostID() {
        requestSpecification.basePath("/comments?postId={id}");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        Response response = requestSpecification.when().get();
        response.then().statusCode(200);
    }

    @Test(priority = 4)
    void updatePost() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        requestSpecification.body(" {\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"Updated Title\",\n" +
                "        \"body\": \"body\",\n" +
                "        \"userId\": 10\n" +
                "}");

        Response response = requestSpecification.when().put();
        response.then().statusCode(200);
    }

    @Test(priority = 4)
    void patchPost() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        requestSpecification.body(" {\n" +
                "        \"title\": \"Patched Title\"\n" +
                "}");

        Response response = requestSpecification.when().patch();
        response.then().statusCode(200);
    }

    @Test(priority = 5)
    void deletePost() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 101);
        requestSpecification.log().all();
        Response response = requestSpecification.when().delete();
        response.then().statusCode(200);
    }

}
