package dto;

public class WatchlistDTO {
    private String watchlistId;
    private String comIndex;
    private String watchlistDate;
    private String DefaultBoardDate;

    public WatchlistDTO() {
    }

    public WatchlistDTO(String watchlistId, String comIndex, String watchlistDate, String defaultBoardDate) {
        this.watchlistId = watchlistId;
        this.comIndex = comIndex;
        this.watchlistDate = watchlistDate;
        DefaultBoardDate = defaultBoardDate;
    }

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getComIndex() {
        return comIndex;
    }

    public void setComIndex(String comIndex) {
        this.comIndex = comIndex;
    }

    public String getWatchlistDate() {
        return watchlistDate;
    }

    public void setWatchlistDate(String watchlistDate) {
        this.watchlistDate = watchlistDate;
    }

    public String getDefaultBoardDate() {
        return DefaultBoardDate;
    }

    public void setDefaultBoardDate(String defaultBoardDate) {
        DefaultBoardDate = defaultBoardDate;
    }
}
