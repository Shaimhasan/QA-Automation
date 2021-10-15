package validator;

public enum FailureFlag {
    HARD_STOP_ON_FAILURE("HardStopOnFailure"),
    CONTINUE_ON_FAILURE("ContinueOnFailure");

    public final String label;

    FailureFlag(String label) {
        this.label = label;
    }

    public static FailureFlag valueOfLabel(String label) {
        for (FailureFlag o : values()) {
            if (o.label.equalsIgnoreCase(label)) {
                return o;
            }
        }
        throw new IllegalArgumentException(String.format("Failure Flag of '%s' not supported", label));
    }
}
