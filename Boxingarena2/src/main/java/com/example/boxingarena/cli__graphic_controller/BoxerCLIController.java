package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxerCLIController extends  NavigatorCliController{
    private final Logger logger = Logger.getLogger(BoxerCLIController.class.getName());
    private int idUser;

    public void start(int id) throws InvalidFormatException {
        idUser = id;
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                int choice = adminManage();

                switch (choice) {
                    case 1 -> {
                        shouldExit = true;
                        new BoxerJoinCLIController().joinTournament(idUser);
                    }
                    case 2 -> {
                        shouldExit = true;
                        new BoxerRankingCLIController().viewRanking(idUser);
                    }

                    case 3 -> {
                        shouldExit = true;
                        new BoxerSubCLIController().viewSabscription(idUser);
                    }

                    case 4 -> {
                        shouldExit = true;
                        logout();
                    }
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (InvalidFormatException e) {
                logger.log(Level.SEVERE, e.getMessage());
            } catch (IOException | SQLException e) {
                throw new InvalidFormatException(e);
            }
        }
    }


    private int adminManage() {

        CLIPrinter.printMessage("*** What do you want to do ? ***\n");
        CLIPrinter.printMessage("1) Join Tournament\n");
        CLIPrinter.printMessage("2) View Ranking\n");
        CLIPrinter.printMessage("3) View Subscription\n");
        CLIPrinter.printMessage("4) Logout\n");
        return getMenuChoice(1, 4);
    }
    private void logout() {
        new LogInCLIController().start();
    }

}
