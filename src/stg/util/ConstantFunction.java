package stg.util;

public class ConstantFunction extends TimeFunction {
    private double v;

    public ConstantFunction(double v) {
        this.v = v;
    }

    public ConstantFunction(double v, int t) {
        super(t);
        this.v = v;
    }

    public double f(int t) {
        return this.v;
    }
}
