package bo.custom.impl;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.LoginBO;
import dao.custom.LoginDAO;

public class LoginBOImpl implements LoginBO {
    private LoginDAO loginDAO = (LoginDAO) DAOFactory.getIntance().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public void loginUser(JFXTextField txt, JFXPasswordField pw, JFXButton btn) throws Exception {
        loginDAO.loginUser(txt, pw, btn);

    }
}
