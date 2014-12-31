package r4j;

public class Ruby {
    public static <T> Array<T> array(T... a) {
        return new Array<>(a);
    }

    public static Range range(int first, int last) {
        return new Range(first, last);
    }
}
