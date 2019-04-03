package lesson1.Course;

import lesson1.Participant;

public class Cross extends Obstacle {

    private int distance;

    public Cross(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Participant participant) {

        participant.run(this.distance);
    }
}
