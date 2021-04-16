package Api_Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Deneme_Api {


    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;


    @Test
    public void setup() {
        response = given().
                accept(ContentType.JSON).
                when().
                get(endPoint);
        json = response.jsonPath();
        // response.prettyPrint();

    }
}