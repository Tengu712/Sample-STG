package stg.app.entity.move;

public interface Mover {
    // it returns false, if it should be removed.
    public boolean update();
    public double getX();
    public double getY();
}
