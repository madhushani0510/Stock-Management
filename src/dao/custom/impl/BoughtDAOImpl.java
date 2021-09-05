package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BoughtDAO;
import entity.Bought;
import entity.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoughtDAOImpl implements BoughtDAO {
    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        String sql="SELECT BoughtID FROM bought ORDER BY BoughtID DESC LIMIT 1 ";
        ResultSet rst= CrudUtil.executeQuery(sql);
        if(rst.next()){
           return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(Bought b) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO bought VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,b.getBoughtID(),b.getComIndex(),b.getBoughtQty(),b.getBoughtPrice(),b.getDate(),b.getExSellingPrice(),b.getBoughtTax());

    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM bought WHERE BoughtID=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Bought company) throws ClassNotFoundException, SQLException {
        String sql="UPDATE bought SET CIndex=?,BoughtQty=?,BoughtPrice=?,Date=?,ExSellingPrice=?,boughtTax=? where BoughtID =?";
        return CrudUtil.executeUpdate(sql,company.getComIndex(),company.getBoughtQty(),company.getBoughtPrice(),company.getDate(),company.getExSellingPrice(),company.getBoughtTax(),company.getBoughtID());
    }

    @Override
    public Bought search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }


    @Override
    public ObservableList<Bought> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM bought";
        ResultSet rst=CrudUtil.executeQuery(SQL);
        ObservableList<Bought>all= FXCollections.observableArrayList();
        ArrayList<Bought> objects =new ArrayList<>();
        while (rst.next()){
           // System.out.println("Bought");
            all.add(new Bought(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getString(5),rst.getDouble(6),rst.getDouble(7)));
        }
        return all;
    }
}
