package ApiGoRest_Test;

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

    @Test  //id unique assertion
    public void TC12() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<String> statusList=json.getList("data.status");
        int count=0;
        for(String w:statusList){
            if(w.equals("Active")){
                count++;
            }
        }
        System.out.println(count);
        Assert.assertEquals(count,11);

    }

    @Test
    public void TC13() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<Integer> idList=json.getList("data.id");
      Assert.assertFalse(idList.contains(4142));

    }

    @Test
    public void TC14() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<Map<String,Object>> allDataList=json.getList("data");
       // System.out.println(allDataList);
      for(Map<String,Object> w:allDataList){  ///listin icindeki bir map var
          for(String k :w.keySet()){        //her bir map'in icine bakar
              Assert.assertFalse(w.get(k)==null);
          }
      }


    }
}
