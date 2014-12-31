package r4j.ruby;

public class Benchmark {
    private long start = System.currentTimeMillis();

    public Benchmark() {

    }

    public static long measure(Runnable runnable) {
        Benchmark bm = new Benchmark();
        runnable.run();
        return bm.msFromStart();
    }

    public long msFromStart() {
        return System.currentTimeMillis() - start;
    }
}
