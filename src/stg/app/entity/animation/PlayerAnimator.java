package stg.app.entity.animation;

import stg.resource.*;

/**
 * プレイヤーのアニメーション。
 * 3種類の画像を１→２→３→２→１→２……のように2フレームごとに切り替える。
 */
public class PlayerAnimator implements Animator {
    private int count;

    public PlayerAnimator() {
        this.count = 0;
    }

    public boolean update() {
        this.count += 1;
        return true;
    }

    public Resources get() {
        switch ((this.count / 2) % 4) {
            case 0: return Resources.Jiki_0;
            case 1: return Resources.Jiki_1;
            case 2: return Resources.Jiki_2;
            case 3: return Resources.Jiki_1;
            // unexpected
            default: return Resources.Jiki_0;
        }
    }
}
