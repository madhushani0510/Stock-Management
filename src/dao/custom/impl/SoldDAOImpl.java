package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SoldDAO;
import entity.Bought;
import entity.Sold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldDAOImpl implements SoldDAO {
    @Override
    public boolean add(Sold sold) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO Sold VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,sold.getSoldId(),sold.getSoldCIndex(),sold.getSoldQty(),sold.getSoldPrice(),sold.getActualProfitMargin(),sold.getDate(),sold.getSoldTax());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM sold WHERE SoldID=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Sold company) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Sold search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Sold> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM sold";
        ResultSet rst=CrudUtil.executeQuery(SQL);
        ObservableList<Sold>all= FXCollections.observableArrayList();
        ArrayList<Sold> objects =new ArrayList<>();
        while (rst.next()){
            // System.out.println("Bought");
            all.add(new Sold(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getDouble(5),rst.getString(6),rst.getDouble(7)));
        }
        return all;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        String sql="SELECT SoldID FROM sold ORDER BY SoldID DESC LIMIT 1";
        ResultSet rst= CrudUtil.executeQuery(sql);
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}

