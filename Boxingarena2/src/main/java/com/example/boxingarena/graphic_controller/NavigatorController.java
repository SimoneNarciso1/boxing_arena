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
    public static final String login = "view/login.fxml";
    public static final String BoxerMySubscription = "view/boxerMySubscription.fxml";
    public static final String adminCreateTournament = "view/adminCreateTournament.fxml";
    public static final String adminPage = "view/adminPage.fxml";
    public static final String BoxerPage = "view/boxerPage.fxml";
    public static final String BoxerRanking = "view/boxerRanking.fxml";
    public static final String RefreePage = "view/refereePage.fxml";
    public static final String RefreeDetailView = "view/refereeDetailView.fxml";
    public static final String ViewAllTournament = "view/viewAllTournament.fxml";
    public static final String viewBoxerTournament = "view/viewboxerTournament.fxml";


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

    protected void showAlertDialog(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected Image convertBlobToImage(Blob blob) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return new Image(inputStream);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            showErrorDialog("Error in the visualization of the tour", "Visualization tour error");
        }

        return null;
    }

    protected void setTextN(Integer translateY, Text toSet, Integer fontText) {
        toSet.setTextAlignment(TextAlignment.CENTER);
        toSet.setTranslateY(translateY);
        toSet.setFont(Font.font("Verdana Pro Cond Semibold", fontText));
    }
}
