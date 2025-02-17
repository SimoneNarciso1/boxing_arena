package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.SubscriptionBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RefereeDetailCLIController {

    public void detailCLI(int id) throws SQLException, InvalidFormatException {
        CLIPrinter.printMessage("Dettagli per il torneo ID: " + id);
        List<SubscriptionBean> subscriptions = SubscriptionControllerApp.getTournamentSubscription(id);

        if (subscriptions.isEmpty()) {
            CLIPrinter.printMessage("\nNessuna iscrizione trovata per questo torneo.");
        } else {
            CLIPrinter.printMessage("\nIscrizioni:");
            String iscrizioni = String.format("%-10s %-20s %-20s %-15s %-10s%n", "ID Boxer", "Boxer", "Torneo", "ID Torneo", "Punti");
            CLIPrinter.printMessage(iscrizioni);
            for (SubscriptionBean subscription : subscriptions) {
                String subscriptionDetails= String.format("%-10d %-20s %-20s %-15d %-10d%n",
                        subscription.getIdBoxer(),
                        subscription.getBoxer(),
                        subscription.getTournament(),
                        subscription.getIdTournament(),
                        subscription.getPoints());
                CLIPrinter.printMessage(subscriptionDetails);
            }
            CLIPrinter.printMessage("vuoi confermare il voto per un iscrizione? (Si digita --> 1/No  digita--> 2");
            Scanner scanner = new Scanner(System.in);
            if(Integer.parseInt(scanner.nextLine().trim()) == 1){
                CLIPrinter.printMessage("\nInserisci l'ID Boxer:");
                try (Scanner scanner1 = new Scanner(System.in)) {
                    int boxerId = Integer.parseInt(scanner1.nextLine().trim());
                    SubscriptionBean selectedSubscription = subscriptions.stream()
                            .filter(s -> s.getIdBoxer() == boxerId)
                            .findFirst()
                            .orElse(null);
                    if (selectedSubscription != null) {
                        confirmVoteCLI(selectedSubscription);
                    } else {
                        CLIPrinter.printMessage("ID Boxer non trovato.");
                    }
                } catch (NumberFormatException e) {
                    CLIPrinter.printMessage("Input non valido. Inserisci un numero intero.");
                }
            }else{
                new RefereeViewCLIController().viewAll(id);
            }

        }
    }
    public void confirmVoteCLI(SubscriptionBean subscription) throws SQLException, InvalidFormatException {
       String confirmString = String.format("%nConferma voto per il Boxer: %s (%d) nel Torneo: %s (%d) con punti: %d%n",
                subscription.getBoxer(), subscription.getIdBoxer(),
                subscription.getTournament(), subscription.getIdTournament(),
                subscription.getPoints());
        CLIPrinter.printMessage(confirmString);
       confirmCLI(  subscription.getIdBoxer(), subscription.getIdTournament(), subscription.getPoints());
        CLIPrinter.printMessage("\nVoto confermato.");
    }
    public void confirmCLI(int boxerId, int tournamentId, int points) throws SQLException, InvalidFormatException {



        SubscriptionControllerApp.updateSubscription(points, boxerId, tournamentId);
        CLIPrinter.printMessage("\nSottoscrizione aggiornata con successo!");

        detailCLI(tournamentId);
    }

}
