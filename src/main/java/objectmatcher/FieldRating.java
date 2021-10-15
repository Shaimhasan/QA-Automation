package objectmatcher;

public class FieldRating {
    private String fieldName;
    private Double similarityScore;
    private String searchResultMsg;
    private String fieldList;

    public FieldRating(String fieldName, Double similarityScore) {
        this.fieldName = fieldName;
        this.similarityScore = similarityScore;
        this.searchResultMsg = "";
        this.fieldList = null;
    }

    public FieldRating(String fieldName, Double similarityScore, String searchResultMsg, String fieldList) {
        this.fieldName = fieldName;
        this.similarityScore = similarityScore;
        this.searchResultMsg = searchResultMsg;
        this.fieldList = fieldList;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public String getSearchResultMsg() {
        return searchResultMsg;
    }

    public void setSearchResultMsg(String searchResultMsg) {
        this.searchResultMsg = searchResultMsg;
    }

    public void setFieldList(String fieldList) {
        this.fieldList = fieldList;
    }

    public String getFieldList() {
        return fieldList;
    }
}