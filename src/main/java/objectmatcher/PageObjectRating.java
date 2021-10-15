package objectmatcher;

public class PageObjectRating {
    private String pageObjectName;
    private Double similarityScore;
    private Class<?> pageObjectClazz;
    private String searchResultMsg;
    private String pageObjList;

    public PageObjectRating(String pageObjectName, Double similarityScore, Class<?> pageObjectClazz) {
        this.pageObjectName = pageObjectName;
        this.similarityScore = similarityScore;
        this.pageObjectClazz = pageObjectClazz;
        this.pageObjList = null;
    }

    public PageObjectRating(String pageObjectName, Double similarityScore, Class<?> pageObjectClazz, String searchResultMsg, String pageObjList) {
        this.pageObjectName = pageObjectName;
        this.similarityScore = similarityScore;
        this.pageObjectClazz = pageObjectClazz;
        this.searchResultMsg = searchResultMsg;
        this.pageObjList = pageObjList;
    }

    public String getPageObjectName() {
        return pageObjectName;
    }

    public Double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public Class<?> getPageObjectClazz() {
        return pageObjectClazz;
    }

    public String getSearchResultMsg() {
        return searchResultMsg;
    }

    public void setSearchResultMsg(String searchResultMsg) {
        this.searchResultMsg = searchResultMsg;
    }

    public void setPageObjList(String pageObjList) {
        this.pageObjList = pageObjList;
    }

    public String getPageObjList() {
        return pageObjList;
    }
}