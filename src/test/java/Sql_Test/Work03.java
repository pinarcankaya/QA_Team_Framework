package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work03 {


    /*--deniz ürünleri kategorisine(seafood) ait ilk ürünun ismini ve category_namerini getirelim
--toplamda 12 adet ürün oldugunu dogrulayalim::*/
    List<Map<String, String>> listMap = new ArrayList<>();

    @Test
    public void soru01() {
        String query = "select product_name,category_name,count(category_name) as total_name\n" +
                "from newschema.categories\n" +
                "join newschema.products\n" +
                "on categories.category_id=products.category_id\n" +
                "where category_name='Seafood'";

        listMap = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap.get(0).get("total_name"));//12
        Assert.assertEquals(listMap.get(0).get("total_name"), "12");
        System.out.println(listMap.get(0).get("product_name"));
        Assert.assertEquals(listMap.get(0).get("product_name"), "Ikura");

    }
    /*suppliers region.u 'OR' olan kisilerin suppliers id'si 16 oldugunu dogrulayiniz ve
    ve toplam 3 adet ürün listelendigini dogrulayiniz.*/


}
