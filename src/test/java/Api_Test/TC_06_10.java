package Api_Test;

import Utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class TC_06_10 {

    Response response;
    JsonPath jsonPath;

    @Test
    public void setUp() {
        response = given().
                accept(ContentType.JSON).
                when().
                get(ConfigurationReader.getProperty("EndPoint"));
        response.prettyPrint();
        jsonPath = response.jsonPath();
    }

    @Test//id unique assertion
    public void TC0106() {
        setUp();
        List<Integer> idAll = jsonPath.getList("data.id");
        System.out.println(idAll.size() + " Tane List'ten gelen id var");

    //1. yol
    //        for (int i = 0; i < idAll.size(); i++) {
    //            for (int j = i + 1; j < idAll.size(); j++) {
    //                Assert.assertTrue(idAll.get(i) != idAll.get(j));
    //            }
    //        }


        //2. yol
        Set<Integer> idSet = new HashSet<>(idAll);
        Assert.assertEquals(idAll.size(), idSet.size());
        System.out.println(idSet.size() + " Tane Set'ten gelen id var");
    }

    @Test//names are not NULL assertion
    public void TC0107() {
        setUp();
        // 2) NULL olan name var mı, varsa hangi page hangi id?
        List<String> nameList = jsonPath.getList("data.name");
        Assert.assertFalse(nameList.contains(null));
        System.out.println(nameList);

    }

    @Test//number of males assertion
    public void TC0108_TC0111() {
        setUp();
        List<String> genderList = jsonPath.getList("data.gender");
        //1. yol
        int count = 0;
        for (int i = 0; i < genderList.size(); i++) {
            if (genderList.get(i).equals("Male")) {
                count++;

            }
        }

        System.out.println(count + ": " + "Tane Male var");
        //2. yol
        int countMale = 0;
        for (String male : genderList) {
            if (male.equals("Male")) {
                countMale++;
            }
        }
        System.out.println(countMale + " Tane Male var");
    }

    @Test//number of females assertion
    public void TC0109() {
        setUp();
        List<String> genderList = jsonPath.getList("data.gender");

        int countFemale = 0;
        for (String male : genderList) {
            if (male.equals("Female")) {
                countFemale++;
            }
        }
        System.out.println(countFemale + " Tane Female var");
    }

    @Test//check dublicate names
    public void TC0110() {
        setUp();
        // 1. yol
        List<String> nameList = jsonPath.getList("data.name");
        List<String> duplicateNameList = new ArrayList<>();

        for (String name : nameList) {
            if (!duplicateNameList.contains(name)) {
                duplicateNameList.add(name);
            }
        }
        System.out.println(duplicateNameList);
        Assert.assertEquals(nameList.size(), duplicateNameList.size());//sizlari ayniysa dublicate yok demek
        //2. yol
        Set<String> dublicateSet = new HashSet<>(nameList);

        System.out.println(dublicateSet.size());
        Assert.assertEquals(nameList.size(), dublicateSet.size());
    }
}
