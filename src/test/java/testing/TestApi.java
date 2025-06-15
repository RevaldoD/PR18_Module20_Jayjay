package testing;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class TestApi {
    @Test
    public void testExistingAccountToken() {
        // Define input values
        String email = "eve.holt@reqres.in";
        String password = "pistol";

        // Build JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        // Send POST request and validate
        RestAssured.given()         // RequestSpecification
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/register")   // Response
                .then().log().all()    // Optional: Logs the full response for debugging
                .assertThat().statusCode(200)            // ValidatableResponse
                .assertThat().body("token", Matchers.notNullValue())
                .assertThat().body("id", Matchers.notNullValue());
    }

    @Test
    public void testUpdateColumn() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("job", "QA engineer");

        RestAssured.given()           // RequestSpecification
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .patch("https://reqres.in/api/users/2")   // Response
                .then().log().all()         // ValidatableResponse
                .assertThat().statusCode(200)
                .assertThat().body("job", Matchers.equalTo("QA engineer"));
    }

    @Test
    public void testLoginInvalidCredentials() {
        String email = "ezel@getnada.com";
        String password = "vanguard";

        // Build JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        RestAssured.given()           // RequestSpecification
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/login")      // Response
                .then().log().all()         // ValidatableResponse
                .assertThat().statusCode(400)
                .assertThat().body("error", Matchers.equalTo("user not found"));
    }

}
