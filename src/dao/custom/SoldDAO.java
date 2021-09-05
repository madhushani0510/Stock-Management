package dao.custom;

import dao.CrudDAO;
import entity.Sold;

import java.sql.SQLException;

public interface SoldDAO extends CrudDAO<Sold,String> {
    String getLastID()throws SQLException, ClassNotFoundException;
}
