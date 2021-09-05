package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RightIssueDAO;
import entity.Rightissues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RightIssueDAOImpl implements RightIssueDAO {
    @Override
    public boolean add(Rightissues rightissues) throws ClassNotFoundException, SQLException {
        String sql="INSERT INTO rightissues VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,rightissues.getRightIssueId(),rightissues.getcIndex(),rightissues.getProportion(),rightissues.getDate());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM rightissues WHERE RightIssueID=?";
        return CrudUtil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Rightissues rightissues) throws ClassNotFoundException, SQLException {
        String sql="UPDATE rightissues SET CIndex=?,Proportion=?,ExRIDate=? WHERE RightIssueID=?";
        return CrudUtil.executeUpdate(sql,rightissues.getcIndex(),rightissues.getProportion(),rightissues.getDate(),rightissues.getRightIssueId());
    }

    @Override
    public Rightissues search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Rightissues> getAll() throws ClassNotFoundException, SQLException {
        String SQL="SELECT * FROM rightissues";
        ResultSet rst=CrudUtil.executeQuery(SQL);
        ObservableList<Rightissues>all= FXCollections.observableArrayList();
        ArrayList<Rightissues> objects=new ArrayList<>();
        while (rst.next()){
            all.add(new Rightissues(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }

        return all;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        String sql="SELECT  RightIssueID FROM rightissues ORDER BY RightIssueID DESC LIMIT 1 ";
        ResultSet rst=CrudUtil.executeQuery(sql);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
