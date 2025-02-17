package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.*;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.exception.DuplicateReceiptException;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.example.boxingarena.utilities.CLIPrinter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class BoxerJoinCLIController {

    public void joinTournament(int id ) throws SQLException, IOException, InvalidFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        List<BoxingTournament> tournaments = TournamentControllerApp.getAllTournaments();


        if (tournaments.isEmpty()) {
            CLIPrinter.printMessage("Nessun torneo disponibile.");
        } else {
            CLIPrinter.printMessage("Elenco di tutti i tornei:");
            String header = String.format("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Nome", "Località", "Data", "Costo");
            CLIPrinter.printMessage(header);
            for (BoxingTournament tournament : tournaments) {
                String tournamentDetails = String.format("%-5d %-20s %-20s %-15s %-10d\n",
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getLocation(),
                        tournament.getDate().toString(),
                        tournament.getCost());
                CLIPrinter.printMessage(tournamentDetails);
            }

            CLIPrinter.printMessage("\nVuoi procedere con il pagamento e l'iscrizione per un torneo? Inserisci l'ID del torneo:");
            try (Scanner scanner = new Scanner(System.in)) {
                int tournamentId = Integer.parseInt(scanner.nextLine().trim());
                BoxingTournament selectedTournament = tournaments.stream()
                        .filter(t -> t.getId() == tournamentId)
                        .findFirst()
                        .orElse(null);

                if (selectedTournament != null) {
                    CLIPrinter.printMessage("\nInsert Boxer Name");
                    String userName = reader.readLine();
                    payAndSubscriptionCLI(id, userName, selectedTournament);
                } else {
                   CLIPrinter.printMessage("\nID torneo non trovato.");
                }
            } catch (NumberFormatException e) {
                CLIPrinter.printMessage("\nInput non valido.");
            } catch (Exception e) {
                throw new InvalidFormatException(e);
            }
        }
    }
    public void payAndSubscriptionCLI( int id, String name ,BoxingTournament boxingTournament) throws InvalidFormatException, SQLException, ReceiptNotFoundException, IOException, DuplicateReceiptException {
        TournamentBean tournamentBean = new TournamentBean(boxingTournament.getId(), boxingTournament.getName());
        UserBean globalBean = new UserBean();
        globalBean.setId(id);
        globalBean.setUsername(name);
        SubscriptionControllerApp.payAndSubscription(globalBean, tournamentBean);

        List<SubscriptionBean> subscriptionBeanList = SubscriptionControllerApp.getTournamentSubscription(boxingTournament.getId());
        for (SubscriptionBean subscription : subscriptionBeanList) {
            ReceiptBean receiptBean = new ReceiptBean();
            receiptBean.setDate(LocalDate.now());
            receiptBean.setIdBoxer(subscription.getIdBoxer());
            receiptBean.setIdTournament(subscription.getIdTournament());
            SubscriptionControllerApp.createReceipt(receiptBean);
        }

        CLIPrinter.printMessage("Pagamento e iscrizione completati per il torneo: " + boxingTournament.getName());
        new BoxerCLIController().start(id);
    }
}
