package stg.util;

import java.util.*;

public class WrappedList<T> {
    private List<T> list;

    public WrappedList(List<T> list) {
        this.list = list;
    }

    public List<T> get() {
        return this.list;
    }

    public void set(List<T> list) {
        this.list = list;
    }

    public void add(T v) {
        this.list.add(v);
    }
}
