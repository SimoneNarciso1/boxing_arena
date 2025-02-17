package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.utilities.CLIPrinter;
import com.example.boxingarena.controller_app.UserControllerApp;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class LogInCLIController  extends  NavigatorCliController{



    private final Logger logger = Logger.getLogger(LogInCLIController.class.getName());



    public void start() {
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                int choice = showMenu();

                switch (choice) {
                    case 1 -> {
                        shouldExit = true;
                        login();
                    }
                    case 2 -> {
                        shouldExit = true;
                        signin();
                        return;
                    }
                    default -> throw new IllegalArgumentException("Invalid choice");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }


    private void signin() throws IOException, SQLException {
        UserControllerApp userControllerApp = new UserControllerApp();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CLIPrinter.printMessage("username: ");
        String username = reader.readLine();
        CLIPrinter.printMessage("password: ");
        String password = reader.readLine();
        CLIPrinter.printMessage("check password: ");
        String checkPassword = reader.readLine();
        CLIPrinter.printMessage("role: ");
        String role = reader.readLine();
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setRole(role);
        UserBean.checkIfPassIsEqual(password, checkPassword);
        userControllerApp.signing(userBean);
        CLIPrinter.printMessage("Sign in");
        showMenu();
    }
    private void login() {
        UserControllerApp userControllerApp = new UserControllerApp();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            CLIPrinter.printMessage("username: ");
            String username = reader.readLine();
            CLIPrinter.printMessage("password: ");
            String password = reader.readLine();
            UserBean userBean = new UserBean();
            userBean.setUsername(username);
            userBean.setPassword(password);
            userControllerApp.login(userBean);
            CLIPrinter.printMessage("Logged in");
            int id = userBean.getId();


            if (userBean.getRole().equals("Admin")) new AdminCLIController().start(id);

            if (userBean.getRole().equals("Boxer")) new BoxerCLIController().start(id);

            if (userBean.getRole().equals("Referee")) new RefereeCLIController().start(id);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }



    private int showMenu() {
        helloMessage();
        CLIPrinter.printMessage("*** What should I do for you? ***\n");
        CLIPrinter.printMessage("1) Login\n");
        CLIPrinter.printMessage("2) Register\n");

        return getMenuChoice(1, 2);
    }

    private void helloMessage() {
        CLIPrinter.printMessage("Welcome to Boxing Arena ");
    }


}
