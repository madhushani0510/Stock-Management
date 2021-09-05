package bo.custom;

import bo.SuperBO;
import dao.CrudDAO;
import dto.BoughtDTO;
import dto.CompanyDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface BoughtBO extends SuperBO {

    String getLastID() throws SQLException, ClassNotFoundException;

    boolean addStocks(BoughtDTO bought) throws ClassNotFoundException, SQLException;

    ObservableList<BoughtDTO> getAllStocks() throws ClassNotFoundException, SQLException;

    boolean deleteStocks(String bought) throws ClassNotFoundException, SQLException;

    boolean updateStocks(BoughtDTO bought) throws ClassNotFoundException, SQLException;

    boolean searchStocks(String bought )throws ClassNotFoundException, SQLException;
}
