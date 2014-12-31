# r4j

Best of Ruby, on Java

# Why?

Java 8 brought Lambda Expressions and more functional coding style to Java.
Ruby has excellent APIs that leverage functional programming in a straightforward manner.
r4j brings those APIs to Java.   

# Examples

```java
import static r4j.Ruby.*;

String s = array(1,2,null,3).compact().map(i -> i + 1).join(", "); // "2, 3, 4"
```

# How to add new features?

1. Participate - Post a feature request @ https://github.com/mikko-apo/r4j/issues

And / Or

1. Fork
2. Add feature and unit tests
3. Pull request
 
# Todo

* Maven publishing

# Version history