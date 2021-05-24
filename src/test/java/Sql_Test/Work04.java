package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work04 {

    List<Map<String,String>> listMap=new ArrayList<>();
    @Test
    public void Soru9() {


        String query = "select company_name,contact_name,contact_title\n" +
                "from newschema.customers \n" +
                "where company_name like 'A%'";
        listMap= DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap.get(0).get("contact_name"));
        Assert.assertEquals(listMap.get(0).get("contact_name"),"Maria Anders");


    }

    @Test
    public void soru10() {

        String query="select count(contact_title)\n" +
                "from newschema.customers   \n" +
                "where contact_title !='Marketing Manager'";

        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
        Assert.assertNotEquals(listMap.get(0).get("count(contact_title)"),79);


    }

    @Test
    public void soru11() {

        String query="select count(product_name)\n" +
                "from newschema.products\n" +
                "where units_in_stock>(select avg(units_in_stock)\n" +
                "                        from newschema.products )";

        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
        Assert.assertEquals(listMap.get(0).get("count(product_name)"),"25");



    }

    @Test
    public void soru12() {
        String query=" select product_name\n" +
                " from newschema.products\n" +
                " where unit_price =(select min(unit_price) from  newschema.products);";

        listMap=DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(listMap);
        Assert.assertEquals(listMap.get(0).get("product_name"),"Geitost");
    }
}
