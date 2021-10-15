package core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SFDCResponse {
    @SerializedName("totalSize")
    @Expose
    private Integer totalSize;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("records")
    @Expose
    private List<Map<String, Object>> records = null;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }
}
