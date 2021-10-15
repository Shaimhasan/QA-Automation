package validator;

public enum ComparisonType {
    COMPARE_NUMBERS("Compare_Numbers"),
    COMPARE_DATES("Compare_Dates"),
    COMPARE_STRINGS("Compare_Strings"),
    COMPARE_PARTIAL_TEXT("Compare_Partial_Text"),
    COMPARE_LISTS("Compare_Lists"),
    COMPARE_API_RESPONSE("Compare_API_Response");

    public final String label;

    ComparisonType(String label) {
        this.label = label;
    }

    public static ComparisonType valueOfLabel(String label) {
        for (ComparisonType o : values()) {
            if (o.label.equalsIgnoreCase(label)) {
                return o;
            }
        }
        throw new IllegalArgumentException(String.format("Comparison Type of '%s' not supported", label));
    }

}
