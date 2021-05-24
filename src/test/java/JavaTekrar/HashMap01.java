package JavaTekrar;

import jdk.internal.org.objectweb.asm.Handle;
import org.testng.annotations.Test;

import java.util.HashMap;

public class HashMap01 {
    @Test
    public void test() {


        // Map bir interface'dir. Map interface'inin 3 tane child class'i vardir
    /* 1)HashMap       2)HashTable     3)TreeMap

    1) HASHMAP: HasMap key-value yapisini kullanir.
    ==> Key ve value programci tarafindan yazilir.
    ==> Key ve value'da null degeri kullanilabilir. Key'de 1'den fazla null degeri kullanilirsa,
     Java son kullanilan null'i kabul eder.
    ==> Value'da 1'den fazla null degeri kullanilabilir.
    ==> HashMap console'a yazdirildiginda hem key degerleri hem de
    value degerleri aralarina = sembolu konularak yazdirilir
    ==> HashMap'ler ekrana yazdirirken rastgele siralama yapar
    ==> HasMap map'ler arasinda en hizlisidir.
    ==> HashMap thread safe'degildir
     */
        HashMap<String, String> b = new java.util.HashMap<>();

        //put methodu--------
        b.put("Turkiye","Ankara");
        b.put("Fransa","Paris");
        b.put("ABD","Washington");
        b.put("Almanya","Berlin");
        //b.put(null,"Adana");
//        b.put("Kanada",null);
  //     b.put("Turkiye","Istanbul");
    //  b.put("Hollanda","Berlin");
        //System.out.println(b);

        //remove------------------------------------
       // b.remove(null);
//         System.out.println(b.remove("Turkiye"));
//        System.out.println(b);

        //get()----------------------
       // System.out.println(b.get("Fransa"));


        //keyset(),values()----------------------------------

//        System.out.println(b.keySet());
//        System.out.println(b.values());
//
//        System.out.println(b.size());

       //containsKey(),containsValue()--------------------------------

//        System.out.println(b.containsKey("Turkiye"));
//        System.out.println(b.containsKey("Italya"));
//
//        System.out.println(b.containsValue("Ankara"));
//        System.out.println(b.containsValue("Roma"));

       //replace()--------------------------------------------

//       b.replace("ABD","Newyork") ;
//        System.out.println(b);

       // entrySet()---------------------------------
        System.out.println(b.entrySet());

        //

        System.out.println(b.hashCode());



    }
}
