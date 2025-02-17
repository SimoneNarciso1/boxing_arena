package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.utilities.FileGenerator;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class AdminViewTournament extends NavigatorController implements InitializableController{

    private   UserBean globalBean = new UserBean();


    @FXML
    private AnchorPane viewboxerTournament;

    @FXML
    private TextArea adminNameTournament;

    @FXML
    public TableView<BoxingTournament> tableBoxingTournament;



    @FXML
    public TableColumn<BoxingTournament, Integer> viewBoxingTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> viewBoxingTournamentName;

    @FXML
    public TableColumn<BoxingTournament, String> viewBoxingTournamentLocation;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> viewBoxingTournamentDate;

    @FXML
    public TableColumn viewBoxingTournamentButton;



    private void viewTournament() throws SQLException {
        viewBoxingTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        viewBoxingTournamentName.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("name"));
        viewBoxingTournamentLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("location"));
        viewBoxingTournamentDate.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        var listMtTournament = TournamentControllerApp.getAllByAdminIdTournaments(globalBean.getId());
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        tableBoxingTournament.setItems(list);

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
                        final Button payAndSubButton = new Button("Download");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                download(boxingTournament);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        viewBoxingTournamentButton.setCellFactory(cellFactory);
    }


    public void download(BoxingTournament tournament) throws SQLException, CsvValidationException, IOException {
        var boxerList = SubscriptionControllerApp.getTournamentSubscription(tournament.getId());
        FileGenerator.generateFile(tournament.getName(), boxerList.toString());
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("File created successfully!");
    }



    @FXML
    public void backToHomeFromBoxingTournament(){
        goToPageInit(adminPage,globalBean);
    }


    @Override
    public void initializeData(Object data) throws SQLException {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            adminNameTournament.setText(globalBean.getUsername());
            viewTournament();

        }
    }
}
