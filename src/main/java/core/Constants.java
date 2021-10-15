package core;

public class Constants {

    public static final String BASEPATH = System.getProperty("user.dir") + "/src/test/resources/";
    public static final String SELENIUMRUNTIMEPATH = BASEPATH + "config/selenium/" + "runtime.properties";
    public static final String KEYBOARDKEYSJSON = BASEPATH + "config/custom/" + "keyboard.json";
    public static final String ENVIRONMENTPATH = BASEPATH + "config/environments/";
    public static final String SCREENSHOTPATH = BASEPATH + "screenshots/";
    public static final String DOWNLOADPATH = System.getProperty("user.dir") + "/target/downloads/";

    //Framework attributes
    public static final String ATTRIBUTE_FOR_TABLE_CELL_COMPARE = "fw.attributeForTableCellCompare";
    public static final String ATTRIBUTE_FOR_TABLE_HEADER_COMPARE = "fw.attributeForTableHeaderCompare";
    public static final String XPATH_FOR_LABELS = "fw.xPathForLabels";
    public static final String XPATH_FOR_LABELS_DELIMITER = "--XPATH--";

    public Constants() {
        //utility class
    }
}
