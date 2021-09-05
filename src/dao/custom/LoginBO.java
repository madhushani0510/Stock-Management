package dao.custom;

import bo.SuperBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public interface LoginBO extends SuperBO {
    public void loginUser(JFXTextField txt, JFXPasswordField pw, JFXButton btn)throws Exception;
}
