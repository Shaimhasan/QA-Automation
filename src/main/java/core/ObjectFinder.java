package core;

import common.CommonPageObject;
import common.TestContext;
import info.debatty.java.stringsimilarity.JaroWinkler;
import objectmatcher.FieldRating;
import objectmatcher.PageObjectRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectFinder {
    private static final double ELEMENT_NOT_FOUND = -1.0;
    protected static final Logger LOG = LogManager.getLogger(ObjectFinder.class);
    private static final double EXACT_THRESHOLD = 1;
    private static final double ABOVE_THRESHOLD = 0.85;
    private static final double BELOW_THRESHOLD = 0.7;
    public static final String WARNING_TEMPLATE = "{} {}";

    private ObjectFinder() {
        //Utility class
    }

    /**
     * Generic method to find object
     *
     * @param objectName
     * @param pageName
     */
    public static Object getMatchingElement(String objectName, String pageName) {
        PageObjectRating pageObjMatch = null;
        FieldRating fieldMatch = null;
        CommonPageObject pageObj = null;
        Object ele = null;

        pageObjMatch = findMatchingPage(pageName);

        if (pageObjMatch.getSimilarityScore() != ELEMENT_NOT_FOUND) {
            fieldMatch = findMatchingElement(objectName, pageObjMatch.getPageObjectClazz());

            if (fieldMatch.getSimilarityScore() != ELEMENT_NOT_FOUND) {
                try {
                    if (pageObjMatch.getPageObjectClazz().newInstance() instanceof CommonPageObject)
                        pageObj = (CommonPageObject) pageObjMatch.getPageObjectClazz().newInstance();

                    ele = invokeMethod(pageObj, fieldMatch.getFieldName());
                } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        return ele;
    }


    /**
     * Generic method to find object
     *
     * @param pageName
     */
    public static PageObjectRating findMatchingPage(String pageName) {
        Set<Class<?>> pageObjects = TestContext.getInstance().setOfPO();
        List<PageObjectRating> listOfFilteredPOs;
        PageObjectRating topMatch = null;

        listOfFilteredPOs = pageObjects.stream()
                                       .map(clazz -> new PageObjectRating(getClassName(clazz),
                                                                          getSimilarityScore(getClassName(clazz), removeSpecialChars(pageName)),
                                                                          clazz))
                                       .filter(pObject -> pObject.getSimilarityScore() >= BELOW_THRESHOLD)
                                       .sorted(Comparator.comparing(PageObjectRating::getSimilarityScore).reversed())
                                       .collect(Collectors.toList());

        if (!listOfFilteredPOs.isEmpty()) {
            topMatch = listOfFilteredPOs.get(0);

            if (topMatch.getSimilarityScore() >= EXACT_THRESHOLD) {
                topMatch.setSearchResultMsg(String.format("Page Object: found exact match on the page: \"%s\"", topMatch.getPageObjectName()));
                LOG.debug(topMatch.getSearchResultMsg());
            } else if (topMatch.getSimilarityScore() >= ABOVE_THRESHOLD) {
                topMatch.setSearchResultMsg(String.format("Page Object: couldn't find exact page match for: \"%s\". Using a similar match: \"%s\"",
                                                          pageName, topMatch.getPageObjectName()));
                LOG.warn(topMatch.getSearchResultMsg());
            } else if (topMatch.getSimilarityScore() >= BELOW_THRESHOLD) {
                String searchResultMsg = String.format("Page Object: Couldn't find a similar page match for: \"%s\". Potential pages for a similar match are: ", pageName);
                StringBuilder pageObjList = new StringBuilder();

                listOfFilteredPOs.forEach(p -> {
                    pageObjList.append(p.getPageObjectName());
                    pageObjList.append(System.lineSeparator());
                });

                topMatch.setSimilarityScore(ELEMENT_NOT_FOUND);
                topMatch.setSearchResultMsg(searchResultMsg);
                topMatch.setPageObjList(pageObjList.toString());
                LOG.warn(WARNING_TEMPLATE, searchResultMsg, pageObjList);
            }
        } else {
            String searchResultMsg = "Page Object: Matching page name of \"" + pageName + "\" not found in available list of pages. Available page objects: " + System.lineSeparator();
            StringBuilder pageObjList = new StringBuilder();

            pageObjects.forEach(p -> {
                pageObjList.append(p.getName());
                pageObjList.append(System.lineSeparator());
            });

            topMatch = new PageObjectRating(null, ELEMENT_NOT_FOUND, null, searchResultMsg, pageObjList.toString());
            LOG.error(WARNING_TEMPLATE, searchResultMsg, pageObjList);
        }

        return topMatch;
    }

    public static FieldRating findMatchingElement(String objectName, Class<?> pageObjectClazz) {
        List<FieldRating> listOfFilteredFields;
        FieldRating topMatch = null;

        final Field[] declaredFields = pageObjectClazz.getDeclaredFields();

        listOfFilteredFields = Arrays.stream(declaredFields)
                                     .map(field -> new FieldRating(field.getName(),
                                                                   getSimilarityScore(field.getName(), removeSpecialChars(objectName))))
                                     .filter(field -> field.getSimilarityScore() >= BELOW_THRESHOLD)
                                     .sorted(Comparator.comparing(FieldRating::getSimilarityScore).reversed())
                                     .collect(Collectors.toList());

        if (!listOfFilteredFields.isEmpty()) {
            topMatch = listOfFilteredFields.get(0);

            if (topMatch.getSimilarityScore() >= EXACT_THRESHOLD) {
                topMatch.setSearchResultMsg("Element: found exact match for the element \"" + objectName + "\"");
                LOG.debug(topMatch.getSearchResultMsg());
            } else if (topMatch.getSimilarityScore() >= ABOVE_THRESHOLD) {
                topMatch.setSearchResultMsg("Element: couldn't find exact element match for: \"" + objectName + "\". " + "Using a similar match: \"" + topMatch.getFieldName() + "\"");
                LOG.warn(topMatch.getSearchResultMsg());
            } else if (topMatch.getSimilarityScore() >= BELOW_THRESHOLD) {
                String searchResultMsg = "Element: couldn't find a similar match for the \"" + objectName + "\" element in the provided page object class \""
                                         + getPageObjName(pageObjectClazz.toString()) + "\".   " + "Here are few elements similar to the provided element name: " + System.lineSeparator();


                String fieldList = getSortedFieldListAsString(listOfFilteredFields.stream()
                                                                                  .map(FieldRating::getFieldName)
                                                                                  .collect(Collectors.toList()));

                topMatch.setSimilarityScore(ELEMENT_NOT_FOUND);
                topMatch.setSearchResultMsg(searchResultMsg);
                topMatch.setFieldList(fieldList);
                LOG.warn(WARNING_TEMPLATE, searchResultMsg, fieldList);
            }
        } else {
            String searchResultMsg = System.lineSeparator() + "Element: The \"" + objectName + "\" element was not found in the \"" + getPageObjName(pageObjectClazz.toString()) + "\" page object. " +
                                     "Full list of elements in this page object are: " + System.lineSeparator();

            String fieldList = getSortedFieldListAsString(Arrays.stream(declaredFields)
                                                                .map(Field::getName)
                                                                .collect(Collectors.toList()));

            topMatch = new FieldRating(null, ELEMENT_NOT_FOUND, searchResultMsg, fieldList);
            LOG.error(WARNING_TEMPLATE, searchResultMsg, fieldList);
        }

        return topMatch;
    }



    private static String getSortedFieldListAsString(List<String> sortedFieldList) {
        StringBuilder fieldList = new StringBuilder();

        sortedFieldList.sort(String.CASE_INSENSITIVE_ORDER);
        sortedFieldList.forEach(f -> {
            fieldList.append(f);
            fieldList.append(System.lineSeparator());
        });

        return fieldList.toString();
    }

    private static String getPageObjName(String pageObjFullName) {
        List<String> name = Arrays.asList(pageObjFullName.split("\\."));
        if (!name.isEmpty()) {
            return name.get(name.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * @param pageObj
     * @param objectName
     * @return element invoked
     */

    private static Object invokeMethod(CommonPageObject pageObj, String objectName) {
        Object returnObj = null;
        try {
            Method method = pageObj.getClass().getMethod(objectName);
            returnObj = method.invoke(pageObj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOG.warn(e.getMessage(), e);
        }
        return returnObj;
    }

    /**
     * @param stringWithSpecialChars
     * @return String with spaces and special characters removed
     */
    public static String removeSpecialChars(String stringWithSpecialChars) {
        stringWithSpecialChars = stringWithSpecialChars.replaceAll("\\s+", "");
        return stringWithSpecialChars.replaceAll("[^a-zA-Z0-9_-]+", "");
    }

    /**
     * @param clazz
     * @return Class Name
     */
    private static String getClassName(Class<?> clazz) {
        return clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1);
    }

    /**
     * @param str1
     * @param str2
     * @return Similarity score
     */
    private static double getSimilarityScore(String str1, String str2) {
        JaroWinkler jw = new JaroWinkler();
        return jw.similarity(str1.toLowerCase(), str2.toLowerCase());
    }

    public static void invokePOMethod(String pageName, String methodName) throws IllegalAccessException,
                                                                                 InstantiationException {
        PageObjectRating matchingPage = ObjectFinder.findMatchingPage(pageName);
        CommonPageObject challengePageObj = null;
        if (matchingPage.getSimilarityScore() != ELEMENT_NOT_FOUND) {
            if (matchingPage.getPageObjectClazz().newInstance() instanceof CommonPageObject) {
                challengePageObj = (CommonPageObject) matchingPage.getPageObjectClazz().newInstance();
                invokeMethod(challengePageObj, methodName);
            }
        } else {
            LOG.error("Did not find method in page object");
        }
    }


}
