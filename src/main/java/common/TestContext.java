package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.io.File;
import java.util.*;

/**
 * Used to hold the execution context including scenario, properties and platform/browser combo
 * for each execution thread
 */
public class TestContext {

    private static List<TestContext> threads = new ArrayList<>();
    private SoftAssertions sa = null;
    private Map<String, Object> testdata = null;
    private long threadToEnvID;
    private Set<Class<?>> setOfPO = null;
    private Set<File> setOfFeatureFiles = null;
    private Deque<String> windowHandles;
    private String activeWindowType;
    private Set<String> setOfAPI = null;
    private Set<String> setOfDB = null;

    protected Logger log = LogManager.getLogger(this.getClass().getName());

    private TestContext() {
    }

    private TestContext(long threadID) {
        this.threadToEnvID = threadID;
    }

    public static synchronized TestContext getInstance() {
        long currentRunningThreadID = Thread.currentThread().getId();
        for (TestContext thread : threads) {
            if (thread.threadToEnvID == currentRunningThreadID) {
                return thread;
            }
        }
        TestContext temp = new TestContext(currentRunningThreadID);
        threads.add(temp);
        return temp;
    }

    public SoftAssertions sa() {
        if (sa == null)
            sa = new SoftAssertions();
        return sa;
    }

    public void resetSoftAssert() {
        sa = new SoftAssertions();
    }

    public Map<String, Object> testdata() {
        if (testdata == null)
            testdata = new HashMap<>();
        return testdata;
    }

    public Object testdataGet(String key) {
        if (testdata.get(key) != null) {
            return testdata.get(key);
        } else if (testdata.get(key.toLowerCase()) != null) {
            log.warn("Non-exact match found for key '{}'. Check key name and capitalization.", key);
            return testdata.get(key.toLowerCase());
        }

        return testdata.get(key);
    }

    public <T> T testdataToClass(String key, Class<T> type) {
        return type.cast(testdata.get(key));
    }

    public void testdataPut(String key, Object data) {
        testdata().put(key, data);
    }

    public Set<Class<?>> setOfPO() {
        if (setOfPO == null)
            setOfPO = new HashSet<>();
        return setOfPO;
    }

    public Set<File> setOfFeatureFiles() {
        if (setOfFeatureFiles == null)
            setOfFeatureFiles = new HashSet<>();
        return setOfFeatureFiles;
    }

    private Deque<String> windowHandles() {
        if (windowHandles == null)
            windowHandles = new ArrayDeque<>();
        return windowHandles;
    }

    public void pushWindowHandle(String windowHandleName) {
        windowHandles().addLast(windowHandleName);
    }

    public String popWindowHandle() {
        return windowHandles().removeLast();
    }

    public Integer getWindowHandlesCount() {
        return windowHandles().size();
    }

    public Boolean windowHandleExists(String windowHandleName) {
        return windowHandles().contains(windowHandleName);
    }

    public String peekLastWindowHandle() {
        return windowHandles().peekLast();
    }

    public void setActiveWindowType(String activeWindowType) {
        this.activeWindowType = activeWindowType;
    }

    public String getActiveWindowType() {
        return activeWindowType;
    }

    public Set<String> setOfAPI() {
        if (setOfAPI == null)
            setOfAPI = new HashSet<>();
        return setOfAPI;
    }

    public Set<String> setOfDB() {
        if (setOfDB == null)
            setOfDB = new HashSet<>();
        return setOfDB;
    }
}