package stg.util;

public abstract class TimeFunction {
    private int t;

    public TimeFunction() {
        this.t = 0;
    }

    public TimeFunction(int t) {
        this.t = t;
    }

    public void update() {
        this.t += 1;
    }

    public double eval() {
        return this.f(this.t);
    }

    public abstract double f(int t);
}
