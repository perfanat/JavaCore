package lesson2;

class MyArraySizeException extends Exception {
    MyArraySizeException() {
    }
}

class MyArrayDataException extends Exception {
    static int x;
    static int y;
    MyArrayDataException() {}

    static void infoEx(){
        System.out.println(String.format("\nОшибка - неверный символ в ячейке %s/%s",x,y));
    }
}

public class MyException {

    private static void myMethod(String[][] str) throws MyArraySizeException, MyArrayDataException {

        boolean noCorrectSize = false;
        if (str.length!=4)noCorrectSize=true;
        for (String[] strings : str) {
            if (strings.length != 4) {
                noCorrectSize = true;
                break;
            }
        }

        if (noCorrectSize){
            throw new MyArraySizeException();
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
        for (String[] strings : str1) {
            for (String string : strings) {
                System.out.print(string + " ");
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
