package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work02 {

    List<Map<String,String>> list=new ArrayList<>();

   @Test
   public void soru04(){
       String query1="select  count(contact_title) as total\n" +
               "from newschema.customers\n" +
               "where contact_title='Owner' and region is null;";
       list= DatabaseConnector.getQueryAsAListOfMaps(query1);
       System.out.println(list.get(0).get("total"));
       //Assert.assertEquals(list.get(0).get("total"),"13");

   }

    @Test
    public void soru5() {
       String query2="select count(country) as totalAlmanya\n" +
               "from newschema.suppliers join newschema.products\n" +
               "on suppliers.supplier_id=products.supplier_id\n" +
               "where country='Germany'";
       list=DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(list.get(0).get("totalAlmanya"));
      // Assert.assertEquals(list.get(0).get("totalAlmanya"),9);


    }

    @Test //her bir category id'ye göre kaç tane ürün olduğunu bulun ve
    // urun sayisi en az olan katagorinin 5 oldugunu dogrulayiniz
    public void soru6() {

       String query3="select category_id,count(product_name) as urun_sayisi\n" +
               "from newschema.products\n" +
               "group by category_id\n" +
               "order by urun_sayisi asc";
       list=DatabaseConnector.getQueryAsAListOfMaps(query3);
        System.out.println(list.get(0).get("urun_sayisi"));
        Assert.assertEquals(list.get(0).get("urun_sayisi"),"5");


    }

    @Test
    public void soru7() {
       String query4="select product_name,product_id \n" +
               "from newschema.products \n" +
               "where product_id=(select max(product_id) from newschema.products);";
        list=DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(list.get(0).get("product_name"));
        Assert.assertEquals(list.get(0).get("product_name"),"Original Frankfurter grüne Soße");
    }

}
