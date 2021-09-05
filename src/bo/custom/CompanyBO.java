package bo.custom;

import bo.SuperBO;
import dto.CompanyDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompanyBO extends SuperBO {

    boolean addCompany(CompanyDTO company) throws ClassNotFoundException, SQLException;

    ObservableList<CompanyDTO> getAllCompanies() throws ClassNotFoundException, SQLException;

   boolean deleteCompany(String company) throws ClassNotFoundException, SQLException;

   boolean updateCompany(CompanyDTO company) throws ClassNotFoundException, SQLException;

   CompanyDTO searchCompany(String company)throws ClassNotFoundException, SQLException;

}
