package Api_Test;

import Api_Pojos.ApiGoPojo;
import Api_Pojos.Datum;
import Utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC_01_05_Pojo {

    ObjectMapper objectMapper = new ObjectMapper();  //
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    ApiGoPojo apiGoPojo;

    @Test
    public void TC01_02() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        response.
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).  // statusCode(200) ==> olarak yazilabilir
                contentType(ContentType.JSON);

        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);
        int code = apiGoPojo.getCode();  //status code olarak kullanialbilir
        System.out.println(code);

    }

    @Test//all data count total data
    public void TC_03() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);

        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);

        int totalData = apiGoPojo.getMeta().getPagination().getTotal();

        System.out.println(totalData);//1470

        Assert.assertNotEquals(totalData, 1375);
    }

    @Test//all page assertion
    public void TC04() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);

        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);

        int totalPage = apiGoPojo.getMeta().getPagination().getPages();

        System.out.println(totalPage);

        Assert.assertNotEquals(totalPage, 20);


    }

    @Test//id natural order assertion
    public void TC02() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);
       // List<Integer> allId = new ArrayList<>();




        ////////////!!!!!DUZENLENECEK////////////
//        for (Datum w : apiGoPojo.getData()) {
//            allId.add(w.getId());
//            System.out.print(w.getId() + " ");
//        }
//
//
//        ///*****
//        for (int i = 0; i < apiGoPojo.getData().size(); i++) {
//            for (int j = i + 1; j < apiGoPojo.getData().size(); j++) {
//
//            }
//        }
//
//        //2.yol
//        List<Integer> idList2 = new ArrayList<>(allId);
//        Collections.sort(idList2);//natural order'a gore siralar
//        System.out.println(idList2);
//        Assert.assertTrue(allId.equals(idList2));


    }
}