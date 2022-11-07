package core;

//import automation.library.alm.core.ALMContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.AutoEngParser;
import common.Property;
import common.TestContext;
import io.cucumber.java8.En;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import reporting.Reporter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static common.DateHelper.formatAsDate;
import static common.DateHelper.getDateTimeFormat;
import static core.Constants.ENVIRONMENTPATH;
import static core.ObjectFinder.getMatchingElement;


public class BaseStepsEngine implements En {

    private static final String ERROR = "ERROR";
    protected final Logger log = LogManager.getLogger(BaseStepsEngine.class);
    protected static final String REGEX_FULLKEY = "(#\\(\\S*?\\))";
    protected static final String REGEX_VALIDATION = "\"([^\"]*)\"";
    protected static final String ENV_PROP = "cukes.env";
    protected static final String PROPERTIES_EXT = ".properties";
    protected static final String LEANFT = "LeanFT";
    protected static final String SELENIUM = "Selenium";
    protected static final String API = "API";
    protected static final String DB = "DB";
    protected static final String ENTERED_VALUE = "Entered value: \"%s\"";
    protected static final String STORED_VALUE = "Stored value \"%s\" into key \"%s\"";
    private static final String JAVAX_NET_SSL_TRUST_STORE = "javax.net.ssl.trustStore";

    public BaseStepsEngine() {
        //base steps
    }

    public SoftAssertions sa() {
        return TestContext.getInstance().sa();
    }

    public enum ScreenShotType {
        DISPLAY, AREA, SCROLLING;
    }

    /**
     * Generic method to find object
     *
     * @param objectName
     * @param pageName
     */
    protected Element getObject(String objectName, String pageName) {
        Object ele = getMatchingElement(objectName, pageName);
        Element returnVal;

        if (ele != null) {
            returnVal = (Element) ele;
            if (returnVal.element() != null)
                return returnVal;
            else {
                String errorMsg = String.format("The \"%s\" element is not present on the page : \"%s\"", objectName, pageName);
                log.error(errorMsg);
                throw new NotFoundException(errorMsg);
            }
        } else {
            throw new ObjectNotFoundException(String.format("The \"%s\" was not found in the page object: : \"%s\"",
                    objectName, pageName));
        }
    }

    /**
     * Generic method to find object lists
     *
     * @param objectName
     * @param pageName
     */
    protected List<Element> getObjects(String objectName, String pageName) {
        Object ele = getMatchingElement(objectName, pageName);

        if (ele != null) {
            return (List<Element>) ele;
        } else {
            throw new ObjectNotFoundException("The \"" + objectName + "\" element was not found in the page object: \"" + pageName + "\"");
        }
    }

    /**
     * Generic method to allow object Null
     *
     * @param objectName
     * @param pageName
     */

    protected Element getObjectAllowNull(String objectName, String pageName) {
        Object ele = getMatchingElement(objectName, pageName);

        if (ele != null) {
            return (Element) ele;
        } else {
            throw new ObjectNotFoundException(
                    "The \"" + objectName + "\" was not found in the page object: : \"" + pageName + "\"");
        }
    }

    protected List<Keys> getKeysToPress(String keySequence) {
        Gson gson = new GsonBuilder().create();
        List<ArrayList<String>> keyArrayList;
        Map<String, ArrayList<String>> keyArray = new HashMap<>();
        List<Keys> keysToPress = new ArrayList<>();

        try {
            keyArray = gson.fromJson(new FileReader(Constants.KEYBOARDKEYSJSON), Map.class);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }

        if (keyArray.size() > 0) {
            keyArrayList = keyArray.entrySet().stream()
                    .filter(key -> key.getKey().equalsIgnoreCase(keySequence))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            if (!keyArrayList.isEmpty()) {
                for (String key : keyArrayList.get(0)) {
                    keysToPress.add(Keys.valueOf(key));
                }
            } else {
                log.error("Unable to find key: '{}'. Is keyboard.json defined for the project?", keySequence);
            }
        }

        return keysToPress;
    }


    /**
     * @param value
     * @param formatType
     * @throws ParseException
     */
    protected String format(String value, String formatType) throws ParseException {
        if (formatTypeIsDate(formatType)) {
            return formatValueToDate(value, formatType);
        } else if (formatTypeIsPhone(formatType)) {
            return formatValueToPhone(value, formatType);
        } else if (formatTypeIsString(formatType)) {
            return formatValueToString(value, formatType);
        } else {
            return formatValueToNumber(value, formatType);
        }
    }


