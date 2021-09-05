package dao.custom;

import dao.CrudDAO;
import entity.Bought;

import java.sql.SQLException;

public interface BoughtDAO extends CrudDAO<Bought,String> {


    String getLastID() throws SQLException, ClassNotFoundException;
}


