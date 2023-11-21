package stg.app.entity;

import stg.app.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;
import stg.resource.*;
import stg.util.*;

/**
 * 弾。
 * 
 * - 当たり判定の半径：6.0
 * - 見た目のサイズ：6.0x6.0
 */
public class Bullet implements Updatable, Drawable, Hitable {
    private App app;
    private Vec2 xy;
    private Mover mover;
    private Collider collider;

    public Bullet(App app, double x, double y, Mover mover) {
        this.app = app;
        this.xy = new Vec2(x, y);
        this.mover = mover;
        this.collider = new Collider(this.xy, 6.0);
    }

    public boolean update() {
        this.mover.apply(this.xy);
        return !(this.xy.getX() < -30.0 || this.xy.getX() > 630.0 || this.xy.getY() < -30.0 || this.xy.getY() > 830.0);
    }

    public double getX() { return this.xy.getX(); }
    public double getY() { return this.xy.getY(); }

    public void draw() {
        final double hw = 6.0;
        final double hh = 6.0;
        final double x = this.xy.getX() - hw;
        final double y = this.xy.getY() - hh;
        this.app
            .getGraphicsContext()
            .drawImage(
                this.app.getImage(Resources.Bullet),
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
