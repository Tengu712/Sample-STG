package stg.app;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import stg.app.scene.*;
import stg.input.*;
import stg.resource.*;

public class App extends AnimationTimer {
    private InputManager inputManager;
    private ResourceManager resourceManager;
    private GraphicsContext g;

    private boolean isActive;
    private Scene scene;

    public App(javafx.scene.Scene scene, GraphicsContext g) {
        this.inputManager = new InputManager(scene);
        this.resourceManager = new ResourceManager();
        this.g = g;
        this.isActive = true;
        this.scene = new TitleScene(this);
    }

    public void handle(long now) {
        this.inputManager.update();
        this.scene = this.scene.update();
    }

    public void terminate() {
        this.isActive = false;
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
