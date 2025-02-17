package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.TournamentControllerApp;

import java.sql.SQLException;
import java.util.List;

public class AdminViewCLIController {

    public void viewTournament(int idUser) throws SQLException {
        List<BoxingTournament> tournaments = TournamentControllerApp.getAllByAdminIdTournaments(idUser);

        if (tournaments.isEmpty()) {
            System.out.println("Nessun torneo disponibile.");
        } else {
            System.out.println("Elenco tornei disponibili:");
            System.out.printf("%-10s %-30s %-20s %-15s\n", "ID", "Nome", "Località", "Data");
            for (BoxingTournament tournament : tournaments) {
                System.out.printf("%-10d %-30s %-20s %-15s\n",
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getLocation(),
                        tournament.getDate().toString());
            }
        }
        new AdminCLIController().start(idUser);
    }
}
