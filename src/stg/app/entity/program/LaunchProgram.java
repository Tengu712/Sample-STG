package stg.app.entity.program;

import stg.app.*;
import stg.app.entity.*;
import stg.util.*;

/**
 * 弾幕を張るプログラム。
 * 弾幕を張るプログラムはこのインターフェースを介して一様に操作される。
 */
public interface LaunchProgram {
    /**
     * 弾幕を張る。
     * 
     * @param obj 弾幕を張る者
     * @param buls 弾が格納されるリスト
     */
    void run(Updatable obj, WrappedList<Bullet> buls);
}
