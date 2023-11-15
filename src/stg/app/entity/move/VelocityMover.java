package stg.app.entity.move;

public class VelocityMover implements Mover {
    private double x;
    private double y;
    private double rad;
    private double spd;

    public VelocityMover(double x, double y, double rad, double spd) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.spd = spd;
    }

    public boolean update() {
        this.x += Math.cos(this.rad) * this.spd;
        this.y += Math.sin(this.rad) * this.spd;
        return this.isAlive();
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }

    public double getRad() { return this.rad; }
    public void setRad(double rad) { this.rad = rad; }

    public double getSpd() { return this.spd; }
    public void setSpd(double spd) { this.spd = spd; }

    public boolean isAlive() {
        return !(this.x < -30.0 || this.x > 630.0 || this.y < -30.0 || this.y > 830.0);
    }
}
