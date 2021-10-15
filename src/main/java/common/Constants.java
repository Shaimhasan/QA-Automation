package common;

public class Constants {
	private static final String USER_DIR = System.getProperty("user.dir");
	public static final String BASEPATH = USER_DIR + "/src/test/resources/";
	public static final String GENERATEDCLASSPATH = USER_DIR + "/target/test-classes/";
	public static final String PAGEOBJECTSJAR = USER_DIR + "/target/lib/";
	public static final String ENVIRONMENTPATH = BASEPATH + "config/environments/";
	public static final String PAGEOBJECTSPATH = GENERATEDCLASSPATH + "pageobjects/";
	public static final String PDFOBJECTSPATH = GENERATEDCLASSPATH + "pdfobjects/";
	public static final String FEATUREFILEPATH = GENERATEDCLASSPATH + "features/";
	public static final String TESTDATAPATH = BASEPATH + "testdata/";
	public static final String PAGEOBJECTJAVAPATH = USER_DIR + "/src/test/java/pageobjects/";
	public static final String RUNTIMEPATH = BASEPATH + "config/" + "runtime.properties";
	public static final String LOGPATH = USER_DIR + "/logs/";
}
