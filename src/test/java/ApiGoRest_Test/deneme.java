package ApiGoRest_Test;



import Utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class deneme {

    String endPoint;
    Response response;
    JsonPath json;

    {
        endPoint = "https://gorest.co.in/public-api/users/";
    }

    @Test
    public void statusCodeAssertion() {

        //status code assertion
        response = given().
                accept(ContentType.JSON).
                when().
                get(endPoint);
        //   response.prettyPrint();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);

        json = response.jsonPath();
        System.out.println(json.getInt("meta.pagination.total"));

    }

    @Test
    public void contentTypeCheck() {

        //content-type assertion
        response = given().
                accept(ContentType.JSON).
                when().
                get(endPoint);


        response.then().assertThat().
                statusCode(200).
                contentType("application/json");

    }

    @Test
    public void total() {

        //content-type assertion
        response = given().
                accept(ContentType.JSON).
                when().
                get(endPoint);

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType("application/json");

    }

    @Test
    public void pageTest() {

        //all page assertion
        response = given().
                accept(ContentType.JSON).
                when().
                get(endPoint);
        json = response.jsonPath();
        int pages = json.getInt("meta.pagination.pages");
        // System.out.println(pages);
        Assert.assertTrue(pages == 73);

        //   response.prettyPrint();


    }

    @Test
    public void idOrder() {

        //id natural order assertion
        response = JsonUtil.responseMethod(endPoint);

        json = response.jsonPath();
        // response.prettyPrint();

        List<Integer> allId = json.getList("data.id");
        System.out.println(allId);

        for (int i = 0; i < allId.size() - 1; i++) {
            for (int j = i + 1; j < allId.size(); j++) {
                Assert.assertTrue(allId.get(j) - allId.get(i) >= 1);
            }
        }


    }

    @Test
    public void idUnique() {

        //id unique assertion
        response = JsonUtil.responseMethod(endPoint);

        json = response.jsonPath();
        //  response.prettyPrint();

        List<Integer> allId = json.getList("data.id");
        System.out.println(allId);

        for (int i = 0; i < allId.size() - 1; i++) {
            for (int j = i + 1; j < allId.size(); j++) {
                Assert.assertTrue(allId.get(i) != allId.get(j));
            }
        }


    }

    @Test
    public void nameNull() {

        //names are not NULL assertion
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> names = json.getList("data.name");
        System.out.println(names);
        Assert.assertTrue(!names.contains(null));


//        for (String w : names) {
//            Assert.assertTrue(!w.equals("NULL"));
//        }
//
    }

    @Test
    public void gender() {
        //number of males assertion=12
        // //number of females assertion=8
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();




        List<String> maleGenders = json.getList("data.findAll{it.gender.equals(\"Male\")}.id");
        // System.out.println(maleGenders.size());
        Assert.assertTrue(maleGenders.size()==12);
        //  System.out.println(maleGenders);

        List<String> genders = json.getList("data.gender");
        //  System.out.println(genders);
        int numberOfMale = 0;

        for (String w : genders) {
            if (w.equals("Male")) {
                numberOfMale++;
            }
        }

        Assert.assertTrue(numberOfMale == 12);

        int numberOfFemale = 0;
        for (String w : genders) {
            if (w.equals("Female")) {
                numberOfFemale++;
            }
        }

        Assert.assertTrue(numberOfFemale == 8);
        Assert.assertFalse(numberOfFemale > numberOfMale);
    }

    @Test
    public void duplicateTest() {
        //check dublicate names
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> names = json.getList("data.name");
        int numberOfDublicate = 0;
        for (int i = 0; i < names.size() - 1; i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    System.out.println(names.get(i));
                    numberOfDublicate++;
                }
            }
        }
        System.out.println(numberOfDublicate);
        Assert.assertFalse(numberOfDublicate == 0);

    }

    @Test
    public void actifPerson() {
        //active status count assertion
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();
        int numberOfActive = 0;
        List<String> actives = json.getList("data.status");
        for (String w : actives) {
            if (w.equals("Active")) {
                numberOfActive++;
            }
        }
        //  System.out.println(numberOfActive);
        Assert.assertTrue(numberOfActive == 14);

        //with Groovy
        List<String> actives2 = json.getList("data.findAll{it.status.equals(\"Active\")}.status");

        Assert.assertTrue(actives2.size()==14);

    }

    @Test
    public void idSearch() {

        //search for id(41) assertion
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<Map<String, Object>> mydata = json.getList("data");

        System.out.println(mydata);
        List<Integer> allId = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < mydata.size(); i++) {
            allId.add((Integer) mydata.get(i).get("id"));

        }

        for (Integer w :
                allId) {
            Assert.assertTrue(w != 41);
        }

        // court chemin
        List<Integer> idAll = json.getList("data.id");
        Assert.assertTrue(idAll.contains(41));

    }

    @Test
    public void dataIsNull() {

        //null data verification
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<Map<String, Object>> mydata = json.getList("data");
        System.out.println(mydata);


        for (Map mydataMap : mydata) {
            for (Object key : mydataMap.keySet()) {
                System.out.println(mydataMap.get(key));
                Assert.assertTrue(mydataMap.get(key.toString())!=null);
            }
        }
    }
    @Test
    public void searchName() {

        //search for name("nuri") assertion
        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> names = json.getList("data.name");
        Assert.assertFalse(names.contains("Nuri"));


        //2éme chemin
        for (String w : names) {
            Assert.assertFalse(w.equals("Nuri"));
        }
    }

    @Test
    public void mail() {


        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();


        List<String> mails = json.getList("data.email");
        for (String w : mails) {
            Assert.assertTrue(!w.equals("aliveli@gmail.com"));

        }


        //count emails "@gmail.com" assertion
        int gmailCount = 0;
        for (String w : mails) {
            if (w.contains("gmail.com")) {
                gmailCount++;
            }
        }

        Assert.assertTrue(gmailCount == 0);


    }

    @Test
    public void startsWith() {

        //counts name begins with A or D assertion

        response = JsonUtil.responseMethod(endPoint);
        json = response.jsonPath();

        List<String> names = json.getList("data.name");
        int startsWithA_D=0;
        for (String w : names) {
            if(w.startsWith("A") || w.startsWith("D")) {
                startsWithA_D++;
            }
        }

        Assert.assertTrue(startsWithA_D==4);
    }
}

