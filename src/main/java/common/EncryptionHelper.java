package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;

public class EncryptionHelper {
    private EncryptionHelper() { //Utility class
    }

    public static String parseSecureText(String propsFilePath, String value) {
        String parseValue = Property.getProperty(propsFilePath, value);
        if (parseValue != null) {
            return decrypt(parseValue);
        }
        return null;
    }

    private static String decrypt(String encodedString) {
        if (encodedString != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            return new String(decodedBytes);
        } else {
            getLogger().error("Null string found in the secureText properties file");
            return null;
        }
    }

    private static Logger getLogger() {
        return LogManager.getLogger(EncryptionHelper.class);
    }
}
