package stg.util;

public class LinearFunction {
    private double value;
    private double gradient;

    public LinearFunction(double value, double gradient) {
        this.value = value;
        this.gradient = gradient;
    }

    public void update() {
        this.value += this.gradient;
    }

    public double get() {
        return this.value;
    }
}
