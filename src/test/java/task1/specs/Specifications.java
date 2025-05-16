package task1.specs;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class Specifications {
    protected static final Map<String, String> headers = new HashMap<>();

    static {
        RestAssured.baseURI = "https://reqres.in/";
        headers.put("x-api-key", "reqres-free-v1");
    }

    // Спецификация для запроса
    protected static RequestSpecification getRequestSpec() {
        return given()
                .contentType(ContentType.JSON);
    }

    // Спецификация для успешных ответов (200 OK)
    protected static ResponseSpecification getSuccessResponseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    // Спецификация для успешных ответов (201 OK)
    protected static ResponseSpecification getSuccessResponseSpec201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    // Спецификация для ответа 204
    protected static ResponseSpecification getResponseSpec204() {
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
                .build();
    }

    // Спецификация для ответа 401
    protected static ResponseSpecification getResponseSpec401() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .build();
    }
}
