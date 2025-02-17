package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.bean.UserBean;
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


public class RefereeController extends NavigatorController implements  InitializableController{

    private UserBean globalBean = new UserBean();

    @FXML
    public TableColumn refereeDetail;

    @FXML
    public TableColumn<BoxingTournament, Integer> refereeCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> refereeDate;

    @FXML
    public TableColumn<BoxingTournament, String> refereeLocation;

    @FXML
    private TextArea refereeName;

    @FXML
    private AnchorPane refereePage;

    @FXML
    public TableView<BoxingTournament> refereeTable;

    @FXML
    public TableColumn<BoxingTournament, Integer> refereeTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> refereeTournamentName;


    private void goToRefereePage() throws Exception {
      //  refereeName.setText(globalBean.getUsername());
        refereeTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        refereeTournamentName.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("name"));
        refereeLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("location"));
        refereeDate.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        refereeCost.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, Integer>("cost"));
        var listMtTournament = TournamentControllerApp.getAllTournaments();
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        refereeTable.setItems(list);

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
                        final Button payAndSubButton = new Button("Detail");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                detail(boxingTournament.getId());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        refereeDetail.setCellFactory(cellFactory);
    }

    private void detail(int id) throws SQLException {
        goToPageInit2(RefreeDetailView,globalBean, id);

    }



    @FXML
    public void logout(){
        goToPageInit(login,globalBean);
    }


    @Override
    public void initializeData(Object data) throws Exception {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            refereeName.setText(globalBean.getUsername());
            goToRefereePage();

        }

    }
}
