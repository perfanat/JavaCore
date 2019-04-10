package lesson2;

class MyArraySizeException extends Exception {
    String name;
    public MyArraySizeException(String name) {
        this.name=name;
    }
}


class MyArrayDataException extends Exception {
    String name;
    static int x;
    static int y;
    public MyArrayDataException() {
        name="Ошибка - неверный символ в ячейке";
    }

    public static void infoEx(){
        System.out.println(String.format("\nОшибка - неверный символ в ячейке %s/%s",x,y));
    }
}

public class MyException {

    public static void myMethod(String [][] str) throws MyArraySizeException, MyArrayDataException {

        boolean noCorrectSize = false;
        if (str.length!=4)noCorrectSize=true;
        for (int i = 0; i < str.length; i++) {
            noCorrectSize|=str[i].length!=4;
        }

        if (noCorrectSize){
            throw new MyArraySizeException("Неверный размер");
        } else {
            System.out.println("Программа инициализации массива отработала корректно");
        }

        int count = 0;
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                    MyArrayDataException.y = i;
                    MyArrayDataException.x = j;
                    try {
                        System.out.print(Integer.parseInt(str[i][j]) + " ");
                        count += Integer.parseInt(str[i][j]);
                    } catch (NumberFormatException ex) {
                        throw new MyArrayDataException();
                    }
            }
            System.out.println();
        }
        System.out.println(String.format("Сумма всех преобразованных элементов равна %s",count));
    }

    public static void main(String[] args) {
        // создание массива (для ошибки - меняем размер)
        String [][] str1 = {{"1","1","1","1",}, {"1","2","3","4",}, {"5","j","7","8",}, {"2","2","2","2"}};

        //просмотр массива
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str1[i].length; j++) {
                System.out.print(str1[i][j]+" ");
            }
            System.out.println();
        }

        try{
            myMethod (str1);
        } catch (MyArraySizeException ex){
            System.out.println("Ошибка - неверный размер массива");
        } catch (MyArrayDataException ex){
            MyArrayDataException.infoEx();
        }
    }
}
