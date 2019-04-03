package lesson1.Animal;

import lesson1.Participant;

/**
 * Робот не является животным, но может учавствовать в соревнованиях
 * так как реализует интерфейс {@link Participant}
 */
public class Robot implements Participant {

    private boolean isOnDistance;
    private int runDistance;
    private int jumpHeight;
    private int swimDistance;
    private lesson1.Enums.Color color;
    private int age;

    private String name;

    public Robot(String name, lesson1.Enums.Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
        this.name = name;
        this.color=color;
        this.age=age;
        this.isOnDistance=true;
    }

    public Robot(String name) {
        this.name = name;
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
            return;
        }
        System.out.println(String.format("Робот %s пробежал кросс длинной %d", getName(), distance));
    }

    public String getName() {
        return name;
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            return;
        }
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не прыгнул в высоту %d и сошёл с дистанции", getName(), height));
            return;
        }
        System.out.println(String.format("Робот %s прыгнул в длину %d", getName(), height));
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance) {
            return;
        }
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(String.format("Робот %s не проплыл расстояние %d и сошёл с дистанции", getName(), distance));
            return;
        }
        System.out.println(String.format("Робот %s проплыл расстояние %d", getName(), distance));
    }

    public void voice() {
        System.out.println("Дзин");
    }
}
