package lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneList {
    private static Map<String, Set<String>>phLis=new HashMap<>();

    public static void main(String[] args) {
        phLis.put("Иванов", new HashSet<>());
        phLis.put("Петров", new HashSet<>());
        phLis.put("Сидоров", new HashSet<>());
        phLis.get("Иванов").add("123456");
        phLis.get("Петров").add("123455");
        phLis.get("Сидоров").add("123454");
        phLis.get("Иванов").add("123453");
        printList(phLis);
        System.out.println("--------");
        phLis.get("Петров").add("123452");
        printList(phLis);
        System.out.println("--------");
        findPhone("Петров");
    }

    private static void printList(Map<String, Set<String>> hashMap){
        for (Map.Entry<String, Set<String>> e : hashMap.entrySet()) {
            for(String s : e.getValue()) {
                System.out.println(e.getKey()+" - "+s);
            }
        }
    }

    private static void findPhone(String str){
        for(Map.Entry<String, Set<String>> e: phLis.entrySet()) {
            if (e.getKey().equals(str))
                for(String s : e.getValue()) {
                    System.out.println(e.getKey()+" - "+s);
                }
        }
    }
}
