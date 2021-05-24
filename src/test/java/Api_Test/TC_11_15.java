package Api_Test;

import Utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.groovy.json.internal.ReaderCharacterSource;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TC_11_15 {
    Response response;
    JsonPath jsonPath;
    SoftAssert softAssert=new SoftAssert();

    @Test
    public void setup() {
        response = given().
                  accept(ContentType.JSON).
                   when().
                   get(ConfigurationReader.getProperty("EndPoint"));
         //response.prettyPrint();
         jsonPath=response.jsonPath();
                
    }

    @Test  //more females assertion
    public void TC0111() {
        setup();
        List<String>maleList=jsonPath.getList("data.gender");

        int countMale=0;
        int countFemale=0;
        for (String male:maleList){
            if (male.equals("Male")){
                countMale++;
            }else {
                countFemale++;
            }
        }
        System.out.println(countMale+" tane Male var");
        System.out.println(countFemale+" tane Female var");

        Assert.assertFalse(countFemale>countMale);
    }

    @Test  //active status count assertion
    public void TC0112() {
        setup();
      List<String>statusList=jsonPath.getList("data.status");
        int count=0;
        for (String w:statusList) {
            if (w.equals("Active")){
                count++;
            }
        }
        System.out.println(count+" tane Active status var.");
        Assert.assertEquals(count,13);
    }

    @Test //search for id(4142) assertion
    public void TC0113() {
        setup();
        List<String>idList=jsonPath.getList("data.id");
        System.out.println(idList);
       // Assert.assertFalse( idList.contains("4142"));

        //softAssert.assertTrue(idList.contains(4142),"Aranan id YOK");
        softAssert.assertFalse(idList.contains(4142));
        softAssert.assertAll();

    }
    @Test //null data verification
    public void TC0114() {
        setup();
        HashMap<String ,String> map=response.as(HashMap.class);

        softAssert.assertFalse(map.containsValue(null));
        softAssert.assertFalse(map.containsKey(null));
        softAssert.assertAll();
        System.out.println(map.values());
        System.out.println(map.keySet());


    }

    @Test  //search for name("nuri duman") assertion
    public void TC0115() {
        setup();
        List<String>nameList=jsonPath.getList("data.name");
        System.out.println(nameList);
        Assert.assertFalse(nameList.contains("nuri duman"));

    }
}
