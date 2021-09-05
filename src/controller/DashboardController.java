package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    public JFXButton btnDashboard;
    public JFXButton btnCompany;
    public AnchorPane rootDash;

    public void initialize() throws IOException {
        setUI("DefaultForm.fxml");
    }

    public void setUI(String location) throws IOException {
        rootDash.getChildren().clear();
        AnchorPane load = FXMLLoader.load(this.getClass().getResource("../view/"+location));
        rootDash.getChildren().addAll(load.getChildren());
//        rootDash.getChildren();
//        rootDash.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+m+".fxml")));
  }
//    private void Default() throws IOException {
//        setUI("DefaultForm");
//    }

    public void companyOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CompanyForm.fxml");
    }

    public void boughtOnAction(ActionEvent actionEvent) throws IOException {
        setUI("BoughtForm.fxml");
    }

    public void soldOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SoldForm.fxml");
    }

    public void dividendOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DividendForm.fxml");
    }

    public void rightissuesOnAction(ActionEvent actionEvent) throws IOException {
        setUI("RightissuesForm.fxml");
    }

    public void watchlistOnAction(ActionEvent actionEvent) throws IOException {
        setUI("WatchlistForm.fxml");
    }


    public void DashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DefaultForm.fxml");
    }
}
