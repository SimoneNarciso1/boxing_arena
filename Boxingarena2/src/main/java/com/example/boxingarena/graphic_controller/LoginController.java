package com.example.boxingarena.graphic_controller;


import com.example.boxingarena.controller_app.UserControllerApp;
import com.example.boxingarena.bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;


public class LoginController extends NavigatorController implements Initializable {

    private final Logger logger = Logger.getLogger(LoginController.class.getName());

    private final UserControllerApp userControllerApp = new UserControllerApp();


    @FXML
    private AnchorPane loginForm;
    @FXML
    private AnchorPane adminForm;
    @FXML
    private AnchorPane refreeForm;
    @FXML
    private AnchorPane boxerForm;
    
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private ComboBox<String> loginRole;

    @FXML
    private Button loginBtn;



    @FXML
    private TextField adminUsername;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button adminSignupbtn;

    @FXML
    private Hyperlink adminSignin;

    @FXML
    private PasswordField adminConfirmPassword;

    @FXML
    private TextField boxerUsername;

    @FXML
    private PasswordField boxerPassword;

    @FXML
    private Button boxerSignupbtn;

    @FXML
    private Hyperlink boxerSignin;

    @FXML
    private PasswordField boxerConfirmPassword;

    @FXML
    private TextField refreeUsername;

    @FXML
    private PasswordField refreePassword;

    @FXML
    private Button refreeSignupbtn;

    @FXML
    private Hyperlink refreeSignin;

    @FXML
    private PasswordField refreeConfirmPassword;

    private static final String LOGIN_ERROR = "Login Error";


    

    private static final String ROLE_1 = "Admin";
    private static final String ROLE_2 = "Boxer";
    private static final String ROLE_3 = "Referee";



    @FXML
    public void login() {  // vai alla pagina  dal login
        try {
            UserBean userBean = new UserBean();
            userBean.setUsername(loginUsername.getText());
            userBean.setPassword(loginPassword.getText());
            userBean.checkField(userBean.getUsername(), userBean.getPassword());
            userBean = userControllerApp.login(userBean);


            if(userBean.getRole().equals(ROLE_1)) {
                goToPageInit(adminPage, userBean);
            }

            if(userBean.getRole().equals(ROLE_2)) {
                goToPageInit(BoxerPage, userBean);
            }

            if(userBean.getRole().equals(ROLE_3)) {

                goToPageInit(RefreePage, userBean);

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            showErrorDialog("Please retry later", LOGIN_ERROR);
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @FXML
    public void switchForm() {
        switch (loginRole.getSelectionModel().getSelectedItem()) {
            case ROLE_1 -> {
                adminForm.setVisible(true);
                refreeForm.setVisible(false);
                boxerForm.setVisible(false);
                loginForm.setVisible(false);
            }
            case ROLE_2 -> {
                adminForm.setVisible(false);
                refreeForm.setVisible(false);
                boxerForm.setVisible(true);
                loginForm.setVisible(false);
            }
            case ROLE_3 -> {
                refreeForm.setVisible(true);
                boxerForm.setVisible(false);
                refreeForm.setVisible(true);
                loginForm.setVisible(false);
            }
        }
    }

    private void setRoleList() {
        String[] role = {ROLE_1, ROLE_2, ROLE_3};
        List<String> listRole = new ArrayList<>(Arrays.asList(role));

        ObservableList<String> listData = FXCollections.observableArrayList(listRole);
        loginRole.setItems(listData);
    }

    @FXML
    public void signinForm() {
        loginForm.setVisible(true);
        adminForm.setVisible(false);
        refreeForm.setVisible(false);
        boxerForm.setVisible(false);
    }

    @FXML
    public void signup() {
        try {
            UserBean userBean = new UserBean();
            if (adminForm.isVisible()) {
                setVisibleOfSigning(userBean, adminUsername, adminPassword, adminConfirmPassword);
            }
            else if (refreeForm.isVisible()) {
                setVisibleOfSigning(userBean, refreeUsername, refreePassword, refreeConfirmPassword);
            }
            else if (boxerForm.isVisible()) {
                setVisibleOfSigning(userBean, boxerUsername, boxerPassword, boxerConfirmPassword);
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private void setVisibleOfSigning(UserBean userBean, TextField adminUsername, PasswordField adminPassword, PasswordField adminConfirmPassword) throws Exception {
        userBean.setUsername(adminUsername.getText());
        userBean.setPassword(adminPassword.getText());
        userBean.setCheckPassword(adminConfirmPassword.getText());
        userBean.setRole(loginRole.getValue());
        UserBean.checkIfPassIsEqual(userBean.getPassword(), userBean.getCheckPassword());
        boolean check = userControllerApp.signing(userBean);
        if(check) {
            adminForm.setVisible(false);
            loginForm.setVisible(true);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRoleList();
    }
    //endregion

}
