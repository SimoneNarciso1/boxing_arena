package com.example.boxingarena;
import com.example.boxingarena.graphic_controller.NavigatorBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.boxingarena.graphic_controller.NavigatorController.login;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(login));
        Scene scene = new Scene(fxmlLoader.load(),1020, 640);
        NavigatorBase navigator = NavigatorBase.getInstance(stage);
        navigator.getStg().setScene(scene);
        navigator.getStg().setTitle("BoxingArena");
        navigator.getStg().setResizable(false);
        navigator.getStg().show();
    }

}