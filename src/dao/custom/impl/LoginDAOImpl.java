package dao.custom.impl;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.custom.LoginDAO;
import db.DBConnection;
import entity.login;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public void loginUser(JFXTextField txt, JFXPasswordField pwd, JFXButton btn) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM login WHERE username=? AND pw=?");
        ps.setString(1,txt.getText());
        ps.setString(2,pwd.getText());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("login now");
            Stage primaryStage = (Stage) btn.getScene().getWindow();
            primaryStage.close();
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
            stage.show();
//            Stage primaryStage = (Stage) btn.getScene().getWindow();
//            primaryStage.close();
//            Stage window=(Stage)this..getScene().getWindow();
//                   window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Dashboard.fxml"))));
//                   window.centerOnScreen();
////            Stage stage = new Stage();
////            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
////            stage.show();

        } else {
            System.out.println("no");
        }

    }

    @Override
    public boolean add(login login) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(login company) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public login search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<login> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
