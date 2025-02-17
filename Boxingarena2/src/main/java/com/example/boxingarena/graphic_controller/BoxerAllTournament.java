package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.*;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BoxerAllTournament extends NavigatorController implements InitializableController{

    private  UserBean globalBean = new UserBean();

    @FXML
    private TextArea boxerName1;
    @FXML
    private AnchorPane viewAllTournament;

    @FXML
    public TableColumn<BoxingTournament, Integer> viewAllTournamentCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> viewAllTournamentDate;

    @FXML
    public TableColumn<BoxingTournament, Integer> viewAllTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllTournamentLocation;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllTournamentName;

    @FXML
    public TableView<BoxingTournament> tableAllTournament;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllAction;



    public void viewAll() throws SQLException {
        viewAllTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        viewAllTournamentName.setCellValueFactory(new PropertyValueFactory<BoxingTournament, String>("name"));
        viewAllTournamentLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("location"));
        viewAllTournamentDate.setCellValueFactory(new PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        viewAllTournamentCost.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("cost"));
        var listMtTournament = TournamentControllerApp.getAllTournaments();
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        tableAllTournament.setItems(list);

        Callback<TableColumn<BoxingTournament, String >, TableCell<BoxingTournament, String>> cellFactory =(param) ->{
            return new TableCell<BoxingTournament, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        final Button payAndSubButton = new Button("PayAndSub");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                payAndSubscription(boxingTournament);
                                ReceiptBean receiptBean = new ReceiptBean();
                                List<SubscriptionBean> subscriptionBeanList = SubscriptionControllerApp.getTournamentSubscription(boxingTournament.getId());
                                for(SubscriptionBean subscription : subscriptionBeanList) {
                                    receiptBean.setDate(LocalDate.now());
                                    receiptBean.setIdBoxer(subscription.getIdBoxer());
                                    receiptBean.setIdTournament(subscription.getIdTournament());
                                    SubscriptionControllerApp.createReceipt(receiptBean);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        viewAllAction.setCellFactory(cellFactory);

    }
    public void payAndSubscription(BoxingTournament boxingTournament) throws Exception {
        TournamentBean tournamentBean = new TournamentBean(boxingTournament.getId(), boxingTournament.getName());
        SubscriptionControllerApp.payAndSubscription(globalBean, tournamentBean);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Pay and Subscription done");
        alert.show();
    }
    @FXML
    public void backToHomeFromAllTournament() {
        //boxerName.setText(globalBean.getUsername());
        goToPageInit(BoxerPage,globalBean);
    }

    @Override
    public void initializeData(Object data) throws SQLException {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            boxerName1.setText(globalBean.getUsername());
            viewAll();
        }
    }
}
