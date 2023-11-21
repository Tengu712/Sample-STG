package stg.util;

public class Interval {
    private int start;
    private int end;
    private int interval;
    private int count;

    public Interval(int start, int end, int interval, int count) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.count = count;
    }

    public void incr() {
        this.count += 1;
    }

    public boolean isIn() {
        final boolean isin = this.count >= this.start && this.count < this.end;
        final boolean ison = (this.count - this.start) % this.interval == 0;
        return isin && ison;
    }
}
