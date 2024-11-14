package testsAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TypiCodeNegativeTests {
    RequestSpecification requestSpecification;

    @BeforeTest
    void setup() {
        requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification = requestSpecification.log().all();
        requestSpecification.baseUri("https://jsonplaceholder.typicode.com");
    }

    @Test(priority = 1)
    void addPostWrongPayload() {
        requestSpecification.basePath("/posts");
        requestSpecification.log().all();
        requestSpecification.body(" {\n" +
                "        \"userid\": \"string id\",\n" +
                "        \"bodye\" : \"5\",\n" +
                "        \"title\": 10\n" +
                "}");

        Response response = requestSpecification.when().post();
        // Demo API accept wrong attributes names and wrong value types to create
        // should not be ec201
        response.then().statusCode(201);
    }

    @Test(priority = 2)
    void getPostsByNonExistentId() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.log().all();
        // non existent ID, pass
        requestSpecification.pathParam("id", 1001);
        Response response = requestSpecification.when().get();
        response.then().statusCode(404);
    }

    @Test(priority = 3)
    void updatePostWrongPayload() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 1);
        requestSpecification.log().all();
        requestSpecification.body(" {\n" +
                "        \"userId\": \"string id\",\n" +
                "        \"body\" : \"5\",\n" +
                "        \"title\": 10\n" +
                "}");

        Response response = requestSpecification.when().put();
        // Demo API accept wrong attributes names and wrong value types to update
        // should not be ec200
        response.then().statusCode(200);
    }

    @Test(priority = 4)
    void deleteNonExistentPost() {
        requestSpecification.basePath("/posts/{id}");
        requestSpecification.pathParam("id", 1000);
        requestSpecification.log().all();
        Response response = requestSpecification.when().delete();
        // Demo API accept wrong attributes names and wrong value types to delete
        // should not be ec200
        response.then().statusCode(200);
    }

}
