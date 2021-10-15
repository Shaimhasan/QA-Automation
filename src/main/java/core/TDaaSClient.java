package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import common.Property;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static common.Constants.RUNTIMEPATH;
import static core.DataTableFormatter.getMapAsFormattedDataTable;

public class TDaaSClient {
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String ERROR_RESPONSE = "-1";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String UNEXEPECTED_CODE = "Unexepected code ";
    private String baseTDaaSURL;
    private String baseAPICallURL;
    private Integer connectionTimeout;
    private String lob;

    private static final Logger LOG = LogManager.getLogger(TDaaSClient.class);

    public TDaaSClient(Map<String, String> urlDetails) {
        PropertiesConfiguration props = Property.getProperties(RUNTIMEPATH);
        this.baseTDaaSURL = props.getString("TDaaSURL");
        this.connectionTimeout = props.getString("TDaaSConnectionWait") == null ? 60 : props.getInt("TDaaSConnectionWait");

        this.lob = isLobProvided(urlDetails) ? "NOT PROVIDED" : urlDetails.get("lob");
        this.baseAPICallURL = getBaseURL(urlDetails);
    }

    private String getBaseURL(Map<String, String> urlDetails) {
        if(urlAttributesAvail(urlDetails) && new UrlValidator().isValid(baseTDaaSURL)) {
            return updateURLForLOB(String.join("/", baseTDaaSURL, urlDetails.get("region"), urlDetails.get("lob"), getEnvironment(urlDetails.get("environment"))),
                                   urlDetails.get("country"));
        }

        return ERROR_RESPONSE;
    }

    private boolean isLobProvided(Map<String, String> urlDetails) {
        return urlDetails.get("lob") == null || urlDetails.get("lob").isEmpty();
    }

    private String updateURLForLOB(String url, String country) {
        switch (lob.toLowerCase()) {
            case "retail":
            case "cards":
                return String.join("/", url, country);
            default:
                return url;
        }
    }

    private String getEnvironment(String environment) {
        String curTestEnv = Property.getVariable("cukes.env");

        if(environment.equalsIgnoreCase("All") || environment.equalsIgnoreCase(curTestEnv)) {
            LOG.info("Using same environment as the current automation test {}",curTestEnv);
            return curTestEnv;
        } else {
            LOG.warn("Using environment from the JSON, which is different than the currently running test. JSON Env: {}, Test Env: {}", environment, curTestEnv);
            return environment;
        }
    }

    private boolean urlAttributesAvail(Map<String, String> urlDetails) {
        final Map<String, Object> emptyParams = urlDetails.entrySet()
                                                          .stream()
                                                          .filter(entry -> entry.getKey().matches("region|lob|environment"))
                                                          .filter(entry -> entry.getValue().isEmpty())
                                                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (!emptyParams.isEmpty()) {
            emptyParams.forEach((key, value) -> LOG.error("No value provided for {}. Unable to build TDaaS query URL", key));
            return false;
        }

        return true;
    }

    public List<Map<String, Object>> runTDaaSQuery(String queryName, Map<String, Object> paramList) {
        if(!baseAPICallURL.equalsIgnoreCase(ERROR_RESPONSE) && queryName != null) {
            final OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
                    .readTimeout(connectionTimeout, TimeUnit.SECONDS)
                    .build();

            HttpUrl url = getQueryURL(queryName, paramList);

            Request request = new Request.Builder()
                    .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                    .header(ACCEPT, JSON_CONTENT_TYPE)
                    .url(url.toString())
                    .build();

            LOG.debug("Running TDaaS query: {}", url);

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException(UNEXEPECTED_CODE + response);

                LOG.debug("TDaaS Query returned successfully. {}. Parsing response...", response.code());
                List<Map<String, Object>> queryResult = parseJSONResponse(response.body().charStream());
                if(!queryResult.isEmpty()) {
                    LOG.debug(getMapAsFormattedDataTable(queryResult.get(0)));
                    return queryResult;
                }
            } catch (IOException e) {
                LOG.error("{} TDaaS query unsuccessful. {} ", queryName, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    private HttpUrl getQueryURL(String queryName, Map<String, Object> paramList) {
        HttpUrl.Builder url = HttpUrl.parse(String.join("/", baseAPICallURL, queryName)).newBuilder();
        paramList.forEach((key, val) -> url.addQueryParameter(getParameter(key), val.toString()));

        return url.build();
    }

    private List<Map<String, Object>> removeNullEntries(List<Map<String, Object>> resultSet) {
        List<Map<String, Object>> resultSetWithoutNull = new ArrayList<>();

        resultSet.forEach(dataRow -> resultSetWithoutNull.add(dataRow.entrySet()
                                                                     .stream()
                                                                     .filter(entry -> entry.getValue() != null)
                                                                     .collect(Collectors.toMap(Map.Entry::getKey,
                                                                                               Map.Entry::getValue))));

        return resultSetWithoutNull;
    }

    private List<Map<String, Object>> parseJSONResponse(Reader apiResponse) {
        Gson gson = new GsonBuilder().create();
        List<Map<String, Object>> response = new ArrayList<>();

        try {
            switch (lob.toLowerCase()) {
                case "retail":
                case "cards":
                    response = gson.fromJson(apiResponse, List.class);
                    break;
                case "salesforce":
                    SFDCResponse sfdcResponse = gson.fromJson(apiResponse, SFDCResponse.class);
                    response = sfdcResponse.getRecords();
                    break;
                default:
                    LOG.info("API response parsing for region '{}' not supported.", lob);
            }

            return removeNullEntries(response);
        }
        catch (NullPointerException | JsonSyntaxException e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private String getParameter(String queryParam) {
        Pattern regEx = Pattern.compile(".*(?i)(P\\d+$)");
        Matcher paramMatcher = regEx.matcher(queryParam);

        if(paramMatcher.find()) {
            return paramMatcher.group(1).toLowerCase();
        } else {
            LOG.warn("Parameter not found in key: {}", queryParam);
            return ERROR_RESPONSE;
        }
    }

}