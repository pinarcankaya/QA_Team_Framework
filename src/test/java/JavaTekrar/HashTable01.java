package JavaTekrar;

import org.testng.annotations.Test;

import java.util.Hashtable;

public class HashTable01 {

    @Test
    public void test() {

        /* HashTable: HashMap ile hemen hemen aynidir
         ==> HashTable console'a yazdirildiginda HashMap gibi rastgele siralamada yazdirilir
         *  Farklari:
         ==> HashTable key ve value'larda null'a musaade etmez.
         ==>HashTable thread safe'dir.Bir kac isi ayni anda yapabilir.
         ==> HashTable daha yavastir.
         ==>Ayni key degerini tekrar kullanirsaniz son value'yu kabul eder
         ==>HashMap'de kullandigimiz methodlarin tamami HashTable'da da kullanilir
         */

        Hashtable<Integer, String> hTable = new Hashtable<>();
        hTable.put(1,"Adana");
        hTable.put(2, "Ankara");
        hTable.put(7, "Antalya");
        hTable.put(82,"");//"" null olmadigindan problem yok
       // hTable.put(null, "");//Run Time Error verir NullPointerException
        //hTable.put(100, null);//Run Time Error verir NullPointerException

        System.out.println(hTable); //{7=Antalya, 82=, 2=Ankara, 1=Adana}












    }
}
