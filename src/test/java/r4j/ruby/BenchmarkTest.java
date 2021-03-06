package r4j.ruby;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static r4j.extra.Retry.sleep;

public class BenchmarkTest {
    @Test
    public void report() {
        String report = Benchmark.bmReport(bm -> {
            bm.report("a", () -> sleep(10));
            bm.report("bb", () -> sleep(5));
        });
        assertThat(report, containsString("a  "));
        assertThat(report, containsString("bb "));
    }
}
