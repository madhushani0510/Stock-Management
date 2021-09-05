package controller;

import bo.BOFactory;
import bo.custom.BoughtBO;
import bo.custom.CompanyBO;
import bo.custom.impl.BoughtBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.BoughtDTO;
import dto.CompanyDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BoughtFormController implements Initializable {


    public JFXTextField txtboughtQty;
    public Label txtboughtPrice;
    public JFXTextField txtexSellingPrice;
    public Label txtexProfitMargin;
    public JFXTextField txtboughtCost;
    public JFXTextField txtboughtExProfit;
    public JFXButton btnboughtClear;
    public JFXButton btnboughtDelete;
    public JFXButton btnboughtUpadate;
    public JFXButton btnboughtSave;
    public JFXButton btncostCaculate;
    public JFXButton btnexpProfirCalculate;
    public JFXTextField txtboughtcompanyName;
    public JFXTextField txtboughtCompanyIndex;
    public JFXTextField txtboughtcoName;
    public JFXTextField txtBoughtPrice;
    public DatePicker txtboughtDate;
    public TableView tblBought;
    public TableColumn colBID;
    public TableColumn colBCIndex;
    public TableColumn colBQty;
    public TableColumn colBPrice;
    public TableColumn colBDate;
    public TableColumn colBSPrice;
    public JFXTextField txtBoughtExProfit;
    public JFXTextField txtBoughtCost;
    public JFXTextField txtBoughtTax;
    public TableColumn colBoughtTax;
    public TableColumn coldBCost;
    public JFXTextField txtExProfitMargin;

    BoughtBO boughtBO= (BoughtBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOUGHT);
    CompanyBO companyBO= (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);


    public Label lblBoughtID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllStocks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setBoughtID();

        colBID.setCellValueFactory(new PropertyValueFactory<>("boughtID"));
        colBCIndex.setCellValueFactory(new PropertyValueFactory<>("comIndex"));
        colBQty.setCellValueFactory(new PropertyValueFactory<>("boughtQty"));
        colBPrice.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
        colBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBSPrice.setCellValueFactory(new PropertyValueFactory<>("exSellingPrice"));
        colBoughtTax.setCellValueFactory(new PropertyValueFactory<>("boughtTax"));
        coldBCost.setCellValueFactory(new PropertyValueFactory<>("BoughtCost"));

    }





    public void loadAllStocks() throws SQLException, ClassNotFoundException {
        ObservableList<BoughtDTO>allStocks=boughtBO.getAllStocks();
        tblBought.setItems(allStocks);
    }




    private void setBoughtID() {

        try {
            String bId = boughtBO.getLastID();
            if (bId != null) {
                bId = bId.split("[A-Z]")[1];
                bId = "B" + (Integer.parseInt(bId) + 1);
                lblBoughtID.setText(bId);

            } else {
                lblBoughtID.setText("B1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        BoughtDTO selectedItem = (BoughtDTO) tblBought.getSelectionModel().getSelectedItem();
        String id = selectedItem.getBoughtID();

        try {
            boolean isDeleted=boughtBO.deleteStocks(id);
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
        loadAllStocks();
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String bid = lblBoughtID.getText();
        String cid = txtboughtCompanyIndex.getText();
        int qty = Integer.parseInt(txtboughtQty.getText());
        double price = Double.parseDouble(txtBoughtPrice.getText());
        String bDate = txtboughtDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        double sellingPrice = Double.parseDouble(txtexSellingPrice.getText());
        double boughtTax = Double.parseDouble(txtBoughtTax.getText());

        BoughtDTO boughtDTO = new BoughtDTO(bid,cid,qty,price,bDate,sellingPrice,boughtTax);
        try {
            boolean isUpdate = boughtBO.updateStocks(boughtDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again",ButtonType.OK).show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllStocks();
    }



    public void saveOnAction(ActionEvent actionEvent) {
        String id= lblBoughtID.getText();
        String bid=txtboughtCompanyIndex.getText();
        int qty=Integer.parseInt(txtboughtQty.getText());
        double price=  Double.parseDouble(txtBoughtPrice.getText());
        String bdate=txtboughtDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        double sellingPrice=Double.parseDouble(txtexSellingPrice.getText());
        double boughtTax = Double.parseDouble(txtBoughtTax.getText());

        BoughtDTO boughtDTO=new BoughtDTO(id,bid,qty,price,bdate,sellingPrice,boughtTax);
        try {
            boolean isAdded=boughtBO.addStocks(boughtDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"OK",ButtonType.OK).show();

        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK).show();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            loadAllStocks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void SearchOnAction(ActionEvent actionEvent) {

        String id = txtboughtCompanyIndex.getText();
        try {
            CompanyDTO companyDTO = companyBO.searchCompany(id);
            if(companyDTO!=null){
                txtboughtcoName.setText(companyDTO.getComnyName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void rawBOnClick(MouseEvent mouseEvent) {
        BoughtDTO selectedItem = (BoughtDTO) tblBought.getSelectionModel().getSelectedItem();
        lblBoughtID.setText(selectedItem.getBoughtID());
        txtboughtCompanyIndex.setText(selectedItem.getComIndex());


    }




    public void expProfitCalculateOnAction(ActionEvent actionEvent){

        double exSellingPrice = Double.parseDouble(txtexSellingPrice.getText());
        //sout("Expected Selling Price : ",exSellingPrice);

        int boughtQty = Integer.parseInt(txtboughtQty.getText());
        //sout("Bought Qty  :",boughtQty);

        double boughtPrice = Double.parseDouble(txtBoughtPrice.getText());
        //sout("Bought Price  :",boughtPrice);

        double tax = Double.parseDouble(txtBoughtTax.getText());
        //sout("Tax  :",tax);

        double totalBoughtCost = (boughtPrice*boughtQty) + (boughtPrice*boughtQty*tax/100);
        //sout("Total Bought Cost  : ",totalBoughtCost);

        double SellingPrice = (exSellingPrice*boughtQty)-(exSellingPrice*boughtQty*tax/100);//Since its a selling, tax is reduced from the selling price
        //sout("Selling Price  :  ",SellingPrice);

        double expectedProfit = (SellingPrice-totalBoughtCost)*100/totalBoughtCost;
        //sout("Expected Profit  :  ",expectedProfit);

        txtExProfitMargin.setText(String.valueOf(expectedProfit));
        
        txtBoughtExProfit.setText(String.valueOf(SellingPrice-totalBoughtCost));

        //txtBoughtCost.setText(String.valueOf(totalBoughtCost));




    }

    public void costCalculateOnAction(ActionEvent actionEvent) {

        int qty=Integer.parseInt(txtboughtQty.getText());


        double price=  Double.parseDouble(txtBoughtPrice.getText());
        double boughtTax = Double.parseDouble(txtBoughtTax.getText());
        double totalCost = (qty*price) +   ((qty*price)*boughtTax / 100) ;
        txtBoughtCost.setText(String.valueOf(totalCost));

    }

    public void sout(String header,double data){
        System.out.println(header+"   "+data);
    }

    public void sout(String header,int data){
        System.out.println(header+"   "+data);
    }



}



