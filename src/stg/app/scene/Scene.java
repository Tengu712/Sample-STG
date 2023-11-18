package stg.app.scene;

/**
 * シーン。
 * シーンはこのインターフェースを介して一様に操作される。
 */
public interface Scene {
    /**
     * シーンの更新。
     * 
     * @return 次のフレームでのシーン。シーン遷移するなら新しいインスタンスを、そうでないならばthisを返すべし。
     */
    Scene update();
}
