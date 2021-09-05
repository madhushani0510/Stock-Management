package controller;

import bo.BOFactory;
import bo.custom.CompanyBO;
import bo.custom.WatchlistBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.WatchlistDAO;
import db.DBConnection;
import dto.CompanyDTO;
import dto.DividendDTO;
import dto.WatchlistDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class WatchlistFormController implements Initializable {
    public JFXTextField txtWComIndex;
    public Label lblWID;
    public DatePicker txtWDate;
    public DatePicker txtDBDate;
    public JFXButton btnWClear;
    public JFXButton btnWDelete;
    public JFXButton btnWUpdate;
    public JFXButton btnWSave;
    public TableView tblWatchlist;
    public TableColumn colWComIndex;
    public TableColumn colWID;
    public TableColumn colWDate;
    public TableColumn colDBDate;
    public JFXButton btnWReport;


    WatchlistBO watchlistBO= (WatchlistBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.WATCHLIST);
    CompanyBO companyBO= (CompanyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COMPANY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllWatchlist();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setWatchlistID();

        colWID.setCellValueFactory(new PropertyValueFactory<>("watchlistId"));
        colWComIndex.setCellValueFactory(new PropertyValueFactory<>("comIndex"));
        colWDate.setCellValueFactory(new PropertyValueFactory<>("watchlistDate"));
        colDBDate.setCellValueFactory(new PropertyValueFactory<>("DefaultBoardDate"));

    }

    private void setWatchlistID() {
            try {
                String wid=watchlistBO.getLastID();

                if (wid != null){
                    wid =wid.split("[A-Z]")[1];
                    wid = "W" + (Integer.parseInt(wid)+1);
                    lblWID.setText(wid);
                }else {
                    lblWID.setText("W1");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }

    private void loadAllWatchlist() throws SQLException, ClassNotFoundException {
        ObservableList<WatchlistDTO> allWatchlist=watchlistBO.getAllWatchlist();
        tblWatchlist.setItems(allWatchlist);
    }

    public void clearWOnAction(ActionEvent actionEvent) {

        lblWID.setText("");
        txtWComIndex.setText("");
        txtWDate.setValue(null);
        txtDBDate.setValue(null);


    }

    public void deleteWonAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        WatchlistDTO selectedItem = (WatchlistDTO) tblWatchlist.getSelectionModel().getSelectedItem();
        String id = selectedItem.getWatchlistId();

        try {
            boolean isDeleted=watchlistBO.deleteFromWatchlist(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed",ButtonType.OK).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllWatchlist();

    }




    public void updateWOnAction(ActionEvent actionEvent) {
    }






    public void saveWonAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String wid = lblWID.getText();
        String comIndex = txtWComIndex.getText();
        String wDate = txtWDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
        String dbDate = txtDBDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);

        try {
            boolean isAdded = watchlistBO.addToWatchlist(new WatchlistDTO(wid, comIndex, wDate, dbDate));
            new Alert(Alert.AlertType.CONFIRMATION, "Added", ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Try Again", ButtonType.OK).show();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllWatchlist();

    }


    public void reportWOnAction(ActionEvent actionEvent)  {

        try {
            InputStream is = this.getClass().getResourceAsStream("/report/WatchlistReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint js = JasperFillManager.fillReport(jr,null,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(js);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
