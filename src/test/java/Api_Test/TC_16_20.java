package Api_Test;


import Utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;

public class TC_16_20 {

  Response response;
  JsonPath json;

   String  EndPoint1 = "https://gorest.co.in/public-api/users/?page=2";


    @Test
    public void setup() {

        response=given().
                accept(ContentType.JSON).
                when().
               // get(ConfigurationReader.getProperty("EndPoint"));
                get(EndPoint1);
        response.prettyPrint();

        json=response.jsonPath();


    }

    @Test //search for email("aliveli@gmail.com")assertion==YANLIŞ
    public void TC_0116() {
        setup();
        List<String>eMailList=json.getList("data.email");
        System.out.println(eMailList);
        System.out.println(eMailList.size());
        Assert.assertFalse(eMailList.contains("aliveli@gmail.com"));
    }

    @Test //count emails "@gmail.com" assertion==15
    public void TC0117() {
        setup();
        List<String >eMailList=json.getList("data.email");

        int countgmail=0;
        for (String w:eMailList) {
            System.out.println(w);
         String arr[]=w.split("@");
            System.out.println(arr[1]);
            if (arr[1].contains("gmail.com")){
                countgmail++;
            }
        }
        System.out.println("gmail.com olan mail sayisi="+ countgmail);
        Assert.assertNotEquals(countgmail,15);
    }

    @Test  //count data later than 2020 assertion(2020'den önce oluşturulmuş datalar)
    public void TC0118() {
        setup();
        List<String >creatDate=json.getList("data.created_at");

        int countYear=0;
        for (String w:creatDate) {
            System.out.println(w);
            String []arr= w.split("-");
            System.out.println(arr[0]);
            List<Integer >year=new ArrayList<>(Integer.parseInt(arr[0]));

            for (int z:year) {
                if (z<2021){
                    countYear++;
                }
            }
        }
        System.out.println(countYear);
        Assert.assertNotEquals(countYear,3);

    }

    @Test //A ve D ile başlayan soyadlarını say
    public void TC_0119() {
        setup();
        List<String>nameList=json.getList("data.name");
        int count=0;
        for (String w:nameList) {
            String arr[]=w.split(" ");
            System.out.println(arr[arr.length-1]);
            List<String>soyad=new ArrayList< >(Collections.singleton(arr[arr.length - 1]));
            for (String z:soyad) {
                if (z.charAt(0)=='A'|| z.charAt(0)=='D'){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test //kaç veri update edilmiş, update ile created.i karşılaştır
    public void TC0120() {
        setup();
        List<String>creatDate=json.getList("data.created_at");
        List<String>uptadeDate=json.getList("data.updated_at");

        System.out.println(creatDate);
        System.out.println(uptadeDate);

        System.out.println(!creatDate.equals(uptadeDate));
    }
}
