package r4j.extra;

import org.junit.Test;
import r4j.ruby.Array;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static r4j.Ruby.array;

public class RetryTest {
    Retry runner = new Retry().delayMs(1).maxTries(2);

    @Test
    public void noExceptionsRunsOnce() {
        Array<Integer> arr = array();
        runner.retry(() -> arr.push(1));
        assertEquals(array(1), arr);
    }

    @Test
    public void supportRunnable() {
        Array<Integer> arr = array();
        runner.retry((Runnable) () -> arr.push(1));
        assertEquals(array(1), arr);
    }

    @Test
    public void noExceptionsRunsOnceReturnsValue() {
        assertEquals("a", runner.retry(() -> "a"));
    }

    @Test
    public void failingThrowsRuntimeException() {
        Array<Integer> arr = array();
        try {
            runner.retry((Runnable) () -> {
                arr.push(1);
                throw new RuntimeException("By design");
            });
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), containsString("Retried 2 times. Time "));
            assertEquals("By design", e.getCause().getMessage());
        }
        assertEquals(array(1, 1), arr);
    }
}
