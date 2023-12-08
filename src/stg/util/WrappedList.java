package stg.util;

import java.util.*;

// NOTE: 配列のフィルタリングで配列の再生成・置換を行う場合は、置換前の配列が無効になってしまう。
//       それを防ぐために、置換をこのインスタンスに隠蔽する。
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
