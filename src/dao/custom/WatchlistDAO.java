package dao.custom;

import dao.CrudDAO;
import entity.Watchlist;

import java.sql.SQLException;

public interface WatchlistDAO extends CrudDAO<Watchlist,String> {
    String getLastID() throws SQLException, ClassNotFoundException;
}
