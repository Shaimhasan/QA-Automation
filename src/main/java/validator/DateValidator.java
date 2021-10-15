package validator;

import common.TestContext;

import static common.DateHelper.formatAsDate;
import static validator.FailureFlag.HARD_STOP_ON_FAILURE;
import static validator.FailureFlag.valueOfLabel;
import static org.assertj.core.api.Assertions.assertThat;

public class DateValidator extends ValidatorManager {
    private FailureFlag onFailureFlag;

    public DateValidator(String onFailureFlag) {
        this.onFailureFlag = valueOfLabel(onFailureFlag);
    }

    @Override
    protected void assertEquals(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isEqualTo(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isEqualTo(formatAsDate(expected));
        }
    }

    @Override
    protected void assertNotEquals(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isNotEqualTo(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isNotEqualTo(formatAsDate(expected));
        }
    }

    @Override
    protected void assertLessThan(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isBefore(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isBefore(formatAsDate(expected));
        }
    }

    @Override
    protected void assertLessThanOrEqual(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isBeforeOrEqualTo(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isBeforeOrEqualTo(formatAsDate(expected));
        }
    }

    @Override
    protected void assertGreaterThan(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isAfter(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isAfter(formatAsDate(expected));
        }
    }

    @Override
    protected void assertGreaterThanOrEqual(Object actual, Object expected) {
        if(onFailureFlag == HARD_STOP_ON_FAILURE) {
            assertThat(formatAsDate(actual)).isAfterOrEqualTo(formatAsDate(expected));
        } else {
            TestContext.getInstance().sa()
                       .assertThat(formatAsDate(actual)).isAfterOrEqualTo(formatAsDate(expected));
        }
    }

    @Override
    protected void assertContains(Object actual, Object expected) {
        throw new UnsupportedOperationException("Contains assertion not available for Compare_Dates.");
    }

    @Override
    protected void assertDoesNotContain(Object actual, Object expected) {
        throw new UnsupportedOperationException("Does Not Contain assertion not available for Compare_Dates.");
    }
}
