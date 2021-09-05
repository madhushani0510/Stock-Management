package controller;

import bo.BOFactory;
import bo.custom.CompanyBO;
import bo.custom.DividendBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.BoughtDTO;
import dto.CompanyDTO;
import dto.DividendDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DividendFormController implements Initializable {
    public JFXTextField txtDividendCIndex;
    public ComboBox cmbStockType;
    public ComboBox cmbDividendType;
    public DatePicker txtDApprovalDate;
    public DatePicker txtDXDDate;
    public DatePicker txtDPaymentDate;
    public JFXButton btnDSave;
    public JFXButton btnDUpdate;
    public JFXButton btnDDelete;
    public JFXButton btnDClear;
    public Label lblDividendID;

    public TableView tblDividend;
    public TableColumn colDID;
    public TableColumn colDIndex;
    public TableColumn colDStockType;
    public TableColumn colDType;
    public TableColumn colADate;
    public TableColumn colXDDate;
    public TableColumn colDPaymentDate;

    DividendBO dividendBO = (DividendBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DIVIDEND);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllDividends();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setDividendID();

        colDID.setCellValueFactory(new PropertyValueFactory<>("dividendID"));
        colDIndex.setCellValueFactory(new PropertyValueFactory<>("CompanyIndex"));
        colDStockType.setCellValueFactory(new PropertyValueFactory<>("stockType"));
        colDType.setCellValueFactory(new PropertyValueFactory<>("dividendType"));
        colADate.setCellValueFactory(new PropertyValueFactory<>("approvalDate"));
        colXDDate.setCellValueFactory(new PropertyValueFactory<>("xdDate"));
        colDPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));



        loadValuesToStockTypeCMB();
        loadValuesToDividendTypeCMB();

    }


    public void loadValuesToStockTypeCMB() {
        cmbStockType.getItems().add("Voting");
        cmbStockType.getItems().add("Non Voting");

    }

    public void loadValuesToDividendTypeCMB() {
        cmbDividendType.getItems().add("Final");
        cmbDividendType.getItems().add("Interim");

    }
    public void loadAllDividends() throws SQLException, ClassNotFoundException {
        ObservableList<DividendDTO>allDividends=dividendBO.getAllDividends();
        tblDividend.setItems(allDividends);

    }

    public void saveDOnAction(ActionEvent actionEvent) {
        String did = lblDividendID.getText();
        String cIndex = txtDividendCIndex.getText();
        String stockType = String.valueOf(cmbStockType.getValue());
        String dividendType = String.valueOf(cmbDividendType.getValue());
        String aDate = txtDApprovalDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        String exDate = txtDXDDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        String payDate = txtDPaymentDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);

        DividendDTO dividendDTO = new DividendDTO(did,cIndex,stockType,dividendType,aDate,exDate,payDate);
        try {
            boolean isAdded = dividendBO.addDividend(dividendDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Added",ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR," Failed",ButtonType.OK).show();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            loadAllDividends();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateDOnAction(ActionEvent actionEvent) {
        String did = lblDividendID.getText();
        String cIndex = txtDividendCIndex.getText();
        String stockType = String.valueOf(cmbStockType.getValue());
        String dividendType = String.valueOf(cmbDividendType.getValue());
        String aDate = txtDApprovalDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        String exDate = txtDXDDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        String payDate = txtDPaymentDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);

        DividendDTO dividendDTO = new DividendDTO(did,cIndex,stockType,dividendType,aDate,exDate,payDate);

        try {
            boolean isUpdate = dividendBO.updateDividend(dividendDTO);

            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Try Again",ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            loadAllDividends();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteDOnAction(ActionEvent actionEvent) {
        DividendDTO selectedItem = (DividendDTO) tblDividend.getSelectionModel().getSelectedItem();
        String id = selectedItem.getDividendID();

        try {
            boolean isDeleted=dividendBO.deleteDividend(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again",ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            loadAllDividends();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void clearDOnAction(ActionEvent actionEvent) {
        txtDividendCIndex.setText("");
        lblDividendID.setText("");
        cmbStockType.setValue(null);
        cmbDividendType.setValue(null);
        txtDApprovalDate.setValue(null);
        txtDPaymentDate.setValue(null);
        txtDXDDate.setValue(null);

        setDividendID();


    }

    private void setDividendID() {

        try {
            String did = dividendBO.getLastID();
            if (did != null) {
                did = did.split("[A-Z]")[1];
                did = "D" + (Integer.parseInt(did) + 1);
                lblDividendID.setText(did);

            } else {
                lblDividendID.setText("D1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void rawDOnClick(MouseEvent mouseEvent) {
        DividendDTO selectedItem= (DividendDTO) tblDividend.getSelectionModel().getSelectedItem();
        lblDividendID.setText(selectedItem.getDividendID());
        txtDividendCIndex.setText(selectedItem.getCompanyIndex());
        cmbStockType.setValue(selectedItem.getStockType());
        cmbDividendType.setValue(selectedItem.getDividendType());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        //convert String to LocalDate
        LocalDate approvalDate = LocalDate.parse(selectedItem.getApprovalDate(), formatter);
        LocalDate edDate = LocalDate.parse(selectedItem.getXdDate(),formatter);
        LocalDate paymentDate = LocalDate.parse(selectedItem.getPaymentDate(),formatter);

        txtDApprovalDate.setValue(approvalDate);
        txtDXDDate.setValue(edDate);
        txtDPaymentDate.setValue(paymentDate);


    }
}