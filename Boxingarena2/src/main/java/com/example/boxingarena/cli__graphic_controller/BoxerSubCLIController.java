package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;


import java.sql.SQLException;
import java.util.List;

public class BoxerSubCLIController {

    public void viewSabscription(int id) throws SQLException {
        List<BoxingTournament> subscriptions = SubscriptionControllerApp.getAllSubscriptionsByUser(id);
            if (subscriptions.isEmpty()) {
                CLIPrinter.printMessage("Non ci sono iscrizioni disponibili.");
            } else {
                CLIPrinter.printMessage("Le tue iscrizioni ai tornei:");
                System.out.printf("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Nome", "Località", "Data", "Costo");
                for (BoxingTournament subscription : subscriptions) {
                    System.out.printf("%-5d %-20s %-20s %-15s %-10d\n",
                            subscription.getId(),
                            subscription.getName(),
                            subscription.getLocation(),
                            subscription.getDate().toString(),
                            subscription.getCost());
                }
            }
            System.out.println("List of Subscription \nReturn To BoxerManager ");
            new BoxerCLIController().start(id);

    }
}
