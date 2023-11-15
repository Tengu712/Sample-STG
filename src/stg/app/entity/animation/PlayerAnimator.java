package stg.app.entity.animation;

import stg.resource.*;

public class PlayerAnimator implements Animator {
    public boolean update() {
        return true;
    }

    public Resources get() {
        return Resources.Jiki_0;
    }

    public double getHalfWidth() {
        return 8.0;
    }

    public double getHalfHeight() {
        return 8.0;
    }
}
