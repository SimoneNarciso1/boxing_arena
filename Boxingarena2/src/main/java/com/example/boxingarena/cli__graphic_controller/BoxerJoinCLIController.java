package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.*;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class BoxerJoinCLIController {

    public void joinTournament(int id ) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        List<BoxingTournament> tournaments = TournamentControllerApp.getAllTournaments();


        if (tournaments.isEmpty()) {
            System.out.println("Nessun torneo disponibile.");
        } else {
            System.out.println("Elenco di tutti i tornei:");
            System.out.printf("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Nome", "Località", "Data", "Costo");
            for (BoxingTournament tournament : tournaments) {
                System.out.printf("%-5d %-20s %-20s %-15s %-10d\n",
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getLocation(),
                        tournament.getDate().toString(),
                        tournament.getCost());
            }

            System.out.println("\nVuoi procedere con il pagamento e l'iscrizione per un torneo? Inserisci l'ID del torneo:");
            try (Scanner scanner = new Scanner(System.in)) {
                int tournamentId = Integer.parseInt(scanner.nextLine().trim());
                BoxingTournament selectedTournament = tournaments.stream()
                        .filter(t -> t.getId() == tournamentId)
                        .findFirst()
                        .orElse(null);

                if (selectedTournament != null) {
                    CLIPrinter.printMessage("Insert Boxer Name");
                    String UserName = reader.readLine();
                    payAndSubscriptionCLI(id, UserName, selectedTournament);
                } else {
                    System.out.println("ID torneo non trovato.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void payAndSubscriptionCLI( int id, String Name ,BoxingTournament boxingTournament) throws Exception {
        TournamentBean tournamentBean = new TournamentBean(boxingTournament.getId(), boxingTournament.getName());
        UserBean globalBean = new UserBean();
        globalBean.setId(id);
        globalBean.setUsername(Name);
        SubscriptionControllerApp.payAndSubscription(globalBean, tournamentBean);

        List<SubscriptionBean> subscriptionBeanList = SubscriptionControllerApp.getTournamentSubscription(boxingTournament.getId());
        for (SubscriptionBean subscription : subscriptionBeanList) {
            ReceiptBean receiptBean = new ReceiptBean();
            receiptBean.setDate(LocalDate.now());
            receiptBean.setIdBoxer(subscription.getIdBoxer());
            receiptBean.setIdTournament(subscription.getIdTournament());
            SubscriptionControllerApp.createReceipt(receiptBean);
        }

        System.out.println("Pagamento e iscrizione completati per il torneo: " + boxingTournament.getName());
        new BoxerCLIController().start(id);
    }
}
