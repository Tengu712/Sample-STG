package stg.app.entity.move;

import stg.util.*;

public class Velocity implements Mover {
    private TimeFunction rad;
    private TimeFunction spd;

    public Velocity(TimeFunction rad, TimeFunction spd) {
        this.rad = rad;
        this.spd = spd;
    }

    public void apply(Vec2 xy) {
        double x = xy.getX();
        double y = xy.getY();
        x += Math.cos(this.rad.eval()) * this.spd.eval();
        y += Math.sin(this.rad.eval()) * this.spd.eval();
        xy.setX(x);
        xy.setY(y);

        this.rad.update();
        this.spd.update();
    }
}
