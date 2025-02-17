package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.exception.InvalidFormatException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxerCLIController extends  NavigatorCliController{
    private final Logger logger = Logger.getLogger(AdminCLIController.class.getName());
    private int idUser;

    public void start(int id) {
        idUser = id;
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                int choice = AdminManage();

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
                throw new RuntimeException(e);
            }
        }
    }


    private int AdminManage() {

        System.out.println("*** What do you want to do ? ***\n");
        System.out.println("1) Join Tournament\n");
        System.out.println("2) View Ranking\n");
        System.out.println("3) View Subscription\n");
        System.out.println("4) Logout\n");
        return getMenuChoice(1, 4);
    }
    private void logout() {
        //  UserControllerApp loginControllerApplication = new UserControllerApp();
        // UserBean userBean = new UserBean();
        // userBean.setId(idUser);
        // loginControllerApplication.logout(userBean);
        new LogInCLIController().start();
    }

}
