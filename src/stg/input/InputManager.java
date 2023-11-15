package stg.input;

import java.util.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class InputManager {
    private HashMap<Keys, Integer> keys;
    private HashMap<Keys, Boolean> state;

    public InputManager(Scene scene) {
        this.keys = new HashMap<>();
        this.keys.put(Keys.Up, 0);
        this.keys.put(Keys.Down, 0);
        this.keys.put(Keys.Left, 0);
        this.keys.put(Keys.Right, 0);
        this.keys.put(Keys.Decide, 0);
        this.state = new HashMap<>();
        this.state.put(Keys.Up, false);
        this.state.put(Keys.Down, false);
        this.state.put(Keys.Left, false);
        this.state.put(Keys.Right, false);
        this.state.put(Keys.Decide, false);
        scene.setOnKeyPressed(this::onPressed);
        scene.setOnKeyReleased(this::onReleased);
    }

    public int get(Keys key) {
        final Integer state = this.keys.get(key);
        return state == null ? 0 : state;
    }

    public void update() {
        for (Keys key : this.keys.keySet()) {
            if (this.state.get(key)) {
                this.keys.put(key, this.keys.get(key) + 1);
            } else if (this.keys.get(key) > 0) {
                this.keys.put(key, 0);
            }
        }
    } 

    private static Keys convertKeyCodeToKeys(KeyCode c) {
        switch (c) {
            case KeyCode.UP:
                return Keys.Up;
            case KeyCode.DOWN:
                return Keys.Down;
            case KeyCode.LEFT:
                return Keys.Left;
            case KeyCode.RIGHT:
                return Keys.Right;
            case KeyCode.Z:
                return Keys.Decide;
            default:
                return null;
        }
    }

    public void onPressed(KeyEvent e) {
        final Keys key = InputManager.convertKeyCodeToKeys(e.getCode());
        if (key != null) {
            this.state.put(key, true);
        }
    }

    public void onReleased(KeyEvent e) {
        final Keys key = InputManager.convertKeyCodeToKeys(e.getCode());
        if (key != null) {
            this.state.put(key, false);
        }
    }
}
