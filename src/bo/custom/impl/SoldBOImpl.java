package bo.custom.impl;

import bo.custom.SoldBO;
import dao.DAOFactory;
import dao.custom.BoughtDAO;
import dao.custom.SoldDAO;
import dto.BoughtDTO;
import dto.SoldDTO;
import entity.Bought;
import entity.Sold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SoldBOImpl implements SoldBO {
    SoldDAO soldDAO = (SoldDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.SOLD);
    BoughtDAO boughtDAO= (BoughtDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.BOUGHT);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return soldDAO.getLastID();
    }

    @Override
    public boolean addSoldStocks(SoldDTO sold) throws ClassNotFoundException, SQLException {
        return soldDAO.add(new Sold(sold.getSoldId(),sold.getSoldCIndex(),sold.getSoldQty(),sold.getSoldPrice(),sold.getActualProfitMargin(),sold.getDate(),sold.getSoldTax()));
    }


    @Override
    public ObservableList<BoughtDTO> getAllSoldStocks() throws ClassNotFoundException, SQLException {
        ObservableList<Bought> all=boughtDAO.getAll();
        ObservableList<BoughtDTO> objects = FXCollections.observableArrayList();
        for (Bought bo:all ){
            objects.add(new BoughtDTO(bo.getBoughtID(),bo.getComIndex(),bo.getBoughtQty(),bo.getBoughtPrice(),bo.getDate(),bo.getExSellingPrice(),bo.getBoughtTax()));
        }
        return objects;
    }

    @Override
    public boolean deleteSoldStocks(String sold) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean updateSoldStocks(SoldDTO sold) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean searchSoldStocks(String sold) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ObservableList<SoldDTO> getAllSoldViewStocks() throws ClassNotFoundException, SQLException {

        ObservableList<Sold> all = soldDAO.getAll();
        ObservableList<SoldDTO> objects = FXCollections.observableArrayList();
        for (Sold so:all) {
            objects.add(new SoldDTO(so.getSoldId(), so.getSoldCIndex(), so.getSoldQty(), so.getSoldPrice(), so.getActualProfitMargin(), so.getDate(), so.getSoldTax()));
        }
        return objects;


    }
}
