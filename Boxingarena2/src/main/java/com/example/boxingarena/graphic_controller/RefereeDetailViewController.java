package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.SubscriptionBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.sql.SQLException;

public class RefereeDetailViewController extends NavigatorController implements InitializableController2 {
   private UserBean globalBean= new UserBean();

    @FXML
    private TableColumn refereeDetailConfirm;

    @FXML
    private TableColumn<SubscriptionBean, String> refereeDetailBoxer;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailBoxerId;

    @FXML
    private TextArea refereeDetailName;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailPoint;

    @FXML
    private TableColumn<SubscriptionBean, String> refereeDetailTournament;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailTournamentId;

    @FXML
    private AnchorPane refereeDetailView;

    @FXML
    private TableView<SubscriptionBean> table;

    @FXML
    private TextField boxerNameConfirm;
    @FXML
    private Spinner<Integer> boxerIdConfirm;
    @FXML
    private TextField boxerTournamentConfirm;
    @FXML
    private Spinner<Integer> boxerTournamentIdConfirm;

    @FXML
    private Spinner<Integer> boxerPointConfirm;

    private void detailView(int id) throws SQLException {
       refereeDetailName.setText(globalBean.getUsername());
       refereeDetailBoxerId.setCellValueFactory(new PropertyValueFactory<SubscriptionBean, Integer>("idBoxer"));
       refereeDetailBoxer.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, String>("boxer"));
       refereeDetailTournament.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, String>("tournament"));
       refereeDetailTournamentId.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, Integer>("idTournament"));
       refereeDetailPoint.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, Integer>("points"));
       var listMtTournament = SubscriptionControllerApp.getTournamentSubscription(id);
       ObservableList<SubscriptionBean> list = FXCollections.observableArrayList(listMtTournament);
       table.setItems(list);

       Callback<TableColumn<SubscriptionBean, String >, TableCell<SubscriptionBean, String>> cellFactory =(param) ->{
           return new TableCell<SubscriptionBean, String>(){
               @Override
               public void updateItem(String item, boolean empty){
                   super.updateItem(item, empty);
                   if(empty){
                       setGraphic(null);
                       setText(null);
                   }
                   else {
                       final Button confirmVote = new Button("ConfirmVote");
                       confirmVote.setOnAction(event -> {
                           SubscriptionBean subscription = getTableView().getItems().get(getIndex());
                           try {
                               confirmVote(subscription);
                           } catch (Exception e) {
                               throw new RuntimeException(e);
                           }
                       });
                       setGraphic(confirmVote);
                   }
               }

           };
       };

       refereeDetailConfirm.setCellFactory(cellFactory);
   }

    @FXML
    public void confirm() throws SQLException {
        var boxer = boxerNameConfirm.getText();
        var boxerId = boxerIdConfirm.getValue();
        var tournament = boxerTournamentConfirm.getText();
        var tournamentId = boxerTournamentIdConfirm.getValue();
        var point = boxerPointConfirm.getValue();

        new SubscriptionBean(boxerId, tournamentId, point, boxer, tournament);
        SubscriptionControllerApp.updateSubscription(point, boxerId, tournamentId);

        boxerNameConfirm.setText("");
        boxerIdConfirm.getValueFactory().setValue(0);
        boxerTournamentIdConfirm.getValueFactory().setValue(0);
        boxerPointConfirm.getValueFactory().setValue(0);
        boxerTournamentConfirm.setText("");

        detailView(tournamentId);
    }

    public void confirmVote(SubscriptionBean subscription) throws SQLException {
        boxerNameConfirm.setText(subscription.getBoxer());
        boxerTournamentConfirm.setText(subscription.getTournament());
        boxerIdConfirm.getValueFactory().setValue(subscription.getIdBoxer());
        boxerIdConfirm.setEditable(false);
        boxerTournamentIdConfirm.getValueFactory().setValue(subscription.getIdTournament());
        boxerIdConfirm.setEditable(false);
        boxerPointConfirm.getValueFactory().setValue(subscription.getPoints());
    }

    @FXML
    public void backToHomeFromRefereeDetail(){
        goToPageInit(RefreePage,globalBean);
    }

    @Override
    public void initializeData2(Object data, int id) throws Exception {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            refereeDetailName.setText(globalBean.getUsername());
            detailView(id);

        }

        SpinnerValueFactory<Integer> spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000, 0);
        boxerPointConfirm.setValueFactory(spinner1);

        SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000000, 0);
        boxerTournamentIdConfirm.setValueFactory(spinner2);

        SpinnerValueFactory<Integer> spinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000000, 0);
        boxerIdConfirm.setValueFactory(spinner3);

    }
}
