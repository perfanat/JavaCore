package lesson2;

class MyArraySizeException extends Exception {
    String name;
    public MyArraySizeException(String name) {
        this.name=name;
    }
}

class MyArrayDataException extends Exception {
    String name;
    public MyArrayDataException(String name) {
        this.name=name;
    }
}

public class MyException {

    // координаты неверного символа
    static int x;
    static int y;

    public static void myMethod(String [][] str) throws MyArraySizeException, MyArrayDataException {
        if (str.length!=4||str[0].length!=4){
            throw new MyArraySizeException("Неверный размер");
        } else {
            System.out.println("Программа инициализации массива отработала корректно");
        }

        int count = 0;
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                if (str[i][j].length()>1){
                    x=i;
                    y=j;
                    throw new MyArrayDataException("Неверный символ");
                }
                System.out.print((int) str[i][j].charAt(0)+" ");
                count+= (int) str[i][j].charAt(0);
            }
            System.out.println();
        }
        System.out.println(String.format("Сумма всех преобразованных элементов равна %s",count));
    }

    public static void main(String[] args) {
        // создание массива (для ошибки - меняем размер)
        String [][] str1 = new String [4][4];
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str1[i].length; j++) {
                str1[i][j]=String.valueOf((char)('а'+i+j));
                // добавление лишнего символа
                if (i==1&&j==1){
                    str1 [i][j]="аа";
                }
            }
        }
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
            System.out.println(String.format("\nОшибка - неверный символ в ячейке %s/%s",x,y));
        }
    }
}
