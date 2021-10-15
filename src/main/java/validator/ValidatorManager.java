package validator;

public abstract class ValidatorManager {

    protected abstract void assertEquals(Object actual, Object expected);

    protected abstract void assertNotEquals(Object actual, Object expected);

    protected abstract void assertLessThan(Object actual, Object expected);

    protected abstract void assertLessThanOrEqual(Object actual, Object expected);

    protected abstract void assertGreaterThan(Object actual, Object expected);

    protected abstract void assertGreaterThanOrEqual(Object actual, Object expected);

    protected abstract void assertContains(Object actual, Object expected);

    protected abstract void assertDoesNotContain(Object actual, Object expected);
}
