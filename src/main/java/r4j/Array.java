package r4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * http://www.ruby-doc.org/core-2.2.0/Array.html
 *
 * @param <T> Type of objects stored in Array
 */
public class Array<T> implements Enumerable<T> {

    private final List<T> list;

    public Array(T... a) {
        list = new ArrayList<T>(Arrays.asList(a));
    }

    public Array(List<T> l) {
        list = new ArrayList<T>(l);
    }

    public T get(int i) {
        int index = convertPosition(i);
        if (index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    public Array<T> get(int first, int length) {
        int fromIndex = convertPosition(first);
        return new Array<>(new ArrayList<>(list.subList(fromIndex, limitPosition(fromIndex + length))));
    }

    public Array<T> get(Range range) {
        return get(range.first, convertPosition(range.first) + range.last - 1);
    }

    @Override
    public Array<T> each(Consumer<? super T> action) {
        list.stream().forEach(action);
        return this;
    }

    public Array<T> compact() {
        return select(o -> o != null);
    }

    public String join(String delimiter) {
        return String.join(delimiter, map(o -> replaceNull(o)).list);
    }

    public Array<T> push(T... a) {
        list.addAll(Arrays.asList(a));
        return this;
    }

    public T pop() {
        return list.isEmpty() ? null : list.remove(list.size() - 1);
    }

    public Array<T> pop(int n) {
        Array<T> ret = get(-n, n);
        for (int i = 0; i < n; i++) {
            if (list.size() > 0) {
                list.remove(list.size() - 1);
            }
        }
        return ret;
    }

    private String replaceNull(T o) {
        return o != null ? o.toString() : "";
    }

    private int convertPosition(int i) {
        return i >= 0 ? i : list.size() + i;
    }

    private int limitPosition(int i) {
        return i < list.size() ? i : list.size();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Array && ((Array) obj).list.equals(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
