package lesson1.Animal;

import lesson1.Participant;

public class Human extends Animal implements Participant {

    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;
    private int swimDistance;

    public Human(String name, lesson1.Enums.Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        super(name, color, age);
        this.isOnDistance = true;
        this.runDistance=runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance=swimDistance;

    }

    public Human(String name, lesson1.Enums.Color color) {
        super(name, color, 0);
    }

    @Override
    public boolean isOnDistance() {
        return isOnDistance;
    }

    @Override
    public void run(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (distance > runDistance) {
            isOnDistance = false;
            System.out.println(String.format("Человек %s не пробежал дистанцию %d и сошёл с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Человек %s пробежал кросс длинной %d", getName(), distance));
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("Человек %s не прыгнул в высоту %d и сошёл с дистанции", getName(), height));
            return;
        }
        System.out.println(String.format("Человек %s прыгнул в длину %d", getName(), height));
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(String.format("Человек %s не проплыл расстояние %d и сошёл с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Человек %s проплыл расстояние %d", getName(), distance));
    }

    @Override
    public void voice() {
        System.out.println("Привет");
    }
}
