package stg.app.entity;

import stg.app.*;
import stg.app.entity.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;

public class Entity implements Updatable, Drawable, Hitable {
    private App app;
    private Mover mover;
    private Collider collider;
    private Animator animator;

    public Entity(App app, Mover mover, Collider collider, Animator animator) {
        this.app = app;
        this.mover = mover;
        this.collider = collider;
        this.animator = animator;
    }

    public boolean update() {
        final boolean res = this.mover.update();
        this.collider.update(this.mover.getX(), this.mover.getY());
        this.animator.update();
        return res;
    }

    public void draw() {
        final double hw = this.animator.getHalfWidth();
        final double hh = this.animator.getHalfHeight();
        final double x = this.mover.getX() - hw;
        final double y = this.mover.getY() - hh;
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
