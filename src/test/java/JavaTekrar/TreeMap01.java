package JavaTekrar;

import org.testng.annotations.Test;

import java.util.TreeMap;

public class TreeMap01 {


    @Test
    public void test() {
        /* TreeMap:
        ==>1) TreeMap key'lerde null kullanmaya musaade etmez ama value'larda istedigimiz kadar null kullanmaya musaade eder
        ==> 2) TreeMap elemanlarini console'a yazdirmak istediginizde key'ler natural order'a gore siralanir.
        ==>3) TreeMap en yavaslaridir
         */
        TreeMap<String, Integer> tMap = new TreeMap<>();
        tMap.put("Ali", null);
        tMap.put("Veli", 1);
        tMap.put("Mahmut", 2);
        tMap.put("Ramazan", 3);
       // tMap.put(null,4);  //java.lang.NullPointerException
        tMap.put("",5);
        System.out.println(tMap);//{Ali=null, Mahmut=2, Ramazan=3, Veli=1}






    }
}
