package ApiGoRest_Test;

import Utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TC_11_15 {

    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @Test  //id unique assertion
    public void TC06() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();



    }
}
