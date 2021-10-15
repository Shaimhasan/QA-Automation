package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reporting.Reporter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.Constants.ENVIRONMENTPATH;

public class AutoEngParser {
    private static final String REGEX_KEYNAME = "#\\((.*)\\)";
    private static final String ERROR = "ERROR";
    protected static final String ENV_PROP = "cukes.env";
    protected static final String PROPERTIES_EXT = ".properties";

    private AutoEngParser() {
        //utility class
    }

    public static String parseValue(String value) {
        return String.valueOf(parseValueToObject(value));
    }

    public static Object parseValueToObject(String value) {
        Object returnValue;

        Pattern p = Pattern.compile(REGEX_KEYNAME);
        Matcher m = p.matcher(value);

        if (m.matches()) {
            String valToRetrieve = m.group(1);

            if (TestContext.getInstance().testdataGet(valToRetrieve) == null) {
                throw new IllegalArgumentException(String.format("Entry not found in the data dictionary for key: \"%s\".", valToRetrieve));
            } else {
                returnValue = TestContext.getInstance().testdataGet(valToRetrieve);
            }
        } else {
            returnValue = value;
        }

        return returnValue;
    }

    public static String parseDictionaryKey(String keyName) {
        return getValueOrVariable(keyName);
    }

    public static String parseSecureText(String value) {
        String propsFilePath = ENVIRONMENTPATH + "securetext-" + Property.getVariable(ENV_PROP) + PROPERTIES_EXT;

        String valToParse = getValueOrVariable(value);
        String secureValue = EncryptionHelper.parseSecureText(propsFilePath, valToParse);

        if (secureValue != null)
            return secureValue;
        else {
            String errorMsg = String.format("Entry for key '%s' is not there in %s", value, propsFilePath);
            getLogger().error(errorMsg);
            Reporter.addStepLog(ERROR, errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static String parseUser(String value) {
        String propsFilePath = ENVIRONMENTPATH + "securetext-" + Property.getVariable(ENV_PROP) + PROPERTIES_EXT;

        String valToParse = getValueOrVariable(value);
        String parseValue = Property.getProperty(propsFilePath, valToParse);

        if (parseValue != null)
            return parseValue;
        else {
            String errorMsg = String.format("Entry for key '%s' is not there in %s", value, propsFilePath);
            getLogger().error(errorMsg);
            Reporter.addStepLog(ERROR, errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static String getValueOrVariable(String value) {
        String valToParse;
        Pattern p = Pattern.compile(REGEX_KEYNAME);
        Matcher m = p.matcher(value);

        if (m.matches()) {
            valToParse = m.group(1);
        } else {
            valToParse = value;
        }
        return valToParse;
    }

    public static String parseIfVariable(String value) {
        Pattern p = Pattern.compile(REGEX_KEYNAME);
        Matcher m = p.matcher(value);

        if (m.matches()) {
            return parseUser(value);
        }

        return value;
    }

    private static Logger getLogger() {
        return LogManager.getLogger(AutoEngParser.class);
    }

}
