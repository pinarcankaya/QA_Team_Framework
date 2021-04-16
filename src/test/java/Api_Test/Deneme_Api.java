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
        response.then().assertThat().statusCode(200).contentType("application/json");

    }

    @Test
    public void TC01() {
        setup();

        int totalData = json.getInt("meta.pagination.total");
        System.out.println(totalData);
        Assert.assertNotEquals(totalData, 1375);
        int pages = json.getInt("meta.pagination.pages");
        System.out.println(pages);
        Assert.assertNotEquals(pages, 20);

    }

    @Test
    public void TC02() {
        setup();
        List<Integer> idListOrder = json.getList("data.id");
        System.out.println(idListOrder);

        for (int i = 0; i < idListOrder.size(); i++) {
            for (int j = i + 1; j < idListOrder.size(); j++) {
                // System.out.println(list.get(i) + "----" +list.get(j));
                Assert.assertTrue(idListOrder.get(i) < idListOrder.get(j));
            }
        }
    }

    @Test  //id unique assertion
    public void TC03() {
        setup();
        List<Integer> idList = json.getList("data.id");
        for (int i = 0; i < idList.size(); i++) {
            for (int j = i + 1; j < idList.size(); j++) {

               Assert.assertTrue(idList.get(i) != idList.get(j));
            }
        }
        //2.yol
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>(idList);
      //  System.out.println(idList.size());
       // System.out.println(linkedHashSet.size());
        Assert.assertEquals(idList.size(),linkedHashSet.size());

    }
}