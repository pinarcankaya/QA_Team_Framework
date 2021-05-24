package Sql_Test;

import Utilities.DatabaseConnector;
import Utilities.JsonUtil;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Work01 {

    List<Map<String, String>> listMap = new ArrayList<>();


    @Test //#title 'i "B" ile baslayan filmleri sorgulayiniz  63 tane oldugunu assert ediniz
    public void soru01() {

        String query1 = "select count(title)\n" +
                "  from sakila.film\n" +
                "  where title like 'B%'";
        listMap =DatabaseConnector.getQueryAsAListOfMaps(query1);



    }

    @Test//Suresi 90 ile 120 dakika arasinda olan filmleri kisadan uzuna dogru siralayiniz.90 dk olan 5 film oldugunu assert ediniz
    public void soru02() {
        String query2 = "select *\n" +
                "from sakila.film\n" +
                "where length between '90' and '120'\n" +
                "order by length asc;";
        listMap =DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(listMap);

    }

    @Test//--en uzun  film uzunlugunun 185 dk oldugunu assert ediniz
    public void soru03() {
        String query3 = "select max(length) as enuzunfilm,title\n" +
                "from sakila.film";
        listMap =DatabaseConnector.getQueryAsAListOfMaps(query3);
        System.out.println(listMap);


    }

    @Test//---film suresinin ortalamasinin 115.272 oldugunu assert ediniz
    public void soru04() {
        String query4 = "select avg(length),title  \n" +
                "from sakila.film;";
        listMap =DatabaseConnector.getQueryAsAListOfMaps(query4);
        System.out.println(listMap);

    }

    @Test//film uzunlugu maximum olan degerden daha kucuk olanlari bulalim,max degerden daha kucuk olduklarini assert edelim
    public void soru05() {

        String query5 = "select title,length\n" +
                "from sakila.film\n" +
                "where length < (select max(length) from sakila.film );";
        listMap = DatabaseConnector.getQueryAsAListOfMaps(query5);
        //List<Integer> customerIdList = customerIdNameMap.keySet()
        //                                                    .stream()
        //                                                   .collect(Collectors.toList());

       // List<String >filmLenght=Integer.valueOf(listMap.)

//        int countEnUzunFilmler = 0;
//        int countDigerleri = 0;
//        for (Map<String, String> w : listMap) {
//            System.out.print(w.get("length")+" ");
//            if (w.get("length").equals("185")) {
//                countEnUzunFilmler++;
//            }else {
//                countDigerleri++;
//            }
//        }
//        System.out.println(countEnUzunFilmler);
//        System.out.println(countDigerleri);

    }


}