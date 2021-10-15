package utility;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FileDownloadCookieJar implements CookieJar {

    private final Set<Cookie> cookieStore = new HashSet<>();

    public FileDownloadCookieJar() {
        //default
    }

    public FileDownloadCookieJar(Set<org.openqa.selenium.Cookie> seleniumCookieSet) {
        for (org.openqa.selenium.Cookie seleniumCookie : seleniumCookieSet) {
            cookieStore.add(new Cookie.Builder().name(seleniumCookie.getName())
                                                .value(seleniumCookie.getValue())
                                                .domain(removeLeadingDot(seleniumCookie.getDomain()))
                                                .path(seleniumCookie.getPath())
                                                .build());

        }
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        //Save cookies to the store
        cookieStore.addAll(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> validCookies = new ArrayList<>();
        for (Cookie cookie : cookieStore) {
            if (cookie.expiresAt() < System.currentTimeMillis()) {
                // invalid cookies
            } else {
                validCookies.add(cookie);
            }
        }
        return validCookies;
    }

    private String removeLeadingDot(String domain) {
        if(domain.startsWith(".")) {
            return domain.substring(1);
        } else
            return domain;
    }
}
