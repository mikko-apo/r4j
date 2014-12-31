package r4j.ruby;

import java.util.function.Consumer;

import static r4j.Ruby.array;
import static r4j.ruby.Array.replaceNull;

/**
 * http://www.ruby-doc.org/stdlib-2.2.0/libdoc/benchmark/rdoc/Benchmark.html
 */
public class Benchmark {
    private long start = System.currentTimeMillis();

    public Benchmark() {

    }

    public static long measure(Runnable runnable) {
        Benchmark bm = new Benchmark();
        runnable.run();
        return bm.msFromStart();
    }

    public static void bm(Consumer<BenchmarkReport> testRun) {
        System.out.println(bmReport(testRun));
    }

    public static String bmReport(Consumer<BenchmarkReport> testRun) {
        BenchmarkReport reporter = new BenchmarkReport();
        testRun.accept(reporter);
        return reporter.produceReport();
    }

    public long msFromStart() {
        return System.currentTimeMillis() - start;
    }

    public static class BenchmarkReport {
        Array<BenchmarkReportLine> lines = array();

        public void report(Runnable runnable) {
            report(null, runnable);
        }

        public void report(String label, Runnable runnable) {
            lines.push(new BenchmarkReportLine(label, measure(runnable)));
        }

        public String produceReport() {
            String longestLabel = longest(lines.map(l -> l.label));
            String longestMeasure = longest(lines.map(l -> l.measure));
            return lines.map(l -> reportLine(longestLabel, longestMeasure, l)).join("\n");
        }

        private String longest(Array<String> arr) {
            return arr.compact().maxBy(String::length);
        }

        private String reportLine(String longestLabel, String longestMeasure, BenchmarkReportLine l) {
            if (longestLabel != null) {
                return String.format("%-" + longestLabel.length() + "s %" + longestMeasure.length() + "s", replaceNull(l.label), l.measure);
            } else {
                return String.format("%" + longestMeasure.length() + "s", l.measure);
            }
        }
    }

    private static class BenchmarkReportLine {
        final String label;
        final String measure;

        public BenchmarkReportLine(String label, Long measure) {
            this.label = label;
            this.measure = measure.toString();
        }
    }
}
