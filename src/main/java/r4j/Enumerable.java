package r4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * http://www.ruby-doc.org/core-2.2.0/Enumerable.html
 *
 * @param <T>
 */
public interface Enumerable<T> {

    public Enumerable<T> each(Consumer<? super T> action);

    default boolean areAll(Predicate<? super T> predicate) {
        AtomicBoolean ret = new AtomicBoolean(true);
        each(o -> ret.set(ret.get() && predicate.test(o)));
        return ret.get();
    }

    default boolean isAny(Predicate<? super T> predicate) {
        AtomicBoolean ret = new AtomicBoolean(false);
        each(o -> ret.set(ret.get() || predicate.test(o)));
        return ret.get();
    }

    default int count() {
        AtomicInteger ret = new AtomicInteger(0);
        each(o -> ret.incrementAndGet());
        return ret.get();
    }

    default int count(Predicate<? super T> predicate) {
        AtomicInteger ret = new AtomicInteger(0);
        each(o -> {
            if (predicate.test(o)) {
                ret.incrementAndGet();
            }
        });
        return ret.get();
    }

    default int count(T a) {
        return count(o -> o.equals(a));
    }
}
