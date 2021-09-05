package controller;

import bo.BOFactory;
import bo.custom.BoughtBO;
import bo.custom.SoldBO;
import dto.BoughtDTO;
import dto.SoldDTO;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SoldViewController implements Initializable {


    public AnchorPane rootSoldView;
    public TableView tblSoldView;
    public TableColumn colSoldID;
    public TableColumn colSoldCIndex;
    public TableColumn colSoldQty;
    public TableColumn colSoldPrice;
    public TableColumn colSoldProfit;
    public TableColumn colSoldDate;
    public TableColumn colSoldTax;
    public TableColumn coldSoldCost;

    SoldBO soldBO=(SoldBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SOLD);
   // BoughtBO boughtbo = (BoughtBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOUGHT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllSoldStocks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colSoldID.setCellValueFactory(new PropertyValueFactory<>("soldId"));
        colSoldCIndex.setCellValueFactory(new PropertyValueFactory<>("soldCIndex"));
        colSoldQty.setCellValueFactory(new PropertyValueFactory<>("soldQty"));
        colSoldPrice.setCellValueFactory(new PropertyValueFactory<>("soldPrice"));
        colSoldProfit.setCellValueFactory(new PropertyValueFactory<>("actualProfitMargin"));
        colSoldDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSoldTax.setCellValueFactory(new PropertyValueFactory<>("soldTax"));
    }

   private void loadAllSoldStocks() throws SQLException, ClassNotFoundException {
       ObservableList<SoldDTO> allStocks=soldBO.getAllSoldViewStocks();
       tblSoldView.setItems(allStocks);

   }



}
