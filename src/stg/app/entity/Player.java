package stg.app.entity;

import java.util.*;

import stg.app.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;
import stg.input.*;
import stg.util.*;

/**
 * プレイヤー。
 * 
 * - 当たり判定の半径：8.0
 * - 見た目の正方形の辺長：24.0
 */
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
            this.pbuls.add(new Bullet(
                this.app,
                this.xy.getX(),
                this.xy.getY(),
                new VelocityMover(Math.toRadians(-90.0), 20.0)
            ));
            this.launchInterval = new Interval(5, Integer.MAX_VALUE, 1, 0);
        } else {
            this.launchInterval.incr();
        }
        this.mover.apply(this.xy);
        this.animator.update();
        return true;
    }

    public double getX() { return this.xy.getX(); }
    public double getY() { return this.xy.getY(); }

    public void draw() {
        final double hw = 12.0;
        final double hh = 12.0;
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

    public boolean hit(Hitable opponent) {
        return this.collider.hit(opponent);
    }

    public Collider getCollider() {
        return this.collider;
    }
}
