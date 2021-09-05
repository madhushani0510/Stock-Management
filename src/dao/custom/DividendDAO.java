package dao.custom;

import dao.CrudDAO;
import entity.Dividend;

import java.sql.SQLException;

public interface DividendDAO extends CrudDAO<Dividend,String> {
    String getLastID() throws SQLException, ClassNotFoundException;
}
