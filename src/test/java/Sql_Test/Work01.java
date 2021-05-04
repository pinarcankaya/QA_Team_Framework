package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Work01 {

    List<Map<String, String>> list1 = new ArrayList<>();


    @Test //#title 'i "B" ile baslayan filmleri sorgulayiniz  63 tane oldugunu assert ediniz
    public void soru01() {

        String query1 = "select count(title)\n" +
                "  from sakila.film\n" +
                "  where title like 'B%'";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("count(title)"));

      Assert.assertEquals(list1.get(0).get("count(title)"),"63");


    }

    @Test//Suresi 90 ile 120 dakika arasinda olan filmleri kisadan uzuna dogru siralayiniz.90 dk olan 5 film oldugunu assert ediniz
    public void soru02() {
        String query2 = "select length\n" +
                "from sakila.film\n" +
                "where length between '90' and '120'\n" +
                "order by length asc;";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(list1);

        int count=0;
        for ( Map<String, String> w : list1) {
            if (w.get("length").equals("90"))
                count++;

        }
        System.out.println(count);
       Assert.assertEquals(count ,5);

    }

    @Test//--en uzun  film uzunlugunun 185 dk oldugunu assert ediniz
    public void soru03() {
        String query3 = "select max(length) as enuzunfilm,title\n" +
                "from sakila.film";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query3);
        System.out.println(list1.get(0).get("enuzunfilm"));
        Assert.assertEquals(list1.get(0).get("enuzunfilm"),"185");


    }

    @Test//---film suresinin ortalamasinin 115.272 oldugunu assert ediniz
    public void soru04() {
        String query4 = "select avg(length),title  \n" +
                "from sakila.film;";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(list1.size());
         Assert.assertEquals(list1.get(0).get("avg(length)"),"115.2720");

    }

    @Test//film uzunlugu maximum olan degerden daha kucuk olanlari bulalim,max degerden daha kucuk olduklarini assert edelim
    public void soru05() {

        String query5="\n" +
                "select length\n" +
                "from sakila.film\n" +
                "where length < (select max(length)  \n" +
                "                    from sakila.film );";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query5);
        System.out.println(list1);
      //  int maxSize=list1.size();


        for (Map<String, String> w: list1) {

        }

    }

}