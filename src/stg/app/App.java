package stg.app;

import javafx.animation.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;

import stg.app.scene.*;
import stg.input.*;
import stg.resource.*;

/**
 * アプリ。というかゲーム。
 * AnimationTimerを継承し、その機能を使うことで(多くの環境で)60FPSでゲームを更新する。
 * 
 * また、入力状態やリソースへのアクセスを管轄する。
 */
public class App extends AnimationTimer {
    private InputManager inputManager;
    private ResourceManager resourceManager;
    private GraphicsContext g;
    private Scene scene;

    public App(javafx.scene.Scene scene, GraphicsContext g) {
        this.inputManager = new InputManager(scene);
        this.resourceManager = new ResourceManager();
        this.g = g;
        this.scene = new TitleScene(this);
    }

    public void handle(long now) {
        this.inputManager.update();
        this.scene = this.scene.update();
    }

    public int getKeyState(Keys key) {
        return this.inputManager.get(key);
    }

    public Image getImage(Resources key) {
        return this.resourceManager.get(key);
    }

    public GraphicsContext getGraphicsContext() {
        return this.g;
    }
}
