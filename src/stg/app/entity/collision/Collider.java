package stg.app.entity.collision;

public class Collider {
    private double x;
    private double y;
    private double r;

    public Collider(double r) {
        this.x = 0.0;
        this.y = 0.0;
        this.r = r;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHit(Hitable opponent) {
        final Collider oc = opponent.getCollider();
        final double ox = oc.x;
        final double oy = oc.y;
        final double or = oc.r;

        final double dx = ox - this.x;
        final double dy = oy - this.y;
        final double dis = or + this.r;

        final double dx2 = dx * dx;
        final double dy2 = dy * dy;
        final double dis2 = dis * dis;

        return dx2 + dy2 < dis2;
    }
}
