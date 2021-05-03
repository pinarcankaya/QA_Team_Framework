package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work01 {

    List<Map<String, String>> list1 = new ArrayList<>();


    @Test //#title 'i "B" ile baslayan filmleri sorgulayiniz  63 tane oldugunu assert ediniz
    public void soru01() {

        String query1 = "select count(title)\n" +
                "  from sakila.film\n" +
                "  where title like 'B%'";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("count(title)"));

      // Assert.assertTrue(list1.get(0).containsValue("63"), String.valueOf(63));
       Assert.assertEquals(list1.get(0).get("count(title)"),"63");


    }

    @Test//Suresi 90 ile 120 dakika arasinda olan filmleri kisadan uzuna dogru siralayiniz.90 dk olan 5 film oldugunu assert ediniz
    public void soru02() {
        String query2 = "select *\n" +
                "from sakila.film\n" +
                "where length between '90' and '120'\n" +
                "order by length asc;";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(list1);
        Assert.assertTrue(list1.get(0).containsValue("90"), String.valueOf(5));

    }

    @Test//--en uzun  film uzunlugunun 185 dk oldugunu assert ediniz
    public void soru03() {
        String query3 = "select max(length) as enuzunfilm,title\n" +
                "from sakila.film";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query3);
        System.out.println(list1);
        Assert.assertTrue(list1.get(0).containsValue("185"), String.valueOf(185));


    }

    @Test//---film suresinin ortalamasinin 115.272 oldugunu assert ediniz
    public void soru04() {
        String query4 = "select avg(length),title  \n" +
                "from sakila.film;";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(list1.size());
        Assert.assertTrue(list1.get(0).containsValue("115.272"), String.valueOf(115.272));//Test gecmedi Expected true but Actual false

    }

    @Test//film uzunlugu maximum olan degerden daha kucuk olanlari bulalim,max degerden daha kucuk olduklarini assert edelim
    public void soru05() {

        String query5="\n" +
                "select title,length\n" +
                "from sakila.film\n" +
                "where length < (select max(length)  \n" +
                "                    from sakila.film );";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query5);
        int maxSize=list1.size();
        Assert.assertEquals(list1.get(0).size(), maxSize);//gecmedi Actual ==>2 expected ==>990
    }


}