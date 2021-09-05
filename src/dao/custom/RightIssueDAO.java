package dao.custom;

import dao.CrudDAO;
import entity.Rightissues;

import java.sql.SQLException;

public interface RightIssueDAO extends CrudDAO<Rightissues,String> {

    String getLastID()throws ClassNotFoundException, SQLException;
}
