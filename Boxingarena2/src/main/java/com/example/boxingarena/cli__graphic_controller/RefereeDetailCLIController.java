package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.SubscriptionBean;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RefereeDetailCLIController {

    public void detailCLI(int id) throws SQLException {
        System.out.println("Dettagli per il torneo ID: " + id);
        List<SubscriptionBean> subscriptions = SubscriptionControllerApp.getTournamentSubscription(id);

        if (subscriptions.isEmpty()) {
            System.out.println("Nessuna iscrizione trovata per questo torneo.");
        } else {
            System.out.println("Iscrizioni:");
            System.out.printf("%-10s %-20s %-20s %-15s %-10s\n", "ID Boxer", "Boxer", "Torneo", "ID Torneo", "Punti");
            for (SubscriptionBean subscription : subscriptions) {
                System.out.printf("%-10d %-20s %-20s %-15d %-10d\n",
                        subscription.getIdBoxer(),
                        subscription.getBoxer(),
                        subscription.getTournament(),
                        subscription.getIdTournament(),
                        subscription.getPoints());
            }
            CLIPrinter.printMessage("vuoi confermare il voto per un iscrizione? (Si digita --> 1/No  digita--> 2");
            Scanner scanner = new Scanner(System.in);
            if(Integer.parseInt(scanner.nextLine().trim()) == 1){
                System.out.println("\nInserisci l'ID Boxer:");
                try (Scanner scanner1 = new Scanner(System.in)) {
                    int boxerId = Integer.parseInt(scanner1.nextLine().trim());
                    SubscriptionBean selectedSubscription = subscriptions.stream()
                            .filter(s -> s.getIdBoxer() == boxerId)
                            .findFirst()
                            .orElse(null);
                    if (selectedSubscription != null) {
                        confirmVoteCLI(selectedSubscription);
                    } else {
                        System.out.println("ID Boxer non trovato.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero.");
                }
            }else{
                new RefereeViewCLIController().viewAll(id);
            }

        }
    }
    public void confirmVoteCLI(SubscriptionBean subscription) throws SQLException {
        System.out.printf("Conferma voto per il Boxer: %s (%d) nel Torneo: %s (%d) con punti: %d\n",
                subscription.getBoxer(), subscription.getIdBoxer(),
                subscription.getTournament(), subscription.getIdTournament(),
                subscription.getPoints());
       confirmCLI(  subscription.getIdBoxer(), subscription.getIdTournament(), subscription.getPoints());
        System.out.println("Voto confermato.");
    }
    public void confirmCLI(int boxerId, int tournamentId, int points) throws SQLException {



        SubscriptionControllerApp.updateSubscription(points, boxerId, tournamentId);
        System.out.println("Sottoscrizione aggiornata con successo!");

        detailCLI(tournamentId);
    }



}
