package stg.app.entity.move;

import stg.app.*;
import stg.input.*;
import stg.util.*;

public class PlayerMover implements Mover {
    private App app;

    public PlayerMover(App app) {
        this.app = app;
    }

    public void apply(Vec2 xy) {
        final boolean l = this.app.getKeyState(Keys.Left) > 0;
        final boolean r = this.app.getKeyState(Keys.Right) > 0;
        final boolean u = this.app.getKeyState(Keys.Up) > 0;
        final boolean d = this.app.getKeyState(Keys.Down) > 0;
        final boolean z = this.app.getKeyState(Keys.Decide) > 0;
        final int inpX = (r ? 1 : 0) - (l ? 1 : 0);
        final int inpY = (d ? 1 : 0) - (u ? 1 : 0);
        final boolean isBoth = inpX * inpX > 0 && inpY * inpY > 0;
        final double coefSpeed = isBoth ? 0.70710678118 : 1.0;

        double x = xy.getX();
        double y = xy.getY();
        x += inpX * coefSpeed * 8.0;
        y += inpY * coefSpeed * 8.0;
        x = Math.max(0.0, Math.min(600.0, x));
        y = Math.max(0.0, Math.min(800.0, y));

        xy.setX(x);
        xy.setY(y);
    }
}
