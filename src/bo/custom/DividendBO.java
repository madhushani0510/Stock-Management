package bo.custom;

import bo.SuperBO;
import dto.DividendDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DividendBO extends SuperBO {
    boolean addDividend(DividendDTO dividend) throws ClassNotFoundException, SQLException;

    ObservableList<DividendDTO> getAllDividends() throws ClassNotFoundException, SQLException;

    boolean deleteDividend(String dividend) throws ClassNotFoundException, SQLException;

    boolean updateDividend(DividendDTO dividend) throws ClassNotFoundException, SQLException;

    DividendDTO searchDividend(String dividend)throws ClassNotFoundException, SQLException;

    String getLastID()throws ClassNotFoundException, SQLException;
}
