package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.DividendDAO;
import entity.Dividend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DividendDAOImpl implements DividendDAO {
    @Override
    public boolean add(Dividend dividend) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO dividend VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,dividend.getDividendID(),dividend.getdCompanyIndex(),dividend.getStockType(),dividend.getDividendType(),dividend.getApprovalDate(),dividend.getXdDate(),dividend.getPaymentDate());

    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM dividend WHERE DividendID=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Dividend dividend) throws ClassNotFoundException, SQLException {
        String sql="UPDATE dividend SET CIndex=?,StockType=?,DividendType=?,ApprovalDate=?,ExDividendDate=?,PaymentDate=? where DividendID=?";
        return CrudUtil.executeUpdate(sql,dividend.getdCompanyIndex(),dividend.getStockType(),dividend.getDividendType(),dividend.getApprovalDate(),dividend.getXdDate(),dividend.getPaymentDate(),dividend.getDividendID());

    }

    @Override
    public Dividend search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Dividend> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM dividend";
        ResultSet rst=CrudUtil.executeQuery(SQL);
        ObservableList<Dividend>all=FXCollections.observableArrayList();
        ArrayList<Dividend> objects = new ArrayList<>();

        while (rst.next()){
//            System.out.println(rst.getString(1));
//            System.out.println(rst.getString(2));
//            System.out.println(rst.getString(3));
//            System.out.println(rst.getString(4));
//            System.out.println(rst.getString(5));
//            System.out.println(rst.getString(6));
//            System.out.println(rst.getString(7));

            all.add(new Dividend(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7)));

        }
        return all;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        String sql="SELECT DividendID FROM dividend ORDER BY DividendID DESC LIMIT 1 ";
        ResultSet rst= CrudUtil.executeQuery(sql);
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
