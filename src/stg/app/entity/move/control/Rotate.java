package stg.app.entity.move.control;

import stg.app.entity.move.*;
import stg.util.*;

public class Rotate implements Controller {
    private Interval interval;
    private LinearFunction addition;

    public Rotate(Interval interval, LinearFunction addition) {
        this.interval = interval;
        this.addition = addition;
    }

    public void update(VelocityMover mover) {
        if (this.interval.isIn()) {
            final double rad = mover.getRad();
            mover.setRad(rad + this.addition.get());
            this.addition.update();
        }
        this.interval.update();
    }
}
