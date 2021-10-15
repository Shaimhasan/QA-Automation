package common;

public class StringHelper {

    private StringHelper() {
        //Utility class
    }

    /**
     * @param stringWithSpecialChars
     * @return String with spaces and special characters removed
     */
    public static String removeSpecialChars(String stringWithSpecialChars) {
        stringWithSpecialChars = stringWithSpecialChars.replaceAll("\\s+", "");
        return stringWithSpecialChars.replaceAll("[^a-zA-Z0-9_-]+", "");
    }
}
