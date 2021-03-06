# r4j - Best of Ruby, on Java

Java 8 brought Lambda Expressions and a more functional coding style to Java.
Ruby has useful APIs that leverage functional programming in a straightforward manner.
r4j brings those tasty Ruby APIs to Java.   

# Examples

Array manipulations - http://www.ruby-doc.org/core-2.2.0/Array.html
```java
import static r4j.Ruby.*;

String s = array(1,2,null,3).compact().map(i -> i + 1).join(", "); // "2, 3, 4"
```

Benchmark - http://www.ruby-doc.org/stdlib-2.2.0/libdoc/benchmark/rdoc/Benchmark.html
```java
import r4j.Ruby.Benchmark;

String report = Benchmark.bm(bm -> {
    bm.report("a", () -> sleep(10));
    bm.report("bb", () -> sleep(5));
});
```

Extra functionality not implemented by the Ruby stdlib
```java
import r4j.extra.Retry;

OperationResult result = new Retry().maxTries(5).retry(() -> unreliableOperation());
```

# How to improve r4j?

1. Participate - Post a feature request or comment @ https://github.com/mikko-apo/r4j/issues

And / Or

1. Fork
2. Add feature and unit tests
3. Pull request
 
# Todo

* Maven publishing
