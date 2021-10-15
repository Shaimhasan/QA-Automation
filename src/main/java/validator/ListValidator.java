package validator;

import common.TestContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static validator.FailureFlag.HARD_STOP_ON_FAILURE;
import static validator.FailureFlag.valueOfLabel;

public class ListValidator extends ValidatorManager {
    private FailureFlag onFailureFlag;

    public ListValidator(String onFailureFlag) {
        this.onFailureFlag = valueOfLabel(onFailureFlag);
    }

    @Override
    protected void assertEquals(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat((List)actual).containsOnlyElementsOf((List)expected);
        } else {
            TestContext.getInstance().sa().assertThat((List) actual).containsOnlyElementsOf((List)expected);
        }
    }

    @Override
    protected void assertNotEquals(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat((List) actual).isNotSameAs(expected);
        } else {
            TestContext.getInstance().sa().assertThat((List) actual).isNotSameAs(expected);
        }
    }

    @Override
    protected void assertLessThan(Object actual, Object expected) {
        throw new UnsupportedOperationException("assertLessThan is not supported for Compare_Lists");
    }

    @Override
    protected void assertLessThanOrEqual(Object actual, Object expected) {
        throw new UnsupportedOperationException("assertLessThanOrEqual is not supported for Compare_Lists");
    }

    @Override
    protected void assertGreaterThan(Object actual, Object expected) {
        throw new UnsupportedOperationException("assertGreaterThan is not supported for Compare_Lists");
    }

    @Override
    protected void assertGreaterThanOrEqual(Object actual, Object expected) {
        throw new UnsupportedOperationException("assertGreaterThanOrEqual is not supported for Compare_Lists");
    }

    @Override
    protected void assertContains(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat((List)actual).containsAnyElementsOf((List)expected);
        } else {
            TestContext.getInstance().sa().assertThat((List) actual).containsAnyElementsOf((List)expected);
        }
    }

    @Override
    protected void assertDoesNotContain(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat((List) actual).doesNotContainAnyElementsOf((List)expected);
        } else {
            TestContext.getInstance().sa().assertThat((List) actual).doesNotContainAnyElementsOf((List)expected);
        }
    }

}