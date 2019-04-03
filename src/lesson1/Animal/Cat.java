package lesson1.Animal;

import lesson1.Participant;

public class Cat extends Animal implements Participant {

    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;

    public Cat(String name, lesson1.Enums.Color color, int age, int runDistance, int jumpHeight) {
        super(name, color, age);
        this.isOnDistance = true;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    public Cat(String name, lesson1.Enums.Color color) {
        super(name, color, 0);
    }

    @Override
    public void voice() {
        System.out.println("Мяу");
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
            System.out.println(String.format("Кошка %s не пробежала дистанцию %d и сошла с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Кошка %s пробежала кросс длинной %d", getName(), distance));
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("Кошка %s не прыгнула на высоту %d и сошла с дистанции", getName(), height));
            return;
        }
        System.out.println(String.format("Кошка %s прыгнула на высоту %d", getName(), height));
    }

    @Override
    public void swim(int distance) {
        isOnDistance = false;
        System.out.println("Кошка не умеет плавать и сошла с дистанции");
        // throw new UnsupportedOperationException("Кошка не умеет плавать");
    }

    public void setRunDistance(int runDistance) {
        this.runDistance = runDistance;
    }
}