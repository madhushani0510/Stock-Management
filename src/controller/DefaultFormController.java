package controller;

import bo.BOFactory;
import bo.custom.BoughtBO;
import bo.custom.SoldBO;
import com.jfoenix.controls.JFXTextField;
import dto.BoughtDTO;
import dto.SoldDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DefaultFormController implements Initializable {
    public AnchorPane rootDefault;
    public JFXTextField txtDefaultProfit;
    public JFXTextField txtDefaultLoss;
    public JFXTextField txtInvestment;
    public Hyperlink hyperCSEDaily;
    public Hyperlink hyperCSEWeekly;
    public Hyperlink hyperCSEMonthly;
    public Hyperlink hyperFGE;

    BoughtBO boughtBO= (BoughtBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOUGHT);
    SoldBO soldBO = (SoldBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SOLD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //loadProfit();
       // loadLoss();

        try {
            loadTotalInvestment();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadTotalLossProfit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadLoss() {


    }

    private void loadProfit() {

    }

    private void loadTotalInvestment() throws SQLException, ClassNotFoundException {
        String lastID = boughtBO.getLastID();
        ObservableList<BoughtDTO> allStocks = boughtBO.getAllStocks();

        int count = 0 ;
        double totalInvestment = 0.0;

        while (!allStocks.get(count).getBoughtID().equals(lastID)) {
            double boughtPrice = allStocks.get(count).getBoughtPrice();
            int boughtQty = allStocks.get(count).getBoughtQty();
            double tax = allStocks.get(count).getBoughtTax();
            double totalBoughtCost = (boughtPrice*boughtQty) + (boughtPrice*boughtQty*tax/100);

            totalInvestment += totalBoughtCost;
            count++;
        }

        txtInvestment.setText(String.valueOf(totalInvestment));
        //System.out.println(count);
    }

    private void loadTotalLossProfit() throws SQLException, ClassNotFoundException {
        String lastID = soldBO.getLastID();
        ObservableList<SoldDTO> allStocks = soldBO.getAllSoldViewStocks();

        int count = 0 ;
        double totalLoss = 0.0;
        double totalProfit = 0.0;

        while (!allStocks.get(count).getSoldId().equals(lastID)) {

            double actualProfitMargin = allStocks.get(count).getActualProfitMargin();

            if(actualProfitMargin < 0){
                System.out.println("Loss : "+actualProfitMargin);
                totalLoss = totalLoss + actualProfitMargin;}

            if(actualProfitMargin > 0){
                System.out.println("Profit : "+actualProfitMargin);
                totalProfit =+ actualProfitMargin;}


            count++;
        }

        txtDefaultLoss.setText(String.valueOf(totalLoss));        //System.out.println(count);
        txtDefaultProfit.setText(String.valueOf(totalProfit));
    }

    public void cseDailyOnAction(ActionEvent actionEvent) {
        try {
            String url = "https://www.cse.lk/pages/cse-daily/cse-daily.component.html";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void cseWeeklyOnAction(ActionEvent actionEvent) {
        try {
            String url = "https://www.cse.lk/pages/cse-weekly/cse-weekly.component.html";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cseMonthlyOnAction(ActionEvent actionEvent) {
        try {
            String url = "https://www.cse.lk/pages/cse-monthly/cse-monthly.component.html";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fGEOnAction(ActionEvent actionEvent) {
        try {
            String url = "https://online.fge.lk/atsweb/login;jsessionid=E9CE84E6F5C4A22B39F4EA247C289F68";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
