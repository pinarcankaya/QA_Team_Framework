package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work04 {

    List<Map<String, String>> listMap = new ArrayList<>();
//'A' ile baslayan company lerdeki kisilerin isimlerini(contact_name) ve unvanlarini(contact_title) listeleyiniz.
// Bu listenin icinde Maria Anders oldugunu dogrulayiniz.
    @Test
    public void soru09() {
        String query = "select contact_name,contact_title\n" +
                "from newschema.customers\n" +
                "where company_name like 'A%'";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap.get(0).get("contact_name"));
        Assert.assertEquals(listMap.get(0).get("contact_name"),"Maria Anders");

        for (Map<String, String> w:listMap){
            w.get("contact_name").contains("Maria Anders");
        }


    }

    @Test//Unvani Marketing Manager olmayanlari listeleyiniz,sayisinin 74 oldugunu assert ediniz.
    public void soru10() {
        String query = "select product_name\n" +
                "from newschema.products=(select min(unit_price) from newschema.products);";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
    }

    @Test//Stok miktari(units_in _stock) ortalamanin uzerinde olan ürünlerin isimlerini yazdiriniz,sayisinin  25 oldugunu assert ediniz
    public void soru11() {
    }
}