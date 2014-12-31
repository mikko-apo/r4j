package r4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static r4j.Ruby.array;
import static r4j.Ruby.range;

public class ArrayTest {

    @Test
    public void example1() {
        assertEquals("2, 3, 4", array(1, 2, null, 3).compact().map(i -> i + 1).join(", "));
    }

    @Test
    public void mixedContent() {
        assertEquals("1, a, 3, 4.0", array(1, "a", null, 3L, 4.0).compact().join(", "));
    }

    @Test
    public void get() {
        Array arr = array(1, 2, 3, 4);
        assertEquals(1, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(null, arr.get(100));
        assertEquals(4, arr.get(-1));
        assertEquals(3, arr.get(-2));
        assertEquals(array(2, 3), arr.get(1, 2));
        assertEquals(array(2, 3, 4), arr.get(1, 3));
        assertEquals(array(2, 3, 4), arr.get(1, 4));
        assertEquals(array(4), arr.get(-1, 1));
        assertEquals(array(4), arr.get(-1, 2));
        assertEquals(array(3, 4), arr.get(-2, 2));
        assertEquals(array(2, 3), arr.get(range(1, 2)));
    }

    @Test
    public void compact() {
        Array<Integer> arr = array(1, null, 3, 4);
        assertEquals(array(1, 3, 4), arr.compact());
    }

    @Test
    public void join() {
        Array<Integer> arr = array(1, null, 3, 4);
        assertEquals("1,,3,4", arr.join(","));
    }

    @Test
    public void push() {
        Array<Integer> newArr = new Array<Integer>();
        newArr.push(1, 2).push(3);
        assertEquals(array(1, 2, 3), newArr);
    }

    @Test
    public void pop() {
        Array<Integer> arr = new Array<Integer>();
        assertEquals(null, arr.pop());
        arr.push(1, 2).push(3, 4);
        assertEquals(new Integer(4), arr.pop());
        assertEquals(array(2, 3), arr.pop(2));
        assertEquals(array(1), arr);
    }

    @Test
    public void each() {
        Array<Integer> newArr = new Array<Integer>();
        Array<Integer> otherArr = array(1, 2, 3, 4).each(i -> newArr.push(i + 1)).map(i -> i + 2);
        assertEquals(array(2, 3, 4, 5), newArr);
        assertEquals(array(3, 4, 5, 6), otherArr);
    }

    @Test
    public void uniq() {
        assertEquals(array(1, 2), array(1, 1, 2, 1, 2).uniq());
    }
}
