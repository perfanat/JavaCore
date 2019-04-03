package lesson1.Animal;

public abstract class Animal {

    private String name;
    private lesson1.Enums.Color color;
    private int age;

    public Animal(String name, lesson1.Enums.Color color, int age) {
        this.name = name;
        this.color=color;
        this.age = age;
    }

    // Перегруженный (overload) конструктор
    public Animal(String name, lesson1.Enums.Color color) {
        this(name, color, 0);
//        this.name = name;
//        this.color = color;
//        this.age = 0;
    }

    public abstract void voice();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public lesson1.Enums.Color getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}