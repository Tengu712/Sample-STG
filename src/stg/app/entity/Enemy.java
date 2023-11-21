package stg.app.entity;

import stg.app.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;
import stg.resource.*;
import stg.util.*;

/**
 * 敵。
 * 
 * - 当たり判定の半径：12.0
 * - 見た目のサイズ：12.0x12.0
 */
public class Enemy implements Updatable, Drawable, Hitable {
    private App app;
    private WrappedList<Bullet> ebuls;
    private Vec2 xy;
    private Mover mover;
    private Collider collider;
    private LaunchProgram program;
    private boolean isAlive;

    public Enemy(App app, WrappedList<Bullet> ebuls, double x, double y, Mover mover, LaunchProgram program) {
        this.app = app;
        this.ebuls = ebuls;
        this.xy = new Vec2(x, y);
        this.mover = mover;
        this.collider = new Collider(this.xy, 12.0);
        this.program = program;
        this.isAlive = true;
    }

    public boolean update() {
        if (!isAlive) return false;
        this.program.update(this, this.ebuls);
        this.mover.apply(this.xy);
        return !(this.xy.getX() < -30.0 || this.xy.getX() > 630.0 || this.xy.getY() < -30.0 || this.xy.getY() > 830.0);
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
                this.app.getImage(Resources.Enemy),
                x,
                y,
                hw * 2.0,
                hh * 2.0
            );
    }

    public boolean hit(Hitable opponent) {
        final boolean hit = this.collider.hit(opponent);
        if (hit) this.isAlive = false;
        return hit;
    }

    public Collider getCollider() {
        return this.collider;
    }
}
