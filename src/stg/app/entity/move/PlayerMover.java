package stg.app.entity.move;

import stg.app.*;
import stg.input.*;

public class PlayerMover implements Mover {
    private App app;
    private double x;
    private double y;

    public PlayerMover(App app) {
        this.app = app;
        this.x = 300.0;
        this.y = 700.0;
    }

    public boolean update() {
        final boolean l = this.app.getKeyState(Keys.Left) > 0;
        final boolean r = this.app.getKeyState(Keys.Right) > 0;
        final boolean u = this.app.getKeyState(Keys.Up) > 0;
        final boolean d = this.app.getKeyState(Keys.Down) > 0;
        final boolean z = this.app.getKeyState(Keys.Decide) > 0;
        final int inpX = (r ? 1 : 0) - (l ? 1 : 0);
        final int inpY = (d ? 1 : 0) - (u ? 1 : 0);
        final boolean isBoth = inpX * inpX > 0 && inpY * inpY > 0;
        final double coefSpeed = isBoth ? 0.70710678118 : 1.0;
        this.x += inpX * coefSpeed * 6.0;
        this.y += inpY * coefSpeed * 6.0;
        this.x = Math.max(0.0, Math.min(600.0, this.x));
        this.y = Math.max(0.0, Math.min(800.0, this.y));
        return true;
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }
}
