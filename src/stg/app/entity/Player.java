package stg.app.entity;

import java.util.*;

import stg.app.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;
import stg.input.*;
import stg.util.*;

public class Player implements Updatable, Drawable, Hitable {
    private App app;
    private WrappedList<Bullet> pbuls;
    private Vec2 xy;
    private Mover mover;
    private Collider collider;
    private Animator animator;
    private Interval launchInterval;

    public Player(App app, WrappedList<Bullet> pbuls) {
        this.app = app;
        this.pbuls = pbuls;
        this.xy = new Vec2(300.0, 700.0);
        this.mover = new PlayerMover(app);
        this.collider = new Collider(this.xy, 8.0);
        this.animator = new PlayerAnimator();
        this.launchInterval = new Interval(5, Integer.MAX_VALUE, 1, 0);
    }

    public boolean update() {
        if (this.app.getKeyState(Keys.Decide) > 0 && this.launchInterval.isIn()) {
            Bullet bullet = new Bullet(
                this.app,
                this.xy.getX(),
                this.xy.getY(),
                new Velocity(
                    new ConstantFunction(Math.toRadians(-90.0)),
                    new ConstantFunction(20.0)
                )
            );
            this.pbuls.add(bullet);
            this.launchInterval = new Interval(5, Integer.MAX_VALUE, 1, 0);
        } else {
            this.launchInterval.update();
        }
        this.mover.apply(this.xy);
        this.animator.update();
        return true;
    }

    public double getX() { return this.xy.getX(); }
    public double getY() { return this.xy.getY(); }

    public void draw() {
        final double hw = this.animator.getHalfWidth();
        final double hh = this.animator.getHalfHeight();
        final double x = this.xy.getX() - hw;
        final double y = this.xy.getY() - hh;
        this.app
            .getGraphicsContext()
            .drawImage(
                this.app.getImage(this.animator.get()),
                x,
                y,
                hw * 2.0,
                hh * 2.0
            );
    }

    public boolean isHit(Hitable opponent) {
        return this.collider.isHit(opponent);
    }

    public Collider getCollider() {
        return this.collider;
    }
}
