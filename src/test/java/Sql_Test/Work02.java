package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work02 {

    List<Map<String,String>> listMap =new ArrayList<>();

   @Test//SORU4:Custemers tablosundan ,contact title'i "Owner" olan kisilerin ,
   // region degerleri null olanlarin sayisini bulunuz
   public void soru04(){
       String query1="select  count(contact_title) as total\n" +
               "from newschema.customers\n" +
               "where contact_title='Owner' and region is null;";
       listMap = DatabaseConnector.getQueryAsAListOfMaps(query1);
       System.out.println(listMap.get(0).get("total"));
       Assert.assertEquals(listMap.get(0).get("total"),"13");

   }

    @Test  //SORU5:SUPPLIERS,PRODUCTS tablosundan, ulkesi Germany olanlarin
    // product name'lerinin 9 tane oldugunu assert ediniz
    public void soru5() {
       String query2="select count(country) as totalAlmanya\n" +
               "from newschema.suppliers join newschema.products\n" +
               "on suppliers.supplier_id=products.supplier_id\n" +
               "where country='Germany'";
       listMap =DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(listMap.get(0).get("totalAlmanya"));
       Assert.assertEquals(listMap.get(0).get("totalAlmanya"),9);


    }

    @Test //SORU 6:Her bir category_id'ye göre kaç tane ürün olduğunu bulun ve
    // urun sayisi en az olan katagorinin 5 oldugunu dogrulayiniz
    public void soru6() {

       String query3="select category_id,count(product_name) as urun_sayisi\n" +
               "from newschema.products\n" +
               "group by category_id\n" +
               "order by urun_sayisi asc";
       listMap =DatabaseConnector.getQueryAsAListOfMaps(query3);
        System.out.println(listMap.get(0).get("urun_sayisi"));
        Assert.assertEquals(listMap.get(0).get("urun_sayisi"),"5");


    }

    @Test  //SORU 7:product_id'si en buyuk olan urunun product_name'inin Original Frankfurter grüne Soße oldugunu dogrulayiniz
    public void soru7() {
       String query4="select product_name,product_id \n" +
               "from newschema.products \n" +
               "where product_id=(select max(product_id) from newschema.products);";
        listMap =DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(listMap.get(0).get("product_name"));
        Assert.assertEquals(listMap.get(0).get("product_name"),"Original Frankfurter grüne Soße");
    }

    @Test
    public void test9() {
       String query="select suppliers.supplier_id\n" +
               " from newschema.suppliers join newschema.products \n" +
               "on suppliers.supplier_id=products.supplier_id\n" +
               "where region='OR';";
       listMap =DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
          for (Map<String,String > w: listMap){
              System.out.println(w.get("supplier_id"));
              Assert.assertEquals(w.get("supplier_id"),"16");
          }
          Assert.assertEquals(listMap.size(),3);





    }
}
