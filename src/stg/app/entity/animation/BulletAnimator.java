package stg.app.entity.animation;

import stg.resource.*;

public class BulletAnimator implements Animator {
    public boolean update() {
        return true;
    }

    public Resources get() {
        return Resources.Bullet;
    }

    public double getHalfWidth() {
        return 4.0;
    }

    public double getHalfHeight() {
        return 4.0;
    }
}
