package stg.app.entity.program;

import stg.app.*;
import stg.app.entity.*;
import stg.util.*;

/**
 * 弾幕を張るプログラムの本体。時間関数。
 */
public interface LaunchProgramLogic {
  void f(int t, App app, Updatable obj, WrappedList<Bullet> buls);
}
