package r4j;

import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static r4j.Ruby.array;

public class EnumerableTest {
    Predicate<Object> notNull = o -> o != null;

    @Test
    public void areAll() {
        assertEquals(true, array().areAll(notNull));
        assertEquals(true, array(1).areAll(notNull));
        assertEquals(false, array(null, 1).areAll(notNull));
    }

    @Test
    public void isAny() {
        assertEquals(false, array().isAny(notNull));
        assertEquals(true, array(1).isAny(notNull));
        assertEquals(true, array(null, 1).isAny(notNull));
    }

    @Test
    public void count() {
        assertEquals(0, array().count());
        assertEquals(1, array(1).count());
        assertEquals(1, array(1).count(1));
        assertEquals(0, array(1).count(2));
        assertEquals(0, array(1).count(o -> o % 2 == 0));
    }
}
