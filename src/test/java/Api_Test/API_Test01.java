package Api_Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;

public class API_Test01 {


    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @Test
    public void TC01(){

        response= given().
                   accept(ContentType.JSON).
                   when().
                   get(endPoint);
       // response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK);
             json=response.jsonPath();
    }

    @Test
    public void TC_0103() { //all data count assertion --- Beklenen sonuc:1375

        TC01();

        //1. yol

//        int totalData= json.getInt("meta.pagination.total");
//        Assert.assertEquals(totalData, 1375);
//        System.out.println(totalData);

        //2. yol

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK).
                body("meta.pagination.total", Matchers.equalTo(1611));

    }
}
