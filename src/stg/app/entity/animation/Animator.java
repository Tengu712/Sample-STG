package stg.app.entity.animation;

import stg.resource.*;

public interface Animator {
    // if it returns false, it means the animation is ended.
    boolean update();
    Resources get();

    double getHalfWidth();
    double getHalfHeight();
}
