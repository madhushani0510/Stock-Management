package bo.custom;

import bo.SuperBO;
import dto.RightIssuesDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface RightissuesBO extends SuperBO {

    String getLastID() throws SQLException, ClassNotFoundException;

    boolean addRightIssues(RightIssuesDTO rightIssuesDTO) throws ClassNotFoundException, SQLException;

    ObservableList<RightIssuesDTO> getAllRightIssues() throws ClassNotFoundException, SQLException;

    boolean deleteRightIssues(String rightIssueID) throws ClassNotFoundException, SQLException;

    boolean updateRightIssues(RightIssuesDTO rightIssuesDTO) throws ClassNotFoundException, SQLException;

    boolean searchRightIssues(String rightIssueID )throws ClassNotFoundException, SQLException;
}

