package bo.custom.impl;

import bo.custom.RightissuesBO;
import dao.DAOFactory;
import dao.custom.RightIssueDAO;
import dto.RightIssuesDTO;
import entity.Rightissues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class RightissuesBOImpl implements RightissuesBO {

    RightIssueDAO rightIssueDAO= (RightIssueDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.RIGHTISSUES);


    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return rightIssueDAO.getLastID();
    }

    @Override
    public boolean addRightIssues(RightIssuesDTO rightIssuesDTO) throws ClassNotFoundException, SQLException {
        return rightIssueDAO.add(new Rightissues(rightIssuesDTO.getRightIssueId(),rightIssuesDTO.getComRIndex(),rightIssuesDTO.getProportion(),rightIssuesDTO.getDate()));

    }

    @Override
    public ObservableList<RightIssuesDTO> getAllRightIssues() throws ClassNotFoundException, SQLException {
        ObservableList<Rightissues> all=rightIssueDAO.getAll();
        ObservableList<RightIssuesDTO> objects= FXCollections.observableArrayList();
        for (Rightissues rd:all){
            objects.add(new RightIssuesDTO(rd.getRightIssueId(),rd.getcIndex(),rd.getProportion(),rd.getDate()));
        }

        return objects;
    }

    @Override
    public boolean deleteRightIssues(String rightIssueID) throws ClassNotFoundException, SQLException {
        return rightIssueDAO.delete(rightIssueID);
    }

    @Override
    public boolean updateRightIssues(RightIssuesDTO rightIssues) throws ClassNotFoundException, SQLException {
        return rightIssueDAO.update(new Rightissues(rightIssues.getRightIssueId(),rightIssues.getComRIndex(),rightIssues.getProportion(),rightIssues.getDate()));
    }

    @Override
    public boolean searchRightIssues(String rightIssueID) throws ClassNotFoundException, SQLException {
        return false;
    }
}