    private boolean formatTypeIsDate(String formatType) {
        return formatType.matches("(?i).*(DATE|MMDDYYYY|YYMMDD).*(?i)");
    }

    private boolean formatTypeIsPhone(String formatType) {
        return formatType.matches("(?i).*(PHONE|INTERNATIONAL).*(?i)");
    }

    private boolean formatTypeIsString(String formatType) {
        return formatType.matches("(?i).*(TRIM_SPACES|REMOVE_COMMA).*(?i)");
    }

    private String formatValueToString(String value, String formatType) {
        switch (formatType.toUpperCase()) {
            case "TRIM_SPACES":
                value = value.trim();
                log.info("Formatted as without spaces {}", value);
                return value;
            case "REMOVE_COMMA":
                value = value.replace(",", "");
                log.info("Formatted as without comma {}", value);
                return value;
            default:
                return value;
        }
    }

    private String formatValueToDate(String dateToFormat, String formatType) {
        LocalDateTime valueAsDate = formatAsDate(dateToFormat);
        final String datePattern1 = "^DATE-(PLUS|MINUS)(\\d+)(DAYS?|MONTHS?|YEARS?)$";
        final String datePattern2 = "^DATE-(.*)$";
        final String datePattern3 = "(?i)(MMDDYYYY|YYMMDD)(?i)";

        if (formatType.matches(datePattern1)) {
            Map<String, String> origFormatMap = getDateTimeFormat(dateToFormat);
            String originalFormat = origFormatMap.get("DATE") != null ? origFormatMap.get("DATE") : origFormatMap.get("DATE-TIME");

            Matcher m = Pattern.compile(datePattern1).matcher(formatType);
            if (m.matches()) {
                return updateDateByPeriod(valueAsDate, m.group(1), m.group(2), m.group(3)).format(DateTimeFormatter.ofPattern(originalFormat)
                        .withLocale(Locale.ENGLISH));
            }
        } else if (formatType.matches(datePattern2)) {
            Matcher m = Pattern.compile(datePattern2).matcher(formatType);
            if (m.matches()) {
                return valueAsDate.format(DateTimeFormatter.ofPattern(reformatDatePattern(m.group(1))).withLocale(Locale.ENGLISH));
            }
        } else if (formatType.matches(datePattern3)) {
            Matcher m = Pattern.compile(datePattern3).matcher(formatType);
            if (m.matches()) {
                return valueAsDate.format(DateTimeFormatter.ofPattern(reformatDatePattern(m.group(1))).withLocale(Locale.ENGLISH));
            }
        } else {
            logStepMessage(String.format("Unmatched date format type: %s. Returning original value.", formatType));
            return dateToFormat;
        }

        return dateToFormat;
    }


    private LocalDateTime updateDateByPeriod(LocalDateTime dateToFormat, String operation, String increment, String period) {
        if (operation.equalsIgnoreCase("PLUS")) {
            return dateToFormat.plus(getPeriod(period, increment));
        } else {
            return dateToFormat.minus(getPeriod(period, increment));
        }
    }

    private TemporalAmount getPeriod(String period, String increment) {
        switch (period) {
            case "DAY":
            case "DAYS":
                return Period.ofDays(Integer.parseInt(increment));
            case "MONTH":
            case "MONTHS":
                return Period.ofMonths(Integer.parseInt(increment));
            case "YEAR":
            case "YEARS":
                return Period.ofYears(Integer.parseInt(increment));
            default:
                return Period.ofDays(0);
        }
    }

    private String reformatDatePattern(String origPattern) {
        return origPattern.replace("D", "d").replace("Y", "y");
    }

    private String formatValueToPhone(String value, String formatType) {
        boolean checkformat = false;

        switch (formatType.toUpperCase()) {
            case "PHONE":
                Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
                checkformat = pattern.matcher(value).matches();
                if (!checkformat) value = value.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
                log.info("Formatted as PHONE {}", value);
                return value;
            case "INTERNATIONAL_PHONE":
                Pattern patternint = Pattern.compile("\\d{3}-\\d{15}");
                checkformat = patternint.matcher(value).matches();
                if (!checkformat) value = value.replaceFirst("(\\d{3})(\\d+)", "+$1 $2");
                log.info("Formatted as PHONE {}", value);
                return value;
            default:
                return value;
        }
    }

