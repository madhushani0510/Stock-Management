package bo.custom.impl;

import bo.custom.WatchlistBO;
import dao.DAOFactory;
import dao.custom.WatchlistDAO;
import dto.DividendDTO;
import dto.WatchlistDTO;
import entity.Dividend;
import entity.Watchlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class WatchlistBOImpl implements WatchlistBO {

    WatchlistDAO watchlistDAO= (WatchlistDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.WATCHLIST);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return watchlistDAO.getLastID();
    }

    @Override
    public boolean addToWatchlist(WatchlistDTO watchlist) throws ClassNotFoundException, SQLException {
        return watchlistDAO.add(new Watchlist(watchlist.getWatchlistId(),watchlist.getComIndex(),watchlist.getWatchlistDate(),watchlist.getDefaultBoardDate()));
    }

    @Override
    public ObservableList<WatchlistDTO> getAllWatchlist() throws ClassNotFoundException, SQLException {
        ObservableList<Watchlist> all=watchlistDAO.getAll();
        ObservableList<WatchlistDTO> objects= FXCollections.observableArrayList();

        for (Watchlist w:all){
            objects.add(new WatchlistDTO(w.getWatchlistId(),w.getComIndex(),w.getWatchlistDate(),w.getDefaultBoardDate()));
        }
        return objects;
    }

    @Override
    public boolean deleteFromWatchlist(String watchlist) throws ClassNotFoundException, SQLException {
        return watchlistDAO.delete(watchlist);
    }
}
