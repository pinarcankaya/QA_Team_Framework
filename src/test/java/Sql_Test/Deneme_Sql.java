package Sql_Test;

import Utilities.DatabaseConnector;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Deneme_Sql {

    List<Map<String,String>> list1 = new ArrayList<>();
    @Test
    public void deneme1() throws SQLException {

        String query = "Select * from newschema.suppliers";

        list1= DatabaseConnector.getQueryAsAListOfMaps(query) ;
        System.out.println(list1);


    }
}
