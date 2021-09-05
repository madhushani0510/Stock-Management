package bo.custom.impl;

import bo.custom.BoughtBO;
import dao.DAOFactory;
import dao.custom.BoughtDAO;
import dao.custom.CompanyDAO;
import dao.custom.impl.BoughtDAOImpl;
import dto.BoughtDTO;
import dto.CompanyDTO;
import entity.Bought;
import entity.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BoughtBOImpl implements BoughtBO {
    BoughtDAO boughtDAO= (BoughtDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.BOUGHT);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return boughtDAO.getLastID();
    }

    @Override
    public boolean addStocks(BoughtDTO bought) throws ClassNotFoundException, SQLException {
        return boughtDAO.add(new Bought(bought.getBoughtID(),bought.getComIndex(),bought.getBoughtQty(),bought.getBoughtPrice(),bought.getDate(),bought.getExSellingPrice(),bought.getBoughtTax()));
    }


    @Override
    public ObservableList<BoughtDTO> getAllStocks() throws ClassNotFoundException, SQLException {
        ObservableList<Bought> all=boughtDAO.getAll();
        ObservableList<BoughtDTO> objects= FXCollections.observableArrayList();
        for (Bought bo:all ){
            objects.add(new BoughtDTO(bo.getBoughtID(),bo.getComIndex(),bo.getBoughtQty(),bo.getBoughtPrice(),bo.getDate(),bo.getExSellingPrice(),bo.getBoughtTax()));
        }
        return objects;
    }

    @Override
    public boolean deleteStocks(String bought) throws ClassNotFoundException, SQLException {
        return boughtDAO.delete(bought);
    }

    @Override
    public boolean updateStocks(BoughtDTO bought) throws ClassNotFoundException, SQLException {
        return boughtDAO.update(new Bought(bought.getBoughtID(),bought.getComIndex(),bought.getBoughtQty(),bought.getBoughtPrice(),bought.getDate(),bought.getExSellingPrice(),bought.getBoughtTax()));
    }

    @Override
    public boolean searchStocks(String bought) throws ClassNotFoundException, SQLException {
        return false;
    }


}
