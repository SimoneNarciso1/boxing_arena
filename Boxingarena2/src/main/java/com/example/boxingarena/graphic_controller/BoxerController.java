package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.graphic_controller.BoxerRankingController;
import com.example.boxingarena.bean.*;
import com.example.boxingarena.controller_app.BoxerRankingControllerApp;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BoxerController extends NavigatorController implements  InitializableController {

    private UserBean globalBean = new UserBean();

    @FXML
    private AnchorPane boxerPage;

    @FXML
    private TextArea boxerName;

    @FXML
    public void joinTournament() throws Exception { // boxer viewAllTournament

        goToPageInit(ViewAllTournament,globalBean);

    }

    @FXML
    public void viewMySubscription() throws SQLException {
        goToPageInit(BoxerMySubscription,globalBean);
    }

    @FXML
    public void viewBoxerRanking(ActionEvent actionEvent) throws SQLException {
       goToPageInit(BoxerRanking,globalBean);

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
            boxerName.setText(globalBean.getUsername());
        }
    }
}
