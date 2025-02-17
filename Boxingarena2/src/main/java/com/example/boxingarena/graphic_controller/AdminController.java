package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.exception.DuplicateReceiptException;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.example.boxingarena.utilities.FileGenerator;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class AdminController extends NavigatorController implements InitializableController {

    private   UserBean globalBean = new UserBean();
    private static final String LOCATION = "location";


   @FXML
    private AnchorPane adminpage;


    @FXML
    private TextArea adminName;



    @FXML
    public void createTournamentForm() throws SQLException {

        goToPageInit(adminCreateTournament,globalBean);

    }

    @FXML
    public void viewBoxingTournamentForm(ActionEvent actionEvent) throws Exception {
        goToPageInit(viewBoxerTournament,globalBean);

    }




    @FXML
    public void logout(){
       goToPageInit(login,globalBean);
    }




    @Override
    public void initializeData(Object data) {
        if (data instanceof UserBean userBean){
        globalBean.setId(userBean.getId());
        globalBean.setUsername(userBean.getUsername());
        adminName.setText(globalBean.getUsername());

        }

    }
}
