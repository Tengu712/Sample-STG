package stg.resource;

import java.util.*;
import javafx.scene.image.*;

public class ResourceManager {
    private HashMap<Resources, Image> imgs;

    public ResourceManager() {
        this.imgs = new HashMap<>();
        this.imgs.put(Resources.Jiki_0, new Image("./img/jiki_0.png"));
        this.imgs.put(Resources.Jiki_1, new Image("./img/jiki_1.png"));
        this.imgs.put(Resources.Jiki_2, new Image("./img/jiki_2.png"));
        this.imgs.put(Resources.Bullet, new Image("./img/bullet.png"));
        this.imgs.put(Resources.Enemy, new Image("./img/enemy.png"));
        this.imgs.put(Resources.Bg_0, new Image("./img/bg_0.png"));
        this.imgs.put(Resources.Bg_1, new Image("./img/bg_1.png"));
    }

    public Image get(Resources key) {
        return this.imgs.get(key);
    }
}
