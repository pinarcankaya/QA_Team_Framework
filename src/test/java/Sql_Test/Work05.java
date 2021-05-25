package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work05 {
    List<Map<String ,String >>listMap=new ArrayList<>();
    List<Map<String, String>> listMap2 = new ArrayList<>();

    @Test //Employees tablosundan Region'u null olan en yasli iscinin Mr.Steven Buchanan oldugunu dogrulayiniz.
    public void soru13() {
      String query="select concat(title_of_courtesy,first_name,\" \",last_name)as ad_soyad,region, birth_date\n" +
              "FROM newschema.employees\n" +
              "where region is null\n" +
              "order by  birth_date asc\n" +
              "limit 1;";

      listMap= DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
        Assert.assertEquals(listMap.get(0).get("ad_soyad"),"Mr.Steven Buchanan");

   //  concat:sutunlari birlestirir.

    }

    @Test
    public void soru() {
        String query="select  ship_name,required_date\n" +
                "from newschema.orders\n" +
                "where ship_country='Mexico'\n" +
                "order by rand()\n" +
                "limit 3;";
        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
    }

    @Test//Orders tablosundan required_date'i Mart
    // ve ship_country'si  Germany olanlari listeleyiniz,sayisinin 8 oldugunu assert ediniz.
    public void soru14() {
        String query="select count(customer_id)\n" +
                "from newschema.orders\n" +
                "where month(required_date)=3 and ship_country='Germany';";
        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        Assert.assertEquals(listMap.get(0).get("count(customer_id)"),"8");
    }

    @Test // Orders tablosunda customer_id leri sevk edilme sureleri uzundan(sipped_date ile required_date arasindaki farka gore) kisaya dogru siralayiniz.
//En uzun surede sevk edilenin SUPRD oldugunu dogrulayiniz
    public void soru15() {
        String query="select customer_id,datediff(required_date,shipped_date) as sevk_suresi\n" +
                "from newschema.orders\n" +
                "order by sevk_suresi desc\n" +
                "limit 1;";
        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
        Assert.assertEquals(listMap.get(0).get("customer_id"),"SUPRD");
      //datediff:cikarma islemi yapar

    }
    @Test//customers tablosunda sehri London olan musteri sayisi, sehri Berlin olan musteri saysindan fazladir.
    public void soru16() {
        String query = "select count(*)\n" +
                "from newschema.customers\n" +
                "where city='London'";

        String query2="select count(*)\n" +
                "from newschema.customers\n" +
                "where city='Berlin'";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        listMap2=DatabaseConnector.getQueryAsAListOfMaps(query2);

        int text1=Integer.parseInt(listMap.get(0).get("count(*)"));
        int text2=Integer.parseInt(listMap2.get(0).get("count(*)"));
        System.out.println(text1);
        System.out.println(text2);
        Assert.assertTrue(text1>text2);

    }

    @Test//Customers tablosunda 69 farkli city vardir
    public void soru17() {
        String query = "select count(distinct(city)) as city\n" +
                "from newschema.customers";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);

        Assert.assertEquals(listMap.get(0).get("city"),"69");

//count olmadan cozum
//        String query = "select distinct(city) as city\n" +
//                "from newschema.customers";
//
//        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
//        System.out.println(listMap.size());
        //       Assert.assertEquals(listMap.size(),69);

    }

    @Test//Products tablosunda kac farkli ürün vardir.(77 ürün oldugunu dogrulayiniz)
    public void soru18() {
        String query = "select count(distinct(product_name)) as urun\n" +
                "from newschema.products";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap.get(0).get("urun"));
        Assert.assertEquals(listMap.get(0).get("urun"),"77");

    }
}
