package lesson1;

import lesson1.Animal.Cat;
import lesson1.Animal.Dog;
import lesson1.Animal.Human;
import lesson1.Animal.Robot;
import lesson1.Course.Course;
import lesson1.Course.Cross;
import lesson1.Course.Wall;
import lesson1.Course.Water;
import lesson1.Enums.Color;

/**
 * Класс для запуска приложения - симулятор кросса
 */
public class Application {

    public static void main(String[] args) {
        Team team = new Team(
                new Cat("Барсик", Color.BLACK, 1, 100, 10),
                new Dog("Бобик", Color.BROWN, 2, 200, 10, 50),
                new Human("Чел", Color.WHITE, 30, 150, 1, 100),
                new Robot("Роб", Color.RED, 2, 100, 2, 3)
        );

        Course course = new Course(
                new Cross(50),
                new Wall(10),
                new Water(5)
        );

        course.doIt(team);
    }
}
