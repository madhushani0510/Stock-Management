package entity;

public class Watchlist {
    private String watchlistId;
    private String comIndex;
    private String watchlistDate;
    private String DefaultBoardDate;

    public Watchlist() {
    }

    public Watchlist(String watchlistId, String cIndex, String watchlistDate, String defaultBoardDate) {
        this.watchlistId = watchlistId;
        this.comIndex = cIndex;
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