    private String formatValueToNumber(String value, String formatType) throws ParseException {
        boolean checkformat = false;

        switch (formatType.toUpperCase()) {
            case "REMOVELEADINGZEROS":
                checkformat = !value.startsWith("00");
                if (!checkformat) {
                    value = value.replaceFirst("^0+(?!$)", "");
                }
                log.info("Formatted as REMOVELEADINGZEROS {}", value);
                return value;
            case "TWOZEROSAFTERDECIMAL":
                checkformat = value.split(".")[1].length() == 2;
                if (!checkformat) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    value = df.format(value);
                }
                log.info("Formatted as TWOZEROSAFTERDECIMAL {}", value);
                return value;
            case "USD":
                DecimalFormat df = new DecimalFormat();
                value = df.parse(value).toString();
                NumberFormat numberCurFormat = NumberFormat.getCurrencyInstance(Locale.US);
                numberCurFormat.setMaximumFractionDigits(2);
                value = numberCurFormat.format(Float.valueOf(value));
                return value;
            case "PERCENTAGE":
                DecimalFormat df1 = new DecimalFormat();
                value = df1.parse(value).toString();
                NumberFormat numberFormat = NumberFormat.getPercentInstance();
                numberFormat.setMaximumFractionDigits(2);
                value = numberFormat.format(Float.valueOf(value));
                return value;
            case "ROUNDTOTWODECIMAL":
                value = String.format("%.2f", Double.parseDouble(value));
                log.info("Formatted as to two decimal places {}", value);
                return value;
            case "ROUNDOFF":
                value = String.format("%.0f", Double.parseDouble(value));
                log.info("Formatted as to round off", value);
                return value;
            default:
                log.warn("Unmatched format type: {}. Options are REMOVELEADINGZEROS | TWOZEROSAFTERDECIMAL | MMDDYYY | YYMMDD | PHONE | DATE-PLUS#DAYS | ROUNDTOTWODECIMAL | ROUNDOFF", formatType);
                return value;
        }
    }

    /**
     * @param value
     * @param delimiter
     * @return
     */
    public String split(String value, String delimiter) {
        String[] resultValues = new String[0];
        switch (delimiter.toUpperCase()) {
            case "COMMA":
                resultValues = value.split(",");
                log.info("Split string for COMMA {}", resultValues);
                break;
            case "PIPE":
                resultValues = value.split("\\|");
                log.info("Split string for PIPE {}", resultValues);
                break;
            case "COLON":
                resultValues = value.split(":");
                log.info("Split string for COLON {}", resultValues);
                break;
            case "SEMICOLON":
                resultValues = value.split(";");
                log.info("Split string for SEMICOLON {}", resultValues);
                break;
            default:
                log.warn("Given delimiter type doesn't exist: {}. Options are COMMA | PIPE | COLON | SEMICOLON", delimiter);
                break;
        }
        return resultValues[0];
    }

    protected String parseValue(String value) {
        return AutoEngParser.parseValue(value);
    }

    protected Object parseValueToObject(String value) {
        return AutoEngParser.parseValueToObject(value);
    }

    protected String parseDictionaryKey(String keyName) {
        return getValueOrVariable(keyName);
    }

    protected String parseSecureText(String value) {
        return AutoEngParser.parseSecureText(value);
    }

    protected String parseUser(String value) {
        return AutoEngParser.parseUser(value);
    }

    private String getValueOrVariable(String value) {
        return AutoEngParser.getValueOrVariable(value);
    }

    protected String parseIfVariable(String value) {
        return AutoEngParser.parseIfVariable(value);
    }

    protected void logStepMessage(String message) {
        logStepMessage("DEBUG", message);
    }

    private void logStepMessage(String type, String message) {
        Reporter.addStepLog(type, message);
        log.debug(message);

        if (Boolean.parseBoolean(System.getProperty("fw.updateALMFlag"))) {
            //ALMContext.getInstance().addLastStep(new ALMRunStep(null, message));
        }
    }

    protected String getConcatenatedVal(String valsToConcat, String splitDelimiter, String joinDelimiter) {
        List<String> stringsToCombine = Arrays.asList(valsToConcat.split(splitDelimiter));
        return stringsToCombine.stream()
                .map(value -> getDictionaryValOrRealVal(value.trim()))
                .filter(value -> !value.isEmpty())
                .collect(Collectors.joining(joinDelimiter));
    }

    protected String getDictionaryValOrRealVal(String value) {
        try {
            return parseValue(value);
        } catch (IllegalArgumentException e) {
            logStepMessage(ERROR, String.format("%s String will not include value at that key.", e.getMessage()));
            return "";
        }
    }

    protected String getSubstringVal(String fullValue, String substringPattern) {
        switch (substringPattern) {
            case "LAST-FOUR":
                return fullValue.substring(fullValue.length() - 4);
            case "BEFORE-PERCENTAGE":
                return fullValue.split("%")[0];
            case "FIRST-FOUR":
                return fullValue.substring(0, 4);
            case "AFTER-COLON":
                return fullValue.split(":")[1].trim();
            case "THIRD-FIFTH-SIXTH":
                fullValue = fullValue.substring(2, fullValue.length()).replaceAll("[!//%|^&*()#$@,!*_=-]", "");

                if (fullValue.length() == 4) {
                    return fullValue.substring(0, 1) + fullValue.substring(2, fullValue.length());
                }
                return fullValue;
            case "FIRST-TWO-NOSPECIALCHAR":
                return fullValue.substring(0, 3).replaceAll("[\\[\\]!//%|^&*()#$@,!*_=-]", "");
            default:
                List<String> stringIndices = Arrays.asList(substringPattern.split("-"));
                if (NumberUtils.isParsable(stringIndices.get(0)) &&
                        NumberUtils.isParsable(stringIndices.get(1))) {
                    return fullValue.substring(Integer.parseInt(stringIndices.get(0)),
                            Integer.parseInt(stringIndices.get(1)));
                } else {
                    log.warn("Not a valid pattern to substring. Returning blank value");
                    return "";
                }

        }
    }

    protected String getXMLAttributeVal(String response, String attributeName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(response)));
        return document.getElementsByTagName(attributeName).item(0).getTextContent();
    }

    protected String setTrustStoreBasedOnEnv() {
        String envPropsFile = ENVIRONMENTPATH + Property.getVariable(ENV_PROP) + PROPERTIES_EXT;
        String certFile = Property.getProperty(envPropsFile, "certFileForEnv");

        if (certFile != null) {
            String pathToCertFile = Paths.get(ENVIRONMENTPATH + certFile).toAbsolutePath().toString();
            String currentCertFile = Property.getVariable(JAVAX_NET_SSL_TRUST_STORE);
            log.debug(currentCertFile);
            setTrustStore(pathToCertFile);
            return currentCertFile;
        } else {
            log.warn("Path to cert file not defined in current env.properties file: {}", envPropsFile);
            return "";
        }
    }

    protected void setTrustStore(String pathToCertFile) {
        if (pathToCertFile != null) {
            System.setProperty(JAVAX_NET_SSL_TRUST_STORE, pathToCertFile);
            log.debug("Switching {} to {}", JAVAX_NET_SSL_TRUST_STORE, pathToCertFile);
        }
    }

    public String replaceParameterVals(String stringToReplace) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(REGEX_FULLKEY);
        Matcher m = p.matcher(stringToReplace);

        while (m.find()) {
            String fullKey = m.group(1);
            m.appendReplacement(sb, getDictionaryValOrRealVal(fullKey));
        }

        m.appendTail(sb);
        return sb.toString();
    }

    public String getValidationId(String stepText, boolean validationStep) {
        if (validationStep) {
            Pattern p = Pattern.compile(REGEX_VALIDATION);
            Matcher m = p.matcher(stepText);
            List<String> matches = new ArrayList<>();
            while (m.find()) {
                matches.add(m.group(1));
            }
            return matches.get(matches.size() - 2);
        }
        return null;
    }

    public int generateDynamicNumber() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(10000);
    }

    public long generateRandomANyNumber(String num) {
        int numValue= Integer.parseInt(num);
        long theRandomNum = (long) (Math.random()*Math.pow(numValue,numValue));
        return theRandomNum;
    }

    public long generateRandomAnyNum() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public int generateDynamicNumberBasedOnDigit(String digitValue) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(Integer.parseInt(digitValue));
    }

    public String generateRandomAlphabeticString() {
        return RandomStringUtils.randomAlphabetic(3);
    }

    public static String getPastDate(String numberOfDays) {
        String requiredDateWithTime =
                ZonedDateTime.now(ZoneOffset.UTC).minusDays(Long.parseLong(numberOfDays)).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return requiredDateWithTime;
    }
    public static String getFutureDate(String numberOfDays) {
        String requiredDateWithTime =
                ZonedDateTime.now(ZoneOffset.UTC).plusDays(Long.parseLong(numberOfDays)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return requiredDateWithTime;
    }

}
