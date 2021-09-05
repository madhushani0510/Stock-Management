package controller;

import bo.BOFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.custom.LoginBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.xml.stream.Location;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXButton btnLogin;
    public JFXButton btnClearLogin;
    public JFXButton btnForgotPWD;
    public AnchorPane rootLogin;
    public ImageView imglogin;

    private LoginBO loginBO= (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        try {
            loginBO.loginUser(txtUserName,pwdPassword,btnLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
       String username=txtUserName.getText();
       String password=pwdPassword.getText();

       if(username.length()>0 && password.length()>0){
           //if (username=="ijse" && password=="1234"){

                if(username.equalsIgnoreCase("ijse")&& password.equals("1234")){
                   Stage window=(Stage)this.rootLogin.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Dashboard.fxml"))));
                   window.centerOnScreen();

               }else {
                   txtUserName.setFocusColor(Paint.valueOf("red"));
                   pwdPassword.setFocusColor(Paint.valueOf("red"));
                   pwdPassword.requestFocus();
                   txtUserName.requestFocus();
                }

       }else {
       new Alert(Alert.AlertType.WARNING,"User Name or Password Empty..",ButtonType.OK).show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtUserName.setText("");
        pwdPassword.setText("");
    }
}
