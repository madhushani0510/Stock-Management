package bo.custom.impl;

import bo.custom.CompanyBO;
import dao.DAOFactory;
import dao.custom.CompanyDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import dto.CompanyDTO;
import entity.Company;
import java.sql.SQLException;
import java.util.ArrayList;



public class CompanyBOImpl implements CompanyBO {
    CompanyDAO companyDAO= (CompanyDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.COMPANY);


    @Override
    public boolean addCompany(CompanyDTO dto) throws ClassNotFoundException, SQLException {
        return companyDAO.add(new Company(dto.getComnyIndex(),dto.getComnyName(),dto.getIndustryGroup(),dto.getFinancialMonth()));
    }

    @Override
    public ObservableList<CompanyDTO> getAllCompanies() throws ClassNotFoundException, SQLException {

        ObservableList<Company> all = companyDAO.getAll();

        ObservableList<CompanyDTO> objects = FXCollections.observableArrayList();

        for (Company c:all
             ) {
            objects.add(new CompanyDTO(c.getcIndex(),c.getCompanyName(),c.getIndustryGroup(),c.getFinancialMonth()));
        }
        return objects;
    }




    @Override
    public boolean deleteCompany(String s) throws ClassNotFoundException, SQLException {
       return companyDAO.delete(s);

    }

    @Override
    public boolean updateCompany(CompanyDTO c) throws SQLException, ClassNotFoundException {
        return companyDAO.update(new Company(c.getComnyIndex(),c.getComnyName(),c.getIndustryGroup(),c.getFinancialMonth()));
    }

    @Override
    public CompanyDTO searchCompany(String company) throws ClassNotFoundException, SQLException {
         Company search=companyDAO.search(company);
         return new CompanyDTO(search.getcIndex(),search.getCompanyName(),search.getIndustryGroup(),search.getFinancialMonth());
    }

}
