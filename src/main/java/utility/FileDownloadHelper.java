package utility;

/*
 * Derived from the example at https://github.com/Ardesco/Powder-Monkey/blob/master/src/main/java/com/lazerycode/selenium/filedownloader/FileDownloader.java
 * Using as a base, ported to OKHttp and this framework
 */

import common.Property;
import core.Constants;
import core.Element;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import javax.net.ssl.HostnameVerifier;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;

public class FileDownloadHelper {

    private static final Logger LOG = LogManager.getLogger(FileDownloadHelper.class);
    private WebDriver driver;
    private boolean followRedirects = true;
    private boolean mimicWebDriverCookieState = true;
    private URI fileURI;

    public FileDownloadHelper(WebDriver driverObject) {
        this.driver = driverObject;
    }

    /**
     * Specify if the FileDownloader class should follow redirects when trying to download a file
     * Default: true
     *
     * @param followRedirects boolean
     */
    public void followRedirectsWhenDownloading(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    /**
     * Mimic the cookie state of WebDriver (Defaults to true)
     * This will enable you to access files that are only available when logged in.
     * If set to false the connection will be made as an anonymous user
     *
     * @param mimicWebDriverCookies boolean
     */
    public void mimicWebDriverCookieState(boolean mimicWebDriverCookies) {
        mimicWebDriverCookieState = mimicWebDriverCookies;
    }

    /**
     * Specify a URL that you want to perform an HTTP Status Check upon/Download a file from
     *
     * @param linkToFile String
     * @throws URISyntaxException
     */
    public void setURI(String linkToFile) throws URISyntaxException {
        fileURI = new URI(linkToFile);
    }

    /**
     * Specify a URL that you want to perform an HTTP Status Check upon/Download a file from
     *
     * @param linkToFile URI
     */
    public void setURI(URI linkToFile) {
        fileURI = linkToFile;
    }

    /**
     * Specify a URL that you want to perform an HTTP Status Check upon/Download a file from
     *
     * @param linkToFile URL
     */
    public void setURI(URL linkToFile) throws URISyntaxException {
        fileURI = linkToFile.toURI();
    }

    /**
     * Perform an HTTP Status Check upon/Download the file specified in the href attribute of a WebElement
     *
     * @param anchorElement Selenium WebElement
     * @throws URISyntaxException
     */
    public void setURISpecifiedInAnchorElement(Element anchorElement) throws URISyntaxException {
        if (anchorElement.element().getTagName().equals("a")) {
            fileURI = new URI(anchorElement.getAttribute("href"));
        } else {
            throw new IllegalArgumentException("You have not specified an <a> element!");
        }
    }

    /**
     * Perform an HTTP Status Check upon/Download the image specified in the src attribute of a WebElement
     *
     * @param imageElement Selenium WebElement
     * @throws URISyntaxException
     */
    public void setURISpecifiedInImageElement(Element imageElement) throws URISyntaxException {
        if (imageElement.element().getTagName().equals("img")) {
            fileURI = new URI(imageElement.getAttribute("src"));
        } else {
            throw new IllegalArgumentException("You have not specified an <img> element!");
        }
    }

    /**
     * Perform an HTTP Status Check upon/Download the image specified in the src attribute of a WebElement
     *
     * @param elementWithLink Selenium WebElement
     * @throws URISyntaxException
     */
    public void setURISpecifiedInOtherElement(Element elementWithLink, String attributeWithLink) throws URISyntaxException {
        if (attributeWithLink != null) {
            fileURI = new URI(elementWithLink.getAttribute(attributeWithLink));
        } else {
            throw new IllegalArgumentException("You have not specified a valid attribute");
        }

    }

    /**
     * Sets URI to current browser URL
     *
     *  @throws URISyntaxException
     */
    public void setURIToCurrentBrowserURL() throws URISyntaxException {
        fileURI = new URI(driver.getCurrentUrl());

        if(FilenameUtils.getExtension(fileURI.toString()).isEmpty()) {
            LOG.warn("Did not find a file extension at the current browser URL.");
        }
    }

    /**
     * Download a file from the specified URI
     *
     * @return File
     */
    public File downloadFile(String extension) {
        if (fileURI == null) throw new IllegalArgumentException("No file URI specified");
        File downloadedFile = new File(getFileName(extension));

        OkHttpClient client = getOkHttpClient();
        Request request = getRequest();

        try (Response response = client.newCall(request).execute()) {
            FileUtils.copyInputStreamToFile(response.body().byteStream(), downloadedFile);
            return downloadedFile;
        } catch (IOException | NullPointerException e) {
            LOG.error("Unable to download file. {}", e.getMessage());
            return null;
        }

    }

    private String getFileName(String extension) {
        String fileName;

        if(FilenameUtils.getExtension(fileURI.toString()).isEmpty()) {
            if(FilenameUtils.indexOfExtension(extension) == -1) {
                extension = "." + extension;
            }
            fileName = String.format("%s%s", UUID.randomUUID(), extension);
        } else {
            fileName = FilenameUtils.getName(fileURI.toString());
        }

        return Paths.get(Constants.DOWNLOADPATH + fileName).toAbsolutePath().toString();
    }

    /**
     * Prepare the OKHttp request.
     * Converts the provided URI to a URL, issues warning if unable
     *
     * @return OKHttp Request object
     */
    private Request getRequest() {
        String urlToUse = null;

        try {
            urlToUse = fileURI.toURL().toString();
        } catch (MalformedURLException e) {
            LOG.warn("Not a valid URL. Trying a string of the URI provided. {}", e.getMessage());
            urlToUse = fileURI.toString();
        }

        return new Request.Builder()
                .url(urlToUse)
                .build();
    }

    /**
     * Set up the OKHttp client.
     * Creates the OKHttp client based on flags set, including setting cookies
     *
     * @return OKHttp client
     */
    private OkHttpClient getOkHttpClient() {
        CookieJar cookieJar = null;

        if(mimicWebDriverCookieState) {
            cookieJar = new FileDownloadCookieJar(driver.manage().getCookies());
        } else {
            cookieJar = new FileDownloadCookieJar();
        }

        return new OkHttpClient
                .Builder()
                .cookieJar(cookieJar)
                .hostnameVerifier(fileDownloadVerifier())
                .followRedirects(followRedirects)
                .build();
    }


    private HostnameVerifier fileDownloadVerifier() {
        return (hostname, session) -> {
            if(hostname.equalsIgnoreCase(session.getPeerHost())) {
                return true;
            } else {
                if(Boolean.parseBoolean(Property.getVariable("fw.ignoreHostNameMismatch"))) {
                    return false;
                } else {
                    LOG.warn("Server host name: {} did not match expected: {}. Proceeding based on ignore flag in runtime.properties", hostname, session.getPeerHost());
                    return true;
                }
            }
        };
    }
}