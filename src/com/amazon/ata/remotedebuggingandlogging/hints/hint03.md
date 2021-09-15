We can create a Logger instance using the LogManager. We want to create a Logger whose name is the class emitting the
logs. We can do this by using the `getLogger(...)` method and passing it the current class.

For example:

```java
public class AuthenticationService {
    private Logger log = LogManager.getLogger(AuthenticationService.class);
}
```
