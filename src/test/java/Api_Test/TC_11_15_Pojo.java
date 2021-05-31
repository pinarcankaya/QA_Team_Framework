package Api_Test;


import Api_Pojos.ApiGoPojo;
import Api_Pojos.Datum;

import Utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TC_11_15_Pojo {
    Response response;
    String endPoint= "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper= new ObjectMapper();
    ApiGoPojo gorest;
    Datum datum;

    @Test
    public void TC_0111() throws JsonProcessingException {
        //female sayısı daha mı fazla
        response= JsonUtil.responseMethod(endPoint);
        // response.prettyPrint();

        gorest= objectMapper.readValue(response.asString(),ApiGoPojo.class);
        List<Datum> dataList= gorest.getData();

        int femaleCount=0;
        int maleCount=0;
        for (Datum dataGender: dataList) {
            // System.out.println(dataGender.getGender());
            if (dataGender.getGender().contains("Female")){
                femaleCount++;
            }else{
                maleCount++;
            }
        }
        System.out.println("female "+femaleCount);
        System.out.println("male "+maleCount);
        Assert.assertTrue(femaleCount>maleCount);
    }

    @Test
    public void TC_0112() throws JsonProcessingException {
        //statusu aktif olan kaç kişi var
        response= JsonUtil.responseMethod(endPoint);
        // response.prettyPrint();

        gorest= objectMapper.readValue(response.asString(),ApiGoPojo.class);
        List<Datum> dataList= gorest.getData();

        int count=0;
        for (Datum status: dataList){
            if (status.getStatus().contains("Active")){
                count++;
            }
        }System.out.println(count + " there are active status code");
        Assert.assertNotEquals(count,421);


    }

    @Test
    public void TC_0113() throws JsonProcessingException {
        //4142 id.si var mı
        response= JsonUtil.responseMethod(endPoint);
        // response.prettyPrint();

        gorest= objectMapper.readValue(response.asString(),ApiGoPojo.class);
        List<Datum> dataList= gorest.getData();
        //1. way
//        boolean idBoolean=false;
//        for (Datum id: dataList){
//            if (id.getId()== 4142){
//                idBoolean=true;
//
//            }Assert.assertEquals(idBoolean,false);
//        }
        //2. way
        List<Integer> allIdList = new ArrayList<>();

        for (Datum w : dataList) {
            allIdList.add(w.getId());
        }
        System.out.println(allIdList);
        Assert.assertFalse(allIdList.contains(4142));
    }

    @Test
    public void TC_0114() throws JsonProcessingException {

        ////null data verification
        //Bu test Case'in JhsonPath ile yapilmasi daha uygun
        response = JsonUtil.responseMethod(endPoint);
        //response.prettyPrint();

        gorest = objectMapper.readValue(response.asString(), ApiGoPojo.class);

        for (int i = 0; i <gorest.getData().size() ; i++) {
            Assert.assertNotEquals(gorest.getData().get(i).getGender(), null);
            Assert.assertNotNull(gorest.getData().get(i).getId());
            Assert.assertNotEquals(gorest.getData().get(i).getStatus(), null);
            Assert.assertNotEquals(gorest.getData().get(i).getEmail(), null);
            Assert.assertNotEquals(gorest.getData().get(i).getName(), null);
            Assert.assertNotEquals(gorest.getData().get(i).getEmail(), null);
            Assert.assertNotEquals(gorest.getData().get(i).getCreatedAt(), null);
        }

    }

    @Test
    public void TC_0115() throws JsonProcessingException {
        //search for name("nuri duman") assertion
        response = JsonUtil.responseMethod(endPoint);
        // response.prettyPrint();

        gorest= objectMapper.readValue(response.asString(),ApiGoPojo.class);
        List<Datum> dataList = gorest.getData();
        for (Datum nameSearch: dataList){
            Assert.assertFalse(nameSearch.getName().contains("nuri duman"));
        }
    }
}
