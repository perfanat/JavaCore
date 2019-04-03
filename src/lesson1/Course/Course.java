package lesson1.Course;

import lesson1.Participant;
import lesson1.Team;

/**
 * Класс - полоса препятствий
 */
public class Course {

    private Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Participant participant : team.getParticipants()) {
            for (Obstacle obstacle : obstacles) {
                obstacle.doIt(participant);

                // если участник сошел с дистанции, то нет смысла двигать его дальше
                if (!participant.isOnDistance()) {
                    break;
                }
            }
            System.out.println("---------------");
        }
    }
}
