package r4j.extra;

import r4j.ruby.Benchmark;

import java.util.concurrent.Callable;

public class Retry {
    private int maxTries = 5;
    private int delayMs = 200;

    public Retry() {
    }

    public Retry maxTries(int maxTries) {
        if (maxTries < 1) {
            throw new RuntimeException("maxTries needs to be > 0. Was " + maxTries);
        }
        this.maxTries = maxTries;
        return this;
    }

    public Retry delayMs(int delayMs) {
        this.delayMs = delayMs;
        return this;
    }

    public <V> V retry(Callable<V> callable) {
        int count = 0;
        Benchmark bm = new Benchmark();
        while (count++ < maxTries) {
            try {
                return callable.call();
            } catch (Exception e) {
                if (count == maxTries) {
                    throw new RuntimeException("Retried " + count + " times. Time " + bm.msFromStart() + "ms.", e);
                } else {
                    if (delayMs > 0) {
                        try {
                            Thread.sleep(delayMs);
                        } catch (InterruptedException e1) {
                        }
                    }
                }
            }
        }
        return null;
    }

    public void retry(Runnable runnable) {
        retry(() -> {
            runnable.run();
            return null;
        });
    }
}
