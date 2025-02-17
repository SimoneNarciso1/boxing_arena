package com.example.boxingarena.graphic_controller;


import com.example.boxingarena.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.sql.SQLException;

public class AdminController extends NavigatorController implements InitializableController {

    private   UserBean globalBean = new UserBean();


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
