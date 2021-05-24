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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TC_06_10_Pojo {

    ObjectMapper objectMapper = new ObjectMapper();
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    ApiGoPojo apiGoPojo;

    @Test //id unique assertion
    public void TC06() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
       apiGoPojo=objectMapper.readValue(response.asString(),ApiGoPojo.class);

       // List<Datum> list=apiGoPojo.getData();//for icine list ismiyle  de yazilabilir

        List<Integer> allIdList = new ArrayList<>();//id//int

        for (Datum w :apiGoPojo.getData()) {
            allIdList.add(w.getId());

        }
        System.out.println(allIdList.size());

        Set<Integer> idSet = new HashSet<>(allIdList);//unique data depolar
        System.out.println(idSet.size());
        Assert.assertEquals(allIdList.size(), idSet.size());

    }

    @Test //names are not NULL assertion
    public void TC07() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);


        for (Datum w : apiGoPojo.getData()) {
            System.out.println(w.getName());
            Assert.assertNotNull(w.getName());
            //Assert.assertFalse(w.getName().contains(null));//2.yol
        }


    }

    @Test//number of males assertion
    public void TC08() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);

        int count = 0;
        for (Datum w : apiGoPojo.getData()) {
            if (w.getGender().equals("Male")) {
                count++;
            }
        }
        System.out.println(count);//12

        Assert.assertNotEquals(count, 483);


    }

    @Test//number of females assertion
    public void TC09() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);


        int count = 0;
        for (Datum w : apiGoPojo.getData()) {
            if (w.getGender().equals("Female")) {
                count++;
            }
        }
        System.out.println(count);//9

        Assert.assertNotEquals(count, 897);


    }

    @Test //check dublicate names
    public void TC10() throws JsonProcessingException {
        response = JsonUtil.responseMethod(endPoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGoPojo.class);


        List<Datum> nameList = apiGoPojo.getData();
        System.out.println(nameList.size());

        //1. yol
        Set<String> dublicateSet = new HashSet<String>();//dublicate'e izin vermez

        for (Datum w : nameList) {
            dublicateSet.add(w.getName());
        }
        System.out.println(dublicateSet.size());
        Assert.assertEquals(nameList.size(), dublicateSet.size());


        //2.yol
//       // List<Datum> nameList = apiGoPojo.getData();
//      List<String> duplicateNameList=new ArrayList<>();
//
//        for (Datum w : nameList) {
//            if (!duplicateNameList.contains(w)) {  ///icinde bir onceki w degeri yoksa (!) ekle
//                duplicateNameList.add(w.getName());
//            }
//        }
//        System.out.println(duplicateNameList);
//        Assert.assertEquals(nameList.size(), duplicateNameList.size());


        ////3.yol
     //

//        List<String> allName = new ArrayList<>();
//
//        for (Datum w : apiGoPojo.getData()) {
//            allName.add(w.getName());
//        }
//
//        int numberOfDublicate = 0;
//        for (int i = 0; i < allName.size() - 1; i++) {
//            for (int j = i + 1; j < allName.size(); j++) {
//                if (allName.get(i).equals(allName.get(j))) {
//                    System.out.println(allName.get(i));
//                    numberOfDublicate++;
//                }
//            }
//
//        }
//        Assert.assertEquals(numberOfDublicate, 0);
//
   }


}
