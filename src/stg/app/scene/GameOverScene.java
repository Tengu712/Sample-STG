package stg.app.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import stg.app.*;
import stg.app.entity.*;
import stg.input.*;
import stg.resource.*;

public class GameOverScene implements Scene {
    private App app;

    private int score;
    private final Font fontGameOver;
    private final Font fontScore;

    public GameOverScene(App app, int score) {
        this.app = app;
        this.score = score;
        this.fontGameOver = new Font(56);
        this.fontScore = new Font(24);
    }

    public Scene update() {
        if (this.app.getKeyState(Keys.Decide) == 1) {
            return new TitleScene(this.app);
        }

        GraphicsContext g = this.app.getGraphicsContext();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 600, 800);
        g.setFill(Color.WHITE);
        g.setTextAlign(TextAlignment.CENTER);
        g.setFont(this.fontGameOver);
        g.fillText("GAMEOVER", 300, 350);
        g.setFont(this.fontScore);
        g.fillText("Score: " + this.score + " frame", 300, 600);
        return this;
    }
}
