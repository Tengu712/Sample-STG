package stg.app.scene;

import java.util.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import stg.app.*;
import stg.app.entity.*;
import stg.app.entity.animation.*;
import stg.app.entity.collision.*;
import stg.app.entity.move.*;
import stg.app.entity.program.*;
import stg.resource.*;
import stg.util.*;

public class GameScene implements Scene {
    private static final int BG_TILE_WIDTH = 12;
    private static final int BG_TILE_HEIGHT = 25;
    private static final int BG_TILE_WIDTH_IN_SCREEN = 12;
    private static final int BG_TILE_HEIGHT_IN_SCREEN = 16;
    private static final String BG_TILE =
        "111111111111" +
        "111111111111" +
        "001111111111" +
        "000001111111" +
        "000000011111" +
        "100000001100" +
        "111000000000" +
        "111100000000" +
        "111111110011" +
        "111111111111" +
        "111111111111" +
        "110111111111" +
        "100011111111" +
        "100111111111" +
        "111111111111" +
        "111111111111" +
        "111111111111" +
        "111111111111" +
        "111110000111" +
        "111111100001" +
        "111111110011" +
        "111111111111" +
        "111111111111" +
        "111111111111" +
        "111111111111";

    private final Font fontScore;

    private App app;
    private WrappedList<Bullet> pbuls;
    private WrappedList<Bullet> ebuls;
    private WrappedList<Enemy> enemies;
    private Player player;
    private int count;
    private int score;

    public GameScene(App app) {
        this.fontScore = new Font(24);
        this.app = app;
        this.pbuls = new WrappedList<>(new ArrayList<Bullet>());
        this.ebuls = new WrappedList<>(new ArrayList<Bullet>());
        this.enemies = new WrappedList<>(new ArrayList<Enemy>());
        this.player = new Player(app, this.pbuls);
        this.count = 0;
        this.score = 0;
    }

    public Scene update() {
        // 60フレームに一度、敵を生成
        if (this.count % 60 != 0) {
            final double x = Math.random() * 400.0 + 100.0;
            final double y = -20.0;
            final double spd = Math.random() * 10.0 + 2.0;
            final double deg = Math.random() * 60.0 - 90.0 - 30.0;
            final double bulRad = Math.toRadians(Math.random() * 360.0);
            final double bulSpd = Math.random() * 6.0 + 4.0;
            this.enemies.add(new Enemy(
                this.app,
                this.ebuls,
                x,
                y,
                new VelocityMover(Math.toRadians(90.0), spd),
                new LaunchCircle(this.app, new Interval(0, Integer.MAX_VALUE, 25, 0), 60, bulRad, bulSpd)
            ));
        }

        // プレイヤーの更新
        this.player.update();
        // 敵の更新
        List<Enemy> newEnemies = new ArrayList<>();
        for (Enemy n : this.enemies.get()) {
            if (n == null) continue;
            if (n.update()) newEnemies.add(n);
        }
        this.enemies.set(newEnemies);
        // 自弾の更新
        List<Bullet> newPbuls = new ArrayList<>();
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            if (n.update()) newPbuls.add(n);
        }
        this.pbuls.set(newPbuls);
        // 敵弾の更新
        List<Bullet> newEbuls = new ArrayList<>();
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            if (n.update()) newEbuls.add(n);
        }
        this.ebuls.set(newEbuls);

        // 敵弾とプレイヤーとの衝突判定
        // 衝突したらゲームオーバーシーンへ
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            if (this.player.hit(n)) {
                return new GameOverScene(this.app, this.score);
            }
        }
        // 自弾と敵との衝突判定
        // 衝突したらスコアを加点
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            for (Enemy m : this.enemies.get()) {
                if (m.hit(n)) {
                    score += 1000;
                }
            }
        }

        // 背景の描画
        // プレイヤーの描画
        this.player.draw();
        // 敵の描画
        for (Enemy n : this.enemies.get()) {
            if (n == null) continue;
            n.draw();
        }
        // 自弾の描画
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            n.draw();
        }
        // 敵弾の描画
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            n.draw();
        }
        // スコアの描画
        g.setFill(Color.WHITE);
        g.setTextAlign(TextAlignment.LEFT);
        g.setFont(this.fontScore);
        g.fillText("Score: " + this.score, 0, 28);

        this.count += 1;
        this.score += 1;
        return this;
    }
}

