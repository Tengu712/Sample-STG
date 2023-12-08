package stg.app.entity.move;

import stg.util.*;

/**
 * 初速度に基づいた動き方。
 */
public class VelocityMover implements Mover {
    // これらを時間的に変化させれば「有機的」な動きをさせられる。
    private double rad;
    private double spd;

    public VelocityMover(double rad, double spd) {
        this.rad = rad;
        this.spd = spd;
    }

    public void apply(Vec2 xy) {
        double x = xy.getX();
        double y = xy.getY();
        x += Math.cos(this.rad) * this.spd;
        y += Math.sin(this.rad) * this.spd;
        xy.setX(x);
        xy.setY(y);
    }
}
