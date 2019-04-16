package lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneList {

    private static Map<String, Set<String>>phLis=new HashMap<>();

    public static void main(String[] args) {
        addPhone ("Иванов", "123456");
        addPhone ("Петров", "123455");
        addPhone ("Сидоров", "123454");
        addPhone ("Иванов", "123453");
        addPhone ("Петров", "123452");
        printList(phLis);
        System.out.println("--------");
        System.out.println("Иванов - "+getPhone("Иванов"));
    }

    private static void addPhone(String name, String phone) {
        if (!phLis.containsKey(name)){
            phLis.put(name, new HashSet<>());
            }
        phLis.get(name).add(phone);

    }

    private static Set<String> getPhone(String name) {
        return phLis.get(name);
    }

    private static void printList(Map<String, Set<String>> hashMap){
        for (Map.Entry<String, Set<String>> e : hashMap.entrySet()) {
            for(String s : e.getValue()) {
                System.out.println(e.getKey()+" - "+s);
            }
        }
    }
}
