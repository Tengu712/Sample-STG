package stg.app.entity;

public interface Updatable {
    // it returns false, if it should be removed.
    public boolean update();
    public double getX();
    public double getY();
}
