package Api_Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;


public class TC_01_05 {


    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;   ///de-serialization

    @Test   //TC_0101 status code assertion  ,//TC_0102 content-type assertion
    public void setup(){
        response = given().
                accept(ContentType.JSON). //hizli, okuma kolayligi var ,daha az yer kaplar
                when().
                get(endPoint) ;                       // get(ConfigurationReader.getProperty("endPoint"));

        response.
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).  // statusCode(200) ==> olarak yazilabilir
                contentType(ContentType.JSON);  // contentType("application/json"); ==>olarak yazilabilir
        json = response.jsonPath();
        //response.prettyPrint();
    }

    @Test
    public void TC0103(){  //all data count assertion --- Beklenen sonuc:1375
        setup();
        //1.yol
         int totalData=json.getInt("meta.pagination.total");
         Assert.assertNotEquals(totalData,1375);
         System.out.println(totalData);

        //2.yol  //2.yolda not equals kullanma sansimiz yok
//        response.then().
//                assertThat().
//                contentType(ContentType.JSON).
//                statusCode(HttpStatus.SC_OK).
//                body("meta.pagination.total", Matchers.equalTo(1611));


    }

        @Test//all page assertion
        public void TC0104() {
        setup();

        int pages=json.getInt("meta.pagination.pages");
            System.out.println(pages);
            Assert.assertEquals(pages,81);
        }

        @Test//id natural order assertion
        public void TC0105() {
        setup();
        List<Integer> idList=json.getList("data.id");
            System.out.println(idList);

            for (int i = 0; i <idList.size() ; i++) {
                for (int j = i+1; j <idList.size() ; j++) {
                    Assert.assertTrue(idList.get(i)<idList.get(j));  //8---11  //11---13
                }
            }

            //2.yol
            List<Integer> idList2=new ArrayList<>(idList);
            Collections.sort(idList2);//natural order'a gore siralar

            Assert.assertTrue(idList.equals(idList2));
    }




}
