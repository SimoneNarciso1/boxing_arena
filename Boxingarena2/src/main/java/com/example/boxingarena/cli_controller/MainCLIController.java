package com.example.boxingarena.cli_controller;

import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.UserControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCLIController {



    private final Logger logger = Logger.getLogger(MainCLIController.class.getName());



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
        UserControllerApp.signing(userBean);
        CLIPrinter.printMessage("Sign in");
        showMenu();
    }
    private void login() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            CLIPrinter.printMessage("username: ");
            String username = reader.readLine();
            CLIPrinter.printMessage("password: ");
            String password = reader.readLine();
            UserBean userBean = new UserBean();
            userBean.setUsername(username);
            userBean.setPassword(password);
            UserControllerApp.login(userBean);
            CLIPrinter.printMessage("Logged in");

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private int getMenuChoice(int min, int max) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            CLIPrinter.printMessage("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            CLIPrinter.printMessage("Invalid option\n");
        }
        return choice;
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
