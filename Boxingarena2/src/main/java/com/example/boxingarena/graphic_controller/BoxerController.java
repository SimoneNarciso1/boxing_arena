package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class BoxerController extends NavigatorController implements  InitializableController {

    private UserBean globalBean = new UserBean();



    @FXML
    private TextArea boxerName;

    @FXML
    public void joinTournament()  {

        goToPageInit(VIEW_ALL_TOURNAMENT,globalBean);

    }

    @FXML
    public void viewMySubscription()  {
        goToPageInit(BOXER_MY_SUBSCRIPTION,globalBean);
    }

    @FXML
    public void viewBoxerRanking(ActionEvent actionEvent)  {
       goToPageInit(BOXER_RANKING,globalBean);

    }

    @FXML
    public void logout(){
        goToPageInit(LOGIN,globalBean);
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
