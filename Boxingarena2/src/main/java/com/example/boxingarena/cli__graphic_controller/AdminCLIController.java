package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.TournamentBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.controller_app.UserControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.bean.BoxingTournament;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;


import java.sql.SQLException;

public class AdminCLIController extends NavigatorCliController{
    private final Logger logger = Logger.getLogger(AdminCLIController.class.getName());
    private int idUser;


    public void start(int id) throws RuntimeException {
       idUser = id;
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                int choice = AdminManage();

                switch (choice) {
                    case 1 -> {
                        shouldExit = true;
                        new AdminCreateCLIController().createTournament(idUser);
                    }
                    case 2 -> {
                        shouldExit = true;
                        new AdminViewCLIController().viewTournament(idUser);
                    }

                    case 3 -> {
                        shouldExit = true;
                        logout();
                    }
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (InvalidFormatException e) {
                logger.log(Level.SEVERE, e.getMessage());
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private int AdminManage() {

        CLIPrinter.printMessage("*** What do you want to do ? ***\n");
        CLIPrinter.printMessage("1) Create Tournament\n");
        CLIPrinter.printMessage("2) View Tournament\n");
        CLIPrinter.printMessage("3) Logout\n");
        return getMenuChoice(1, 3);
    }






    private void logout() {
      //  UserControllerApp loginControllerApplication = new UserControllerApp();
       // UserBean userBean = new UserBean();
       // userBean.setId(idUser);
       // loginControllerApplication.logout(userBean);
        new LogInCLIController().start();
    }
}
