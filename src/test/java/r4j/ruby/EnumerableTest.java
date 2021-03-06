package r4j.ruby;

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

    @Test
    public void find() {
        assertEquals(null, array().find(notNull));
        assertEquals("a", array("a").find(notNull));
        assertEquals("a", array(null, "a").find(notNull));
    }

    @Test
    public void map() {
        Array<Integer> arr = array(1, 2, 3, 4);
        assertEquals(array(2, 3, 4, 5), arr.map(i -> i + 1));
    }

    @Test
    public void select() {
        Array<Integer> arr = array(1, 2, 3, 4);
        assertEquals(array(2, 4), arr.select(i -> (i % 2) == 0));
    }

    @Test
    public void maxBy() {
        Array<String> arr = array("a", "ab", "abc", "abcd");
        assertEquals(array("abcd", "abc"), arr.maxBy(2, String::length));
        assertEquals("abcd", arr.maxBy(String::length));
        arr.clear();
        assertEquals(null, arr.maxBy(String::length));
        assertEquals(null, arr.maxBy(String::length));
    }
}
