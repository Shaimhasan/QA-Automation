package validator;

//import automation.library.alm.core.ALMContext;

import common.TestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reporting.Reporter;

public class AssertHelper {
    private ThreadLocal<ValidatorManager> validatorManager = new InheritableThreadLocal<>();
    private ThreadLocal<ComparisonType> comparisonType = new InheritableThreadLocal<>();
    private ThreadLocal<ComparisonOperator> comparisonOperator = new InheritableThreadLocal<>();
    private ThreadLocal<String> onFailureFlag = new InheritableThreadLocal<>();
    protected Logger log = LogManager.getLogger(this.getClass().getName());

    public AssertHelper(String comparisonType, String comparisonOperator, String onFailureFlag) {
        this.comparisonType.set(ComparisonType.valueOfLabel(comparisonType));
        this.comparisonOperator.set(ComparisonOperator.valueOfLabel(comparisonOperator));
        this.validatorManager.set(setValidator(onFailureFlag));
        this.onFailureFlag.set(onFailureFlag);
    }

    public AssertHelper(ComparisonType comparisonType, ComparisonOperator comparisonOperator, String onFailureFlag) {
        this.comparisonType.set(comparisonType);
        this.comparisonOperator.set(comparisonOperator);
        this.validatorManager.set(setValidator(onFailureFlag));
        this.onFailureFlag.set(onFailureFlag);
    }

    private ValidatorManager setValidator(String onFailureFlag) {
        switch (comparisonType.get()) {
            case COMPARE_DATES:
                return new DateValidator(onFailureFlag);
            case COMPARE_STRINGS:
                return new StringValidator(onFailureFlag);
            case COMPARE_NUMBERS:
                return new NumberValidator(onFailureFlag);
            case COMPARE_LISTS:
                return new ListValidator(onFailureFlag);
            default:
                throw new IllegalArgumentException(String.format("Comparison Type of '%s' not supported", comparisonType.get()));
        }
    }

    public void performValidation(Object actual, Object expected) {
        setExpectedAndActual(actual, expected);

        try {
            switch (comparisonOperator.get()) {
                case EQ:
                    this.validatorManager.get().assertEquals(actual, expected);
                    break;
                case NE:
                    this.validatorManager.get().assertNotEquals(actual, expected);
                    break;
                case GT:
                    this.validatorManager.get().assertGreaterThan(actual, expected);
                    break;
                case LT:
                    this.validatorManager.get().assertLessThan(actual, expected);
                    break;
                case GTE:
                    this.validatorManager.get().assertGreaterThanOrEqual(actual, expected);
                    break;
                case LTE:
                    this.validatorManager.get().assertLessThanOrEqual(actual, expected);
                    break;
                case CONTAINS:
                    this.validatorManager.get().assertContains(actual, expected);
                    break;
                case NOT_CONTAINS:
                    this.validatorManager.get().assertDoesNotContain(actual, expected);
                    break;
            }

            if (!TestContext.getInstance().sa().wasSuccess() &&
                this.onFailureFlag.get().equalsIgnoreCase("ContinueOnFailure")) {
                Reporter.addStepLog("FAIL", getResultMessage(actual.toString(), expected.toString()));
            }

            cleanUpThreadLocal();
        } catch (AssertionError e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private void cleanUpThreadLocal() {
        validatorManager.remove();
        comparisonType.remove();
        comparisonOperator.remove();
        onFailureFlag.remove();
    }

    public String getResultMessage(String actual, String expected) {
        return String.format("%s -> Expecting \"%s\" to be %s \"%s\".",
                             comparisonType.get(),
                             actual,
                             comparisonOperator.get().label.toLowerCase(),
                             expected);
    }

    private void setExpectedAndActual(Object actual, Object expected) {
        String expectedResult = String.format("Expected value: \"%s\"", getResultAsString(expected));
        String actualResult = String.format("Actual value: \"%s\"", getResultAsString(actual));

        Reporter.addStepLog(expectedResult);
        Reporter.addStepLog(actualResult);
        log.debug(expectedResult);
        log.debug(actualResult);

        if (Boolean.parseBoolean(System.getProperty("fw.updateALMFlag"))) {
            //ALMContext.getInstance().addLastStep(new ALMRunStep(expectedResult, actualResult));
        }
    }

    private Object getResultAsString(Object result) {
        if(comparisonType.get() == ComparisonType.COMPARE_NUMBERS) {
            return Double.parseDouble(result.toString());
        } else {
            return result;
        }
    }
}
