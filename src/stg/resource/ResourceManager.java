package stg.resource;

import java.util.*;
import javafx.scene.image.*;

public class ResourceManager {
    private HashMap<Resources, Image> imgs;

    public ResourceManager() {
        this.imgs = new HashMap<>();
        this.imgs.put(Resources.Jiki_0, new Image("./img/jiki_0.png"));
        this.imgs.put(Resources.Bullet, new Image("./img/bullet.png"));
    }

    public Image get(Resources key) {
        return this.imgs.get(key);
    }
}
