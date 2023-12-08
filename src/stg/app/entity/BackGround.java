package stg.app.entity;

public class BackGround implements Updatable, Drawable {
    private String[] tilelines;
    private final int width;
    private final int height;
    private App app;
    private int count;

    public BackGround(App app, String[] tilelines) {
        this.app = app;
        this.tilelines = tilelines;
        this.width = tilelines[0].length;
        this.height = tilelines.length;
    }

    public boolean update() {
        this.count += 1;
        return true;
    }
    
    public double getX() { return 0.0; }
    public double getY() { return 0.0; }

    public void draw() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                Resources res = Resources.Bg_0;
                switch (this.tilelines[i].charAt(j)) {
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
    }
}