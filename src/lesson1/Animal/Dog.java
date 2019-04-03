package lesson1.Animal;

import lesson1.Participant;

public class Dog extends Animal implements Participant {

    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;
    private int swimDistance;

    public Dog(String name, lesson1.Enums.Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        super(name, color, age);
        this.isOnDistance = true;
        this.runDistance=runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance=swimDistance;
    }

    public Dog(String name, lesson1.Enums.Color color) {

        super(name, color, 0);
    }

    @Override
    public void voice() {

        System.out.println("Гав");
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
            System.out.println(String.format("Собака %s не пробежала дистанцию %d и сошла с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Собака %s пробежала кросс длинной %d", getName(), distance));
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("Собака %s не прыгнула в высоту %d и сошла с дистанции", getName(), height));
            return;
        }
        System.out.println(String.format("Собака %s прыгнула в длину %d", getName(), height));
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(String.format("Собака %s не проплыла дистанцию %d и сошла с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Собака %s проплыла расстояние %d", getName(), distance));
    }
}