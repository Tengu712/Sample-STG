package stg.app.entity.program;

import stg.app.*;
import stg.app.entity.*;
import stg.util.*;

/**
 * n-way全方位弾。
 */
public interface LaunchProgram {
    private App app;
    private Interval interval;
    private int n;

    public Program(App app, Interval interval, int n) {
        this.app = app;
        this.interval = interval;
        this.n = n;
    }

    public void update(Updatable obj, WrappedList<Bullet> buls) {
        if (!this.interval.isIn()) {
          return;
        }
        this.interval.incr();

        for (int i = 0; i < this.n; ++i) {
        }
    }
}
