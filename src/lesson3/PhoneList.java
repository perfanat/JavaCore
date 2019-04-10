package lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneList {
    private static HashMap<String,String>phLis=new HashMap<>(Map.of(
            "123456", "Иванов",
            "123455", "Петров",
            "123454", "Сидоров",
            "123453", "Иванов"));

    public static void main(String[] args) {
        printList(phLis);
        System.out.println("--------");
        phLis.put ("123452", "Петров");
        printList(phLis);
        System.out.println("--------");
        findPhone("Иванов");
    }

    private static void printList(HashMap<String, String> hashMap){
        for(Map.Entry<String, String> e: hashMap.entrySet()) {
            System.out.println(e.getValue()+": "+e.getKey());
        }
    }

    private static void findPhone(String str){
        for(Map.Entry<String, String> e: phLis.entrySet()) {
            if (e.getValue().equals(str))
            System.out.println(e.getValue()+": "+e.getKey());
        }
    }
}
