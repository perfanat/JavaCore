package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Collection {

    // начальный список
    private static ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(
            "стол", "стул", "диван", "стол", "кресло", "диван", "стол", "тумба", "диван", "шкаф", "стул", "диван"));

    // список уникальных слов
    private static Map<String, Integer> resList = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Начальный список: ");
        printArr(arrayList);
        System.out.println("\n-------------");
        checkArr(arrayList);
        System.out.println("Список уникальных слов: ");
        printMap(resList);
    }

    // метод печати списка
    private static void printArr(ArrayList<String> arr){
        for (String s : arr) {
            System.out.print(s+" ");
        }
    }

    // метод печати уникального списка
    private static void printMap(Map<String, Integer> arr){
        for(Map.Entry<String, Integer> e: arr.entrySet()) {
            System.out.print(e.getKey() + "-"+e.getValue()+" ");
        }
    }

    // метод создания списка уникальных слов
//    private static void checkArr(ArrayList<String> arr){
//        for (String s : arr) {
//            int count = 0;
//            for (String c:arr) {
//                if (c.equals(s)){
//                    count++;
//                }
//            }
//            resList.put(s, count);
//        }
//    }
    private static void checkArr(ArrayList<String> arr){
        for (String s : arr) {
            resList.putIfAbsent(s, 0);
            int count = resList.get(s);
            resList.put(s, ++count);
        }
    }
}
