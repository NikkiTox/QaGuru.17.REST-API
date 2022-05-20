package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;;

public class reqresInAPITests {
    @Test
    void checkRegistrationCode200() {
        String authorizedData = "{\"email\": \"eve.holt@reqres.in\"," +
                "\"password\": \"pistol\"}";
        given()
                .body(authorizedData)
                .contentType(ContentType.JSON)
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void checkRegistrationCode400() {
        String authorizedData = "{\"email\": \"sydney@fife\"}";
        given()
                .body(authorizedData)
                .contentType(ContentType.JSON)
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void checkDelete204() {
        given()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void checkCreate201() {
        String createUser = "{\"name\": \"morpheus\"," +
                "\"job\": \"leader\"}";
        given()
                .body(createUser)
                .contentType(ContentType.JSON)
                .post("https://reqres.in/api/users/2")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void check404() {
        get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);

    }
}
