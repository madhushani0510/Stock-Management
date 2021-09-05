package dao.custom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.CrudDAO;
import entity.login;

public interface LoginDAO extends CrudDAO<login, String> {
    public void loginUser(JFXTextField txt, JFXPasswordField pwd, JFXButton btn)throws Exception;
}
