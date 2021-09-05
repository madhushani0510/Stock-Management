package bo.custom;

import bo.SuperBO;

import dto.WatchlistDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface WatchlistBO extends SuperBO {

    String getLastID() throws SQLException, ClassNotFoundException;

    boolean addToWatchlist(WatchlistDTO watchlist) throws ClassNotFoundException, SQLException;

    ObservableList<WatchlistDTO> getAllWatchlist() throws ClassNotFoundException, SQLException;

    boolean deleteFromWatchlist(String bought) throws ClassNotFoundException, SQLException;


}
