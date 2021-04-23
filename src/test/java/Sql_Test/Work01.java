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
    public void soru1() {

        String quer1 = "  select count(title)   ----javada assert et \n" +
                "  from film\n" +
                "  where title like 'B%'";


    }

    @Test//Suresi 90 ile 120 dakika arasinda olan filmleri kisadan uzuna dogru siralayiniz.90 dk olan 5 film oldugunu assert ediniz
    public void soru2() {
        String query2 = "select *\n" +
                "from film\n" +
                "where length between '90' and '120'\n" +
                "order by length asc;";

    }

    @Test//--en uzun  film uzunlugunun 185 dk oldugunu assert ediniz
    public void soru0201() {
        String query0201 = "select max(length) as enuzunfilm,title\n" +
                "from film";


    }

    @Test//---film suresinin ortalamasinin 115.272 oldugunu assert ediniz
    public void soru0202() {
        String query3 = "select avg(length),title  \n" +
                "from film;";


    }

    @Test//film uzunlugu maximum olan degerden daha kucuk olanlari bulalim,max degerden daha kucuk olduklarini assert edelim
    public void testName() {

        String query4="\n" +
                "select title,length\n" +
                "from sakila.film\n" +
                "where length < (select max(length)  \n" +
                "                    from sakila.film );";
        list1=DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(list1);
    }


}