package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CompanyDAO;
import entity.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public boolean add(Company c) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO Company VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql, c.getcIndex(),c.getCompanyName(),c.getIndustryGroup(),c.getFinancialMonth());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM company WHERE cIndex=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Company c) throws ClassNotFoundException, SQLException {
        String sql="update Company set CompanyName=?,IndustryGroup=?,FinancialMonth=? where CIndex =?";
        return CrudUtil.executeUpdate(sql,c.getCompanyName(),c.getIndustryGroup(),c.getFinancialMonth(),c.getcIndex());
    }



    @Override
    public Company search(String s) throws ClassNotFoundException, SQLException {
        String sql = "select * from Company where CIndex=?";
        ResultSet rst = CrudUtil.executeQuery(sql,s);
        if (rst.next()) {
            return new Company(rst.getString(1), rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public ObservableList<Company> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM Company";
        ResultSet rst=CrudUtil.executeQuery(SQL);
       ObservableList<Company>all= FXCollections.observableArrayList();
        ArrayList<Company> objects = new ArrayList<>();
        while (rst.next()){
            all.add(new Company(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }

        return all;
    }
}
