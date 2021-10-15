package validator;

public enum ComparisonOperator {
    EQ("Equal To"),
    GTE("Greater Than or Equal To"),
    GT("Greater Than"),
    LT("Less Than"),
    LTE("Less Than or Equal To"),
    NE("Not Equal To"),
    CONTAINS("Contains"),
    NOT_CONTAINS("Does Not Contain");

    public final String label;

    ComparisonOperator(String label) {
        this.label = label;
    }

    public static ComparisonOperator valueOfLabel(String label) {
        for (ComparisonOperator o : values()) {
            if (o.label.equalsIgnoreCase(label)) {
                return o;
            }
        }
        throw new IllegalArgumentException(String.format("Comparison Operator of '%s' not supported", label));
    }

}
