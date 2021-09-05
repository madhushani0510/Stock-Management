package bo.custom.impl;

import bo.custom.DividendBO;
import dao.DAOFactory;
import dao.custom.DividendDAO;
import dto.DividendDTO;
import entity.Dividend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class DividendBOImpl implements DividendBO {

    DividendDAO dividendDAO = (DividendDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.DIVIDEND);

    @Override
    public boolean addDividend(DividendDTO dividend) throws ClassNotFoundException, SQLException {
        return dividendDAO.add(new Dividend(dividend.getDividendID(),dividend.getCompanyIndex(),dividend.getStockType(),dividend.getDividendType(),dividend.getApprovalDate(),dividend.getXdDate(),dividend.getPaymentDate()));

    }

    @Override
    public ObservableList<DividendDTO> getAllDividends() throws ClassNotFoundException, SQLException {
        ObservableList<Dividend> all=dividendDAO.getAll();
        ObservableList<DividendDTO> objects= FXCollections.observableArrayList();


        for (Dividend d:all ){
           // System.out.println("DividendBO After For");
            System.out.println(d.getDividendID()+" "+d.getdCompanyIndex()+" "+d.getStockType()+" "+d.getDividendType()+" "+d.getApprovalDate()+" "+d.getXdDate()+" "+d.getPaymentDate());

            objects.add(new DividendDTO(d.getDividendID(),d.getdCompanyIndex(),d.getStockType(),d.getDividendType(),d.getApprovalDate(),d.getXdDate(),d.getPaymentDate()));
        }
        return objects;
    }

    @Override
    public boolean deleteDividend(String dividend) throws ClassNotFoundException, SQLException {
        return dividendDAO.delete(dividend);
    }

    @Override
    public boolean updateDividend(DividendDTO dividend) throws ClassNotFoundException, SQLException {
        return dividendDAO.update(new Dividend(dividend.getDividendID(),dividend.getCompanyIndex(),dividend.getStockType(),dividend.getDividendType(),dividend.getApprovalDate(),dividend.getXdDate(),dividend.getPaymentDate()));

    }

    @Override
    public DividendDTO searchDividend(String dividend) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dividendDAO.getLastID();
    }


}
