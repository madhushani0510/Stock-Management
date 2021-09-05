package dto;

public class RightIssuesDTO {
    private  String rightIssueId;
    private String comRIndex;
    private String proportion;
    private String date;

    public RightIssuesDTO() {
    }

    public RightIssuesDTO(String rightIssueId, String comRIndex, String proportion, String date) {
        this.rightIssueId = rightIssueId;
        this.comRIndex = comRIndex;
        this.proportion = proportion;
        this.date = date;
    }

    public String getRightIssueId() {
        return rightIssueId;
    }

    public void setRightIssueId(String rightIssueId) {
        this.rightIssueId = rightIssueId;
    }

    public String getComRIndex() {
        return comRIndex;
    }

    public void setComRIndex(String comRIndex) {
        this.comRIndex = comRIndex;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
