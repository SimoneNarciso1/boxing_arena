package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.bean.BoxerRankingBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.BoxerRankingControllerApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class BoxerRankingController extends NavigatorController implements  InitializableController{
    private UserBean globalBean = new UserBean();

    @FXML
    private TextArea boxerNameLead;

    @FXML
    private TableColumn<BoxerRankingBean, String> boxerNameBoxerRanking;

    @FXML
    private TableColumn<BoxerRankingBean, Integer> boxerNumberMatchBoxerRanking;

    @FXML
    private TableColumn<BoxerRankingBean, Integer> boxerPointBoxerRanking;

    @FXML
    private TableView<BoxerRankingBean> boxerTableLeadbooard;


    public void ranking() throws SQLException {
        boxerNameBoxerRanking.setCellValueFactory(new PropertyValueFactory<BoxerRankingBean, String>("boxer"));
        boxerNumberMatchBoxerRanking.setCellValueFactory(new PropertyValueFactory<BoxerRankingBean, Integer>("numberMatches"));
        boxerPointBoxerRanking.setCellValueFactory(new  PropertyValueFactory<BoxerRankingBean, Integer>("totalPoints"));
        var boxerRanking1 = BoxerRankingControllerApp.getBoxerRanking();
        ObservableList<BoxerRankingBean> list = FXCollections.observableArrayList(boxerRanking1);
        boxerTableLeadbooard.setItems(list);
    }




    @FXML
    public void backToHomeFromBoxerRanking(){
        goToPageInit(BoxerPage,globalBean);
    }

    @Override
    public void initializeData(Object data) throws SQLException {
        if (data instanceof UserBean userBean){
            globalBean.setId(userBean.getId());
            globalBean.setUsername(userBean.getUsername());
            boxerNameLead.setText(globalBean.getUsername());
            ranking();
        }
    }
}
