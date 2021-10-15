package common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DateHelper {
    private DateHelper() {
        //utility class
    }

    public static LocalDateTime formatAsDate(Object dateToFormat) {
        DateTimeFormatter format;
        Map<String, String> dateFormat = getDateTimeFormat(dateToFormat);

        if(dateFormat.containsKey("DATE")) {
            format = DateTimeFormatter.ofPattern(dateFormat.get("DATE")).withLocale(Locale.ENGLISH);
            return LocalDate.parse(dateToFormat.toString(), format).atStartOfDay();
        } else {
            format = DateTimeFormatter.ofPattern(dateFormat.get("DATE-TIME")).withLocale(Locale.ENGLISH);
            return LocalDateTime.parse(dateToFormat.toString(), format);
        }
    }


    public static Map<String, String> getDateTimeFormat(Object dateToFormat) {
        Map<String, String> dateFormatMap = getDateFormatMap();
        Map<String, String> dateTimeFormatMap = getDateTimeFormatMap();

        List<String> matchingFormat = dateFormatMap.entrySet().stream()
                .filter(entry -> dateToFormat.toString().matches(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        if (!matchingFormat.isEmpty()) {
            return Collections.singletonMap("DATE", matchingFormat.get(0));
        } else {
            matchingFormat = dateTimeFormatMap.entrySet().stream()
                    .filter(entry -> dateToFormat.toString().matches(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            if(!matchingFormat.isEmpty()) {
                return Collections.singletonMap("DATE-TIME", matchingFormat.get(0));
            } else {
                throw new IllegalArgumentException(String.format("Unknown date format: '%s'", dateToFormat));
            }
        }
    }

    private static Map<String, String> getDateFormatMap() {
        Map<String, String> dateFormatMap = new HashMap<>();

        dateFormatMap.put("^\\d{2}([0][1-9]|[1][0-2])([0][1-9]|[1-2][0-9]|[3][0-1])$", "yyMMdd");
        dateFormatMap.put("^([0][1-9]|[1][0-2])([0][1-9]|[1-2][0-9]|[3][0-1])\\d{2}$", "MMddyy");
        dateFormatMap.put("^\\d{8}$", "MMddyyyy");
        dateFormatMap.put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "MM-dd-yyyy");
        dateFormatMap.put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "MM-dd-yy");
        dateFormatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        dateFormatMap.put("^\\d{1}\\/\\d{1}\\/\\d{4}$", "M/d/yyyy");
        dateFormatMap.put("^\\d{2}\\/\\d{2}\\/\\d{4}$", "MM/dd/yyyy");
        dateFormatMap.put("^\\d{1}\\/\\d{1}\\/\\d{2}$", "M/d/yy");
        dateFormatMap.put("^\\d{2}\\/\\d{2}\\/\\d{2}$", "MM/dd/yy");
        dateFormatMap.put("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$", "yyyy/MM/dd");
        dateFormatMap.put("^\\d{1,2}\\s[a-zA-Z]{3}\\s\\d{4}$", "dd MMM yyyy");
        dateFormatMap.put("^\\d{1,2}\\s[a-zA-Z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        dateFormatMap.put("^[a-zA-Z]{3}\\s\\d{1,2},{0,1}\\s\\d{4}$", "MMM dd yyyy");
        dateFormatMap.put("^[a-zA-Z]{4,}\\s\\d{1,2},{0,1}\\s\\d{4}$", "MMMM dd yyyy");

        return dateFormatMap;
    }

    private static Map<String, String> getDateTimeFormatMap() {
        Map<String, String> dateTimeFormatMap = new HashMap<>();

        dateTimeFormatMap.put("^\\d{12}$", "yyyyMMddHHmm");
        dateTimeFormatMap.put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        dateTimeFormatMap.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "MM-dd-yyyy HH:mm");
        dateTimeFormatMap.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}[a-zA-Z]{2}$", "MM-dd-yyyy [hh][h]:mma");
        dateTimeFormatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        dateTimeFormatMap.put("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        dateTimeFormatMap.put("^\\d{1,2}\\/\\d{1,2}\\/\\d{2}\\s\\d{1,2}:\\d{2}[a-zA-Z]{2}$", "MM/dd/yy [hh][h]:mma");
        dateTimeFormatMap.put("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s\\d{1,2}:\\d{2}[a-zA-Z]{2}$", "MM/dd/yyyy [hh][h]:mma");
        dateTimeFormatMap.put("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        dateTimeFormatMap.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        dateTimeFormatMap.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        dateTimeFormatMap.put("^\\s[a-z]{3}\\d{1,2}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "MMM dd yyyy HH:mm");
        dateTimeFormatMap.put("^\\s[a-z]{3}\\d{1,2}\\s\\d{4}\\s\\d{1,2}:\\d{2}[a-zA-Z]{2}$", "MMM dd yyyy [hh][h]:mma");
        dateTimeFormatMap.put("^\\s[a-z]{4,}\\d{1,2}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "MMMM dd yyyy HH:mm");
        dateTimeFormatMap.put("^\\s[a-z]{4,}\\d{1,2}\\s\\d{4}\\s\\d{1,2}:\\d{2}[a-zA-Z]{2}$", "MMMM dd yyyy [hh][h]:mma");
        dateTimeFormatMap.put("^\\d{14}$", "yyyyMMddHHmmss");
        dateTimeFormatMap.put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        dateTimeFormatMap.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM-dd-yyyy HH:mm:ss");
        dateTimeFormatMap.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        dateTimeFormatMap.put("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        dateTimeFormatMap.put("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}[a-zA-Z]{2}$", "MM/dd/yyyy hh:mm:ssa");
        dateTimeFormatMap.put("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        dateTimeFormatMap.put("^\\d{1,2}\\s[a-zA-Z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        dateTimeFormatMap.put("^\\d{1,2}\\s[a-zA-Z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        dateTimeFormatMap.put("^[a-zA-Z]{3}\\s\\d{1,2},{0,1}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MMM dd[,] yyyy HH:mm:ss");
        dateTimeFormatMap.put("^[a-zA-Z]{3}\\s\\d{1,2},{0,1}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}\\s{0,1}[a-zA-Z]{2}$", "MMM dd[,] yyyy [hh][h]:mm:ss[ ]a");
        dateTimeFormatMap.put("^[a-zA-Z]{4,}\\s\\d{1,2},{0,1}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MMMM dd[,] yyyy HH:mm:ss");
        dateTimeFormatMap.put("^[a-zA-Z]{4,}\\s\\d{1,2},{0,1}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}[a-zA-Z]\\s{0,1}{2}$", "MMMM[,] dd yyyy [hh][h]:mm:ss[ ]a");

        return dateTimeFormatMap;
    }
}
