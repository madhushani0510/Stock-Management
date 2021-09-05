package controller;

import bo.BOFactory;
import bo.custom.BoughtBO;
import bo.custom.CompanyBO;
import bo.custom.SoldBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.BoughtDTO;
import dto.CompanyDTO;
import dto.SoldDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SoldFormController implements Initializable {
    public JFXTextField txtSoldTax;
    public TableView tblSold;
    public DatePicker txtSoldDate;
    public JFXTextField txtSoldCIndex;
    public JFXTextField txtSoldPrice;
    public JFXTextField txtSoldProfitMargin;
    public JFXTextField txtSoldQty;
    public JFXTextField txtSoldCost;
    public JFXTextField txtSoldProfit;
    public JFXTextField txtSoldComName;
    public JFXButton btnSoldSave;

    public TableColumn colBID;
    public TableColumn colBCIndex;
    public TableColumn colBQty;
    public TableColumn colBPrice;
    public TableColumn colBDate;
    public TableColumn colBSPrice;
    public TableColumn colBoughtTax;
    public TableColumn coldBCost;
    public JFXButton btnSReport;
    public JFXButton btnSoldCost;
    public JFXButton btnSoldUpdate;
    public JFXButton btnProfit;
    public JFXButton btnSoldSearch;
    public JFXButton btnSoldClear;
    public JFXButton SoldView;

    SoldBO soldBO=(SoldBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SOLD);
    CompanyBO companyBO= (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);
    BoughtBO boughtbo = (BoughtBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOUGHT);

    public Label lblSoldID;
    private double boughtPrice = 0.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllBoughtStocks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadAllSoldStocks();

        setSoldID();

        colBID.setCellValueFactory(new PropertyValueFactory<>("boughtID"));
        colBCIndex.setCellValueFactory(new PropertyValueFactory<>("comIndex"));
        colBQty.setCellValueFactory(new PropertyValueFactory<>("boughtQty"));
        colBPrice.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
        colBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBSPrice.setCellValueFactory(new PropertyValueFactory<>("exSellingPrice"));
        colBoughtTax.setCellValueFactory(new PropertyValueFactory<>("boughtTax"));
        coldBCost.setCellValueFactory(new PropertyValueFactory<>("BoughtCost"));

    }



    private void loadAllSoldStocks() {
       // ObservableList<SoldDTO> allSoldStocks=soldBO.getAllSoldStocks();

    }

    public void loadAllBoughtStocks() throws SQLException, ClassNotFoundException {
        ObservableList<BoughtDTO> allStocks=boughtbo.getAllStocks();
        tblSold.setItems(allStocks);

    }
    private void setSoldID(){
        try{
            String sID =soldBO.getLastID();
            if (sID != null){
                sID=sID.split("[A-Z]")[1];
                sID = "S" + (Integer.parseInt(sID) + 1);
                lblSoldID.setText(sID);
            }else {
                lblSoldID.setText("S1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void soldSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String soldID=lblSoldID.getText();
        String soldCIndex=txtSoldCIndex.getText();
        int soldQty=Integer.parseInt(txtSoldQty.getText());
        double soldPrice=Double.parseDouble(txtSoldPrice.getText());
        double soldProfitMargin=Double.parseDouble(txtSoldProfit.getText());
        String soldDate=txtSoldDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        double soldTax=Double.parseDouble(txtSoldTax.getText());

        SoldDTO soldDTO=new SoldDTO(soldID,soldCIndex,soldQty,soldPrice,soldProfitMargin,soldDate,soldTax);

        try{
            boolean isAdded=soldBO.addSoldStocks(soldDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"OK", ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK).show();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    public void soldDeleteOnAction(ActionEvent actionEvent) {
        BoughtDTO selectedItem = (BoughtDTO) tblSold.getSelectionModel().getSelectedItem();
        String id = selectedItem.getBoughtID();

        try {
            boolean isDeleted=boughtbo.deleteStocks(id);
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
            loadAllBoughtStocks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void soldUpdateOnAction(ActionEvent actionEvent) {
    }

    public void soldProfitOnAction(ActionEvent actionEvent) {
        double sellingPrice = Double.parseDouble(txtSoldPrice.getText());
        int soldQty = Integer.parseInt(txtSoldQty.getText());
        double tax = Double.parseDouble(txtSoldTax.getText());

        double totalBoughtCost = (boughtPrice*soldQty) + (boughtPrice*soldQty*tax/100);
        double SellingPrice = (sellingPrice*soldQty)-(sellingPrice*soldQty*tax/100);//Since its a selling, tax is reduced from the selling price

        double actualProfitMargin = (SellingPrice-totalBoughtCost)*100/totalBoughtCost;

        double actualProfit = (SellingPrice-totalBoughtCost);


        txtSoldProfit.setText(String.valueOf(actualProfit));
        txtSoldProfitMargin.setText(String.valueOf(actualProfitMargin));
    }

    public void soldSearchOnAction(ActionEvent actionEvent) {
        String id = txtSoldCIndex.getText();
        try {
            CompanyDTO companyDTO = companyBO.searchCompany(id);
            if(companyDTO!=null){
                txtSoldComName.setText(companyDTO.getComnyName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void soldCostOnAction(ActionEvent actionEvent) {
        int soldQty = Integer.parseInt(txtSoldQty.getText());
        double soldPrice = Double.parseDouble(txtSoldPrice.getText());
        double soldTax = Double.parseDouble(txtSoldTax.getText());

        double soldIncome = (soldQty*soldPrice) + ((soldQty*soldPrice)*soldTax/100);
        txtSoldCost.setText(String.valueOf(soldIncome));



    }


    public void rawSOnClicked(MouseEvent mouseEvent) {
        BoughtDTO selectedItem= (BoughtDTO) tblSold.getSelectionModel().getSelectedItem();


        txtSoldCIndex.setText(selectedItem.getComIndex());
        txtSoldQty.setText(String.valueOf(selectedItem.getBoughtQty()));
        txtSoldPrice.setText(String.valueOf(selectedItem.getExSellingPrice()));



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d"); //convert String to LocalDate
        LocalDate soldDate = LocalDate.parse(selectedItem.getDate(), formatter);
        txtSoldDate.setValue(soldDate);

        txtSoldTax.setText(String.valueOf(selectedItem.getBoughtTax()));
        boughtPrice = selectedItem.getBoughtPrice();
    }

    public void soldReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Sold.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint js = JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(js);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public void soldClearOnAction(ActionEvent actionEvent) {
        lblSoldID.setText("");
        txtSoldCIndex.setText("");
        txtSoldQty.setText("");
        txtSoldPrice.setText("");
        txtSoldProfitMargin.setText("");
        txtSoldDate.setValue(null);
        txtSoldTax.setText("");
        setSoldID();
    }

    public void soldViewOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/SoldViewForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
