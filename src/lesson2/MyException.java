package lesson2;

class MyArraySizeException extends Exception {
    String name;
    public MyArraySizeException(String name) {
        this.name=name;
    }
}

public class MyException {

    public static void myMethod(String [][] str){
        if (str.length!=4&&str[0].length!=4){
            throw new MyArraySizeException("Неверный размер");
        } else {
            System.out.println("Hello, World");
        }
    }

    public static void main(String[] args) {
        String [][] str1 = new String [3][3];
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str1[i].length; j++) {
                str1[i][j]=String.valueOf((char)('а'+i+j));
            }
        }
        //просмотр массива
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str1[i].length; j++) {
                System.out.print(str1[i][j]);
            }
            System.out.println();
        }

        try{
            myMethod (str1);
        } catch (MyArraySizeException ex){
            System.out.println("Ошибка - неверный размер массива");
        }
    }
}
