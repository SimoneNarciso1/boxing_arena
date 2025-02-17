package com.example.boxingarena.graphic_controller;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.InputStream;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigatorController {
    private static final Logger logger = Logger.getLogger(NavigatorController.class.getName());
    public static final String LOGIN = "view/login.fxml";
    public static final String BOXER_MY_SUBSCRIPTION = "view/boxerMySubscription.fxml";
    public static final String ADMIN_CREATE_TOURNAMENT = "view/adminCreateTournament.fxml";
    public static final String ADMIN_PAGE = "view/adminPage.fxml";
    public static final String BOXER_PAGE = "view/boxerPage.fxml";
    public static final String BOXER_RANKING = "view/boxerRanking.fxml";
    public static final String REFREE_PAGE = "view/refereePage.fxml";
    public static final String REFREE_DETAIL_VIEW = "view/refereeDetailView.fxml";
    public static final String VIEW_ALL_TOURNAMENT = "view/viewAllTournament.fxml";
    public static final String VIEW_BOXER_TOURNAMENT = "view/viewboxerTournament.fxml";


    protected void goToPageInit(String page, Object obj) {
        try {
            NavigatorBase.getInstance().goToPageInit(page, obj);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            showErrorDialog("Error in the visualization of the page", "Visualization page error");
        }
    }
    protected void goToPageInit2(String page, Object obj, int id) {
        try {
            NavigatorBase.getInstance().goToPageInit2(page, obj, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            showErrorDialog("Error in the visualization of the page", "Visualization page error");
        }
    }



    protected void showErrorDialog(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
