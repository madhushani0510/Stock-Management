package bo.custom;

import bo.SuperBO;
import dto.BoughtDTO;
import dto.SoldDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SoldBO extends SuperBO {

    String getLastID() throws SQLException, ClassNotFoundException;

    boolean addSoldStocks(SoldDTO sold) throws ClassNotFoundException, SQLException;

    ObservableList<BoughtDTO> getAllSoldStocks() throws ClassNotFoundException, SQLException;

    boolean deleteSoldStocks(String sold) throws ClassNotFoundException, SQLException;

    boolean updateSoldStocks(SoldDTO sold) throws ClassNotFoundException, SQLException;

    boolean searchSoldStocks(String sold )throws ClassNotFoundException, SQLException;

    ObservableList<SoldDTO> getAllSoldViewStocks() throws ClassNotFoundException, SQLException;
}
