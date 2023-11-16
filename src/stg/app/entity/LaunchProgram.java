package stg.app.entity;

import stg.app.*;
import stg.util.*;

public abstract class LaunchProgram {
    private int t;
    protected App app;

    public LaunchProgram(App app) {
        this.app = app;
        this.t = 0;
    }

    public LaunchProgram(App app, int t) {
        this.app = app;
        this.t = t;
    }

    public void update(Updatable obj, WrappedList<Bullet> buls) {
        this.program(this.t, obj, buls);
        this.t += 1;
    }

    public abstract void program(int t, Updatable obj, WrappedList<Bullet> buls);
}
