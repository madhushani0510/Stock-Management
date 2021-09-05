package entity;

public class Rightissues {

    private String rightIssueId;
    private String comRIndex;
    private String proportion;
    private String date;

    public Rightissues() {
    }

    public Rightissues(String rightIssueId, String cIndex, String proportion, String date) {
        this.rightIssueId = rightIssueId;
        this.comRIndex = cIndex;
        this.proportion = proportion;
        this.date = date;
    }

    public String getRightIssueId() {
        return rightIssueId;
    }

    public void setRightIssueId(String rightIssueId) {
        this.rightIssueId = rightIssueId;
    }

    public String getcIndex() {
        return comRIndex;
    }

    public void setcIndex(String cIndex) {
        this.comRIndex = cIndex;
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
