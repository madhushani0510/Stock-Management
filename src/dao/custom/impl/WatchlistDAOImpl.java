package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.WatchlistDAO;
import entity.Watchlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchlistDAOImpl implements WatchlistDAO {
    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        String sql="SELECT WatchListID FROM watchlist ORDER BY WatchListID DESC LIMIT 1 ";
        ResultSet rst=CrudUtil.executeQuery(sql);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(Watchlist watchlist) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO watchlist VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,watchlist.getWatchlistId(),watchlist.getComIndex(),watchlist.getWatchlistDate(),watchlist.getDefaultBoardDate());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM watchlist WHERE WatchListID=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Watchlist company) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Watchlist search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Watchlist> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM watchlist";
        ResultSet rst=CrudUtil.executeQuery(SQL);
        ObservableList<Watchlist>all= FXCollections.observableArrayList();
        ArrayList<Watchlist> objects = new ArrayList<>();

        while (rst.next()){
            all.add(new Watchlist(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return all;
    }


}
