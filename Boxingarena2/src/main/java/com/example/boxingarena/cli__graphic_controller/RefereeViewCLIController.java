package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.TournamentControllerApp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RefereeViewCLIController {

    public void viewAll(int id)throws SQLException {

            List<BoxingTournament> tournaments = TournamentControllerApp.getAllTournaments();

            if (tournaments.isEmpty()) {
                System.out.println("Nessun torneo disponibile.");
            } else {
                System.out.println("Tornei disponibili:");
                System.out.printf("%-5s %-20s %-20s %-15s %-10s\n", "ID", "Nome", "Località", "Data", "Costo");
                for (BoxingTournament tournament : tournaments) {
                    System.out.printf("%-5d %-20s %-20s %-15s %-10d\n",
                            tournament.getId(),
                            tournament.getName(),
                            tournament.getLocation(),
                            tournament.getDate().toString(),
                            tournament.getCost());
                }

                System.out.println("\nInserisci l'ID del torneo per i dettagli:");
                try (Scanner scanner = new Scanner(System.in)) {
                    int tournamentId = Integer.parseInt(scanner.nextLine().trim());
                   new  RefereeDetailCLIController().detailCLI(tournamentId);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero.");
                }
            }
            new RefereeCLIController().start(id);
        }
    }

