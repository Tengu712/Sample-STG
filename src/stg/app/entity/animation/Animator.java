package stg.app.entity.animation;

import stg.resource.*;

/**
 * アニメーション。
 * アニメーションはこのインターフェースを介して一様に操作される。
 */
public interface Animator {
    /**
     * アニメーションの更新。
     * 
     * @return アニメーションが続いていればtrue、終了していればfalseを返す。
     */
    boolean update();
    /**
     * 現在のイメージを取得するメソッド。
     */
    Resources get();
}
