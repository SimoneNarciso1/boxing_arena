package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RefereeViewCLIController {

    public void viewAll(int id)throws SQLException {

            List<BoxingTournament> tournaments = TournamentControllerApp.getAllTournaments();

            if (tournaments.isEmpty()) {
                CLIPrinter.printMessage("\nNessun torneo disponibile.");
            } else {
                CLIPrinter.printMessage("\nTornei disponibili:");
                String tournamentString = String.format("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Nome", "Località", "Data", "Costo");
                CLIPrinter.printMessage(tournamentString);
                for (BoxingTournament tournament : tournaments) {
                    String tournamentDetail = String.format("%-5d %-20s %-20s %-15s %-10d\n",
                            tournament.getId(),
                            tournament.getName(),
                            tournament.getLocation(),
                            tournament.getDate().toString(),
                            tournament.getCost());
                    CLIPrinter.printMessage(tournamentDetail);
                }

                CLIPrinter.printMessage("\nInserisci l'ID del torneo per i dettagli:");
                try (Scanner scanner = new Scanner(System.in)) {
                    int tournamentId = Integer.parseInt(scanner.nextLine().trim());
                   new  RefereeDetailCLIController().detailCLI(tournamentId);
                } catch (NumberFormatException e) {
                    CLIPrinter.printMessage("\nInput non valido. Inserisci un numero intero.");
                }
            }
            new RefereeCLIController().start(id);
        }
    }

