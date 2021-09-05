package controller;

import bo.BOFactory;
import bo.custom.RightissuesBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RightIssuesDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class RightissuesFormController implements Initializable {


    public JFXTextField txtRProportion;
    public JFXTextField txtRComIndex;
    public Label lblRID;
    public DatePicker txtRDate;
    public JFXButton btnRClearOnAction;
    public JFXButton btnRSave;
    public JFXButton btnRUpdate;
    public JFXButton btnRDelete;
    public TableView tblRightissues;
    public TableColumn colRComIndex;
    public TableColumn colRID;
    public TableColumn colExRDate;
    public TableColumn colRProportion;

    RightissuesBO rightissuesBO= (RightissuesBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RIGHTISSUES);

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            loadAllRightIssues();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setRightIssuesID();

        colRID.setCellValueFactory(new PropertyValueFactory<>("RightIssueId"));
        colRComIndex.setCellValueFactory(new PropertyValueFactory<>("ComRIndex"));
        colRProportion.setCellValueFactory(new PropertyValueFactory<>("Proportion"));
        colExRDate.setCellValueFactory(new PropertyValueFactory<>("Date"));


    }

    private void loadAllRightIssues() throws SQLException, ClassNotFoundException {
        ObservableList<RightIssuesDTO>allRightIssues=rightissuesBO.getAllRightIssues();
        tblRightissues.setItems(allRightIssues);
    }



    private void setRightIssuesID() {
        try {
            String rid = rightissuesBO.getLastID();
            if (rid != null) {
                rid = rid.split("[A-Z]")[1];
                rid = "D" + (Integer.parseInt(rid) + 1);
                lblRID.setText(rid);

            } else {
                lblRID.setText("R1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveROnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String rid=lblRID.getText();
        String comIndex=txtRComIndex.getText();
        String proportion=txtRProportion.getText();
        String exDate=txtRDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);

        RightIssuesDTO rightIssuesDTO=new RightIssuesDTO(rid,comIndex,proportion,exDate);

        try { boolean isAdded= rightissuesBO.addRightIssues(rightIssuesDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Added",ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Not Added",ButtonType.OK).show();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllRightIssues();


    }

    public void updateROnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String rID=lblRID.getText();
        String rComIndex=txtRComIndex.getText();
        String proportion=txtRProportion.getText();
        String rDate=txtRDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);

        RightIssuesDTO rightIssuesDTO=new RightIssuesDTO(rID,rComIndex,proportion,rDate);

        try {
            boolean isUpdate = rightissuesBO.updateRightIssues(rightIssuesDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Not Updated",ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadAllRightIssues();
    }

    public void deleteROnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RightIssuesDTO selectedItem = (RightIssuesDTO) tblRightissues.getSelectionModel().getSelectedItem();
        String id = selectedItem.getRightIssueId();

            try {
                boolean isDeleted=rightissuesBO.deleteRightIssues(id);
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Not Deleted",ButtonType.OK).show();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
     loadAllRightIssues();
    }

    public void clearROnAction(ActionEvent actionEvent) {
        lblRID.setText("");
        txtRComIndex.setText("");
        txtRDate.setValue(null);
        txtRProportion.setText("");

        setRightIssuesID();
    }


    public void rowROnClick(MouseEvent mouseEvent) {

        RightIssuesDTO selectedItem = (RightIssuesDTO) tblRightissues.getSelectionModel().getSelectedItem();
        lblRID.setText(selectedItem.getRightIssueId());
        txtRComIndex.setText(selectedItem.getComRIndex());
        txtRProportion.setText(selectedItem.getProportion());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        LocalDate rDate = LocalDate.parse(selectedItem.getDate());
        txtRDate.setValue(rDate);
    }
}
