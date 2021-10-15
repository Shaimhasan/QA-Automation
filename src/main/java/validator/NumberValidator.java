package validator;

import common.TestContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static validator.FailureFlag.HARD_STOP_ON_FAILURE;
import static validator.FailureFlag.valueOfLabel;

public class NumberValidator extends ValidatorManager {
    private FailureFlag onFailureFlag;

    public NumberValidator(String onFailureFlag) {
        this.onFailureFlag = valueOfLabel(onFailureFlag);
    }

    @Override
    protected void assertEquals(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isEqualTo(Double.parseDouble(expected.toString()),
                                                             withPrecision(2d));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isEqualTo(Double.parseDouble(expected.toString()),
                                                                                            withPrecision(2d));
        }
    }

    @Override
    protected void assertNotEquals(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isNotEqualTo(Double.parseDouble(expected.toString()));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isNotEqualTo(Double.parseDouble(expected.toString()));
        }

    }

    @Override
    protected void assertLessThan(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isLessThan(Double.parseDouble(expected.toString()));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isLessThan(Double.parseDouble(expected.toString()));
        }
    }

    @Override
    protected void assertLessThanOrEqual(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isLessThanOrEqualTo(Double.parseDouble(expected.toString()));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isLessThanOrEqualTo(Double.parseDouble(expected.toString()));
        }
    }

    @Override
    protected void assertGreaterThan(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isGreaterThan(Double.parseDouble(expected.toString()));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isGreaterThan(Double.parseDouble(expected.toString()));
        }
    }

    @Override
    protected void assertGreaterThanOrEqual(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(Double.parseDouble(actual.toString())).isGreaterThanOrEqualTo(Double.parseDouble(expected.toString()));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(Double.parseDouble(actual.toString())).isGreaterThanOrEqualTo(Double.parseDouble(expected.toString()));
        }
    }

    @Override
    protected void assertContains(Object actual, Object expected) {
        throw new UnsupportedOperationException("Contains comparison not supported for Compare_Numbers");
    }

    @Override
    protected void assertDoesNotContain(Object actual, Object expected) {
        throw new UnsupportedOperationException("Does Not Contain assertion not available for Compare_Numbers.");
    }

}
