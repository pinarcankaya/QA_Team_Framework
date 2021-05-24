package Api_Test;
import Utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class TC_16_20 {


    Response response;
    JsonPath json;
    String endPoint = "https://gorest.co.in/public-api/users/?page=2";

    @Test  //search for email("aliveli@gmail.com")assertion
    public void TC_16() {
        response= JsonUtil.responseMethod(endPoint);
        json=response.jsonPath();
        response.prettyPrint();


        List<String> emailList=json.getList("data.email");
        //
        for (String email : emailList) {
            if (email.equals("aliveli@gmail.com")) {
                System.out.println("Email var");

            }
            Assert.assertNotEquals(email, "aliveli@gmail.com");
        }


        //2.yol
        //Assert.assertFalse(emailList.contains("aliveli@gmail.com"));

    }

    @Test  //count emails "@gmail.com" assertion
    public void TC_17() {
        response= JsonUtil.responseMethod(endPoint);
        json=response.jsonPath();

        List<String> emailList=json.getList("data.email");
        System.out.println(emailList);

        int count=0;
        for (String email :emailList){
            if(email.contains("@gmail")){
                count++;
            }
        }
        System.out.println(count);
        Assert.assertNotEquals(count,15);//1

    }

    @Test//count data later than 2020 assertion
    public void TC_18() {
        response= JsonUtil.responseMethod(endPoint);
        json=response.jsonPath();
        // response.prettyPrint();

        List<String>  createddateList=json.getList("data.created_at");


        for(String  date : createddateList){
            String updateDate=createddateList.toString().substring(1,11).replace("-","");
            Assert.assertFalse(Integer.valueOf(updateDate)<2020);//2020'den kucuk date yok
        }
    }

    @Test  //counts surname begins with A and D assertion
    public void TC_19() {
        response=JsonUtil.responseMethod(endPoint);
        json=response.jsonPath();
        //response.prettyPrint();
        List<String> nameList=json.getList("data.name");
        int count=0;
        for (String w:nameList) {
            String arr[]=w.split(" ");
            System.out.println(arr[arr.length-1]);
            List<String>soyad=new ArrayList<>(Collections.singleton(arr[arr.length - 1]));
            for (String z:soyad) {
                if (z.charAt(0)=='A'|| z.charAt(0)=='D'){
                    count++;
                }
            }
        }
        System.out.println(count);
//        System.out.println(nameList);
//        int count=0;
//        for(String names : nameList){
//
//            String []surnames=names.split(" ");
//            String surnameFirstLetter=surnames[1].substring(0,1);
//            System.out.println(surnameFirstLetter);
//            if(surnameFirstLetter.equals("A") || surnameFirstLetter.equals("D")){
//                count++;
//            }
//            }
//        System.out.println(count);
//        Assert.assertNotEquals(count,327);
    }

    @Test  //updated data count assertion
    public void TC_20() {
        response=JsonUtil.responseMethod(endPoint);
        json=response.jsonPath();
        //  response.prettyPrint();
        List<String> createdDate=json.getList("data.created_at");
        List<String> updatedDate=json.getList("data.updated_at");
        System.out.println(createdDate.size());
        System.out.println(updatedDate.size()); // --all date uptadated



    }
}
