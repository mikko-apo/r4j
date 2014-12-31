package r4j;

import r4j.ruby.Array;
import r4j.ruby.Range;

public class Ruby {
    public static <T> Array<T> array(T... a) {
        return new Array<>(a);
    }

    public static Range range(int first, int last) {
        return new Range(first, last);
    }
}
