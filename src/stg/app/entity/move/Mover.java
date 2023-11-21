package stg.app.entity.move;

import stg.util.*;

/**
 * 動き方。
 * 動き方はこのインターフェースを介して一様に操作される。
 */
public interface Mover {
    /**
     * Vec2で与えられるxy座標に動きを適応する。
     */
    void apply(Vec2 xy);
}
