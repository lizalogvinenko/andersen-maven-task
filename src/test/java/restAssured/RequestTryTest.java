package restAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestTryTest {
    @Test
    public void getRequest() {
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200);
    }

    @Test
    public void createUser() {
        String body = "{\n" +
                "  \"id\": 111,\n" +
                "  \"username\": \"Masha\",\n" +
                "  \"firstName\": \"Masha\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"79864762567\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(String.format(body))
                .when()
                .post("/user")
                .then()
                .statusCode(200);
    }
}
