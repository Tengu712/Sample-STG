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
import stg.app.entity.move.control.*;
import stg.util.*;

public class GameScene implements Scene {
    private final Font fontScore;

    private App app;
    private Entity player;
    private List<Entity> ebuls;
    private int count;

    public GameScene(App app) {
        this.fontScore = new Font(24);
        this.app = app;
        this.player = new Entity(
            app,
            new PlayerMover(app),
            new Collider(8.0),
            new PlayerAnimator()
        );
        this.ebuls = new ArrayList<>();
        this.count = 0;
    }

    public Scene update() {
        // update entities
        //   player
        this.player.update();
        //   enemy bullets
        List<Entity> newEbuls = new ArrayList<>();
        for (Entity n : this.ebuls) {
            if (n == null) continue;
            if (n.update()) newEbuls.add(n);
        }
        this.ebuls = newEbuls;

        // collision check
        //   player and enemy bullets
        for (Entity n : this.ebuls) {
            if (n == null) continue;
            if (this.player.isHit(n)) {
                return new GameOverScene(this.app, this.count);
            }
        }

        // draw
        //   background
        GraphicsContext g = this.app.getGraphicsContext();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 600, 800);
        //   player
        this.player.draw();
        //   enemy bullets
        for (Entity n : this.ebuls) {
            if (n == null) continue;
            n.draw();
        }
        //   score
        g.setFill(Color.WHITE);
        g.setTextAlign(TextAlignment.LEFT);
        g.setFont(this.fontScore);
        g.fillText("Score: " + this.count, 0, 28);

        // finish
        this.count += 1;
        return this;
    }
}
