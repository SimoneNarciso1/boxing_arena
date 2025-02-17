package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;


import java.sql.SQLException;
import java.util.List;

public class BoxerSubCLIController {

    public void viewSabscription(int id) throws SQLException, InvalidFormatException {
        List<BoxingTournament> subscriptions = SubscriptionControllerApp.getAllSubscriptionsByUser(id);
            if (subscriptions.isEmpty()) {
                CLIPrinter.printMessage("Non ci sono iscrizioni disponibili.");
            } else {
                CLIPrinter.printMessage("Le tue iscrizioni ai tornei:");
                String subs = String.format("%-5s %-20s %-20s %-15s %-10s%n", "ID", "Nome", "Località", "Data", "Costo");
                CLIPrinter.printMessage(subs);
                for (BoxingTournament subscription : subscriptions) {
                   String sub = String.format("%-5d %-20s %-20s %-15s %-10d%n",
                            subscription.getId(),
                            subscription.getName(),
                            subscription.getLocation(),
                            subscription.getDate().toString(),
                            subscription.getCost());
                   CLIPrinter.printMessage(sub);
                }
            }
            CLIPrinter.printMessage("\nList of Subscription \nReturn To BoxerManager ");
            new BoxerCLIController().start(id);

    }
}
