package lesson3;

import java.util.ArrayList;
import java.util.Arrays;

public class Collection {

    // начальный список
    private static ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(
            "стол", "стул", "диван", "стол", "кресло", "диван", "стол", "тумба", "диван", "шкаф", "стул", "диван"));

    // список уникальных слов
    private static ArrayList<String> resList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Начальный список: ");
        printArr(arrayList);
        System.out.println("\n-------------");
        checkArr(arrayList);
        System.out.println("Список уникальных слов: ");
        printArr(resList);
    }

    // метод печати списка
    private static void printArr(ArrayList<String> arr){
        for (String s : arr) {
            System.out.print(s+" ");
        }
    }

    // метод создания списка уникальных слов
    private static void checkArr(ArrayList<String> arr){
        for (String s : arr) {
            int count = 0;
            for (String s1 : arr) {
                if (s.equals(s1)) {
                    count++;
                }
            }
            if (!resList.contains(s+"-"+count)) {
                resList.add(s+"-"+count);
            }
        }
    }
}
