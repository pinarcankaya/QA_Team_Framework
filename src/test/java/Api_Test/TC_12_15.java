package Api_Test;
import Utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
public class TC_12_15 {

    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @Test  //active status count assertion
    public void TC0112() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<String> statusList = json.getList("data.status");

        int count = 0;
        for (String w : statusList) {
            if (w.equals("Active")) {
                count++;
            }
        }
        System.out.println(count);//13
        Assert.assertNotEquals(count, 421);

    }

    @Test  //search for id(4142) assertion
    public void TC0113() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<Integer> idList = json.getList("data.id");
        Assert.assertFalse(idList.contains(4142));


    }

    @Test  //null data verification
    public void TC0114() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<Map<String, Object>> allDataList = json.getList("data");
        System.out.println(allDataList);

        List<Map<String, Object>> map = json.getList("data");

        for (Map<String, Object> w : allDataList) {
            for (String k: w.keySet()) {

                Assert.assertFalse(w.get(k) == null);//distaki map'in icindeki key'lere tek tek  bakar
            }
        }
    }

    @Test  //search for name("nuri duman") assertion
    public void TC0115() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> nameList = json.getList("data.name");
        System.out.println(nameList);
        Assert.assertTrue(!nameList.contains("nuri duman"));


    }
}

