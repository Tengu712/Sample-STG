package stg.app.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import stg.app.*;
import stg.input.*;

public class TitleScene implements Scene {
    private App app;

    private final Font fontTitle;
    private final Font fontGuide;

    public TitleScene(App app) {
        this.app = app;
        this.fontTitle = new Font(48);
        this.fontGuide = new Font(24);
    }

    public Scene update() {
        if (app.getKeyState(Keys.Decide) == 1) {
            return new GameScene(this.app);
        }

        GraphicsContext g = this.app.getGraphicsContext();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 600, 800);
        g.setFill(Color.WHITE);
        g.setTextAlign(TextAlignment.CENTER);
        g.setFont(this.fontTitle);
        g.fillText("Example STG", 300, 300);
        g.setFont(this.fontGuide);
        g.fillText("Press Z to Start", 300, 600);
        return this;
    }
}
