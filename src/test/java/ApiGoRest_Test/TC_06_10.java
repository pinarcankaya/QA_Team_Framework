package ApiGoRest_Test;

import Utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TC_06_10 {


    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @Test  //id unique assertion
    public void TC06() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<Integer> idList = json.getList("data.id");
        for (int i = 0; i < idList.size(); i++) {
            for (int j = i + 1; j < idList.size(); j++) {

                Assert.assertTrue(idList.get(i) != idList.get(j));
            }
        }
        //2.yol
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>(idList);
        Assert.assertEquals(idList.size(), linkedHashSet.size());

    }

    @Test  //names are not NULL assertion
    public void TC07() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        List<String> nameList = json.getList("data.name");
        // System.out.println(nameList);
        Assert.assertTrue(!nameList.contains(null));
//        for(String name : nameList){
//            Assert.assertFalse(name.contains("null"));
//        }

    }

    @Test  //names are not NULL assertion
    public void TC08() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> genderList = json.getList("data.gender");

        int countMale = 0;
        for (String male : genderList) {
            if (male.equals("Male")) {
                countMale++;
            }
        }
        System.out.println(countMale);
        Assert.assertTrue(countMale == 10);
        //2.yol
        // List<String> maleGenders = json.getList("data.findAll{it.gender.equals(\"Male\")}.id");
        // System.out.println(maleGenders.size());



        int countFemale = 0;
        for (String female : genderList) {
            if (female.equals("Female")) {
                countFemale++;
            }
        }
        System.out.println(countFemale);
        Assert.assertTrue(countFemale == 10);

        //TC11
        Assert.assertFalse(countFemale>countMale);
    }



    @Test
    public void TC10() {
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> nameList=json.getList("data.name");
        System.out.println(nameList.size());

        List<String> duplicateNameList=new ArrayList<>();

        for(String name :nameList){
           if(!duplicateNameList.contains(name)){
               duplicateNameList.add(name);
           }
        }
        System.out.println(duplicateNameList.size());
        Assert.assertEquals(nameList.size(),duplicateNameList.size());
    }

}