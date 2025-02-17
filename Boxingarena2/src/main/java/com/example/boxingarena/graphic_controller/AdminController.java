package com.example.boxingarena.graphic_controller;


import com.example.boxingarena.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController extends NavigatorController implements InitializableController {

    private   UserBean globalBean = new UserBean();


    @FXML
    private TextArea adminName;



    @FXML
    public void createTournamentForm()  {

        goToPageInit(ADMIN_CREATE_TOURNAMENT,globalBean);

    }

    @FXML
    public void viewBoxingTournamentForm(ActionEvent actionEvent) {
        goToPageInit(VIEW_BOXER_TOURNAMENT,globalBean);

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
        adminName.setText(globalBean.getUsername());

        }

    }
}
