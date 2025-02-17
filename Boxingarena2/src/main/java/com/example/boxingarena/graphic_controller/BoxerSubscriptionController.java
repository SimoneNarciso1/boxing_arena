package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.graphic_controller.BoxerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDate;

public class BoxerSubscriptionController extends NavigatorController implements InitializableController{


    @FXML
    private AnchorPane boxerMySubscription;

    @FXML
    public TableColumn<BoxingTournament, Integer> boxerMySubscriptionCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> boxerMySubscriptionDate;

    @FXML
    public TableColumn<BoxingTournament, Integer> boxerMySubscriptionId;

    @FXML
    public TableColumn<BoxingTournament, String> boxerMySubscriptionLocation;

    @FXML
    public TextArea boxerMySubscriptionName;

    @FXML
    public TableView<BoxingTournament> boxerMySubscriptionTable;

    @FXML
    public TableColumn<BoxingTournament, String> boxerMySubscriptionTournamentName;


    private static UserBean globalBean = new UserBean();

   public  void viewSubscription() throws SQLException {
          boxerMySubscriptionId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
          boxerMySubscriptionTournamentName.setCellValueFactory(new PropertyValueFactory<BoxingTournament, String>("name"));
          boxerMySubscriptionLocation.setCellValueFactory(new PropertyValueFactory<BoxingTournament, String>("location"));
          boxerMySubscriptionDate.setCellValueFactory(new PropertyValueFactory<BoxingTournament, LocalDate>("date"));
          boxerMySubscriptionCost.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("cost"));
          var listMtTournament = SubscriptionControllerApp.getAllSubscriptionsByUser(globalBean.getId());
          ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
          boxerMySubscriptionTable.setItems(list);

   }
    @FXML
    public void backToHomeFromSubscriptionTable(){
       goToPageInit(BoxerPage,globalBean);
    }

    @Override
    public void initializeData(Object data) throws SQLException {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            boxerMySubscriptionName.setText(globalBean.getUsername());
            viewSubscription();
        }
    }
}
