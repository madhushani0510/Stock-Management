package controller;

import bo.BOFactory;
import bo.custom.CompanyBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.panel.IProperty;
import db.DBConnection;
import dto.CompanyDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class CompanyFormController {
    public AnchorPane rootCompany;
    public JFXTextField txtIndex;
    public JFXTextField txtCompanyName;
    public ComboBox cmbIGroup;
    public ComboBox cmbFMonth;
    public TableView tblCompany;
    public TableColumn colIndex;
    public TableColumn colName;
    public TableColumn colIGroup;
    public TableColumn colFMonth;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public TableColumn columnCompanyIndex;
    public TableColumn colGroup;
    public TableColumn colMonth;


    CompanyBO companyBO= (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);

    public  void initialize() throws SQLException, ClassNotFoundException {
        loadAllCompanies();

        colIndex.setCellValueFactory(new PropertyValueFactory<>("comnyIndex"));
        colName.setCellValueFactory(new PropertyValueFactory<>("comnyName"));
        colGroup.setCellValueFactory(new PropertyValueFactory<>("IndustryGroup"));
       colMonth.setCellValueFactory(new PropertyValueFactory<>("FinancialMonth"));

        loadValuesToIGroupCMB();
        loadValuesToFMonthCMB();
    }

    public  void loadAllCompanies() throws SQLException, ClassNotFoundException {
        ObservableList<CompanyDTO>allCompanies=companyBO.getAllCompanies();
        tblCompany.setItems(allCompanies);
    }
    public void clearOnAction(ActionEvent actionEvent) {

        txtCompanyName.setText("");
        txtIndex.setText("");

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CompanyDTO selectedItem = (CompanyDTO)tblCompany.getSelectionModel().getSelectedItem();
        String id = selectedItem.getComnyIndex();

        try{
            boolean isDelete=companyBO.deleteCompany(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Try Again", ButtonType.OK).show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllCompanies();


    }
    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = txtIndex.getText();
        String name = txtCompanyName.getText();
        String fmoanth = cmbFMonth.getSelectionModel().getSelectedItem().toString();
        String igroup = cmbIGroup.getSelectionModel().getSelectedItem().toString();

        CompanyDTO companyDTO = new CompanyDTO(id, name, igroup, fmoanth);
        try {
            boolean isUpdate = companyBO.updateCompany(companyDTO);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again", ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllCompanies();
    }

    public void loadValuesToIGroupCMB(){
        cmbIGroup.getItems().add("Education");
        cmbIGroup.getItems().add("Electronic");
        cmbIGroup.getItems().add("Cleaning");
        cmbIGroup.getItems().add("Technology");
        cmbIGroup.getItems().add("ITService");
        cmbIGroup.getItems().add("Bank");

    }
    public void loadValuesToFMonthCMB() {
        cmbFMonth.getItems().add("January");
        cmbFMonth.getItems().add("February");
        cmbFMonth.getItems().add("March");
        cmbFMonth.getItems().add("April");
        cmbFMonth.getItems().add("May");

    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cIndex=txtIndex.getText();
        String companyName=txtCompanyName.getText();
        String iGroup=cmbIGroup.getSelectionModel().getSelectedItem().toString();
        String fMonth=cmbFMonth.getSelectionModel().getSelectedItem().toString();
        try {
            boolean isAdded=companyBO.addCompany(new CompanyDTO(cIndex,companyName,iGroup,fMonth));
            new Alert(Alert.AlertType.CONFIRMATION,"Yeeah", ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Nope", ButtonType.OK).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllCompanies();


    }

    public void rawOnClick(MouseEvent mouseEvent) {
        CompanyDTO selectedItem= (CompanyDTO) tblCompany.getSelectionModel().getSelectedItem();

        txtIndex.setText(selectedItem.getComnyIndex());
        txtCompanyName.setText(selectedItem.getComnyName());
        cmbIGroup.setValue(selectedItem.getIndustryGroup());
        cmbFMonth.setValue(selectedItem.getFinancialMonth());
    }
}
