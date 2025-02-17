package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.TournamentBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AdminCreateTournamentController extends NavigatorController implements  InitializableController {
    private UserBean globalBean = new UserBean();

    @FXML
    private AnchorPane adminCreateTournament;
    @FXML
    private TextArea adminName1;
    @FXML
    private TextField tournamentName;

    @FXML
    private TextField tournamentLocation;

    @FXML
    private DatePicker tournamentDate;

    @FXML
    private Spinner<Integer> tournamentCost;

    @FXML
    private Spinner<Integer> tournamentNumber;


    @FXML
    public void createTournament() throws Exception {

        TournamentBean tournamentBean = new TournamentBean();
        tournamentBean.setName(tournamentName.getText());
        tournamentBean.setLocation(tournamentLocation.getText());
        tournamentBean.setCost(tournamentCost.getValue());
        tournamentBean.setNumber(tournamentNumber.getValue());
        tournamentBean.setDate(tournamentDate.getValue());
        tournamentBean.checkField(tournamentBean.getName(), tournamentBean.getLocation(), tournamentBean.getDate(), tournamentBean.getCost(), tournamentBean.getNumber());

        TournamentControllerApp.createTournament(globalBean, tournamentBean);

    }

    @FXML
    public void backToHomeFromCreate() {
       goToPageInit(adminPage,globalBean);
    }

    @Override
    public void initializeData(Object data) {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            adminName1.setText(globalBean.getUsername());
        }
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,1);
        tournamentCost.setValueFactory(spinner);

        SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        tournamentNumber.setValueFactory(spinner2);
    }
}
