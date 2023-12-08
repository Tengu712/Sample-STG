package stg.app.entity.program;

import stg.app.*;
import stg.app.entity.*;
import stg.app.entity.move.*;
import stg.util.*;

/**
 * n-way全方位弾。
 */
public class LaunchCircle implements LaunchProgram {
    private App app;
    private Interval interval;
    private int n;
    private double initRad;
    private double spd;

    public LaunchCircle(App app, Interval interval, int n, double initRad, double spd) {
        this.app = app;
        this.interval = interval;
        this.n = n;
        this.initRad = initRad;
        this.spd = spd;
    }

    public void run(Updatable obj, WrappedList<Bullet> buls) {
        if (!this.interval.isIn()) {
          return;
        }
        this.interval.incr();

        final double dr = 360.0 / (double)this.n;
        for (int i = 0; i < this.n; ++i) {
            buls.add(new Bullet(
                this.app,
                obj.getX(),
                obj.getY(),
                new VelocityMover(Math.toRadians(dr) * i + this.initRad, this.spd)
            ));
        }
    }
}
