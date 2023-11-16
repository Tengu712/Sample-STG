package stg.util;

public class LinearFunction extends TimeFunction {
    private double v;
    private double a;

    public LinearFunction(double v, double a) {
        this.v = v;
        this.a = a;
    }

    public LinearFunction(double v, double a, int t) {
        super(t);
        this.v = v;
        this.a = a;
    }

    public double f(int t) {
        return this.v + this.a * (double)t;
    }
}
