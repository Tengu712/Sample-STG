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
        // spawn enemies
        this.spawnEnemies();

        // update entities
        //   player
        this.player.update();
        //   enemies
        List<Enemy> newEnemies = new ArrayList<>();
        for (Enemy n : this.enemies.get()) {
            if (n == null) continue;
            if (n.update()) newEnemies.add(n);
        }
        this.enemies.set(newEnemies);
        //   player bullets
        List<Bullet> newPbuls = new ArrayList<>();
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            if (n.update()) newPbuls.add(n);
        }
        this.pbuls.set(newPbuls);
        //   enemy bullets
        List<Bullet> newEbuls = new ArrayList<>();
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            if (n.update()) newEbuls.add(n);
        }
        this.ebuls.set(newEbuls);

        // collision check
        //   player and enemy bullets
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            if (this.player.isHit(n)) {
                return new GameOverScene(this.app, this.score);
            }
        }
        //   enemies and player bullets
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            for (Enemy m : this.enemies.get()) {
                if (m.isHit(n)) {
                    m.hit();
                    score += 1000;
                }
            }
        }

        // draw
        //   background
        final GraphicsContext g = this.app.getGraphicsContext();
        g.setFill(Color.BLACK);
        g.fillRect(0.0, 0.0, 600.0, 800.0);
        for (int i = 0; i < GameScene.BG_TILE_HEIGHT; ++i) {
            for (int j = 0; j < GameScene.BG_TILE_WIDTH; ++j) {
                Resources res = Resources.Bg_0;
                final char chip = GameScene.BG_TILE.charAt(i * GameScene.BG_TILE_WIDTH + j);
                switch (chip) {
                    case '0':
                        res = Resources.Bg_0;
                        break;
                    case '1':
                        res = Resources.Bg_1;
                }
                double y = 50.0 * (double)(i - GameScene.BG_TILE_HEIGHT + GameScene.BG_TILE_HEIGHT_IN_SCREEN);
                y += (double)(this.count % (50 * GameScene.BG_TILE_HEIGHT));
                if (y > 50.0 * GameScene.BG_TILE_HEIGHT_IN_SCREEN) y -= 50.0 * GameScene.BG_TILE_HEIGHT;
                this.app
                    .getGraphicsContext()
                    .drawImage(this.app.getImage(res), 50.0 * (double)j, y, 50.0, 50.0);
            }
        }
        //   player
        this.player.draw();
        //   enemies
        for (Enemy n : this.enemies.get()) {
            if (n == null) continue;
            n.draw();
        }
        //   player bullets
        for (Bullet n : this.pbuls.get()) {
            if (n == null) continue;
            n.draw();
        }
        //   enemy bullets
        for (Bullet n : this.ebuls.get()) {
            if (n == null) continue;
            n.draw();
        }
        //   score
        g.setFill(Color.WHITE);
        g.setTextAlign(TextAlignment.LEFT);
        g.setFont(this.fontScore);
        g.fillText("Score: " + this.score, 0, 28);

        // finish
        this.count += 1;
        this.score += 1;
        return this;
    }

    private void spawnEnemies() {
        if (this.count % 60 != 0) {
            return;
        }
        final double x = Math.random() * 400.0 + 100.0;
        final double spd = Math.random() * 10.0 + 2.0;
        final double returnDeg = Math.random() * 60.0 - 90.0 - 30.0;
        Enemy enemy = new Enemy(
            this.app,
            this.ebuls,
            x,
            -20.0,
            new Velocity(
                new TimeFunction() {
                    public double f(int t) {
                        if (t < 30) return Math.toRadians(90.0);
                        else return Math.toRadians(returnDeg);
                    }
                },
                new TimeFunction() {
                    public double f(int t) {
                        if (t < 30) return spd;
                        else if (t < 90) return 0.0;
                        else return spd;
                    }
                }
            ),
            this.createLaunchProgram()
        );
        this.enemies.add(enemy);
    }

    private LaunchProgram createLaunchProgram() {
        final int k = (int)(Math.random() * 2.0);
        switch (k) {
            case 0: {
                return new LaunchProgram(this.app) {
                    public void program(int t, Updatable e, WrappedList<Bullet> ebuls) {
                        if (t != 50) {
                            return;
                        }
                        final double spd = Math.random() * 6.0 + 4.0;
                        for (int i = 0; i < 60; ++i) {
                            Bullet bullet = new Bullet(
                                this.app,
                                e.getX(),
                                e.getY(),
                                new Velocity(new ConstantFunction(Math.toRadians(6.0) * i), new ConstantFunction(spd))
                            );
                            ebuls.add(bullet);
                        }
                    }
                };
            }
            case 1: {
                return new LaunchProgram(this.app) {
                    public void program(int t, Updatable e, WrappedList<Bullet> ebuls) {
                        if (t != 20 && t != 40) {
                            return;
                        }
                        final double spd = Math.random() * 3.0 + 4.0;
                        final double rad = Math.random() * 360.0;
                        for (int i = 0; i < 20; ++i) {
                            Bullet bullet = new Bullet(
                                this.app,
                                e.getX(),
                                e.getY(),
                                new Velocity(new ConstantFunction(Math.toRadians(18.0) * i + Math.toRadians(rad)), new ConstantFunction(spd))
                            );
                            ebuls.add(bullet);
                        }
                    }
                };
            }
            // unexpected
            default:
                return null;
        }
    }
}
