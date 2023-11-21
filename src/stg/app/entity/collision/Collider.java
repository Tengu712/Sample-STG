package stg.app.entity.collision;

import stg.util.*;

// NOTE: 理想的には、インターフェースにして形状の多相性を吸収すべき。
/**
 * 円状のコライダー。
 * 
 * コンストラクタで与えられたVec2座標に追従する。
 */
public class Collider {
    private Vec2 xy;
    private double r;

    public Collider(Vec2 xy, double r) {
        this.xy = xy;
        this.r = r;
    }

    public boolean hit(Hitable opponent) {
        final Collider oc = opponent.getCollider();
        final double ox = oc.xy.getX();
        final double oy = oc.xy.getY();
        final double or = oc.r;

        final double dx = ox - this.xy.getX();
        final double dy = oy - this.xy.getY();
        final double dis = or + this.r;

        final double dx2 = dx * dx;
        final double dy2 = dy * dy;
        final double dis2 = dis * dis;

        return dx2 + dy2 < dis2;
    }
}
