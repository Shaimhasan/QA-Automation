package validator;

import common.TestContext;

import static org.assertj.core.api.Assertions.assertThat;
import static validator.FailureFlag.HARD_STOP_ON_FAILURE;
import static validator.FailureFlag.valueOfLabel;

public class StringValidator extends ValidatorManager {
    private FailureFlag onFailureFlag;

    public StringValidator(String onFailureFlag) {
        this.onFailureFlag = valueOfLabel(onFailureFlag);
    }

    @Override
    protected void assertEquals(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isEqualTo(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).isEqualTo(expected.toString());
        }
    }

    @Override
    protected void assertNotEquals(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isNotEqualTo(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual).isNotEqualTo(expected);
        }
    }

    @Override
    protected void assertLessThan(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isLessThan(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).isLessThan(expected.toString());
        }
    }

    @Override
    protected void assertLessThanOrEqual(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isLessThanOrEqualTo(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).isGreaterThan(expected.toString());
        }
    }

    @Override
    protected void assertGreaterThan(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isGreaterThan(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).isGreaterThan(expected.toString());
        }
    }

    @Override
    protected void assertGreaterThanOrEqual(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).isGreaterThanOrEqualTo(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).isGreaterThan(expected.toString());
        }
    }

    @Override
    protected void assertContains(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).contains(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).contains(expected.toString());
        }
    }

    protected void assertDoesNotContain(Object actual, Object expected) {
        if (onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(actual.toString()).doesNotContain(expected.toString());
        } else {
            TestContext.getInstance().sa().assertThat(actual.toString()).doesNotContain(expected.toString());
        }
    }

}
