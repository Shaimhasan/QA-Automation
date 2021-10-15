package core;

import common.TestContext;

import java.util.Map;
import java.util.stream.Collectors;

public class DataTableFormatter {

    private DataTableFormatter() {
        //Utility Class
    }

    private static final String DATA_TABLE_LOG_FORMAT = "%-1s %-40s %-1s %-40s %-1s";
    private static final String DATA_TABLE_LOG_DIVIDER = "----------------------------------------";
    private static final String DATA_TABLE_LOG_COLUMN_SEPARATOR = "|";
    private static final String DATA_TABLE_LOG_COLUMN_SEPARATOR_BEGIN = "\n" + DATA_TABLE_LOG_COLUMN_SEPARATOR;

    public static String getDataDictionaryAsFormattedTable() {
        StringBuilder dataTable = new StringBuilder();

        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));
        dataTable.append(getDataTableRow("Data Dictionary Key", "Data Dictionary Value"));
        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));

        getFilteredDataTableAsMap().entrySet()
                                   .stream()
                                   .sorted(Map.Entry.comparingByKey())
                                   .forEach(entry -> dataTable.append(getDataTableRow(entry.getKey(), entry.getValue().toString())));

        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));
        return dataTable.toString();
    }

    public static Map<String, Object> getFilteredDataTableAsMap() {
        return TestContext.getInstance().testdata().entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() != null)
                          .filter(entry -> !entry.getKey().contains("fw."))
                          .filter(entry -> !entry.getKey().matches(".*(?i)(password|pwd|username|userid)(?i).*"))
                          .filter(entry -> !entry.getKey().contains("VALIDATION."))
                          .filter(entry -> !entry.getKey().contains("priorData"))
                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> getFilteredPriorDataTableAsMap() {
        return TestContext.getInstance().testdata().entrySet()
                          .stream()
                          .filter(entry -> entry.getKey().startsWith("priorData"))
                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> getValidationTableAsMap() {
        return TestContext.getInstance().testdata().entrySet()
                          .stream()
                          .filter(entry -> entry.getKey().contains("VALIDATION."))
                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static String getDataTableRow(String key, String value) {
        return String.format(DATA_TABLE_LOG_FORMAT,
                             DATA_TABLE_LOG_COLUMN_SEPARATOR_BEGIN,
                             key,
                             DATA_TABLE_LOG_COLUMN_SEPARATOR,
                             value,
                             DATA_TABLE_LOG_COLUMN_SEPARATOR);
    }

    public static String getMapAsFormattedDataTable(Map<String, Object> mapToLog) {
        StringBuilder dataTable = new StringBuilder();

        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));
        dataTable.append(getDataTableRow("Key", "Value"));
        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));

        mapToLog.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> dataTable.append(getDataTableRow(entry.getKey(), entry.getValue().toString())));

        dataTable.append(getDataTableRow(DATA_TABLE_LOG_DIVIDER, DATA_TABLE_LOG_DIVIDER));
        return dataTable.toString();
    }
}
