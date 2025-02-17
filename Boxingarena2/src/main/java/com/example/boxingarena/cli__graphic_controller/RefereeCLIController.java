package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefereeCLIController extends NavigatorCliController{
    private final Logger logger = Logger.getLogger(RefereeCLIController.class.getName());
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
                        new RefereeViewCLIController().viewAll(idUser);
                    }
                    case 2 -> {
                        shouldExit = true;
                        logout();
                    }

                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (InvalidFormatException e) {
                logger.log(Level.SEVERE, e.getMessage());
            } catch (SQLException e) {
                throw new InvalidFormatException(e);
            }
        }
    }


    private int adminManage() {

        CLIPrinter.printMessage("*** What do you want to do ? ***%n");
        CLIPrinter.printMessage("1) View List%n");
        CLIPrinter.printMessage("2) Logout%n");
        return getMenuChoice(1, 2);
    }
    private void logout() {
        new LogInCLIController().start();
    }
}
